package DeveloperExercise;

import java.util.Comparator;
import java.util.Random;

public class MultiThreadsTest {
    public static void main(String[] args) {

        QuickPushDataStructure PQThreads = new QuickPushDataStructure(new TestIntComperator());
        Thread[] ProducerTrd = new Thread[10];
        Thread[] ConsumerTrd = new Thread[10];
        for (int i = 0; i < 10; ++i) {
            ProducerTrd[i] = new Thread(()->{
                Random random = new Random();
                int randomNumber = random.nextInt(100);
                PQThreads.push(randomNumber);
            });
        }

        for (int i = 0; i < 10; ++i) {
            ConsumerTrd[i] = new Thread(()->{
                System.out.println(PQThreads.pop());
            });
        }

        for (int i = 0; i < 10; ++i) {
            ProducerTrd[i].start();
            ConsumerTrd[i].start();
        }

        class TestIntComperator implements Comparator<Integer> {

            @Override
            public int compare(Integer num1, Integer num2) {
                return  num1 - num2;
            }
        }
    }

}
