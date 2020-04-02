package expressions;

import java.util.Objects;

public final class Element implements Expression<Integer> {
    public Element() { }

    @Override
    public String toString() {
        return "element";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(toString());
    }

    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }

    @Override
    public Integer evaluate(int x) {
        return x;
    }

    @Override
    public Element simplify() {
        return new Element();
    }

    @Override
    public void replace(Expression<Integer> newElement) { }
}
