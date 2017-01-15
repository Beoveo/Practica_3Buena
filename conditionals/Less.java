package conditionals;

import generate_bc.Compiler;
import analisis.Condition;
import analisis.LexicalParser;
import analisis.Term;

public class Less extends Condition {
	private Term t1;
	private Term t2;
	public Less(){}
	public Less(Term term1, Term term2){
		this.t1 = term1;
		this.t2 = term2;
	}
	
	@Override
	protected Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser) {
		if(op != "<") return null;
		else 
			lexParser.increaseProgramCounter(); // No se si esto va aqui
			return new Less(t1, t2);
	}
	
	@Override
	protected ConditionalJumps compiler(Compiler compiler) {
		compiler.addByteCode(new Ifle());
		return new Ifle();
	}
	
	public String toString(){ return this.t1 + "<" + this.t2; }
}