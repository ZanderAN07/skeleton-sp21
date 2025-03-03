package gh2;

import deque.ArrayDeque;

// Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency. */
    public GuitarString(double frequency) {
        int capacity = (int) (SR / frequency); // Calculate buffer capacity
        buffer = new ArrayDeque<>();  // Initialize the buffer
        for (int a = 0; a < capacity; a++) {
            buffer.addFirst(0.0);  // Fill buffer with zeros
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int size = buffer.size();
        // Remove all elements in the buffer
        for (int s = 0; s < size; s++) {
            buffer.removeFirst();
        }
        // Add new random values to the buffer
        for (int s = 0; s < size; s++) {
            double r = Math.random() - 0.5; // Random number between -0.5 and 0.5
            buffer.addFirst(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. */
    public void tic() {
        double factor1 = buffer.get(0);  // First element in the buffer
        double factor2 = buffer.get(1);  // Second element in the buffer
        // Apply the Karplus-Strong formula and decay
        double adder = DECAY * 0.5 * (factor1 + factor2);
        buffer.removeFirst();  // Remove the first element
        buffer.addLast(adder); // Add the new calculated value to the end
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);  // Return the first element in the buffer
    }
}

