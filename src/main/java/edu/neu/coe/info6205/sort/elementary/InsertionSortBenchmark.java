package edu.neu.coe.info6205.sort.elementary;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class InsertionSortBenchmark {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600}; // Doubling sizes
        for (int size : sizes) {
            benchmarkSorts(size);
        }
    }

    private static void benchmarkSorts(int size) {
        System.out.println("Benchmarking size: " + size);
        Integer[] randomArray = generateRandomArray(size);
        Integer[] orderedArray = generateOrderedArray(size);
        Integer[] partiallyOrderedArray = generatePartiallyOrderedArray(size);
        Integer[] reverseOrderedArray = generateReverseOrderedArray(size);

        benchmark("Random", randomArray);
        benchmark("Ordered", orderedArray);
        benchmark("Partially Ordered", partiallyOrderedArray);
        benchmark("Reverse Ordered", reverseOrderedArray);
    }

    private static void benchmark(String description, Integer[] array) {
        InsertionSort<Integer> sorter = new InsertionSort<>();
        // Warm-up
        for (int i = 0; i < 10; i++) {
            sorter.sort(Arrays.copyOf(array, array.length));
        }
        // Timed run
        long start = System.nanoTime();
        sorter.sort(Arrays.copyOf(array, array.length));
        long duration = System.nanoTime() - start;
        System.out.println(description + " array took " + duration / 1_000_000 + " ms");
    }

    private static Integer[] generateRandomArray(int size) {
        return random.ints(size, 0, size).boxed().toArray(Integer[]::new);
    }

    private static Integer[] generateOrderedArray(int size) {
        Integer[] array = generateRandomArray(size);
        Arrays.sort(array);
        return array;
    }

    private static Integer[] generatePartiallyOrderedArray(int size) {
        Integer[] array = generateOrderedArray(size);
        for (int i = 0; i < size / 10; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            Integer temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

    private static Integer[] generateReverseOrderedArray(int size) {
        Integer[] array = generateOrderedArray(size);
        Arrays.sort(array, Collections.reverseOrder());
        return array;
    }
}

