package analisis;


import conditionals.ConditionalJumps;
import exception.ArrayException;
import generate_bc.Compiler;

public abstract class Condition {
	 private Term t1;
	 private Term t2;
	 protected ConditionalJumps condition; //para la compilacion 
	 private int jump;
	 
	 public Condition(){};
	 public Condition (Term term1, ConditionalJumps condJump, Term term2){
		 this.t1 = term1;
		 this.condition = condJump;
		 this.t2 = term2;
	 }
	 
	 public Condition parse(String term1, String op, String term2, LexicalParser parser){// PArseador de las condiciones x > y
		 this.t1 = TermParser.parse(term1);
		 this.t2 = TermParser.parse(term2);
		 return parseOp(this.t1, op, this.t2, parser);
	 }

	 public void compile(Compiler compiler) throws ArrayException{
		 getTerm1().compile(compiler); //genera los bc de los terminos 1 y 2
		 getTerm2().compile(compiler);
		 condition = compiler(compiler); //genera los bc de la condicion que sea
	 }
	
	 protected abstract Term getTerm1();
	 protected abstract Term getTerm2();
	 protected abstract Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser);
	 protected abstract ConditionalJumps compiler(Compiler compiler) throws ArrayException;
	 
	 public void setJump (int j){ this.jump = j; }
	
	public String toString(){
		return "" + this.t1 + this.condition + this.t2;
	}
}


