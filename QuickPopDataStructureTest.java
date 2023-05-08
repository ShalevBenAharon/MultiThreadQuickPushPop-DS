package DeveloperExercise;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class QuickPopDataStructureTest {

    @Test
    void push() {
        QuickPopDataStructure<Integer> PQ = new QuickPopDataStructure(new TestIntComperator());
        QuickPopDataStructure<ClassMate> classQueue = new QuickPopDataStructure<>(new AgeTestCmpr());

        PQ.push(3);
        PQ.push(6);
        PQ.push(7);
        PQ.push(2);
        PQ.push(4);
        Iterator runner = PQ.iterator();
        assertEquals(7, runner.next());
        assertEquals(6, runner.next());
        assertEquals(4, runner.next());
        assertEquals(3, runner.next());
        assertEquals(2, runner.next());

        classQueue.push(new ClassMate("Shani",32));
        classQueue.push(new ClassMate("Ouri",26));
        classQueue.push(new ClassMate("Kosta",33));
        classQueue.push(new ClassMate("Racheli",23));
        classQueue.push(new ClassMate("Lin",26));
        classQueue.push(new ClassMate("Yonatan",32));
        classQueue.push(new ClassMate("Shalev",31));
    }

    @Test
    void pop() {
        QuickPopDataStructure<Integer> PQ = new QuickPopDataStructure(new TestIntComperator());
        QuickPopDataStructure<ClassMate> classQueue = new QuickPopDataStructure<>(new AgeTestCmpr());
        PQ.push(3);
        PQ.push(6);
        PQ.push(7);
        PQ.push(2);
        PQ.push(4);

        assertEquals(7, PQ.pop());
        assertEquals(6, PQ.pop());
        assertEquals(4, PQ.queueHead.getData());

        classQueue.push(new ClassMate("Shani",32));
        classQueue.push(new ClassMate("Ouri",26));
        classQueue.push(new ClassMate("Kosta",33));
        classQueue.push(new ClassMate("Racheli",23));
        classQueue.push(new ClassMate("Lin",26));
        classQueue.push(new ClassMate("Yonatan",32));
        classQueue.push(new ClassMate("Shalev",31));

        assertEquals("Kosta",((ClassMate)classQueue.pop()).name);
        assertEquals("Yonatan",((ClassMate)classQueue.pop()).name);
        assertEquals("Shani",((ClassMate)classQueue.pop()).name);
        assertEquals("Shalev",((ClassMate)classQueue.pop()).name);
    }
}

class TestIntComperator implements Comparator<Integer> {

    @Override
    public int compare(Integer num1, Integer num2) {
        return num1 - num2;
    }
}

class ClassMate {
    String name;
    int age;

    public ClassMate(String name,int age){
        this.name = name;
        this.age = age;
    }
}

class AgeTestCmpr implements Comparator<ClassMate>{

    @Override
    public int compare(ClassMate classMate, ClassMate classMate2) {
        return classMate.age - classMate2.age;
    }
}