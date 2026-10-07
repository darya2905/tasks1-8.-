package processors;

import models.LinkedQueue;
import java.util.Queue;
import java.util.LinkedList;

public class QueueEvenProcessor {

    public static LinkedQueue<Integer> keepEvenOnlyCustom(LinkedQueue<Integer> queue) {
        LinkedQueue<Integer> result = new LinkedQueue<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int value = queue.dequeue();
            if (value % 2 == 0) {
                result.enqueue(value);
            }
        }

        return result;
    }

    public static Queue<Integer> keepEvenOnlyStd(Queue<Integer> queue) {
        Queue<Integer> result = new LinkedList<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int value = queue.poll();
            if (value % 2 == 0) {
                result.offer(value);
            }
        }

        return result;
    }


    public static Queue<Integer> createStdQueue(int[] numbers) {
        Queue<Integer> queue = new LinkedList<>();
        for (int num : numbers) {
            queue.offer(num);
        }
        return queue;
    }


    public static LinkedQueue<Integer> createCustomQueue(int[] numbers) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int num : numbers) {
            queue.enqueue(num);
        }
        return queue;
    }
}
