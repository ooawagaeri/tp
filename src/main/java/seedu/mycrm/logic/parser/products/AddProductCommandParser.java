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
import seedu.mycrm.model.products.Description;
import seedu.mycrm.model.products.Manufacturer;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.model.products.ProductName;
import seedu.mycrm.model.products.Type;

public class AddProductCommandParser implements Parser<AddProductCommand> {

    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_TYPE,
                PREFIX_PRODUCT_MANUFACTURER, PREFIX_PRODUCT_DESCRIPTION);

        Optional<String> name = argMap.getValue(PREFIX_PRODUCT_NAME);
        ProductName pName;
        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        } else if (name.get().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        } else {
            pName = ProductName.getName(name.get());
        }

        Optional<String> typeWrapper = argMap.getValue(PREFIX_PRODUCT_TYPE);
        Type type = Type.getType(typeWrapper);

        Optional<String> manufacturerWrapper = argMap.getValue(PREFIX_PRODUCT_MANUFACTURER);
        Manufacturer manufacturer = Manufacturer.getManufacturer(manufacturerWrapper);

        Optional<String> descriptionWrapper = argMap.getValue(PREFIX_PRODUCT_DESCRIPTION);
        Description description = Description.getDescription(descriptionWrapper);

        return new AddProductCommand(new Product(pName, type, manufacturer, description));
    }
}
