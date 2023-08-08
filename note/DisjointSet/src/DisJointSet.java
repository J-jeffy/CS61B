/*并查集:
* 用于处理一些不交集（Disjoint sets，一系列没有重复元素的集合）的合并及查询问题。
    常见的两种操作：
        -合并两个集合；               //并
        -查找某元素属于哪个集合；      //查
    初始化所有元素为-1,即表示每个元素都是一个集合,同时表示集合的长度为1.
    负数表示此结点是根节点,其中具体的数值表示此树的长度.
    */
public class DisJointSet {
    private int[] set;

    public DisJointSet(int N) {
        set = new int[N];
        for (int i = 0; i < N; i++) {
            set[i] = -1;
        }
    }

    /*find: 寻找指定p结点的根节点并返回其根节点下标,并将p节点的父节点设置为根节点(降低树的高度)*/
    private int find(int p) {
        int n = p;
        while(set[n] >= 0) {
            n = set[n];
        }
        if (n != p) {
            return set[p] = n; //
        }
        return n;
    }


    /*isConnected: 分别寻找两个结点的根节点,并返回这两根节点是否相同*/
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /*connect: 将p和q两棵树连接起来,要使用size,将size小的树的根节点加到size大的树的根节点*/
    public void connect(int p, int q) {
        int sp = find(p); //p的根节点 set[sp]为负数且表示根节点中size的大小
        int sq = find(q); //q的根节点 set[sq]为负数且表示根节点中size的大小
        if (sp <= sq) { //将sq加到sp中,并更新sp中的size.因为根节点都是负数,值越小,size越大,相同size,右边加到左边
            set[sp] += set[sq]; //更新size大小
            set[sq] = sp; //使sq指向sp
        }else { //sq的size更大,将sp加到sq
            set[sq] += set[sp]; //更新size大小
            set[sp] = sq; //使sq指向sp
        }
    }

    public static void main(String[] args) {
        DisJointSet disJointSet = new DisJointSet(10);
        disJointSet.connect(0,1);
        disJointSet.connect(2,3);
        disJointSet.connect(1,3);
        disJointSet.connect(4,5);
        disJointSet.connect(6,7);
        disJointSet.connect(5,7);
        disJointSet.connect(0,4);
        disJointSet.find(7);
        disJointSet.connect(7,8);
        disJointSet.connect(9,8);
        disJointSet.connect(9,1);
    }
}
