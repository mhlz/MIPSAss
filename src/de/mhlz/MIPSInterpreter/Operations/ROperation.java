package de.mhlz.MIPSInterpreter.Operations;

import de.mhlz.MIPSInterpreter.Exceptions.IllegalRegisterException;
import de.mhlz.MIPSInterpreter.Interpreter;
import de.mhlz.MIPSInterpreter.Operations.RType.*;

/**
 * Created by mischa on 11.02.14.
 */
public abstract class ROperation extends Operation {

	protected int rs;
	protected int rt;
	protected int rd;
	protected int shamt;

	public ROperation(Interpreter i, int rs, int rt, int rd, int shamt) {
		super(i);

		this.rs = rs;
		this.rt = rt;
		this.rd = rd;
		this.shamt = shamt;
	}

	public static ROperation getOperationFromString(Interpreter interpreter, String code, int rs, int rt, int rd, int shamt) {
		if(code.equals("add")) {
			return new AddOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("and")) {
			return new AndOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("break")) {
			return new BreakOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("div")) {
			return new DivOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("jalr")) {
			return new JALROperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("jr")) {
			return new JROperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("mfhi")) {
			return new MFHIOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("mflo")) {
			return new MFLOOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("mthi")) {
			return new MTHIOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("mtlo")) {
			return new MTLOOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("mult")) {
			return new MultOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("nor")) {
			return new NorOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("or")) {
			return new OrOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("sll")) {
			return new SLLOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("sllv")) {
			return new SLLVOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("slt")) {
			return new SLTOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("sra")) {
			return new SRAOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("srav")) {
			return new SRAVOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("srl")) {
			return new SRLOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("srlv")) {
			return new SRLVOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("sub")) {
			return new SubOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("subu")) {
			return new SubUOperation(interpreter, rs, rt, rd, shamt);
		} else if(code.equals("xor")) {
			return new XOrOperation(interpreter, rs, rt, rd, shamt);
		} else {
			return null;
		}
	}

	public abstract boolean execute();
}
