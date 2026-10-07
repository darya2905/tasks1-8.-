public class MyLinkedList {
    Node head;

    public void add(int data) {
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

    public void clear() {
        head = null;
    }

    public int countLocalMaxima() {
        if (head == null || head.next == null || head.next.next == null) {
            return 0;
        }

        int count = 0;
        Node prev = head;
        Node curr = head.next;
        Node next = curr.next;

        while (next != null) {
            if (curr.data > prev.data && curr.data > next.data) {
                count++;
            }
            prev = curr;
            curr = next;
            next = next.next;
        }
        return count;
    }

    public int countLocalMinima() {
        if (head == null || head.next == null || head.next.next == null) {
            return 0;
        }

        int count = 0;
        Node prev = head;
        Node curr = head.next;
        Node next = curr.next;

        while (next != null) {
            if (curr.data < prev.data && curr.data < next.data) {
                count++;
            }
            prev = curr;
            curr = next;
            next = next.next;
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }
}
