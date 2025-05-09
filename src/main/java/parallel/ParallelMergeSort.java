package main.java.parallel;

import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelMergeSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < 500) {
            // Fallback para versão sequencial quando o array é pequeno
            sequentialSort(array, start, end);
        } else {
            int mid = (start + end) / 2;

            ParallelMergeSort left = new ParallelMergeSort(array, start, mid);
            ParallelMergeSort right = new ParallelMergeSort(array, mid + 1, end);

            invokeAll(left, right);
            merge(start, mid, end);
        }
    }

    private void sequentialSort(int[] arr, int start, int end) {
        // Implementa MergeSort sequencial
        if (start < end) {
            int mid = (start + end) / 2;
            sequentialSort(arr, start, mid);
            sequentialSort(arr, mid + 1, end);
            merge(start, mid, end);
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
