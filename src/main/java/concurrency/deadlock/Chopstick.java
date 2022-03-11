package concurrency.deadlock;

/**
 * @Description: 筷子类
 * @Date: 2022/3/10
 * @Author: Everglow
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void taked() throws InterruptedException {
        while (taken){
            wait();
            taken=true;
        }
    }


    public synchronized void drop(){
        taken=false;
        notifyAll();
    }
}
