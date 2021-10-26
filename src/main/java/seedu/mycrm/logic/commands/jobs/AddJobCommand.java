package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;

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
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.products.Product;

public class AddJobCommand extends Command {
    public static final String COMMAND_WORD = "addJob";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a repair job to MyCRM.\n"
        + "Parameters: "
        + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION "
        + PREFIX_DELIVERY_DATE + "DELIVERY DATE"
        + PREFIX_FEE + "FEE"
        + " [" + PREFIX_CONTACT_INDEX + "CONTACT INDEX]"
        + "[" + PREFIX_PRODUCT_INDEX + "PRODUCT INDEX]\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_JOB_DESCRIPTION + "Graphics card replacement needed "
        + PREFIX_CONTACT_INDEX + "1 "
        + PREFIX_PRODUCT_INDEX + "1 "
        + PREFIX_DELIVERY_DATE + "15/09/2021 "
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
            && toAdd.equals(((AddJobCommand) other).toAdd));
    }
}
