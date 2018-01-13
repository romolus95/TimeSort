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
                if(values[i] > values[(int)(i+gap)] && compCount < compLimit){
                    double temp = values[i];
                    values[i] = values[(int)(i+gap)];
                    values[(int)(i+gap)] = temp;
                    sorted = false;
                }
                compCount++;
                i += 1;
            }
        }
        return values;
    }


}
