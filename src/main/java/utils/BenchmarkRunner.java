package main.java.utils;

import main.java.sorters.BubbleSort;
import main.java.sorters.Sorter;
import main.java.parallel.ParallelBubbleSort;
import main.java.utils.DatasetGenerator;
import main.java.utils.CSVWriter;

@SuppressWarnings("unused")
public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000}; // Tamanhos dos datasets
        String csvFile = "results.csv";

        for (int size : sizes) {
            int[] array = DatasetGenerator.generateRandomArray(size, 100000);
            
            benchmarkSort("BubbleSort", new BubbleSort(), array, csvFile);
            benchmarkSort("ParallelBubbleSort", new ParallelBubbleSort(), array, csvFile);
        }
    }

    private static void benchmarkSort(String name, Sorter sorter, int[] originalArray, String csvFile) {
        int[] arrayCopy = originalArray.clone();

        long start = System.currentTimeMillis();
        sorter.sort(arrayCopy);
        long end = System.currentTimeMillis();

        long duration = end - start;
        System.out.printf("%s took %d ms for %d elements.%n", name, duration, arrayCopy.length);

        String line = String.format("%s,%d,%d", name, arrayCopy.length, duration);
        CSVWriter.writeLine(csvFile, line);
    }
}
