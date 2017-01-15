package analisis;


import conditionals.ConditionalJumps;
import exception.ArrayException;
import generate_bc.Compiler;

public abstract class Condition {
	 private Term t1;
	 private Term t2;
	 protected ConditionalJumps condition; //para la compilacion 
	 
	 public Condition(){};
	 public Condition (Term term1, ConditionalJumps condJump, Term term2){
		 this.t1 = term1;
		 this.condition = condJump;
		 this.t2 = term2;
	 }
	 
	 public Condition parse(String term1, String op, String term2, LexicalParser parser){// PArseador de las condiciones x > y
		 this.t1 = TermParser.parse(term1);
		 this.t2 = TermParser.parse(term2);
		 return parseOp(t1, op, t2, parser);
	 }

	 public void compile(Compiler compiler) throws ArrayException{
		 this.t1.compile(compiler); //genera los bc de los terminos 1 y 2
		 this.t2.compile(compiler);
		 condition = compiler(compiler); //genera los bc de la condicion que sea
		 int n = compiler.getCurrentNumOfBc();
		 setJump(n);
	 }
	
	 protected abstract Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser);
	 protected abstract ConditionalJumps compiler(Compiler compiler);
	 
	 public void setJump (int jump){ this.condition.setJump(jump); }
	
			 
}


