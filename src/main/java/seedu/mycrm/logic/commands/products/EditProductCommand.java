package seedu.mycrm.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX;
import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import java.util.Arrays;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.products.Description;
import seedu.mycrm.model.products.Manufacturer;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.model.products.ProductComponent;
import seedu.mycrm.model.products.ProductName;
import seedu.mycrm.model.products.Type;

public class EditProductCommand extends Command {

    public static final String COMMAND_WORD = "editProduct";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the product identified "
            + "by the index number used in the displayed product list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PRODUCT_NAME + "NAME] "
            + "[" + PREFIX_PRODUCT_TYPE + "TYPE] "
            + "[" + PREFIX_PRODUCT_MANUFACTURER + "MANUFACTURER] "
            + "[" + PREFIX_PRODUCT_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PRODUCT_MANUFACTURER + "Gigabyte "
            + PREFIX_PRODUCT_TYPE + "Motherboard";

    public static final String MESSAGE_EDIT_PRODUCT_SUCCESS = "Edited Product: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in MyCRM.";

    private static final CommandType COMMAND_TYPE = CommandType.PRODUCTS;

    private final Index index;
    private final EditProductDescriptor descriptor;

    /**
     * Creates a {@code EditProductCommand}.
     */
    public EditProductCommand(Index index, EditProductDescriptor descriptor) {
        requireAllNonNull(index, descriptor);

        this.index = index;
        this.descriptor = descriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getOneBased() > model.getFilteredProductList().size()) {
            throw new CommandException(MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product toEdit = model.getFilteredProductList().get(index.getZeroBased());
        Product edited = createEditedProduct(toEdit, descriptor);

        if (!edited.isSameProduct(toEdit) && model.hasProduct(edited)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.setProduct(toEdit, edited);
        model.updateFilteredProductList(Model.PREDICATE_SHOW_ALL_PRODUCTS);

        return new CommandResult(String.format(MESSAGE_EDIT_PRODUCT_SUCCESS, edited), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    private static Product createEditedProduct(Product toEdit, EditProductDescriptor descriptor) {
        requireNonNull(toEdit);

        ProductName name = descriptor.pName.isEmpty() ? toEdit.getName() : descriptor.pName;
        Type type = descriptor.type.isEmpty() ? toEdit.getType() : descriptor.type;
        Manufacturer manufacturer = descriptor.manufacturer.isEmpty() ? toEdit.getManufacturer()
                : descriptor.manufacturer;
        Description description = descriptor.description.isEmpty() ? toEdit.getDescription() : descriptor.description;

        return new Product(name, type, manufacturer, description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof EditProductCommand) {
            EditProductCommand cmd = (EditProductCommand) o;
            return cmd.index.equals(this.index) && cmd.descriptor.equals(this.descriptor);
        }
        return false;
    }

    public static class EditProductDescriptor {
        private ProductName pName;
        private Type type;
        private Manufacturer manufacturer;
        private Description description;

        /**
         * Initialize all fields to empty fields.
         */
        public EditProductDescriptor() {
            pName = ProductName.getEmptyName();
            type = Type.getEmptyType();
            manufacturer = Manufacturer.getEmptyManufacturer();
            description = Description.getEmptyDescription();
        }

        /**
         * Copy constructor.
         */
        public EditProductDescriptor(EditProductDescriptor toCopy) {
            setProductName(toCopy.pName);
            setType(toCopy.type);
            setManufacturer(toCopy.manufacturer);
            setDescription(toCopy.description);
        }

        public boolean isAnyFieldEdited() {
            return isAnyNonEmpty(pName, type, manufacturer, description);
        }

        private boolean isAnyNonEmpty(ProductComponent<?>... components) {
            return Arrays.stream(components).anyMatch(component -> !component.isEmpty());
        }

        public void setProductName(ProductName name) {
            this.pName = name;
        }

        public ProductName getName() {
            return this.pName;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Type getType() {
            return this.type;
        }

        public void setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }

        public Manufacturer getManufacturer() {
            return this.manufacturer;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Description getDescription() {
            return this.description;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (o == null) {
                return false;
            }
            if (o instanceof EditProductDescriptor) {
                EditProductDescriptor descriptor = (EditProductDescriptor) o;
                return descriptor.pName.equals(this.pName)
                        && descriptor.type.equals(this.type)
                        && descriptor.manufacturer.equals(this.manufacturer)
                        && descriptor.description.equals(this.description);
            }
            return false;
        }
    }
}
