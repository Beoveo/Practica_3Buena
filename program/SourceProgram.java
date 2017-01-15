package program;


public class SourceProgram {
	private static final int MAX_SOURCE_PROGRAM = 100;
	private String[] sProgram;
	private int numInstruction;
	
	public SourceProgram(){
		this.sProgram = new String[MAX_SOURCE_PROGRAM];
		this.numInstruction = 0;
	}
	 
	public void addSourceProgram(String line){
		sProgram[this.numInstruction] = line;
		this.numInstruction++;
	}
	 
	public String getInstruction(int programCounter) {
		return this.sProgram[programCounter];
	}

	public int getNumeroInstrucciones() {
		return this.numInstruction;
	}
}