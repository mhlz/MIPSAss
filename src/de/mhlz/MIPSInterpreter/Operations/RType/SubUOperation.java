package de.mhlz.MIPSInterpreter.Operations.RType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.ROperation;

/**
 * Created by mischa on 13.02.14.
 */
public class SubUOperation extends ROperation {
	public SubUOperation(Interpreter i, int rs, int rt, int rd, int shamt) {
		super(i, rs, rt, rd, shamt);
	}

	@Override
	public boolean execute() {
		i.setReg(rd, Math.abs(i.getReg(rs)) - Math.abs(i.getReg(rt)));

		return false;
	}
}
