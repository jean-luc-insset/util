/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author jldeleage
 */
public interface MapWrappedCollection<T extends Mappable> extends Map<Object, T>, Iterable<T> {

    public final static String  HASH_MAP_WRAPPED_COLLECTION = "hashMapWrappedCollection";

    public  void          add(T t);
    public  int           size();
    public  Collection<T> getUnderlyingCollection();

}
