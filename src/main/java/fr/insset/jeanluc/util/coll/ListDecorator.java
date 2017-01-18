package fr.insset.jeanluc.util.coll;



import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;




/**
 * This decorator adds the methods filter and map to lists.
 *
 * @author jldeleage
 */
public class ListDecorator<T> implements List<T>  {


    public ListDecorator() {
        this(new LinkedList<T>());
    }


    public ListDecorator(List<T> delegate) {
        this.delegate = delegate;
    }


    //========================================================================//


    public ListDecorator<T> filter(Predicate<T> p) {
        ListDecorator<T>    result = new ListDecorator<>();
        for (T t : this) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }


    public <R> ListDecorator<R> map(Function<T,R> function) {
        ListDecorator<R>    result = new ListDecorator<>();
        for (T t : this) {
            result.add(function.apply(t));
        }
        return result;
    }



    //========================================================================//

    public int size() {
        return delegate.size();
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    public Object[] toArray() {
        return delegate.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    public boolean add(T e) {
        return delegate.add(e);
    }

    public boolean remove(Object o) {
        return delegate.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return delegate.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return delegate.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return delegate.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    public void replaceAll(UnaryOperator<T> operator) {
        delegate.replaceAll(operator);
    }

    public void sort(Comparator<? super T> c) {
        delegate.sort(c);
    }

    public void clear() {
        delegate.clear();
    }

    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public T get(int index) {
        return delegate.get(index);
    }

    public T set(int index, T element) {
        return delegate.set(index, element);
    }

    public void add(int index, T element) {
        delegate.add(index, element);
    }

    public T remove(int index) {
        return delegate.remove(index);
    }

    public int indexOf(Object o) {
        return delegate.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return delegate.lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return delegate.listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return delegate.listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return delegate.subList(fromIndex, toIndex);
    }


    //========================================================================//


    public Spliterator<T> spliterator() {
        return delegate.spliterator();
    }

    public boolean removeIf(Predicate<? super T> filter) {
        return delegate.removeIf(filter);
    }

    public Stream<T> stream() {
        return delegate.stream();
    }

    public Stream<T> parallelStream() {
        return delegate.parallelStream();
    }


    //========================================================================//


    public void forEach(Consumer<? super T> action) {
        delegate.forEach(action);
    }
    


    private     List<T>     delegate;

}
