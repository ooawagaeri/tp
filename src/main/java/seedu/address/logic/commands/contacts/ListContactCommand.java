package seedu.address.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listContact";

    public static final String MESSAGE_SUCCESS = "Here are the listed all contacts:";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
