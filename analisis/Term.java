package analisis;

import bytecodes.ByteCode;
import generate_bc.Compiler;

public interface Term {
	Term parse(String term);
	ByteCode compile(Compiler compiler);
}
