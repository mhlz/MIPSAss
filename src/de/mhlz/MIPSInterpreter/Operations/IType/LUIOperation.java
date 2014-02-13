package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 11.02.14.
 */
public class LUIOperation extends IOperation {
	public LUIOperation(Interpreter i, int rs, int rt, short immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		int temp = imm << 16;
		i.setReg(rt, temp);

		return false;
	}
}
