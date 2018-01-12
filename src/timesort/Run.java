package timesort;

import org.apache.commons.math3.stat.correlation.KendallsCorrelation;
import timesort.ext.RandomArrayGenerator;

import java.util.Arrays;

public class Run {

    public static void main(String[] args) {
        double[] unsortedValues = RandomArrayGenerator.generateArray(100);
        double[] sortedValues = combSort(unsortedValues.clone());
        System.out.println(Arrays.toString(unsortedValues));
        System.out.println(Arrays.toString(sortedValues));
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
