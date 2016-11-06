/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author jldeleage
 */
public class CompositeHashMapWrappedCollection<T extends Mappable>
        extends DefaultMapWrappedCollection<T>
        implements MapWrappedCollection<T> {

    public CompositeHashMapWrappedCollection(Collection<T> delegue) {
        super(delegue);
    }


    public CompositeHashMapWrappedCollection(Collection<T> delegue, MapWrappedCollection<T> parent) {
        super(delegue);
        this.parent = parent;
    }

    @Override
    public Set<Entry<Object, T>> entrySet() {
        return super.entrySet();
    }

    @Override
    public Collection<T> values() {
        Set<Object> keySet = keySet();
        Collection<T>   result = new LinkedList<>();
        for (Object key : keySet) {
            result.add(get(key));
        }
        return result;
    }

    @Override
    public Set<Object> keySet() {
        Set<Object> result = parent.keySet();
        result.addAll(super.keySet());
        return result;
    }

    @Override
    public T get(Object key) {
        T result = super.get(key);
        if (result != null) {
            return result;
        }
        return parent == null ? null : parent.get(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if (super.containsValue(value)) {
            return true;
        }
        return parent == null ? super.containsValue(value) : parent.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        if (super.containsKey(key)) {
            return true;
        }
        return parent == null ? false : parent.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        if (!super.isEmpty()) {
            return false;
        }
        return parent == null?true : parent.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return values().iterator();
    }

    @Override
    public int size() {
        return parent == null?super.size() : parent.size()+super.size();
    }


    private     MapWrappedCollection<T>     parent;

}
