/**
 * Resizeable array implemented from a simpler {@code List}
 * @author Jason Liang
 * @version 22 March 2019
 * @param <T> Type for this collection
 */
public class ArrayList<T> implements List<T> {
    /**
     * Default starting capacity
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Container array for storing elements
     */
    private T[] array;

    /**
     * Number of data in the list
     */
    private int size;

    /**
     * Constructs a list with a given initial capacity
     * @param initialSize The initial capacity of the container array
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int initialSize) {
        size = 0;
        array = (T[])(new Object[initialSize]);
    }

    /**
     * Constructs an empty list with {@code DEFAULT_SIZE}
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Decreases all indices greater than pos by one,
     * moving the objects with those indices towards index pos
     * @param pos the index to be shifted
     */
    private void shiftLeft(int pos) {
        for (int i = pos; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    /**
     * Increases all indices greater than or equal to pos by one,
     * moving the objects with those indices away from index pos
     * @param pos the index to be shifted
     */
    private void shiftRight(int pos) {
        for (int i = size; i > pos; i--) {
            array[i] = array[i-1];
        }
    }

    /**
     * Doubles the size of the container array
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        T[] temp = (T[])(new Object[array.length*2]);
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
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
     * Adds a new element to the end of the list
     * @param item The data to be added
     */
    @Override
    public void add(T item) {
        add(size, item);
    }

    /**
     * Adds a new element at index pos
     * @param pos The index at where the element is to be added
     * @param item The element to be added
     */
    @Override
    public void add(int pos, T item) {
        checkOutBounds(pos, 0, size);
        if (size >= array.length)
            expand();
        shiftRight(pos);
        size++;
        array[pos] = item;
    }

    /**
     * Retrieves the element at index pos
     * @param pos Index of the element to be retrieved
     * @return The data at index pos
     */
    @Override
    public T get(int pos) {
        checkOutBounds(pos, 0, size - 1);
        return array[pos];
    }

    /**
     * Removes and returns the element at index pos by
     * shifting all elements with indices greater than pos to the left.
     * @param pos Index of the element to be removed
     * @return The element that was once at index pos
     */
    @Override
    public T remove(int pos) {
        checkOutBounds(pos, 0, size - 1);
        T data = array[pos];
        shiftLeft(pos);
        size--;
        return data;
    }

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


}
