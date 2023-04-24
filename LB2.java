package org.example;

public class PerceptronArabian {
    double[] enters;
    double outer;
    double[] weights;
    double[][] patterns = {
            {
                    0, 0, 0, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 1, 0, 0, 0, 0, 1, 0, 0,
                    0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                    1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                    0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 1, 0, 0, 0, 0, 1, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 0, 0, 0,
            },
            {
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                    0, 0, 0, 1, 0, 1, 0, 0, 0, 0,
                    0, 0, 1, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            },
            {
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0,
                    0, 0, 0, 0, 1, 0, 0, 1, 0, 0,
                    0, 0, 0, 1, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                    0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
                    0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                    0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                    0, 0, 0, 1, 1, 1, 1, 1, 1, 0,
            },
            {
                    0, 0, 0, 0, 0, 1, 1, 0, 0, 0,
                    0, 0, 0, 0, 1, 0, 0, 1, 0, 0,
                    0, 0, 0, 1, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 1, 1, 1, 1, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 1, 0, 0, 0, 0, 1, 0,
                    0, 0, 0, 0, 1, 1, 1, 1, 0, 0,
            }
    };

    double answers[] = {0,1,2,3};

    public PerceptronArabian() {
        enters = new double[patterns[0].length];
        weights = new double[enters.length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 0.2 * Math.random() + 0.1;
        }
    }

    public void countOuter() {
        outer = 0;

        for (int i = 0; i < enters.length; i++) {
            outer += enters[i] * weights[i];
        }
        if (outer > 0.5) outer = 1;
        else outer = 0;
    }


    public void test() {
        study();
        for (int p = 0; p < patterns.length; p++) {
            enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length);
            countOuter();
            System.out.println(outer*answers[p]);
        }
    }


    public void study() {

        double gError = 0;
        do {
            gError = 0;
            for (int p = 0; p < patterns.length; p++) {
                enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length);
                countOuter();
                double error;
                if(answers[p]!=0){
                    error = answers[p]/answers[p] - outer;
                }
                else {
                    error = answers[p] - outer;
                }
                gError += Math.abs(error);
                for (int i = 0; i < enters.length; i++) {
                    weights[i] += 0.1 * error * enters[i];
                }
            }
        } while (gError != 0);
    }
}
