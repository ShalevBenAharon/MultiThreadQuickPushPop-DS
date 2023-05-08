package DeveloperExercise;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

public class QuickPopDataStructure<T> implements IPQueue<T> , Iterable {

    public Node queueHead;
    private Comparator<T> comparator;

    private Object lockPush = new Object();
    private Object lockPop = new Object();
    private Semaphore pqSizeSemaphoe = new Semaphore(0);

    public QuickPopDataStructure(Comparator<? super T> comperator) {
        this.comparator = (Comparator<T>) comperator;
        queueHead = null;
    }

    @Override
    public void push(T data) {
        synchronized (lockPush) {
            Node<T> curNode = queueHead;
            Node<T> newNode = new Node(data, null, null);

            if (queueHead == null) {
                queueHead = newNode;
            }
            else if (comparator.compare(curNode.data, newNode.data) < 0) {
                newNode.nextNode = curNode;
                curNode.prevNode = newNode;
                queueHead = newNode;
            }
            else {
                while (curNode.nextNode != null && comparator.compare(curNode.data, newNode.data) > 0) {
                    curNode = curNode.nextNode;
                }
                insertNode(curNode, newNode);
            }

            pqSizeSemaphoe.release();
        }
    }

    @Override
    public T pop() {
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

    private void insertNode(Node curNode, Node newNode) {

        if (curNode.nextNode == null ) {
            newNode.prevNode = curNode;
            curNode.nextNode = newNode;

        } else {
            newNode.nextNode = curNode;
            newNode.prevNode = curNode.prevNode;
            curNode.prevNode.nextNode = newNode;
            curNode.prevNode = newNode;
        }
    }

    @Override
    public Iterator iterator() {
        return new PQIterator(queueHead);
    }

    private class PQIterator implements Iterator<T>{
        private Node<T> currentNode;

        public PQIterator(Node<T> startNode){
            currentNode = startNode;
        }
        @Override
        public boolean hasNext() {
            return currentNode.nextNode != null;
        }

        @Override
        public T next() {
            T data = currentNode.getData();
            if(hasNext()) {
                currentNode = currentNode.getNextNode();
            }
            return data;
        }
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

        private Node getNextNode() {
            return nextNode;
        }

        private Node getPrevNode() {
            return prevNode;
        }

        public T getData() {
            return data;
        }

    }

}




