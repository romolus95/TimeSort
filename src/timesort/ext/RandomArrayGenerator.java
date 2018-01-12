package timesort.ext;

import java.util.Random;

/**
 *
 * @author krauses
 */
public class RandomArrayGenerator {

   private static Random random = new Random();

   /**
    * Erzeugt ein Array mit zufälligen, nicht-negativen Werten vom Typ double.
    *
    * @param numValues Länge des erzeugten Arrays
    * @return ein Array vom Typ double mit den oben beschriebenen Eigenschaften
    */
   public static double[] generateArray(int numValues) {
      double[] values = new double[numValues];
      if (random.nextDouble() < 0.4) {
         double q = 0.4;
         for (int i = 0; i < numValues; i++) {
            double r = 100 * Math.log(random.nextDouble()+0.000000001) / Math.log(q);
            values[i] = r;
         }
      } else {
         int max_value = random.nextInt(500) + 1000;
         for (int i = 0; i < numValues; i++) {
            double r = max_value * random.nextDouble();
            values[i] = r;
         }
      }
      return values;
   }

}
