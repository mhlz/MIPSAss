package de.mhlz.MIPSInterpreter.Operations.JType;

import de.mhlz.MIPSInterpreter.Interpreter;

/**
 * Created by mischa on 11.02.14.
 */
public class JOperation extends de.mhlz.MIPSInterpreter.Operations.JOperation {
	public JOperation(Interpreter i, int target) {
		super(i, target);
	}

	@Override
	public boolean execute() {
		i.setPC(jumpTarget);

		return true;
	}
}
