class ClassDef {
    public static void main(String [] args) {
        Example a, b;
        a = new Example(1);
        b = new Example(2);
        a.print();
        b.print();
        System.out.println(a.comp(b));
        System.out.println(a.comp(a));
    }
}

class Example {
    // declaration of class variables
    int x;

    // input arguments of the class
    Example(int i){
        x = i;
    }
    // 'this' keyword is the same as self in python
    public void print() {
        System.out.println(this.x);
    }

    public int comp(Example other) {
        if (this == other) {
            return 1;
        }
        return 0;
    }
}

