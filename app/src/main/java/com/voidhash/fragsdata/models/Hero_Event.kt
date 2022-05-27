package com.voidhash.fragsdata.models

import java.util.concurrent.ConcurrentHashMap


interface Event

data class Hero_Event (
    var name: String,
    var race: String,
    var fragment: String,
    var items: List<Item_Event>
): Event

data class Item_Event(
    var name: String
)

class EventManager() {
    val subscriptions = ConcurrentHashMap<Any, MutableList<Any>>()

    inline fun <reified T : Event> publish(message: T) {
        val subscribers = subscriptions[T::class.java]
        subscribers?.apply {
            this.forEach {
                val function = it as (T) -> Unit
                function.invoke(message)
            }
        }
    }

    inline fun <reified T : Event> subscribe(noinline action: (arg : T) -> Unit) {
        val subscribers = subscriptions.getOrPut(T::class.java, { mutableListOf()})
        subscribers?.add(action)
    }

    inline fun <reified T : Event> unsubscribe(noinline action: (arg : T) -> Unit) {
        val subscribers = subscriptions[T::class.java]
        subscribers?.apply {
            if (this.isNotEmpty()) {
                subscribers.remove(action)
            }
        }
    }

    companion object {
        private var instance: EventManager? = null

        fun getInstance(): EventManager {
            if(instance == null) {
                instance = EventManager()
            }
            return  instance!!
        }
    }
}