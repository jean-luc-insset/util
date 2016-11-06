package fr.insset.jeanluc.util.coll;



/**
 * It can be useful to wrap a collection into a map. Only collections of
 * Mappable objects can be used this way.
 *
 * @author jldeleage
 */
public interface Mappable {
    
    public Object   getKey();

}
