package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 在Thread类中设置一个静态域用于捕获异常
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exce=Executors.newCachedThreadPool();
        exce.execute(new ExceptionThread());
    }
}
