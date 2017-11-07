package settheory;

public interface SetCharacter<T> extends Comparable<SetCharacter<T>>{

    T getValue();

    default boolean isSameCharacter(SetCharacter<?> other){
        return getValue().equals(other.getValue());
    }
}
