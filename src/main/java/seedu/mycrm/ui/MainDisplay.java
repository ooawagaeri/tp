package seedu.mycrm.ui;

import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

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
 * The UI component that is responsible for displaying job list and mail list. <br>
 * After creating an instance of MainDisplay, method {@code init(Logic, HostServices)} should be invoked to initialize
 * its inner parts.
 */
public class MainDisplay extends UiPart<Region> {
    private static final String FXML = "MainDisplay.fxml";
    private static final String HEADER_JOBS = "Jobs";
    private static final String HEADER_MAIL = "Mail";

    @FXML
    private VBox mainDisplayBox;

    @FXML
    private Label header;

    @FXML
    private StackPane mailListPanelPlaceholder;

    @FXML
    private StackPane jobListPanelPlaceholder;

    public MainDisplay() {
        super(FXML);
    }

    /**
     * Initializes inner parts of main display.
     */
    public void init(Logic logic, HostServices hostServices) {
        requireAllNonNull(logic, hostServices);

        MailListPanel mailListPanel = new MailListPanel(logic.getFilteredMailList(), hostServices);
        mailListPanelPlaceholder.managedProperty().bind(mailListPanelPlaceholder.visibleProperty());
        mailListPanelPlaceholder.getChildren().add(mailListPanel.getRoot());

        JobListPanel jobListPanel = new JobListPanel(logic.getFilteredJobList());
        jobListPanelPlaceholder.managedProperty().bind(jobListPanelPlaceholder.visibleProperty());
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        // By default, show job list, hide mail list
        showJobList();
    }

    /**
     * Displays job list and updates header label.
     */
    public void showJobList() {
        mailListPanelPlaceholder.setVisible(false);
        jobListPanelPlaceholder.setVisible(true);
        changeHeaderText(HEADER_JOBS);
    }

    /**
     * Displays mail list and updates header label.
     */
    public void showMailList() {
        jobListPanelPlaceholder.setVisible(false);
        mailListPanelPlaceholder.setVisible(true);
        changeHeaderText(HEADER_MAIL);
    }

    private void changeHeaderText(String text) {
        assert text != null;

        header.setText(text);
    }
}
