package hu.palacsint.variablenamelister;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class Configuration {

    private final String parentDirectoryPath;

    private final List<String> ignoreList = newArrayList();

    private final IOFileFilter fileFilter;

    private final String typeReportFilePath = "/tmp/report/report-types.txt";

    private final String nameReportFilePath = "/tmp/report/report-names.txt";

    public Configuration() {
        parentDirectoryPath = "/home/miki/prog/workspace/main";
        fileFilter = createFileFilter();
    }

    private IOFileFilter createFileFilter() {
        final IOFileFilter javaFilter = new WildcardFileFilter("*.java");
        final IOFileFilter bshFilter = new WildcardFileFilter("*.bsh.java");
        final IOFileFilter notBshFilter = new NotFileFilter(bshFilter);

        final IOFileFilter filter = new AndFileFilter(javaFilter, notBshFilter);

        return filter;
    }

    public boolean isIgnoredFile(final String filename) {
        return ignoreList.contains(filename);
    }

    public String getParentDirectoryPath() {
        return parentDirectoryPath;
    }

    public IOFileFilter getFileFilter() {
        return fileFilter;
    }

    public String getNameReportFilePath() {
        return nameReportFilePath;
    }

    public String getTypeReportFilePath() {
        return typeReportFilePath;
    }
}
