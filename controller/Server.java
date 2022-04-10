package controller;

public class Server {
	private String code;

	public String getCode() {
		return code;
	}

	public boolean connectToNetwork() {
		// true if connected, false if failed
		return true;
	}

	public boolean checkForCode() {
		return true;
	}
}
