package lab1_SOMD;

import java.io.*;
import java.util.*;

class ManagementSystem {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<Patient> patientList = new ArrayList<Patient>();
		List<Room> roomList = new ArrayList<Room>();
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to our Bed Management System BMS!");
		while (true) {
			System.out.println("What do you want to do?"
					+ "\n1) new Patient   2) show current Patients   3) new Room   4) show current Rooms   "
					+ "5) save data   6) load data   7) exit");
			String input = in.nextLine();
			if (Integer.valueOf(input) == 1) {
				AddNewPatient(patientList);
			}
			if (Integer.valueOf(input) == 2) {
				ShowCurrentPatients(patientList);
			}
			if (Integer.valueOf(input) == 3) {
				roomList.add(CreateNewRoom());
			}
			if (Integer.valueOf(input) == 4) {
				ShowCurrentRooms(roomList);
			}
			if (Integer.valueOf(input) == 5) {
				SavePatientList(patientList);
			}
			if (Integer.valueOf(input) == 6) {
				patientList = LoadPatientList();
			}
			if (Integer.valueOf(input) == 7) {
				//add some stuff
			}
		}
	}

	public static void SavePatientList(List<Patient> patientList) throws IOException {
		String fileName = "patientList.data";
		FileOutputStream fos = new FileOutputStream(fileName);
		try (ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(patientList);
			oos.close();
		} catch (NotSerializableException nse) {
			System.out.println("The data couldn't be saved!");
		} catch (IOException eio) {
			System.out.println("IOexception");
		}
		System.out.println("Data saved!");
		return;
	}

	public static List<Patient> LoadPatientList() throws IOException, ClassNotFoundException {
		String fileName = "patientList.data";
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);
		List<Patient> patientList = (List<Patient>) ois.readObject();
		ois.close();
		return patientList;
	}

	public static Room CreateNewRoom() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the room number:");
		String input = in.nextLine();
		int roomNum = findRoomNum(input);
		boolean finished = false;
		List<Patient> patientList = new ArrayList<Patient>();
		List<BedPosition> bedPosList = new ArrayList<BedPosition>();
		int i = 0;
		while (!finished) {
			i++;
			System.out.println("Where in this room are beds?\n1) WindowRight   2) WindowLeft   3) MiddleRight  "
					+ " 4) MiddleLeft   5) DoorRight   6) DoorLeft   7) Finish");
			input = in.nextLine();
			switch (input.charAt(0)) {
			case '1':
				System.out.println("Bed "+ i +" right of the window added");
				bedPosList.add(new BedPosition(Position.WindowRight));
				break;
			case '2':
				System.out.println("Bed "+ i +" left of the window added");
				bedPosList.add(new BedPosition(Position.WindowLeft));
				break;
			case '3':
				System.out.println("Bed "+ i +" in the middle right added");
				bedPosList.add(new BedPosition(Position.MiddleRight));
				break;
			case '4':
				System.out.println("Bed "+ i +" in the middle left added");
				bedPosList.add(new BedPosition(Position.MiddleLeft));
				break;
			case '5':
				System.out.println("Bed "+ i +" right of the door added");
				bedPosList.add(new BedPosition(Position.DoorRight));
				break;
			case '6':
				System.out.println("Bed "+ i +" left of the door added");
				bedPosList.add(new BedPosition(Position.DoorLeft));
				break;
			case '7':
				finished = true;
				break;
			}
		}
		Room room = new Room(roomNum, bedPosList);
		return room;

	}

	public static int findRoomNum(String input) {
		String roomNum = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 48 && input.charAt(i) <= 57) {
				roomNum += input.charAt(i);
			}
		}
		return Integer.parseInt(roomNum);
	}

	public static int findBedCount(String input) {
		String bedCount = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 48 && input.charAt(i) <= 57) {
				bedCount += input.charAt(i);
			}
		}
		return Integer.parseInt(bedCount);
	}

	public static void AddNewPatient(List<Patient> patientList) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the name and birthyear of the patient:");
		String input = in.nextLine();
		String name = findName(input);
		int yoB = findYoB(input);
		System.out.println("Is " + name + " private insured? [Y/N]");
		input = in.nextLine();
		boolean privInsurance = findInsurance(input);
		System.out.println("is " + name + " infectious? [Y/N]");
		input = in.nextLine();
		boolean infectious = findInfectious(input);
		Patient bob = new Patient(name, yoB, infectious, privInsurance);
		patientList.add(bob);
	}

	public static void ShowCurrentPatients(List<Patient> patientList) {
		if (patientList.size() == 0) {
			System.out.println("There are no patients!");
			return;
		}
		System.out.println("Name / Year of Birth / Private Insurance / infectious");
		for (int i = 0; i < patientList.size(); i++) {
			System.out.println(patientList.get(i).getName() + " / " + patientList.get(i).getYoB() + " / "
					+ patientList.get(i).getPrivInsurance() + " / " + patientList.get(i).getInfectious());
		}
		return;
	}

	public static void ShowCurrentRooms(List<Room> roomList) {
		if (roomList.size() == 0) {
			System.out.println("There are no rooms created!");
			return;
		}
		for (int i = 0; i < roomList.size(); i++) {
			System.out.println("Room: " + roomList.get(i).getNumber() + ", Number of Beds: " + roomList.get(i).getBedPos().size());
		}
	}

	private static String findName(String input) {
		String name = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 65 && input.charAt(i) <= 90 || input.charAt(i) >= 97 && input.charAt(i) <= 122) {
				name += input.charAt(i);
			}
		}
		return name;
	}

	private static int findYoB(String input) {// YoB = Year of Birth
		String yoB = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) >= 48 && input.charAt(i) <= 57) {
				yoB += input.charAt(i);
			}
		}
		return Integer.parseInt(yoB);
	}

	private static boolean findInsurance(String input) {
		if (input.charAt(0) == 89 || input.charAt(0) == 121) {
			return true;
		}
		if (input.charAt(0) == 78 || input.charAt(0) == 110) {
			return false;
		}
		return false;
	}

	private static boolean findInfectious(String input) {
		if (input.charAt(0) == 89 || input.charAt(0) == 121) {
			return true;
		}
		if (input.charAt(0) == 78 || input.charAt(0) == 110) {
			return false;
		}
		return false;
	}

}