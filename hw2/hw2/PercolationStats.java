package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int T;
    private double[] threshold;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        int rx, ry;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation pc = pf.make(N);
            int sum = 0;
            while(!pc.percolates()){
                rx = StdRandom.uniform(0, N);
                ry = StdRandom.uniform(0, N);
                pc.open(rx, ry);
                sum++;
            }
            threshold[i] = (double) sum / (double) N;
        }
        this.T = T;
    } // perform T independent experiments on an N-by-N grid 在 N×N 网格上执行 T 个独立实验
    public double mean() {
        return StdStats.mean(threshold);
    } // sample mean of percolation threshold 渗透临界值的样本平均值
    public double stddev() {
        return StdStats.stddev(threshold);
    } // sample standard deviation of percolation threshold 渗滤阈值样本标准差
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    } // low endpoint of 95% confidence interval 95% 置信区间的低端点
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    } // high endpoint of 95% confidence interval 95% 置信区间的高端点

}
