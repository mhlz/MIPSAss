package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 11.02.14.
 */
public class XOrIOperation extends IOperation {
	public XOrIOperation(Interpreter i, int rs, int rt, short immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		int temp = (int)(imm << 16);
		temp = temp >>> 16;
		i.setReg(rt, i.getReg(rs) ^ temp);

		return false;
	}
}
