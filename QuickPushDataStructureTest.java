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
        PQ.Push(3);
        PQ.Push(6);
        PQ.Push(7);
        PQ.Push(2);
        PQ.Push(4);

    }
    @Test
    void pop() {
        assertEquals(7,PQ.Pop());
        assertEquals(6,PQ.Pop());
        assertEquals(4,PQ.Pop());
        assertEquals(3,PQ.Pop());
        assertEquals(2,PQ.Pop());
    }

    class TestIntComperator implements Comparator<Integer> {

        @Override
        public int compare(Integer num1, Integer num2) {
            return  num1 - num2;
        }
    }
}