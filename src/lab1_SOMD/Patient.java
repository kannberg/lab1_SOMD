package lab1_SOMD;

public class Patient implements java.io.Serializable{
	private String name;
	private int yoB;
	private boolean infectious;
	private boolean privInsurance;

	public Patient(String name, int yoB, boolean infectious, boolean privInsurance) {
		this.name = name;
		this.yoB = yoB;
		this.infectious = infectious;
		this.privInsurance = privInsurance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYoB() {
		return this.yoB;
	}

	public void setYoB(int yoB) {
		this.yoB = yoB;
	}

	public void setInfectious(boolean infectious) {
		this.infectious = infectious;
	}

	public boolean getInfectious() {
		return this.infectious;
	}

	public void setPrivInsurance(boolean privInsurance) {
		this.privInsurance = privInsurance;
	}

	public boolean getPrivInsurance() {
		return this.privInsurance;
	}
}