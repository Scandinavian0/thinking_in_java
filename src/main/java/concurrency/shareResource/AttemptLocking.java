package concurrency.shareResource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 特殊情况，synchronized不能尝试着获取关键字且最终获取锁时会失败，或者尝试获锁一段时间，然后放弃它
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class AttemptLocking {
    private ReentrantLock lock=new ReentrantLock();
    public void untimed(){
        boolean captured=lock.tryLock();
        try {
            System.out.println("tryLock(): "+captured);
        }finally {
            if (captured){
                lock.unlock();
            }
        }
    }
    public void timed(){
        boolean captured=false;
        try {
            captured=lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        try{
            System.out.println("tryLock(2,TImeUnit.SECONDS"+captured);
        }finally {
            if (captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al=new AttemptLocking();
        al.untimed(); // ture- grabbed by task
        al.timed(); //. true--- locka is available;
        // Now create a separate task to grab the lock
        new Thread(){
            {setDaemon(true);}
            public void run(){
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield(); // Give the 2nd task a chance
        al.untimed(); // False -- lock grabbed by task
        al.timed();     // False -- lock grabbed by task
    }

}
