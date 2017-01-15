package conditionals;
import generate_bc.Compiler;
import conditionals.ConditionalJumps;
import conditionals.IfNeq;
import exception.ArrayException;
import analisis.Condition;
import analisis.LexicalParser;
import analisis.Term;

public class NotEqual extends Condition {
	private Term t1;
	private Term t2;
	public NotEqual(){}
	public NotEqual(Term term1, Term term2){
		this.t1 = term1;
		this.t2 = term2;
	}
	
	@Override
	protected Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser) {
		if(op != "!=") return null;
		else 
			lexParser.increaseProgramCounter(); // No se si esto va aqui
			return new NotEqual(t1, t2);
	}
	
	@Override
	protected ConditionalJumps compiler(Compiler compiler)throws ArrayException {
		compiler.addByteCode(new IfNeq());
		return new IfNeq();
	}
	
	public String toString(){ return this.t1 + "!=" + this.t2; }
}
