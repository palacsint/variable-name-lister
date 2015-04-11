package hu.palacsint.variablenamelister.visitor;

import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class VariableDeclaratorParentVisitor extends VoidVisitorAdapter<Void> {

    private String type;

    public VariableDeclaratorParentVisitor() {
    }

    @Override
    public void visit(final FieldDeclaration fieldDeclaration, final Void arg) {
        type = fieldDeclaration.getType().toString();
    }

    @Override
    public void visit(final VariableDeclarationExpr variableDeclarationExpr, final Void arg) {
        type = variableDeclarationExpr.getType().toString();
    }

    public String getType() {
        return type;
    }
}
