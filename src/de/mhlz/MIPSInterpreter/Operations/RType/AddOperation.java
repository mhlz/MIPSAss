package de.mhlz.MIPSInterpreter.Operations.RType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.ROperation;

/**
 * Created by mischa on 11.02.14.
 */
public class AddOperation extends ROperation {
	public AddOperation(Interpreter i, int rs, int rt, int rd, int shamt) {
		super(i, rs, rt, rd, 0);
	}

	@Override
	public boolean execute() {
		i.setReg(rd, i.getReg(rs) + i.getReg(rt));

		return false;
	}
}
