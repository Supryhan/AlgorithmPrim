public class Stack {

    private Node head;
    private int size;

    private class Node {
        public Node previous;
        public String value;
    }
    public String pop() {
        if (head == null) {
            return null;
        }
        String oldValue = head.value;
        head = head.previous;
        size -= 1;
        return oldValue;
    }

    public void push(String value) {
        Node oldValue = head;
        head = new Node();
        head.previous = oldValue;
        head.value = value;
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }
}
