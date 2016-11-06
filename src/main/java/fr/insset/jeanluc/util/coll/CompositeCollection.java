/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public class CompositeCollection<T> implements Collection<T> {

    public CompositeCollection(Collection<T>... collections) {
        this.collections = collections;
    }


    @Override
    public int size() {
        int result = 0;
        for (Collection c : collections) {
            result += c.size();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        for (Collection c : collections) {
            if (! c.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Collection c : collections) {
            if (c.contains(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int         indexNextCollection = 0;
            private Iterator<T> current;
            private T           nextElement;
            @Override
            public boolean hasNext() {
                return nextElement != null;
            }
            @Override
            public T next() {
                T   result = nextElement;
                if (current.hasNext()) {
                    nextElement = current.next();
                }
                return result;
            }
            protected void nextIterator() {
                while (indexNextCollection < collections.length) {
                    current = collections[indexNextCollection].iterator();
                    indexNextCollection++;
                    if (current.hasNext()) {
                        nextElement = current.next();
                        return;
                    }
                }
                current = null;
                nextElement = null;
            }
            {
                nextIterator();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int      index = 0;
        for (T  t : this) {
            result[index++] = t;
        }
        return result;
    }

    @Override
    public <U> U[] toArray(U[] a) {
        int indice = 0;
        for (T t : this) {
            if (indice >= a.length) {
                break;
            }
            a[indice++] = (U)t;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        List     aux = new LinkedList();
        for (Object t : this) {
            aux.add(t);
        }
        for (Object obj : c) {
            if (aux.contains(obj)) {
                aux.remove(obj);
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Collection<T>[]  collections;

}
