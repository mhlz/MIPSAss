package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 13.02.14.
 */
public class SWOperation extends IOperation {
	public SWOperation(Interpreter i, int rs, int rt, int immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		i.setWord(i.getReg(rs) + (int)(imm), i.getReg(rt));

		return false;
	}
}
