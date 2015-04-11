package hu.palacsint.variablenamelister;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Multimap;

public class VariableLister {

    private final Configuration configuration;

    public VariableLister(final Configuration configuration) {
        this.configuration = checkNotNull(configuration, "configuration cannot be null");
    }

    public void run() {
        final List<File> sourceFiles = collecSourceFiles();

        final VariableCollector variableNameCollector = collectVariables(sourceFiles);

        createNameReport(variableNameCollector);
        createTypeReport(variableNameCollector);
    }

    private List<File> collecSourceFiles() {
        final SourceFileCollector fileCollector = new SourceFileCollector(configuration);
        final List<File> sourceFiles = fileCollector.collectSourceFiles();
        return sourceFiles;
    }

    private VariableCollector collectVariables(final List<File> sourceFiles) {
        final VariableCollector variableCollector = new VariableCollector(configuration);
        for (final File sourceFile: sourceFiles) {
            final String sourceFilePath = sourceFile.getAbsolutePath();
            variableCollector.collectVariableNames(sourceFilePath);
        }
        return variableCollector;
    }

    private void createNameReport(final VariableCollector variableNameCollector) {
        final Multimap<VariableName, VariableType> nameMap = variableNameCollector.getNameMap();
        final String reportFilePath = configuration.getNameReportFilePath();

        createReport(nameMap, reportFilePath);
    }

    private void createTypeReport(final VariableCollector variableNameCollector) {
        final Multimap<VariableType, VariableName> typeMap = variableNameCollector.getTypeMap();
        final String reportFilePath = configuration.getTypeReportFilePath();
        createReport(typeMap, reportFilePath);
    }

    private <K, V> void createReport(final Multimap<K, V> reportData, final String reportFilePath) {
        final StringBuilder report = new StringBuilder();

        for (final K key: reportData.keySet()) {
            final Collection<V> values = reportData.get(key);
            report.append(key + ": " + values).append("\n");
        }

        try {
            FileUtils.write(new File(reportFilePath), report);
        } catch (final IOException ioe) {
            throw new RuntimeException("Could not write report file: " + reportFilePath, ioe);
        }
    }
}
