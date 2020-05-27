package lab10;

public interface Queue
{
    public void enqueue(Object object);
    public Object dequeue() throws EmptyQueueException;

    public void clear();

    public int size();
    public boolean isEmpty();
}
