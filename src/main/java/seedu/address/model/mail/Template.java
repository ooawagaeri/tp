package seedu.address.model.mail;

import java.util.Objects;

public class Template {

    private final Subject subject;
    private final Body body;

    /**
     * Every field must be present.
     */
    public Template(Subject subject, Body body) {
        this.subject = subject;
        this.body = body;
    }

    public Subject getSubject() {
        return subject;
    }

    public Body getBody() {
        return body;
    }

    /**
     * Returns true if both email templates have the subject header.
     * This defines a weaker notion of equality between two templates.
     */
    public boolean isSameTemplate(Template otherTemplate) {
        if (otherTemplate == this) {
            return true;
        }

        return otherTemplate != null
                && otherTemplate.getSubject().equals(getSubject());
    }

    /**
     * Returns true if both email templates have the same fields.
     * This defines a stronger notion of equality between two templates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Template)) {
            return false;
        }

        Template otherTemplate = (Template) other;
        return otherTemplate.getSubject().equals(getSubject())
                && otherTemplate.getBody().equals(getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, body);
    }

    @Override
    public String toString() {
        return String.format("Subject: %s; Body: %s", subject, body);
    }
}
