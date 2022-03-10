package concurrency.cooperation;

import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 涂蜡和抛光，先进行涂蜡，抛光后再涂蜡
 * @Date: 2022/3/8
 * @Author: Everglow
 */
class Car{
    private boolean waxOn=false;
    // 涂蜡
    public synchronized void waxed(){
        waxOn=true; // Readt to buff
        notifyAll(); // 用于唤醒wait的线程
    }

    public synchronized void buffed(){
        waxOn=false;    // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn==true){
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn==true){
            wait();
        }
    }
}

class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("Wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Exiting Wax on task ");
    }
}

class WaxOff implements Runnable{
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("Wax off!");
                TimeUnit.SECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax off task ");
    }
}
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car=new Car();
        ExecutorService exec=Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();;
    }
}
