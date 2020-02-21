// Aaron J. Wakeen
// February 20, 2020
// Lab_6: Conversion with input validation
// CLASS

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
//import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;

public class ConversionCalculator implements ActionListener{
	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel menuPanel;
	private JPanel resultsPanel;
	private Border loweredEtchedBorder;
	private JLabel unitsLabel;
	JTextField inputField;
	JLabel conversionLabel;
	JLabel resultUnitsLabel;
	JLabel equalsLabel;
	JButton submitButton;
	JComboBox<String> conversionCombo;
	GridBagLayout mainFrameLayout;
	GridBagLayout menuPanelLayout;
	GridBagLayout resultsPanelLayout;
	
	public ConversionCalculator() {
		prepareGUI();
	} // END _DEF_CONSTRUCTOR_
	
	private void prepareGUI() {
		
		mainFrameLayout = new GridBagLayout();
		mainFrame = new JFrame("Conversion Calculator");
		mainFrame.setLayout(mainFrameLayout);
		mainFrame.setSize(700,700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		headerLabel = new JLabel("Welcome to my Conversion Calculator!", JLabel.CENTER);
		menuPanel = new JPanel();
		resultsPanel = new JPanel();
		
		// setup menuPanel
		prepareMenuPanel();
		
		// setup resultsPanel
		prepareResultsPanel();
		
		addMainFrameComponent(headerLabel, 1, 0, 1, 1, 0, 0, 5, 15,
				GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
		addMainFrameComponent(menuPanel, 0, 1, 3, 1, 0, 2, 50, 5,
				GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
		addMainFrameComponent(resultsPanel, 0, 2, 3, 1, 0, 2, 50, 5,
				GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
		mainFrame.setVisible(true);
		
	}  // END _PREPARE_GUI
	
	public void addMainFrameComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, int ipadx, int ipady, int fill, int anchor) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.ipadx = ipadx;
		constraints.ipady = ipady;
		constraints.fill = fill;
		constraints.anchor = anchor;
		mainFrameLayout.setConstraints(component, constraints);
		mainFrame.add(component);
	} // END _ADD_MAIN_FRAME_COMPONENT
	
	public void prepareMenuPanel() {
		menuPanelLayout = new GridBagLayout();
		menuPanel.setLayout(menuPanelLayout);
		
		JLabel menuTitleLabel = new JLabel("Select a conversion type:");
		
		// create comboBox
		final DefaultComboBoxModel<String> conversionOptions = new DefaultComboBoxModel<String>();
		conversionOptions.addElement("Gallons to Liters");
		conversionOptions.addElement("Inches to Centimeters");
		conversionOptions.addElement("Pounds to Kilograms");
		conversionOptions.addElement("Miles to Kilometers");
		conversionCombo = new JComboBox<String>(conversionOptions);
		conversionCombo.setSelectedIndex(0);
		conversionCombo.addActionListener(this);
		
		// To Scale if more conversion options are added
		//JScrollPane comboScrollPane = new JScrollPane(conversionCombo);
		
		JLabel inputLabel = new JLabel("Input: ", JLabel.RIGHT);		
		unitsLabel = new JLabel("Gallons", JLabel.LEFT);
		inputField = new JTextField(6);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		
		addMenuPanelComponent(menuTitleLabel, 0, 0, 4, 1, 1, 0, 0, 5,
				GridBagConstraints.HORIZONTAL, GridBagConstraints.LINE_START);
		addMenuPanelComponent(conversionCombo, 0, 1, 3, 1, 1, 1, 5, 0,
				GridBagConstraints.HORIZONTAL, GridBagConstraints.LINE_START);
		addMenuPanelComponent(inputLabel, 0, 2, 1, 1, 1, 1, 5, 0,
				GridBagConstraints.NONE, GridBagConstraints.LINE_END);
		addMenuPanelComponent(inputField, 1, 2, 1, 1, 1, 1, 0, 0,
				GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addMenuPanelComponent(unitsLabel, 2, 2, 1, 1, 1, 1, 5, 0,
				GridBagConstraints.NONE, GridBagConstraints.LINE_START);
		addMenuPanelComponent(submitButton, 3, 3, 1, 1, 1, 1, 5, 5,
				GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_END);
		
		//menuPanel.setSize(400, 300);
		menuPanel.setBorder(loweredEtchedBorder);
		menuPanel.add(menuTitleLabel);
	}	// END _PREPARE_MENU_PANEL
	
	public void addMenuPanelComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, int ipadx, int ipady, int fill, int anchor) {
		
		GridBagConstraints menuConstraints = new GridBagConstraints();
		menuConstraints.gridx = gridx;
		menuConstraints.gridy = gridy;
		menuConstraints.gridwidth = gridwidth;
		menuConstraints.gridheight = gridheight;
		menuConstraints.weightx = weightx;
		menuConstraints.weighty = weighty;
		menuConstraints.ipadx = ipadx;
		menuConstraints.ipady = ipady;
		menuConstraints.fill = fill;
		menuConstraints.anchor = anchor;
		menuPanelLayout.setConstraints(component, menuConstraints);
		menuPanel.add(component);
	}  // END _ADD_MENU_PANEL_COMPONENT
	
	public void prepareResultsPanel() {
		
		conversionLabel = new JLabel("Results");
		resultsPanel.setBorder(loweredEtchedBorder);
		resultsPanel.add(conversionLabel);
	}  // END _PREPARE_RESULTS_PANEL
	
	public void prepareResultsPanel(String conversionText, String units, double results) {
		resultsPanel.removeAll();
		resultsPanelLayout = new GridBagLayout();
		resultsPanel.setLayout(resultsPanelLayout);
		
		conversionLabel = new JLabel(conversionText);
		String resultStr = String.format("%.2f %s", results, units);
		resultUnitsLabel = new JLabel(resultStr);
		equalsLabel = new JLabel("Equals");
		
		addResultsPanelComponent(conversionLabel, 0, 0, 3, 1, 1, 1, 15, 0, 
				GridBagConstraints.NONE, GridBagConstraints.LINE_START);
		addResultsPanelComponent(equalsLabel, 1, 1, 1, 1, 1, 1, 10, 0, 
				GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addResultsPanelComponent(resultUnitsLabel, 1, 2, 2, 1, 1, 1, 15, 0, 
				GridBagConstraints.NONE, GridBagConstraints.LINE_END);
		
		resultsPanel.setBorder(loweredEtchedBorder);
		resultsPanel.updateUI();
		
	}  // END _PREPARE_RESULTS_PANEL[STRING, STRING, DOUBLE]
	
	public void addResultsPanelComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, int ipadx, int ipady, int fill, int anchor) {
		
		GridBagConstraints resultsConstraints = new GridBagConstraints();
		resultsConstraints.gridx = gridx;
		resultsConstraints.gridy = gridy;
		resultsConstraints.gridwidth = gridwidth;
		resultsConstraints.gridheight = gridheight;
		resultsConstraints.weightx = weightx;
		resultsConstraints.weighty = weighty;
		resultsConstraints.ipadx = ipadx;
		resultsConstraints.ipady = ipady;
		resultsConstraints.fill = fill;
		resultsConstraints.anchor = anchor;
		resultsPanelLayout.setConstraints(component, resultsConstraints);
		resultsPanel.add(component);
	}  // END _ADD_RESULTS_PANEL_COMPONENT
	
	public String makeConversionString() {
		int selection = conversionCombo.getSelectedIndex();
		
		String conversionString = Double.toString(getErrorCheckedInput());
		
		switch (selection) {
		case 0:
			conversionString += " Gallons   x   3.78541178   (Liters / Gallon)";
			break;
		case 1:
			conversionString += " Inches   x   2.54   (Centimeters / Inch)";
			break;
		case 2:
			conversionString += " Pounds   x   0.45359237   (Kilograms / Pound)";
			break;
		case 3:
			conversionString += " Miles   x   1.609344   (Kilometers / Mile)";
			break;
		default:
			conversionString = "ERROR: in makeConversionString()";
			break;
		}
		return conversionString;
	}  // END _MAKE_CONVERSION_STRING
	
	public Double getErrorCheckedInput() {
	/* If error thrown, outputs error dialog and 
	 * sets the input value to 0, so processed input is cleaned 
	 */	
		Double retVal = 0.0;
		
		try {
			
			String inputString = inputField.getText();
			retVal = Double.valueOf(inputString);
			
		}  catch (NullPointerException npe) {
			
			String npeMSG = String.format("No input provided, please input a number.%n%s", npe.toString());
			JOptionPane.showMessageDialog(mainFrame,
					npeMSG, "ERROR",
					JOptionPane.ERROR_MESSAGE);
			inputField.setText("0");
			
		} catch (NumberFormatException nfe) {
			
			String ermsg = nfe.toString();
			String printableErmsg = String.format("Invalid input, please input a number.%n[ Input provided %s ]", ermsg.substring(ermsg.lastIndexOf(":")));
			
			JOptionPane.showMessageDialog(mainFrame,
					printableErmsg,
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
			inputField.setText("0");
		} // END_TRY
		
		return retVal;
	} // END _GET_ERROR_CHECKED_INPUT
		
	public String getUnits() {
		int selection = conversionCombo.getSelectedIndex();
		String unitsString = "";
		switch (selection) {
		case 0:
			unitsString = "Gallons";
			break;
		case 1:
			unitsString = "Inches";
			break;
		case 2:
			unitsString = "Pounds";
			break;
		case 3:
			unitsString = "Miles";
			break;
	
		default:
			unitsString = "Units";

		}  // END_SWITCH
		return unitsString;
	}  // END _GET_UNITS
	
	public String getResultUnits() {
		int selection = conversionCombo.getSelectedIndex();
		String unitsString = "";
		switch (selection) {
		case 0:
			unitsString = "Liters";
			break;
		case 1:
			unitsString = "Centimeters";
			break;
		case 2:
			unitsString = "Kilograms";
			break;
		case 3:
			unitsString = "Kilometers";
			break;
	
		default:
			unitsString = "Units";

		}  // END_SWITCH
		return unitsString;
	}  // END _GET_UNITS
	
	public double calculateResults() {
		double results = 0.0;
		double base = getErrorCheckedInput();
		int selection = conversionCombo.getSelectedIndex();
		switch (selection) {
		case 0:
			results = base * 3.78541178;
			break;
		case 1:
			results = base * 2.54;
			break;
		case 2:
			results = base * 0.45359237;
			break;
		case 3:
			results = base * 1.609344;
			break;
	
		default:
			results = 0;

		}  // END_SWITCH
		
		return results;
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == submitButton) {
			String conversionText = makeConversionString();
			String units = getResultUnits();
			double results = calculateResults();
			prepareResultsPanel(conversionText, units, results);
			
		} else if (source == conversionCombo) {
			unitsLabel.setText(getUnits());		
		}
	}  // END _ACTION_PERFORMED
}  // END __CONVERSION_CALCULATOR__
