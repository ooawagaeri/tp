package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.testutil.ProductBuilder;

public class ProductTest {

    @Test
    public void isSameProduct() {
        // same object -> returns true
        assertTrue(ASUS_GPU.isSameProduct(ASUS_GPU));

        // null -> returns false
        assertFalse(INTEL_CPU.isSameProduct(null));

        // same name, all other attributes different -> returns true
        Product editedProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE)
                .withType(ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE)
                .withManufacturer(ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER)
                .withDescription(ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION)
                .build();
        assertTrue(new ProductBuilder().build().isSameProduct(editedProduct));

        // different name, all other attributes same -> returns false
        editedProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE)
                .withName(ProductBuilder.DEFAULT_PRODUCT_TWO_NAME).build();
        assertFalse(new ProductBuilder().build().isSameProduct(editedProduct));

        // name differs in case, all other attributes same -> returns false
        ProductName diffCaseName = ProductName.getName(
                ProductBuilder.DEFAULT_PRODUCT_ONE_NAME.toString().toLowerCase());
        editedProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE)
                .withName(diffCaseName).build();
        assertFalse(new ProductBuilder().build().isSameProduct(editedProduct));

        // name has trailing spaces, all other attributes same -> returns false
        ProductName nameWithTrailingSpaces = ProductName.getName(
                ProductBuilder.DEFAULT_PRODUCT_ONE_NAME.toString() + " ");
        editedProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE)
                .withName(nameWithTrailingSpaces).build();
        assertFalse(new ProductBuilder().build().isSameProduct(editedProduct));
    }

    @Test
    public void equals() {
        Product product = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build();

        assertTrue(product.equals(product));

        Product productCopy = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build();
        assertTrue(product.equals(productCopy));

        assertFalse(product.equals(1));

        assertFalse(product.equals(null));

        Product differentProduct = new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build();
        assertFalse(product.equals(differentProduct));

        Product diffNameProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_TWO_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER,
                ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION);
        assertFalse(product.equals(diffNameProduct));

        Product diffTypeProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER,
                ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION);
        assertFalse(product.equals(diffTypeProduct));

        Product emptyTypeProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME, Type.getEmptyType(),
                ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER, ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION);
        assertFalse(product.equals(emptyTypeProduct));

        Product diffManufacturerProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER,
                ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION);
        assertFalse(product.equals(diffManufacturerProduct));

        Product emptyManufacturerProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, Manufacturer.getEmptyManufacturer(),
                ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION);
        assertFalse(product.equals(emptyManufacturerProduct));

        Product diffDescriptionProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER,
                ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION);
        assertFalse(product.equals(diffDescriptionProduct));

        Product emptyDescriptionProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER,
                Description.getEmptyDescription());
        assertFalse(product.equals(emptyDescriptionProduct));
    }
}
