package commands;

import java.io.IOException;

import paquete.Engine;

public class LoadFich implements Command {
	protected String fich;
	public LoadFich(){}
	public LoadFich (String fichero){
		this.fich = fichero;
	}
	
	@Override
	public void execute(Engine engine) throws IOException {
		engine.LoadFichero(fich);
	}

	@Override
	public Command parse(String[] s) {
		if (s.length!=2 || !s[0].equalsIgnoreCase("loadfich")) return null;
		else return new LoadFich(s[1]);
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

