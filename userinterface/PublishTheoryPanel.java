package userinterface;

import domain.GameController;
import domain.LocalData;
import domain.boards.BoardController;
import domain.cards.IngredientCard;
import domain.player.Player;
import domain.potion.Molecule;
import exception.UserErrorException;
import test.TestGameInitializer;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		setPreferredSize(new Dimension(228, 234));

		JLabel moleculeLabel = new JLabel();
		moleculeLabel.setBounds(120, 72, 100, 100);
		//moleculeLabel.setIcon(); //TODO unknown molecule
		add(moleculeLabel);

		JLabel ingredientLabel = new JLabel();
		ingredientLabel.setBounds(10, 47, 100, 160);
		ingredientLabel.setIcon(GlobalIcons.getIngredientCardImage("Unknown"));
		add(ingredientLabel);

		ingredientComboBox = new JComboBox<IngredientCardComboBoxItem>();
		ingredientComboBox.setBounds(10, 18, 100, 25);
		ingredientComboBox.addActionListener(e -> {
			if (ingredientComboBox.getSelectedItem() != null) {
				String ingr = ingredientComboBox.getSelectedItem().toString();
				ingredientLabel.setIcon(GlobalIcons.getIngredientCardImage(ingr));
			}
		});
		add(ingredientComboBox);

		moleculeComboBox = new JComboBox<MoleculeComboBoxItem>();
		ArrayList<Molecule> moleculesCopy = GameController.getInstance().getGameInventory().getMolecules();
		moleculesCopy.sort((o1, o2) -> Integer.valueOf(o1.getMoleculeId()).compareTo(Integer.valueOf(o2.getMoleculeId())));
		for (Molecule molecule : moleculesCopy) {
			moleculeComboBox.addItem(new MoleculeComboBoxItem(molecule));
		}
		moleculeComboBox.setBounds(113, 18, 105, 25);
		moleculeComboBox.addActionListener(e -> {
			if (moleculeComboBox.getSelectedItem() != null) {
				String moleculeid = moleculeComboBox.getSelectedItem().toString();
				moleculeLabel.setIcon(GlobalIcons.getMoleculeImage(moleculeid));
			}
		});
		add(moleculeComboBox);

		JLabel lblNewLabel = new JLabel("Select ingredient");
		lblNewLabel.setBounds(10, 5, 120, 13);
		add(lblNewLabel);

		JLabel lblSelectMolecule = new JLabel("Select molecule");
		lblSelectMolecule.setBounds(113, 5, 120, 13);
		add(lblSelectMolecule);

		JButton publishTheoryButton = new JButton("Publish Theory");
		publishTheoryButton.setBounds(60, 205, 120, 25);
		publishTheoryButton.addActionListener(this);
		add(publishTheoryButton);

		updatePublishTheoryPanel();
	}

	public void updatePublishTheoryPanel() {
		ingredientComboBox.removeAllItems();
		for (IngredientCard ingredientCard : GameController.getInstance().getGameInventory().getIngrCards()) {
			ingredientComboBox.addItem(new IngredientCardComboBoxItem(ingredientCard));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		IngredientCard ingredientCard = ((IngredientCardComboBoxItem)ingredientComboBox.getSelectedItem()).getIngredientCard();
		Molecule molecule = ((MoleculeComboBoxItem)moleculeComboBox.getSelectedItem()).getMolecule();
		try {
			BoardController.publishTheory(ingredientCard, molecule);
			if (LocalData.getInstance().getLocalPlayer().getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(4))){
				GameController.getInstance().getGameInventory().getArtCards().get(4).useCard();
			}
		}
		catch (UserErrorException exc) {
			JOptionPane.showMessageDialog(this, exc.getMessage());
		}
	}
}
