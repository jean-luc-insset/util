package fr.insset.jeanluc.util.hierarchy;



/**
 *
 * @author jldeleage
 */
public interface Hierarchy<T> {

    public Hierarchy<T> getParent();

    public void         setParent(Hierarchy<T> inParent);



    public  T           getLocalProperty(Object inKey);

    public  default T   getProperty(Object inKey) {
        Hierarchy current = this;
        do {
            T result = getLocalProperty(inKey);
            if (result != null) {
                return result;
            }
            current = current.getParent();
            if (current == null) {
                return null;
            }
        } while (true);
    }

}
