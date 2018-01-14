package timesort;

import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

import timesort.imp.TimeSort;

public class SorterThread implements Runnable{

    private double[] sortedArray;
    private double[] timelyArray;
    private TimeSort sorter;
    private double arrayCorrelation;
    private SortingAlgorithm algorithm;

    public SorterThread(double[] timelyArray, double[] sortedArray, int compLimit, SortingAlgorithm algorithm){
        this.timelyArray = timelyArray;
        this.sortedArray = sortedArray;
        this.algorithm = algorithm;
        sorter = new TimeSort(compLimit);
    }

    public double getArrayCorrelation() {
        return arrayCorrelation;
    }

    @Override
    public void run() {
        switch (algorithm){
            case COMBSORT: sorter.combSort(timelyArray);
                break;
            case QUICKSORT: sorter.quickSort(timelyArray);
                break;
            case BUBBLESORT: sorter.bubbleSort(timelyArray);
                break;
        }
        arrayCorrelation = new KendallsCorrelation().correlation(sortedArray, timelyArray);
    }
}
