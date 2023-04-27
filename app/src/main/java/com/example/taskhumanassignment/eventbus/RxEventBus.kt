package com.example.taskhumanassignment.eventbus

import android.util.Log
import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.ofType

/**
 * Event bus backed by a [PublishProcessor].
 *
 * Events are posted to a common bus and all active subscribers are notified of events. Threading
 * and back pressure can be handled by subscribers.
 *
 * Created by fkusnierz on 6/1/21.
 */
abstract class RxEventBus {

    companion object {
        private const val TAG: String = "RxEventBus"

        // The event buffer capacity of the Flowable returned by observeEvents()
        private const val BUFFER_CAPACITY: Long = 128
    }

    private val publishProcessor = PublishProcessor.create<Any>()

    /**
     * Post an event, or any class type, to the bus.
     */
    fun postEvent(event: Any) {
        Log.d(TAG, "postEvent() event=$event")
        publishProcessor.onNext(event)
    }

    /**
     * Register to receive all events posted to the bus.
     */
    fun observeEvents(): Flowable<Any> {
        return publishProcessor.serialize()
            .onBackpressureBuffer(
                BUFFER_CAPACITY,
                { Log.e(TAG, "onOverflow()") },
                BackpressureOverflowStrategy.ERROR)
    }

    /**
     * Register to receive, on the main/UI thread, all events posted to the bus.
     */
    fun observeEventsOnMainThread(): Flowable<Any> {
        return observeEvents()
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Register to receive only events of the given type posted to the bus.
     *
     * @return Observer that will only emit events of type [T], filtering out all other events.
     */
    inline fun <reified T : Any> observeEvent(): Flowable<T> {
        return observeEvents().ofType()
    }

    /**
     * Register to receive, on the main/UI thread, only events of the given type posted to the bus.
     *
     * @return Observer that will only emit,on the main/UI thread, events of type [T], filtering out
     *         all other events.
     */
    inline fun <reified T : Any> observeEventOnMainThread(): Flowable<T> {
        return observeEventsOnMainThread().ofType()
    }
}