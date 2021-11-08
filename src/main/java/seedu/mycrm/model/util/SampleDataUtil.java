package seedu.mycrm.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.contact.Address;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.Type;

/**
 * Contains utility methods for populating {@code MyCrm} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("1st Tier"), false),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("1st Tier", "Premium"), false),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("2nd Tier"), false),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("3rd Tier"), false),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("New"), false),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("1st Tier"), false)
        };
    }

    public static Product[] getSampleProducts() {
        return new Product[] {
            new Product(ProductName.getName("Intel i5-10400F"), Type.getType("CPU"),
                    Manufacturer.getManufacturer("Intel"), Description.getDescription("2.90GHz")),
            new Product(ProductName.getName("Ryzen 5 5600"), Type.getType("CPU"),
                    Manufacturer.getManufacturer("AMD"), Description.getDescription("3.00GHz"))
        };
    }

    public static Job[] getSampleJobs() {
        Contact charlotte = new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            getTagSet("2nd Tier"), false);

        Contact roy = new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("1st Tier"), false);

        Product intel = new Product(ProductName.getName("Intel i5-10400F"), Type.getType("CPU"),
            Manufacturer.getManufacturer("Intel"), Description.getDescription("2.90GHz"));

        Product ryzen = new Product(ProductName.getName("Ryzen 5 5600"), Type.getType("CPU"),
                Manufacturer.getManufacturer("AMD"), Description.getDescription("3.00GHz"));

        return new Job[] {
            new Job(new JobDescription("CPU fried"), roy, ryzen,
                    new JobDate("12/08/2021"), new JobStatus(true), new JobDate("14/02/2021"),
                    new JobDate("29/08/2021"), new JobFee("$10.0")),
            new Job(new JobDescription("Graphics card replacement needed"), charlotte, intel,
                    new JobDate("15/09/2022"), new JobStatus(false), new JobDate("24/10/2021"),
                    null, new JobFee("$30.0"))
        };
    }

    public static Template[] getSampleTemplates() {
        return new Template[] {
            new Template(new Subject("Completed"), new Body("You order has been completed")),
            new Template(new Subject("Done"), new Body("You order has been marked as done!\\nCongratz!")),
        };
    }

    public static ReadOnlyMyCrm getSampleMyCrm() {
        MyCrm sampleAb = new MyCrm();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }

        for (Product sampleProduct : getSampleProducts()) {
            sampleAb.addProduct(sampleProduct);
        }

        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }

        for (Template sampleTemplate : getSampleTemplates()) {
            sampleAb.addTemplate(sampleTemplate);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
