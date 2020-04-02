package expressions;

public interface Expression<T> {
    Class<T> getValueClass();
    T evaluate(int element);
    Expression<T> simplify();
    void replace(Expression<Integer> newElement);
}
