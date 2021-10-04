package seedu.address.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.products.Product;
import seedu.address.testutil.ProductBuilder;

public class AddProductCommandTest {

    private Model model;

    @Test
    public void execute() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Product p = new ProductBuilder().build();

        assertCommandSuccess(new AddProductCommand(p), model, p.toString(), model);
    }

    @Test
    public void equals() {
        final AddProductCommand standardCommand = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProduct.ONE).build());

        // same values -> returns true
        AddProductCommand cmdWithSameValues = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProduct.ONE).build());
        assertTrue(standardCommand.equals(cmdWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different product -> returns false
        AddProductCommand cmdWithDiffValues = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProduct.TWO).build());
        assertFalse(standardCommand.equals(cmdWithDiffValues));
    }
}
