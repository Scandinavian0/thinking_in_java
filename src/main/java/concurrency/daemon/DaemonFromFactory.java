package concurrency.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2022/2/28
 * @Author: Everglow
 */
public class DaemonFromFactory implements Runnable{
    @Override
    public void run() {
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread()+":"+this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i=0;i<10;i++){
            executorService.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
