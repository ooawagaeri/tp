package seedu.mycrm.logic.commands.products;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.ProductBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddProductCommand}.
 */
public class AddProductCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_newProduct_success() {
        Product validProduct = new ProductBuilder().withName("10Gb PCI-E NIC Network Card").withType("Network card")
                .withManufacturer("10Gtek").withDescription("Support Windows Server/Linux/VMware").build();

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.addProduct(validProduct);

        assertCommandSuccess(new AddProductCommand(validProduct), model,
                String.format(AddProductCommand.MESSAGE_SUCCESS, validProduct), expectedModel);
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        Product productInList = model.getMyCrm().getProductList().get(0);
        assertCommandFailure(new AddProductCommand(productInList), model, AddProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }
}
