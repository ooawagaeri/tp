package seedu.address.testutil;

import seedu.address.model.MyCrm;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class MyCrmBuilder {

    private MyCrm addressBook;

    public MyCrmBuilder() {
        addressBook = new MyCrm();
    }

    public MyCrmBuilder(MyCrm addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public MyCrmBuilder withPerson(Contact person) {
        addressBook.addPerson(person);
        return this;
    }

    public MyCrm build() {
        return addressBook;
    }
}
