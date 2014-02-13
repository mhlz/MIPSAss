package de.mhlz.MIPSInterpreter.Operations;

import de.mhlz.MIPSInterpreter.Exceptions.IllegalRegisterException;
import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.IType.*;

/**
 * Created by mischa on 11.02.14.
 */
public abstract class IOperation extends Operation {

	protected int imm;

	protected int rs;
	protected int rt;

	public IOperation(Interpreter i, int rs, int rt, int immediate) {
		super(i);

		this.rs = rs;
		this.rt = rt;
		imm = immediate;
	}

	public static IOperation getOperationFromString(Interpreter interpreter, String code, int rs, int rt, int imm) {
		if(code.equals("addi")) {
			return new AddIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("andi")) {
			return new AndIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("beq")) {
			return new BEQOperation(interpreter, rs, rt, imm);
		} else if(code.equals("beqz")) {
			return new BEQZOperation(interpreter, rs, rt, imm);
		} else if(code.equals("bgtz")) {
			return new BGTZOperation(interpreter, rs, rt, imm);
		} else if(code.equals("blez")) {
			return new BLEZOperation(interpreter, rs, rt, imm);
		} else if(code.equals("bne")) {
			return new BNEOperation(interpreter, rs, rt, imm);
		} else if(code.equals("lui")) {
			return new LUIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("lw")) {
			return new LWOperation(interpreter, rs, rt, imm);
		} else if(code.equals("ori")) {
			return new OrIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("slti")) {
			return new SLTIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("subi")) {
			return new SubIOperation(interpreter, rs, rt, imm);
		} else if(code.equals("sw")) {
			return new SWOperation(interpreter, rs, rt, imm);
		} else if(code.equals("xori")) {
			return new XOrIOperation(interpreter, rs, rt, imm);
		} else {
			return null;
		}
	}

	@Override
	public abstract boolean execute();
}
