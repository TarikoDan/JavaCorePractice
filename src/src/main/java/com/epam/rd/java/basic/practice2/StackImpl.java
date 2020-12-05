package src.main.java.com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class StackImpl implements Stack {
  private Object[] stack;
  private final int maxSize;
  private int top = -1;

  public StackImpl() {
    this.maxSize = 10;
    this.stack = new Object[maxSize];
  }

  public StackImpl(int size) {
    this.maxSize = size;
    this.stack = new Object[size];
  }

  @Override
  public void clear() {
    stack = new Object[maxSize];
    top = -1;
  }

  @Override
  public int size() {
    return this.top + 1;
  }

  public Iterator<Object> iterator() {
    return new IteratorImpl();
  }

  private class IteratorImpl implements Iterator<Object> {
    int index = top;

    @Override
    public boolean hasNext() {
      return index >= 0;
    }

    @Override
    public Object next() {
      return stack[index--];
    }

    @Override
    public void remove() {
      stack[index--] = null;
    }
  }

  @Override
  public void push(Object element) {
    if (isFull()) return;
    stack[++top] = element;
  }

  @Override
  public Object pop() {
    if (isEmpty()) return null;
    final Object o = stack[top];
    stack[top--] = null;
    return o;
  }

  @Override
  public Object top() {
    return isEmpty() ? null : stack[top];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("[");
    if (size() > 0) {
      for (int i = 0; i < size() - 1; i++) {
        sb.append(stack[i])
            .append(", ");
      }
      sb.append(stack[size() - 1]);
    }
    sb.append("]");
    return sb.toString();
  }

  private boolean isFull() {
    return top == maxSize - 1;
  }

  private boolean isEmpty() { return top < 0; }

  public static void main(String[] args) {
    final StackImpl stack = new StackImpl();
    System.out.println(stack.toString());
    System.out.println(stack.size());
    System.out.println(stack.pop());

    stack.push("A");
    stack.push("B");
    stack.push("C");
    stack.push(null);
    System.out.println(stack.toString());
    System.out.println(stack.size());
    final Iterator<Object> iterator = stack.iterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next());
    }
    System.out.println("----------------");
    stack.push("D");
    System.out.println(stack.toString());
    System.out.println(stack.size());

    System.out.println(stack.pop());

    System.out.println(stack.toString());
    System.out.println(stack.pop());

    System.out.println(stack.toString());
    System.out.println(stack.top());

    stack.clear();
    System.out.println(stack.toString());


  }

}
