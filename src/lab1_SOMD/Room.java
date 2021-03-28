package lab1_SOMD;

import java.util.*;

public class Room implements java.io.Serializable {
	private int number;
	private boolean singleRoom;
	private boolean infectious;
	private List<BedPosition> bedPosList;

	public Room(int roomNum, List<BedPosition> bedPosList) {
		this.number = roomNum;
		this.bedPosList = bedPosList;
		if (bedPosList.size() == 1) {
			this.singleRoom = true;
		}
		this.infectious = false;
	}

	int getNumber() {
		return this.number;
	}

	void addBed(BedPosition bed) {
		this.bedPosList.add(bed);
	}

	int patientCount() {
		return -1;
	}

	boolean hasInfections() {
		return false;
	}

	List<BedPosition> getBedPos() {
		return this.bedPosList;
	}

	void addBedPos(BedPosition bedPosition) {
		this.bedPosList.add(bedPosition);
	}
}