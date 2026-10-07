public class LinkedList {
    Node head;

    LinkedList() {
        head = null;
    }

    void add(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        System.out.print("List: ");

        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    void findLocalExtremes() {
        if (head == null) {
            System.out.println("Error: list is empty");
            return;
        }

        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        if (size < 3) {
            System.out.println("Error: list has less than 3 elements");
            return;
        }

        int[] arr = new int[size];
        current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }

        int maxCount = 0;
        int minCount = 0;

        System.out.println("\nSearching for local extremes:");

        for (int i = 1; i < size - 1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                maxCount++;
                System.out.println("  Found local maximum: " + arr[i]);
            }
            else if (arr[i] < arr[i-1] && arr[i] < arr[i+1]) {
                minCount++;
                System.out.println("  Found local minimum: " + arr[i]);
            }
        }

        System.out.println("Local maxima: " + maxCount);
        System.out.println("Local minima: " + minCount);
        System.out.println("Total extremes: " + (maxCount + minCount));
    }
}
