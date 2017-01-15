package paquete;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import program.ParsedProgram;
import program.SourceProgram;
import analisis.LexicalParser;
import bytecodes.ByteCode;
import bytecodes.ByteCodeParser;
import bytecodes.ByteCodeProgram;
import commands.Command;
import commands.CommandParser;
import exception.ArrayException;
import exception.BadFormatByteCodeException;
import exception.ExceptionDivisionByZero;
import exception.LexicalAnalisisException;
import exception.StackException;
import generate_bc.Compiler;


/** 
 *CLASS ENGINE:
 * En la clase engine se introducira el comando por teclado, despues se parseara y se comprobara que esta bien escrito y
 * si esto se cumple se ejecutara llamando a executeCommand (que a su vez llamara a engine).
 * Una vez ejecutado si es correcta la ejecuccion se mostrara el programa almacenado, sino se dara error.
 */
	public class Engine {
		private SourceProgram sProgram = new SourceProgram();
		private ParsedProgram parsedProgram;
		private ByteCodeProgram bcProgram;
		private ByteCode bc;
		private boolean end;
		private static Scanner in;
		private CPU cpu;
		private Command comando = null;
		
		/**
		 * La constructora engine inicializa el booleano end que usaremos en el metodo quit.
		 * Ademas inicializa un nuevo objeto del tipo ByteCodeProgram, 
		 * que usaremos para mostrar el programa almacenado por pantalla.
		 */
		public Engine(){
			this.end = false;
			this.bcProgram = new ByteCodeProgram();
		}
		
		public void LoadFichero(String archivo) throws IOException{
			String line;
			FileReader fr = new FileReader(archivo);
			in = new Scanner(fr);
			line = in.nextLine();
			while(line.equalsIgnoreCase("END")){
				this.sProgram.addSourceProgram(line);
			}
		}
		
		/**
		 * Metodo que lee todo el programa de instrucciones bytecode siempre que sean correctas, que el programa no este lleno
		 *  y hasta que se introduzca por teclado la palabra END. 
		 * @return Devuelve un nuevo objeto de la CPU con ese programa de bytecodes.
		 */
		public boolean readByteCodeProgram(){
			boolean lleno = false;
			String line = " ";	
			while(!line.equalsIgnoreCase("END") && !lleno){
				line = in.nextLine();
				line =  line.toUpperCase();
				if(!line.equalsIgnoreCase("END")){
					bc = ByteCodeParser.parse(line);
					if(bc == null) System.out.println("Error : Introduzca un bytecode correcto.");
					else{
						lleno = bcProgram.insertarByteCode(bc);
						if(lleno) System.out.println("Error: A alcanzado el limite de instrucciones, o la instruccion esta ocupada.");
				}
			}
		}
		cpu = new CPU(bcProgram);
		return lleno;
		}
		
		/**
		 * Metodo que lee el comando a ejecutar y si se corresponde con los disponibles se ejecuta y se muestra el programa almacenado.
		 * Si el comando es null, vuelve a pedir un comando.
		 * @throws LexicalAnalisisException 
		 * @throws IOException 
		 * @throws ArrayException 
		 * @throws BadFormatByteCodeException 
		 * 
		 */
		public void start() throws ExceptionDivisionByZero, StackException, ArrayException, IOException, LexicalAnalisisException, BadFormatByteCodeException{
			this.end = false;
			String line = " ";
				while(!end){
				System.out.println(">"); 
				line = in.nextLine();
				line =  line.toUpperCase();
				comando = CommandParser.parse(line); 
				if(comando == null) System.out.println("Error: Vuelva introducir el comando.");
				else {
					System.out.println("Comienza la ejecucion de " + comando.toString());
					comando.execute(this);
					if (bcProgram.getNumBC() != 0){
						System.out.println("Programa almacenado: " + 
								System.getProperty("line.separator") + bcProgram.toString());
						}else System.out.println("Introduzca al menos una instruccion bytecode.");
				}
			}
			in.close();
		}
		
		/**
		 * Este metodo resetea el programa y la cpu, es decir, resetea toda la maquina virtual.
		 * Se ejecuta cuando llamamos a RESET.
		 * @return Devuelve true si se ha ejecutado.
		 */
		public boolean resetProgram(){ 
			bcProgram.reset();
			cpu.reset();
			return true;
		}
		
		/**
		 * Este metodo cambia el valor del booleano global end a true, lo que significa que se va ha ejecutar quit.
		 * @return Devuelve el nuevo valor de end.
		 */
		public boolean endExecution(){ return this.end = true;}
		
		
		 /**
		  * El metodo executeCommandRun ejecuta el comando RUN.
		  * Este ejecutarÃ¡ en la CPU cada bytecode almacenado en bcProgram e 
		  * irÃ¡ mostrando por pantalla el estado de la cpu, siempre y cuando no se ejecute HALT.
		  * Si se ejecuta HALT la maquina ejecutara todas las instrucciones hasta esta, en la cual se para.
		  * Una vez salga del bucle, se llama a resetHalt(), un metodo que vuelve a poner halt() a false.
		  * @return Devuelve si se ha ejecutado el comando RUN.
		  */
		public boolean executeCommandRun() throws ExceptionDivisionByZero, StackException {
			boolean exeOk;
			exeOk = cpu.run();
			System.out.println("El estado de la maquina tras ejecutar el programa es:" +
								System.getProperty("line.separator"));
			System.out.println(cpu.toString());
			cpu.reset();
			cpu.resetHalt();
			return exeOk; 
}

		/**
		 * Metodo que ejecuta el comando HELP. Muestra la ayuda por pantalla.
		 * @return Devuelve ok si se ha podido mostrar.
		 */
		public boolean help(){
			boolean ok = true;
			CommandParser.showHelp();
			return ok;
		}

		/**
		 * El metodo replace comprueba que la pos sea valida, que exista el programa de instrucciones 
		 * y que no reemplacemos en una pos vacia.
		 * Si se cumplen estas condiciones, se muestra por pantalla el mensaje "Nueva instruccion: ", se lee por teclado
		 * la instruccion a reemplazar y se llama al metodo que lo reemplaza.
		 * @param replace Es la pos en la cual se encuentra la instruccion a reemplazar.
		 * @return Devuelve si se ha podido realizar el cambio.
		 */
		public boolean replace(int replace) throws BadFormatByteCodeException{
			String line;
			boolean replaceOK = false;
			if (cpu.getNumBC() > 0 && cpu.getByteCode(replace) != null){
				replaceOK = true;
			System.out.println("Nueva instruccion: ");
			line = in.nextLine();
			bc = ByteCodeParser.parse(line);
			if(bc == null) throw new BadFormatByteCodeException("Error: No se pudo parsear el bytecode.");
			cpu.replaceBC(replace, bc);
			}
			return replaceOK;
		}


		public void compile() throws LexicalAnalisisException, ArrayException {
			 //try {
			    this.lexicalAnalysis();
			    this.generateByteCode();
			// }
			//catch ... 
		}

		private void generateByteCode() throws ArrayException{
			Compiler comp = new Compiler();
			comp.compile(this.parsedProgram);
		}

		private void lexicalAnalysis() throws LexicalAnalisisException {
			LexicalParser lexicalParser;
			lexicalParser = new LexicalParser(this.sProgram);
			lexicalParser.lexicalParser(this.parsedProgram, "END");
		}
}