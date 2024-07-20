package vm;

import static vm.Bytecode.*;

import java.util.ArrayList;
import java.util.List;

public class VM {
	int[] globals;
	int[] code;
	int[] stack;
	
	int ip;
	int sp = -1;
	int fp;
	
	boolean trace = false;
	
	public VM(int[] code, int main, int datasize) {
		this.code = code;
		this.ip = main;
		globals = new int[datasize];
		stack = new int[100];
	}
	
	public void cpu() {
	loop:
		while(ip < code.length) {
			int addr, offset, a, b;
			int opcode = code[ip]; // fetch
			if(trace) System.err.printf("%-35s", disassemble());
			ip++; // jumps to next instr or to nex
			switch (opcode) {
				case IADD:
					b = stack[sp--];
					a = stack[sp--];
					stack[++sp] = a + b;
					break;
				case ISUB:
					b = stack[sp--];
					a = stack[sp--];
					stack[++sp] = a - b;
					break;
				case IMUL:
					b = stack[sp--];
					a = stack[sp--];
					stack[++sp] = a * b;
					break;
				case BR:
					addr = code[ip++];
					break;
				/*case BRT:
					addr = code[ip++];
					if(stack[sp--] == true) ip = addr;
					break;
				case BRF:
					addr = code[ip++];
					if(stack[sp--] == FALSE) ip = addr;
					break;*/
				case ICONST:
					int v = code[ip];
					ip++;
					sp++;
					stack[sp] = v;
					break;
				case LOAD: // load from local or arg
					offset = code[ip++];
					stack[++sp] = stack[sp+offset];
					break;
				case GLOAD: // load from global memory
					addr = code[ip++];
					stack[++sp] = globals[addr];
					break;
				case STORE: // store in local
					offset = code[ip++];
					stack[fp+offset] = stack[sp--];
					break;
				case GSTORE: // store in global
					addr = code[ip++];
					globals[addr] = stack[sp--];
					break;
				case PRINT:
					System.out.println(stack[sp--]);
					break;
				case POP:
					--sp;
					break;
				case HALT:
					break loop;
			}

			if(trace) System.err.println(stackString());
			opcode = code[ip];
		}
		if(trace) System.err.printf("%-35s", disassemble());
		if(trace) System.err.println(stackString());
		if(trace) dumpDataMemory();
	}
	
	protected String stackString() {
		StringBuilder buf = new StringBuilder();
		buf.append("stack=[");
		for(int i=0; i<=sp; i++) {
			int o = stack[i];
			buf.append(" ");
			buf.append(o);
		}
		buf.append(" ]");
		return buf.toString();
	}
	
	private String disassemble() {
		if(ip >= code.length) return "";
		int opcode = code[ip];
		String opName = Bytecode.instructions[opcode].name;
		StringBuilder buf = new StringBuilder();
		buf.append(String.format("%04d:\t%-11s", ip, opName));
		int nargs = Bytecode.instructions[opcode].n;
		if(nargs > 0) {
			List<String> operands = new ArrayList<String>();
			for(int i=ip+1; i<=ip+nargs; i++) {
				operands.add(String.valueOf(code[i]));
			}
			for(int i=0; i<operands.size(); i++) {
				String s = operands.get(i);
				if (i>0) buf.append(", ");
				buf.append(s);
			}
		}
		return buf.toString();
	}
	
	protected void dumpDataMemory() {
		System.err.println("Data memory:");
		int addr = 0;
		for (int o : globals) {
			System.err.printf("%04d: %s\n", addr, o);
			addr++;
		}
		System.err.println();
	}
	
	protected void dumpCodeMemory() {
		System.err.println("Code memory:");
		int addr = 0;
		for (int o : code) {
			System.err.printf("%04d: %s\n", addr, o);
			addr++;
		}
		System.err.println();
	}
}
