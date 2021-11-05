package seedu.mycrm.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.contact.Address;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final boolean isHidden;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("isHidden") boolean isHidden) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.isHidden = isHidden;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        isHidden = source.checkIsHidden();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> contactTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            contactTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = Name.getName(name);

        if (phone != null && !Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone;

        if (phone != null) {
            modelPhone = new Phone(phone);
        } else {
            modelPhone = new Phone();
        }

        if (email != null && !Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail;

        if (email != null) {
            modelEmail = new Email(email);
        } else {
            modelEmail = new Email();
        }

        if (address != null && !Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress;

        if (address != null) {
            modelAddress = new Address(address);
        } else {
            modelAddress = new Address();
        }

        final Set<Tag> modelTags = new HashSet<>(contactTags);

        final boolean isHidden = this.isHidden;

        return new Contact(modelName, modelPhone, modelEmail, modelAddress, modelTags, isHidden);
    }

}
