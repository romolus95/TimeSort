package timesort.imp;

import java.util.Arrays;

public class TimeSort {

    private int compCount;
    private int compLimit;
    private final int DEFAULT_BUCKET_SIZE = 50;

    public TimeSort(int compLimit){
        this.compCount = 0;
        this.compLimit = compLimit;
    }

    public void combSort(double[] values){
        double gap = values.length;
        double shrink = 1.3;
        boolean sorted = false;
        while(!sorted && compCount < compLimit){
            int i = 0;
            gap = Math.floor(gap/shrink);
            if(gap > 1){
                sorted = false;
            } else {
                sorted = true;
                gap = 1;
            }
            while (i + gap < values.length && compCount < compLimit){
                if(compare(values[i], values[(int)(i+gap)])){
                    double temp = values[i];
                    values[i] = values[(int)(i+gap)];
                    values[(int)(i+gap)] = temp;
                    sorted = false;
                }
                i += 1;
            }
        }
    }

    public void quickSort(double[] values){
        quickSort(values, 0, values.length-1);
    }

    public void quickSort(double values[], int left, int right) {
        if (left < right) {
            int i = partition(values,left,right);
            quickSort(values,left,i-1);
            quickSort(values,i+1,right);
        }
    }

    public int partition(double values[], int left, int right) {
        double pivot, help;
        int i, j;
        pivot = values[right];
        i = left;
        j = right-1;
        while(i<=j && compCount < compLimit) {
            if (compare(values[i], pivot)) {
                help = values[i];
                values[i] = values[j];
                values[j] = help;
                j--;
            } else i++;
        }
        help = values[i];
        values[i] = values[right];
        values[right] = help;
        return i;
    }

    public void bubbleSort(double[] values) {
        double temp;
        for(int i=1; i<values.length; i++) {
            for(int j=0; j<values.length-i; j++) {
                if(compare(values[j],values[j+1]) && compCount < compLimit) {
                    temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }

    public double[] mergeSort(double[] values) {
        double[] left = Arrays.copyOfRange(values, 0, (values.length+1/2));
        double[] right = Arrays.copyOfRange(values, (values.length+1/2), values.length);
        if(values.length > 1) return values;
        else{
            left = mergeSort(left);
            right = mergeSort(right);
        }
        return merge(left, right);
    }

    public double[] merge(double[] left, double[] right) {
        int i = 0, j = 0, k = 0;
        double[] result = new double[left.length + right.length];
        while (i < left.length-1 && j < right.length-1){
                if(compare(left[i], right[j])){
                    result[k++] = right[j++];
                } else {
                    result[k++] = left[i++];
                }
        }
        while (i < left.length-1){
            result[k++] = left[i++];
        }
        while (j < right.length-1){
            result[k++] = right[j++];
        }
        return result;
    }

    public void shellSort(double[] values){
        int n = values.length;

        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n && compCount < compLimit; i += 1) {
                double temp = values[i];
                int j;
                for (j = i; compareEq(j, gap)
                        && compare(values[j - gap], temp)
                        && compCount < compLimit; j -= gap)
                    values[j] = values[j - gap];
                values[j] = temp;
            }
        }
    }

    public boolean compare(double x, double y) {
        compCount++;
        return x > y;
    }

    public boolean compareEq(double x, double y){
        compCount++;
        return x >= y;
    }

}
