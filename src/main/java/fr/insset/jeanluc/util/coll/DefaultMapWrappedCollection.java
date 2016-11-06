/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author jldeleage
 */
public class DefaultMapWrappedCollection<T extends Mappable> implements MapWrappedCollection<T> {

    public DefaultMapWrappedCollection() {
        this(new LinkedList<>());
    }



    public DefaultMapWrappedCollection(Collection<T> delegue) {
        this.delegate = delegue;
    }

    @Override
    public void add(T t) {
        delegate.add(t);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }


    @Override
    public Collection<T> getUnderlyingCollection() {
        return delegate;
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (T t : delegate) {
            if (key.equals(t.getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (T t : delegate) {
            if (value.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(Object key) {
        for (T t : delegate) {
            if (key.equals(t.getKey())) {
                return t;
            }
        }
        return null;
    }       //

    @Override
    public T put(Object key, T value) {
        delegate.add(value);
        return value;
    }

    @Override
    public T remove(Object key) {
        for (T t : delegate) {
            if (key.equals(t.getKey())) {
                delegate.remove(t);
                return t;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Object, ? extends T> m) {
        for (T t : m.values()) {
            put(t.getKey(), t);
        }
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set<Object> keySet() {
        Set<Object> result = new HashSet<>();
        for (T t : delegate) {
            result.add(t.getKey());
        }
        return result;
    }

    @Override
    public Collection<T> values() {
        Collection<T>   result = new LinkedList<>();
        for (T t : delegate) {
            
        }
        return result;
    }

    @Override
    public Set<Entry<Object, T>> entrySet() {
        Set<Entry<Object, T>> result = new HashSet<>();
        for (T t : delegate) {
            result.add(new Map.Entry<Object, T>() {
                @Override
                public Object getKey() {
                    return t.getKey();
                }
                @Override
                public T getValue() {
                    return t;
                }
                @Override
                public T setValue(T value) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return result;
    }


    private Collection<T>      delegate;

}       // DefaultHashMapWrappedCollection
