import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tick
{
    private int tick = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() {
        lock.lock();
            try {
                while (tick != 0) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                condition.signalAll();
            }

        //幹活
        ++tick;
        System.out.println(Thread.currentThread().getName() + "  " + tick);
        //通知
        this.notifyAll();
    }

    public synchronized void decrement() {
        while (tick == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --tick;
        System.out.println(Thread.currentThread().getName() + " " + tick);
        this.notifyAll();
    }
}

/*
    线程         操作        资源类(高内聚/低耦合）
 */

public class TestTread {


    public static void main(String[] args) {
        //资源类
        Tick tick = new Tick();
        //线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    tick.increment();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    tick.decrement();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    tick.increment();
                }
            }
        }, "C").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    tick.decrement();
                }
            }
        }, "D").start();
    }
}
