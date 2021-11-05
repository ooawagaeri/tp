package seedu.mycrm.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.product.ProductNameContainsKeywordsPredicate;

/**
 * Finds and lists all products in MyCrm whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindProductCommand extends Command {
    public static final String COMMAND_WORD = "findProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all products whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + "Intel";

    private static final CommandType COMMAND_TYPE = CommandType.PRODUCTS;

    private final ProductNameContainsKeywordsPredicate predicate;

    /** Creates a FindProductCommand. */
    public FindProductCommand(ProductNameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);

        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireAllNonNull(model, stateManager);

        model.updateFilteredProductList(predicate);

        return stateManager.handleList(new CommandResult(String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW,
            model.getFilteredProductList().size()), COMMAND_TYPE));
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindProductCommand // instanceof handles nulls
            && predicate.equals(((FindProductCommand) other).predicate)); // state check
    }
}
