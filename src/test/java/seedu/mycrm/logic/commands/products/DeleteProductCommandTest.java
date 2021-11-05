package seedu.mycrm.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX;
import static seedu.mycrm.commons.core.Messages.MESSAGE_REMOVE_LINKED_PRODUCT;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.mycrm.testutil.TypicalProducts.getLinkedJobMyCrm;
import static seedu.mycrm.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.product.Product;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteProductCommand}.
 */
public class DeleteProductCommandTest {

    private Model model;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteProductCommand deleteCommand = new DeleteProductCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteProductCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        DeleteProductCommand deleteCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteProductCommand deleteCommand = new DeleteProductCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteProductCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);
        showNoProduct(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Index outOfBoundIndex = INDEX_SECOND_PRODUCT;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyCrm().getProductList().size());

        DeleteProductCommand deleteCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_productLinkedToJob_throwsCommandException() {
        model = new ModelManager(getLinkedJobMyCrm(), new UserPrefs());
        DeleteProductCommand deleteCommand = new DeleteProductCommand(INDEX_FIRST_PRODUCT);

        assertCommandFailure(deleteCommand, model, MESSAGE_REMOVE_LINKED_PRODUCT);
    }

    @Test
    public void equals() {
        DeleteProductCommand deleteFirstCommand = new DeleteProductCommand(INDEX_FIRST_PRODUCT);
        DeleteProductCommand deleteSecondCommand = new DeleteProductCommand(INDEX_SECOND_PRODUCT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProductCommand deleteFirstCommandCopy = new DeleteProductCommand(INDEX_FIRST_PRODUCT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different product -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no product.
     */
    private void showNoProduct(Model model) {
        model.updateFilteredProductList(p -> false);

        assertTrue(model.getFilteredProductList().isEmpty());
    }
}
