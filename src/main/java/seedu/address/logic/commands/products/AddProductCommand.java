package seedu.address.logic.commands.products;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.products.Product;

/**
 * Adds a product to MyCRM.
 */
public class AddProductCommand extends Command {

    public static final String COMMAND_WORD = "addProduct";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Kyaaa Skadi:3";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new product to the CRM."
            + "\nParameters: n/NAME [t/TYPE] [m/MANUFACTURER] [d/DESCRIPTION]"
            + "\nNote: Product name cannot be empty."
            + "\nExample: addProduct n/Intel i5-10400F t/CPU m/Intel d/2.90GHz";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s";

    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in MyCRM";

    private final Product toAdd;

    /**
     * Creates an AddProductCommand.
     */
    public AddProductCommand(Product product) {
        requireNonNull(product);

        this.toAdd = product;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.addProduct(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toString()));
    }

    @Override
    public String toString() {
        return toAdd.toString();
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
