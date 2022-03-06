package concurrency.shareResource;

import concurrency.shareResource.IntGenerator;

/**
 * @Description: 同步控制
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue=0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield(); // Cause failure faster;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
