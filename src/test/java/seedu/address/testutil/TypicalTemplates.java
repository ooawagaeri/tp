package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MyCrm;
import seedu.address.model.mail.Template;

public class TypicalTemplates {

    public static final Template COMPLETED = new TemplateBuilder().withSubject(VALID_SUBJECT_COMPLETE)
            .withBody("Dear valued customer, your order has been completed and ready for collection").build();

    public static final Template DONE = new TemplateBuilder()
            .withSubject(VALID_SUBJECT_DONE)
            .withBody(VALID_BODY_DONE).build();

    public static final Template THANK_YOU = new TemplateBuilder()
            .withSubject("Thank you for placing an order")
            .withBody("Dear valued customer, thank you for placing an order with us").build();

    private TypicalTemplates() {}

    /**
     * Returns an {@code AddressBook} with all the typical templates.
     */
    public static MyCrm getTypicalAddressBook() {
        MyCrm ab = new MyCrm();
        for (Template template : getTypicalTemplates()) {
            ab.addTemplate(template);
        }
        return ab;
    }

    /**
     * Returns list of typical templates.
     */
    public static List<Template> getTypicalTemplates() {
        return new ArrayList<>(Arrays.asList(THANK_YOU, COMPLETED, DONE));
    }
}
