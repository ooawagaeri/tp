package seedu.mycrm.logic.parser.products;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import java.util.Optional;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.products.EditProductCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.Type;

/** Parses input arguments and creates a EditProductCommand object. */
public class EditProductCommandParser implements Parser<EditProductCommand> {

    private EditProductCommand.EditProductDescriptor descriptor;

    private ArgumentMultimap map;

    /**
     * Parses the given {@code String} of arguments in the context of the {@code EditProductCommand}
     * and returns a {@code EditProductCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public EditProductCommand parse(String args) throws ParseException {
        requireNonNull(args);

        this.map = ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_TYPE,
                PREFIX_PRODUCT_MANUFACTURER, PREFIX_PRODUCT_DESCRIPTION);
        Index index;

        try {
            index = ParserUtil.parseIndex(map.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProductCommand.MESSAGE_USAGE), e);
        }

        descriptor = new EditProductCommand.EditProductDescriptor();
        editName();
        editType();
        editManufacturer();
        editDescription();

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProductCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProductCommand(index, descriptor);
    }

    private void editName() {
        Optional<String> nameWrapper = map.getValue(PREFIX_PRODUCT_NAME);
        if (nameWrapper.orElse("").length() > 0) {
            // name is not empty
            descriptor.setProductName(ProductName.getName(nameWrapper));
        }
    }

    private void editType() {
        descriptor.setType(
                Type.getType(map.getValue(PREFIX_PRODUCT_TYPE)));
    }

    private void editManufacturer() {
        descriptor.setManufacturer(
                Manufacturer.getManufacturer(map.getValue(PREFIX_PRODUCT_MANUFACTURER)));
    }

    private void editDescription() {
        descriptor.setDescription(
                Description.getDescription(map.getValue(PREFIX_PRODUCT_DESCRIPTION)));
    }
}
