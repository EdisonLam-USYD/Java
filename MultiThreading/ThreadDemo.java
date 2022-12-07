package MultiThreading;

// creating Runnable
class MyThread implements Runnable{
    String thread_name;
    MyThread(String name) {
        this.thread_name = name;
    }
    
    public void run() {
        System.out.println(thread_name + "Starting");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("In " + thread_name +
                    ", count is " + count);
            }
        }
        catch (InterruptedException exc) {
            System.out.println(thread_name + ": Interrupted");
        }
        finally {
            System.out.println("Terminating thread process");
        }
    }
}


public class ThreadDemo {
    public static void main(String [] args) {
        System.out.println("Main Thread starting:");
        // First constructing MyThread Object
        MyThread mt = new MyThread("Thread 1");
        // Next, constructing a thread from that Object
        Thread newThrd = new Thread(mt);
        // Finally executing the thread:
        newThrd.start();

        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException exc) {
                System.out.println("Main Thread Interrupted");
            }
        }
        System.out.println("Main thread ending.");

    }
}
