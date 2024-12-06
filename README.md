# INFORMATION

> ### Quick Note(s)
>
> I am a second year Computer Science student
> 
> This repo used multiple sources to learn about CPU function at low level, and is a side project, therefore I update code whenever I remember!
> I am constantly learning, lots of my University modules cover relating concepts. I may add written work that is wrong.
>


## The repo:

<p>This repo covers a basic virtual CPU, using basic set insctructions in the bytecode class. To further understand the function of a CPU at low-level, I added printing of the stack process to the console, allowing for visual understanding of what is happening. This whole project displays the fundamental utilisation of JVM, creating a basic virtual machine that can add, subract, multiply and divide numbers using the defined instruction set. The repo itself is to help me understand the basic process of coding instruction sets for a CPU, whilst allowing for error and testing of different code. Whilst I am confident with Java and Python, replicating Assembly-based situations within the language is very new to me.</p>

#### example:

```Java
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
```


## The code:

<p>The code itself displays how it can manually operate instrcutions through the use of methods and classes within Java. The instructions use The code itself will be soemwhat broken down in the local "Code" directory. On top of this, the "References" directory displays links to each site, or books, used whilst creating any code. This code isn't anything new, but I enjoy learning about low-level concepts, and the practical uses.</p>

#### Example:

```Java
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
```



> ### DISCLAIMER
>
> Any information I have used as a reference, or tutorial, is linked below. This repo covers an openly accessible subject, therefore many sites cover similar topics. The code is my own, regardless of tutorials, or bases.
> 

REFERENCES:
------

### Initial Tutorial followed:
[Youtube](https://www.youtube.com/watch?v=OjaAToVkoTw&pp=ygUZYmFzaWMgSlZNIHZpcnR1YWwgbWFjaGluZQ%3D%3D)
### Other:
[GeeksforGeeks](https://www.geeksforgeeks.org/central-processing-unit-cpu/)

[Computer Science Wiki](https://computersciencewiki.org/index.php/Architecture_of_the_central_processing_unit_(CPU))

[Medium](https://medium.com/swlh/an-introduction-to-jvm-bytecode-5ef3165fae70)

[Yale University](https://pclt.sites.yale.edu/cpu-instructions)

