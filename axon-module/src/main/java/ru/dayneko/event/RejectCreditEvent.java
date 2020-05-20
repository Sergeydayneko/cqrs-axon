package ru.dayneko.event;

import ru.dayneko.utils.Reason;

/**
 * Event when a user tries to take money with a negative account
 */
public class RejectCreditEvent extends Event<String> {
    private Reason reason;

    public RejectCreditEvent(String id, Reason reason) {
        super(id);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
