package hu.palacsint.common.base;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

public class Preconditions2 {

    private Preconditions2() {
    }

    public static String checkNotBlank(final String inputString, final Object errorMessage) {
        checkArgument(StringUtils.isNoneBlank(inputString), errorMessage);
        return inputString;
    }

}
