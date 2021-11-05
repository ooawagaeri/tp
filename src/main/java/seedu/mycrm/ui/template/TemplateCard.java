package seedu.mycrm.ui.template;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.ui.UiPart;

/**
 * A UI component that displays information of a {@code Template}.
 */
public class TemplateCard extends UiPart<Region> {

    private static final String FXML = "TemplateListCard.fxml";

    public final Template template;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label subject;
    @FXML
    private Label body;

    /**
     * Creates a {@code TemplateCard} with the given {@code Template} and index to display.
     */
    public TemplateCard(Template template, int displayedIndex) {
        super(FXML);
        this.template = template;
        id.setText(displayedIndex + ". ");
        subject.setText(template.getSubject().toString());
        body.setText(template.getMailReadyBody());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TemplateCard)) {
            return false;
        }

        TemplateCard card = (TemplateCard) other;
        return id.getText().equals(card.id.getText())
                && template.equals(card.template);
    }
}
