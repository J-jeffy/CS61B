package synthesizer;

/*The purpose of AbstractBoundedQueue will be to simply provide
 a protected fillCount and capacity variable that all subclasses will inherit,
  as well as so called “getter” methods capacity() and fillCount()
  that return capacity and fillCount, respectively.
  This saves a tiny amount of work for future implementations like ArrayRingBuffer.
  java (see next section).*/
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount; //实际容量
    protected int capacity; //队列大小
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
}
