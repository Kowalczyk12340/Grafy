package lab10;

import java.util.Iterator;

public interface List
{
    public int size();
    public void clear();
    public boolean isEmpty();
    public void add(Object object);
    public void insert(Object object, int index) throws IndexOutOfBoundsException;
    public void set(Object object, int index) throws IndexOutOfBoundsException;
    public Object get(int index) throws IndexOutOfBoundsException;
    public Object remove(int index) throws IndexOutOfBoundsException;
    public boolean contains(Object object);
    MyLinkedListIterator getIterator();
}
