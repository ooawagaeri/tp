package seedu.address.logic.commands.products;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a product to MyCRM.
 */
public class AddProductCommand extends Command {

    public static final String COMMAND_WORD = "addProduct";

    public static final String DUMMY_MESSAGE = "Kyaaa Skadi:3";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(DUMMY_MESSAGE);
    }
}
