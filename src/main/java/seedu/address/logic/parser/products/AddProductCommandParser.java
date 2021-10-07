package seedu.address.logic.parser.products;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import java.util.Optional;

import seedu.address.logic.commands.products.AddProductCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.Product;
import seedu.address.model.products.Type;

public class AddProductCommandParser implements Parser<AddProductCommand> {

    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_TYPE,
                PREFIX_PRODUCT_MANUFACTURER, PREFIX_PRODUCT_DESCRIPTION);

        Optional<String> name = argMap.getValue(PREFIX_PRODUCT_NAME);
        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        } else if (name.get().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        }

        Optional<String> typeWrapper = argMap.getValue(PREFIX_PRODUCT_TYPE);
        Type type = typeWrapper.orElse("").equals("")
                ? Type.getEmptyType()
                : Type.getType(typeWrapper.get());

        Optional<String> manufacturerWrapper = argMap.getValue(PREFIX_PRODUCT_MANUFACTURER);
        Manufacturer manufacturer = manufacturerWrapper.orElse("").equals("")
                ? Manufacturer.getEmptyManufacturer()
                : Manufacturer.getManufacturer(manufacturerWrapper.get());

        Optional<String> descriptionWrapper = argMap.getValue(PREFIX_PRODUCT_DESCRIPTION);
        Description description = descriptionWrapper.orElse("").equals("")
                ? Description.getEmptyDescription()
                : Description.getDescription(descriptionWrapper.get());

        return new AddProductCommand(new Product(name.get(), type, manufacturer, description));
    }
}
