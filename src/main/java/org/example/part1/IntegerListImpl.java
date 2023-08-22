package org.example.part1;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class IntegerListImpl implements CustomListInteger {
    private Integer[] values;
    private int size;
    private boolean isSorted = false;

    public IntegerListImpl() {
        values = new Integer[10];
    }

    public IntegerListImpl(Collection<Integer> ints) {
        values = ints.toArray(Integer[]::new);
        size = ints.size();
    }

    public IntegerListImpl(Integer[] ints) {
        values = Arrays.copyOf(ints, ints.length);
        size = ints.length;
    }

    @Override
    public Integer add(Integer item) {
        return add(size, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (values.length == size) {
            values = grow();
        }

        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = item;
        ++size;
        isSorted = false;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);

        values[index] = item;
        isSorted = false;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        var index = indexOf(item);
        if (index == -1) throw new NoSuchElementException();

        return remove(index);
    }

    private Integer remove(int index) {
        checkIndex(index);
        Integer toReturn = values[index];

        if (size - 1 > index) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
        }
        --size;

        return toReturn;
    }

    @Override
    public boolean contains(Integer item) {
        return search(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        return search(item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        return searchReverse(item);
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public boolean equals(CustomListInteger otherList) {
        if (otherList == null) throw new NullPointerException();

        if (this == otherList) return true;

        if (this.size() != otherList.size()) return false;

        for (int i = 0; i < values.length; ++i) {
            if (!values[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        values = new Integer[10];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(values, size);
    }

    private void sort() {
        for (int i = 0; i < size; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (this.values[j] < this.values[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            if (i != minElementIndex) {
                this.values[i] = this.values[i] ^ this.values[minElementIndex];
                this.values[minElementIndex] = this.values[i] ^ this.values[minElementIndex];
                this.values[i] = this.values[i] ^ this.values[minElementIndex];
            }
        }
        isSorted = true;
    }

    public void quickSort(Integer[] arr, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(this.values, start, end);

            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                if (!arr[i].equals(arr[j])){
                        arr[i] = arr[i] ^ arr[j];
                        arr[j] = arr[i] ^ arr[j];
                        arr[i] = arr[i] ^ arr[j];
                }

                //swapElements(arr, i, j);
            }
        }

        if (!arr[i+1].equals(arr[end])){
            arr[i+1] = arr[i+1] ^ arr[end];
            arr[end] = arr[i+1] ^ arr[end];
            arr[i+1] = arr[i+1] ^ arr[end];
        }
        //swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void checkItem(Integer item) {
        if (item == null) throw new NullPointerException();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private int search(Integer item) {
        if (!isSorted) {
            this.quickSort(this.values, 0, size-1);
        }
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(values[mid])) {
                while (mid - 1 > 0 && values[mid].equals(values[mid - 1])) {
                    --mid;
                }
                return mid;
            }

            if (item < values[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return -1;
    }

    private int searchReverse(Integer item) {
        if (!isSorted) {
            this.quickSort(this.values, 0, size-1);
        }
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(values[mid])) {
                while (mid < size && values[mid].equals(values[mid + 1])) {
                    ++mid;
                }
                return mid;
            }

            if (item < values[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return -1;
    }

    private Integer[] grow() {
        return Arrays.copyOf(values, (int) (values.length * 1.5));
    }
}
