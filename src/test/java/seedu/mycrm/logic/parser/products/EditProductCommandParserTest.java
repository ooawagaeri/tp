package seedu.mycrm.logic.parser.products;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_DESCRIPTION_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_MANUFACTURER_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_NAME_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_TWO_DESCRIPTION_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_TWO_MANUFACTURER_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_TYPE_DESC;
import static seedu.mycrm.logic.commands.products.EditProductCommand.MESSAGE_NOT_EDITED;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.products.EditProductCommand;
import seedu.mycrm.logic.commands.products.EditProductCommand.EditProductDescriptor;
import seedu.mycrm.testutil.EditProductDescriptorBuilder;

public class EditProductCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProductCommand.MESSAGE_USAGE);

    private static final String indexOne = String.valueOf(INDEX_FIRST_PRODUCT.getOneBased());
    private static final String indexTwo = String.valueOf(INDEX_SECOND_PRODUCT.getOneBased());

    private EditProductCommandParser parser = new EditProductCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PRODUCT_NAME_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "some random string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parser_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-5" + PRODUCT_NAME_DESC, MESSAGE_INVALID_FORMAT);

        // 0 index
        assertParseFailure(parser, "0" + PRODUCT_NAME_DESC, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/some random string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parser_allFieldsSpecified_success() {
        String arguments = indexOne + " " + PRODUCT_NAME_DESC + PRODUCT_TYPE_DESC + PRODUCT_MANUFACTURER_DESC
                + PRODUCT_DESCRIPTION_DESC;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withProductName(DEFAULT_PRODUCT_ONE_NAME)
                .withType(DEFAULT_PRODUCT_ONE_TYPE).withManufacturer(DEFAULT_PRODUCT_ONE_MANUFACTURER)
                .withDescription(DEFAULT_PRODUCT_ONE_DESCRIPTION).build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        assertParseSuccess(parser, arguments, cmd);
    }

    @Test
    public void parser_someFieldsSpecified_success() {
        String arguments = indexTwo + " " + PRODUCT_MANUFACTURER_DESC + PRODUCT_DESCRIPTION_DESC;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withManufacturer(DEFAULT_PRODUCT_ONE_MANUFACTURER).withDescription(DEFAULT_PRODUCT_ONE_DESCRIPTION)
                .build();
        EditProductCommand cmd = new EditProductCommand(INDEX_SECOND_PRODUCT, descriptor);

        assertParseSuccess(parser, arguments, cmd);
    }

    @Test
    public void parser_oneFieldSpecified_success() {
        // product name
        String arguments = indexOne + " " + PRODUCT_NAME_DESC;
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withProductName(DEFAULT_PRODUCT_ONE_NAME)
                .build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);
        assertParseSuccess(parser, arguments, cmd);

        // type
        arguments = indexOne + " " + PRODUCT_TYPE_DESC;
        descriptor = new EditProductDescriptorBuilder().withType(DEFAULT_PRODUCT_ONE_TYPE).build();
        cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);
        assertParseSuccess(parser, arguments, cmd);

        // manufacturer
        arguments = indexTwo + " " + PRODUCT_MANUFACTURER_DESC;
        descriptor = new EditProductDescriptorBuilder().withManufacturer(DEFAULT_PRODUCT_ONE_MANUFACTURER).build();
        cmd = new EditProductCommand(INDEX_SECOND_PRODUCT, descriptor);
        assertParseSuccess(parser, arguments, cmd);

        // description
        arguments = indexTwo + " " + PRODUCT_DESCRIPTION_DESC;
        descriptor = new EditProductDescriptorBuilder().withDescription(DEFAULT_PRODUCT_ONE_DESCRIPTION).build();
        cmd = new EditProductCommand(INDEX_SECOND_PRODUCT, descriptor);
        assertParseSuccess(parser, arguments, cmd);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String arguments = indexOne + " " + PRODUCT_NAME_DESC + PRODUCT_TYPE_DESC + PRODUCT_MANUFACTURER_DESC
                + PRODUCT_DESCRIPTION_DESC + PRODUCT_TWO_MANUFACTURER_DESC + PRODUCT_TWO_DESCRIPTION_DESC;
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder()
                .withProductName(DEFAULT_PRODUCT_ONE_NAME).withType(DEFAULT_PRODUCT_ONE_TYPE)
                .withManufacturer(DEFAULT_PRODUCT_TWO_MANUFACTURER).withDescription(DEFAULT_PRODUCT_TWO_DESCRIPTION)
                .build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);
        assertParseSuccess(parser, arguments, cmd);
    }
}
