package org.example.part1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class StringListImpl implements CustomListString {

    private String[] values;

    public StringListImpl() {
        values = new String[]{};
    }

    public StringListImpl(Collection<String> strings) {
        values = strings.toArray(String[]::new);
    }

    public StringListImpl(String[] strings) {
        values = Arrays.copyOf(strings, strings.length);
    }

    @Override
    public String add(String item) {
        add(values.length, item);
        return item;
    }


    @Override
    public String add(int index, String item) {
        if (item == null) throw new NullPointerException();
        if (index < 0 || index > values.length) {
            throw new IndexOutOfBoundsException();
        }

        var result = new String[values.length + 1];
        result[index] = item;
        System.arraycopy(values, 0, result, 0, index);
        System.arraycopy(values, index, result, index + 1, values.length - index);
        values = result;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);

        values[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int index = search(item);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String toReturn = values[index];
        String[] result = new String[values.length - 1];
        System.arraycopy(values, 0, result, 0, index);
        System.arraycopy(values, index + 1, result, index, values.length - index - 1);
        values = result;
        return toReturn;
    }

    @Override
    public boolean contains(String item) {
        return search(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        return search(item);
    }

    @Override
    public int lastIndexOf(String item) {
        return searchReverse(item);
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public boolean equals(CustomListString otherList) {
        if (otherList == null) throw new NullPointerException();

        if (this == otherList) return true;

        if (values.length != otherList.size()) return false;

        for (int i = 0; i < values.length; ++i) {
            if (!values[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public boolean isEmpty() {
        return values.length == 0;
    }

    @Override
    public void clear() {
        values = new String[]{};
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    private int search(String item) {
        int i = 0;
        for (; i < values.length; ++i) {
            if (values[i].hashCode() == item.hashCode() && values[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private int searchReverse(String item) {
        int i = values.length - 1;
        for (; i >= 0; --i) {
            if (values[i].hashCode() == item.hashCode() && values[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
