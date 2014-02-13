package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 11.02.14.
 */
public class BNEOperation extends IOperation {
	public BNEOperation(Interpreter i, int rs, int rt, short immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		int signImm = (int)(imm);
		if(!i.getReg(rs).equals(i.getReg(rt))) {
			i.setPC(signImm);

			return true;
		}

		return false;
	}
}
