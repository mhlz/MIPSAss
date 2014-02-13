package de.mhlz.MIPSInterpreter.Operations.RType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.ROperation;

/**
 * Created by mischa on 11.02.14.
 */
public class SRLOperation extends ROperation {
	public SRLOperation(Interpreter i, int rs, int rt, int rd, int shamt) {
		super(i, rs, rt, rd, shamt);
	}

	@Override
	public boolean execute() {
		i.setReg(rd, i.getReg(rt) >>> shamt);

		return false;
	}
}
