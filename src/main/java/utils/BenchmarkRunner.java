package main.java.utils;

import main.java.sorters.BubbleSort;
import main.java.sorters.CountingSort;
import main.java.sorters.InsertionSort;
import main.java.sorters.MergeSort;
import main.java.sorters.QuickSort;
import main.java.sorters.SelectionSort;
import main.java.sorters.Sorter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import main.java.parallel.ParallelBubbleSort;
import main.java.parallel.ParallelMergeSort;
import main.java.parallel.ParallelQuickSort;
import main.java.parallel.ParallelInsertionSort;
import main.java.utils.DatasetGenerator;
import main.java.utils.CSVWriter;

@SuppressWarnings("unused")
public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000}; // Tamanhos dos datasets
        String csvFile = "results.csv";

        for (int innerSize : sizes) {
            int[] array = DatasetGenerator.generateRandomArray(innerSize, 100000);
            
            for (int size : sizes) {
    int[] originalArray = DatasetGenerator.generateRandomArray(size, 100000);

    benchmarkSort("BubbleSort", new BubbleSort(), originalArray.clone(), csvFile);
    benchmarkSort("ParallelBubbleSort", new ParallelBubbleSort(), originalArray.clone(), csvFile);

    benchmarkSort("SelectionSort", new SelectionSort(), originalArray.clone(), csvFile);
    benchmarkSort("InsertionSort", new InsertionSort(), originalArray.clone(), csvFile);
    benchmarkSort("CountingSort", new CountingSort(), originalArray.clone(), csvFile);

    benchmarkSort("QuickSort", new QuickSort(), originalArray.clone(), csvFile);
    benchmarkSort("MergeSort", new MergeSort(), originalArray.clone(), csvFile);

    benchmarkParallelForkJoin("ParallelQuickSort", new ParallelQuickSort(originalArray.clone(), 0, originalArray.length - 1), originalArray.clone(), csvFile);
    benchmarkParallelForkJoin("ParallelMergeSort", new ParallelMergeSort(originalArray.clone(), 0, originalArray.length - 1), originalArray.clone(), csvFile);
    benchmarkParallelForkJoin("ParallelInsertionSort", new ParallelInsertionSort(originalArray.clone(), 0, originalArray.length - 1), originalArray.clone(), csvFile);
}
    System.out.println("Finished benchmarking for size: " + innerSize);
            }
System.out.println("Benchmarking completed.");
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
     private static void benchmarkParallelForkJoin(String name, RecursiveAction action, int[] originalArray, String csvFile) {
        int[] arrayCopy = originalArray.clone();
        ForkJoinPool pool = new ForkJoinPool();

        long start = System.currentTimeMillis();
        pool.invoke(action);
        long end = System.currentTimeMillis();

        long duration = end - start;
        System.out.printf("%s took %d ms for %d elements.%n", name, duration, arrayCopy.length);

        String line = String.format("%s,%d,%d", name, arrayCopy.length, duration);
        CSVWriter.writeLine(csvFile, line);
    }
}
