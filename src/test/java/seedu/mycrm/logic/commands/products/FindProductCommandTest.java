package seedu.mycrm.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;
import static seedu.mycrm.testutil.TypicalProducts.getTypicalMyCrm;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.product.ProductNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProductCommand}.
 */
public class FindProductCommandTest {
    private Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void equals() {
        ProductNameContainsKeywordsPredicate firstPredicate =
                new ProductNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ProductNameContainsKeywordsPredicate secondPredicate =
                new ProductNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProductCommand findFirstCommand = new FindProductCommand(firstPredicate);
        FindProductCommand findSecondCommand = new FindProductCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindProductCommand firstCommandCopy = new FindProductCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_emptyKeywords_noProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 0);

        ProductNameContainsKeywordsPredicate predicate = getPredicate(" ");
        FindProductCommand command = new FindProductCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProductList());
    }

    @Test
    public void execute_multipleKeywords_multipleProductsFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 2);

        ProductNameContainsKeywordsPredicate predicate = getPredicate("Intel asus samsong");
        FindProductCommand command = new FindProductCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ASUS_GPU, INTEL_CPU), model.getFilteredProductList());
    }

    /**
     * Parses {@code userInput} into a {@code ProductNameContainsKeywordsPredicate}.
     */
    private ProductNameContainsKeywordsPredicate getPredicate(String userInput) {
        return new ProductNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
