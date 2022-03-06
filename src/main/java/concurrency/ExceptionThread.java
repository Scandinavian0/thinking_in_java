package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程的异常捕获
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
