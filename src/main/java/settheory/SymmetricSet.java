package settheory;

import com.google.common.collect.Lists;
import kotlin.Pair;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class SymmetricSet {

    private Map<Pair<SetCharacter<?>, SetCharacter<?>>, Boolean> characterBooleanMap = new HashMap<>();
    private List<SetCharacter<?>> characters;

    @Getter
    private boolean valid;

    public SymmetricSet(Collection<SetCharacter<?>> characters) {
        checkArgument(characters.size() % 2 == 0, "Requires even size");
        checkArgument(characters.size() > 4, "Requires at least two sets");
        this.characters = Lists.newArrayList(characters);

    }

    public SymmetricSet process() {
        for(int i = 1; i < characters.size(); i += 2){
            SetCharacter<?> key = characters.get(i - 1);
            SetCharacter<?> value = characters.get(i);

            characterBooleanMap.put(new Pair<>(key, value), false);
        }

        for(Pair<SetCharacter<?>, SetCharacter<?>> set : characterBooleanMap.keySet()){
            for(int i = 1; i < characters.size(); i+= 2){
                SetCharacter<?> value = characters.get(i - 1);
                SetCharacter<?> key = characters.get(i);

                if(set.getFirst().isSameCharacter(key) && set.getSecond().isSameCharacter(value)){
                    characterBooleanMap.put(set, true);
                }
            }
        }
        //Now check if all unique characters have a set associated with them
        this.valid = characterBooleanMap.values().stream().allMatch(e -> e.equals(Boolean.TRUE));

        //Return reference to this object for chaining api
        return this;

    }
}