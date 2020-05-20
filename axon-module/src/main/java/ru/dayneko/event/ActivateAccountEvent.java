package ru.dayneko.event;


import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;

/**
 * Event for activating newly created account
 */
public class ActivateAccountEvent extends Event<String> {

    @NotNull
    private final Status status;

    public ActivateAccountEvent(String id, Status status) {
        super(id);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
