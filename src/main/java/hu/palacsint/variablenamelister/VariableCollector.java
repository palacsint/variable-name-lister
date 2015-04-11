package hu.palacsint.variablenamelister;

import static com.google.common.base.Preconditions.checkNotNull;
import hu.palacsint.variablenamelister.visitor.VariableCollectorMethodVisitor;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Multimap;
import com.google.common.io.Closeables;

public class VariableCollector {

    private static final Logger logger = LoggerFactory.getLogger(VariableCollector.class);

    private final VariableCollectorMethodVisitor variableCollectorVisitor = new VariableCollectorMethodVisitor();

    private final Configuration configuration;

    public VariableCollector(final Configuration configuration) {
        this.configuration = checkNotNull(configuration, "configuration cannot be null");
    }

    public void collectVariableNames(final String filename) {
        logger.info("filename: {}", filename);
        if (configuration.isIgnoredFile(filename)) {
            return;
        }

        final FileInputStream sourceStream = getFileStream(filename);

        final CompilationUnit compilationUnit;
        try {
            compilationUnit = JavaParser.parse(sourceStream);
        } catch (final japa.parser.ParseException pe) {
            throw new RuntimeException("Could not parse file: " + filename, pe);
        } finally {
            Closeables.closeQuietly(sourceStream);
        }

        variableCollectorVisitor.visit(compilationUnit, null);
    }

    private FileInputStream getFileStream(final String filename) {
        try {
            final FileInputStream sourceStream = new FileInputStream(filename);
            return sourceStream;
        } catch (final FileNotFoundException fnfe) {
            throw new RuntimeException("Could not open file: " + filename, fnfe);
        }
    }

    public Multimap<VariableName, VariableType> getNameMap() {
        return variableCollectorVisitor.getNameMap();
    }

    public Multimap<VariableType, VariableName> getTypeMap() {
        return variableCollectorVisitor.getTypeMap();
    }
}
