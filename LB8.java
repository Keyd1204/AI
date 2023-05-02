import java.util.Random;

public class GeneticAlgorithm {
private static final int POPULATION_SIZE = 100;
private static final double MUTATION_RATE = 0.01;
private static final int TOURNAMENT_SIZE = 5;
private static final int ELITE_SIZE = 10;
private static final int NUM_GENERATIONS = 1000;

private static final Random random = new Random();

private static double[][] population = new double[POPULATION_SIZE][2];

public static void main(String[] args) {
    // Инициализация начальной популяции случайными значениями
    for (int i = 0; i < POPULATION_SIZE; i++) {
        population[i][0] = random.nextDouble() * 20 - 10; // x в диапазоне [-10, 10]
        population[i][1] = random.nextDouble() * 20 - 10; // y в диапазоне [-10, 10]
    }

    for (int generation = 1; generation <= NUM_GENERATIONS; generation++) {
        // Вычисление пригодности для каждого индивидуума в популяции
        double[] fitness = new double[POPULATION_SIZE];
        double maxFitness = Double.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            double x = population[i][0];
            double y = population[i][1];
            fitness[i] = 1 / (1 + x * x + y * y);
            if (fitness[i] > maxFitness) {
                maxFitness = fitness[i];
                maxIndex = i;
            }
        }

        // Вывод на экран лучшего индивидуума в текущей популяции
        System.out.printf("Поколение %d: лучшая пригодность = %.4f, x = %.4f, y = %.4f%n",
                generation, maxFitness, population[maxIndex][0], population[maxIndex][1]);

        // Выбор элитных индивидуумов для передачи в следующее поколение
        double[][] elitePopulation = new double[ELITE_SIZE][2];
        for (int i = 0; i < ELITE_SIZE; i++) {
            elitePopulation[i] = population[getMaxFitnessIndex(fitness)];
        }

        // Генерация новой популяции с помощью метода элит или метода рулетки
        double[][] newPopulation = new double[POPULATION_SIZE][2];
        for (int i = 0; i < ELITE_SIZE; i++) {
            newPopulation[i] = elitePopulation[i];
        }
        for (int i = ELITE_SIZE; i < POPULATION_SIZE; i++) {
            double[] parent1 = tournamentSelection(fitness);
            double[] parent2 = tournamentSelection(fitness);
            double[] child = crossover(parent1, parent2);
            mutate(child);
            newPopulation[i] = child;
        }
       
    // Обновление текущей популяции
    population = newPopulation;
}
}

// Возвращает индекс индивидуума с максимальной пригодностью
private static int getMaxFitnessIndex(double[] fitness) {
int maxIndex = 0;
double maxFitness = Double.MIN_VALUE;
for (int i = 0; i < POPULATION_SIZE; i++) {
if (fitness[i] > maxFitness) {
maxFitness = fitness[i];
maxIndex = i;
}
}
return maxIndex;
}

// Метод выбора родителей турнирным отбором
private static double[] tournamentSelection(double[] fitness) {
double[] parent = null;
for (int i = 0; i < TOURNAMENT_SIZE; i++) {
int index = random.nextInt(POPULATION_SIZE);
if (parent == null || fitness[index] > 1 / (1 + parent[0] * parent[0] + parent[1] * parent[1])) {
parent = population[index];
}
}
return parent;
}

// Метод скрещивания двух родителей
private static double[] crossover(double[] parent1, double[] parent2) {
double[] child = new double[2];
child[0] = (parent1[0] + parent2[0]) / 2;
child[1] = (parent1[1] + parent2[1]) / 2;
return child;
}

// Метод мутации
private static void mutate(double[] child) {
if (random.nextDouble() < MUTATION_RATE) {
child[0] += random.nextGaussian() * 0.1; // Мутация x
}
if (random.nextDouble() < MUTATION_RATE) {
child[1] += random.nextGaussian() * 0.1; // Мутация y
}
}
