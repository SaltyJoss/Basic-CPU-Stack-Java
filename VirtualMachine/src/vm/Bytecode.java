package vm;

public class Bytecode {
	
	public static class Instruction {
		String name; // "iadd", "isub", "imul" etc...
		int n = 0;
		public Instruction(String name) { this(name, 0); }
		public Instruction(String name, int nOpnds) {
			this.name = name;
			this.n = nOpnds;
		}
	}
	
	// INSTRUCUTION BYTECODES -> refer to notes for extra information -> each int resembles a constant 
	public static final int IADD = 1;		// int add
	public static final int ISUB = 2;		// int subract
	public static final int IMUL = 3;		// int multiply
	public static final int ILT = 4;		// int less then
	public static final int IEQ = 5;		// int equal
	public static final int BR = 6;			// branch
	public static final int BRT = 7; 		// branch if true
	public static final int BRF = 8;		// branch if false
	public static final int ICONST = 9;		// push constant int
	public static final int LOAD = 10;		// load from local or arg
	public static final int GLOAD = 11; 	// load from global memory
	public static final int STORE = 12; 	// store in local memory
	public static final int GSTORE = 13;	// store in global memory
	public static final int PRINT = 14;		// print stack to top
	public static final int POP = 15;		// throw away top of stack
	public static final int HALT = 16;		// stop
	
	
	public static Instruction[] instructions = new Instruction[] {
			null,
			new Instruction("IADD"),
			new Instruction("ISUB"),
			new Instruction("IMUL"),
			new Instruction("ILT"),
			new Instruction("IEQ"),
			new Instruction("BR", 1),
			new Instruction("BRT", 1),
			new Instruction("BRF", 1),
			new Instruction("ICONST", 1),
			new Instruction("LOAD", 1),
			new Instruction("GLOAD", 1),
			new Instruction("STORE", 1),
			new Instruction("GSTORE", 1),
			new Instruction("PRINT"),
			new Instruction("POP"),
			new Instruction("HALT")
	};
}
