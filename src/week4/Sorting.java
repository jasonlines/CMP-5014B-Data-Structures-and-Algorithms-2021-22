package week4;

import java.util.Arrays;
import java.util.Random;

public class Sorting{


    public static void selectionSort(int[] ar){
        int min;
        int temp;
        for(int i=0; i<ar.length-1; i++){
            min=i;
            for(int j=i+1;j<ar.length;j++){
                if(ar[j] < ar[min]){
                    min=j;
                }
            }
            if(i!=min){
                temp=ar[i];
                ar[i]=ar[min];
                ar[min]=temp;
            }
        }
    }

    public static void insertionSort(int[] ar) {
        int temp;
        int j;
        for(int i=1; i < ar.length; i++){
            temp = ar[i];
            j = i-1;
            while(j>=0 && temp<ar[j]){
                ar[j+1] = ar[j];
                j--;
            }
            ar[j + 1] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp;
        for(int i = n-1; i > 0; i--){
        // Scan from (0) to i, swapping any out of order elements
            for(int j = 0; j < i; j++){
        // Examine neighbours and swap if out of order
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static int[] generateRandomArray(int size){
        int[] output = new int[size];
        Random r = new Random();
        for(int i = 0; i < size; i++){
            output[i] = r.nextInt(size);
        }
        return output;
    }

    public static int[] generateOrderedArray(int size){
        int[] output = new int[size];
        for(int i = 0; i < size; i++){
            output[i] = i;
        }
        return output;
    }

    public static int[] generateReversedArray(int size){
        int[] output = new int[size];
        for(int i = 0; i < size; i++){
            output[i] = size-i;
        }
        return output;
    }


    public enum Case{BEST,WORST,RANDOM}
    public enum Algo{SELECTION, BUBBLE, INSERTION}

    public static void experiment(Case caseToTest, Algo algoToUse, int startN, int endN, int increment) {

        System.out.println(algoToUse+" -- "+caseToTest);
        long startTime;
        int[] input = null;

        for(int inputSize = startN; inputSize <= endN; inputSize+=increment){
            System.out.print(inputSize);
            for(int repetition = 0; repetition < 5; repetition++) {

                switch (caseToTest) {
                    case BEST -> input = generateOrderedArray(inputSize);
                    case WORST -> input = generateReversedArray(inputSize);
                    case RANDOM -> input = generateRandomArray(inputSize);
                }

                startTime = System.nanoTime();
                switch (algoToUse) {
                    case SELECTION -> selectionSort(input);
                    case BUBBLE -> bubbleSort(input);
                    case INSERTION -> insertionSort(input);
                }
                System.out.print(","+(System.nanoTime()-startTime));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateReversedArray(10)));

        Case caseToTest = Case.RANDOM;
        Algo algoToUse = Algo.SELECTION;

        // experiment args are case, algo, start n, end n, increment
        experiment(caseToTest,algoToUse, 10000, 100000, 10000); // "cold" run
        experiment(caseToTest,algoToUse, 10000, 100000, 10000);


        /*
            NOTE:
            A student reported an interesting find to me (which I've recreated) where bubble sort in the worst case
            (i.e. reversed input) actually takes less time than using randomised inputs. This is unexpected, but I
            believe it's a case of the JVM being "helpful" and optimising code at compile time as it detects that the
            logical operator is unnecessary (since swaps are always made in the worst case). This is an interesting
            example of why we should stick to formal analyses of algorithms to understand how an algorithm scales,
            rather than placing too much faith in wall clock experiments like this, as there can be many known (and
            unknown!) variables that affect the analysis. You should see that for the randomised inputs (i.e. simulating
            an average case) the ranking of the three sorting algorithms is as expected, with insertion sort being
            fastest from selection and then bubble sort being the slowest. Try running your own experiments to see if
            you observe a similar trend.
         */

    }


}