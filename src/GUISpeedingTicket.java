import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

/** 
 * Speeding Ticket Program GUI
 * This program is linked to my other program speeding ticket and will provide the GUI for it.
 * @author Jason Grimes s5069580
 */
public class GUISpeedingTicket {
	private JLabel lblTotalPoints, lblDriverName, lblSpeedTraveled, lblSpeedLimit, lbloutcome; //This is where i declare all private variables in the GUI.
	private JTextField txtDriverName, txtSpeedTraveled, txtSpeedLimit;
	private JPanel panel;
	private JFrame frame; 
	private JButton btnCalculate, btnExit;
	private SpeedingTicket sT = new SpeedingTicket(); // This is where I create an object so I can use the SpeedingTicket class.

/**
 * This method places everything made in the GUI program onto the frame.
 */
	public GUISpeedingTicket() {
		createPage();
		labelstxt();
		buttons();
		frame.add(panel);
		frame.setVisible(true);
	}
/**
 * This method is where I create the frame and panel and set the sizes.
 */
	public void createPage() {
		frame = new JFrame("Basic Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color (0,0,0));
	}

/**
 * This method is where I create all of the labels and text boxes as well as set where they go what they say and the color of them.
 */
	public void labelstxt() {
		
		
		lblDriverName = new JLabel("Please Insert The Name Of The Driver.");
		lblDriverName.setBounds(10,50,250,20);
		panel.add(lblDriverName);
		lblDriverName.setForeground(Color.WHITE);
		
		lblSpeedTraveled = new JLabel("Please Insert The Speed You Were Traveling At.");
		lblSpeedTraveled.setBounds(10,90,300,20);
		panel.add(lblSpeedTraveled);
		lblSpeedTraveled.setForeground(Color.WHITE);

		txtSpeedTraveled = new JTextField(3);
		txtSpeedTraveled.setBounds(10,115,150,20);
		panel.add(txtSpeedTraveled);

		lblSpeedLimit = new JLabel("Please Insert The Speed Limit Of The Road.");
		lblSpeedLimit.setBounds(10,140,300,20);
		panel.add(lblSpeedLimit);
		lblSpeedLimit.setForeground(Color.WHITE);
		
		txtSpeedLimit = new JTextField(3);
		txtSpeedLimit.setBounds(10,165,150,20);
		panel.add(txtSpeedLimit);
		
		lbloutcome = new JLabel();
		lbloutcome.setBounds(10,250,500, 20);
		lbloutcome.setForeground(Color.WHITE);
		panel.add(lbloutcome);
		
		
		lblDriverName = new JLabel();
		lblDriverName.setBounds(10,25,150,20);
		panel.add(lblDriverName);
		
		txtDriverName = new JTextField(15);
		txtDriverName.setBounds(10,70,150,20);
		panel.add(txtDriverName);
		
		lblTotalPoints = new JLabel();
		lblTotalPoints.setBounds(10,145,150,20);
		panel.add(lblTotalPoints);
		
		}
	/*
	 * This is the method I create my buttons in and link them to the appropriate action listener so they function correctly.
	 */
	public void buttons() {
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(10,200,100,20);
		btnCalculate.addActionListener(new CalculateHandler());
		panel.add(btnCalculate);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(100, 200, 100, 20);
		btnExit.addActionListener(new CloseListener());
		panel.add(btnExit);
	}
	/*
	 * this class has the ActionListener used for my button and labels.
	 * this allows the logic and the GUI to link so that when my calculate button is pressed it will run the logic and set the values depending on what was entered into the fields.
	 */
	
	class CalculateHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String speedValueStr = txtSpeedTraveled.getText(); //This will get the input and set it as the value for the string and then change it to an integer so that the speeding ticket class can use it.
			int speedValue = Integer.valueOf(speedValueStr);
			String speedLimitStr = txtSpeedLimit.getText();
			int speedLimit = Integer.valueOf(speedLimitStr);
			sT.setspeedValue(speedValue);
			lbloutcome.setText(sT.speedTest(speedValue,speedLimit)); // This is where the label is given the value of the outcome and displayed.
			sT.setdriverName(txtDriverName.getText());
			// lblTotalPoints.setText();
			try {
				sT.storeRecords();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			panel.revalidate();
			panel.repaint();
		}
	}
	/**
	 * This ActionListener makes it so that when the Exit button is pressed it will close the program.
	 *
	 */
		class CloseListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		sT.closeFile(); // This will close the printWriter.
		System.exit(0); // This will close the program.
	}
}
		/**
		 * This method creates an instance of the GUI speeding ticket class.
		 * @param args command line argument not used.
		 */
	public static void main(String[] args) {
		new GUISpeedingTicket(); // Creating an instance of GUI speedingTicket class.
	}

}
