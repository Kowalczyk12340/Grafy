package lab10;

public class ListFifoQueue implements Queue
{
    private MyLinkedList list = new MyLinkedList();

    public ListFifoQueue()
    {
        clear();
    }

    public void enqueue(Object value)
    {
        this.list.add(value);
    }

    public Object dequeue() throws EmptyQueueException
    {
        if(isEmpty())
            throw new EmptyQueueException("Kolejka jest pusta");
        Object tmp = this.list.get(0);

        this.list.remove(0);

        return tmp;
    }

    public void clear()
    {
        this.list.clear();
    }

    public int size()
    {
        return this.list.size();
    }

    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
}
