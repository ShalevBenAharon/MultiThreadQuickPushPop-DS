package DeveloperExercise;

import java.util.Comparator;
import java.util.concurrent.Semaphore;

public class QuickPopDataStructure<T> implements IPQueue<T> {

    public Node queueHead;
    Comparator<T> comparator;

    Object lockPush = new Object();
    Object lockPop = new Object();
    Semaphore pqSizeSemaphoe = new Semaphore(0);

    public QuickPopDataStructure(Comparator<? super T> comperator) {
        this.comparator = (Comparator<T>) comperator;
        queueHead = null;
    }

    @Override
    public void Push(T data) {
        synchronized (lockPush) {
            Node<T> curNode = queueHead;
            Node<T> newNode = new Node(data, null, null);

            if (queueHead == null) {
                queueHead = newNode;
                pqSizeSemaphoe.release();
                return;
            }
            if (comparator.compare(curNode.data, newNode.data) < 0) {
                newNode.nextNode = curNode;
                curNode.prevNode = newNode;
                queueHead = newNode;
                pqSizeSemaphoe.release();
                return;
            }
            while (curNode.nextNode != null && comparator.compare(curNode.data, newNode.data) >= 0) {
                curNode = curNode.nextNode;
            }

            if (curNode.nextNode != null ) {
                newNode.nextNode = curNode;
                newNode.prevNode = curNode.prevNode;
                curNode.prevNode.nextNode = newNode;
                curNode.prevNode = newNode;
            }else {
                newNode.prevNode = curNode;
                curNode.nextNode = newNode;
            }
            pqSizeSemaphoe.release();
        }
    }

    @Override
    public T Pop() {
        T data = null;

        try {
            pqSizeSemaphoe.acquire();

            synchronized (lockPop) {
                data = (T) queueHead.data;
                queueHead = queueHead.nextNode;
                queueHead.prevNode = null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Thread interuppted");
        }

        return data;
    }

    class Node<T> {

        private Node nextNode;

        private Node prevNode;

        private T data;

        Node(T data, Node<T> nextNode, Node<T> prevNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
        public Node getNextNode() {
            return nextNode;
        }
        public Node getPrevNode() {
            return prevNode;
        }

        public T getData() {
            return data;
        }

    }

}




