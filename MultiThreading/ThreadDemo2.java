package MultiThreading;

// better implementation of threading
class MyThread2 implements Runnable{
    Thread thrd;
    MyThread2(String name) {
        thrd = new Thread(this, name);
    }

    public static MyThread2 createAndStart(String name) {
        MyThread2 myThrd =  new MyThread2(name);
        myThrd.thrd.start();
        return myThrd;
    }

    public void run() {
        System.out.println(thrd.getName() + " Starting");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("In " + thrd.getName() +
                    ", count is " + count);
            }
        }
        catch (InterruptedException exc) {
            System.out.println(thrd.getName() + ": Interrupted");
        }
        finally {
            System.out.println(thrd.getName() + ": Terminating thread process");
        }
    }
}

// uses isAlive method of a Thread object to determine when to stop main thread
public class ThreadDemo2 {
    public static void main(String[] args) {
        System.out.println("Main thread starting.");
        // Create and start a thread.
        MyThread2 mt = MyThread2.createAndStart("Child #1");
        MyThread2 mt2 = MyThread2.createAndStart("Child #2");
        MyThread2 mt3 = MyThread2.createAndStart("Child #3");
        do {
            System.out.print(".");
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException exc) {
                System.out.println("Main thread interrupted.");
            }
        } while (mt.thrd.isAlive() || mt2.thrd.isAlive() || mt3.thrd.isAlive());
        System.out.println("Main thread ending.");
        }
}
