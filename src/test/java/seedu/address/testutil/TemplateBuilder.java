package seedu.address.testutil;

import seedu.address.model.mail.Template;

/**
 * A utility class to help with building Template objects.
 */
public class TemplateBuilder {

    private static final String DEFAULT_SUBJECT = "Some subject";
    private static final String DEFAULT_BODY = "Some body";

    private String subject;
    private String body;
    /**
     * Creates a {@code TemplateBuilder} with the default details.
     */
    public TemplateBuilder() {
        subject = DEFAULT_SUBJECT;
        body = DEFAULT_BODY;
    }

    /**
     * Initializes the TemplateBuilder with the data of {@code templateToCopy}.
     */
    public TemplateBuilder(Template templateToCopy) {
        subject = templateToCopy.getSubject();
        body = templateToCopy.getBody();
    }

    /**
     * Sets the {@code Subject} of the {@code Template} that we are building.
     */
    public TemplateBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Sets the {@code Body} of the {@code Template} that we are building.
     */
    public TemplateBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public Template build() {
        return new Template(subject, body);
    }

}
