package hu.palacsint.variablenamelister;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class SourceFileCollector {

    private final Configuration configuration;

    public SourceFileCollector(final Configuration configuration) {
        this.configuration = checkNotNull(configuration, "configuration cannot be null");
    }

    public List<File> collectSourceFiles() {
        final String parentDirectoryPath = configuration.getParentDirectoryPath();
        final File parentDirectory = new File(parentDirectoryPath);
        return collectSourceFiles(parentDirectory);
    }

    private List<File> collectSourceFiles(final File parentDirectory) {
        final IOFileFilter fileFilter = configuration.getFileFilter();
        final Collection<File> files = FileUtils.listFiles(parentDirectory, fileFilter, TrueFileFilter.INSTANCE);
        return newArrayList(files);
    }
}
