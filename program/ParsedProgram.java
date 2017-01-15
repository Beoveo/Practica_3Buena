package program;


import instruction.Instruction;
import exception.ArrayException;

public class ParsedProgram {
	private static final int MAX_INSTR = 100;
	private Instruction[] pProgram;
	private int numInstrParsed;
	
	public ParsedProgram(){
		this.pProgram = new Instruction[ParsedProgram.MAX_INSTR]; 
		this.numInstrParsed = 0;
	}
	
	/**
	 * Añade una instruccion al array de instrucciones si la pos esta vacia y si no ha llegado al maximo del array
	 * @param instr: La instruccion a insertar.
	 */
	public void addInstruction(Instruction instr) throws ArrayException{
		if(pProgram[this.numInstrParsed] != null || this.numInstrParsed == ParsedProgram.MAX_INSTR - 1) throw new ArrayException("Error: Ha ocupado todas las posiciones del Parsed Program");
		else{
			pProgram[this.numInstrParsed] = instr;
			this.numInstrParsed++;
		} 
	}
	
	/**
	 * Devuelve el numero de instrucciones del programa parseado
	 * @return
	 */
	public int getNumInstr() { return this.numInstrParsed; }
	/**
	 * Devuelve la instruccion del array tipo Instruction[] que se encuentra en la pos indicada.
	 * @param pos: Pos de la instruccion solicitada.
	 * @return Devuelve la instruccion.
	 */
	public Instruction getInstr(int pos) { return this.pProgram[pos]; }
}
