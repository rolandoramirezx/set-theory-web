package com.rolandoramirezx.settheoryspring.settheoryspring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SetTheorySpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(SetTheorySpringApplication::class.java, *args)
}

data class MessageEntity(val message: String) //val = read only

@RestController
@RequestMapping("/api/settheory")
class SetTheoryController {

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun debug() : MessageEntity {
        return MessageEntity("Got it")
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun postSets(@RequestBody sets : Array<String>) : Array<String> {
        return sets
    }
}

