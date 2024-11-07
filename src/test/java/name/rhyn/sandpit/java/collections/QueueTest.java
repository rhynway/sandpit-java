package name.rhyn.sandpit.java.collections;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Test;

class QueueTest {

  @Test
  void priorityQueue() {
    // The elements of the priority queue are ordered according to their comparable natural ordering
    Queue<Integer> prioritizedNumbers = new PriorityQueue<>();
    prioritizedNumbers.add(11);
    prioritizedNumbers.offer(12);
    prioritizedNumbers.offer(13);
    // numberQueue.add(null); null can't be added

    assertEquals(11, prioritizedNumbers.peek());
    assertEquals(3, prioritizedNumbers.size());
    assertEquals(11, prioritizedNumbers.poll());
    assertEquals(2, prioritizedNumbers.size());
    assertTrue(prioritizedNumbers.remove(12));
    assertEquals(1, prioritizedNumbers.size());
    assertEquals(13, prioritizedNumbers.element());
  }

  @Test
  void linkedList() {
    Queue<String> linkedListQueue = new LinkedList<>();
    linkedListQueue.add("A");
    linkedListQueue.offer("B");

    assertEquals(2, linkedListQueue.size());
    assertEquals("A", linkedListQueue.poll());
    assertEquals("B", linkedListQueue.peek());
  }

  @Test
  void arrayQueue() {
    String one = "One";
    String two = "Two";
    String three = "Three";
    String four = "Four";
    String five = "Five";

    Deque<String> texts = new ArrayDeque<>();
    boolean oneAdded = texts.add(one);
    assertTrue(oneAdded);
    texts.offer(two);
    texts.add(three);
    texts.offerLast(four);

    assertEquals(4, texts.size());
    assertEquals("[One, Two, Three, Four]", texts.toString());

    assertEquals(one, texts.element()); // Doesn't remove
    assertEquals(one, texts.peek()); // Doesn't remove
    assertEquals(four, texts.peekLast());// Doesn't remove
    assertEquals(one, texts.remove());
    assertEquals("[Two, Three, Four]", texts.toString());
    assertEquals(two, texts.poll()); // Removes and returns entry
    assertEquals("[Three, Four]", texts.toString());
    assertEquals(four, texts.getLast());
    assertEquals(four, texts.peekLast());
    assertEquals(three, texts.removeFirst());
    assertEquals(four, texts.pollFirst());
    assertTrue(texts.isEmpty());
    texts.add(five);
    texts.add(five);
    assertTrue(texts.removeFirstOccurrence(five));
    assertEquals(1, texts.size());
  }

  @Test
  void arrayBlockingQueue() throws InterruptedException {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);
    blockingQueue.put("A");
    blockingQueue.put("B");

    assertEquals(2, blockingQueue.size());
    assertEquals("A", blockingQueue.take());
    assertEquals("B", blockingQueue.take());
    assertEquals(0, blockingQueue.size());
  }

}
