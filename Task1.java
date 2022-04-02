package HW4;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static int mergeComparisons = 0;
    public static int mergeExchanges = 0;
    public static int shellComparisons = 0;
    public static int shellExchanges = 0;

    public static void selectionSort(int[] arr) {
        int exchanges = 0;
        int comparisons = 0;
        boolean cont = true;
        for (int i = 0; i != arr.length - 1; i++) {
            if (cont) {
                cont = false;
            }
            int indexOfMinimumValue = i;
            for (int j = i + 1; j != arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[indexOfMinimumValue]) {
                    indexOfMinimumValue = j;
                }
            }
            int originalValue = arr[i];
            arr[i] = arr[indexOfMinimumValue];
            arr[indexOfMinimumValue] = originalValue;
            cont = true;
            exchanges++;
        }
        System.out.println("Exchanges: " + exchanges);
        System.out.println("Comparisons: " + comparisons);
        
    }
    public static void insertionSort(int[] arr) {
        int exchanges = 0;
        int comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j = j - 1;
                exchanges++;
            }
            arr[j + 1] = key;
        }

        System.out.println("Exchanges: " + exchanges);
        System.out.println("Comparisons: " + comparisons);
    }

    public static void merge(int[]source, int[] destination, int lower, int mid, int upper) {
        int s1 = lower; int s2 = mid + 1; int d = lower;
        while (s1 <= mid && s2 <= upper) {
            if (source[s1] < source[s2]) {
                destination[d] = source[s1];
                mergeExchanges ++;
                mergeComparisons ++;
                s1 = s1 + 1;
            } else {
                destination[d] = source[s2];
                mergeExchanges ++;
                mergeComparisons ++;
                s2 = s2 + 1;
            }
            d = d + 1;
        }
        if (s1 > mid) {
            while (s2 <= upper) {
                destination[d] = source[s2];
                mergeExchanges ++;
                s2 = s2 + 1;
                d = d + 1;
            }
        } else {
            while (s1 <= mid)  {
                destination[d] = source[s1];
                mergeExchanges ++;
                s1 = s1 + 1;
                d = d +1;
            }
        }  // end if

    }
    public static void mergeSort(int[]source, int []destination, int lower, int upper) {
        if (lower != upper) {
            int mid = (lower + upper) / 2;
            mergeSort (destination, source, lower, mid);
            mergeSort (destination, source, mid+1, upper);
            merge(source, destination, lower, mid, upper);
        }
        }
        
    public static void doInsertionSort(int[] arr) {
        System.out.print("Array before: ");
        print(arr);
        System.out.println("Insertion Sort");
        insertionSort(arr);
        System.out.print("Array After: ");
        print(arr);

        System.out.println("---------------------");
    }
    public static void doMergeSort(int[] arr) {
        mergeComparisons = 0;
        mergeExchanges = 0;
        System.out.print("Array before: ");
        print(arr);
        System.out.println("Merge Sort: ");
        int []copyarr = arr.clone();
        mergeSort(arr, copyarr, 0, arr.length - 1);
        System.out.println("Exchanges: " + mergeExchanges);
        System.out.println("Comparisons: " + mergeComparisons);
        System.out.print("Array After: ");
        print(copyarr);

        System.out.println("---------------------");
    }

    public static void doSelectionSort(int[] arr) {

        System.out.print("Array before: ");
        print(arr);
        System.out.println("Selection Sort");
        selectionSort(arr);
        System.out.print("Array After: ");
        print(arr);

        System.out.println("---------------------");
    }
    public static void print(int[] arr) {
        for (int j : arr)
            System.out.print(j + " ");
        System.out.println();
    }

    public static int[] readFile(String fileName, int size) {
        int[] arr = new int[size];
        File file = new File(fileName);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
            int index = 0;
            while (fileReader.hasNextLine() && index != size) {
                arr[index] = Integer.parseInt(fileReader.nextLine());
                index++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File or Path name Entered!");
        }
        return arr;
    }
    public static void step1(){
        int size = 10;
        int[] arr = readFile("input1.txt", size);
        int[] newArr = arr;
        System.out.print("Array is: ");
        print(newArr);

        System.out.println("FOR AlMOST SORTED: ");
        Arrays.sort(newArr);
        doSelectionSort(newArr);
        doInsertionSort(newArr);
        doMergeSort(newArr);

        System.out.println("FOR RANDOM: ");
        newArr = readFile("input1.txt", size);
        doSelectionSort(newArr);
        newArr = readFile("input1.txt", size);
        doInsertionSort(newArr);
        newArr = readFile("input1.txt", size);
        doMergeSort(newArr);
    }

    public static void main(String[] args) {

        System.out.println("----------------------- STEP 1 -----------------------------");
        step1();

    }
}

