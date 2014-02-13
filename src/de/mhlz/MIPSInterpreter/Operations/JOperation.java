package de.mhlz.MIPSInterpreter.Operations;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.JType.JALOperation;

/**
 * Created by mischa on 11.02.14.
 */
public abstract class JOperation extends Operation {

	protected int jumpTarget;

	public JOperation(Interpreter i, int target) {
		super(i);

		jumpTarget = target;
	}

	public static JOperation getOperationFromString(Interpreter interpreter, String code, int jt) {
		if(code.equals("j")) {
			return new de.mhlz.MIPSInterpreter.Operations.JType.JOperation(interpreter, jt);
		} else if(code.equals("jal")) {
			return new JALOperation(interpreter, jt);
		} else {
			return null;
		}
	}

	@Override
	public abstract boolean execute();
}
