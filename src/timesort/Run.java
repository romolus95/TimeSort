package timesort;

import timesort.ext.RandomArrayGenerator;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static final int COUNT_OF_RUNS = 100000;
    public static final int ARRAY_LENGTH = 160;
    public static final int MAX_COMPARISONS = 300;
    public static final double CORRELATION_REQ = 0.4;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<SorterThread> sorterThreads = new ArrayList<>();
        for(int i = 0; i < COUNT_OF_RUNS; i++){
            double[] unsortedValues = RandomArrayGenerator.generateArray(ARRAY_LENGTH);
            double[] sortedValues = combSort(unsortedValues.clone());
            SorterThread sorterThread = new SorterThread(unsortedValues, sortedValues, MAX_COMPARISONS);
            sorterThreads.add(sorterThread);
            threads.add(new Thread(sorterThread));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();
        double count = 0;
        for(SorterThread st: sorterThreads){
            int corr = (int) (st.getArrayCorrelation()*100);
            if(corr >= (int) (CORRELATION_REQ * 100)) {
                count += 1;
            }
        }
        double percentage = (count/COUNT_OF_RUNS)*100;
        System.out.println("Total count of timely sorted arrays: " + count);
        System.out.println("Percentage of timely sorted arrays: " + percentage);
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
