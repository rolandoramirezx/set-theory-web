package settheory;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractSet <T> {

    private Collection <SetCharacter<T>> characters = new ArrayList<>();

    AbstractSet(String line) { //line coming from input file
        this.characters = characters;
    }

    public Collection<SetCharacter<T>> getCharacters() {
        return ImmutableList.copyOf(characters); //building new collection of same values, can't modify

    }
}
