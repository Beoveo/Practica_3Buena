package instruction;



import conditionals.GoTo;
import analisis.Condition;
import analisis.ConditionParser;
import analisis.LexicalParser;
import program.ParsedProgram;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import generate_bc.Compiler;

public class While implements Instruction{
	private Condition condition;
	private ParsedProgram whileBody;
	
	public While(){}
	public While(Condition cond, ParsedProgram wBody){
		this.condition = cond;
		this.whileBody = wBody;
	}
	 
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException{
		if(words.length != 4) return null;
		else {
			if(!words[0].equalsIgnoreCase("WHILE")) return null;
			else {
				Condition cond2 = ConditionParser.parse(words[1], words[2], words[3], lexParser);
				ParsedProgram wBody = new ParsedProgram();
				lexParser.lexicalParser(wBody, "ENDWHILE");
				lexParser.increaseProgramCounter();
				return new While(cond2, wBody);
			}
		}
	 }
 

	 
	 public void compile(Compiler compiler) throws ArrayException{
		 int nProg = compiler.getCurrentNumOfBc(); // num de bc antes de compilar while
		 
		 this.condition.compile(compiler);
		 compiler.compile(this.whileBody);
		 
		 int m = compiler.getCurrentNumOfBc() + 1; //salta a la instr despues del endWhile
		 
		 this.condition.setJump(m);
		 compiler.addByteCode(new GoTo(nProg));
	 }
	 
}