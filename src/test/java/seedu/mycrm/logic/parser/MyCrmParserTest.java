package seedu.mycrm.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.ClearCommand;
import seedu.mycrm.logic.commands.ExitCommand;
import seedu.mycrm.logic.commands.HelpCommand;
import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.logic.commands.contacts.DeleteContactCommand;
import seedu.mycrm.logic.commands.contacts.EditContactCommand;
import seedu.mycrm.logic.commands.contacts.EditContactCommand.EditContactDescriptor;
import seedu.mycrm.logic.commands.contacts.FindContactCommand;
import seedu.mycrm.logic.commands.contacts.HideContactCommand;
import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.commands.contacts.UndoHideContactCommand;
import seedu.mycrm.logic.commands.jobs.FindJobCommand;
import seedu.mycrm.logic.commands.products.AddProductCommand;
import seedu.mycrm.logic.commands.products.DeleteProductCommand;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.NameContainsKeywordsPredicate;
import seedu.mycrm.model.job.JobContainsKeywordsPredicate;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.ContactBuilder;
import seedu.mycrm.testutil.ContactUtil;
import seedu.mycrm.testutil.EditContactDescriptorBuilder;
import seedu.mycrm.testutil.ProductBuilder;
import seedu.mycrm.testutil.ProductUtil;


public class MyCrmParserTest {

    private final MyCrmParser parser = new MyCrmParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_editContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_hideContact() throws Exception {
        HideContactCommand command = (HideContactCommand) parser.parseCommand(
                HideContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new HideContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_undoHideContact() throws Exception {
        UndoHideContactCommand command = (UndoHideContactCommand) parser.parseCommand(
                UndoHideContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new UndoHideContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_findContact() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindContactCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listContact() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " -a") instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_addProduct() throws Exception {
        Product p = new ProductBuilder().build();
        AddProductCommand command = (AddProductCommand) parser.parseCommand(ProductUtil.getAddProductCommand(p));
        assertEquals(new AddProductCommand(p), command);
    }

    @Test
    public void parseCommand_deleteProduct() throws Exception {
        DeleteProductCommand command = (DeleteProductCommand) parser.parseCommand(
                DeleteProductCommand.COMMAND_WORD + " " + INDEX_FIRST_PRODUCT.getOneBased());
        assertEquals(new DeleteProductCommand(INDEX_FIRST_PRODUCT), command);
    }

    @Test
    public void parseCommand_findJob() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindJobCommand command = (FindJobCommand) parser.parseCommand(
                FindJobCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindJobCommand(new JobContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
