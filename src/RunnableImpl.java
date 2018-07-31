public class RunnableImpl {

    public static void main(String[] args) {

        MyRunnable r1 = new MyRunnable("Thread-1");
        r1.start();

        MyRunnable r2 = new MyRunnable("Thread-2");
        r2.start();
    }
}

class MyRunnable implements Runnable {

    private Thread t;
    private String threadName;

    MyRunnable(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for(int i = 4;i > 0;i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // Let thread sleep for a while
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.print("Thread " + threadName + " interrupted.");
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
