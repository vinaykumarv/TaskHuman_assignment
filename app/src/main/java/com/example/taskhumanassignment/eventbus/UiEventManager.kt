package com.example.taskhumanassignment.eventbus

/**
 * A singleton instance of an RxEventBus.
 *
 * Events are posted to a common bus and all active subscribers are notified of events. Threading
 * and back pressure can be handled by subscribers.
 *
 * Use UiEventManager.postEvent(EventObject) to post events to UiEventManager event bus.
 * Example:
 * ```
 *      UiEventManager.postEvent(SomeEventType())
 * ```
 *
 * Use UiEventManager.observeEvents() to listen to all events of any type and receive the events on
 * the  main/UI thread.
 * Example:
 * ```
 *      val disposable = UiEventManager.observeEventsOnMainThread()
 *          .subscribe {
 *              // handle events
 *          }
 * ```
 *
 * Use UiEventManager.observeEventOnMainThread() to listen only for events of a specific type and
 * receive the events on the  main/UI thread.
 * Example:
 * ```
 *      val disposable = UiEventManager.observeEventOnMainThread<SomeEventType>()
 *          .subscribe {
 *              // handle events
 *          }
 * ```
 */
object UiEventManager: RxEventBus() {
    // Singleton implementation of RxEventBus.  All methods inherited from RxEventBus.
}