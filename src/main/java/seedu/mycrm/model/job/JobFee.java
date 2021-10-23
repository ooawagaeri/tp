package seedu.mycrm.model.job;

/**
 * Represents the fee that will be charged to the client for the repair.
 */
public class JobFee {
    public static final String MESSAGE_CONSTRAINTS =
            "Fee should be amount in dollars, followed by "
            + "amount in cents, separated by a dot ($ sign is optional). "
            + "Lowest denomination supported is 1 cent "
            + "E.g $5.30 ";

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
            Float.parseFloat(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the given string for the job fee into amount in cents.
     */
    public static int parse(String fee) {
        if (fee.startsWith("$")) {
            fee = fee.substring(1);
        }
        int dollars = 0;
        int cents = 0;

        if (fee.contains(".")) {
            String[] amounts = fee.split("\\.");
            dollars = Integer.parseInt(amounts[0]);
            cents = Integer.parseInt(amounts[1]);

        } else {
            dollars = Integer.parseInt(fee);
        }

        int amount = dollars * 100 + cents;
        return amount;
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
        return String.format("$%d.%d", dollars, centsRemaining);
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
