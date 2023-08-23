package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Scanner;


/* 行索引和列索引是 0 到N− 1 之间的整数，其中 (0, 0) 是左上角位置
* */
public class Percolation {

    private int top;
    private int bottom;
    private int len;
    private boolean[][] array;

    private WeightedQuickUnionUF backwash;
    private WeightedQuickUnionUF wqu;
    private int size; // number of open sites
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        len = N;
        backwash = new WeightedQuickUnionUF(N * N + 2); //top and bottom
        wqu = new WeightedQuickUnionUF(N * N + 1); //top
        array = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = false;
            }
        }
        size = 0;
        top = N * N;
        bottom = top + 1;
    }

    
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!array[row][col]) { //避免重复,使得size错误
            array[row][col] = true;
            size++;
            if (row == 0) {
                wqu.union(top, parse(row, col));
                backwash.union(top, parse(row, col));
            }
            if (row == len - 1) {
                backwash.union(bottom, parse(row, col));
            }
            adjacent(row, col);
        }
    }

    /*adjacent: 判断周围四个相邻的*/
    
    private void adjacent(int row, int col) {
        int N = len - 1; //range: 0 - N-1
        int x1 = row - 1;
        int x2 = row + 1;
        int y1 = col - 1;
        int y2 = col + 1;
        //边界检查处理
        if (x1 < 0) {
            x1 = 0;
        }
        if (x2 > N) {
            x2 = N;
        }
        if (y1 < 0) {
            y1 = 0;
        }
        if (y2 > N) {
            y2 = N;
        }
        //邻接判断
        if (isOpen(x1, col) && x1 != row) {
            wqu.union(parse(x1, col), parse(row, col));
            backwash.union(parse(x1, col), parse(row, col));
        }
        if (isOpen(x2, col) && x2 != row) {
            wqu.union(parse(x2, col), parse(row, col));
            backwash.union(parse(x2, col), parse(row, col));
        }
        if (isOpen(row, y1) && y1 != col) {
            wqu.union(parse(row, y1), parse(row, col));
            backwash.union(parse(row, y1), parse(row, col));
        }
        if (isOpen(row, y2) && y2 != col) {
            wqu.union(parse(row, y2), parse(row, col));
            backwash.union(parse(row, y2), parse(row, col));
        }

    }
    //坐标映射
    private int parse(int row, int rol) {
        return row * len + rol;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return wqu.connected(top, parse(row, col)); //会出现 backwash
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate? 系统是否渗透
    public boolean percolates() { //bottom 加速没使用 可以继续优化
        return backwash.connected(top, bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        Scanner sc = new Scanner(System.in);
        while (true) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            percolation.open(x, y);
        }
    }
}
