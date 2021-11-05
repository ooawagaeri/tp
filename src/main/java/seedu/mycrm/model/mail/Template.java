package seedu.mycrm.model.mail;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Template {

    private final Subject subject;
    private final Body body;

    /**
     * Every field must be present.
     */
    public Template(Subject subject, Body body) {
        requireNonNull(subject);
        requireNonNull(body);

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
     * Returns augmented template body.
     * This replaces all the '\n' tags with newline breaks.
     */
    public String getMailReadyBody() {
        return body.toString().replace("\\n", "\n");
    }


    /**
     * Returns true if both email templates have the subject header.
     * This defines a weaker notion of equality between two templates.
     *
     * @param otherTemplate target template to compare.
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
        return other == this
                || (other instanceof Template
                && subject.equals(((Template) other).subject)
                && body.equals(((Template) other).body));
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
