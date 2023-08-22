package org.example;

import org.example.part1.IntegerListImpl;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        Integer[] arrBubble = new Integer[100_000];
//        fillArray(arrBubble);
//        Integer[] arrSelection = Arrays.copyOf(arrBubble, arrBubble.length);
//        Integer[] arrInsertion = Arrays.copyOf(arrBubble, arrBubble.length);
//        long start = System.currentTimeMillis();
//        sortBubble(arrBubble);
//        System.out.println(System.currentTimeMillis() - start);
//        start = System.currentTimeMillis();
//        sortSelection(arrSelection);
//        System.out.println(System.currentTimeMillis() - start);
//        start = System.currentTimeMillis();
//        sortInsertion(arrInsertion);
//        System.out.println(System.currentTimeMillis() - start);
        var arr = new Integer[40];
        fillArray(arr);
        var asd = new IntegerListImpl(arr);
        System.out.println(Arrays.toString(asd.toArray()));
        asd.contains(1);
        System.out.println(Arrays.toString(asd.toArray()));
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j+1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(Integer[] arr, int a, int b){
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    private static void fillArray(Integer[] arr) {
        var rng = new Random();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = rng.nextInt(10);
        }
    }
}