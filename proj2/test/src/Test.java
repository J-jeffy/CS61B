import edu.princeton.cs.introcs.StdDraw;

public class Test {
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5); //第一象限坐标系,取值[0-1]
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.ellipse(0.5, 0.5, 0.3, 0.2);
        StdDraw.filledCircle(0.2, 0.2,0.2);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
    }

    /*circle(double x, double y, double radius)
    ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis)
    square(double x, double y, double halfLength)
    rectangle(double x, double y, double halfWidth, double halfHeight)*/


}
