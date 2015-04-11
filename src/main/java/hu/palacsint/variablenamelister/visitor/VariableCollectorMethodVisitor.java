package hu.palacsint.variablenamelister.visitor;

import static com.google.common.base.Preconditions.checkState;
import hu.palacsint.variablenamelister.VariableName;
import hu.palacsint.variablenamelister.VariableType;
import japa.parser.ast.Node;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

public class VariableCollectorMethodVisitor extends VoidVisitorAdapter<Void> {

    private final Multimap<VariableName, VariableType> nameMap = LinkedHashMultimap.create();

    private final Multimap<VariableType, VariableName> typeMap = LinkedHashMultimap.create();

    public VariableCollectorMethodVisitor() {
    }

    @Override
    public void visit(final VariableDeclaratorId variableDeclaratorId, final Void arg) {
        final String name = variableDeclaratorId.getName();
        final VariableName variableName = VariableName.create(name);

        final List<String> types = getType(variableDeclaratorId);
        for (final String type: types) {
            final VariableType variableType = VariableType.create(type);

            nameMap.put(variableName, variableType);
            typeMap.put(variableType, variableName);
        }
    }

    private List<String> getType(final VariableDeclaratorId variableDeclaratorId) {
        final Node parentNode = variableDeclaratorId.getParentNode();
        final VariableDeclaratorIdParentVisitor parentVisitor = new VariableDeclaratorIdParentVisitor();
        parentNode.accept(parentVisitor, null);
        final List<String> types = parentVisitor.getTypes();
        checkState(!types.isEmpty(), "Unknown parent (%s) for variable: %s", parentNode.getClass(),
                variableDeclaratorId.toString());
        return types;
    }

    public Multimap<VariableName, VariableType> getNameMap() {
        return LinkedHashMultimap.create(nameMap);
    }

    public Multimap<VariableType, VariableName> getTypeMap() {
        return LinkedHashMultimap.create(typeMap);
    }
}
