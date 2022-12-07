package Projects.LineOfSight2D;
import java.lang.Math;

class intercept_calc {
    // Point a1, a2;
    // Point b1, b2;

    // intercept_calc(Point a1, Point a2, Point b1, Point b2) {
    //     this.a1 = a1;
    //     this.b1 = b1;
    //     this.a2 = a2;
    //     this.b2 = b2;
    // }

    // throws Exception if no intercept, else returns coordinates
    static Point find(Point a1, Point a2, Point b1, Point b2) throws ArithmeticException{
        if (a1.sameAs(b1) || a1.sameAs(b2) || a2.sameAs(b1) || a2.sameAs(b2))
            throw new ArithmeticException();
        // checking if in range of each other
        if (Math.min(a1.x, a2.x) > Math.max(b1.x, b2.x) || Math.min(b1.x, b2.x) > Math.max(a1.x, a2.x))
            throw new ArithmeticException();
        if (Math.min(a1.y, a2.y) > Math.max(b1.y, b2.y) || Math.min(b1.y, b2.y) > Math.max(a1.y, a2.y))
            throw new ArithmeticException();

        // Line AB represented as a1x + b1y = c1
        double A1 = a2.y - a1.y;
        double B1 = a1.x - a2.x;
        double C1 = A1*(a1.x) + B1*(a1.y);
      
        // Line CD represented as a2x + b2y = c2
        double A2 = b2.y - b1.y;
        double B2 = b1.x - b2.x;
        double C2 = A2*(b1.x) + B2*(b1.y);

        double det = A1*B2 - A2*B1;

        if (det == 0) {
            throw new ArithmeticException();
        }
        double x = (B2*C1 - B1*C2)/det;
        double y = (A1*C2 - A2*C1)/det;

        if (x > Math.max(a1.x, b1.x) || x > Math.max(a2.x, b2.x) || x < Math.min(a1.x, b1.x) || x < Math.min(a2.x, b2.x))
            throw new ArithmeticException();
        if (y > Math.max(a1.y, b1.y) || y > Math.max(a2.y, b2.y) || y < Math.min(a1.y, b1.y) || y < Math.min(a2.y, b2.y))
            throw new ArithmeticException();
        return new Point(x, y);
    }
}
