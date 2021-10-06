package seedu.address.logic.commands.products;

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

    private final Product toAdd;

    public AddProductCommand(Product product) {
        this.toAdd = product;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(toAdd.toString());
    }

    @Override
    public String toString() {
        return "AddProductCommand: " + toAdd;
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
