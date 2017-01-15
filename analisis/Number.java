package analisis;

import bytecodes.ByteCode;
import bytecodes.Push;
import generate_bc.Compiler;

public class Number implements Term {
	protected int number;
	
	public Number(){}
	public Number(int num) {
		this.number = num;
	}
	@Override
	public Term parse(String term) {
		if (term.length() !=1) return null;
		 else {
			this.number = Integer.parseInt(term);
			return new Number(this.number);
		  }
	 }

	public ByteCode compile(Compiler compiler) {
		 compiler.addByteCode(new Push(this.number));
		 return new Push(this.number);
	}

	public String toString(){
		String s = "" + this.number;
		return s;}
}