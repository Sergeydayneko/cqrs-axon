package ru.dayneko.event;

import javax.validation.constraints.NotNull;

/**
 * Base Event for extending
 *
 * @param <T> type of id
 */
public class Event<T> {

    @NotNull
    private final T id;

    protected Event(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
