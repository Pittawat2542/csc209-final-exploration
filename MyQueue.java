public class MyQueue {

    int[] info;
    int head, tail;
    int size;

    public MyQueue(int size) {
        info = new int[size];
        head = tail = -1;
    }

    public void enqueue(int info) {
        if (!isFull()) {
            if (isEmpty()) {
                head = tail = 0;
                this.info[0] = info;
            } else {
                if (tail == this.info.length - 1) {
                    tail = 0;
                    this.info[tail] = info;
                } else {
                    this.info[++tail] = info;
                }
            }
            size++;
        } else {
            System.out.println("Queue is full!");
        }
    }

    public int dequeue() {
        if (!isEmpty()) {
            if (head == tail) {
                int data = info[head];
                head = tail = -1;
                size--;
                return data;
            } else {
                if (head == this.info.length - 1) {
                    int data = info[head];
                    head = 0;
                    size--;
                    return data;
                } else {
                    size--;
                    return info[head++];
                }
            }
        } else {
            System.out.println("Nothing to dequeue!");
            return Integer.MAX_VALUE;
        }
    }

    public int peek() {
        if (!isEmpty()) {
            return info[head];
        } else {
            System.out.println("Nothing to peek at queue!");
            return Integer.MAX_VALUE;
        }
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean isFull() {
        return (head == 0 && tail == info.length - 1) || (head == tail + 1);
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = -1;
    }

}
