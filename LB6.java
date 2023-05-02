package org.example;

public class Backpropagation {
    double[] enters;
    double[] outers;
    double[][] weights;
    double[] kohonen;
    double[] countKohActive;

    double[][] patterns =
            {
                    {
                            1, 0
                    },
                    {
                            0, 1
                    }
            };


    Backpropagation(){
        enters = new double[2];
        kohonen = new double[2];
        weights = new double[enters.length][kohonen.length];
        countKohActive = new double[kohonen.length];
        for (int i = 0; i < enters.length; i++){
            for(int j = 0; j < kohonen.length; j++)
            {
                weights[i][j] =Math.random()-0.5;
                //weights[i][j] = 0.1;
            }
        }
        for(int i = 0; i < 1000; i++){
            int maxId = studyKohonen();
            countKohActive[maxId]++;
        }
        for(int i = 0; i < countKohActive.length; i++ ){
            System.out.println(countKohActive[i]);
        }
        // study();
        for (int p = 0; p < patterns.length; p++ ){
            enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length);
            countOut();
            System.out.println(kohonen[0] + " " + kohonen[1]);
        }
    }

    public int countOut(){
        for(int i = 0; i < kohonen.length; i++ ){
            kohonen[i] = 0;
            for(int j = 0; j < enters.length; j++){
                kohonen[i] += enters[j] * weights[j][i];
            }
            //System.out.println(kohonen[i]);
        }
        int di = -1;
        if(countKohActive[0]-countKohActive[1]>20){
            di=0;
        }else if(countKohActive[0]-countKohActive[1]<-15){
            di=1;
        }

        double maxKoh = kohonen[0];
        int maxKohId = 0;
        for(int i = 1; i < kohonen.length; i++){
            if(kohonen[i] > maxKoh){
                maxKoh = kohonen[i];
                maxKohId = i;
            }
        }

        for(int i = 0; i < kohonen.length; i++){
            kohonen[i]= 0;
        }
        kohonen[maxKohId] = 1;

        if(maxKohId == di &&(di == 0))
            return 1;
        else if (maxKohId == di &&(di == 1))
            return 0;
        else return maxKohId;
    }

    public int studyKohonen(){
        double a = 0.7;
        int maxId = 0;
        for (int p = 0; p < patterns.length; p++ ){
            enters = java.util.Arrays.copyOf(patterns[p], patterns[p].length);
            maxId = countOut();
            for (int i = 0; i < enters.length; i++)
            {

                for(int j =0; j < kohonen.length; j++){
                    weights[i][maxId] += a*(enters[i]-weights[i][maxId]);
                }
                //System.out.println(weights[i][j]);
            }
        }
        return maxId;
    }

    public void study(){
        int i = 0;
        while(true){
            int maxId = studyKohonen();
            countKohActive[maxId]++;

            i++;

            if(i > 20*kohonen.length){
                double norma = 0.1;
                double mean = 0;
                double sum = 0;

                for(int j = 0; j < countKohActive.length; j++){
                    sum += countKohActive[j];
                }

                for(int j = 0; j < countKohActive.length; j++){
                    countKohActive[j] /= sum;
                }

                norma = stdev(countKohActive);
                mean = mean(countKohActive);

                boolean ok = true;

                for(int j=0; j<countKohActive.length; j++){
                    if(!(countKohActive[j]<(mean+norma) &&
                            countKohActive[j]>(mean-norma))){
                        ok = false;
                        break;
                    }
                }

                if (ok) break;
            }
        }
    }

    public static double mean(double[] list){
        double sum = 0;
        for (double i : list) {
            sum+=i;
        }
        double mean = sum/list.length;
        return mean;
    }

    public static double stdev(double[] list){
        double sum = 0.0;
        double mean = 0.0;
        double num=0.0;
        double numi = 0.0;
        double deno = 0.0;

        for (double i : list) {
            sum+=i;
        }
        mean = sum/list.length;

        for (double i : list) {
            numi = Math.pow((double) i - mean, 2);
            num+=numi;
        }

        return Math.sqrt(num/list.length);
    }

//    public static void main(String[] args){
//
//        new Backpropagation();
//        new Backpropagation();
//    }
}
