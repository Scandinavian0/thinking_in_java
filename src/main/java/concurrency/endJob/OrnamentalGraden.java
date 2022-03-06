package concurrency.endJob;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description 终结任务  装饰性花园  多大门计数
 * @Author Everglow
 * @Date 2022/3/6 19:49
 * @Version 1.0
 */
class Count{
    private int count=0;
    private Random rand=new Random(47);
    // remove the synchronized keyword to see counting fail;

    /**
     * 计算增量
     * @return
     */
    public synchronized int increment(){
        int temp=count;
        // Returns the next pseudorandom,The values true and false are produced with (approximately) equal probability.
        if(rand.nextBoolean()){
            // Yield half the time
            Thread.yield();// 暂停该线程，执行下一个线程
        }
        return (count=++temp);
    }
    public synchronized int value(){return count;}
}

class Entrance implements Runnable{
    private static Count count=new Count();
    private static List<Entrance> entrances=new ArrayList<>();
    private int number=0;
    // Doesn't need synchronization to read
    private final int id ;
    private static volatile boolean cancaled=false;
    // Atmoic operation on a volatile field:
    public static void cancel(){cancaled=true;}
    public Entrance(int id){
        this.id=id;
        // keep this task in a list .Also prevents
        // garbage collection of dead tasks
        entrances.add(this);
    }

    @Override
    public void run() {

    }
}
public class OrnamentalGraden {

}
