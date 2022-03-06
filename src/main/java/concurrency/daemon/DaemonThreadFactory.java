package concurrency.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * @Description:
 * @Date: 2022/2/28
 * @Author: Everglow
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
