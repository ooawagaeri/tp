package seedu.mycrm.ui;

import static java.util.Objects.requireNonNull;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.job.JobListPanel;
import seedu.mycrm.ui.template.MailListPanel;

/**
 * After creating an instance of SideDisplay, should instantly call method fillInnerParts(Logic).
 */
public class MainDisplay extends UiPart<Region> {
    private MailListPanel mailListPanel;
    private JobListPanel jobListPanel;

    @FXML
    private VBox mainDisplayBox;

    @FXML
    private Label header;

    @FXML
    private StackPane mailListPanelPlaceholder;

    @FXML
    private StackPane jobListPanelPlaceholder;

    private static final String FXML = "MainDisplay.fxml";
    private static final String HEADER_JOBS = "Jobs";
    private static final String HEADER_MAIL = "Mail";

    public MainDisplay() {
        super(FXML);
    }

    public void init(Logic logic, HostServices hostServices) {
        requireNonNull(logic);
        requireNonNull(hostServices);

        mailListPanel = new MailListPanel(logic.getFilteredMailList(), hostServices);
        mailListPanelPlaceholder.managedProperty().bind(mailListPanelPlaceholder.visibleProperty());
        mailListPanelPlaceholder.getChildren().add(mailListPanel.getRoot());

        jobListPanel = new JobListPanel(logic.getFilteredJobList());
        jobListPanelPlaceholder.managedProperty().bind(jobListPanelPlaceholder.visibleProperty());
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        // By default, show job list, hide mail list
        showJobList();
    }

    public void showJobList() {
        mailListPanelPlaceholder.setVisible(false);
        jobListPanelPlaceholder.setVisible(true);
        changeHeaderText(HEADER_JOBS);
    }

    public void showMailList() {
        jobListPanelPlaceholder.setVisible(false);
        mailListPanelPlaceholder.setVisible(true);
        changeHeaderText(HEADER_MAIL);
    }

    private void changeHeaderText(String text) {
        requireNonNull(text);

        header.setText(text);
    }
}
