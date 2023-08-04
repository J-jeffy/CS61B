import javax.print.DocFlavor;

/*NBody 是一个将实际运行您的模拟的类。这个类将没有构造函数。此类的目标是模拟数据文件之一中指定的宇宙。
例如，如果我们查看 data/planes.txt（使用命令行 more 命令），我们会看到以下内容：*/
public class NBody {
    /*The input format is a text file that contains the information for a particular universe (in SI units).
    The first value is an integer N which represents the number of planets.
    The second value is a real number R which represents the radius of the universe,
        used to determine the scaling of the drawing window.
    Finally, there are N rows, and each row contains 6 values.
        The first two values are the x- and y-coordinates of the initial position;
        the next pair of values are the x- and y-components of the initial velocity;
        the fifth value is the mass;
        the last value is a String that is the name of an image file used to display the planets.
        Image files can be found in the images directory.
        The file above contains data for our own solar system (up to Mars).
    5
    2.50e+11
    1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif
    2.2790e+11  0.0000e+00  0.0000e+00  2.4100e+04  6.4190e+23     mars.gif
    5.7900e+10  0.0000e+00  0.0000e+00  4.7900e+04  3.3020e+23  mercury.gif
    0.0000e+00  0.0000e+00  0.0000e+00  0.0000e+00  1.9890e+30      sun.gif
    1.0820e+11  0.0000e+00  0.0000e+00  3.5000e+04  4.8690e+24    venus.gif
    */

    private static int readNum(String filename) {
        In in = new In(filename);
        return in.readInt();
    }

    /* Given a file name, it should return a double corresponding to the radius of the universe in that file
     * readRadius("./data/planets.txt")*/
    public static double readRadius(String txt){
        In in = new In(txt);
        in.readInt();
        return in.readDouble();
    }

    /*Given a file name, it should return an array of Planets corresponding to the planets in the file
     * readPlanets("./data/planets.txt")*/
    public static Planet[] readPlanets(String txt){
        In in = new In(txt);
        int n = in.readInt();
        int i =  0;
        in.readDouble();
        Planet[] planets = new Planet[n];
        while(n>0){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = "images/"+in.readString();
            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
            i++;
            n--;
        }
        return planets;
    }


    public static void main(String[] args) {
        //Collecting All Needed Input
        double T,dt;//Hint: the arguments come in as Strings.
        String filename;
        T = Double.parseDouble(args[0]);//157788000.0
        dt = Double.parseDouble(args[1]);//25000.0
        filename = args[2];
        int num = NBody.readNum(filename);
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);


        //Drawing the Background.Drawing All of the Planets
        StdDraw.setScale(-radius, radius);

        /* Clears the drawing window. */
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();


        //Creating an Animation
        double time = 0;
        while(time < T){
            double[] Fx = new double[num];
            double[] Fy = new double[num];
            int i = 0;
            for (Planet planet:planets) {
                Fx[i] = planets[i].calcNetForceExertedByX(planets);
                Fy[i] = planets[i].calcNetForceExertedByY(planets);
                i++;
            }
//            for (int i = 0; i < num; i++) {
//                Fx[i] = planets[i].calcNetForceExertedByX(planets);
//                Fy[i] = planets[i].calcNetForceExertedByY(planets);
//            }
//            for (int i = 0; i < num; i++) {
//                planets[i].update(dt,Fx[i],Fy[i]);
//            }
            int j = 0;
            for (Planet planet:planets) {
                planets[j].update(dt,Fx[j],Fy[j]);
                j++;
            }
            /* Stamps three copies of advice.png in a triangular pattern. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet:planets) {
                planet.draw();
            }

            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;//Increase your time variable by dt.
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }



}
