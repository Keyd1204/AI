public HopfieldNetwork(int numNeurons) {
    this.numNeurons = numNeurons;
    this.weights = new double[numNeurons][numNeurons];
}

public void train(double[] pattern) {
    for (int i = 0; i < numNeurons; i++) {
        for (int j = 0; j < numNeurons; j++) {
            if (i == j) {
                weights[i][j] = 0;
            } else {
                weights[i][j] += pattern[i] * pattern[j];
            }
        }
    }
}

public double[] recall(double[] pattern) {
    double[] output = new double[numNeurons];

    for (int i = 0; i < numNeurons; i++) {
        double sum = 0;
        for (int j = 0; j < numNeurons; j++) {
            sum += weights[i][j] * pattern[j];
        }
        if (sum > 0) {
            output[i] = 1;
        } else {
            output[i] = -1;
        }
    }

    return output;
}

public static void main(String[] args) {
    int numNeurons = 100;
    HopfieldNetwork network = new HopfieldNetwork(numNeurons);

    // Train the network on the digit patterns
    double[] pattern0 = {1,-1,-1,-1,1,
                         1,-1,-1,-1,1,
                         1,1,1,-1,1,
                         1,-1,-1,-1,1,
                         1,-1,-1,-1,1};
    network.train(pattern0);

    double[] pattern1 = {-1,-1,1,-1,-1,
                         -1,-1,1,-1,-1,
                         -1,-1,1,-1,-1,
                         -1,-1,1,-1,-1,
                         -1,-1,1,-1,-1};
    network.train(pattern1);

    double[] pattern2 = {1,-1,1,1,-1,
                         1,-1,-1,1,-1,
                         -1,1,-1,-1,1,
                         -1,-1,-1,1,-1,
                         -1,-1,1,-1,1};
    network.train(pattern2);

    // Test the network on a noisy pattern
    double[] testPattern = {1,-1,1,-1,1,
                            -1,-1,-1,-1,-1,
                            1,-1,1,-1,1,
                            -1,-1,-1,-1,-1,
                            1,-1,1,-1,1};
    double[] result = network.recall(testPattern);

    // Print the result
    System.out.println("Input pattern:");
    printPattern(testPattern);
    System.out.println("Recovered pattern:");
    printPattern(result);
}

public static void printPattern(double[] pattern) {
    for (int i = 0; i < pattern.length; i++) {
        if (i % 10 == 0) {
            System.out.println();
        }
        if (pattern[i] > 0) {
            System.out.print("1 ");
        } else {
            System.out.print("0 "); 
}
