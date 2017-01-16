package conditionals;

import paquete.CPU;
import exception.ExceptionDivisionByZero;
import exception.StackException;
import bytecodes.ByteCode;

public abstract class Jump implements ByteCode{
	 protected int n;
	 
	 public Jump(int m){ this.n = m; }
	 
	 public ByteCode parse(String[] s){
		 if(s.length != 2) return null;
		 else return parseJump(s[1], n);
	 }
	 
	 protected abstract ByteCode parseJump(String s, int n);

	@Override
	public void execute(CPU cpu) throws ExceptionDivisionByZero, StackException {
		cpu.setProgramCounter(n);
	}

}
