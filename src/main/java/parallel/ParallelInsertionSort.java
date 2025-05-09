package main.java.parallel;

import java.util.concurrent.RecursiveAction;

public class ParallelInsertionSort extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelInsertionSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= 1000) {
            insertionSort(array, start, end);
        } else {
            int mid = (start + end) / 2;

            ParallelInsertionSort left = new ParallelInsertionSort(array, start, mid);
            ParallelInsertionSort right = new ParallelInsertionSort(array, mid + 1, end);

            invokeAll(left, right);
            merge(start, mid, end);
        }
    }

    private void insertionSort(int[] arr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= start && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private void merge(int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) temp[k++] = array[i++];
        while (j <= end) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, start, temp.length);
    }
}
