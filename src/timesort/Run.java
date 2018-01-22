package timesort;

import timesort.ext.RandomArrayGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {

    public static final int COUNT_OF_RUNS = 10000;
    public static final int ARRAY_LENGTH = 160;
    public static final int MAX_COMPARISONS = 300;
    public static final double CORRELATION_REQ = 0.4;
    public static final SortingAlgorithm[] ALGORITHMS = {
            SortingAlgorithm.SHELLSORT, SortingAlgorithm.COMBSORT, SortingAlgorithm.MERGESORT, SortingAlgorithm.QUICKSORT};

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Comparison for " + COUNT_OF_RUNS + " arrays");
        System.out.println("Array length: " + ARRAY_LENGTH + " elements\n");
        Map<SortingAlgorithm, List<SorterThread>> algorithmListMap = new HashMap<>();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < ALGORITHMS.length; i++){
            List<SorterThread> sorterThreads = generateSorterThreads(ALGORITHMS[i]);
            threads.addAll(generateThreads(sorterThreads));
            algorithmListMap.put(ALGORITHMS[i], sorterThreads);
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();
        for(SortingAlgorithm algorithm : algorithmListMap.keySet()) {
            double count = 0;
            double maxCorr = -1;
            double minCorr = 1;
            for (SorterThread st : algorithmListMap.get(algorithm)) {
                int corr = (int) (st.getArrayCorrelation() * 100);
                maxCorr = st.getArrayCorrelation()>maxCorr?st.getArrayCorrelation():maxCorr;
                minCorr = st.getArrayCorrelation()<minCorr?st.getArrayCorrelation():minCorr;
                if (corr >= (int) (CORRELATION_REQ * 100)) {
                    count += 1;
                }
            }
            double percentage = (count / COUNT_OF_RUNS) * 100;
            switch (algorithm) {
                case QUICKSORT:
                    System.out.println("QuickSort:");
                    break;
                case COMBSORT:
                    System.out.println("CombSort:");
                    break;
                case BUBBLESORT:
                    System.out.println("BubbleSort:");
                    break;
                case MERGESORT:
                    System.out.println("MergeSort:");
                    break;
                case SHELLSORT:
                    System.out.println("ShellSort:");
                    break;

            }
            System.out.println("Total count of timely sorted arrays: " + count);
            System.out.println("Percentage of timely sorted arrays: " + percentage);
            System.out.println("Minimal Correlation: " + minCorr);
            System.out.println("Maximal Correlation: " + maxCorr + "\n");
        }
    }

    public static List<SorterThread> generateSorterThreads(SortingAlgorithm algorithm){
        List<SorterThread> sorterThreads = new ArrayList<>();
        for(int i = 0; i < COUNT_OF_RUNS; i++){
            double[] unsortedValues = RandomArrayGenerator.generateArray(ARRAY_LENGTH);
            double[] sortedValues = combSort(unsortedValues.clone());
            SorterThread sorterThread = new SorterThread(unsortedValues, sortedValues, MAX_COMPARISONS, algorithm);
            sorterThreads.add(sorterThread);
        }
        return sorterThreads;
    }

    public static List<Thread> generateThreads(List<SorterThread> sorterThreads){
        List<Thread> threads = new ArrayList<>();
        for(SorterThread st: sorterThreads) threads.add(new Thread(st));
        return threads;
    }

    public static double[] combSort(double[] values){
        double gap = values.length;
        double shrink = 1.3;
        boolean sorted = false;
        while(!sorted){
            int i = 0;
            gap = Math.floor(gap/shrink);
            if(gap > 1){
                sorted = false;
            } else {
                sorted = true;
                gap = 1;
            }
            while (i + gap < values.length){
                if(values[i] > values[(int)(i+gap)]){
                    double temp = values[i];
                    values[i] = values[(int)(i+gap)];
                    values[(int)(i+gap)] = temp;
                    sorted = false;
                }
                i += 1;
            }
        }
        return values;
    }
}
