package dtu.calculator;

public class ErrorMessageHolder {
	private static String errorMessage = "";

	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String input) {
		errorMessage = input;
	}
}
