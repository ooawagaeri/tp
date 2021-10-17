package seedu.mycrm.testutil;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;

/**
 * A utility class to help with building MyCrm objects.
 * Example usage: <br>
 *     {@code MyCrm cb = new MyCrmBuilder().withContact("John", "Doe").build();}
 */
public class MyCrmBuilder {

    private MyCrm myCrm;

    public MyCrmBuilder() {
        myCrm = new MyCrm();
    }

    public MyCrmBuilder(MyCrm myCrm) {
        this.myCrm = myCrm;
    }

    /**
     * Adds a new {@code Contact} to the {@code Contact} that we are building.
     */
    public MyCrmBuilder withContact(Contact contact) {
        myCrm.addContact(contact);
        return this;
    }

    public MyCrm build() {
        return myCrm;
    }
}
