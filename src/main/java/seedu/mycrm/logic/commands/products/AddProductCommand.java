package seedu.mycrm.logic.commands.products;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.product.Product;

/**
 * Adds a product to MyCRM.
 */
public class AddProductCommand extends Command {

    public static final String COMMAND_WORD = "addProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new product to the CRM."
            + "\nParameters: n/NAME [t/TYPE] [m/MANUFACTURER] [d/DESCRIPTION]"
            + "\nNote: Product name cannot be empty."
            + "\nExample: addProduct n/Intel i5-10400F t/CPU m/Intel d/2.90GHz";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s\n";

    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.PRODUCTS;

    private final Product toAdd;

    /**
     * Creates an AddProductCommand to add the specified {@code product}.
     */
    public AddProductCommand(Product product) {
        requireNonNull(product);

        this.toAdd = product;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        requireNonNull(stateManager);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.addProduct(toAdd);
        CommandResult commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, toString()), COMMAND_TYPE);
        return stateManager.handleProduct(toAdd, commandResult);
    }

    @Override
    public String toString() {
        return toAdd.toString();
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

        if (o == null) {
            return false;
        }

        if (o instanceof AddProductCommand) {
            AddProductCommand cmd = (AddProductCommand) o;
            return cmd.toAdd.equals(this.toAdd);
        }
        return false;
    }
}
