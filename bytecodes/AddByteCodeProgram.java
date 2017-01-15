package bytecodes;

import commands.Command;
import exception.ArrayException;
import paquete.Engine;
/**
 * CLASE ADD_BYTECODE_PROGRAM:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class AddByteCodeProgram implements Command {
	/**
	 * Metodo que ejecuta el comando.
	 */
	@Override
	public void execute(Engine engine) throws ArrayException{
		engine.readByteCodeProgram();
	}
	
	/**
	 * Metodo que parsea el comando y comprueba cual es de
	 * todos los disponibles.
	 */
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("BYTECODE")) return null;
	else return new AddByteCodeProgram();
	}
	
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
		return " BYTECODE: Permite introducir un programa " +
	System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "BYTECODE" + System.getProperty("line.separator")
				+ "Introduzca el bytecode. Una instruccion por linea:";
	}
}
