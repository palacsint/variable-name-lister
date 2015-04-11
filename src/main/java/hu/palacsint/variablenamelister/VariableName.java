package hu.palacsint.variablenamelister;

import static hu.palacsint.common.base.Preconditions2.checkNotBlank;

import javax.annotation.concurrent.Immutable;

@Immutable
public class VariableName {

    private final String variableName;

    private VariableName(final String variableName) {
        this.variableName = checkNotBlank(variableName, "variableName cannot be blank");
    }

    public static VariableName create(final String variableName) {
        return new VariableName(variableName);
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((variableName == null) ? 0 : variableName.hashCode());
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
        final VariableName other = (VariableName) obj;
        if (variableName == null) {
            if (other.variableName != null) {
                return false;
            }
        } else if (!variableName.equals(other.variableName)) {
            return false;
        }
        return true;
    }

}
