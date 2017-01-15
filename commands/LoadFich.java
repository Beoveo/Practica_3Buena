package commands;


import java.io.FileNotFoundException;

import exception.ArrayException;

import paquete.Engine;

public class LoadFich implements Command {
	protected String fich;
	public LoadFich(){}
	public LoadFich (String fichero){
		this.fich = fichero;
	}
	
	@Override
	public void execute(Engine engine) throws ArrayException, FileNotFoundException {
		engine.LoadFichero(fich);
	}

	@Override
	public Command parse(String[] s) {
		if (s.length!=2 || !s[0].equalsIgnoreCase("LOADFICH")) return null;
		else{
			s[1].toLowerCase();
			return new LoadFich(s[1]);
				
		}
	}

	@Override
	public String textHelp() {
		return "LOADFICH: Carga el fichero." +
				System.getProperty("line.separator");
	}
	
	public String toString(){
		return "LOAD" + this.fich;
	}
}

