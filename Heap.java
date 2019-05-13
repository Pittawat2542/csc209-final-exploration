public class Heap {
    int[] heap;
    int size;

    public Heap(int size) {
        heap = new int[size];
    }

    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Heap is full. Cannot enqueue further.");
        } else {
            heap[size++] = data;
            moveUp(size - 1);
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Heap is empty. Nothing to dequeue.");
            return Integer.MAX_VALUE;
        } else {
            int data = heap[0];
            heap[0] = heap[--size];
            moveDown(0);
            return data;
        }
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size >= heap.length;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public int parentPosition(int index) {
        return (index - 1) / 2;
    }

    public int leftChildPosition(int index) {
        return 2 * index + 1;
    }

    public int rightChildPosition(int index) {
        return 2 * index + 2;
    }

    public void moveUp(int index) {
        int parent = parentPosition(index);

        // swap while we not hit root and parent is less than our new data
        while (parent >= 0 && heap[parent] < heap[index]) {
            swap(index, parent);
            index = parent;
            parent = parentPosition(index);
        }
    }

    public void moveDown(int index) {
        int leftChild = leftChildPosition(index);
        int rightChild = rightChildPosition(index);

        // swap while left and right not hit size and our data is smaller than one of them
        while ((leftChild < size && heap[index] < heap[leftChild]) || (rightChild < size && heap[index] < heap[rightChild])) {
            if (rightChild >= size) { // if right child is not exist
                swap(index, leftChild);
                index = leftChild;
            } else { // if we have both left and right child
                // we choose the bigger one
                if (heap[leftChild] > heap[rightChild]) {
                    swap(index, leftChild);
                    index = leftChild;
                } else {
                    swap(index, rightChild);
                    index = rightChild;
                }
            }

            leftChild = leftChildPosition(index);
            rightChild = rightChildPosition(index);
        }
    }

    public void printAll() {
        for (int i=0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
