package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;

public class AddJobCommand extends Command {
    public static final String COMMAND_WORD = "addJob";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a repair job to MyCRM. "
        + "Parameters: "
        + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION "
        + PREFIX_CONTACT_INDEX + "CONTACT INDEX "
        + PREFIX_DELIVERY_DATE + "DELIVERY DATE\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_JOB_DESCRIPTION + "Graphics card replacement needed "
        + PREFIX_CONTACT_INDEX + "3 "
        + PREFIX_DELIVERY_DATE + "15/09/2021 ";

    public static final String MESSAGE_SUCCESS = "New repair job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This reapir job already exists in the MyCRM";

    private final Job toAdd;
    private final Index contactIndex;

    /**
     * Creates an AddJobCommand to add the specified {@code Job}
     */
    public AddJobCommand(Job job, Index contactIndex) {
        requireNonNull(job);
        this.contactIndex = contactIndex;
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (contactIndex != null) {
            if (contactIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }

            Contact client = lastShownList.get(contactIndex.getZeroBased());
            toAdd.setClient(client);
        }

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddJobCommand // instanceof handles nulls
            && toAdd.equals(((AddJobCommand) other).toAdd));
    }
}
