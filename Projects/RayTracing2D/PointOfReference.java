package Projects.RayTracing2D;

import java.util.LinkedList;
import java.awt.geom.Point2D;

public class PointOfReference extends Point2D.Double {
    private final int numRays;
    private int boundX, boundY;
    private LinkedList<Ray> rays;
    LinkedList<Line2DBlock> lineBlocks;
    

    PointOfReference(LinkedList<Line2DBlock> lineBlocks, double x, double y, int boundX, int boundY, int numRays) {
        super(x,y);
        this.boundY = boundY;
        this.boundX = boundX;
        this.numRays = numRays;
        this.lineBlocks = lineBlocks;
        this.rays = new LinkedList<>();

    }

    public void calculateRays() {
        this.rays.clear();
        for (int i = 0; i < numRays; i++) {
            Ray a = Ray.createAndStart(lineBlocks, x, y, 2*i*Math.PI/(numRays), boundX, boundY);
            rays.add(a);
        }
        for (Ray ray : rays) {
            try {
                ray.thrd.join(); // waiting for all to be done
            }
            catch (InterruptedException exc) {}
        }
    }

    // dont think this is needed
    public void updateLineBlocks(LinkedList<Line2DBlock> lineBlocks) {
        this.lineBlocks = lineBlocks;
    }

    public LinkedList<Ray> getRays() {
        return this.rays;
    }

    public void debugPrint() {
        for (Ray ray : rays) {
            ray.debugPrint();
        }
    }

    // Testing the class
    public static void main(String[] args) {
        int WIDTH = 10, HEIGHT = 10, BORDER = 1;
        LinkedList<Line2DBlock> l = new LinkedList<>();
        l.add(new Line2DBlock(BORDER, BORDER, BORDER, HEIGHT-BORDER));
        l.add(new Line2DBlock(BORDER, HEIGHT-BORDER, WIDTH-BORDER, HEIGHT-BORDER));
        l.add(new Line2DBlock(WIDTH-BORDER, HEIGHT-BORDER, WIDTH-BORDER, BORDER));
        l.add(new Line2DBlock(WIDTH-BORDER, BORDER, BORDER, BORDER));

        PointOfReference pov = new PointOfReference(l, 4, 6, WIDTH, HEIGHT, 8);
        // pov.calculateRays();
        // pov.debugPrint();
        System.out.println("Updating l without using update command");
        l.add(new Line2DBlock(0,1,10,8));
        pov.calculateRays();
        pov.debugPrint();


    }



}
