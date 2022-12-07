package MultiThreading;
// Use a synchronized block to control access to SumArray. 
class SumArray { 
    private int sum; 
    
    int sumArray(int[] nums) { 
    sum = 0; // reset sum 
    
    for(int i=0; i<nums.length; i++) { 
        sum += nums[i]; 
            System.out.println("Running total for " + 
            Thread.currentThread().getName() + 
            " is " + sum); 
        try { 
            Thread.sleep(10); // allow task-switch 
        } 
        catch(InterruptedException exc) { 
            System.out.println("Thread interrupted."); 
        } 
    } 
    return sum; 
    } 
} 
    
class MyThread3 implements Runnable { 
    Thread thrd; 
    static SumArray sa = new SumArray(); 
    int[] a; 
    int answer; 
    // Construct a new thread. 
    MyThread3(String name, int[] nums) { 
        thrd = new Thread(this, name); 
        a = nums; 
    } 
    
    // A factory method that creates and starts a thread.
    public static MyThread3 createAndStart(String name, int[] nums) {
        MyThread3 myThrd = new MyThread3(name, nums);
        myThrd.thrd.start(); // start the thread
        return myThrd;
    }

    // Entry point of thread. 
    public void run() { 
        // int sum; 
        
        System.out.println(thrd.getName() + " starting."); 
        
        // synchronize calls to sumArray() 
        synchronized(sa) { 
        answer = sa.sumArray(a); 
        } 
        System.out.println("Sum for " + thrd.getName() + 
        " is " + answer); 
        
        System.out.println(thrd.getName() + " terminating."); 
    } 
} 
public class ThreadSyncDemo {
    public static void main(String[] args) { 
        int[] a = {1, 2, 3, 4, 5}; 
        
        MyThread3 mt1 = MyThread3.createAndStart("Child #1", a); 
        MyThread3 mt2 = MyThread3.createAndStart("Child #2", a); 
        
        System.out.println("are the sa the same? " + (mt1.sa == mt2.sa));
        try { 
        mt1.thrd.join(); 
        mt2.thrd.join(); 
        } catch(InterruptedException exc) { 
        System.out.println("Main thread interrupted."); 
        } 
    } 
}
