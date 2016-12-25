/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        return getCollectionWhereToAdd(e).add(e);
    }

    @Override
    public boolean remove(Object o) {
        for (Collection<T> coll : collections) {
            if (coll.remove(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        List     aux = null;
        try {
            aux = FactoryMethods.newList(Class.class);
        } catch (InstantiationException ex) {
            Logger.getLogger(CompositeCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        boolean     result = false;
        for (T obj : c) {
            result |= add(obj);
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object obj : c) {
            result |= remove(obj);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void clear() {
        for (Collection coll : collections) {
            coll.clear();
        }
    }


    //========================================================================//


    protected   Collection<T>   getCollectionWhereToAdd(T e) {
        return collections[collections.length-1];
    }


    private Collection<T>[]  collections;


}
