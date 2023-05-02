public class Cognitron {
private int numInputs;
private int numOutputs;
private double[][] weights;
private double[] output;
private double[] threshold;

// Конструктор
public Cognitron(int numInputs, int numOutputs) {
this.numInputs = numInputs;
this.numOutputs = numOutputs;
this.weights = new double[numOutputs][numInputs];
this.output = new double[numOutputs];
this.threshold = new double[numOutputs];
}

// Метод для обучения когнитрона
public void train(double[][] input, double[][] target, double learningRate, int numIterations) {
// Реализация алгоритма обучения когнитрона
}

// Метод для распознавания образов
public double[] recognize(double[] input) {
// Реализация алгоритма распознавания образов
return output;
}
}
public void train(double[][] input, double[][] target, double learningRate, int numIterations) {
int numExamples = input.length;
for (int iteration = 0; iteration < numIterations; iteration++) {
for (int example = 0; example < numExamples; example++) {
double[] inputVector = input[example];
double[] targetVector = target[example];
double[] activation = new double[numOutputs];
for (int j = 0; j < numOutputs; j++) {
double sum = 0;
for (int i = 0; i < numInputs; i++) {
sum += weights[j][i] * inputVector[i];
}
activation[j] = sum - threshold[j];
output[j] = activationFunction(activation[j]);
}
for (int j = 0; j < numOutputs; j++) {
double delta = (targetVector[j] - output[j]) * derivativeFunction(activation[j]);
for (int i = 0; i < numInputs; i++) {
weights[j][i] += learningRate * delta * inputVector[i];
}
threshold[j] -= learningRate * delta;
}
}
}
}
public double[] recognize(double[] input) {
double[] activation = new double[numOutputs];
for (int j = 0; j < numOutputs; j++) {
double sum = 0;
for (int i = 0; i < numInputs; i++) {
sum += weights[j][i] * input[i];
}
activation[j] = sum - threshold[j];
output[j] = activationFunction(activation[j]);
}
return output;
}
private double activationFunction(double x) {
return 1 / (1 + Math.exp(-x));
}

private double derivativeFunction(double x) {
double fx = activationFunction(x);
return fx * (1 - fx);
}
public static void main(String[] args) {
// Загрузка данных для обучения
double[][] trainingData = {{0,0,1,1,0,0,0,0,0,0},
{0,1,1,1,1,0,0,0,0,0},
{1,1,0,1,1,1,0,0,0,0},
{1,1,1,0,1,1,1,0,0,0},
{0,1,1,1,1,0,0,0,0,0},
{0,0,1,1,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,1},
{0,0,0,0,0,0,0,0,1,1},
{0,0,0,0,0,0,0,1,1,1},
{0,0,0,0,0,0,1,1,1,0},
{0,0,0,0,0,1,1,1,0,0},
{0,0,0,0,1,1,1,0,0,0},
{0,0,0,1,1,1,0,0,0,0},
{0,0,1,1,1,0,0,0,0,0},
{0,1,1,1,0,0,0,0,0,0},
{1,1,1,0,0,0,0,0,0,0},
{1,1,0,0,0,0,0,0,0,0},
{1,0,0,0,0,0,0,0,0,0},
{1,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,1,1},
{1,0,0,0,0,0,0,1,1,1},
{1,0,0,0,0,0,1,1,1,0},
{1,0,0,0,0,1,1,1,0,0},
{1,0,0,0,1,1,1,0,0,0},
{1,0,0,1,1,1,0,0,0,0},
{1,0,1,1,1,0,0,0,0,0},
{1,1,1,1,0,0,0,0,0,0},
{0,1,1,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}};
double[][] testData = {{0,0,1,1,0,0,0,0,0,0},
{0,1,1,1,1,0,0,0,0,0},
{1,1,1,1,1,1,0,0,0,0},
{1,1,1,1,0,0,0,0,0,0},
{0,1,1,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}};

// Создание когнитрона с 10 входами и 7 скрытыми нейронами
Cognitron cognitron = new Cognitron(10, 7);

// Обучение когнитрона на данных для обучения
cognitron.train(trainingData);

// Распознавание образов в тестовых данных
for (int i = 0; i < testData.length; i++) {
double[] input = testData[i];
double[] output = cognitron.recognize(input);
int recognizedDigit = getMaxIndex(output);

System.out.print("Test " + (i + 1) + ": ");
printArray(input);
System.out.println("Recognized digit: " + recognizedDigit);
}
}

private static int getMaxIndex(double[] array) {
int maxIndex = 0;
double maxValue = array[0];

for (int i = 1; i < array.length; i++) {
if (array[i] > maxValue) {
maxIndex = i;
maxValue = array[i];
}
}

return maxIndex;
}

private static void printArray(double[] array) {
for (double d : array) {
System.out.print((int) d + " ");
}
System.out.println();
}
