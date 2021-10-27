package seedu.mycrm.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.products.Product;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "myCrm")
class JsonSerializableMyCrm {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";
    public static final String MESSAGE_DUPLICATE_TEMPLATE = "Template list contains duplicate template(s)";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "Product list contains duplicate product(s)";
    public static final String MESSAGE_DUPLICATE_JOBS = "Job list contains duplicate job(s)";
    public static final String MESSAGE_INVALID_JOBS = "JSON Job list contains an illegal job(s)";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();
    private final List<JsonAdaptedTemplate> templates = new ArrayList<>();
    private final List<JsonAdaptedProduct> products = new ArrayList<>();
    private final List<JsonAdaptedJob> jobs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMyCrm} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableMyCrm(@JsonProperty("contacts") List<JsonAdaptedContact> contacts,
                                 @JsonProperty("templates") List<JsonAdaptedTemplate> events,
                                 @JsonProperty("products") List<JsonAdaptedProduct> products,
                                 @JsonProperty("jobs") List<JsonAdaptedJob> jobs) {
        this.contacts.addAll(contacts);
        this.templates.addAll(events);
        this.products.addAll(products);
        this.jobs.addAll(jobs);
    }

    /**
     * Converts a given {@code ReadOnlyMyCrm} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMyCrm}.
     */
    public JsonSerializableMyCrm(ReadOnlyMyCrm source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
        templates.addAll(source.getTemplateList().stream().map(JsonAdaptedTemplate::new).collect(Collectors.toList()));
        products.addAll(source.getProductList().stream().map(JsonAdaptedProduct::new).collect(Collectors.toList()));
        jobs.addAll(source.getJobList().stream().map(JsonAdaptedJob::new).collect(Collectors.toList()));
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

        for (JsonAdaptedTemplate jsonAdaptedTemplate : templates) {
            Template template = jsonAdaptedTemplate.toModelType();
            if (myCrm.hasTemplate(template)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEMPLATE);
            }
            myCrm.addTemplate(template);
        }

        for (JsonAdaptedProduct jsonAdaptedProduct : products) {
            Product product = jsonAdaptedProduct.toModelType();
            if (myCrm.hasProduct(product)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRODUCT);
            }
            myCrm.addProduct(product);
        }

        for (JsonAdaptedJob jsonAdaptedJob : jobs) {
            Job job = jsonAdaptedJob.toModelType();
            String clientName = jsonAdaptedJob.getClient();
            String productName = jsonAdaptedJob.getProduct();

            Optional<Contact> matchClient = myCrm.getContactList()
                .stream()
                .filter(contact -> clientName.equals(contact.getName().toString()))
                .findFirst();

            if (matchClient.isPresent()) {
                job.setClient(matchClient.get());
            } else {
                throw new IllegalValueException(MESSAGE_INVALID_JOBS);
            }

            Optional<Product> matchProduct = myCrm.getProductList()
                .stream()
                .filter(product -> productName.equals(product.getName().toString()))
                .findFirst();

            if (matchProduct.isPresent()) {
                job.setProduct(matchProduct.get());
            } else {
                throw new IllegalValueException(MESSAGE_INVALID_JOBS);
            }

            if (myCrm.hasJob(job)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_JOBS);
            }
            myCrm.addJob(job);
        }

        return myCrm;
    }
}
