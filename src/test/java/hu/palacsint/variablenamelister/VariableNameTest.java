package hu.palacsint.variablenamelister;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VariableNameTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBlank() {
        VariableName.create("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        VariableName.create(null);
    }

    @Test
    public void testGetter() throws Exception {
        final String variableNameInput = "var1";

        final VariableName variableName = VariableName.create(variableNameInput);

        assertEquals(variableNameInput, variableName.getVariableName());
    }

    @Test
    public void testToString() throws Exception {
        final String variableNameInput = "var1";

        final VariableName variableName = VariableName.create(variableNameInput);

        assertThat(variableName.toString()).contains(variableNameInput);
    }

    @Test
    public void testHashCodeWithSameVariableName() throws Exception {
        final String variableNameInputOne = new String("var1");
        final String variableNameInputTwo = new String("var1");

        final VariableName variableNameOne = VariableName.create(variableNameInputOne);
        final VariableName variableNameTwo = VariableName.create(variableNameInputTwo);

        assertThat(variableNameOne.hashCode()).isEqualTo(variableNameTwo.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentVariableName() throws Exception {
        final String variableNameInputOne = new String("var");
        final String variableNameInputTwo = new String("another var");

        final VariableName variableNameOne = VariableName.create(variableNameInputOne);
        final VariableName variableNameTwo = VariableName.create(variableNameInputTwo);

        assertThat(variableNameOne.hashCode()).isNotEqualTo(variableNameTwo.hashCode());
    }

    @Test
    public void testEqualsWithDifferentVariableName() throws Exception {
        final String variableNameInputOne = new String("var");
        final String variableNameInputTwo = new String("another var");

        final VariableName variableNameOne = VariableName.create(variableNameInputOne);
        final VariableName variableNameTwo = VariableName.create(variableNameInputTwo);

        assertFalse(variableNameOne.equals(variableNameTwo));
    }

    @Test
    public void testEqualsWithSameVariableName() throws Exception {
        final String variableNameInputOne = new String("var");
        final String variableNameInputTwo = new String("var");

        final VariableName variableNameOne = VariableName.create(variableNameInputOne);
        final VariableName variableNameTwo = VariableName.create(variableNameInputTwo);

        assertTrue(variableNameOne.equals(variableNameTwo));
    }

}
