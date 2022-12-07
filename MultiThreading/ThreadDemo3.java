package MultiThreading;

// Using join method to find whether threads are done (best way)

public class ThreadDemo3 {
    public static void main(String[] args) {
        System.out.println("Main thread starting.");
        MyThread2 mt1 = MyThread2.createAndStart("Child #1");
        MyThread2 mt2 = MyThread2.createAndStart("Child #2");
        MyThread2 mt3 = MyThread2.createAndStart("Child #3");
        try {
            mt1.thrd.join();
            System.out.println("Child #1 joined.");
            mt2.thrd.join();
            System.out.println("Child #2 joined.");
            mt3.thrd.join();
            System.out.println("Child #3 joined.");
        }
        catch(InterruptedException exc) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread ending.");
    }

}
