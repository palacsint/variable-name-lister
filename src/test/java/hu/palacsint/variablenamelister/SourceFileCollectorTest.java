package hu.palacsint.variablenamelister;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SourceFileCollectorTest {

    @Mock
    private Configuration configuration;

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(configuration);
    }

    @Test
    public void testCollectSourceFilesHappyPath() {
        final String parentDirectoryPath = getResourcesDirectoryPath();
        when(configuration.getParentDirectoryPath()).thenReturn(parentDirectoryPath);
        when(configuration.getFileFilter()).thenReturn(new WildcardFileFilter("*.java"));

        final SourceFileCollector fileCollector = new SourceFileCollector(configuration);
        final List<File> sourceFiles = fileCollector.collectSourceFiles();

        assertContains(sourceFiles, "VariableNameExample.java");

        verify(configuration).getParentDirectoryPath();
        verify(configuration).getFileFilter();
    }

    private void assertContains(final List<File> files, final String filename) {
        for (final File file: files) {
            final String absolutePath = file.getAbsolutePath();
            final boolean fileFound = absolutePath.contains(filename);
            if (fileFound) {
                return;
            }
        }

        fail("File not found: " + filename);
    }

    private String getResourcesDirectoryPath() {
        final URL resourceUrl = SourceFileCollectorTest.class.getResource("/");
        assertNotNull("resource root", resourceUrl);
        final String resourcesDirectoryPath = resourceUrl.getPath();
        return resourcesDirectoryPath;
    }

}
