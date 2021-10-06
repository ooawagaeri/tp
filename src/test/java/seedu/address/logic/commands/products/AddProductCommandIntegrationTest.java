package seedu.address.logic.commands.products;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.products.Product;
import seedu.address.testutil.ProductBuilder;

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

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addProduct(validProduct);

        assertCommandSuccess(new AddProductCommand(validProduct), model,
                String.format(AddProductCommand.MESSAGE_SUCCESS, validProduct), expectedModel);
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        Product productInList = model.getAddressBook().getProductList().get(0);
        assertCommandFailure(new AddProductCommand(productInList), model, AddProductCommand.MESSAGE_DUPLICATE_PRODUCT);
    }
}
