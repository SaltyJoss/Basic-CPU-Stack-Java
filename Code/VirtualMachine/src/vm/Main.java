package vm;

import static vm.Bytecode.*;

public class Main {
	static int[] hello = {
			ICONST, 99,
			GSTORE, 0,
			GLOAD, 0,
			PRINT,
			HALT
	};
	
	public static void main(String[] args) {
		int datasize = 1;
		int mainip = 0;
		VM vm = new VM(hello, mainip, datasize);
		vm.trace = true;
		vm.cpu();
		
	}
}
