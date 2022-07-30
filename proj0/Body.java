import java.lang.Math;

public class Body{
    static final double G_CONST = 6.67e-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel; 
    double mass;
    String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    /*
     * Creates a clone of a body object
     */
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    /*
     * Returns the distance between two points (pythagorean theorem)
     */

    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double dist = Math.sqrt((Math.pow(dx, 2) + (Math.pow(dy, 2))));
        return dist;
    }

    /*
     * returns the calculated total force excerted by a body
     */
    public double calcForceExertedBy(Body b){
        double force = (G_CONST * this.mass * b.mass) / Math.pow(calcDistance(b), 2);
        return force;
    }

    /*
     * returns the calculated force in the x direction, excerted by a body
     */
    public double calcForceExertedByX(Body b){
        double forceX = (calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / calcDistance(b);
        return forceX;
    }

    /*
     * returns the calculated force in the y direction, excerted by a body
     */
    public double calcForceExertedByY(Body b){
        double forceY = (calcForceExertedBy(b) * (b.yyPos - this.yyPos)) / calcDistance(b);
        return forceY;
    }

/*
* returns the calculated net force in the x direction, excerted by a body
*/
    public double calcNetForceExertedByX(Body[] arr){
        double netforceX = 0.0;
        for (Body b : arr){
            if (this.equals(b)){
                continue;
            }
            netforceX = netforceX + calcForceExertedByX(b);
        }
        return netforceX;
    }

/*
* returns the calculated net force in the y direction, excerted by a body
*/
    public double calcNetForceExertedByY(Body[] arr){
        double netforceY = 0.0;
        for (Body b : arr){
            if (this.equals(b)){
                continue;
            }
            netforceY = netforceY + calcForceExertedByY(b);
        }
        return netforceY;

    }

    public void update(double dt, double fX, double fY){
        double acellX = fX / this.mass;
        double acellY = fY / this.mass;
        this.xxVel = this.xxVel + (dt * acellX);
        this.yyVel = this.yyVel + (dt * acellY);
        this.xxPos = this.xxPos + (dt * this.xxVel);
        this.yyPos = this.yyPos + (dt * this.yyVel);

    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
