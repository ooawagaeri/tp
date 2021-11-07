package seedu.mycrm.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.logic.commands.ExportReportCommand.MESSAGE_EMPTY_JOB_LIST;
import static seedu.mycrm.logic.commands.ExportReportCommand.SHOWING_EXPORT_MESSAGE;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalJobs.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;

class ExportReportCommandTest {

    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    void execute_exportReportWithEmptyJobList_success() {
        Model model = new ModelManager();
        Command command = new ExportReportCommand();

        assertThrows(CommandException.class, MESSAGE_EMPTY_JOB_LIST, ()
            -> command.execute(model, new StateManager(model)));
    }

    @Test
    void execute_exportNonEmptyReport_success() throws CommandException {
        Model expectedModel = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel.setMyCrm(new MyCrm());

        CommandResult commandResult = new ExportReportCommand().execute(model, new StateManager(model));

        CommandResult expectedCommandResult = new CommandResult(SHOWING_EXPORT_MESSAGE, CommandType.EXPORT);

        assertEquals(expectedCommandResult.getFeedbackToUser(),
                commandResult.getFeedbackToUser());
    }
}
