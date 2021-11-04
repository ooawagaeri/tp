package seedu.mycrm.logic.parser.products;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.mycrm.logic.commands.products.FindProductCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.product.ProductNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindProductCommand object.
 */
public class FindProductCommandParser implements Parser<FindProductCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProductCommand
     * and returns a FindProductCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindProductCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProductCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindProductCommand(new ProductNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
