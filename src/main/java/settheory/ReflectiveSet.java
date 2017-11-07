package settheory;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class ReflectiveSet {

    private Map<SetCharacter<?>, Boolean> characterBooleanMap = new HashMap<>();
    private List<SetCharacter<?>> characters;

    @Getter
    private boolean valid;

     //Add test case to ReflectiveSetTest.java
    //Write a unit test for this method
    //Make sure it throws illegalArugmentExcpetion when chracters.size() isn't an even number
    //Make sure that characters and this.characters contain the same elements in the same order
    //http://joel-costigliola.github.io/assertj/assertj-core.html

    public ReflectiveSet(Collection<SetCharacter<?>> characters) {
        checkArgument(characters.size() % 2 == 0, "Requires even size");
        this.characters = Lists.newArrayList(characters);
    }

    public ReflectiveSet process(){
        //Initialize the characterBooleanMap with unique keys and false for all values
        characters.forEach(c -> characterBooleanMap.put(c, false));

        //Loop through all unique keys in characterBooleanMap
        for(SetCharacter<?> character : characterBooleanMap.keySet()){

            //Loop through all characters 2 at a time so that we get a set
            for(int i = 1; i < characters.size(); i += 2){

                //First unit in the set
                SetCharacter<?> first = characters.get(i - 1);

                //Second unit in the set
                SetCharacter<?> second = characters.get(i);

                //Test if first and second are the same character as character
                if(first.isSameCharacter(character) && second.isSameCharacter(character)){
                    //Update the entry in characterBooleanMap to true
                    //to indicate that we have found character ?? for character ?
                    characterBooleanMap.put(character, true);
                }
            }
        }
        //Now check if all unique characters have a set associated with them
        this.valid = characterBooleanMap.values().stream().allMatch(e -> e.equals(Boolean.TRUE));

        //Return reference to this object for chaining api
        return this;
    }
}
