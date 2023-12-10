package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

import domain.GameController;
import domain.GameInventory;
import domain.boards.*;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.*;
import exception.UserErrorException;
import test.TestGameInitializer;
import userinterface.util.GlobalIcons;

import java.util.*;
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
		setPreferredSize(new Dimension(272, 284));

		JLabel moleculeLabel = new JLabel();
		moleculeLabel.setBounds(150, 107, 100, 100);
		//moleculeLabel.setIcon(); //TODO unknown molecule
		add(moleculeLabel);

		JLabel ingredientLabel = new JLabel();
		ingredientLabel.setBounds(25, 77, 100, 160);
		ingredientLabel.setIcon(GlobalIcons.getIngredientCardImage("Unknown"));
		add(ingredientLabel);

		ingredientComboBox = new JComboBox<IngredientCardComboBoxItem>();
		ingredientComboBox.setBounds(10, 30, 120, 25);
		ingredientComboBox.addActionListener(e -> {
			if (ingredientComboBox.getSelectedItem() != null) {
				String ingr = ingredientComboBox.getSelectedItem().toString();
				ingredientLabel.setIcon(GlobalIcons.getIngredientCardImage(ingr));
			}
		});
		add(ingredientComboBox);

		moleculeComboBox = new JComboBox<MoleculeComboBoxItem>();
		ArrayList<Molecule> moleculesCopy = GameController.getGameInventory().getMolecules();
		moleculesCopy.sort((o1, o2) -> Integer.valueOf(o1.getMoleculeId()).compareTo(Integer.valueOf(o2.getMoleculeId())));
		for (Molecule molecule : moleculesCopy) {
			moleculeComboBox.addItem(new MoleculeComboBoxItem(molecule));
		}
		moleculeComboBox.setBounds(140, 30, 120, 25);
		moleculeComboBox.addActionListener(e -> {
			if (moleculeComboBox.getSelectedItem() != null) {
				String moleculeid = moleculeComboBox.getSelectedItem().toString();
				moleculeLabel.setIcon(GlobalIcons.getMoleculeImage(moleculeid));
			}
		});
		add(moleculeComboBox);

		JLabel lblNewLabel = new JLabel("Select ingredient");
		lblNewLabel.setBounds(10, 10, 120, 13);
		add(lblNewLabel);

		JLabel lblSelectMolecule = new JLabel("Select molecule");
		lblSelectMolecule.setBounds(140, 10, 120, 13);
		add(lblSelectMolecule);

		JButton publishTheoryButton = new JButton("Publish Theory");
		publishTheoryButton.setBounds(73, 248, 120, 25);
		publishTheoryButton.addActionListener(this);
		add(publishTheoryButton);

		updatePublishTheoryPanel();
	}

	public void updatePublishTheoryPanel() {
		Player player = GameController.getCurrentPlayer();
		ingredientComboBox.removeAllItems();
		for (IngredientCard ingredientCard : GameController.getGameInventory().getIngrCards()) {
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
