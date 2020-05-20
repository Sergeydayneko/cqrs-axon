package ru.dayneko.event;

/**
 * Base Event for extending
 *
 * @param <T> type of id
 */
public class Event<T> {

    private final T id;

    protected Event(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
