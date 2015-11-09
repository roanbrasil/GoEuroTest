package goeuro.devtest.config;

/**
 * DefaultConfig class that receive arguments (command) on command line
 * @author roanbrasil
 *
 */
public class DefaultConfig implements Config {
	
	private String command;
	
	public DefaultConfig(String command) {
		this.command = command;
	}


	@Override
	public String getCommand() {
		return command;
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
