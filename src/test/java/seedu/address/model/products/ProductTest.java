package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class ProductTest {
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
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER);
        assertFalse(product.equals(diffNameProduct));

        Product diffTypeProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE, ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER);
        assertFalse(product.equals(diffTypeProduct));

        Product emptyTypeProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME, Type.getEmptyType(),
                ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER);
        assertFalse(product.equals(emptyTypeProduct));

        Product diffManufacturerProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER);
        assertFalse(product.equals(diffManufacturerProduct));

        Product emptyManufacturerProduct = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME,
                ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE, Manufacturer.getEmptyManufacturer());
        assertFalse(product.equals(emptyManufacturerProduct));
    }
}
