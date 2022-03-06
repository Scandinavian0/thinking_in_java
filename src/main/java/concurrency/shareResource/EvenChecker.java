package concurrency.shareResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 其中一个任务产生偶数，而其他业务消费这些数字。
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class EvenChecker implements Runnable{
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int val= generator.next();
            if (val%2!=0){
                System.out.println(val+" not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }
    // Test any type of IntGenerator
    public static void test(IntGenerator gp,int count){
        System.out.println("press Control -C to exit");
        ExecutorService exce= Executors.newCachedThreadPool();
        for (int i=0;i<count;i++){
            exce.execute(new EvenChecker(gp,i));
        }
        exce.shutdown();
    }
    // Default value for count
    public static void test(IntGenerator gp){
        test(gp,10);
    }
}
