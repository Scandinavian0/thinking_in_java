package concurrency.shareResource;

/**
 * @Description:
 * @Date: 2022/3/2
 * @Author: Everglow
 */
public abstract  class IntGenerator {
    private volatile boolean canceled=false;
    public abstract int next();
    // Allow this to be canceled;
    public void cancel(){canceled=true;}
    public boolean isCanceled(){return canceled;}
}
