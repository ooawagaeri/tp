package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

import java.util.List;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;

/**
 * Undoes hiding a contact identified using it's displayed index from the myCrm.
 */
public class UndoHideContactCommand extends Command {
    public static final String COMMAND_WORD = "undoHideContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo Hiding the details of the contact identified "
            + "by the index number used in the displayed contact list.\n"
            + "Existing contact info will be hidden.\n"
            + "User must call listContact -a in order to see hidden contacts.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNDO_HIDE_CONTACT_SUCCESS = "Undo hiding Contact: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Index targetIndex;

    /**
     * @param targetIndex of the contact in the filtered contact list to edit
     **/
    public UndoHideContactCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToUndoHide = lastShownList.get(targetIndex.getZeroBased());
        String successMessage;

        if (!contactToUndoHide.checkIsHidden()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_UNDO_HIDE_REQUEST);
        }

        successMessage = String.format(MESSAGE_UNDO_HIDE_CONTACT_SUCCESS, contactToUndoHide);
        model.undoHideContact(contactToUndoHide);
        model.updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
        return new CommandResult(successMessage, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoHideContactCommand // instanceof handles nulls
                && targetIndex.equals(((UndoHideContactCommand) other).targetIndex)); // state check
    }
}
