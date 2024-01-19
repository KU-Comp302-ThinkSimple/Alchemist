package userinterface;

import domain.GameController;
import domain.LocalData;
import domain.boards.BoardController;
import domain.player.Player;
import domain.theory.Hypotheses;
import exception.UserErrorException;
import test.TestGameInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebunkTheoryView extends JPanel implements ActionListener {
    private JComboBox<HypothesisComboBoxItem> hypothesisComboBox;
    private JComboBox<AtomComboBoxItem> atomComboBox;

    public static void main(String[] args) throws UserErrorException, RuntimeException {
        TestGameInitializer.initializeTestGame();
        GameController.getInstance().setCurrentRound(3);
        Player player = LocalData.getInstance().getLocalPlayer();
        System.out.println(player.getInventory().getPlayerIngredientCardList().get(0).getMolecule().getRedAtom().getAtomSign());
        System.out.println(player.getInventory().getPlayerIngredientCardList().get(0).getMolecule().getGreenAtom().getAtomSign());
        System.out.println(player.getInventory().getPlayerIngredientCardList().get(0).getMolecule().getBlueAtom().getAtomSign());

        BoardController.publishTheory(player.getInventory().getPlayerIngredientCardList().get(0), GameController.getInstance().getGameInventory().getMolecules().get(0));
        JFrame frame = new JFrame();
        DebunkTheoryView dbk = new DebunkTheoryView();
        frame.getContentPane().add(dbk);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private class HypothesisComboBoxItem {
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

    private class AtomComboBoxItem {
        private final int atomColorId;

        public AtomComboBoxItem(int atomColorId) {
            super();
            this.atomColorId = atomColorId;
        }

        public int getAtomColorId() {
            return atomColorId;
        }

        @Override
        public String toString() {
            switch (atomColorId) {
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
                    throw new IllegalArgumentException("Unexpected atom color id: " + atomColorId);
            }
        }
    }

    public DebunkTheoryView() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{99, 99, 0};
        gridBagLayout.rowHeights = new int[]{13, 21, 21, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JButton debunkButton = new JButton("Debunk");
        debunkButton.addActionListener(this);

        JLabel lblNewLabel = new JLabel("Choose Hypothesis");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Atom to Debunk");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 0;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        hypothesisComboBox = new JComboBox<HypothesisComboBoxItem>();
        GridBagConstraints gbc_hypothesisComboBox = new GridBagConstraints();
        gbc_hypothesisComboBox.anchor = GridBagConstraints.NORTH;
        gbc_hypothesisComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_hypothesisComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_hypothesisComboBox.gridx = 0;
        gbc_hypothesisComboBox.gridy = 1;
        add(hypothesisComboBox, gbc_hypothesisComboBox);

        atomComboBox = new JComboBox<AtomComboBoxItem>();
        atomComboBox.addItem(new AtomComboBoxItem(0));
        atomComboBox.addItem(new AtomComboBoxItem(1));
        atomComboBox.addItem(new AtomComboBoxItem(2));
        GridBagConstraints gbc_atomComboBox = new GridBagConstraints();
        gbc_atomComboBox.anchor = GridBagConstraints.NORTH;
        gbc_atomComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_atomComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_atomComboBox.gridx = 1;
        gbc_atomComboBox.gridy = 1;
        add(atomComboBox, gbc_atomComboBox);
        GridBagConstraints gbc_debunkButton = new GridBagConstraints();
        gbc_debunkButton.anchor = GridBagConstraints.NORTH;
        gbc_debunkButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_debunkButton.insets = new Insets(0, 0, 0, 5);
        gbc_debunkButton.gridx = 0;
        gbc_debunkButton.gridy = 2;
        add(debunkButton, gbc_debunkButton);

        updateDebunkTheoryPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hypotheses hypothesis = ((HypothesisComboBoxItem) hypothesisComboBox.getSelectedItem()).getHypothesis();
        int atomColorId = ((AtomComboBoxItem) atomComboBox.getSelectedItem()).getAtomColorId();
        try {
            String trueSign = BoardController.debunkTheory(hypothesis, atomColorId);
            JOptionPane.showMessageDialog(this, "The true sign of " + hypothesis.getIngredient().getName() + "'s " + atomComboBox.getSelectedItem() + " atom is " + trueSign + "!");
            if (trueSign.contains("false")) {
                if (LocalData.getInstance().getLocalPlayer().getInventory().getPlayerArtifactCardList().contains(GameController.getInstance().getGameInventory().getArtCards().get(2))){
					GameController.getInstance().getGameInventory().getArtCards().get(2).useCard();
				}

            }
        } catch (UserErrorException exc) {
            JOptionPane.showMessageDialog(this, exc.getMessage());
        }
    }

    public void updateDebunkTheoryPanel() {
        hypothesisComboBox.removeAllItems();
        for (Hypotheses hypothesis : GameController.getInstance().getBoard().getPublicationBoard().getHypotheses()) {
            hypothesisComboBox.addItem(new HypothesisComboBoxItem(hypothesis));
        }
    }
}
