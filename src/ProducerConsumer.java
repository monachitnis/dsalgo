import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {
    private static AtomicInteger req = new AtomicInteger(0);
    private static int LIMIT = 10; //default

    public static void rateLimit(int lim) {
        LIMIT = lim;

        // NOTE: Comment out Test#1 when running Test#2 and vice versa
        // Use Ctrl+C or stop execution to stop test.

        // Test 1: Using another producer thread.

        Thread prod = new Thread(new Producer());
        Thread cons = new Thread(new Consumer());
        prod.start();
        cons.start();

        // Test 2: Using pre-set number of requests, and only consumer thread.

//        req.set(50);
//        Thread cons2 = new Thread(new Consumer());
//        cons2.start();
    }
    private static class Producer implements Runnable {
        @Override
        public void run() {
            while(true) {
                // creating fixed number of requests
                System.out.println("Creating upto requests " + req.addAndGet(20));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //IN A ROBUST MODEL, QUEUING IS NECESSARY, ELSE REQUESTS ARE CONSUMED IN ARBITRARY ORDER
            }
        }
    }
    private static class Consumer implements Runnable {

        @Override
        public void run() {
            while(true) {
                //create batches of LIMIT
                long start = System.currentTimeMillis();
                int batch = Math.min(LIMIT, req.get());
                for (int i = batch; i > 0; i--) {
                    System.out.println("Submitting request " + req.getAndDecrement());
                }
                long end = System.currentTimeMillis();
                long diff = end - start;
                if (diff < 1000) {
                    try {
                        Thread.sleep(1000 - diff);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        rateLimit(10);
    }

}
