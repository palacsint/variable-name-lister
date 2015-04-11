public class VariableNameExample {

	private int field = 7;

	private static final int STATIC_FIELD = 5;

	public VariableNameExample(final String constructorParameter) {
	}

	public void method(final Object methodParameter) {
		final Long localVariable = 772L;
	}

	public void method2() {
	    try {
	        method("param");
	    } catch (IllegalStateException | IllegalArgumentException exception) {
	    }
	}
	
	public class InnerClass {

		private Object innerField = "value3";

		public void innerClassMethod(final String innerMethodParameter) {
			final String innerLocalVariable = "value2";
		}
	}
}
