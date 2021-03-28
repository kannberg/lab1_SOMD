package lab1_SOMD;

public class BedPosition implements java.io.Serializable{
	private Position pos;
	private Patient patient;
	
	public BedPosition(Position position) {
		this.pos = position;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}