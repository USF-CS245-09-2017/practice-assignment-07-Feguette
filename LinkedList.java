/**
 * Single linked list implemented from a simpler {@code List}
 * @author Jason Liang
 * @version 22 March 2019
 * @param <T> Type for this collection
 */
public class LinkedList<T> implements List<T> {
    /**
     * First element of the list
     */
    private Node<T> head;
    /**
     * Number of elements in the list
     */
    private int size;

    /**
     * Constructs a list starting with a single node
     * @param start starting node
     */
    public LinkedList(Node<T> start) {
        this.head = start;
        size = 1;
    }

    /**
     * Constructs an empty list.
     */
    public LinkedList() {
        this.head = null;
        size = 0;
    }

    /**
     * Checks if the index is within the the domain of [lower, upper]
     * Otherwise, method will throw an exception
     * @param index Index to be compared to
     * @param lower Inclusive lower bound
     * @param upper Inclusive upper bound
     */
    private static void checkOutBounds(int index, int lower, int upper) {
        if (index < lower || index > upper )
            throw new IndexOutOfBoundsException();
    }

    /**
     * Removes and returns the element at index pos by
     * unlinking the node at index pos, and relinking the previous node to the successive node
     * @param pos Index of the element to be removed
     * @return The element that was once at index pos
     */
    @Override
    public T remove(int pos) {
        checkOutBounds(pos, 0, size-1);
        Node<T> proto = head;
        if (pos == 0) {
            head = head.next;
            size--;
            return proto.data;
        }

        for (int i = 1; i < pos; i++) {
            proto = proto.next;
        }
        Node<T> meta = proto.next;
        T temp = meta.data;
        meta = meta.next;
        proto.setNext(meta);
        size--;
        return temp;
    }

    /**
     * Retrieves the element at index pos
     * @param pos Index of the element to be retrieved
     * @return The data at index pos
     */
    public T get(int pos) {
        checkOutBounds(pos, 0, size - 1);
        Node<T> proto = head;
        for (int i = 1; i <= pos; i++) {
            proto = proto.next;
        }
        return proto.data;
    }

    /**
     * Adds a new element at index pos
     * @param pos The index at where the element is to be added
     * @param item The element to be added
     */
    @Override
    public void add(int pos, T item) {
        checkOutBounds(pos, 0, size);
        Node<T> proto = head;
        Node<T> curr = new Node<>(item);
        if (pos != 0) {
            for (int i = 1; i < pos; i++) {
                proto = proto.next;
            }
            curr.setNext(proto.next);
            proto.setNext(curr);
        }
        else {
            curr.setNext(proto);
            head = curr;
        }
        size++;
    }

    /**
     * Adds a new element to the end of the list
     * @param item The data to be added
     */
    @Override
    public void add(T item) {
        add(size, item);
    }
//    @SuppressWarnings("unchecked")
//    public T[] toArray() {
//        T[] temp = (T[])(new Object[size]);
//        int i = 0;
//        for (Node successive = head; successive != null; successive = successive.next) {
//            temp[i++] = successive;
//        }
//        return temp;
//    }
//
//    public boolean contains(T item) {
//        Node successive = head;
//        while (successive != null) {
//            if (successive.equals(item))
//                return true;
//            successive = successive.next;
//        }
//        return false;
//    }

    /**
     * Returns {@code true} if the list is empty
     * @return {@code true} if the list is empty
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Returns the size of the list
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Helper class for storing data
     * @param <T> An element stored on a single Object
     */
    private class Node<T> {
        T data;
        Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        private Node(T data) {
            this(data, null);
        }

        private void setNext(Node<T> next) {
            this.next = next;
        }

        private void setNext(T data) {
            this.next = new Node<>(data);
        }
    }

}
