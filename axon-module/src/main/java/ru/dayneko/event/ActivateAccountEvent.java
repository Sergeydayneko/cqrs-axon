package ru.dayneko.event;


import ru.dayneko.utils.Status;

/**
 * Event for activating newly created account
 */
public class ActivateAccountEvent extends Event<String> {

    private final Status status;

    public ActivateAccountEvent(String id, Status status) {
        super(id);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
