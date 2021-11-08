package seedu.mycrm.ui.report;

import static java.util.Objects.requireNonNull;

import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Translate;
import seedu.mycrm.logic.Logic;

/**
 * Prints any given area of a node.
 */
public class NodePrinter {

    private Logic logic;
    private Printable printable;

    public NodePrinter(Logic logic) {
        this.logic = logic;
    }

    public boolean print(PrinterJob job) {
        return print(job, getPrintable());
    }

    /**
     * Prints any given area of a node.
     * Print the node in multiple pages if the node is too long.
     */
    private boolean print(PrinterJob job, Node node) {
        requireNonNull(node);
        requireNonNull(job);
        Group root = new Group();
        root.getChildren().add(node);
        root.applyCss();
        root.layout();

        PageLayout pageLayout = job.getJobSettings().getPageLayout();
        double pageHeight = pageLayout.getPrintableHeight();

        //@@author zhujik
        //Reused from https://stackoverflow.com/questions/26785651/javafx-printing-a-node-across-multiple-pages
        // with minor modifications
        Translate gridTransform = new Translate();
        node.getTransforms().add(gridTransform);

        System.out.println(node.getBoundsInParent().getHeight());

        int rows = (int) Math.ceil(node.getBoundsInParent().getHeight() / pageHeight);

        boolean success = true;
        for (int row = 0; row < rows; row++) {

            gridTransform.setY(-row * pageHeight);

            success &= job.printPage(pageLayout, node);

        }
        node.getTransforms().clear();

        return success;

    }

    private Node getPrintable() {
        printable = new Printable();
        printable.init(logic);
        return printable.getRoot();
    }
}
