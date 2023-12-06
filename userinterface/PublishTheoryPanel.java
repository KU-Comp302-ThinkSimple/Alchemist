package userinterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import domain.GameController;
import domain.GameInventory;
import domain.boards.*;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.*;
import exception.UserErrorException;
import test.TestGameInitializer;
public class PublishTheoryPanel extends JPanel implements ActionListener{
	private final JComboBox<IngredientCardComboBoxItem> ingredientComboBox;
	private final JComboBox<MoleculeComboBoxItem> moleculeComboBox;
	
	private class IngredientCardComboBoxItem{
		private final IngredientCard ingredientCard;
		
		public IngredientCardComboBoxItem(IngredientCard ingredientCard) {
			super();
			this.ingredientCard = ingredientCard;
		}
		
		public IngredientCard getIngredientCard() {
			return ingredientCard;
		}

		@Override
		public String toString() {
			return ingredientCard.getName();
		}
	}
	
	private class MoleculeComboBoxItem{


		public Molecule getMolecule() {
			return molecule;
		}

		public MoleculeComboBoxItem(Molecule molecule) {
			super();
			this.molecule = molecule;
		}

		private final Molecule molecule;
		
		@Override
		public String toString() {
			return Integer.toString(molecule.getMoleculeId());
		}
	} 
	
	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(new PublishTheoryPanel());
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	public PublishTheoryPanel() {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setSize(274,107);
		
		ingredientComboBox = new JComboBox<IngredientCardComboBoxItem>();
		ingredientComboBox.setBounds(10, 30, 120, 25);
		add(ingredientComboBox);
		
		moleculeComboBox = new JComboBox<MoleculeComboBoxItem>();
		for (Molecule molecule : GameController.getGameInventory().getMolecules()) {
			moleculeComboBox.addItem(new MoleculeComboBoxItem(molecule));
		}
		moleculeComboBox.setBounds(140, 30, 120, 25);
		add(moleculeComboBox);
		
		JLabel lblNewLabel = new JLabel("Select ingredient");
		lblNewLabel.setBounds(10, 10, 120, 13);
		add(lblNewLabel);
		
		JLabel lblSelectMolecule = new JLabel("Select molecule");
		lblSelectMolecule.setBounds(140, 10, 120, 13);
		add(lblSelectMolecule);
		
		JButton publishTheoryButton = new JButton("Publish Theory");
		publishTheoryButton.setBounds(10, 65, 120, 25);
		add(publishTheoryButton);
		
		update();
	}
	
	public void update() {
		Player player = GameController.getCurrentPlayer();
		ingredientComboBox.removeAllItems();
		for (IngredientCard ingredientCard : player.getInventory().getPlayerIngredientCardList()) {
			ingredientComboBox.addItem(new IngredientCardComboBoxItem(ingredientCard));
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		IngredientCard ingredientCard = ((IngredientCardComboBoxItem)ingredientComboBox.getSelectedItem()).getIngredientCard();
		Molecule molecule = ((MoleculeComboBoxItem)moleculeComboBox.getSelectedItem()).getMolecule();
		try {
			BoardController.publishTheory(ingredientCard, molecule);
		}
		catch (UserErrorException exc) {
			JOptionPane.showMessageDialog(this, exc.getMessage());
		}
	}
}
