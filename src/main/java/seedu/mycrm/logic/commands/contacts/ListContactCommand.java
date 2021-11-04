package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.function.Predicate;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;

/**
 * Lists all contacts in the myCrm to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listContact";

    public static final String MESSAGE_SUCCESS_ALL = "Here are the contacts all listed:";
    public static final String MESSAGE_SUCCESS_NOT_HIDDEN = "Here are the active contacts listed:";
    public static final String SHOW_ALL_CONTACTS = "-a";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all unhidden contact by default. "
            + "If a user types in '-a', MyCRM will also list hidden contact. "
            + "Parameters: [-a]\n"
            + "Example: " + COMMAND_WORD + SHOW_ALL_CONTACTS;;

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Predicate<Contact> listPredicate;

    /**
     * Constructor for listContactCommand.
     * If no predicate given, by default listContactCommand will list all commands.
     */
    public ListContactCommand() {
        this.listPredicate = PREDICATE_SHOW_ALL_CONTACTS;
    }

    /**
     * Constructor for listContactCommand.
     * List all contacts in the list with given predicate.
     *
     * @param listPredicate
     */
    public ListContactCommand(Predicate<Contact> listPredicate) {
        this.listPredicate = listPredicate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredContactList(listPredicate);
        String successMessage;
        if (listPredicate.equals(PREDICATE_SHOW_ALL_CONTACTS)) {
            successMessage = MESSAGE_SUCCESS_ALL;
        } else {
            successMessage = MESSAGE_SUCCESS_NOT_HIDDEN;
        }
        return stateManager.handleList(new CommandResult(successMessage, COMMAND_TYPE));
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListContactCommand // instanceof handles nulls
                && listPredicate.equals(((ListContactCommand) other).listPredicate)); // state check
    }
}
