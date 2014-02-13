package de.mhlz.MIPSInterpreter.Exceptions;

/**
 * Created by mischa on 13.02.14.
 */
public class RegisterAddressException extends MIPSRuntimeException {

	protected String codeLine;
	protected int lineNumber;
	protected int register;

	public RegisterAddressException(String codeLine, int lineNumber, int register) {
		this.codeLine = codeLine;
		this.lineNumber = lineNumber;
		this.register = register;
	}
	@Override
	public String getMessage() {
		return "Tried to access an illegal register (" + register + ") in line " + lineNumber + " '" + codeLine + "'";
	}
}
