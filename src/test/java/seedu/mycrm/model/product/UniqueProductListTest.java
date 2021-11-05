package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.product.exceptions.DuplicateProductException;
import seedu.mycrm.model.product.exceptions.ProductNotFoundException;
import seedu.mycrm.testutil.ProductBuilder;

public class UniqueProductListTest {

    private UniqueProductList uniqueProductList;

    @BeforeEach
    public void init() {
        this.uniqueProductList = new UniqueProductList();
    }

    @Test
    public void contains_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.contains(null));
    }

    @Test
    public void contains_productNotInList_returnsFalse() {
        assertFalse(uniqueProductList.contains(ASUS_GPU));
    }

    @Test
    public void contains_productInList_returnsTrue() {
        uniqueProductList.add(ASUS_GPU);
        assertTrue(uniqueProductList.contains(ASUS_GPU));
    }

    @Test
    public void contains_productWithSameNameInList_returnsTrue() {
        Product p = new Product(ProductBuilder.DEFAULT_PRODUCT_ONE_NAME, Type.getEmptyType(),
                Manufacturer.getEmptyManufacturer(), Description.getEmptyDescription());
        uniqueProductList.add(p);
        assertTrue(uniqueProductList.contains(ASUS_GPU));
    }

    @Test
    public void add_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.add(null));
    }

    @Test
    public void add_duplicateProduct_throwsDuplicateProductException() {
        uniqueProductList.add(ASUS_GPU);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.add(ASUS_GPU));
    }

    @Test
    public void setProduct_nullTargetProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProduct(null, ASUS_GPU));
    }

    @Test
    public void setProduct_nullEditedProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProduct(ASUS_GPU, null));
    }

    @Test
    public void setProduct_targetProductNotInList_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> uniqueProductList.setProduct(ASUS_GPU,
                ASUS_GPU));
    }

    @Test
    public void setProduct_editedProductIsSameProduct_success() {
        uniqueProductList.add(ASUS_GPU);
        uniqueProductList.setProduct(ASUS_GPU, ASUS_GPU);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(ASUS_GPU);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasSameName_success() {
        uniqueProductList.add(ASUS_GPU);
        Product editProduct = new ProductBuilder().withType(ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE.toString()).build();
        uniqueProductList.setProduct(ASUS_GPU, editProduct);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(editProduct);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasDifferentName_success() {
        uniqueProductList.add(ASUS_GPU);
        uniqueProductList.setProduct(ASUS_GPU, INTEL_CPU);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(INTEL_CPU);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasNonUniqueName_throwsDuplicateProductException() {
        uniqueProductList.add(ASUS_GPU);
        uniqueProductList.add(INTEL_CPU);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.setProduct(ASUS_GPU,
                INTEL_CPU));
    }

    @Test
    public void remove_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.remove(null));
    }

    @Test
    public void remove_productDoesNotExist_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> uniqueProductList.remove(ASUS_GPU));
    }

    @Test
    public void remove_existingProduct_removesProduct() {
        uniqueProductList.add(ASUS_GPU);
        uniqueProductList.remove(ASUS_GPU);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_nullUniqueProductList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts((UniqueProductList) null));
    }

    @Test
    public void setProducts_uniqueProductList_replacesOwnListWithProvidedUniqueProductList() {
        uniqueProductList.add(ASUS_GPU);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(INTEL_CPU);
        uniqueProductList.setProducts(expectedUniqueProductList);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts((List<Product>) null));
    }

    @Test
    public void setProducts_list_replacesOwnListWithProvidedList() {
        uniqueProductList.add(ASUS_GPU);
        List<Product> productList = Collections.singletonList(INTEL_CPU);
        uniqueProductList.setProducts(productList);

        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(INTEL_CPU);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_listWithDuplicateProducts_throwsDuplicateProductException() {
        List<Product> listWithDuplicateProducts = Arrays.asList(ASUS_GPU, ASUS_GPU);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.setProducts(listWithDuplicateProducts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProductList.asUnmodifiableObservableList().remove(0));
    }
}
