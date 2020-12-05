package src.main.java.com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
  private Object[] objectsList;
  private int listSize;

  public ListImpl() {
    this.objectsList = new Object[0];
  }

  public ListImpl(int listSize) {
    this.objectsList = new Object[listSize];
    this.listSize = listSize;
  }

  @Override
  public void clear() {
    objectsList = new Object[0];
    listSize = 0;
  }

  @Override
  public int size() {
    return this.listSize;
  }

  public Iterator<Object> iterator() {
    return new IteratorImpl();
  }

  private class IteratorImpl implements Iterator<Object> {
    private int index = 0;

    @Override
    public boolean hasNext() {
      return index < size();
    }

    @Override
    public Object next() {
      return objectsList[index++];
    }

  }

  @Override
  public void addFirst(Object element) {
    Object[] oldList = objectsList;
    objectsList = new Object[++listSize];
    objectsList[0] = element;
    for (int i = 0; i < oldList.length; i++) {
      objectsList[i + 1] = oldList[i];
    }
  }

  @Override
  public void addLast(Object element) {
    Object[] oldList = objectsList;
    objectsList = new Object[++listSize];
    objectsList[listSize - 1] = element;
    for (int i = 0; i < oldList.length; i++) {
      objectsList[i] = oldList[i];
    }
  }

  @Override
  public void removeFirst() {
    if (isEmpty()) return;
    Object[] oldList = objectsList;
    objectsList = new Object[--listSize];
    for (int i = 0; i < objectsList.length; i++) {
      objectsList[i] = oldList[i + 1];
    }
  }

  @Override
  public void removeLast() {
    if (isEmpty()) return;
    Object[] oldList = objectsList;
    objectsList = new Object[--listSize];
    for (int i = 0; i < objectsList.length; i++) {
      objectsList[i] = oldList[i];
    }
  }

  @Override
  public Object getFirst() {
    return isEmpty() ? null : objectsList[0];
  }

  @Override
  public Object getLast() {
    return isEmpty() ? null : objectsList[size() - 1];
  }

  @Override
  public Object search(Object element) {
    if (isEmpty()) return new NoSuchElementException();
    for (int i = 0; i < size(); i++) {
      if (element == null) {
        if (objectsList[i] == null) {
          return null;
        }
      } else if (objectsList[i] != null && objectsList[i].equals(element)) {
        return objectsList[i];
      }
    }
    return new NoSuchElementException();
  }

  @Override
  public boolean remove(Object element) {
    if (isEmpty()) return false;
    Object[] oldList = objectsList;
    Object[] newList = new Object[size() - 1];
    int foundedIndex = -1;

//    for (int i = 0; i < size(); i++) {
//      if (element == null) {
//        if (objectsList[i] == null) {
//          foundedIndex = i;
//          break;
//        }
//      } else {
//        if (element.equals(objectsList[i])) {
//          foundedIndex = i;
//          break;
//        }
//      }
//    }
    if (element == null) {
      for (int i = 0; i < size(); i++) {
        if (objectsList[i] == null) {
          foundedIndex = i;
          break;
        }
      }
    } else {
      for (int i = 0; i < size(); i++) {
        if (element.equals(objectsList[i])) {
          foundedIndex = i;
          break;
        }
      }
    }

    if (foundedIndex != -1) {
      for (int i = 0; i < foundedIndex; i++) {
        newList[i] = oldList[i];
      }
      for (int i = foundedIndex; i < newList.length; i++) {
        newList[i] = oldList[i + 1];
      }
      objectsList = newList;
      listSize--;
    }
    return foundedIndex != -1;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("[");
    if (size() > 0) {
      for (int i = 0; i < size() - 1; i++) {
        builder.append(objectsList[i])
            .append(", ");
      }
      builder.append(objectsList[size() - 1]);
    }
    builder.append("]");
    return builder.toString();
  }

  private boolean isEmpty() {
    return size() < 1;
  }

  public static void main(String[] args) {
    final ListImpl list = new ListImpl();
    System.out.println(list.size());
    list.addFirst("a");
    list.addFirst("b");
    list.addFirst(null);
    list.addFirst("c");
    list.addLast("A");
    final Iterator<Object> iterator = list.iterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next());
    }
    System.out.println("------------");
    System.out.println(list.getFirst());
    System.out.println(list.getLast());
    System.out.println(list.search("a"));
    System.out.println("------------");

    System.out.println(list.remove("a"));
    list.removeLast();
    list.removeFirst();
    list.remove(null);
    System.out.println(list.toString());
    list.clear();
    System.out.println(list.toString());
    System.out.println(list.search(null));
    list.addFirst(null);
    System.out.println(list.toString());
    System.out.println(list.search(null));
    System.out.println(list.search("a"));

  }
}
