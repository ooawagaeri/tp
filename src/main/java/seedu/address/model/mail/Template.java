package seedu.address.model.mail;

import java.util.Objects;

public class Template {

    private final String subject;
    private final String body;

    /**
     * Every field must be present.
     */
    public Template(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
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
                && otherTemplate.getSubject().equals(subject);
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
        return otherTemplate.getSubject().equals(subject)
                && otherTemplate.getBody().equals(body);
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
