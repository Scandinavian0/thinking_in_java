package concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2022/3/10
 * @Author: Everglow
 */
public class Phisalosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFacotr;
    private Random rand= new Random(47);

    private void pause() throws InterruptedException{
        if (ponderFacotr==0){
            return;
        }
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFacotr*250));
    }

    public Phisalosopher(Chopstick left, Chopstick right, int id, int ponderFacotr, Random rand) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFacotr = ponderFacotr;
        this.rand = rand;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + "" + "thinking");
                pause();
                System.out.println(this+" "+"grabbing right");
                right.taked();
                System.out.println(this+" "+"grabbing left");
                left.taked();
                System.out.println(this +" "+"eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
