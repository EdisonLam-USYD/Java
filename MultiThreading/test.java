package MultiThreading;

class MyThreadtest implements Runnable{
    Thread thrd;

    MyThreadtest(String name) {
        thrd = new Thread(this, name);
    }

    MyThreadtest createAndRun(String name) {
        MyThreadtest mt = new MyThreadtest(name);
        mt.thrd.start();
        return mt;
    }

    public void run() {
        
    }



}


public class test {
    public static void main(String [] args) {

    }
}
