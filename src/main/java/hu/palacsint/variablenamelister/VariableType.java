package hu.palacsint.variablenamelister;

import static hu.palacsint.common.base.Preconditions2.checkNotBlank;

import javax.annotation.concurrent.Immutable;

@Immutable
public class VariableType {

    private final String variableType;

    private VariableType(final String variableType) {
        this.variableType = checkNotBlank(variableType, "variableType cannot be blank");
    }

    public static VariableType create(final String variableType) {
        return new VariableType(variableType);
    }

    public String getVariableType() {
        return variableType;
    }

    @Override
    public String toString() {
        return variableType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((variableType == null) ? 0 : variableType.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariableType other = (VariableType) obj;
        if (variableType == null) {
            if (other.variableType != null) {
                return false;
            }
        } else if (!variableType.equals(other.variableType)) {
            return false;
        }
        return true;
    }

}
