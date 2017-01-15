package commands;


import java.io.IOException;

import exception.ArrayException;
import exception.BadFormatByteCodeException;
import exception.ExceptionDivisionByZero;
import exception.LexicalAnalisisException;
import exception.StackException;
import paquete.Engine;

/** 
 * CLASS COMMAND:
 * Esta clase maneja los comandos de la maquina virtual.
 * Dentro de dichos comandos estan los bytecodes almacenados.
 * Ademas se encarga de ejecutar los comandos llamando a engine.
 *
 */
public interface Command {
	void execute(Engine engine) throws LexicalAnalisisException, ArrayException, IOException, ExceptionDivisionByZero, StackException, BadFormatByteCodeException;
	Command parse(String[] s);
	String textHelp();	
}