package ru.dayneko.comand;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * Base command for extending
 *
 * @param <T> type of ID
 */
public class Command<T> {

    @TargetAggregateIdentifier
    @NotNull
    private final T id;

    protected Command(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
