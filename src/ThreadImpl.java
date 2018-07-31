/**
 * Created by mona on 11/3/16.
 */
public class ThreadImpl {

    enum MODE {
        oozie, local
    }

    public static void main(String[] args) {
        System.out.println(MODE.local);
        System.out.println(MODE.oozie.name().equals("oozie"));

        MyThread t1 = new MyThread("Thread-1");
        t1.start();

        MyThread t2 = new MyThread("Thread-2");
        t2.start();
    }

}

class MyThread extends Thread {
    private Thread t;
    private String threadName;

    MyThread(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);

    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i=4;i>0;i--) {
                System.out.println("Thread:" + threadName + ", " + i);
                // Sleep for a while
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
        }
        t.start();
    }
}
