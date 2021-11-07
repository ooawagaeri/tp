package seedu.mycrm.logic.parser.products;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import java.util.Optional;

import seedu.mycrm.logic.commands.products.AddProductCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.Type;

/** Parses input arguments and creates a AddProductCommand object. */
public class AddProductCommandParser implements Parser<AddProductCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddProductCommand}
     * and returns a {@code AddProductCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_TYPE,
                PREFIX_PRODUCT_MANUFACTURER, PREFIX_PRODUCT_DESCRIPTION);

        // Checks and create product name.
        Optional<String> nameWrapper = argMap.getValue(PREFIX_PRODUCT_NAME);
        ProductName productName;
        if (nameWrapper.orElse("").length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
        }
        productName = ProductName.getName(nameWrapper);

        // Create optional fields
        Type type = Type.getType(argMap.getValue(PREFIX_PRODUCT_TYPE));
        Manufacturer manufacturer = Manufacturer.getManufacturer(argMap.getValue(PREFIX_PRODUCT_MANUFACTURER));
        Description description = Description.getDescription(argMap.getValue(PREFIX_PRODUCT_DESCRIPTION));

        return new AddProductCommand(new Product(productName, type, manufacturer, description));
    }
}
