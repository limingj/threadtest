import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TickTest{
    private int tick=30;
    private Lock lock = new ReentrantLock();

    public void test(){
        lock.lock();
        try {
            if(tick>0){
                    tick--;
                    System.out.println(Thread.currentThread().getName()+"卖出票，剩余"+tick);
            }
        } finally {
            lock.unlock();
        }
    }
}

/*class Ticket {
    private int count = 30;
    public synchronized void sale(){
        while(count>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出第："+(count--)+"\t还剩："+count);
        }
    }
}*/


public class Test04 {

    public static void main(String[] args) {
        TickTest t= new TickTest();
        ExecutorService pool = Executors.newFixedThreadPool(3);

           try {
               for (int i = 1; i <=30; i++) {
                   pool.execute(()->{
                       t.test();
                   });
               }
           } finally {
               pool.shutdown();
           }
    }
}
