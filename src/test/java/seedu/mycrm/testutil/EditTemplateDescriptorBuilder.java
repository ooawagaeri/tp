package seedu.mycrm.testutil;

import seedu.mycrm.logic.commands.mails.EditTemplateCommand.EditTemplateDescriptor;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.model.mail.Template;

/**
 * A utility class to help with building EditTemplateDescriptor objects.
 */
public class EditTemplateDescriptorBuilder {

    private EditTemplateDescriptor descriptor;

    public EditTemplateDescriptorBuilder() {
        descriptor = new EditTemplateDescriptor();
    }

    public EditTemplateDescriptorBuilder(EditTemplateDescriptor descriptor) {
        this.descriptor = new EditTemplateDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTemplateDescriptor} with fields containing {@code template}'s details
     */
    public EditTemplateDescriptorBuilder(Template template) {
        descriptor = new EditTemplateDescriptor();
        descriptor.setSubject(template.getSubject());
        descriptor.setBody(template.getBody());
    }

    /**
     * Sets the {@code Subject} of the {@code EditTemplateDescriptor} that we are building.
     */
    public EditTemplateDescriptorBuilder withSubject(String name) {
        descriptor.setSubject(new Subject(name));
        return this;
    }

    /**
     * Sets the {@code Body} of the {@code EditTemplateDescriptor} that we are building.
     */
    public EditTemplateDescriptorBuilder withBody(String phone) {
        descriptor.setBody(new Body(phone));
        return this;
    }

    public EditTemplateDescriptor build() {
        return descriptor;
    }
}
