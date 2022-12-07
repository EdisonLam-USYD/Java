/*
 * Written by Edison Lam and used in his 2D Ray Tracing program
 * extention of the Line2D class within awt.Line2D
 * (reflectivity has not been implemented)
 */

package Projects.RayTracing2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

class Line2DBlock extends Line2D.Double {

    // float reflectivity;
    
    
    // Line2DBlock(double x1, double y1, double x2, double y2, float r) {
    //     super(x1, y1, x2, y2);
    //     reflectivity = r;
    // }
    // Line2DBlock(Line2D line, float r) {
    //     super(line.getP2(), line.getP1());
    //     this.reflectivity = r;
    // }
    Line2DBlock(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }
    Line2DBlock(Line2D line) {
        super(line.getP2(), line.getP1());
    }

    public static synchronized double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
    public static synchronized double distance(Point2D p1, Point2D p2) {
        double x1, y1, x2, y2;
        x1 = (double) p1.getX();
        x2 = (double) p2.getX();
        y1 = (double) p1.getY();
        y2 = (double) p2.getY();
        return distance(x1, y1, x2, y2);
    }
    public double getDistance() {
        return distance(getP1(), getP2());
    }

    public Point2D findIntercept(Line2DBlock line) throws ArithmeticException{
        double s1_x, s1_y, s2_x, s2_y;
        s1_x = this.getX2() - this.getX1();
        s1_y = this.getY2() - this.getY1();
        s2_x = line.getX2() - line.getX1();
        s2_y = line.getY2() - line.getY1();

        double s, t;
        s = (-s1_y * (this.getX1() - line.getX1()) + s1_x * (this.getY1() - line.getY1())) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (this.getY1() - line.getY1()) - s2_y * (this.getX1() - line.getX1())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            double x = this.getX1() + (t * s1_x);
            double y = this.getY1() + (t * s1_y);
    
            return new Point2D.Double(x,y);
        }
        throw new ArithmeticException();
    }

    // use ray as the base and the block as the parameter
    public double findRayDistance(Line2DBlock line) throws ArithmeticException{
        double s1_x, s1_y, s2_x, s2_y;
        s1_x = this.getX2() - this.getX1();
        s1_y = this.getY2() - this.getY1();
        s2_x = line.getX2() - line.getX1();
        s2_y = line.getY2() - line.getY1();

        double s, t;
        s = (-s1_y * (this.getX1() - line.getX1()) + s1_x * (this.getY1() - line.getY1())) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (this.getY1() - line.getY1()) - s2_y * (this.getX1() - line.getX1())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            double x = this.getX1() + (t * s1_x);
            double y = this.getY1() + (t * s1_y);
    
            return distance(x, y, this.getX1(), this.getY1());
        }
        throw new ArithmeticException();
    }

    /*
     * Unit testing the intercept functions
     */
    public static void main(String[] args) {
        Line2DBlock a = new Line2DBlock(0, 3, 6, 3);
        Line2DBlock b = new Line2DBlock(6, 7, 6, 0);
        double c;
        Point2D d;
        
        try {
            System.out.println("testing b.int(a) " + "X: " + b.getX1() + "; Y: " + b.getY1());
            c = b.findRayDistance(a);
            d = b.findIntercept(a);
            System.out.println("output is " + c);
            System.out.println(d.toString());
        }
        catch (ArithmeticException exc){
            System.out.println("No intercept found");
        }
        try {
            System.out.println("testing a.int(b) " + "X: " + a.getX1() + "; Y: " + a.getY1());
            c = a.findRayDistance(b);
            System.out.println("output is " + c);
            d = a.findIntercept(b);
            System.out.println(d.toString());
        }
        catch (ArithmeticException exc){
            System.out.println("No intercept found");
        }
    }

    

}