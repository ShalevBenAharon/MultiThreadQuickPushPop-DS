package DeveloperExercise;

import java.util.Comparator;
import java.util.concurrent.Semaphore;

public class QuickPushDataStructure<T> implements IPQueue<T> {

    private Node<T> queueHead;
    private Comparator<T> comparator;

    private Object pushLock = new Object();
    private Object popLock = new Object();
    private Semaphore resourceSemaphore = new Semaphore(0);

    public QuickPushDataStructure(Comparator<? extends T> comper) {
        comparator = (Comparator<T>) comper;
        queueHead = new Node<>(null, null, null);
    }

    @Override
    public void push(T data) {
        synchronized (pushLock) {

            Node<T> newHead = new Node<>(data, queueHead, null);
            queueHead.prevNode = newHead;
            queueHead = newHead;
            resourceSemaphore.release();
        }
    }
    @Override
    public T pop() {
        try{
            resourceSemaphore.acquire();
        }catch (InterruptedException e){
            throw new RuntimeException("Thread Interrupt ");
        }
        synchronized (popLock) {

            Node<T> curNode = queueHead;
            Node<T> MaxNode = queueHead;
            while (curNode.nextNode != null) {
                if (comparator.compare(MaxNode.data, curNode.data) < 0) {
                    MaxNode = curNode;
                }
                curNode = curNode.nextNode;
            }
            if (MaxNode.prevNode != null) {
                MaxNode.prevNode.nextNode = MaxNode.nextNode;
                MaxNode.nextNode.prevNode = MaxNode.prevNode;

            }
            else{
                queueHead = queueHead.nextNode;
                queueHead.prevNode = null;
            }
            MaxNode.prevNode = null;
            MaxNode.nextNode = null;
            return MaxNode.getData();
        }
    }

    class Node<T> {

        private Node nextNode;

        private Node prevNode;

        private T data;

        public Node(T data, Node<T> nextNode, Node<T> prevNode) {
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