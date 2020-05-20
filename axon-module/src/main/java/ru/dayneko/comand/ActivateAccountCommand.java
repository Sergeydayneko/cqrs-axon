package ru.dayneko.comand;

import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;

/**
 * Command for activating account
 */
public class ActivateAccountCommand extends Command<String> {

    @NotNull
    private Status status;

    public ActivateAccountCommand(String id, Status status) {
        super(id);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
