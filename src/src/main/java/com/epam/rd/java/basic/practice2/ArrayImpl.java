package src.main.java.com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
  private int arraySize;
  private int occupancy;
  private Object[] objectsArray;

  public ArrayImpl(int arraySize) {
    this.arraySize = arraySize;
    this.occupancy = -1;
    this.objectsArray = new Object[arraySize];
  }

  public ArrayImpl() {
    this.arraySize = 0;
    this.occupancy = -1;
    this.objectsArray = new Object[arraySize];
  }

  @Override
  public void clear() {
    objectsArray = new Object[0];
    this.arraySize = 0;
    this.occupancy = -1;
  }

  @Override
  public int size() {
    return this.arraySize;
  }

  @Override
  public Iterator<Object> iterator() {
    return new IteratorImpl();
  }

  private class IteratorImpl implements Iterator<Object> {
    private int index = -1;

    @Override
    public boolean hasNext() {
      if (size() == 0) {
        return false;
      }
      return index < occupancy;
    }

    @Override
    public Object next() {
      if (index >= size())
        throw new NoSuchElementException();
      return get(++index);
    }

  }

  @Override
  public void add(Object element) {
    if (isFull()) arrayGrow();
    objectsArray[++occupancy] = element;
  }

  @Override
  public void set(int index, Object element) {
    if (index < 0) return;
    splitArray(index);
    objectsArray[index] = element;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index >= size()) {
      return new NoSuchElementException();
    }
    return objectsArray[index];
  }

  @Override
  public int indexOf(Object element) {
    int indexOf = -1;
    for (int i = 0; i < size(); i++) {
      if (get(i).equals(element)) {
        indexOf = i;
        break;
      }
    }
    return indexOf;
  }

  @Override
  public void remove(int index) {
    if (index < 0 || index >= size() || isEmpty()) return;
    final Object[] newArray = new Object[--arraySize];
    if (index <= occupancy) {
      copyArray(newArray, 0, index - 1, 0);
      copyArray(newArray, index + 1, occupancy, -1);
      occupancy--;
    } else {
      copyArray(newArray, 0, occupancy, 0);
    }
    objectsArray = newArray;
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    if (size() > 0) {
      for (int i = 0; i < size() - 1; i++) {
        stringBuilder.append(get(i));
        stringBuilder.append(", ");
      }
      stringBuilder.append(get(size() - 1));
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  private boolean isFull() {
    return occupancy == arraySize - 1;
  }

  private boolean isEmpty() {
    return occupancy < 0;
  }

  private void arrayGrow() {
    Object[] newArray = new Object[++arraySize];
    copyArray(newArray, 0, occupancy, 0);
    objectsArray = newArray;
  }

  private void splitArray(int index) {
    if (isFull()) arrayGrow();
    final Object[] newArray;
    if (index >= size()) {
      arraySize = index + 1;
    }
    newArray = new Object[size()];
    if (index <= occupancy) {
      copyArray(newArray, 0, index - 1, 0);
      newArray[index] = null;
      copyArray(newArray, index, occupancy, 1);
      occupancy++;
    } else {
      copyArray(newArray, 0, occupancy, 0);
      occupancy = index;
    }
    objectsArray = newArray;
  }

  private Object[] copyArray(Object[] array, int start, int copyPoint, int offset) {
    for (int i = start; i <= copyPoint; i++) {
      array[i + offset] = objectsArray[i];
    }
    return array;
  }

  public static void main(String[] args) {
    ArrayImpl objectsArray = new ArrayImpl(10);
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.isFull());
    objectsArray.arrayGrow();
    System.out.println(objectsArray.size());
    objectsArray.add("A");
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.toString());
    objectsArray.add("B");
    objectsArray.add("C");
    objectsArray.add(null);
    System.out.println(objectsArray.isFull());
    objectsArray.add("D");
    objectsArray.add("E");
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.toString());
    System.out.println("----------");
    final Iterator<Object> iterator = objectsArray.iterator();
//    while (iterator.hasNext()) {
//      System.out.print(iterator.next());
//    }
    System.out.println("----------");
    System.out.println(objectsArray.get(2));
    System.out.println(objectsArray.get(9));
    System.out.println(objectsArray.get(-5));
    objectsArray.remove(5);
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.toString());
    objectsArray.set(5, "?");
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.toString());
    objectsArray.remove(5);
    System.out.println(objectsArray.size());
    System.out.println(objectsArray.occupancy);
    System.out.println(objectsArray.toString());
    for (int i = 0; i < 11; i++) {
      System.out.println(iterator.next());

    }


//    objectsArray.set(0, "A");
//    objectsArray.add("B");
//    objectsArray.add("C");
//    objectsArray.add(null);
//    objectsArray.add("D");
//    objectsArray.add("E");
//    System.out.println(objectsArray.get(0));
//    System.out.println(objectsArray.toString());
//    System.out.println(objectsArray.size());
//    System.out.println(objectsArray.indexOf("B"));
//    objectsArray.remove(1);
//    objectsArray.clear();

  }
}
