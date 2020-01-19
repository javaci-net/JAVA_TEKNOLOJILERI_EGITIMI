package queue;

public class Queue<T> {

    private class MyAtomicInt {
        private int value;

        public synchronized int getValue() {
            return value;
        }

        public synchronized void incrementValue() {
            value++;
        }

        public synchronized void decrementValue() {
            value--;
        }
    }

    private T[] elements;
    private int start;
    private int stop;

    private Object enqueueLock = new Object();
    private Object dequeueLock = new Object();


    private MyAtomicInt numElements;

    public Queue() {
        this.elements = (T[]) new Object[3];
        this.start = 0;
        this.stop = 0;
        this.numElements = new MyAtomicInt();
    }

    public void enqueue(T value) throws InterruptedException {
        if (numElements.getValue() == 3) {
            synchronized (dequeueLock) {
                dequeueLock.wait();
            }
        }
        if (stop == -1)
            stop = 0;
        elements[stop] = value;
        stop = (stop + 1) % 3;
        numElements.incrementValue();
        if (stop == 0)
            stop = -1;

        synchronized (enqueueLock) {
            enqueueLock.notify();
        }
    }

    public T dequeue() throws InterruptedException {
        T ret = null;
        if (numElements.getValue() == 0) {
            synchronized (enqueueLock) {
                enqueueLock.wait();
            }
        }
        ret = elements[start];
        start = (start + 1) % 3;
        numElements.decrementValue();
        synchronized (dequeueLock) {
            dequeueLock.notify();
        }
        return ret;
    }

}
