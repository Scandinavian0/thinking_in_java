package concurrency.block;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程阻塞
 * @Date: 2022/3/7
 * @Author: Everglow
 */

class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepBlodk.run!");
    }
}
class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
       try{
           System.out.println("Wating,");
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
public class Interruption {
}
