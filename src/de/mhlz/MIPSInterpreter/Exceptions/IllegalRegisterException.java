package de.mhlz.MIPSInterpreter.Exceptions;

/**
 * Created by mischa on 13.02.14.
 */
public class IllegalRegisterException extends MIPSCompileTimeException {

	protected String regName;
	protected int lineNumber;
	protected String line;

	public IllegalRegisterException(String registerName, int lineNumber, String line) {
		this.lineNumber = lineNumber;
		this.regName = registerName;
		this.line = line;
	}
	@Override
	public String getMessage() {
		return "Tried to access an illegal register ('" + regName + "') in line " + lineNumber + "('" + line + "')";
	}
}
