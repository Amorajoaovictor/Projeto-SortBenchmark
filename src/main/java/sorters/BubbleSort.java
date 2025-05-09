package main.java.sorters;

public class BubbleSort implements Sorter {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) { // cria uma variável para controlar o número de passagens
            // Se não houver troca, o array já está ordenado
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
