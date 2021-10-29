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
import seedu.mycrm.model.products.Description;
import seedu.mycrm.model.products.Manufacturer;
import seedu.mycrm.model.products.ProductName;
import seedu.mycrm.model.products.Type;

public class EditProductCommandParser implements Parser<EditProductCommand> {

    private EditProductCommand.EditProductDescriptor descriptor;

    private ArgumentMultimap map;

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
        Optional<String> nameContainer = map.getValue(PREFIX_PRODUCT_NAME);
        if (nameContainer.orElse("").length() > 0) {
            // nameContainer contains a non-empty string
            descriptor.setProductName(ProductName.getName(nameContainer));
        }
    }

    private void editType() {
        Optional<String> typeContainer = map.getValue(PREFIX_PRODUCT_TYPE);
        if (typeContainer.isPresent()) {
            descriptor.setType(Type.getType(typeContainer));
        }
    }

    private void editManufacturer() {
        Optional<String> manufacturerContainer = map.getValue(PREFIX_PRODUCT_MANUFACTURER);
        if (manufacturerContainer.isPresent()) {
            descriptor.setManufacturer(Manufacturer.getManufacturer(manufacturerContainer));
        }
    }

    private void editDescription() {
        Optional<String> descriptionContainer = map.getValue(PREFIX_PRODUCT_DESCRIPTION);
        if (descriptionContainer.isPresent()) {
            descriptor.setDescription(Description.getDescription(descriptionContainer));
        }
    }
}
