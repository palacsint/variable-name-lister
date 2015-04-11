package hu.palacsint.variablenamelister.visitor;

import static com.google.common.collect.Lists.newArrayList;
import japa.parser.ast.Node;
import japa.parser.ast.body.MultiTypeParameter;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class VariableDeclaratorIdParentVisitor extends VoidVisitorAdapter<Void> {

    private final List<String> types = newArrayList();

    public VariableDeclaratorIdParentVisitor() {
    }

    @Override
    public void visit(final Parameter parameter, final Void arg) {
        final String type = parameter.getType().toString();
        types.add(type);
    }

    @Override
    public void visit(final MultiTypeParameter multiTypeParameter, final Void arg) {
        final List<Type> types = multiTypeParameter.getTypes();
        for (final Type type: types) {
            this.types.add(type.toString());
        }
    }

    @Override
    public void visit(final VariableDeclarator variableDeclarator, final Void arg) {
        final Node parentNode = variableDeclarator.getParentNode();
        final VariableDeclaratorParentVisitor grandParentVisitor = new VariableDeclaratorParentVisitor();
        parentNode.accept(grandParentVisitor, null);
        types.add(grandParentVisitor.getType());
    }

    public List<String> getTypes() {
        return newArrayList(types);
    }
}
