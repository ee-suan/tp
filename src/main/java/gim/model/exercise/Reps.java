package gim.model.exercise;

import static gim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
<<<<<<< HEAD:src/main/java/gim/model/exercise/Reps.java
 * Represents a Exercise's Reps in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidReps(String)}
=======
 * Represents a Exercise's Reps in the exercise tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidRep(String)}
>>>>>>> c0f7df5d3b3500cd7399b0b4c63352ac4d96c054:src/main/java/gim/model/exercise/Rep.java
 */
public class Reps {

    public static final String MESSAGE_CONSTRAINTS = "Reps can only take non negative integer values";

    /*
     * The first character of the Reps must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]\\d*$";

    public final String value;

    /**
     * Constructs an {@code Reps}.
     *
     * @param reps A valid Reps.
     */
    public Reps(String reps) {
        requireNonNull(reps);
        checkArgument(isValidReps(reps), MESSAGE_CONSTRAINTS);
        value = reps;
    }

    /**
     * Returns true if a given string is a valid Reps.
     */
    public static boolean isValidReps(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reps // instanceof handles nulls
                && value.equals(((Reps) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
