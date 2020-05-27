package lab10;

public class MyLinkedList implements List
{
    protected static class Node
    {
        protected Object object;
        protected Node next;

        private Node(Object object) {
            this.object = object;
            this.next = null;
        }
    }

    private int size;
    private Node first;
    private Node last;

    public MyLinkedList() {
        clear();
    }

    public MyLinkedList(MyLinkedList list) {
        this.size = list.size;
        this.first = list.first;
        this.last = list.last;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(Object object) {
        Node node = new Node(object);

        if (isEmpty()) {
            this.first = this.last = node;
        } else {
            this.last.next = node;
            this.last = node;
        }

        this.size++;
    }

    @Override
    public void insert(Object object, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();

        Node node = new Node(object);
        Node temp = this.first;

        if (index == 0) {
            this.first = node;
            this.first.next = temp;
        } else if (index == (this.size - 1)) {
            this.last.next = this.last = node;
        } else if (index < this.size) {
            Node atIndex = null;
            Node L, L1;

            for (int i = 0; i < this.size; i++) {
                if (i == index) {
                    L = atIndex;
                    L1 = L.next;
                    L.next = node;
                    node.next = L1;
                } else {
                    atIndex = temp.next;
                }
            }
        }

        this.size++;
    }

    @Override
    public void set(Object object, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();

        Node temp = this.first;

        for (int i = 0; i < index; i++) temp = temp.next;

        temp.object = object;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node temp = this.first;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.object;
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node deleted;

        if (index == 0) {
            deleted = this.first;
            this.first = this.first.next;
            if (this.first == null) this.last = null;
        } else {
            Node temp = this.first;

            int i = 1;

            while (i < index) {
                temp = temp.next;
                i++;
            }

            deleted = temp.next;
            temp.next = temp.next.next;

            if (temp.next == null) {
                this.last = temp;
            }
        }

        this.size--;

        return deleted.object;
    }

    @Override
    public boolean contains(Object object) {
        Node temp = this.first;

        for (int i = 0; i < this.size; i++) {
            if (temp.object.equals(object)) {
                return true;
            } else {
                temp = temp.next;
                if (temp == null) {
                    return false;
                }
            }
        }
        return false;
    }

    public Node getFirst() {
        return this.first;
    }

    public Node getLast()
    {
        return this.last;
    }

    public String toString()
    {
        String bufor = "";

        Node tmp = first;

        while (tmp != null)
        {
            bufor += tmp.object;
            tmp = tmp.next;
        }

        return bufor;
    }
    public MyLinkedListIterator getIterator()
    {
        return new MyLinkedListIterator(this);
    }
}