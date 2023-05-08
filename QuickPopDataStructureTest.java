package DeveloperExercise;

import com.sun.org.apache.bcel.internal.util.ClassQueue;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickPopDataStructureTest {

    @Test
    void push() {
        QuickPopDataStructure<Integer> PQ = new QuickPopDataStructure(new TestIntComperator());
        QuickPopDataStructure<ClassMate> classQueue = new QuickPopDataStructure<>(new AgeTestCmpr());

        PQ.Push(3);
        PQ.Push(6);
        PQ.Push(7);
        PQ.Push(2);
        PQ.Push(4);

        assertEquals(7, PQ.queueHead.getData());
        assertEquals(6, PQ.queueHead.getNextNode().getData());
        assertEquals(4, PQ.queueHead.getNextNode().getNextNode().getData());
        assertEquals(3, PQ.queueHead.getNextNode().getNextNode().getNextNode().getData());
        assertEquals(2, PQ.queueHead.getNextNode().getNextNode().getNextNode().getNextNode().getData());

        classQueue.Push(new ClassMate("Shani",32));
        classQueue.Push(new ClassMate("Ouri",26));
        classQueue.Push(new ClassMate("Kosta",33));
        classQueue.Push(new ClassMate("Racheli",23));
        classQueue.Push(new ClassMate("Lin",26));
        classQueue.Push(new ClassMate("Yonatan",32));
        classQueue.Push(new ClassMate("Shalev",31));
    }

    @Test
    void Pop() {
        QuickPopDataStructure<Integer> PQ = new QuickPopDataStructure(new TestIntComperator());
        QuickPopDataStructure<ClassMate> classQueue = new QuickPopDataStructure<>(new AgeTestCmpr());
        PQ.Push(3);
        PQ.Push(6);
        PQ.Push(7);
        PQ.Push(2);
        PQ.Push(4);

        assertEquals(7, PQ.Pop());
        assertEquals(6, PQ.Pop());
        assertEquals(4, PQ.queueHead.getData());

        classQueue.Push(new ClassMate("Shani",32));
        classQueue.Push(new ClassMate("Ouri",26));
        classQueue.Push(new ClassMate("Kosta",33));
        classQueue.Push(new ClassMate("Racheli",23));
        classQueue.Push(new ClassMate("Lin",26));
        classQueue.Push(new ClassMate("Yonatan",32));
        classQueue.Push(new ClassMate("Shalev",31));

        assertEquals("Kosta",((ClassMate)classQueue.Pop()).name);
        assertEquals("Yonatan",((ClassMate)classQueue.Pop()).name);
        assertEquals("Shani",((ClassMate)classQueue.Pop()).name);
        assertEquals("Shalev",((ClassMate)classQueue.Pop()).name);
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