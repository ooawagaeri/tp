package seedu.mycrm.logic.parser.products;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.products.FindProductCommand;
import seedu.mycrm.model.product.ProductNameContainsKeywordsPredicate;

public class FindProductCommandParserTest {

    private final FindProductCommandParser parser = new FindProductCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindProductCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindProductCommand expectedFindCommand =
                new FindProductCommand(new ProductNameContainsKeywordsPredicate(Arrays.asList("AsUS", "samsung")));
        assertParseSuccess(parser, "AsUS samsung", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n AsUS \n \t samsung  \t", expectedFindCommand);
    }

}
