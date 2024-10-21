package structures;

public interface IStack {

    Object pop();
    Object peek();
    Object  push(Object o);
    boolean isEmpty();

    void clear();

}
