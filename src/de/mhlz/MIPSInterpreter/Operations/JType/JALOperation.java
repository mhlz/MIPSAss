package de.mhlz.MIPSInterpreter.Operations.JType;

import de.mhlz.MIPSInterpreter.Interpreter;

/**
 * Created by mischa on 11.02.14.
 */
public class JALOperation extends de.mhlz.MIPSInterpreter.Operations.JOperation {
	public JALOperation(Interpreter i, int target) {
		super(i, target);
	}

	@Override
	public boolean execute() {
		i.setReg(Interpreter.REG_RA, i.getPC() + 1);
		i.setPC(jumpTarget);

		return true;
	}
}
