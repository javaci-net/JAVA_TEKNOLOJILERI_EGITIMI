package queue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class QueueTest {

	public class Worker<T, U> extends Thread {
	    private Queue<T> input;
	    private Queue<U> output;
	    private Function<T, U> function;

	    public Worker(Queue<T> input, Queue<U> output, Function<T, U> function) {
	        this.input = input;
	        this.output = output;
	        this.function = function;
	    }

	    @Override
	    public void run() {
	        try {
	            //System.out.println("Running thread:" + Thread.currentThread().getName());
	            T t = input.dequeue();
	            U u = function.apply(t);
	            output.enqueue(u);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }


	}
	
    @Test
    public void testQueue() throws InterruptedException {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, (int)q.dequeue());
        q.enqueue(4);

        assertEquals(2, (int)q.dequeue());
        assertEquals(3, (int)q.dequeue());
        assertEquals(4, (int)q.dequeue());
        //q.dequeue();
        q.enqueue(5);
        assertEquals(5, (int)q.dequeue());
        q.enqueue(6);
        q.enqueue(7);
        assertEquals(6, (int)q.dequeue());
        assertEquals(7, (int)q.dequeue());
    }


    @Test
    public void testMultiThread() throws InterruptedException {
        Queue<Integer> inputQueue = new Queue<>();
        Queue<String> outputQueue = new Queue<>();
        Function<Integer, String> f1 = (i) -> {
            return i.toString();
        };
        Function<String, Integer> f2 = (s) -> {
            return Integer.parseInt(s);
        };
        inputQueue.enqueue(1);
        inputQueue.enqueue(2);
        inputQueue.enqueue(3);
        List<Worker> workerList = new ArrayList<Worker>();
        for (int i=0; i<99; i++) {
            Worker w1 = new Worker(inputQueue, outputQueue, f1);
            w1.start();
            Worker w2 = new Worker(outputQueue, inputQueue, f2);
            w2.start();
            workerList.add(w1);
            workerList.add(w2);
        }
        for (Worker w : workerList)
            w.join();
        assertEquals(1, (int) inputQueue.dequeue());
        assertEquals(2, (int) inputQueue.dequeue());
        assertEquals(3, (int) inputQueue.dequeue());

    }
}