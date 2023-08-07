

/*双端队列是具有动态大小的序列容器，可以在两端（前端或后端）扩展或收缩。循环数组实现*/
public class ArrayDeque<T> {
    private int size; //队列空间大小
    private int first; //实际队首元素下标
    private int last; //实际队尾元素下标
    private static double deFactor = 0.5;
    private static double upFactor = 2;

    private T[] array;

    public ArrayDeque() {
        array = (T[]) (new Object[8]); //只能先创建为Object数组再转为T[]类型
        size = 8;
        first = 0;
        last = 0;
    }

    /*程序在任何给定时间使用的内存量必须与项目数成正比。
    例如，如果向双端队列添加 10,000 个项目，然后删除 9,999 个项目，则不应仍然使用长度为 10,000 左右的数组。
    对于长度为 16 或以上的数组，使用系数应始终至少为 25%。对于较小的阵列，您的使用系数可以任意低。
    队列满后,将数组
    */
    private void resize(boolean flag1, boolean flag2) {
        int lastSize = size;
        if (flag1) { //数组增大
            size = (int) (size * upFactor);
        } else { //数组减小
            size = (int) (size * deFactor);
        }
        T[] item = (T[]) (new Object[size]);
        if (flag2) { //不用分段,无论是addFirst和addLast溢出时都只有一种情况,first == last
            System.arraycopy(array, first + 1, item, 0, lastSize - first - 1); //从first+1开始复制,复制到size-1
            System.arraycopy(array, 0, item, lastSize - first - 1, last + 1); //从0开始复制,复制到last
            last = lastSize - first + last - 1; //总长度-1
        } else { //数组减小
            if (first > last) { //first 在右边,分两段复制
                System.arraycopy(array, first + 1, item, 0, lastSize - first - 1); //从first+1开始复制,复制到size-1
                System.arraycopy(array, 0, item, lastSize - first - 1, last + 1); //从0开始复制,复制到last
                last = lastSize - first + last - 1; //总长度-1
            } else { //first在左边
                System.arraycopy(array, first + 1, item, 0, last - first); //从first开始复制,复制到last
                last = -first + last - 1; //总长度-1
            }
        }
        first = size - 1; //指向下一个first下标
        array = item;
    }

    /*
    * 1.先加到数组array[first]中
    * 2.(first-1+size) % size
    * 3.判断是否队列满
    * */
    public void addFirst(T item) {
        int pos = (first - 1 + size) % size; //代表加上这个元素恰好就满了,因此先加到数组
        array[first] = (T) item;
        first = pos;
        if (pos == last) { //队列满  重点:(first-1+size)
            resize(true, true); //增大队列,addFirst调用
        }
    }

    /*将类型的项添加T到双端队列的后面。
    * 因为初试下标相同,first 是先直接添加,而last就不能直接添加了,否者会覆盖元素
    * 采用先下标加一,判断是否满,若未满就添加,已满就添加至队尾*/
    public void addLast(T item) {
        last = (last + 1) % size; //代表加上这个元素恰好就满了,因此先加到数组
        array[last] = (T) item;
        if (last == first) { //队列满
            resize(true, true); //增大队列,addLast调用
        }
    }

    /*如果 deque 为空则返回 true，否则返回 false。*/
    public boolean isEmpty() {
        if (first == last) {
            return true;
        }
        return false;
    }

    /*返回双端队列中的项目数。*/
    public int size() {
        if (first == last) {
            return 0;
        } else if (first > last) {
            return size + last - first;
        }
        return last - first;
    }

    /*从第一个到最后一个打印双端队列中的项目，以空格分隔。*/
    public void printDeque() {
        if (first > last) {
            for (int i = first + 1; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            for (int i = 0; i <= last; i++) {
                System.out.print(array[i] + " ");
            }
        } else {
            for (int i = first + 1; i <= last; i++) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }

    /*删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。*/
    public T removeFirst() {
        if (first == last) { //为空
            return null;
        }
        T val = array[(first + 1) % size];
        first = (first + 1) % size; //修改下标即可
        if ((double) size() / (double) size <= 0.25 && size > 8) { //判断利用率问题,
            resize(false, false);
        }
        return val;
    }

    /*删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。*/
    public T removeLast() {
        if (first == last) { //为空
            return null;
        }
        T val = array[last]; //和first不同
        last = (last - 1 + size) % size;
        if ((double) size() / (double) size <= 0.25 && size > 8) { //判断利用率问题,
            resize(false, false);
        }
        return val;
    }

    /*获取给定索引处的项目，其中 0 是前面的项目，1 是下一个项目，依此类推。
    如果不存在这样的项目，则返回 null。一定不能改变双端队列！*/
    /*将类型的项添加T到双端队列的前面。*/
    public T get(int index) {
        if (first > last) {
            if (index < size - first - 1) { //index是从0开始,0是第一个
                return array[first + index + 1];
            }
            return array[index - size + first + 1];
        }
        return array[first + index + 1];
    }

}
