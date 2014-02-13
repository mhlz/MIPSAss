package de.mhlz.MIPSInterpreter;

import de.mhlz.MIPSInterpreter.Exceptions.IllegalRegisterException;
import de.mhlz.MIPSInterpreter.Exceptions.RegisterAddressException;
import de.mhlz.MIPSInterpreter.Operations.Operation;

import java.util.*;

/**
 * Created by mischa on 11.02.14.
 */
public class Interpreter {

	public static int REG_ZERO =  0;
	public static int REG_AT   =  1;
	public static int REG_V0   =  2;
	public static int REG_V1   =  3;
	public static int REG_A0   =  4;
	public static int REG_A1   =  5;
	public static int REG_A2   =  6;
	public static int REG_A3   =  7;
	public static int REG_T0   =  8;
	public static int REG_T1   =  9;
	public static int REG_T2   = 10;
	public static int REG_T3   = 11;
	public static int REG_T4   = 12;
	public static int REG_T5   = 13;
	public static int REG_T6   = 14;
	public static int REG_T7   = 15;
	public static int REG_S0   = 16;
	public static int REG_S1   = 17;
	public static int REG_S2   = 18;
	public static int REG_S3   = 19;
	public static int REG_S4   = 20;
	public static int REG_S5   = 21;
	public static int REG_S6   = 22;
	public static int REG_S7   = 23;
	public static int REG_T8   = 24;
	public static int REG_T9   = 25;
	public static int REG_K0   = 26;
	public static int REG_K1   = 27;
	public static int REG_GP   = 28;
	public static int REG_SP   = 29;
	public static int REG_FP   = 30;
	public static int REG_RA   = 31;

	private static String[] regNames = {
			"0",
			"AT",
			"V0",
			"V1",
			"A0",
			"A1",
			"A2",
			"A3",
			"T0",
			"T1",
			"T2",
			"T3",
			"T4",
			"T5",
			"T6",
			"T7",
			"S0",
			"S1",
			"S2",
			"S3",
			"S4",
			"S5",
			"S6",
			"S7",
			"T8",
			"T9",
			"K0",
			"K1",
			"GP",
			"SP",
			"FP",
			"RA",
	};


	protected Integer[] registers;

	protected int PC;

	protected String code;
	protected String[] codeLines;

	protected ArrayList<Operation> operations;

	protected int hi;
	protected int lo;

	protected Map<Integer, Integer> memory;

	protected int compileLine;
	protected String compileLineString;

	protected int cycles;

	public Interpreter() {
		registers = new Integer[32];
		for(int i = 0; i < 32; i++) {
			registers[i] = 0;
		}

		PC = 0;

		operations = new ArrayList<Operation>();
		memory = new TreeMap<Integer, Integer>();
	}

	public Interpreter(String code) {
		this();

		this.setCodeString(code);
	}

	public int getWord(int address) {
		return memory.get(address);
	}

	public void setWord(int address, int value) {
		memory.put(address, value);
	}

	public void setCodeString(String code) {
		this.code = code;
		operations.clear();
		memory.clear();

		registers[REG_SP] = 5000;

		compileLine = 0;
		cycles = 0;

		codeLines = code.split("\\r?\\n");
		for(String line : codeLines) {
			compileLine++;
			compileLineString = line;
			operations.add(Operation.getOperationFromString(this, line));
		}
	}

	public void setCodeHex(String code) {

	}

	public void setCodeBinary(String code) {

	}

	public void addCodeString(String code) {
		this.code += code;

		String[] ncodeLines = code.split("\\r?\\n");
		ArrayList<String> newCodeLines = new ArrayList<String>();
		newCodeLines.addAll(Arrays.asList(codeLines));
		newCodeLines.addAll(Arrays.asList(ncodeLines));
		codeLines = newCodeLines.toArray(new String[newCodeLines.size()]);

		compileLine = operations.size();
		for(String line : ncodeLines) {
			compileLineString = line;
			operations.add(Operation.getOperationFromString(this, line));
			compileLine++;
		}
	}

	public void addCodeHex(String code) {

	}

	public void addCodeBinary(String code) {

	}

	public int tick() {
		cycles++;

		if(PC >= operations.size()) {
			return -1;
		}

		while(operations.get(PC) == null) {
			PC++;
			if(PC >= operations.size()) {
				return -1;
			}
		}
		Operation op = operations.get(PC);
		//System.out.println(cycles + ": " + PC + "=> " + codeLines[PC]);
		if(!op.execute()) {
			PC++;
		}

		//System.out.println(this);

		return PC;
	}

	public void setPC(int newPC) {
		PC = newPC;
	}

	public int getPC() {
		return PC;
	}

	public Integer getReg(int addr) throws RegisterAddressException {
		if(addr == -1) {
			throw new RegisterAddressException(codeLines[PC], PC, addr);
		}
		return registers[addr];
	}

	public void setReg(int addr, Integer content) {
		registers[addr] = content;
	}

	public void setHilo(long newHilo) {
		hi = (int)(newHilo >>> 32);
		long temp = newHilo << 32;
		lo = (int)(temp >>> 32);
	}

	public long getHilo() {
		return ((long)hi << 32) + (long)lo;
	}

	public int getHi() {
		return hi;
	}

	public int getLo() {
		return lo;
	}

	public void setHi(int hi) {
		this.hi = hi;
	}

	public void setLo(int lo) {
		this.lo = lo;
	}

	public String[] getCodeLines() {
		return codeLines;
	}

	public String getCurrentCodeLine() {
		if(codeLines.length <= PC || PC < 0) {
			return ":END:";
		}
		int pos = PC;
		while(operations.get(pos) == null) {
			pos++;
			if(codeLines.length <= pos || pos < 0) {
				return ":END:";
			}
		}
		return codeLines[pos];
	}

	public Integer[] getRegisters() {
		return registers;
	}

	public String getCurrentCompileLine() {
		return compileLineString;
	}

	public int getCurrentCompileLineNumber() {
		return compileLine;
	}

	public String toString() {
		String ret = "PC: " + PC + "\n";
		int i = 0;
		for(Integer reg : registers) {
			ret += regNames[i] + ": " + String.format("%32s", Integer.toBinaryString(reg)).replace(' ', '0') + " (" + reg + ")\n";
			i++;
		}

		return ret;
	}


	public int getRegisterAddress(String reg) throws IllegalRegisterException {
		reg = reg.substring(1).toUpperCase();
		int addr = Arrays.asList(regNames).indexOf(reg);
		if(addr == -1) {
			throw new IllegalRegisterException(reg, compileLine, compileLineString);
		}

		return addr;
	}
}
