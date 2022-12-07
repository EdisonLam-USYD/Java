package Projects.LineOfSight2D;

class Point {
    double x,y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static void cmd_display(Point p) {
        System.out.println("(" + p.x + ", " + p.y + ")");
    }

    boolean sameAs(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}