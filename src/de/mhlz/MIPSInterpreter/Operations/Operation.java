package de.mhlz.MIPSInterpreter.Operations;


import de.mhlz.MIPSInterpreter.Interpreter;

import java.util.Arrays;

public abstract class Operation {

	private static String[] rtypes = {
			"add",
			"and",
			"break",
			"div",
			"jalr",
			"jr",
			"mfhi",
			"mflo",
			"mthi",
			"mtlo",
			"mult",
			"nor",
			"or",
			"sll",
			"sllv",
			"slt",
			"sra",
			"srav",
			"srl",
			"srlv",
			"sub",
			"subu",
			"xor"
	};

	private static String[] itypes = {
			"addi",
			"andi",
			"beq",
			"beqz",
			"bgtz",
			"blez",
			"bne",
			"lui",
			"lw",
			"ori",
			"slti",
			"subi",
			"sw",
			"xori"
	};

	private static String[] jtypes = {
			"jal",
			"j"
	};

	protected Interpreter i;

	public Operation(Interpreter i) {
		this.i = i;
	}

	public static Operation getOperationFromString(Interpreter interpreter, String opline) {
		opline = opline.trim();
		try {
			if(opline.contains("#")) {
				opline = opline.substring(0, opline.indexOf("#") - 1);
			}
		} catch(StringIndexOutOfBoundsException e) {
			return null;
		}
		String op = "";
		int paramStart = 0;
		int i = 0;
		for(Character c : opline.toCharArray()) {
			if(c == ' ' || c == '\t') {
				paramStart = i + 1;
				break;
			}
			op += c;
			i++;
		}
		op = op.toLowerCase();
		if(Arrays.asList(rtypes).contains(op)) {
			String params[] = opline.substring(paramStart).split(",");
			int rd = interpreter.getRegisterAddress(params[0].trim());
			int rs = -1;
			int rt = -1;
			int shamt = -1;
			if(op.startsWith("j")) {
				rd = -1;
				rs = interpreter.getRegisterAddress(params[0].trim());
			} else if(op.startsWith("mult") || op.startsWith("div")) {
				rd = -1;
				rs = interpreter.getRegisterAddress(params[0].trim());
				rt = interpreter.getRegisterAddress(params[1].trim());
			} else if(params.length > 1 && params[2].trim().startsWith("$")) {
				rs = interpreter.getRegisterAddress(params[1].trim());
				rt = interpreter.getRegisterAddress(params[2].trim());
			} else if(params.length > 1) {
				rt = interpreter.getRegisterAddress(params[1].trim());
				shamt = Integer.parseInt(params[2].trim());
			}

			return ROperation.getOperationFromString(interpreter, op, rs, rt, rd, shamt);
		} else if(Arrays.asList(jtypes).contains(op)) {
			String param = opline.substring(paramStart);
			int jt = Arrays.asList(interpreter.getCodeLines()).indexOf(param + ":");

			return JOperation.getOperationFromString(interpreter, op, jt);
		} else if(Arrays.asList(itypes).contains(op)) {
			String params[] = opline.substring(paramStart).split("[,(]");
			int rs = -1;
			int rt = -1;
			int imm = -1;
			if(params.length == 2) {
				if(op.startsWith("l")) {
					rt = interpreter.getRegisterAddress(params[0].trim());
					imm = Short.parseShort(params[1].trim());
				} else {
					rs = interpreter.getRegisterAddress(params[0].trim());
					imm = (short)Arrays.asList(interpreter.getCodeLines()).indexOf(params[1].trim() + ":");
				}
			} else if(params.length == 3 && params[2].endsWith(")")) {
				params[2] = params[2].trim().substring(0, params[2].length() - 1);

				rt = interpreter.getRegisterAddress(params[0].trim());
				rs = interpreter.getRegisterAddress(params[2].trim());
				imm = Short.parseShort(params[1].trim());
			} else if(params.length == 3 && op.startsWith("b")) {
				rt = interpreter.getRegisterAddress(params[0].trim());
				rs = interpreter.getRegisterAddress(params[1].trim());
				imm = (short)Arrays.asList(interpreter.getCodeLines()).indexOf(params[2].trim() + ":");
			} else if(params.length == 3) {
				rt = interpreter.getRegisterAddress(params[0].trim());
				rs = interpreter.getRegisterAddress(params[1].trim());
				imm = Integer.parseInt(params[2].trim());
			} else {
				return null;
			}

			return IOperation.getOperationFromString(interpreter, op, rs, rt, imm);
		}

		return null;
	}


	public abstract boolean execute();

}
