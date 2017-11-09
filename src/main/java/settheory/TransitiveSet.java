package settheory;

import com.google.common.collect.Lists;
import kotlin.Pair;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class TransitiveSet {

    private Map<Pair<SetCharacter<?>, SetCharacter<?>>, Boolean> characterBooleanMap = new HashMap<>();
    private List<SetCharacter<?>> characters;

    @Getter
    private boolean valid;

    public TransitiveSet(Collection<SetCharacter<?>> characters) {
        checkArgument(characters.size() % 2 == 0, "Requires even size");
        checkArgument(characters.size() >= 6, "Requires at least three sets");
        this.characters = Lists.newArrayList(characters);
    }

    public TransitiveSet process() {

        for(int i = 3; i <characters.size(); i += 4){
            SetCharacter<?> key = characters.get(i-3); //because we can't have negatives
            SetCharacter<?> value = characters.get(i);

            characterBooleanMap.put(new Pair <> (key, value), false);
        }

        for(Pair<SetCharacter<?>, SetCharacter<?>> set : characterBooleanMap.keySet()) {
            for(int i = 5; i < characters.size(); i+= 6){ // index 5 is our last character
                SetCharacter<?> key = characters.get(i-1); //first letter in set i.e (first, second)
                SetCharacter<?> value = characters.get(i);

                if(set.getFirst().isSameCharacter(key) && set.getSecond().isSameCharacter(value)){
                    characterBooleanMap.put(set, true);
                }
            }
        }

        this.valid = characterBooleanMap.values().stream().allMatch(e -> e.equals(Boolean.TRUE));

        return this;
    }
}
