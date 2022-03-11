package concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Date: 2022/3/10
 * @Author: Everglow
 */
public class DeadLockIngDiningPhilosophers {
    public static void main(String[] args) {
        int ponder=5;
        if (args.length>0){
            ponder=Integer.parseInt(args[0]);
        }
        int size=5;
        if (args.length>1){
            ponder=Integer.parseInt(args[1]);
        }
        ExecutorService exec= Executors.newCachedThreadPool();
        Chopstick[] sticks=new Chopstick[size];
        for (int i=0;i<size;i++){
            sticks[i]=new Chopstick();
        }
//        for (int i=0,l<size,i++){
//            exec.execute(new Phisalosopher(sticks[s.p));
        }
    }

