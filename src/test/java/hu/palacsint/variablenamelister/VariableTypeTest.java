package hu.palacsint.variablenamelister;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VariableTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBlank() {
        VariableType.create("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        VariableType.create(null);
    }

    @Test
    public void testGetter() throws Exception {
        final String variableTypeInput = "var1";

        final VariableType variableType = VariableType.create(variableTypeInput);

        assertEquals(variableTypeInput, variableType.getVariableType());
    }

    @Test
    public void testToString() throws Exception {
        final String variableTypeInput = "var1";

        final VariableType variableType = VariableType.create(variableTypeInput);

        assertThat(variableType.toString()).contains(variableTypeInput);
    }

    @Test
    public void testHashCodeWithSameVariableType() throws Exception {
        final String variableTypeInputOne = new String("type1");
        final String variableTypeInputTwo = new String("type1");

        final VariableType variableTypeOne = VariableType.create(variableTypeInputOne);
        final VariableType variableTypeTwo = VariableType.create(variableTypeInputTwo);

        assertThat(variableTypeOne.hashCode()).isEqualTo(variableTypeTwo.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentVariableType() throws Exception {
        final String variableTypeInputOne = new String("type");
        final String variableTypeInputTwo = new String("another type");

        final VariableType variableTypeOne = VariableType.create(variableTypeInputOne);
        final VariableType variableTypeTwo = VariableType.create(variableTypeInputTwo);

        assertThat(variableTypeOne.hashCode()).isNotEqualTo(variableTypeTwo.hashCode());
    }

    @Test
    public void testEqualsWithDifferentVariableType() throws Exception {
        final String variableTypeInputOne = new String("type");
        final String variableTypeInputTwo = new String("another type");

        final VariableType variableTypeOne = VariableType.create(variableTypeInputOne);
        final VariableType variableTypeTwo = VariableType.create(variableTypeInputTwo);

        assertFalse(variableTypeOne.equals(variableTypeTwo));
    }

    @Test
    public void testEqualsWithSameVariableType() throws Exception {
        final String variableTypeInputOne = new String("type");
        final String variableTypeInputTwo = new String("type");

        final VariableType variableTypeOne = VariableType.create(variableTypeInputOne);
        final VariableType variableTypeTwo = VariableType.create(variableTypeInputTwo);

        assertTrue(variableTypeOne.equals(variableTypeTwo));
    }

}
