import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizationComparison {

    public static void main(String[] args) throws InterruptedException {
        int[] threadCounts = {4, 8, 16, 32, 64};  // Брой на потоците за всеки тест

        System.out.println("Performance comparison between Lock and Atomic operations:");
        for (int threadCount : threadCounts) {
            System.out.println("\nTesting with " + threadCount + " threads:");
            testPerformance(new LockCounter(), threadCount);
            testPerformance(new AtomicCounter(), threadCount);
        }
    }

    private static void testPerformance(Counter counter, int threadCount) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        long startTime = System.nanoTime();

        // Пускане на задачите, за да инкрементират брояча в паралелни потоци
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 100_000; j++) {
                    counter.increment();
                }
            });
        }

        // Затваряне и изчакване на приключването на всички задачи
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long duration = System.nanoTime() - startTime;
        System.out.println(counter.getClass().getSimpleName() + " - Total count: " + counter.getValue()
                + ", Time taken: " + duration / 1_000_000 + " ms");
    }

    interface Counter {
        void increment();
        int getValue();
    }

    // Реализация с Lock
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

    // Реализация с AtomicInteger
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
