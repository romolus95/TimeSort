package timesort;

import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

import timesort.imp.TimeSort;

public class SorterThread implements Runnable{

    private double[] sortedArray;
    private double[] timelyArray;
    private TimeSort sorter;
    private double arrayCorrelation;

    public SorterThread(double[] timelyArray, double[] sortedArray, int compLimit){
        this.timelyArray = timelyArray;
        this.sortedArray = sortedArray;
        sorter = new TimeSort(compLimit);
    }

    public double getArrayCorrelation() {
        return arrayCorrelation;
    }

    @Override
    public void run() {
        sorter.combSort(timelyArray);
        arrayCorrelation = new KendallsCorrelation().correlation(sortedArray, timelyArray);
    }
}
