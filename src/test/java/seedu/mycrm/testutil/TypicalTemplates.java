package seedu.mycrm.testutil;

import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.mail.Template;

public class TypicalTemplates {

    public static final Template COMPLETED = new TemplateBuilder().withSubject(VALID_SUBJECT_COMPLETE)
            .withBody(VALID_BODY_COMPLETE).build();

    public static final Template DONE = new TemplateBuilder()
            .withSubject(VALID_SUBJECT_DONE)
            .withBody(VALID_BODY_DONE).build();

    public static final Template THANK_YOU = new TemplateBuilder()
            .withSubject("Thank you for placing an order")
            .withBody("Dear valued customer, thank you for placing an order with us").build();

    private TypicalTemplates() {}

    /**
     * Returns an {@code MyCrm} with all the typical templates.
     */
    public static MyCrm getTypicalMyCrm() {
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
