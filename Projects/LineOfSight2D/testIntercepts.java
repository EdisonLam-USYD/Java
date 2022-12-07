package Projects.LineOfSight2D;

class testIntercepts {
    public static void main(String [] args) {
        Point A = new Point(2.7, 0);
        Point B = new Point(3, 0);
        Point C = new Point(2.8, 8);
        Point D = new Point(2.8, 0);

        try {
            Point intercept = intercept_calc.find(A, B, C, D);
        System.out.print("The intersection of the given lines AB " + "and CD is: ");
        Point.cmd_display(intercept);
        }
        catch (ArithmeticException exc) {
            System.out.print("No intersection between lines");
        }
    }
}