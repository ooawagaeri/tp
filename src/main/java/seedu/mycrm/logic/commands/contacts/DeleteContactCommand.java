package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

import java.util.List;
import java.util.function.Predicate;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;

/**
 * Deletes a contact identified using it's displayed index from the myCrm.
 */
public class DeleteContactCommand extends Command {

    public static final String COMMAND_WORD = "deleteContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted Contact: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Index targetIndex;

    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToDelete = lastShownList.get(targetIndex.getZeroBased());
        Predicate<Job> latestJobPredicate = model.getLatestJobPredicate() == null ? PREDICATE_SHOW_ALL_INCOMPLETE_JOBS
                : model.getLatestJobPredicate();
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        boolean isLinkedToJob = model.getFilteredJobList().stream()
                .anyMatch(job -> job.getClient() != null && job.getClient().isSameContact(contactToDelete));
        model.updateFilteredJobList(latestJobPredicate);

        if (isLinkedToJob) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DELETE_REQUEST);
        }

        model.deleteContact(contactToDelete);
        model.updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);

        return new CommandResult(String.format(MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteContactCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteContactCommand) other).targetIndex)); // state check
    }
}
