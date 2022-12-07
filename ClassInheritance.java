class ClassInheritance {
    public static void main(String [] args) {
        Triangle t1 = new Triangle(2, 1, "red", "dotted");
        Triangle t2 = new Triangle(4, 2, "blue", "solid");
        System.out.println("Info for t1: ");
        t1.showStyle();
        t1.showInfo();
        System.out.println("Area is " + t1.area());
        System.out.println();
        System.out.println("Info for t2: ");
        t2.showStyle();
        t2.showInfo();
        System.out.println("Area is " + t2.area());
    }
}

// original class
// note: private methods and properties are not visible within the subclass but still inherited 
// (can only be accessed within parent class super-constructor and parent class methods)
class Shapes {
    double height;
    double width;
    private String colour;
    Shapes(double h, double w, String colour) {
        this.height = h;
        this.width = w;
        this.colour = colour;
    }

    void showInfo() {
        System.out.println("Height = " + this.height + "\nWidth = " + this.width + "\nColour = " + this.colour);
    }
}

// subclass of shapes
// as colour is private, Triangle can not access that property but using parent methods such as showInfo, it can be seen it still exists
class Triangle extends Shapes {
    String style;

    Triangle(double h, double w, String c, String style) {
        super(h, w, c);
        this.style = style;
    }
    double area() {
        return this.height * 0.5;
    }

    void showStyle() {
        System.out.println(this.style);
    }
}
