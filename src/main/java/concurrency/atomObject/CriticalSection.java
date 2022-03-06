package concurrency.atomObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Date: 2022/3/3
 * @Author: Everglow
 */
class Pair{  // not thread-safe
    private int x,y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pair(){this(0,0);}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void incrementX(){x++;}
    public void incrementY(){y++;}

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public class PairValuesNotEqualException extends RuntimeException{
        public PairValuesNotEqualException(){
            super("Pair values not equal:"+Pair.this);
        }
    }
    public void checkState(){
        if (x!=y){
            throw new PairValuesNotEqualException();
        }
    }
}

abstract class PairManager{
    AtomicInteger checkCounter=new AtomicInteger();
    protected Pair p=new Pair();
    private List<Pair> storage= Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair(){
        // Make a copy to keep the original safe
        return new Pair(p.getX(),p.getY());
    }
    // Assume this is a time consuming operation
    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
public class CriticalSection {
}
