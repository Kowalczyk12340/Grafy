package lab10;

public class MyLinkedListIterator implements Iterator
{
    private final MyLinkedList list;
    private MyLinkedList.Node current;

    public MyLinkedListIterator(MyLinkedList list)
    {
        this.list = list;
        this.current = null;
    }

    @Override
    public void previous()
    {
        MyLinkedList.Node cur = current;
        MyLinkedList.Node temp = null;

        if (cur == this.list.getFirst() || cur == null) this.current = null;
        else if (cur != this.list.getFirst()) {
            int i = 0;
            this.current = this.list.getFirst();
            while (i < this.list.size())
            {
                if (cur == this.current)
                {
                    this.current = temp;
                    break;
                } else {
                    temp = this.current;
                    this.current = this.current.next;
                }

                i++;
            }
        }
    }

    @Override
    public void next()
    {
        this.current = this.current.next;
    }

    @Override
    public void first()
    {
        this.current = this.list.getFirst();
    }

    @Override
    public void last()
    {
        this.current = this.list.getLast();
    }

    @Override
    public boolean isDone()
    {
        return this.current == null;
    }

    @Override
    public Object current()
    {
        return this.current.object;
    }
}
