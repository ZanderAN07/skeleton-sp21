package gh2;

import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        GuitarString[] GuitarConcert = new GuitarString[37];
        Double InitialConcert = 440.0;
        GuitarConcert[1] = new GuitarString(InitialConcert);
        for (int a = 1; a < 37; a++) {
            Double newConcert = InitialConcert * Math.pow(2, 3.0 / 12.0);
            InitialConcert = newConcert;
            GuitarConcert[a] = new GuitarString(newConcert);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index == -1) {
                    System.out.println("Unexpected Token");
                }
                GuitarConcert[index].pluck();
            }
        }
    }
}
