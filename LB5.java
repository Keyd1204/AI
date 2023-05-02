public HammingNetwork(int[][] weights, int[] thresholds) {
    this.weights = weights;
    this.thresholds = thresholds;
    this.numNeurons = weights.length;
}

// метод для распознавания образа
public int recognize(int[] input) {
    int[] activations = new int[numNeurons];
    // вычисляем активацию каждого нейрона
    for (int i = 0; i < numNeurons; i++) {
        int sum = 0;
        for (int j = 0; j < input.length; j++) {
            sum += weights[i][j] * input[j];
        }
        activations[i] = sum >= thresholds[i] ? 1 : 0; // активация или ингибирование
    }

    // определяем, какой образ сеть распознала
    int index = -1;
    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < numNeurons; i++) {
        int distance = hammingDistance(activations, weights[i]); // расстояние Хэмминга
        if (distance < minDistance) {
            minDistance = distance;
            index = i;
        }
    }
    return index;
}

// метод для вычисления расстояния Хэмминга
private int hammingDistance(int[] v1, int[] v2) {
    int distance = 0;
    for (int i = 0; i < v1.length; i++) {
        if (v1[i] != v2[i]) {
            distance++;
        }
    }
    return distance;
}
