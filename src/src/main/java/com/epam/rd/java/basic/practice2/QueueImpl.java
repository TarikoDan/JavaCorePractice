package src.main.java.com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {
  private Object[] queue;
  private final int maxSize;
  private int currentSize;
  private final int top;
  private int back;

  public QueueImpl() {
    this.maxSize = 10;
    this.queue = new Object[maxSize];
    this.currentSize = 0;
    this.top = 0;
    this.back = -1;
  }

  public QueueImpl(int maxSize) {
    this.maxSize = maxSize;
    this.queue = new Object[maxSize];
    this.currentSize = 0;
    this.top = 0;
    this.back = -1;
  }

  @Override
  public void clear() {
    this.currentSize = 0;
    queue = new Object[maxSize];
  }

  @Override
  public int size() {
    return this.currentSize;
  }

  public Iterator<Object> iterator() {
    return new IteratorImpl();
  }

  private class IteratorImpl implements Iterator<Object> {
    int index = 0;

    @Override
    public boolean hasNext() {
      return index < currentSize;
    }

    @Override
    public Object next() {
      return queue[index++];
    }
  }

  @Override
  public void enqueue(Object element) {
    if (isFull()) return;
//    maxSize++;
    back++;
    queue[back] = element;
    currentSize++;
  }

  @Override
  public Object dequeue() {
    if (isEmpty()) return null;
    final Object temp = queue[top];
    Object[] newQueue = new Object[maxSize];
    for (int i = 1; i < currentSize; i++) {
      newQueue[i - 1] = queue[i];
    }
    queue = newQueue;
    currentSize--;
    back--;
    return temp;
  }

  @Override
  public Object top() {
    return isEmpty() ? null : queue[top];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("[");
    if (size() > 0) {
      for (int i = 0; i < currentSize - 1; i++) {
        sb.append(queue[i])
            .append(", ");
      }
      sb.append(queue[currentSize - 1]);
    }
    sb.append("]");
    return sb.toString();
  }

  private boolean isFull() {
    return currentSize == maxSize;
  }

  private boolean isEmpty() {
    return currentSize == 0;
  }

  public static void main(String[] args) {
//    final QueueImpl queue = new QueueImpl(5);
//    System.out.println(queue.size());
//    queue.enqueue("A");
//    queue.enqueue("B");
//    queue.enqueue("C");
//    System.out.println(queue.toString());
//    System.out.println(queue.dequeue());
//    System.out.println(queue.top());
    System.out.println("---------------------------------");
    final QueueImpl queue = new QueueImpl();
    System.out.println(queue.size());
    System.out.println(queue.top());
    System.out.println(queue.dequeue());
    System.out.println(queue.toString());

    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.enqueue(null);
    System.out.println(queue.size());
    System.out.println(queue.toString());
    queue.enqueue("D");
    System.out.println(queue.size());
    System.out.println(queue.toString());
    System.out.println(queue.top());
    final Iterator<Object> iterator = queue.iterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next());
    }
    System.out.println("-------");
    System.out.println(queue.dequeue());
    System.out.println(queue.size());
    System.out.println(queue.toString());
    queue.clear();
    System.out.println(queue.size());
    System.out.println(queue.toString());


  }
}
