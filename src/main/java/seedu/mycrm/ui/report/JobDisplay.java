package seedu.mycrm.ui.report;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.UiPart;

import javafx.scene.layout.Region;
import seedu.mycrm.ui.job.JobListPanel;

public class JobDisplay extends UiPart<Region> {

    private static final String FXML = "JobDisplay.fxml";

    private String COMPLETED_JOB_NUMBER_MESSAGE = "The total number of jobs completed in this month: ";
    private String INPROGRESS_JOB_NUMBER_MESSAGE = "The total number of in-progress jobs: ";

    private JobListPanel completedJobListPanel;
    private JobListPanel inProgressJobListPanel;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab completedJobTab;

    @FXML
    private Tab inProgressJobTab;

    @FXML
    private StackPane completedJobListPanelPlaceholder;

    @FXML
    private StackPane inProgressJobListPanelPlaceholder;

    @FXML
    private NumberCard numberOfCompletedJobDisplay;

    @FXML
    private NumberCard numberOfInProgressJobDisplay;

    public JobDisplay() {
        super(FXML);
    }

    public void init(Logic logic) {
        int numberOfCompletedJob = logic.getFilteredMonthlyCompletedJobList().size();
        int numberOfInProgressJob = logic.getFilteredJobList().size();

        completedJobListPanel = new JobListPanel(logic.getFilteredMonthlyCompletedJobList());
        numberOfCompletedJobDisplay = new NumberCard(COMPLETED_JOB_NUMBER_MESSAGE, numberOfCompletedJob);
        VBox vBox1 = new VBox(numberOfCompletedJobDisplay.getRoot(), completedJobListPanel.getRoot());
        vBox1.setVgrow(completedJobListPanel.getRoot(), Priority.ALWAYS);
        completedJobListPanelPlaceholder.managedProperty().bind(completedJobListPanelPlaceholder.visibleProperty());
        completedJobListPanelPlaceholder.getChildren().add(vBox1);

        inProgressJobListPanel = new JobListPanel(logic.getFilteredJobList());
        numberOfInProgressJobDisplay = new NumberCard(INPROGRESS_JOB_NUMBER_MESSAGE, numberOfInProgressJob);
        VBox vBox2 = new VBox(numberOfInProgressJobDisplay.getRoot(), inProgressJobListPanel.getRoot());
        vBox2.setVgrow(inProgressJobListPanel.getRoot(), Priority.ALWAYS);
        inProgressJobListPanelPlaceholder.managedProperty().bind(inProgressJobListPanelPlaceholder.visibleProperty());
        inProgressJobListPanelPlaceholder.getChildren().add(vBox2);
    }
}
