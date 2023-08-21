import java.util.LinkedList;
import java.util.Queue;

/*二叉排序树的操作主要有：
1. 查找：递归查找是否存在key。
2. 插入：原树中不存在key，插入key返回true，否则返回false。
3. 构造：循环的插入操作。
4. 删除：
	- 叶子节点：直接删除，不影响原树。
    - 仅仅有左或右子树的节点：节点删除后，将它的左子树或右子树整个移动到删除节点的位置就可以，子承父业。
    - 既有左又有右子树的节点：找到须要删除的节点p的直接前驱或者直接后继s，用s来替换节点p，然后再删除节点s。
*/
public class BinarySearchTree {
    private Node root; //根节点
    private int size;

    private class Node {
        double var;
        Node left;
        Node right;

        public Node(double key) {
            var = key;
        }
    }

    /*1. 查找：递归查找是否存在key。*/
    public boolean find(Node node, double value) {
        if (node == null) {
            return false;
        }
        if (value == node.var) {
            return true; //找到该值
        } else if (value > node.var) {
            return find(node.right, value); //递归寻找右子树
        } else {
            return find(node.left, value); //递归寻找左子树
        }
    }

    /*2. 插入：原树中不存在key，插入key返回true，否则返回false。*/
    public boolean insert(double key) {
        if (find(root, key)) {
            return false; //如果已经存在就返回false
        }
        root = add(root,key);
        return true;
    }

    /*insert: 在树中寻找该结点,若未找到则创建并插入结点到指定位置,若找到了就返回该结点
     * parameter: node, key 指定根结点和插入的值
     * return: node 找到结点就返回该结点,未找到就创建并插入结点并返回
     * */
    /*递归插入新节点,返回根节点*/
    private Node add(Node node, double key) {
        if (node == null) { //根结点不存在,或者子结点不存在
            size++;
            return new Node(key);
        }else if (node.var > key) { //进入左子树递归
            node.left = add(node.left, key);
        } else if (node.var < key){ //进入右子树递归
            node.right = add(node.right,key);
        }
        return node; //每层函数都要返回结点
    }



    /*4. 删除指定key值结点*/
    public boolean remove(double key) {
        if (!find(root, key)){
            return false; //不不存在,删除失败
        }
        root = remove(root, key);
        return true;
    }

    /*remove:
    * 删除：
       - 叶子节点：直接删除，不影响原树。
       - 仅仅有左或右子树的节点：节点删除后，将它的左子树或右子树整个移动到删除节点的位置就可以，子承父业。
       - 既有左又有右子树的节点：找到须要删除的节点p的直接前驱或者直接后继s，用s来替换节点p，然后再删除节点s。
    * */
    private Node remove(Node node, double key) {
        if (node == null) {
            return null;
        }
        if (node.var > key) { //迭代寻找
            node.left = remove(node.left, key);
        } else if (node.var < key) { //根节点的左子树为待删除结点
            node.right = remove(node.right, key);
        } else { //找到了该删除的结点
            //如果该结点左子树为空,则返回其右子树,返回右子树代表删除自己的结点,让右子树替代自己
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left; //如果返回的右子树也为空也符合情况
            Node min = minNode(node.right); //寻找右子树的最小值或者左子树的最大值
            node.var = min.var; //替换该结点
            node.right = deleteMin(node.right); //删除原min结点,因为min结点的左子树必为空,返回右节点即可.
        }
        return node;
    }

    /*删除指定根结点中的最小结点*/
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right; //该最小结点的右子树不能丢
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /*删除指定根结点中的最大结点*/
    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left; //该最大结点的左子树不能丢
        }
        node.right = deleteMin(node.right);
        return node;
    }

    /*寻找root的最小结点*/
    public double findMin() {
        if (root != null) {
            Node node = minNode(root);
            return node.var;
        }
        return -1;
    }

    /*寻找指定根节点的最小值*/
    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    /*寻找root的最大结点*/
    public double findMax() {
        if (root != null) {
            Node node = maxNode(root);
            return node.var;
        }
        return -1;
    }

    /*寻找指定根节点的最大值*/
    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    /*traversal: 三种遍历方式
    * parameter: n 1,前序 2,后序 其他,中序 */
    public void traversal(int n) {
        if (root != null) {
            if ( n == 1) { //前序遍历
                preorder(root);
                System.out.println();
            } else if (n == 2) {
                postorder(root);
                System.out.println();
            }else {
                inorder(root);
                System.out.println();
            }
        }
    }

    /*中序遍历*/
    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.var + " ");
        inorder(node.right);
    }

    /*后序遍历*/
    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.var + " ");
    }

    /*前序遍历*/
    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.var + " ");
        preorder(node.left);
        preorder(node.right);
    }

    /*levelOrder: 层序遍历*/
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>(); //入队queue.add(),出队queue.remove()
        queue.add(root);
        while(!queue.isEmpty()) {

            Node node = queue.remove(); //一次循环出队一个结点
            System.out.print(node.var + " ");

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        traversal(0); //中序遍历
        return null;
    }
}
