/**
 * @Author: ZHANG
 * @Date: 2020/1/18
 * @Description: 动态数组
 */
public class Array<T> {
    /**
     * 数组空间
     */
    private int capacity;
    /**
     * 实际元素个数,以及下一个元素的位置（增删时需维护）
     */
    private int size;

    private T[] data;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        data = (T[]) new Object[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    public T get(int index) {
        return data[index];
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        add(size, item);
    }

    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Index must ge 0 and le size");

        if (size == capacity) resize(2 * capacity);

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = item;
        size++;
    }

    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size - 1);
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index must ge 0 and lt size.");

        T removeValue = data[index];

        for (int i = index; i < size; i++) {
            data[index] = data[index + 1];
        }

        size--;
        data[size] = null;

        return removeValue;
    }

    public void set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index must ge 0 and lt size");

        data[index] = item;
    }


    /**
     * 扩容、缩容: 维护容量capacity
     */
    private void resize(int newCapacity) {
        T[] data = (T[]) new Object[newCapacity];
        for (int i = 0; i < this.data.length; i++) {
            data[i] = this.data[i];
        }
        this.data = data;
        this.capacity = newCapacity;
    }
}
