package commands;

import exception.BadFormatByteCodeException;
import paquete.Engine;

/**
 * CLASE REPLACE:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class Replace implements Command {
	protected int param;
	

	public Replace() {}
	public Replace(int p){
		this.param = p;
	}
	/**
	 * Metodo que ejecuta el comando.
	 */
	@Override
	public void execute(Engine engine) throws BadFormatByteCodeException{
		engine.replace(this.param);
	}
	
	/**
	 * Metodo que ya tiene la linea divida en dos palabras, la primera es el nombre de la
	 * instruccion y la segunda es el parametro/valor que usara.
	 * Si coincide con la instruccion devuelve un nuevo objeto de esta, sino devuelve null.
	 */
	public Command parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("REPLACE")|| param < 0) return null;
		else return new Replace(param);
	}
	
	/**
	 * Metodo que parsea el comando.
	 */
	@Override
	public Command parse(String[] s) {
		if (s.length!=2) return null;
	else return parseAux(s[0], s[1]);
	}
	
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
		return "REPLACE: Ejecuta el programa." +
	System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "REPLACE";
	}
	
	
}
