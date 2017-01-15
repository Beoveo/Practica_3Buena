package instruction;


import exception.ArrayException;
import program.ParsedProgram;
import analisis.Condition;
import analisis.ConditionParser;
import analisis.LexicalParser;
import generate_bc.Compiler;


public class IfThen implements Instruction {
	private Condition condition;
	private ParsedProgram ifBody;
	
	public IfThen(){}
	public IfThen(Condition cond, ParsedProgram body) {
		this.condition = cond;
		this.ifBody = body;
	}
	
	public Instruction lexParse(String[] words, LexicalParser lexParser){
		 if(words.length != 4) return null;
			else{
				if(!words[0].equalsIgnoreCase("if")) return null;
				else {
					Condition cond = ConditionParser.parse(words[1], words[2], words[3], lexParser);
					if(cond == null) return null;
					else{
						lexParser.increaseProgramCounter();
						return new IfThen(cond, ifBody);
					}
				}
			}
	 }

	public void compile(Compiler compiler) throws ArrayException{
		//esto da valor a ConditionalJump de condition
		this.condition.compile(compiler);
	    compiler.compile(this.ifBody);
		int jump = compiler.getCurrentNumOfBc(); //valor del prog despues de compilar body
		this.condition.setJump(jump);
	}

	
}	
