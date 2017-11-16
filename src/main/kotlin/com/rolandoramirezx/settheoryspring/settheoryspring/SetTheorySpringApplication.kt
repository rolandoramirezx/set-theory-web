package com.rolandoramirezx.settheoryspring.settheoryspring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import settheory.ReflectiveSet
import settheory.SetCharacterFactory
import settheory.SymmetricSet
import settheory.TransitiveSet

@SpringBootApplication
open class SetTheorySpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(SetTheorySpringApplication::class.java, *args)
}

data class MessageEntity(val message: String) //val = read only

//see line 32
data class SetResult(val set : String, val outcome : String)

@RestController
@RequestMapping("/api/settheory")
class SetTheoryController {

    @CrossOrigin(origins = arrayOf("http://localhost:63342", "https://rolandoramirezx.github.io/"))
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun debug() : MessageEntity {
        return MessageEntity("Got it")
    }

    @CrossOrigin(origins = arrayOf("http://localhost:63342", "https://rolandoramirezx.github.io/"))
    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun postSets(@RequestBody sets : Array<String>) : Array<SetResult> {
        val result = mutableListOf<SetResult>()
        //on each itt. of array, Kotlin is assigning it to var it
        sets.map { it -> result.add(processList(it)) } //transformation func
        return result.toTypedArray()
    }

    //Make a function call processList that accepts a String and returns
    //a SetResultObject
    fun processList (set: String ) : SetResult {
        val characters = set.map { it -> SetCharacterFactory.getInstance().of(it.toString()) } //ln 46-47 settheory.java
        val isReflective = ReflectiveSet(characters).process().isValid
        val isSymmetric = SymmetricSet(characters).process().isValid
        val isTransitive = TransitiveSet(characters).process().isValid

        if(isReflective){
            return SetResult(set, "Reflective") //ln 44
        } else if (isSymmetric){
            return SetResult(set, "Symmetric")
        } else if (isTransitive){
            return SetResult(set, "Transitive")
        } else{
            return SetResult (set, "is not a set")
        }

    }

}

