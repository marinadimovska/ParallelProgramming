import org.w3c.dom.css.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter1 implements Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public String getListStyle() {
        return null;
    }

    @Override
    public String getSeparator() {
        return null;
    }
}
