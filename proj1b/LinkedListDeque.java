public class LinkedListDeque<T> implements Deque<T> {
    private Node first;
    private Node last;
    private int size;


    private class Node {
        private T val;
        private Node pre;
        private Node next;
        public Node(T v, Node p, Node n) {
            val = v;
            pre = p;
            next = n;
        }
        /*从第一个开始递归返回第index个变量的val*/
        public T getValRecursive(int index) {
            if (index == 0) { //base case
                return val;
            }
            return this.next.getValRecursive(--index); //递归关键
        }
    }
    /*创建一个空的链表双端队列。*/
    public LinkedListDeque() {
        first = new Node(null, null,  null);
        last = new Node(null, null, null);
        first.next = last;
        last.pre = first;
        size = 0;
    }

    /*add and remove operations must not involve any looping or recursion.
        A single such operation must take “constant time”,
        i.e. execution time should not depend on the size of the deque.
    */
    /*size must take constant time.*/
    @Override
    public void addFirst(T i) {
        Node node = new Node(i, null, null);
        node.next = first.next; //node的后继节点为first后继节点
        first.next.pre = node; //first后继节点的前驱节点为node
        first.next = node; //first的后继节点为node
        node.pre = first; //node的前驱节点为first
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T val = first.next.val;
        first.next = first.next.next; //先指向first的后继第二节点
        first.next.pre = first; //改变后继第二节点的指向为first
        size -= 1;
        return val;
    }

    @Override
    public void addLast(T middle) {
        Node node = new Node(middle, null, null);
        node.next = last; //node的后继为last
        node.pre = last.pre; //node的前驱为last的前驱
        last.pre.next = node; //last的前驱结点的后继为node
        last.pre = node; //last的前驱结点为node
        size += 1;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T val = last.pre.val;
        last.pre = last.pre.pre; //先将last的前驱指向last前驱的前驱
        last.pre.next = last; //last前驱的后继改为last
        size -= 1;
        return val;
    }

    /*get必须使用迭代，而不是递归。*/
    @Override
    public T get(int index) {
        if (index > size || index < 0) { //如果越界则直接返回
            return null;
        }
        Node p = first.next; //指向first的后继节点再开始迭代
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.val;
    }

    /*与get相同，但使用递归。*/
    public T getRecursive(int index) {
        if (index > size || index < 0) { //如果越界则直接返回
            return null;
        }
        return this.first.next.getValRecursive(index); //关键:由于有first辅助节点,所以要使用first.next节点进行迭代
    }

    @Override
    public void printDeque() {
        Node p = first.next; //指向first的后继节点再开始迭代
        int index = size;
        while (index != 0) {
            System.out.print(p.val + " ");
            p = p.next;
            index--;
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
}
