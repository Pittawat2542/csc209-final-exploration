public class HeapTest {
    public static void main(String[] args) {
        Heap heap = new Heap(10);

        heap.enqueue(10);
        heap.enqueue(20);
        heap.enqueue(30);
        heap.enqueue(40);
        heap.enqueue(50);
        heap.enqueue(60);
        heap.enqueue(70);
        heap.enqueue(80);
        heap.enqueue(150);

        heap.printAll();

        heap.dequeue();
        heap.dequeue();
        heap.printAll();
    }
}
