package main.java.parallel;

import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelQuickSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (start < end) {
            if (end - start < 500) {
                sequentialQuickSort(array, start, end);
            } else {
                int pivot = partition(array, start, end);
                invokeAll(
                    new ParallelQuickSort(array, start, pivot - 1),
                    new ParallelQuickSort(array, pivot + 1, end)
                );
            }
        }
    }

    private void sequentialQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sequentialQuickSort(arr, low, pi - 1);
            sequentialQuickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }

        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
}
