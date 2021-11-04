package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_TEMPLATES;

import java.util.List;
import java.util.Optional;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.commons.util.CollectionUtil;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.model.mail.Template;

/**
 * Edits a template identified using it's displayed index from the myCrm.
 */
public class EditTemplateCommand extends Command {

    public static final String COMMAND_WORD = "editTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the template identified "
            + "by the index number used in the displayed template list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_BODY + "BODY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SUBJECT + "Alert "
            + PREFIX_BODY + "Your repair product requires attention!";

    public static final String MESSAGE_EDIT_TEMPLATE_SUCCESS = "Edited Template: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TEMPLATE = "This template already exists in MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    private final Index index;
    private final EditTemplateDescriptor editTemplateDescriptor;

    /**
     * @param index of the template in the filtered template list to edit
     * @param editTemplateDescriptor details to edit the template with
     */
    public EditTemplateCommand(Index index, EditTemplateDescriptor editTemplateDescriptor) {
        requireNonNull(index);
        requireNonNull(editTemplateDescriptor);

        this.index = index;
        this.editTemplateDescriptor = new EditTemplateDescriptor(editTemplateDescriptor);
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Template> lastShownList = model.getFilteredTemplateList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
        }

        Template templateToEdit = lastShownList.get(index.getZeroBased());
        Template editedTemplate = createEditedTemplate(templateToEdit, editTemplateDescriptor);

        if (!templateToEdit.isSameTemplate(editedTemplate) && model.hasTemplate(editedTemplate)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEMPLATE);
        }

        model.setTemplate(templateToEdit, editedTemplate);
        model.updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
        return new CommandResult(String.format(MESSAGE_EDIT_TEMPLATE_SUCCESS, editedTemplate), COMMAND_TYPE);
    }

    /**
     * Creates and returns a {@code Template} with the details of {@code templateToEdit}
     * edited with {@code editTemplateDescriptor}.
     */
    private static Template createEditedTemplate(Template templateToEdit,
                                                 EditTemplateDescriptor editTemplateDescriptor) {
        assert templateToEdit != null;

        Subject updatedSubject = editTemplateDescriptor.getSubject().orElse(templateToEdit.getSubject());
        Body updatedBody = editTemplateDescriptor.getBody().orElse(templateToEdit.getBody());

        return new Template(updatedSubject, updatedBody);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditTemplateCommand)) {
            return false;
        }

        EditTemplateCommand e = (EditTemplateCommand) other;
        return index.equals(e.index)
                && editTemplateDescriptor.equals(e.editTemplateDescriptor);
    }

    /**
     * Stores the details to edit the template with. Each non-empty field value will replace the
     * corresponding field value of the template.
     */
    public static class EditTemplateDescriptor {
        private Subject subject;
        private Body body;

        public EditTemplateDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTemplateDescriptor(EditTemplateDescriptor toCopy) {
            setSubject(toCopy.subject);
            setBody(toCopy.body);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(subject, body);
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public void setBody(Body body) {
            this.body = body;
        }

        public Optional<Body> getBody() {
            return Optional.ofNullable(body);
        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof EditTemplateDescriptor
                    && getSubject().equals(((EditTemplateDescriptor) other).getSubject())
                    && getBody().equals(((EditTemplateDescriptor) other).getBody()));
        }
    }
}
