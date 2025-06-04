package collections;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<E> implements List<E> {
    private Object[] elements;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Negative capacity: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    @Override
    public E put(int index, E element) {
        checkIndex(index);

        E oldValue = elementData(index);

        elements[index] = element;

        return oldValue;
    }

    @Override
    public E remove(int index) {
        // Перевіряємо чи індекс в межах списку
        checkIndex(index);

        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }


        elements[--size] = null;

        return oldValue;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }

        // Перетворюємо колекцію в масив
        Object[] a = c.toArray();  // Правильне оголошення змінної a
        int numNew = a.length;

        // Переконуємося, що у нас достатньо місця
        ensureCapacity(size + numNew);

        // Копіюємо елементи в кінець нашого масиву
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    System.arraycopy(elements, i + 1, elements, i, numMoved);
                }
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}