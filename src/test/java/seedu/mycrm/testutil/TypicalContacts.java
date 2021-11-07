package seedu.mycrm.testutil;

import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TAG_FIRST_TIER;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TAG_SECOND_TIER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("1st Tier").build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("2nd Tier", "Premium").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("1st Tier").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_SECOND_TIER).build();
    public static final Contact BOB = new ContactBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FIRST_TIER,
                    VALID_TAG_SECOND_TIER).build();

    // Test when a contact is linked to a job, not allow to delete.
    public static final Contact BOB_LINKED_JOB = new ContactBuilder()
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FIRST_TIER,
                    VALID_TAG_SECOND_TIER).build();
    public static final Job COMPLETED =
            new JobBuilder().withClient(BOB_LINKED_JOB).withCompletionDate("13/12/2021")
                    .withCompletionStatus(true).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns an {@code MyCrm} with all the typical contacts.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm mc = new MyCrm();
        for (Contact contact : getTypicalContacts()) {
            contact.setNotHidden();
            mc.addContact(contact);
        }
        return mc;
    }

    /**
     * Returns an {@code MyCrm} with one typical contact.
     */
    public static MyCrm getOneTypicalMyCrm() {
        MyCrm mc = new MyCrm();
        Contact contact = getTypicalContacts().get(0);
        contact.setNotHidden();
        mc.addContact(contact);
        return mc;
    }

    /**
     * Returns an {@code MyCrm} with one typical contact linked to a job.
     */
    public static MyCrm getLinkedJobMyCrm() {
        MyCrm mc = new MyCrm();
        Contact contact = BOB_LINKED_JOB;
        Job job = COMPLETED;
        contact.setNotHidden();
        mc.addContact(contact);
        mc.addJob(job);
        return mc;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
