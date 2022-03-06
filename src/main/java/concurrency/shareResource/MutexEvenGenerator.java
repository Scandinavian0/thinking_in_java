package concurrency.shareResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 显示的Lock对象，虽然代码缺乏优雅性，但是更加灵活
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class MutexEvenGenerator extends IntGenerator{
    private int currentEvenValue=0;
    private Lock lock=new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield(); // Cause failure faster;
            ++currentEvenValue;
            return currentEvenValue;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
