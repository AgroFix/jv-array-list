package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void growIfArrayFull(int minCapacity) {
        if (minCapacity > values.length) {
            int newCapacity = values.length + (values.length >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    @Override
    public void add(T value) {
        growIfArrayFull(size + 1);
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("It`s wrong index: "
                    + index + " for this value: " + value);
        }
        growIfArrayFull(size + 1);
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List equal null");
        }
        growIfArrayFull(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(values[i])
                    || element == null && values[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
    }
}
