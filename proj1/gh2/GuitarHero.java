package gh2;

import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        GuitarString[] guitarConcert = new GuitarString[37]; // Renamed to camelCase
        Double initialConcert = 440.0; // Renamed to camelCase
        guitarConcert[1] = new GuitarString(initialConcert);
        for (int a = 1; a < 37; a++) {
            Double newConcert = initialConcert * Math.pow(2, 3.0 / 12.0);
            initialConcert = newConcert;
            guitarConcert[a] = new GuitarString(newConcert);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    System.out.println("Unexpected Token");
                }
                guitarConcert[index].pluck();
            }
        }
    }
}
