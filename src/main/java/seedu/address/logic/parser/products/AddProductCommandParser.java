package seedu.address.logic.parser.products;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;

import java.util.Optional;

import seedu.address.logic.commands.products.AddProductCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.products.Product;

public class AddProductCommandParser implements Parser<AddProductCommand> {

    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT_NAME);

        Optional<String> name = argMap.getValue(PREFIX_PRODUCT_NAME);
        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        } else if (name.get().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        }

        return new AddProductCommand(new Product(name.get()));
    }
}
