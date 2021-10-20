package seedu.mycrm.logic.commands.contacts;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

public class HideContactCommand extends Command {
    public static final String COMMAND_WORD = "hideContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides the details of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Existing contact info will be hidden"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_HIDE_CONTACT_SUCCESS = "Hidden Contact: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Index index;

    /**
    * @param index of the contact in the filtered contact list to edit
    **/
    public HideContactCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToHide = lastShownList.get(index.getZeroBased());
        contactToHide.setHidden();
        model.hideContact(contactToHide);
        model.updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
        return new CommandResult(String.format(MESSAGE_HIDE_CONTACT_SUCCESS, contactToHide), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return null;
    }
}
