package seedu.address.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.address.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.products.Product;

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

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        DeleteProductCommand deleteCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteProductCommand deleteCommand = new DeleteProductCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteProductCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);
        showNoProduct(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Index outOfBoundIndex = INDEX_SECOND_PRODUCT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProductList().size());

        DeleteProductCommand deleteCommand = new DeleteProductCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
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
