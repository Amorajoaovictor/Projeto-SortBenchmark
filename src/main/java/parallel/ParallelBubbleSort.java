package main.java.parallel;

import main.java.sorters.Sorter;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelBubbleSort implements Sorter {

    private static final int THRESHOLD = 1000;

    @Override
    public void sort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new BubbleTask(array, 0, array.length - 1));
    }

    private static class BubbleTask extends RecursiveAction {
        private final int[] array;
        private final int left, right;

        BubbleTask(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left < THRESHOLD) {
                bubbleSort(array, left, right);
            } else {
                int mid = (left + right) / 2;
                invokeAll(new BubbleTask(array, left, mid),
                          new BubbleTask(array, mid + 1, right));
            }
        }

        private void bubbleSort(int[] array, int left, int right) {
            for (int i = left; i < right; i++) {
                for (int j = left; j < right - (i - left); j++) {
                    if (array[j] > array[j+1]) {
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
            }
        }
    }
}
