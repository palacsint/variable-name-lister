package hu.palacsint.variablenamelister;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

@RunWith(MockitoJUnitRunner.class)
public class VariableCollectorTest {

    @Mock
    private Configuration configuration;

    @Test
    public void testCollectVariableNames() throws Exception {
        final VariableCollector variableNameCollector = createVariableNameCollector();
        final Multimap<VariableName, VariableType> variableNames = variableNameCollector.getNameMap();

        final Multimap<VariableName, VariableType> expectedNames = LinkedHashMultimap.create();
        fillExpectedNamesMap(expectedNames, "field", "int");
        fillExpectedNamesMap(expectedNames, "innerField", "Object");
        fillExpectedNamesMap(expectedNames, "localVariable", "Long");
        fillExpectedNamesMap(expectedNames, "innerLocalVariable", "String");
        fillExpectedNamesMap(expectedNames, "methodParameter", "Object");
        fillExpectedNamesMap(expectedNames, "innerMethodParameter", "String");
        fillExpectedNamesMap(expectedNames, "constructorParameter", "String");
        fillExpectedNamesMap(expectedNames, "STATIC_FIELD", "int");
        fillExpectedNamesMap(expectedNames, "exception", "IllegalStateException", "IllegalArgumentException");

        assertEquals(expectedNames, variableNames);
    }

    @Test
    public void testCollectVariableTypes() throws Exception {
        final VariableCollector variableNameCollector = createVariableNameCollector();
        final Multimap<VariableType, VariableName> variableNames = variableNameCollector.getTypeMap();

        final Multimap<VariableType, VariableName> expectedTypes = LinkedHashMultimap.create();
        fillExpectedTypesMap(expectedTypes, "field", "int");
        fillExpectedTypesMap(expectedTypes, "innerField", "Object");
        fillExpectedTypesMap(expectedTypes, "localVariable", "Long");
        fillExpectedTypesMap(expectedTypes, "innerLocalVariable", "String");
        fillExpectedTypesMap(expectedTypes, "methodParameter", "Object");
        fillExpectedTypesMap(expectedTypes, "innerMethodParameter", "String");
        fillExpectedTypesMap(expectedTypes, "constructorParameter", "String");
        fillExpectedTypesMap(expectedTypes, "STATIC_FIELD", "int");
        fillExpectedTypesMap(expectedTypes, "exception", "IllegalStateException", "IllegalArgumentException");
        assertEquals(expectedTypes, variableNames);
    }

    @Test
    public void testIgnoredFile() throws Exception {
        final String ignoreFilename = "Ignored.java";
        when(configuration.isIgnoredFile(ignoreFilename)).thenReturn(true);

        final VariableCollector variableCollector = new VariableCollector(configuration);
        variableCollector.collectVariableNames(ignoreFilename);

        assertTrue("empty names", variableCollector.getNameMap().isEmpty());
        assertTrue("empty types", variableCollector.getTypeMap().isEmpty());
    }

    @Test
    public void testNonExistingFile() throws Exception {
        final String nonExistingFilename = "NonExisting.java";

        final VariableCollector variableCollector = new VariableCollector(configuration);
        try {
            variableCollector.collectVariableNames(nonExistingFilename);
            fail("exception expected");
        } catch (final RuntimeException expected) {
            assertThat(expected.getMessage()).contains(nonExistingFilename);
            assertThat(expected.getMessage()).containsIgnoringCase("could not open");
        }
    }

    @Test
    public void testUnparseableFile() throws Exception {
        final String unparseableFilename = "Unparseable.java";
        final String unparseableFilePath = getTestJavaSourcePath(unparseableFilename);

        final VariableCollector variableCollector = new VariableCollector(configuration);
        try {
            variableCollector.collectVariableNames(unparseableFilePath);
            fail("exception expected");
        } catch (final RuntimeException expected) {
            assertThat(expected.getMessage()).contains(unparseableFilename);
            assertThat(expected.getMessage()).containsIgnoringCase("could not parse");
        }
    }

    private VariableCollector createVariableNameCollector() throws Exception {
        final VariableCollector variableCollector = new VariableCollector(configuration);
        final String filename = getTestJavaSourcePath();
        variableCollector.collectVariableNames(filename);
        return variableCollector;
    }

    private void fillExpectedNamesMap(final Multimap<VariableName, VariableType> expectedNames, final String name,
            final String... types) {
        for (final String type: types) {
            expectedNames.put(VariableName.create(name), VariableType.create(type));
        }
    }

    private void fillExpectedTypesMap(final Multimap<VariableType, VariableName> expectedTypes, final String name,
            final String... types) {
        for (final String type: types) {
            expectedTypes.put(VariableType.create(type), VariableName.create(name));
        }
    }

    private String getTestJavaSourcePath() throws Exception {
        return getTestJavaSourcePath("VariableNameExample.java");
    }

    private String getTestJavaSourcePath(final String filename) throws Exception {
        final URL resource = VariableCollectorTest.class.getResource("/" + filename);
        assertNotNull("test sample resource", resource);
        final String path = resource.getPath();
        return path;
    }
}
