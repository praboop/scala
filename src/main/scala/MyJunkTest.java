import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyJunkTest {

    public MyJunkTest() throws Exception {
        System.out.println("One");
        wait(6000);
        System.out.println("Done");
    }

    public static void main(String...args) throws Exception {
        /*
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();

        for (int i=10; i< 20; i+=10)
        queue.put(i);

        ArrayList<Integer> list = new ArrayList();

        System.out.println(list + " AND " + queue);

        list.add(queue.poll(1, TimeUnit.SECONDS));

        System.out.println(list + " AND " + queue);

        queue.drainTo(list);

        System.out.println(list + " AND " + queue);

         */
    new MyJunkTest();


    }
}
