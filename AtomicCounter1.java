import org.w3c.dom.css.Counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter1 implements Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
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
