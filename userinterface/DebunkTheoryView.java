package userinterface;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.*;

import test.TestGameInitializer;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.theory.*;
import java.util.*;
public class DebunkTheoryView extends JPanel{
	public static void main(String[] args) {
		TestGameInitializer.initializeTestGame();
		Hypotheses hyp = new Hypotheses(GameController.getCurrentPlayer(), GameController.getGameInventory().getIngrCards().get(0), GameController.getGameInventory().getMolecules().get(0));
		JFrame frame = new JFrame();
		DebunkTheoryView dbk = new DebunkTheoryView();
		frame.getContentPane().add(dbk);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	
	private class HypothesisComboBoxItem{
		private final Hypotheses hypothesis;
		
		public HypothesisComboBoxItem(Hypotheses hypothesis) {
			super();
			this.hypothesis = hypothesis;
		}
		
		public Hypotheses getHypothesis() {
			return hypothesis;
		}

		@Override
		public String toString() {
			return "I: " + hypothesis.getIngredient().getName() + " M(id): " + hypothesis.getMolecule().getMoleculeId();
		}
	}
	
	private class AtomComboBoxItem{
		private final int atomId;

		public AtomComboBoxItem(int moleculeId) {
			super();
			this.atomId = moleculeId;
		}

		public int getMoleculeId() {
			return atomId;
		}
		
		@Override
		public String toString() {
			switch (atomId) {
			case 0: {
				return "Red";
			}
			case 1: {
				return "Green";
			}
			case 2: {
				return "Blue";
			}
			default:
				throw new IllegalArgumentException("Unexpected atom color id: " + atomId);
			}
		}
	}
	
	public DebunkTheoryView() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Hypothesis");
		lblNewLabel.setBounds(10, 10, 99, 13);
		add(lblNewLabel);
		
		JComboBox hypothesisComboBox = new JComboBox();
		hypothesisComboBox.setBounds(10, 33, 99, 21);
		add(hypothesisComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Atom to Debunk");
		lblNewLabel_1.setBounds(119, 10, 83, 13);
		add(lblNewLabel_1);
		
		JComboBox atomComboBox = new JComboBox();
		atomComboBox.setBounds(119, 33, 99, 21);
		add(atomComboBox);
		
		JButton debunkButton = new JButton("Debunk");
		debunkButton.setBounds(10, 64, 85, 21);
		add(debunkButton);
	}
}
