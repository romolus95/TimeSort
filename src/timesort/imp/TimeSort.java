package timesort.imp;

public class TimeSort {

    private int compCount;
    private int compLimit;

    public TimeSort(int compLimit){
        this.compCount = 0;
        this.compLimit = compLimit;
    }

    public double[] combSort(double[] values){
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
        return values;
    }

    public double[] quickSort(double[] values){
        quickSort(values, 0, values.length-1);
        return values;
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

    public double[] bubbleSort(double[] values) {
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
        return values;
    }

    public boolean compare(double x, double y) {
        compCount++;
        return x > y;
    }

}
