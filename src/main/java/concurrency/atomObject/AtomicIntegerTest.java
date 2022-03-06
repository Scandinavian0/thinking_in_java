package concurrency.atomObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Date: 2022/3/3
 * @Author: Everglow
 */
public class AtomicIntegerTest implements Runnable{
    private AtomicInteger i=new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }
    private void evenIncrement(){
        i.addAndGet(2);
    }
    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Aborting");
                System.exit(0);
            }
        },5000); // Terminate(结束) after 5 seconds
        ExecutorService exec= Executors.newCachedThreadPool();
        AtomicIntegerTest alt=new AtomicIntegerTest();
        exec.execute(alt);
        while (true){
            int val=alt.getValue();
            System.out.println("start: "+val);
            if (val%2!=0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }

}
