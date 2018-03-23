package com.github.antonkrupnov;

import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public class OddSorting {

    public static boolean canBeSorted(int[] array) {
        int[] sortedArray = sort(array);
        System.out.println(Arrays.toString(sortedArray));
        for (int i = 0; i + 1 < sortedArray.length; i++) {
            if (sortedArray[i + 1] < sortedArray[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] sort(int[] array) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i + 1 < array.length; i++) {
                if (array[i] - array[i + 1] == 1) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
        }
        return array;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

