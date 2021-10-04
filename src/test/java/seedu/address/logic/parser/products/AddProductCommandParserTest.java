package seedu.address.logic.parser.products;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.products.AddProductCommand;
import seedu.address.model.products.Product;

public class AddProductCommandParserTest {
    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(new AddProductCommandParser(), PRODUCT_NAME_DESC,
                new AddProductCommand(new Product(VALID_PRODUCT_NAME)));
    }

    @Test
    public void parse_invalidArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE);

        // Lack of name field
        assertParseFailure(new AddProductCommandParser(), "", expectedMessage);

        // Empty string for name field
        assertParseFailure(new AddProductCommandParser(), INVALID_PRODUCT_NAME_DESC, expectedMessage);
    }
}
