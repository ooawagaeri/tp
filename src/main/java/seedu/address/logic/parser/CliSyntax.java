package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("c/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_PRODUCT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PRODUCT_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_PRODUCT_MANUFACTURER = new Prefix("m/");
    public static final Prefix PREFIX_PRODUCT_DESCRIPTION = new Prefix("d/");

    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");
    public static final Prefix PREFIX_BODY = new Prefix("b/");
}
