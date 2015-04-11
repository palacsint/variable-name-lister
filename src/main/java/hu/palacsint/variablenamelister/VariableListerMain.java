package hu.palacsint.variablenamelister;

public class VariableListerMain {

    private VariableListerMain() {
    }

    public static void main(final String[] args) throws Exception {
        final Configuration configuration = new Configuration();
        final VariableLister variableLister = new VariableLister(configuration);
        variableLister.run();
    }
}
