package seedu.address.testutil;

import static seedu.address.logic.commands.products.AddProductCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;

import seedu.address.model.products.Product;

public class ProductUtil {


    /**
     * Get Add product command string for the given product.
     */
    public static String getAddProductCommand(Product product) {
        return COMMAND_WORD + " " + PREFIX_PRODUCT_NAME + product.getName();
    }
}
