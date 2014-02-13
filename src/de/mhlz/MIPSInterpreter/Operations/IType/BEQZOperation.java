package de.mhlz.MIPSInterpreter.Operations.IType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IOperation;

/**
 * Created by mischa on 11.02.14.
 */
public class BEQZOperation extends IOperation {
	public BEQZOperation(Interpreter i, int rs, int rt, int immediate) {
		super(i, rs, rt, immediate);
	}

	@Override
	public boolean execute() {
		int signImm = (int)(imm);
		if(i.getReg(rs).equals(0)) {
			i.setPC(signImm);

			return true;
		}

		return false;
	}
}
