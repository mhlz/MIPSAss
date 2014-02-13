package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 13.02.14.
 */
public class LWOperation extends IOperation {
	public LWOperation(Interpreter i, int rs, int rt, short immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		i.setReg(rt, i.getWord(i.getReg(rs) + (int)(imm)));

		return false;
	}
}
