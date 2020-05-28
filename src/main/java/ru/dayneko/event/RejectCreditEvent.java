package ru.dayneko.event;

import ru.dayneko.utils.Reason;

import javax.validation.constraints.NotNull;

/**
 * Event when a user tries to take money with a negative account
 */
public class RejectCreditEvent extends Event<String> {

    @NotNull
    private final Reason reason;

    public RejectCreditEvent(String id, Reason reason) {
        super(id);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
