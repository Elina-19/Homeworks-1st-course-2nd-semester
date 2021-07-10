package com.company;

import java.util.*;

public class ArraySet<T> extends AbstractSet<T> implements SortedSet<T> {

    private final static int DEFAULT_SIZE = 10;

    private T data[];
    private int size;
    private int start;
    private int end;
    private Comparator<T> comparator;

    private ArraySet() {
        data = (T[])new Object[DEFAULT_SIZE];
    }

    public ArraySet(Collection<T> coll){
        checkNull(coll);
        data = (T[])coll.toArray();
        size = coll.size();
    }

    public ArraySet(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    private void setData(T[] array){
        data = array;
    }

    private void checkNull(Collection<?> coll){
        for (int i = 0; i < coll.size(); i++) {
            if (coll.toArray()[i] == null) {
                throw new NullPointerException("The collection can't have null elements");
            }
        }
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException("The element can't be null");
        }
        if (fromElement.equals(toElement)) {
            return new ArraySet<T>();
        }
        ArraySet<T> newSet = new ArraySet<>();
        newSet.start = size-1;
        newSet.end = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(fromElement)) {
                newSet.start = i;
            }
            if (data[i].equals(toElement)) {
                newSet.end = i;
            }
        }

        if (newSet.start > newSet.end) {
            throw new IllegalArgumentException("The first element is greater than second or there's no such element");
        }
        newSet.size = newSet.end-newSet.start;
        int k = 0;
        for (int i = newSet.start; i < newSet.end; i++) {
            newSet.data[k++] = data[i];
        }
        return newSet;
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return subSet(data[0], toElement);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if (fromElement == null) {
            throw new NullPointerException("The element can't be null");
        }
        ArraySet<T> newSet = new ArraySet<>();
        newSet.start = -1;
        newSet.end = size-1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(fromElement)) {
                newSet.start = i;
            }
        }

        if (newSet.start == -1) {
            throw new IllegalArgumentException("There's no such element");
        }

        newSet.size = size - newSet.start;
        int k = 0;
        for (int i = newSet.start; i < size; i++) {
            newSet.data[k++] = data[i];
        }
        return newSet;
    }

    @Override
    public T first() {
        if (data[0] == null) {
            throw new NoSuchElementException("Set is empty");
        }
        return data[0];
    }

    @Override
    public T last() {
        if (data[0] == null) {
            throw new NoSuchElementException("Set is empty");
        }
        return data[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (data[0] == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("The element can't be null");
        }
        for (int i = 0; i < size; i++) {
            if (((T)o).equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicCollectionIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean add(T t) {
        if (size == data.length) {
            increase();
        }
        if (t == null) {
            throw new NullPointerException("The element can't be null");
        }
        for (int i = 0; i<size; i++) {
            if (data[i].equals(t)) {
                return false;
            }
        }
        int index = 0;
        if (comparator != null) {
            for (int i = 0; i < size; i++) {
                if (comparator.compare(t, data[i]) > 0) {
                    index++;
                }
                else {
                    break;
                }
            }
            T[] newArray = (T[]) new Object[data.length];
            System.arraycopy(data, 0, newArray, 0, index);
            newArray[index] = t;
            System.arraycopy(data, index, newArray, index + 1, size - index);
            size++;
            data = newArray;
            return true;
        }
        data[size++] = t;
        return true;
    }

    private void increase() {
        T[] newArray = (T[]) new Object[data.length*2];
        System.arraycopy(data, 0, newArray, 0, data.length);
        data = newArray;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArraySet<?> that = (ArraySet<?>) o;
        return size == that.size && equalsArrays(that);
    }

    private boolean equalsArrays(ArraySet<?> that) {
        int count = 0;
        for (int i = 0; i<size; i++) {
            if (!data[i].equals(that.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("The element can't be null");
        }
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                T[] newArray = (T[])new Object[data.length];
                System.arraycopy(data, 0, newArray, 0, i);
                System.arraycopy(data, i+1, newArray, i, size-i-1);
                size--;
                data = newArray;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkNull(c);
        if (size != c.size()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i<size; i++) {
            for (int j = 0; j<size; j++) {
                if (data[i].equals(c.toArray()[j])) {
                    count++;
                }
            }
        }
        if (count == size) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkNull(c);
        for (int i = 0; i < c.size(); i++) {
            add(((T[])c.toArray())[i]);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNull(c);
        T[] newArray = (T[])new Object[size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < c.size(); j++) {
                if (data[i].equals(c.toArray()[j])) {
                    newArray[k++] = data[i];
                }
            }
        }
        size = k;
        data = newArray;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNull(c);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i].equals(c.toArray()[j])) {
                    remove(data[i]);
                }
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
    }

    private class BasicCollectionIterator implements Iterator<T> {
        private int current = 0;
        private boolean flag;

        @Override
        public boolean hasNext() {

            return current < size;
        }

        @Override
        public T next() {
            if (current < size) {
                flag = true;
                return data[current++];
            }
            throw new NoSuchElementException("No element");
        }

        @Override
        public void remove() {
            if (current != 0 && flag) {
                T[] array = (T[])new Object[data.length];
                System.arraycopy(data, 0, array, 0, current-1);
                System.arraycopy(data, current, array, current-1, size-current);
                current--;
                size--;
                data = array;
            }
            else {
                throw new IllegalStateException("Nothing remove");
            }
        }
    }
}
