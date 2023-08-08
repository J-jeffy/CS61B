import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> integers = new ArrayDeque<>();
        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addFirst(4);
        integers.addFirst(5);
        integers.addFirst(6);
        integers.addFirst(7);
        integers.addFirst(8);
        integers.addFirst(9);
        integers.addFirst(10);
        integers.addLast(11);
        System.out.println(integers.get(1));
        System.out.println(integers.get(2));
        System.out.println(integers.get(3));
        System.out.println(integers.get(4));
        integers.addLast(12);
        integers.addLast(13);
        integers.addLast(14);
        integers.addLast(15);
        integers.addLast(16);
        integers.addLast(17);
        integers.addLast(18);
        integers.addLast(19);
        integers.printDeque();
        int a = integers.removeFirst();
        int b = integers.removeFirst();
        int c = integers.removeFirst();
        int d = integers.removeLast();
        int e = integers.removeLast();
        int f = integers.removeLast();
        integers.addLast(13);
        integers.addLast(14);
        integers.addFirst(1);
        integers.addFirst(2);
        integers.removeLast();
        integers.removeLast();
        integers.removeLast();
        integers.removeLast();
        System.out.println(integers.get(1));
        System.out.println(integers.get(2));
        System.out.println(integers.get(3));
        System.out.println(integers.get(4));
        integers.removeLast();
        integers.removeLast();
        integers.removeLast();
        integers.removeFirst();
        integers.removeFirst();
        integers.removeFirst();
        integers.addLast(13);
        integers.addLast(14);
        integers.addFirst(1);
        integers.addFirst(2);
        System.out.println(a + " " + b + " " + c);
        System.out.println(d + " " + e + " " + f);
        integers.printDeque();
        System.out.println(integers.size());

    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> integers = new ArrayDeque<>();
        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addFirst(4);
        integers.addFirst(6);
        integers.addFirst(7);
        for (int i = 10; i < 20; i++) {
            integers.addLast(i);
        }
        for (int i = 0; i < 8; i++) {
            integers.removeLast();
        }
        integers.addFirst(8);
        integers.addFirst(9);
        for (int i = 0; i < 8; i++) {
            integers.removeLast();
        }
    }
}
