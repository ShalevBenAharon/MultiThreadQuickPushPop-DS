package DeveloperExercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


import static org.junit.jupiter.api.Assertions.*;

class QuickPushDataStructureTest {

    QuickPushDataStructure PQ = null;
    @BeforeEach
    void setUp() {
        PQ = new QuickPushDataStructure<>(new TestIntComperator());
        PQ.push(3);
        PQ.push(6);
        PQ.push(7);
        PQ.push(2);
        PQ.push(4);

    }
    @Test
    void pop() {
        assertEquals(7,PQ.pop());
        assertEquals(6,PQ.pop());
        assertEquals(4,PQ.pop());
        assertEquals(3,PQ.pop());
        assertEquals(2,PQ.pop());
    }


    class TestIntComperator implements Comparator<Integer> {

        @Override
        public int compare(Integer num1, Integer num2) {
            return  num1 - num2;
        }
    }


}