package seedu.address.logic.commands.contacts;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class LinkContactCommand extends Command {

    public static final String COMMAND_WORD = "linkContact";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from Link Contact");
    }
}
