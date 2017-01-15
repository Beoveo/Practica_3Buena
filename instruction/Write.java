package instruction;


import exception.ArrayException;
import generate_bc.Compiler;
import bytecodes.Load;
import bytecodes.Out;
import analisis.LexicalParser;


public class Write implements Instruction {
	private String varName;
	
	public Write(){}
	public Write(String variable) {
		this.varName = variable;
	}

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		String varName;
		if(words.length != 2) return null;
		else{
			if(!words[0].equalsIgnoreCase("write")) return null;
			else {
				varName = words[1];
				if (varName.length() !=1) return null;
				 else {
					 char name = varName.charAt(0);
					 if ('a' <= name && name <= 'z') {
						 lexParser.increaseProgramCounter();
						 return new Write(varName); 
					 } else return null;
				 }
			}
		}
	}
	
	@Override
	public void compile(Compiler compiler) throws ArrayException {
		int index = compiler.getIndex(this.varName); 
		compiler.addByteCode(new Load(index)); 
		compiler.addByteCode(new Out());
	}

	
	public String toString(){
		return "write " + this.varName;
	}
	
}
