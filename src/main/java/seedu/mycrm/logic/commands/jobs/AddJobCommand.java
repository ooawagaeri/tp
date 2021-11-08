package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_RECEIVED_DATE;

import java.util.List;
import java.util.Objects;

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
import seedu.mycrm.model.product.Product;

public class AddJobCommand extends Command {
    public static final String COMMAND_WORD = "addJob";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a repair job to MyCRM.\n"
            + "Parameters: "
            + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION "
            + PREFIX_EXPECTED_COMPLETION_DATE + "EXPECTED COMPLETION DATE "
            + PREFIX_FEE + "FEE "
            + " [" + PREFIX_RECEIVED_DATE + "RECEIVED DATE]"
            + " [" + PREFIX_CONTACT_INDEX + "CONTACT INDEX] "
            + " [" + PREFIX_PRODUCT_INDEX + "PRODUCT INDEX] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_JOB_DESCRIPTION + "CPU card replacement needed "
            + PREFIX_CONTACT_INDEX + "1 "
            + PREFIX_PRODUCT_INDEX + "1 "
            + PREFIX_EXPECTED_COMPLETION_DATE + "10/01/2022 "
            + PREFIX_FEE + "$30.00";

    public static final String MESSAGE_SUCCESS = "New repair job added: %1$s\n";

    public static final String MESSAGE_DUPLICATE_JOB = "This repair job already exists in the MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Job toAdd;
    private final Index contactIndex;
    private final Index productIndex;

    /**
     * Creates an AddJobCommand to add the specified {@code Job}
     */
    public AddJobCommand(Job job, Index contactIndex, Index productIndex) {
        requireNonNull(job);
        this.contactIndex = contactIndex;
        this.productIndex = productIndex;
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        if (toAdd.getExpectedCompletionDate().value.isBefore(toAdd.getReceivedDate().value)) {
            throw new CommandException(MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE);
        }

        linkContactToJob(toAdd, model.getFilteredContactList());
        linkProductToJob(toAdd, model.getFilteredProductList());

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        return stateManager.handleAddJob(toAdd);
    }

    private void linkContactToJob(Job job, List<Contact> lastShownContactList) throws CommandException {
        if (contactIndex != null) {
            if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }

            Contact client = lastShownContactList.get(contactIndex.getZeroBased());
            job.setClient(client);
        }
    }

    private void linkProductToJob(Job job, List<Product> lastShownProductList) throws CommandException {
        if (productIndex != null) {
            if (productIndex.getZeroBased() >= lastShownProductList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
            }

            Product product = lastShownProductList.get(productIndex.getZeroBased());
            job.setProduct(product);
        }
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddJobCommand // instanceof handles nulls
            && toAdd.equals(((AddJobCommand) other).toAdd))
            && Objects.equals(contactIndex, ((AddJobCommand) other).contactIndex)
            && Objects.equals(productIndex, ((AddJobCommand) other).productIndex);
    }
}
