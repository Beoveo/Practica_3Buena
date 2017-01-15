package generate_bc;

import exception.ArrayException;
import instruction.Instruction;
import program.ParsedProgram;
import bytecodes.ByteCode;
import bytecodes.ByteCodeProgram;

public class Compiler {
	 private ByteCodeProgram bcProgram;
	 private String[] varTable; //guarda las variables en una pos de memoria
	 private int numVars;
	
	 
	 public void compile(ParsedProgram pProgram) throws ArrayException {
		 int i = 0;
		 
		 while (i < pProgram.getNumInstr()){
		 		Instruction instr = pProgram.getInstr(i);
		 		instr.compile(this);
		 		i++;
		 	}
	 }
	 
	 //throws
	 public void addByteCode(ByteCode b)throws ArrayException {bcProgram.insertarByteCode(b);}
	 
	 
	 public int getIndex(String varName) {
		 int pos = 0;
		 boolean find = false;
		 while(pos < this.numVars && !find){
			 if(this.varTable[pos].equalsIgnoreCase(varName)){
				 find = true;
				 return pos;
			 }else pos++;
		 }
		 
		 if(pos == this.numVars){
			 this.numVars++;
			 varTable[this.numVars] = varName;
			 pos = this.numVars - 1;
		 }
		return pos;
	 }
	 
	 public int getCurrentNumOfBc() {return bcProgram.getNumBC();} //tamano del programa entero, hasta el END

	
	 
}

