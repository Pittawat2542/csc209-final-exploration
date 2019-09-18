import java.util.Random;

public class Sorting {
    public static void main(String[] args) {
        int[] arr = new int[30];
        System.out.println("Selection Sort");
        randomArray(arr);
        printAll(arr);
        selectionSort(arr);
        printAll(arr);

        System.out.println("Insertion Sort");
        randomArray(arr);
        printAll(arr);
        insertionSort(arr);
        printAll(arr);

        System.out.println("Bubble Sort");
        randomArray(arr);
        printAll(arr);
        bubbleSort(arr);
        printAll(arr);

        System.out.println("Shell Sort");
        randomArray(arr);
        printAll(arr);
        shellSort(arr);
        printAll(arr);

        System.out.println("Heap Sort");
        randomArray(arr);
        printAll(arr);
        heapSort(arr);
        printAll(arr);

        System.out.println("Quick Sort");
        randomArray(arr);
        printAll(arr);
        quickSort(0, arr.length - 1,arr);
        printAll(arr);


        System.out.println("Merge Sort");
        randomArray(arr);
        printAll(arr);
        mergeSort(0, arr.length - 1, arr);
        printAll(arr);

        System.out.println("Radix Sort");
        randomArray(arr);
        printAll(arr);
        radixSort(arr);
        printAll(arr);
    }

    public static void randomArray(int[] arr) {
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000) + 1;
        }
    }

    public static void printAll(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // Find the ith smallest
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }

            // Swap smallest and ith
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (current < arr[j]) { // current is smaller than jth
                    arr[j + 1] = arr[j]; // move to the right
                } else {
                    break;
                }
            }

            // Insert current data to jth
            arr[j + 1] = current;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) { // if data is less than the one before
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void shellSort(int[] arr) {
        for (int divider = 5; divider > 0; divider -= 2) { // 5-sort, 3-sort, 1-sort
            for (int counter = 0; counter < divider; counter++) {
                // Insertion sort
                for (int i = counter + divider; i < arr.length; i += divider) {
                    int current = arr[i];
                    int j = i - divider;
                    for (; j >= 0; j -= divider) {
                        if (current < arr[j]) {
                            arr[j + divider] = arr[j];
                        } else {
                            break;
                        }
                    }

                    arr[j + divider] = current;
                }
            }
        }
    }

    public static void heapSort(int[] arr) {
        // 1. Change array into heap
        Heap heap = new Heap(arr.length);
        // Copy data from array to heap
        for (int i = 0; i < arr.length; i++) {
            heap.heap[i] = arr[i];
        }

        heap.size = arr.length;

        for (int i = arr.length / 2; i >= 0; i--) {
            heap.moveDown(i);
        }

        // 2. Sort
        for (int i = 0; i < arr.length && heap.size != 0; i++) {
            int temp = heap.heap[0];
            heap.heap[0] = heap.heap[heap.size - 1];
            heap.heap[heap.size - 1] = temp;
            heap.size--;

            heap.moveDown(0);
        }

        // Copy data back to array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.heap[i];
        }
    }

    public static void quickSort(int left, int right, int[] arr) {
        if (left < right) {
            int pivot = arr[left];
            int leftPointer = left + 1;
            int rightPointer = right;

            while(leftPointer <= rightPointer) {
                // left pointer try to find big number
                while (leftPointer < rightPointer && arr[leftPointer] < pivot) {
                    leftPointer++;
                }

                // right pointer try to find small number
                while (rightPointer >= leftPointer && arr[rightPointer] >= pivot) {
                    rightPointer--;
                }

                if (leftPointer < rightPointer) {
                    // Swap data
                    int temp = arr[leftPointer];
                    arr[leftPointer++] = arr[rightPointer];
                    arr[rightPointer--] = temp;
                } else {
                    leftPointer++;
                }
            }

            // swap pivot and right
            int temp = arr[left];
            arr[left] = arr[rightPointer];
            arr[rightPointer] = temp;

            // rightPointer is currently the pivot
            quickSort(left, rightPointer - 1, arr);
            quickSort(rightPointer + 1, right, arr);
        }
    }

    public static void mergeSort(int left, int right, int[] arr) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid, arr);
            mergeSort(mid + 1, right, arr);
            merge(left, right, arr);
        }
    }

    public static void radixSort(int[] arr) {
        MyQueue[] queues = new MyQueue[10];

        // Initialize queues
        for (int i = 0; i < 10; i++) {
            queues[i] = new MyQueue(arr.length);
        }

        // Find the longest digits
        // Find max value
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // Find how many digits
        int digits = 0;
        while (max > 0) {
            max /= 10;
            digits++;
        }

        // Start Sorting
        int counter = 1;

        for (int i=0; i < digits; i++) {
            // Check every item on array
            for (int j = 0; j < arr.length; j++) {
                queues[(arr[j] / counter) % 10].enqueue(arr[j]);
            }

            // Dequeue everything
            int j = 0;
            for (int k=0; k < 10; k++) {
                while (!queues[k].isEmpty()) {
                    arr[j++] = queues[k].dequeue();
                }
            }

            counter *= 10;
        }
    }

    public static void merge(int left, int right, int[] arr) {
        int mid = (left + right) / 2;
        int[] temp = new int[right - left + 1];
        int leftPointer = left;
        int rightPointer = mid + 1;
        int tempPointer = 0;

        while (leftPointer <= mid && rightPointer <= right) {
            if (arr[leftPointer] < arr[rightPointer]) {
                temp[tempPointer++] = arr[leftPointer++];
            } else {
                temp[tempPointer++] = arr[rightPointer++];
            }
        }

        // If there is anything left
        while (leftPointer <= mid) {
            temp[tempPointer++] = arr[leftPointer++];
        }

        while (rightPointer <= right) {
            temp[tempPointer++] = arr[rightPointer++];
        }

        // Copy back to original array
        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
}
