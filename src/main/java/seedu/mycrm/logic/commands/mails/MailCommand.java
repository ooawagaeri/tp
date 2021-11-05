package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TEMPLATE_INDEX;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;

public class MailCommand extends Command {
    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Constructs the mail with identified template to target job by their index number used in the "
            + "displayed template and contact list.\n"
            + "Parameters: "
            + PREFIX_JOB_INDEX + "JOB INDEX "
            + PREFIX_TEMPLATE_INDEX + "TEMPLATE INDEX\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_JOB_INDEX + "1 "
            + PREFIX_TEMPLATE_INDEX + "1 \n";

    public static final String MESSAGE_MAIL_SUCCESS = "New Email Type '%s': \nMailto URL is ready";

    private static final CommandType COMMAND_TYPE = CommandType.MAIL;

    private final Index jobIndex;

    private final Index templateIndex;

    /**
     * Creates a MailCommand to mail the specified {@code Job} and {@code Template}
     */
    public MailCommand(Index jobIndex, Index templateIndex) {
        requireNonNull(jobIndex);
        requireNonNull(templateIndex);
        this.jobIndex = jobIndex;
        this.templateIndex = templateIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        java.util.List<Job> lastJobList = model.getFilteredJobList();
        java.util.List<Template> lastTemplateList = model.getFilteredTemplateList();

        if (jobIndex.getZeroBased() >= lastJobList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        if (templateIndex.getZeroBased() >= lastTemplateList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
        }

        Job jobToMail = lastJobList.get(jobIndex.getZeroBased());

        if (jobToMail.getClientEmail().equals("")) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_NO_EMAIL);
        }

        Template templateToMail = lastTemplateList.get(templateIndex.getZeroBased());
        model.addMail(new Mail(jobToMail, templateToMail));

        return new CommandResult(String.format(MESSAGE_MAIL_SUCCESS, templateToMail.getSubject().toString()),
                COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MailCommand
                && jobIndex.equals(((MailCommand) other).jobIndex))
                && templateIndex.equals(((MailCommand) other).templateIndex);
    }
}
