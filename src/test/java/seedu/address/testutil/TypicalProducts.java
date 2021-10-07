package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.MyCrm;
import seedu.address.model.products.Product;

public class TypicalProducts {
    public static final Product ASUS_GPU = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build();
    public static final Product INTEL_CPU = new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build();
    public static final Product SAMSUNG_SSD = new ProductBuilder(ProductBuilder.DefaultProductIndex.THREE).build();

    /**
     * Returns an {@code MyCrm} with all the typical products.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm myCrm = new MyCrm();
        for (Product p : getTypicalProducts()) {
            myCrm.addProduct(p);
        }
        return myCrm;
    }

    public static ArrayList<Product> getTypicalProducts() {
        return new ArrayList<>(Arrays.asList(ASUS_GPU, INTEL_CPU, SAMSUNG_SSD));
    }
}
