package instruction;



import bytecodes.Store;
import exception.ArrayException;
import analisis.LexicalParser;
import analisis.Term;
import analisis.TermParser;
import generate_bc.Compiler;


public class SimpleAssignment implements Instruction{
	 private String varName;
	 private Term rhs;
	 
	 public SimpleAssignment() {}
	 public SimpleAssignment(String varName2, Term rhs2) {
		this.varName = varName2;
		this.rhs = rhs2;
	}

	

	public Instruction lexParse(String[] words, LexicalParser lexParser){
		if (words.length != 3) return null;
		 else {
			Term term = TermParser.parse(words[0]);
			if(term == null || words[1].equalsIgnoreCase("=")) return null;
			else{ 
				 Term rhs = TermParser.parse(words[2]);
				 if(rhs == null) return null;
				 else {
					 lexParser.increaseProgramCounter();
					 return new SimpleAssignment(words[0], rhs);
				 } 
			 }
		 }
	}

	 
	 public void compile(Compiler compiler) throws ArrayException{ 
		 this.rhs.compile(compiler);
		 int pos = compiler.getIndex(this.varName);
		 compiler.addByteCode(new Store(pos)); 
	 }
	 
	 public String toString(){
		 return this.varName + " = " + this.rhs; 
	 }
	
}
