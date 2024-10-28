import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        int[] threadCounts = {4, 8, 16, 32, 64, 128};

        for (int threadCount : threadCounts) {
            System.out.println("Testing with " + threadCount + " threads:");
            runTest(new LockCounter(), threadCount);
            runTest(new AtomicCounter(), threadCount);
            System.out.println();
        }
    }

    private static void runTest(Counter counter, int threadCount) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        long startTime = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 100_000; j++) {
                    counter.increment();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long duration = System.nanoTime() - startTime;
        System.out.println(counter.getClass().getSimpleName() + " - Total Count: " + counter.getValue()
                + ", Time taken: " + duration / 1_000_000 + " ms");
    }

    interface Counter {
        void increment();
        int getValue();
    }

    static class LockCounter implements Counter {
        private int count = 0;
        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public int getValue() {
            return count;
        }
    }

    static class AtomicCounter implements Counter {
        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public void increment() {
            count.incrementAndGet();
        }

        @Override
        public int getValue() {
            return count.get();
        }
    }
}
