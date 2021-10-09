package seedu.address.logic.parser.products;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.products.EditProductCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.ProductName;
import seedu.address.model.products.Type;

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

    private void editName() throws ParseException {
        Optional<String> nameContainer = map.getValue(PREFIX_PRODUCT_NAME);
        if (nameContainer.isEmpty()) {
            // name is not changed
            return;
        }

        String nameString = nameContainer.get();
        if (nameString.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProductCommand.MESSAGE_USAGE));
        } else {
            descriptor.setProductName(ProductName.getName(nameString));
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
