package seedu.address.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRODUCTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;

/**
 * Lists all products in MyCrm to the user.
 */
public class ListProductCommand extends Command {

    public static final String COMMAND_WORD = "listProduct";

    public static final String MESSAGE_SUCCESS = "Listed all products";

    private static final CommandType COMMAND_TYPE = CommandType.PRODUCTS;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
