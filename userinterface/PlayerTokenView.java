package userinterface;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import domain.player.Player;
import domain.potion.Potion;
import net.miginfocom.swing.MigLayout;

public class PlayerTokenView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField goldFiels;
	private JTextField repField;
	private JTextField healthField;
	private ArrayList<JPanel> potPanels=new  ArrayList<JPanel>();
	private int id; //There will be two of this view so there should be an id attribute to separate them
	private String playerName;
	private String playerGold;
	private String playerRep;
	private String playerHealth;
	private ArrayList<Potion> nameText;

	/**
	 * Create the panel.
	 */
	public PlayerTokenView() {
		setLayout(new MigLayout("", "[130px,grow][][130px,grow][][][grow][][][grow][]", "[][26px][26px][grow][][][grow][grow][]"));
		
		nameField = new JTextField();
		add(nameField, "cell 0 0,alignx center,aligny center");
		nameField.setColumns(10);
		
		healthField = new JTextField();
		add(healthField, "cell 5 1,alignx leading");
		healthField.setColumns(10);
		
		goldFiels = new JTextField();
		add(goldFiels, "flowx,cell 8 1,alignx center,aligny top");
		goldFiels.setColumns(10);
		
		repField = new JTextField();
		add(repField, "cell 8 2,growx");
		repField.setColumns(10);
		
		JPanel imagePanel = new JPanel();
		add(imagePanel, "cell 0 1 1 3,grow");
		
		JPanel potPanel0 = new JPanel();
		add(potPanel0, "cell 0 5 2 2,grow");
		potPanels.add(potPanel0);
		
		JPanel potPanel1 = new JPanel();
		add(potPanel1, "cell 2 5 1 2,grow");
		potPanels.add(potPanel1);
		
		JPanel potPanel2 = new JPanel();
		add(potPanel2, "cell 5 5 1 2,grow");
		potPanels.add(potPanel2);
		
		JPanel potPanel3 = new JPanel();
		add(potPanel3, "cell 8 6,grow");
		potPanels.add(potPanel3);
		
		JPanel potPanel4 = new JPanel();
		add(potPanel4, "cell 0 7 1 2,grow");
		potPanels.add(potPanel4);
		
		JPanel potPanel5 = new JPanel();
		add(potPanel5, "cell 2 7 1 2,grow");
		potPanels.add(potPanel5);
		
		JPanel potPanel6 = new JPanel();
		add(potPanel6, "cell 5 7 1 2,grow");
		potPanels.add(potPanel6);
		
		JPanel potPanel7 = new JPanel();
		add(potPanel7, "cell 8 7 1 2,grow");
		potPanels.add(potPanel7);

	}
	
	

}
