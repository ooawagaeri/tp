package seedu.mycrm.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.contact.Contact;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "myCrm")
class JsonSerializableMyCrm {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMyCrm} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableMyCrm(@JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ReadOnlyMyCrm} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMyCrm}.
     */
    public JsonSerializableMyCrm(ReadOnlyMyCrm source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this myCrm into the model's {@code MyCrm} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MyCrm toModelType() throws IllegalValueException {
        MyCrm myCrm = new MyCrm();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (myCrm.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            myCrm.addContact(contact);
        }
        return myCrm;
    }

}
