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
		long t1 = i.getReg(rs);
		long t2 = i.getReg(rt);
		if(t1 < 0) {
			t1 = -t1;
		}
		if(t2 < 0) {
			t2 = -t2;
		}
		i.setReg(rd, (int)t1 - (int)t2);

		return false;
	}
}
