package program;

import exception.ArrayException;

public class SourceProgram {
	private static final int MAX_SOURCE_PROGRAM = 100;
	private String[] sProgram;
	private int numInstruction;
	
	public SourceProgram(){
		this.sProgram = new String[MAX_SOURCE_PROGRAM];
		this.numInstruction = 0;
	}
	 
	public void addSourceProgram(String line)throws ArrayException{
		if(this.numInstruction == MAX_SOURCE_PROGRAM - 1) throw new ArrayException("Error: Ha ocupado todas las posiciones del Programa Fuente");
		sProgram[this.numInstruction] = line;
		this.numInstruction++;
	}
	 
	public String getInstruction(int programCounter) throws ArrayException{
		if(this.numInstruction == 0) throw new ArrayException("Error: Intruccion no encontrada");
		else{
			return this.sProgram[programCounter];
			}
		
	}

	public int getNumeroInstrucciones() {
		return this.numInstruction;
	}
}
