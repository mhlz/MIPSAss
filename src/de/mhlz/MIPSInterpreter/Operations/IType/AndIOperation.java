package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 11.02.14.
 */
public class AndIOperation extends IOperation {
	public AndIOperation(Interpreter i, int rs, int rt, int immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		int temp = imm;
		i.setReg(rt, i.getReg(rs) & temp);

		return false;
	}
}
