package main.java.sorters;

public class CountingSort implements Sorter {

    @Override
    public void sort(int[] array) {
        int max = getMax(array);
        int[] count = new int[max + 1];

        for (int num : array) {
            count[num]++;
        }

        int idx = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                array[idx++] = i;
            }
        }
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }
}
