package seedu.mycrm.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.product.Product;

public class TypicalProducts {
    public static final Product ASUS_GPU = new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build();
    public static final Product INTEL_CPU = new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build();
    public static final Product SAMSUNG_SSD = new ProductBuilder(ProductBuilder.DefaultProductIndex.THREE).build();

    private static final Job INCOMPLETED = new JobBuilder().build();

    private static final Job asusLinkedJob = new JobBuilder(INCOMPLETED).withProduct(ASUS_GPU).build();

    /**
     * Returns a {@code MyCrm} object with all the typical products.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm myCrm = new MyCrm();
        for (Product p : getTypicalProducts()) {
            myCrm.addProduct(p);
        }
        return myCrm;
    }

    /**
     * Returns a list of typical products.
     */
    public static ArrayList<Product> getTypicalProducts() {
        return new ArrayList<>(Arrays.asList(ASUS_GPU, INTEL_CPU, SAMSUNG_SSD));
    }

    /**
     * Returns a {@code MyCrm} object with a product linked to an uncompleted job.
     */
    public static MyCrm getLinkedJobMyCrm() {
        MyCrm myCrm = new MyCrm();
        myCrm.addProduct(ASUS_GPU);
        myCrm.addJob(asusLinkedJob);
        return myCrm;
    }
}
