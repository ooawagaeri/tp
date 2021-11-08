package seedu.mycrm.model.job;

/**
 * Represents the fee that will be charged to the client for the repair.
 */
public class JobFee {

    public static final String MESSAGE_CONSTRAINTS =
             "Fee should be a positive amount in dollars\n"
             + "Lowest denomination supported is 1 cent, and values with more precision will be truncated.\n"
             + "Amounts equal to or greater than 10000000 are not permitted.\n"
             + "E.g $5.30 ($ sign is optional).\n";

    private static final int MAX_VALUE = 10000000;

    private int cents;

    public JobFee(String fee) {
        this.cents = JobFee.parse(fee);
    }

    public JobFee(int cents) {
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    /**
     * Returns true if the given string for the job fee conforms to the correct format.
     */
    public static boolean isValidJobFee(String test) {
        try {
            if (test.startsWith("$")) {
                test = test.substring(1);
            }
            int value = parse(test);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the given string for the job fee into amount in cents.
     */
    public static int parse(String fee) throws NumberFormatException {
        if (fee.startsWith("$")) {
            fee = fee.substring(1);
        }
        int dollars = 0;
        int cents = 0;

        if (fee.contains(".")) {
            String[] amounts = fee.split("\\.");
            validateNumeric(amounts[0]);
            validateNumeric(amounts[1]);
            dollars = Integer.parseInt(amounts[0]);

            if (amounts[1] != null) {
                if (amounts[1].length() > 2) {
                    // To do a preliminary check if string only contains numbers
                    cents = Integer.parseInt(amounts[1]);

                    // Lowest permissible denomination is 1 cent
                    cents = Integer.parseInt(amounts[1].substring(0, 2));
                } else {
                    cents = Integer.parseInt(amounts[1]);
                }
                if (dollars >= MAX_VALUE || dollars < 0 || cents < 0) {
                    throw new NumberFormatException();
                }
            }

        } else {
            dollars = Integer.parseInt(fee);
            if (dollars >= MAX_VALUE || dollars < 0 || cents < 0) {
                throw new NumberFormatException();
            }
        }

        int amount = dollars * 100 + cents;
        return amount;
    }

    private static void validateNumeric(String input) throws NumberFormatException {
        for (int i = 0; i < input.length(); i++) {
            Integer.parseInt(input.substring(i, i + 1));
        }
    }

    /**
     * Returns JobFee in term of dollar
     */
    public double getDollar() {
        return (double) cents / 100.00;
    }

    /**
     * Adds two job fees together.
     */
    public static JobFee add(JobFee fee1, JobFee fee2) {
        return new JobFee(fee1.getCents() + fee2.getCents());
    }

    @Override
    public String toString() {
        int dollars = cents / 100;
        int centsRemaining = cents % 100;
        return String.format("$%d.%02d", dollars, centsRemaining);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobFee // instanceof handles nulls
            && cents == (((JobFee) other).cents)); // state check
    }

    @Override
    public int hashCode() {
        return cents;
    }
}
