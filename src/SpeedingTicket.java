import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * This program provides the logic behind my program speeding ticket. 
 * This program will take inputed values by the user and determine if they are speeding and what punishment is given. 
 * @author Jason Grimes s5069580
 *
 */
public class SpeedingTicket {
	private String driverName; //this is where I declare all of my variables.
	private int speedValue, speedLimit, points;
	private PrintWriter out = null; 	//This is where i declare my print writer to be used to write to a file.

	Scanner Input = new Scanner(System.in); // This is where I make my scanner for the user input.
	/**
	 * This method will take the user input and set it as SpeedValue to be used in the speedTest method.
	 * @return This returns speedValue so that it can be used in the speedTest method.
	 */
	public int SpeedValue() {
		speedValue = Input.nextInt(); // This is the method i used to scan and set the user input as the speedValue.
		return speedValue;

	}
	/**
	 * This method will take the user input and set it as speedLimit to be used in the speedTest method.
	 * @return This returns speedLimit so that it can be used in the speedTest method.
	 */
	public int speedLimit() {
		speedLimit = Input.nextInt(); 
		return speedLimit;
	}

	/**
	 * This method will take the speedValue and SpeedLimit from the user and compare these and determine how much faster the user was going to the speed Limit.
	 * Depending on the difference in speed the if statement will determine what bracket the user falls under (1-5mph 5-9mph 10-14mph 15-20mph or under) and the outcome will be given a value and returned.
	 * I used an if statement that goes through the different brackets of punishment depending on how much you are speeding by.
	 * The outcome string had to be declared at the start with "" but the value changes depending on the values entered as determined by the if statement.
	 * 
	 * @param speedValue This is the speed the person was travelling at and will be compared to the speed limit parameter.
	 * @param speedLimit This is the speed limit that is set with the users input and will be used to compare the speed the user was going.
	 * @return outcome This is the outcome that will be set and returned depending on the condition reached in the if statement.
	 */
	public String speedTest(int speedValue, int speedLimit) 
	{
		this.speedValue = speedValue;
		this.speedLimit = speedLimit;
		String Outcome = "";  
		if (speedValue >= speedLimit + 1 && speedValue <= speedLimit + 4) { 
			Outcome = ("Speed Limit broken written warning"); 
		} 
		else if (speedValue >= speedLimit + 5 && speedValue <= speedLimit + 9) {
			Outcome = ("Speed Limit broken by 5 or more MPH fined £50");
		}

		else if  (speedValue >= speedLimit + 10 && speedValue <= speedLimit + 14) {
			Outcome = ("Speed Limit broken by 10 or more MPH fined £100"); 
		}
		else if (speedValue >= speedLimit + 15 && speedValue <= speedLimit + 19) {
			Outcome = ("Speed Limit broken by 15 or more MPH fined £150 and 3 points on your license");
			points = 3; 
		}

		else if (speedValue >= speedLimit + 20)
			Outcome = ("you are 20 MPH or more above the speed limit £200 fine and disqualifcation of license");

		else {
			Outcome = ("You are under the speed limit.");

		}
		return Outcome;

	}
	/**
	 * This getter will make the variable available to other classes.
	 * @return returns the current value of speedLimit
	 */
	public int getspeedLimit() {
		return speedLimit;

	}
	/**
	 * This setter will make it possible for other classes to edit the value of speedLimit.
	 * @param speedLimit speedLimit is given a value from the input on the GUI.
	 */
	public void setspeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;

	}
	/**
	 * This getter will make the variable available to other classes.
	 * @return returns the current value of speedValue
	 */
	public int getspeedValue() {
		return speedValue;

	}
	/**
	 * This setter will make it possible for other classes to edit the value of speedValue.
	 * @param speedValue speedValue is given a value from the input on the GUI.
	 */
	public void setspeedValue(int speedValue) {
		this.speedValue = speedValue;
	}
	/**
	 * This getter will make the variable available to other classes.
	 * @return returns the current value of driverName.
	 */
	public String getdriverName() {
		return driverName; 
	}
	/**
	 * This setter will make it possible for other classes.
	 * @param driverName driverName is given a value from the input on the GUI.
	 */
	public void setdriverName(String driverName) {
		this.driverName = driverName;
	}
/**
 * This method will take the input from the user and store it in the file driverRecords.
 * It will then take the name of the driver and store it in driver records and add 3 points on if they are caught speeding between 15-19mph.
 * @throws IOException This will output and error if it fails to locate/create the file.
 */
	public void storeRecords() throws IOException{


		out = new PrintWriter(new BufferedWriter (new FileWriter ("driverRecords.txt",true)));

		Scanner fr = new Scanner (new File("driverRecords.txt"));

		String newText = "";
		boolean exists = false;
		String line = "", fileText = "";

		while(fr.hasNext()){
			String name = "";
			line = fr.nextLine();
			fileText = line;
			name = line.substring(0,driverName.length()); // this will take the driverName and set that as name and split it from the points so that we can check if it is the same person.
			if	(driverName.equals(name)) {
				System.out.println(driverName + " " + name );
				newText = fileText.replaceAll(driverName + " " + points, driverName + " " + (points +3)); //This is will replace the old points with the new points.
				System.out.println("person in file");
				out.println(newText);
				exists = true;
			}
			
		}
		if	(exists == false) {
			out.println(driverName + " "+points);
			System.out.println("doesn't exist");
			System.out.println(driverName + " " + points);
		}
		

	}
		public void closeFile() {
			out.close();
		}

	//String record = driverName+" "+Integer.toString(points);
	//	out.write(driverName+" "+Integer.toString(points));


	
}







