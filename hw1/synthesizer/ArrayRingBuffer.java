package synthesizer; //   Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;


//  Make sure to make this class and all of its methods public
//  Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) (new Object[capacity]);
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) throws RuntimeException {
        //  Enqueue the item. Don't forget to increase fillCount and update last.
        if (!isFull()) { //队列未满
            //装入队列
            rb[last] = x;
            //fullCount +1
            fillCount += 1;
            //last下标+1
            last = (last + 1) % capacity;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() throws RuntimeException {
        //   Dequeue the first item. Don't forget to decrease fillCount and update
        //判空
        if (isEmpty()) {
            throw new RuntimeException();
        }
        //fillCount-1
        fillCount -= 1;
        //移动下标
        int index = first;
        first = (first + 1) % capacity;
        //返回删除的值
        return rb[index];
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() throws RuntimeException {
        //   Return the first item. None of your instance variables should change.
        //判空
        if (isEmpty()) {
            throw new RuntimeException();
        }
        //返回当前下标的值
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }

    public class KeyIterator implements Iterator<T> {
        private int ptr;
        public KeyIterator() {
            ptr = 0;
        }
        public boolean hasNext() {
            return (ptr != fillCount);
        }
        public T next() {
            return rb[ptr++];
        }
    }


    //   When you get to part 5, implement the needed code to support iteration.
}
