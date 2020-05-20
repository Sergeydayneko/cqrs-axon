package ru.dayneko.comand;

import ru.dayneko.utils.Status;

/**
 * Command for activating account
 */
public class ActivateAccountCommand extends Command<String> {

    private Status status;

    public ActivateAccountCommand(String id, Status status) {
        super(id);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
