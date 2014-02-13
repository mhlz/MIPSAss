package de.mhlz.MIPSInterpreter.Operations.RType;

import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.ROperation;

/**
 * Created by mischa on 11.02.14.
 */
public class DivOperation extends ROperation {
	public DivOperation(Interpreter i, int rs, int rt, int rd, int shamt) {
		super(i, rs, rt, rd, shamt);
	}

	@Override
	public boolean execute() {
		i.setLo(i.getReg(rs) / i.getReg(rt));
		i.setHi(i.getReg(rs) % i.getReg(rt));

		return false;
	}
}