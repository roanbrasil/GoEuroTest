package goeuro.devtest.config;

/**
 * Invalid Config class is responsable to throw exception when zero or more than
 * one arguments are passed
 * 
 * @author roanbrasil
 *
 */
public class InvalidConfig implements Config {

	@Override
	public String getCommand() {
		throw new UnsupportedOperationException(
				"It's necessary just one argument, it's invalid to zero arguments or more than one argument");
	}

	@Override
	public boolean isValid() {
		return false;
	}

}
