package seedu.mycrm.ui.template;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.ui.UiPart;

/**
 * A UI component that displays information of a {@code Mail}.
 */
public class MailCard extends UiPart<Region> {

    private static final String FXML = "MailListCard.fxml";

    private static HostServices myHostServices;

    public final Mail mail;

    @FXML
    private HBox cardPane;
    @FXML
    private Hyperlink link;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label body;

    /**
     * Creates a {@code MailCard} with the given {@code Mail} to display.
     */
    public MailCard(Mail mail) {
        super(FXML);
        this.mail = mail;

        email.setText(mail.getMailEmail());
        subject.setText(mail.getMailSubject());
        body.setText(mail.getMailBody());

        link.setText("Click here to send email");
        link.setOnAction(e -> myHostServices.showDocument(mail.constructMail()));
    }

    /**
     * Sets mail card host service for URL opening.
     *
     * @param hostServices application host service
     */
    public static void setGetHostController(HostServices hostServices) {
        myHostServices = hostServices;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MailCard)) {
            return false;
        }

        MailCard card = (MailCard) other;
        return mail.equals(card.mail);
    }
}
