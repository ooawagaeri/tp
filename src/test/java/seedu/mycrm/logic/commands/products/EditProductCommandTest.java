package seedu.mycrm.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_FOUR_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_FOUR_MANUFACTURER;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_FOUR_NAME;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_FOUR_TYPE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;
import static seedu.mycrm.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.ClearCommand;
import seedu.mycrm.logic.commands.products.EditProductCommand.EditProductDescriptor;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.EditProductDescriptorBuilder;
import seedu.mycrm.testutil.ProductBuilder;

public class EditProductCommandTest {

    private Model model;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Product editedProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.FOUR).build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(editedProduct).build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        String expectedMsg = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), editedProduct);

        assertCommandSuccess(cmd, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index lastProductIndex = Index.fromOneBased(model.getFilteredProductList().size());
        Product temp = model.getFilteredProductList().get(lastProductIndex.getZeroBased());

        Product editedProduct = new ProductBuilder(temp).withName(DEFAULT_PRODUCT_FOUR_NAME)
                .withDescription(DEFAULT_PRODUCT_FOUR_DESCRIPTION).build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(editedProduct).build();
        EditProductCommand cmd = new EditProductCommand(lastProductIndex, descriptor);

        String expectedMsg = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(lastProductIndex.getZeroBased()), editedProduct);

        assertCommandSuccess(cmd, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_noFieldsSpecifiedUnfilteredList_success() {
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, new EditProductDescriptor());
        Product editedProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());

        String expectedMsg = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());

        assertCommandSuccess(cmd, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product temp = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Product editedProduct = new ProductBuilder(temp).withManufacturer(DEFAULT_PRODUCT_FOUR_MANUFACTURER)
                .withType(DEFAULT_PRODUCT_FOUR_TYPE).build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(editedProduct).build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        String expectedMsg = String.format(EditProductCommand.MESSAGE_EDIT_PRODUCT_SUCCESS, editedProduct);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased()), editedProduct);

        assertCommandSuccess(cmd, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_duplicateProductUnfilteredList_failure() {
        Product dupProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build();

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(dupProduct).build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        assertCommandFailure(cmd, model, EditProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_duplicateProductFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product dupProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(dupProduct).build();
        EditProductCommand cmd = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);

        assertCommandFailure(cmd, model, EditProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        EditProductCommand cmd = new EditProductCommand(outOfBoundIndex, new EditProductDescriptor());

        assertCommandFailure(cmd, model, MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Index outOfBoundIndex = Index.fromOneBased(INDEX_FIRST_PRODUCT.getOneBased() + 1);
        assertTrue(outOfBoundIndex.getZeroBased() <= model.getFilteredProductList().size());
        EditProductCommand cmd = new EditProductCommand(outOfBoundIndex, new EditProductDescriptor());

        assertCommandFailure(cmd, model, MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditProductCommand standardCommand = new EditProductCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptorBuilder(ASUS_GPU).build());

        // same values -> returns true
        EditProductCommand commandWithSameValues = new EditProductCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptorBuilder(ASUS_GPU).build());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProductCommand(INDEX_SECOND_PRODUCT,
                new EditProductDescriptorBuilder(ASUS_GPU).build())));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProductCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptorBuilder(INTEL_CPU).build())));
    }
}
