package seedu.mycrm.ui.report;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.ui.UiPart;


public class Printable extends UiPart<VBox> {

    private static final String FXML = "Printable.fxml";

    private GraphDisplay graphDisplay;

    @FXML
    private Label completedJobListTitle;

    @FXML
    private Label inProgressJobListTitle;

    @FXML
    private Label topThreeProductListTitle;

    @FXML
    private TextArea completedJobList;

    @FXML
    private TextArea inProgressJobList;

    @FXML
    private TextArea topThreeProductList;

    @FXML
    private StackPane graphDisplayPlaceholder;


    public Printable() {
        super(FXML);
    }

    /**
     * Initialize inner parts.
     */
    public void init(Logic logic) {
        graphDisplay = new GraphDisplay();
        graphDisplay.init(logic);
        if (graphDisplayPlaceholder.getChildren().size() > 0) {
            graphDisplayPlaceholder.getChildren().remove(0);
        }
        graphDisplayPlaceholder.getChildren().add(graphDisplay.getRoot());

        setTextArea(logic);
    }

    private void setTextArea(Logic logic) {
        int id;

        id = 1;
        completedJobList.setText("");
        for (Job j: logic.getFilteredMonthlyCompletedJobList()) {
            completedJobList.setText(completedJobList.getText() + id + ". "
                    + j.toString() + "\n" + getJobInfo(j) + "\n\n");
            id++;
        }
        enableResize(completedJobList);
        completedJobListTitle.setText("Completed Jobs:");

        id = 1;
        inProgressJobList.setText("");
        for (Job j: logic.getFilteredJobList()) {
            inProgressJobList.setText(inProgressJobList.getText() + id + ". "
                    + j.toString() + "\n" + getJobInfo(j) + "\n\n");
            id++;
        }
        enableResize(inProgressJobList);
        inProgressJobListTitle.setText("In-Progress Jobs:");

        id = 1;
        topThreeProductList.setText("");
        for (Product p: logic.getFilteredTopThreeProductList()) {
            topThreeProductList.setText(topThreeProductList.getText() + id + ". "
                    + p.toString() + "\n" + getProductInfo(p) + "\n\n");
            id++;
        }
        enableResize(topThreeProductList);
        topThreeProductListTitle.setText("Top Three Products:");
    }

    private String getJobInfo(Job j) {
        String repairFee = "Repair Fee:" + j.getFee();
        String jobReceivedDate = "Repair Job Received On: " + j.getReceivedDate().toString();
        String expectedCompletionDate = "Expected Completion" + j.getExpectedCompletionDate().toString();

        return repairFee + "\n"
                + jobReceivedDate + "\n"
                + expectedCompletionDate;

    }

    private String getProductInfo(Product product) {
        String type = "Type: " + product.getType().toString();
        String manufacturer = "Manufacturer: " + product.getManufacturer().toString();
        String description = "Description: " + product.getDescription().toString();

        return type + "\n"
                + manufacturer + "\n"
                + description;

    }

    //@@author Ruturaj Patil
    //Reused from https://stackoverflow.com/questions/15593287/binding-textarea-height-to-its-content
    // with minor modifications
    private void enableResize(TextArea textArea) {
        SimpleIntegerProperty count = new SimpleIntegerProperty(20);
        int rowHeight = 10;

        textArea.prefHeightProperty().bindBidirectional(count);
        textArea.minHeightProperty().bindBidirectional(count);
        textArea.scrollTopProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                if (newVal.intValue() > rowHeight) {
                    count.setValue(count.get() + newVal.intValue());
                }
            }
        });
    }
}
