package seedu.address.logic.commands.contactcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "editContact";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from Edit Contact");
    }
}
