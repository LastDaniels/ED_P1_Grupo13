package proyecto1.grupo13;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ArrayListAuto<E> implements List<E>{
    public E[] arr;
    private int size=1;
    private int n=0;
    
    public ArrayListAuto(){
        arr=(E[])new Object[size];
        n=0;
    }
    
    public void aumentarSize(){
        if (n==size) {
            size++;
            arr=Arrays.copyOf(arr, size);
        }
    }
    
    @Override
    public boolean add(E e){
        if (n>=size) aumentarSize();
        arr[n++]=e;
        return true;
    }
    
    public boolean addAll(ArrayList<E> al){
        for (int i=0; i<al.size(); i++) {
            add(al.get(i));
        }
        return true;
    }
    
    @Override
    public E remove(int index) {
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + n);
        E element = arr[index];
        int numMoved = n - index - 1;
        if (numMoved > 0) System.arraycopy(arr, index + 1, arr, index, numMoved);
        arr[--n] = null;
        return element;
    }

    public boolean removeElement(E element) {
        for (int i = 0; i < n; i++) {
            if (arr[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }
        
    public boolean contiene(E e){
        for(E e1:arr){
            if(e1.equals(e)) return true;
        }
        return false;
    }
    
    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    
    @Override
    public E get(int index) {
        if (index < 0 ||index >= n ) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + n);
        return arr[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < n;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No hay más elementos en la lista.");
                }
                return (E) arr[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public E set(int index, E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastIndexOf'");
    }

    @Override
    public ListIterator<E> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

    
    
}
