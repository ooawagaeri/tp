package seedu.address.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.products.Product;

public class DeleteProductCommand extends Command {

    public static final String COMMAND_WORD = "deleteProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the product identified by the index number used in the displayed product list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Product: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteProductCommand.
     */
    public DeleteProductCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProduct(productToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete));
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
