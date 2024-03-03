package org.course_work.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<T extends Comparable<? super T>> implements Sort<T> {


    @Override
    public T[] sort(T[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, mid, arr.length);

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }
    @SuppressWarnings("unchecked")
    private T[] merge(T[] left, T[] right) {
        int totalLength = left.length + right.length;
        T[] result = (T[]) Array.newInstance(left.getClass().getComponentType(), totalLength);

        int leftPointer = 0, rightPointer = 0, resultPointer = 0;
        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer].compareTo(right[rightPointer]) <= 0) {
                result[resultPointer++] = left[leftPointer++];
            } else {
                result[resultPointer++] = right[rightPointer++];
            }
        }

        while (leftPointer < left.length) {
            result[resultPointer++] = left[leftPointer++];
        }
        while (rightPointer < right.length) {
            result[resultPointer++] = right[rightPointer++];
        }

        return result;
    }
}



