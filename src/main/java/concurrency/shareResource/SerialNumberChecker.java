package concurrency.shareResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2022/3/2
 * @Author: Everglow
 */
class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value not produced
        // by the SerialNumberGenerator
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        // Wrap index and write over old elements
        index = ++index % len;
    }
    public synchronized boolean contains(int val){
        for(int i=0;i<len;i++){
            if (array[i]==val) return true;
        }
        return false;
    }
}
class SerialNumberGenerator{
    private static volatile int serialNumber=0;
    public static int nextSerialNumber(){
        return serialNumber++; // not thread-safe
    }
}

public class SerialNumberChecker {
    private static final int SIZE=0;
    private static CircularSet serials=new CircularSet(1000);
    private static ExecutorService exec= Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable{
        @Override
        public void run() {
            while (true){
                int serial=SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)){
                    System.out.println("Duplicate: "+serial);
                    System.exit(0);
                }
                serials.add(serial);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<SIZE;i++){
            exec.execute(new SerialChecker());
        }
        if (args.length>0){
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
            System.out.println("No duplicates detected");
            System.exit(0);
        }
    }
}
