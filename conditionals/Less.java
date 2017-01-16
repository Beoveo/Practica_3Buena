package conditionals;

import generate_bc.Compiler;
import conditionals.ConditionalJumps;
import conditionals.Ifle;
import exception.ArrayException;
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
		if(!op.equalsIgnoreCase("<")) return null;
		else return new Less(t1, t2);
	}
	
	@Override
	protected ConditionalJumps compiler(Compiler compiler)throws ArrayException {
		compiler.addByteCode(new Ifle());
		return new Ifle();
	}
	
	protected Term getTerm1 () { return this.t1;}
	protected Term getTerm2 () { return this.t2;}
	
	public String toString(){ return this.t1 + "<" + this.t2; }
}
