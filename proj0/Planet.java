class Planet {
    public double xxPos;//当前的x位置
    public double yyPos;//当前的y位置
    public double xxVel;//当前的x速度
    public double yyVel;//当前的y速度
    public double mass;//质量
    public String imgFileName;//描绘行星的图像对应的文件名
    public static double G = 6.67*Math.pow(10,-11);

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* 接受一个 Planet 对象并初始化一个相同的 Planet 对象（即副本）*/
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    /*此方法将接受单个行星，并应返回等于提供的行星与正在进行计算的行星之间的距离的双精度值*/
    public double calcDistance(Planet planet){
        return Math.sqrt(Math.pow((planet.xxPos-xxPos),2)+Math.pow((planet.yyPos-yyPos),2));
    }

    /* calcForceExertedBy 方法接受一个行星，并返回一个双精度值，描述给定行星对该行星施加的力。
    您应该在此方法中调用 calcDistance 方法。*/
    public double calcForceExertedBy(Planet planet){
        return Planet.G*mass*planet.mass/Math.pow(calcDistance(planet),2);

    }

    /*这两个方法分别描述在 X 方向上施加的力*/
    public double calcForceExertedByX(Planet planet){
        return calcForceExertedBy(planet)*(planet.xxPos-xxPos)/calcDistance(planet);

    }

    /*这两个方法分别描述在 Y 方向上施加的力*/
    public double calcForceExertedByY(Planet planet) {
        return calcForceExertedBy(planet)*(planet.yyPos-yyPos)/calcDistance(planet);

    }

    /*每个方法接受一个行星数组，并计算该数组中的所有行星对当前行星施加的净 X */
    public double calcNetForceExertedByX(Planet[] allPlanets ) {
        double Fx = 0;
        for (Planet planet : allPlanets) {
            if(this.equals(planet)){
                continue;
            }
            Fx += this.calcForceExertedByX(planet);
        }
        return Fx;
    }

    /*每个方法接受一个行星数组，并计算该数组中的所有行星对当前行星施加的净 Y 力*/
    public double calcNetForceExertedByY(Planet[] allPlanets ) {
        double Fy = 0;
        for (Planet planet : allPlanets) {
            if(planet.equals(this)){
                continue;
            }
            Fy += this.calcForceExertedByY(planet);
        }
        return Fy;
    }

    /*您将添加一个方法来确定施加在行星上的力将导致该行星加速的程度，以及在短时间内行星速度和位置的变化 dt
    * 编写一个 update(dt, fX, fY) 方法，使用上述步骤更新行星的位置和速度实例变量（该方法不需要返回任何内容）。*/
    public void update(double dt,double fX,double fY) {
        double aX = fX/mass;
        double aY = fY/mass;
        xxVel = xxVel + aX*dt;
        yyVel = yyVel + aY*dt;
        xxPos = xxPos + xxVel*dt;
        yyPos = yyPos + yyVel*dt;
    }

    /* we’ll want a planet to be able to draw itself at its appropriate position.
     To do this, take a brief detour back to the Planet.java file.
     Add one last method to the Planet class, draw,
     that uses the StdDraw API mentioned above to draw the Planet’s image at the Planet’s position.
     The draw method should return nothing and take in no parameters.*/
    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}
