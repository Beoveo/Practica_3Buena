package analisis;

import instruction.Instruction;
import instruction.InstructionParser;
import exception.LexicalAnalisisException;
import program.ParsedProgram;
import program.SourceProgram;



public class LexicalParser {
	private SourceProgram sProgram;
	private int programCounter;
	
	public LexicalParser(SourceProgram scProg){
		this.sProgram = scProg;
		this.programCounter = 0;
	}
	 
	public void lexicalParser(ParsedProgram pProgram, String stopKey)
	throws LexicalAnalisisException {
		String []line;
		boolean stop = false;
		while (this.programCounter < sProgram.getNumeroInstrucciones() && !stop){
		String instr = sProgram.getInstruction(this.programCounter);
		line = instr.split(" +");
			if (instr.equalsIgnoreCase(stopKey)){
				stop = true;
			}
			else {
			Instruction instruction = InstructionParser.parse(line, this);
			pProgram.addInstruction(instruction);
			}
		}
	 }
	
	public int getProgramCounter(){ return this.programCounter;}
	public void increaseProgramCounter(){ this.programCounter++;}
}