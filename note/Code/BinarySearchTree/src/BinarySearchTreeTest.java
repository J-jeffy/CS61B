public class BinarySearchTreeTest {
        public static void main(String[] args) {
                BinarySearchTree bst = new BinarySearchTree();
                bst.insert(5);
                bst.insert(4);
                bst.insert(9);
                bst.insert(7);
                bst.insert(6);
                bst.insert(8);
                System.out.println("size: " + bst.getSize());
                System.out.println("min,max: " + bst.findMin() + " " + bst.findMax());
                bst.traversal(0);
                System.out.print("层序遍历:");
                bst.levelOrder();
                bst.remove(5);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(7);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(8);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(9);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                System.out.println("min,max: " + bst.findMin() + " " + bst.findMax());
                bst.insert(1);
                bst.insert(3);
                bst.insert(5);
                bst.insert(7);
                bst.insert(9);
                bst.insert(8);
                bst.traversal(0);
                System.out.print("层序遍历:");
                bst.levelOrder();
                System.out.println("size: " + bst.getSize());
                System.out.println("min,max: " + bst.findMin() + " " + bst.findMax());
                bst.remove(5);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(7);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(8);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                bst.remove(9);
                System.out.println("size: " + bst.getSize());
                bst.traversal(0);
                System.out.println("min,max: " + bst.findMin() + " " + bst.findMax());

        }



}
