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
 * Hides a contact identified using it's displayed index from the myCrm.
 */
public class HideContactCommand extends Command {
    public static final String COMMAND_WORD = "hideContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides the details of the contact identified "
            + "by the index number used in the displayed contact list.\n"
            + "Existing contact info will be hidden.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_HIDE_CONTACT_SUCCESS = "Hidden Contact: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Index targetIndex;

    /**
    * @param targetIndex of the contact in the filtered contact list to edit
    **/
    public HideContactCommand(Index targetIndex) {
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

        Contact contactToHide = lastShownList.get(targetIndex.getZeroBased());
        String successMessage;

        if (contactToHide.checkIsHidden()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_HIDE_REQUEST);
        }

        successMessage = String.format(MESSAGE_HIDE_CONTACT_SUCCESS, contactToHide);
        model.hideContact(contactToHide);
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
                || (other instanceof HideContactCommand // instanceof handles nulls
                && targetIndex.equals(((HideContactCommand) other).targetIndex)); // state check
    }
}
