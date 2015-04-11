package hu.palacsint.variablenamelister;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VariableListerTest {

    @Mock
    private Configuration configuration;

    @Test
    public void testConstructor() {
        new VariableLister(configuration);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullConfiguration() {
        final Configuration configuration = null;
        new VariableLister(configuration);
    }

}
