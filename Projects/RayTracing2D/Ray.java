package Projects.RayTracing2D;

import java.lang.Math;
import java.awt.geom.Point2D;
import java.util.LinkedList;


public class Ray extends Line2DBlock implements Runnable {
    Thread thrd;
    private double piRads, gradient; // to find gradients
    private int boundX, boundY;
    volatile LinkedList<Line2DBlock> lines;
    // public boolean on, pChange;

    Ray(LinkedList<Line2DBlock> lines, double x1, double y1,  double piRads,  int boundX, int boundY) {
        super(x1, y1, x1, y1);
        this.boundX = boundX;
        this.boundY = boundY;
        this.gradient = Math.tan(piRads);
        this.piRads = piRads;
        this.lines = lines;
        
        this.thrd = new Thread(this);
    }

    @Override
    public void run() {
        double min, current;
        Point2D currentPoint, closestPoint = null;
        min = (double) (boundX + boundY); // impossibly large distance
        this.extendRay();
        for (Line2DBlock line : lines) {
            try {
                currentPoint = this.findIntercept(line);
                current = Line2DBlock.distance(this.getP1(), currentPoint);
            }
            catch (ArithmeticException exc) {
                currentPoint = null;
                current = (double) (boundX + boundY);
            }
            if (current < min) {
                min = current;
                closestPoint = currentPoint; 
            }
        }
        if (closestPoint == null) {
            closestPoint = this.getP1();
        }
        this.setP2(closestPoint);

        // try{
        //     System.out.println("Thread " + thrd.getName() + " is done.");
        // }
        // catch (NullPointerException exc) {
        //     System.out.println("run function finished.");
        // }
    }

    public static Ray createAndStart(LinkedList<Line2DBlock> lines, double x1, double y1,  double piRads,  int boundX, int boundY) {
        Ray a = new Ray(lines, x1, y1, piRads, boundX, boundY);
        a.thrd.start();
        return a;
    }

    public void extendRay() {
        // find gradient and extend until border
        // y1 - y2 = m (x1 - x2) -> sub x1 as borderX or y1 as borderY (other is the result)
        double x, y;
        
        // assuming it will reach the x-boundary first:
        if (Math.cos(piRads) == 0) {
            x = this.getX1();
            if (Math.sin(piRads) > 0) {
                y = this.boundY;
            }
            else {
                y = 0;
            }
            this.setP2(x, y);
            return;
        }
        if (Math.cos(piRads) > 0){
            x = boundX;
            y = gradient * (boundX - this.getX1()) + this.getY1();
            if (y > boundY){
                y = boundY;
                x = (boundY - this.getY1())/gradient + this.getX1();
            }
            else if (y < 0) {
                y = 0;
                x = -(this.getY1())/gradient + this.getX1();
            }
            this.setP2(x,y);
            return;
        }
        // assuming it moves towards x = 0 and reaches x = 0 first
        x = 0;
        y = gradient * -(this.getX1()) + this.getY1();
        if (y > boundY){
            y = boundY;
            x = (boundY - this.getY1())/gradient + this.getX1();
        }
        else if (y < 0) {
            y = 0;
            x = -(this.getY1())/gradient + this.getX1();
        }
        this.setP2(x,y);
    }
    
    public void setP1(double x, double y) {
        this.x1 = x;
        this.y1 = y;
        this.thrd.start(); // everytime the base point is reset, start again
    }
    public void setP1(Point2D p) {
        this.setP1(p.getX(), p.getY());
    }

    private void setP2(double x, double y) {
        this.x2 = x;
        this.y2 = y;
    }
    private void setP2(Point2D p) {
        this.setP2(p.getX(), p.getY());
    }

    public void debugPrint() {
        System.out.println("X1, Y1 = " + this.x1 + ", " + this.y1);
        System.out.println("X2, Y2 = " + this.x2 + ", " + this.y2);
        System.out.println("piRad = " + piRads);
        System.out.println();
    }


    /*
     * Testing the function for a single thread implementation
     * WORKS!
     */
    // public static void main(String[] args) {
    //     int WIDTH = 10, HEIGHT = 10, BORDER = 1;
    //     LinkedList<Line2DBlock> l = new LinkedList<>();
    //     l.add(new Line2DBlock(BORDER, BORDER, BORDER, HEIGHT-BORDER, 0));
    //     l.add(new Line2DBlock(BORDER, HEIGHT-BORDER, WIDTH-BORDER, HEIGHT-BORDER, 0));
    //     l.add(new Line2DBlock(WIDTH-BORDER, HEIGHT-BORDER, WIDTH-BORDER, BORDER, 0));
    //     l.add(new Line2DBlock(WIDTH-BORDER, BORDER, BORDER, BORDER, 0));
    //     for (int i = 0; i < 6; i++) {
    //         System.out.println("Testing ray with direction " + i*Math.PI/3);
    //         Ray a = new Ray(l, 5, 5, i*Math.PI/3, WIDTH, WIDTH);
    //         a.run();
    //         // System.out.println(a.toString());
    //         a.debugPrint();
    //     }
        
    // }

    /*
     * Testing the function on a multithread implementation
     * Works as well
     */
    public static void main(String[] args) {
        int WIDTH = 10, HEIGHT = 10, BORDER = 1;
        LinkedList<Line2DBlock> l = new LinkedList<>();
        l.add(new Line2DBlock(BORDER, BORDER, BORDER, HEIGHT-BORDER));
        l.add(new Line2DBlock(BORDER, HEIGHT-BORDER, WIDTH-BORDER, HEIGHT-BORDER));
        l.add(new Line2DBlock(WIDTH-BORDER, HEIGHT-BORDER, WIDTH-BORDER, BORDER));
        l.add(new Line2DBlock(WIDTH-BORDER, BORDER, BORDER, BORDER));
        l.add(new Line2DBlock(0,1,10,8));
        LinkedList<Ray> rays = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            System.out.println("Testing ray with direction " + i*Math.PI/3);
            Ray a = Ray.createAndStart(l, 5, 5, i*Math.PI/3, WIDTH, WIDTH);
            rays.add(a);
        }
        for (Ray ray : rays) {
            try {
                ray.thrd.join();
                ray.debugPrint();
            }
            catch (InterruptedException exc) {}
        }
        // can not re'start' each thread. must create a new one

    }
    
}
