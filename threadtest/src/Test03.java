import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Test03 {
    public static void main(String[] args) {
        //Map map = new HashMap();
         List list = new ArrayList();
        ConcurrentHashMap map = new ConcurrentHashMap();
        for (int i = 0; i < 30; i++) { int itemI = i;
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);
            },String.valueOf(itemI)).start();
        }
    }
}
