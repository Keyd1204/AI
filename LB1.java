import java.util.Random;

public class Perceptron {
private double[] weights;
private double bias;
private double learningRate;

public Perceptron(int inputSize, double learningRate) {
    weights = new double[inputSize];
    Random random = new Random();
    for (int i = 0; i < inputSize; i++) {
        weights[i] = random.nextDouble();
    }
    bias = random.nextDouble();
    this.learningRate = learningRate;
}

public int predict(double[] inputs) {
    double activation = 0;
    for (int i = 0; i < weights.length; i++) {
        activation += weights[i] * inputs[i];
    }
    activation += bias;
    if (activation > 0) {
        return 1;
    } else {
        return 0;
    }
}

public void train(double[][] inputs, int[] targets, int epochs) {
    for (int epoch = 0; epoch < epochs; epoch++) {
        for (int i = 0; i < inputs.length; i++) {
            int prediction = predict(inputs[i]);
            int error = targets[i] - prediction;
            for (int j = 0; j < weights.length; j++) {
                weights[j] += error * inputs[i][j] * learningRate;
            }
            bias += error * learningRate;
        }
    }
}

public static void main(String[] args) {
    // AND logic function
    double[][] andInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    int[] andTargets = {0, 0, 0, 1};
    Perceptron andPerceptron = new Perceptron(2, 0.1);
    andPerceptron.train(andInputs, andTargets, 100);
    System.out.println("AND logic function:");
    for (int i = 0; i < andInputs.length; i++) {
        int prediction = andPerceptron.predict(andInputs[i]);
        System.out.println(andInputs[i][0] + " AND " + andInputs[i][1] + " = " + prediction);
    }

    // OR logic function
    double[][] orInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    int[] orTargets = {0, 1, 1, 1};
    Perceptron orPerceptron = new Perceptron(2, 0.1);
    orPerceptron.train(orInputs, orTargets, 100);
    System.out.println("OR logic function:");
    for (int i = 0; i < orInputs.length; i++) {
        int prediction = orPerceptron.predict(orInputs[i]);
        System.out.println(orInputs[i][0] + " OR " + orInputs[i][1] + " = " + prediction);
    }

    // NOT logic function
    double[][] notInputs = {{0}, {1}};
    int[] notTargets = {1, 0};
    Perceptron notPerceptron = new Perceptron(1, 0.1);
    notPerceptron.train(notInputs, notTargets, 100);
    System.out.println("NOT logic function:");
    for (int i = 0; i < notInputs.length;
    int prediction = notPerceptron.predict(notInputs[i]);
    System.out.println("NOT " + notInputs[i][0] + " = " + prediction);
}
}
