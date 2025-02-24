package gh2;


import deque.Deque;


import deque.ArrayDeque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer array with zeros.
        ArrayDeque<Double> buffer = new ArrayDeque<>();
        int capacity = (int) (SR / frequency);
        for (int a = 0; a < (capacity); a++) {
            buffer.addFirst(0.0);
        }
        this.buffer = buffer;
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck(){
            // TODO: Dequeue everything in buffer, and replace with random numbers
            //       between -0.5 and 0.5. You can get such a number by using:
            //       double r = Math.random() - 0.5;
            //
            //       Make sure that your random numbers are different from each
            //       other. This does not mean that you need to check that the numbers
            //       are different from each other. It means you should repeatedly call
            //       Math.random() - 0.5 to generate new random numbers for each array index.
            int size = buffer.size();
            for (int s = 0; s < size; s++) {
                buffer.removeFirst();
            }
            for (int s = 0; s < size; s++) {
                double r = Math.random() - 0.5;
                buffer.addFirst(r);
            }
        }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double factor1 = buffer.get(0);
        double factor2 = buffer.get(1);
        double adder = 0.996*0.5*(factor1 + factor2);
        buffer.removeFirst();
        buffer.addLast(adder);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
