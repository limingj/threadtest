import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class MyCallnable implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println("**********");
        System.out.println("**********");
        return null;
    }
}

public class Test02 {
    public static void main(String[] args) {
        FutureTask f = new FutureTask(Executors.callable(()->{
            System.out.println(Thread.currentThread().getName());
        }));
        Thread t = new Thread(f,"bb");
    }
}
