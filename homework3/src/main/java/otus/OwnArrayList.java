package otus;

import java.util.*;


/**
 * Created by svetlana on 4/19/17.
 */
public class OwnArrayList<T> implements List<T>
{

    private static final int DEFAULT = 10;
    private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
    private int sizeOfData;

    private static final Object[] EMPTY_DATA = {};
    private transient Object[] objectData;

    protected transient int modCount = 0;

    public OwnArrayList(int initialCapacity) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        this.objectData = new Object[initialCapacity];
    }

    public OwnArrayList(Collection<? extends T> c)
    {
        objectData = c.toArray();
        sizeOfData = objectData.length;
        if (objectData.getClass() != Object[].class)
            objectData = Arrays.copyOf(objectData, sizeOfData, Object[].class);
    }

    public OwnArrayList(T[] array) {
        if (array==null)
            throw new NullPointerException();
        objectData = array;
        sizeOfData = objectData.length;
    }

    public int size() {
        return sizeOfData;
    }

    public boolean isEmpty() {
        return sizeOfData == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    T objectData(int index) {
        return (T) objectData[index];
    }

    private void setNecessarySize(int minSize) {
        if (objectData == EMPTY_DATA) {
            minSize = Math.max(DEFAULT, minSize);
        }

        modCount++;
        if (minSize - objectData.length > 0)
        {
            int oldSize = objectData.length;
            int newSize = oldSize + (oldSize >> 1);
            if (newSize - minSize < 0)
                newSize = minSize;
            if (newSize - MAX_SIZE > 0)
            {
                if (minSize < 0)
                    throw new OutOfMemoryError();
                newSize = (minSize > MAX_SIZE) ? Integer.MAX_VALUE : MAX_SIZE;
            }
            objectData = Arrays.copyOf(objectData, newSize);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objectData, sizeOfData);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < sizeOfData)
            return (T[]) Arrays.copyOf(objectData, sizeOfData, a.getClass());
        System.arraycopy(objectData, 0, a, 0, sizeOfData);
        if (a.length > sizeOfData)
            a[sizeOfData] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        setNecessarySize(sizeOfData + 1);
        objectData[sizeOfData++] = t;
        return true;
   }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        setNecessarySize(sizeOfData + numNew);  // Increments modCount
        System.arraycopy(a, 0, objectData, sizeOfData, numNew);
        sizeOfData += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index >= sizeOfData)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeOfData);

        Object[] a = c.toArray();
        int numNew = a.length;
        setNecessarySize(sizeOfData + numNew);  // Increments modCount

        int numMoved = sizeOfData - index;
        if (numMoved > 0)
            System.arraycopy(objectData, index, objectData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, objectData, index, numNew);
        sizeOfData += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        if (index >= sizeOfData)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeOfData);

        T oldValue = objectData(index);
        objectData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index >= sizeOfData)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeOfData);

        setNecessarySize(sizeOfData + 1);
        System.arraycopy(objectData, index, objectData, index + 1,
                sizeOfData - index);
        objectData[index] = element;
        sizeOfData++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.sizeOfData)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeOfData);

        modCount++;
        T oldValue = objectData(index);

        int numMoved = sizeOfData - index - 1;
        if (numMoved > 0)
            System.arraycopy(objectData, index+1, objectData, index,
                    numMoved);
        objectData[--sizeOfData] = null; // clear to let GC do its work

        return oldValue;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < sizeOfData; i++)
                if (objectData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < sizeOfData; i++)
                if (o.equals(objectData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = sizeOfData-1; i >= 0; i--)
                if (objectData[i]==null)
                    return i;
        } else {
            for (int i = sizeOfData-1; i >= 0; i--)
                if (o.equals(objectData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > sizeOfData)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != sizeOfData;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= sizeOfData)
                throw new NoSuchElementException();
            Object[] objectData = OwnArrayList.this.objectData;
            if (i >= objectData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) objectData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                OwnArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] objectData = OwnArrayList.this.objectData;
            if (i >= objectData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) objectData[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                OwnArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T e) {
            checkForComodification();

            try {
                int i = cursor;
                OwnArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
