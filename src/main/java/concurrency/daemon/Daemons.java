package concurrency.daemon;

/**
 * @Description:
 * @Date: 2022/2/28
 * @Author: Everglow
 */
class Daemon implements Runnable{
    private Thread[] t=new Thread[10];

    @Override
    public void run() {
        for (int i=0;i<t.length;i++){
//            t[i]=new Thread(new DaemonSpan)
        }
    }
}
public class Daemons {
}
