package concurrency.shareResource;

/**
 * @Description: 可以产生一系列偶数值的next方法
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public class EvenGenerator extends IntGenerator{
    private int currentEvenValue=0;
    @Override
    public int next() {
        ++currentEvenValue; // Danger point here;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
