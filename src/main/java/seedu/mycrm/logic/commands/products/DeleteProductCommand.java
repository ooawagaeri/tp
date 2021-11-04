package seedu.mycrm.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX;
import static seedu.mycrm.commons.core.Messages.MESSAGE_REMOVE_LINKED_PRODUCT;

import java.util.List;
import java.util.function.Predicate;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.product.Product;

/** Deletes the specified product from the CRM. */
public class DeleteProductCommand extends Command {

    public static final String COMMAND_WORD = "deleteProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the product identified by the index number used in the displayed product list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Product: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.PRODUCTS;

    private final Index targetIndex;

    /**
     * Creates a DeleteProductCommand.
     */
    public DeleteProductCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToDelete = lastShownList.get(targetIndex.getZeroBased());

        // check the full job list to find if the product is linked with any jobs
        Predicate<Job> latestJobPredicate = model.getLatestJobPredicate() == null
                ? model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS
                : model.getLatestJobPredicate();
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        boolean isLinkedToJob = model.getFilteredJobList().stream()
                .anyMatch(j -> j.getProduct() != null && j.getProduct().isSameProduct(productToDelete));

        // restore the user's job predicate
        model.updateFilteredJobList(latestJobPredicate);

        if (isLinkedToJob) {
            throw new CommandException(MESSAGE_REMOVE_LINKED_PRODUCT);
        }

        model.deleteProduct(productToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DeleteProductCommand)) {
            return false;
        }

        return this.targetIndex.equals(((DeleteProductCommand) o).targetIndex);
    }
}
