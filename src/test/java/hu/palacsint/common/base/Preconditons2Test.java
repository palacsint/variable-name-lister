package hu.palacsint.common.base;

import static hu.palacsint.common.base.Preconditions2.checkNotBlank;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class Preconditons2Test {

    @Test
    public void testHappyPath() {
        final String inputString = "valid non-blank value";
        final String result = checkNotBlank(inputString, "cannot be blank");

        assertSame(inputString, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        final String inputString = "";

        checkNotBlank(inputString, "cannot be blank");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankString() {
        final String inputString = " \t";

        checkNotBlank(inputString, "cannot be blank");
    }

    @Test
    public void testErrorMessage() {
        final String inputString = " \t";

        final String errorMessage = "cannot be blank";
        try {
            checkNotBlank(inputString, errorMessage);
        } catch (final IllegalArgumentException expected) {
            assertThat(expected.getMessage()).isEqualTo(errorMessage);
        }
    }
}
