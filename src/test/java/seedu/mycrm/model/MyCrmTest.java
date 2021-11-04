package seedu.mycrm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TAG_FIRST_TIER;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalContacts.getTypicalMyCrm;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.exceptions.DuplicateContactException;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.ContactBuilder;
import seedu.mycrm.testutil.ProductBuilder;

public class MyCrmTest {

    private final MyCrm myCrm = new MyCrm();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), myCrm.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myCrm.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMyCrm_replacesData() {
        MyCrm newData = getTypicalMyCrm();
        myCrm.resetData(newData);
        assertEquals(newData, myCrm);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FIRST_TIER)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        MyCrmStub newData = new MyCrmStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> myCrm.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myCrm.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInMyCrm_returnsFalse() {
        assertFalse(myCrm.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInMyCrm_returnsTrue() {
        myCrm.addContact(ALICE);
        assertTrue(myCrm.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInMyCrm_returnsTrue() {
        myCrm.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FIRST_TIER)
                .build();
        assertTrue(myCrm.hasContact(editedAlice));
    }

    //// Product tests

    @Test
    public void hasProduct_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myCrm.hasProduct(null));
    }

    @Test
    public void hasProduct_productNotInMyCrm_returnsFalse() {
        assertFalse(myCrm.hasProduct(INTEL_CPU));
    }

    @Test
    public void hasProduct_productInMyCrm_returnsTrue() {
        myCrm.addProduct(INTEL_CPU);
        assertTrue(myCrm.hasProduct(INTEL_CPU));
    }

    @Test
    public void hasProduct_productWithSameIdentityFieldsInMyCrm_returnsTrue() {
        myCrm.addProduct(INTEL_CPU);
        Product editedProduct = new ProductBuilder(INTEL_CPU).withManufacturer("Asus").build();
        assertTrue(myCrm.hasProduct(editedProduct));
    }


    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myCrm.getContactList().remove(0));
    }

    @Test
    public void getProductList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myCrm.getProductList().remove(0));
    }

    /**
     * A stub ReadOnlyMyCrm whose contacts list can violate interface constraints.
     */
    private static class MyCrmStub implements ReadOnlyMyCrm {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Template> templates = FXCollections.observableArrayList();
        private final ObservableList<Product> products = FXCollections.observableArrayList();
        private final ObservableList<Job> jobs = FXCollections.observableArrayList();
        private final ObservableList<History> histories = FXCollections.observableArrayList();
        private final ObservableList<Mail> mail = FXCollections.observableArrayList();

        MyCrmStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Template> getTemplateList() {
            return templates;
        }

        @Override
        public ObservableList<Product> getProductList() {
            return products;
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs;
        }

        @Override
        public ObservableList<History> getHistoryList() {
            return histories;
        }

        @Override
        public ObservableList<Mail> getMailList() {
            return mail;
        }

        @Override
        public ObservableList<Product> getTopThreeProductList() {
            return products;
        }
    }

}
