package userinterface;

import domain.GameController;
import domain.cards.IngredientCard;
import domain.cards.artifactCards.ArtifactCard;
import domain.cards.artifactCards.ElixirOfInsight;
import domain.player.Player;
import userinterface.util.GlobalIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ArtifactCardPanels {
    private Player player;
    private JPanel viewPort;
    private JPanel viewPort_1;
    ArtifactCardPanels(){
        player = GameController.getInstance().getCurrentPlayer();
        for(int i=0;i<player.getInventory().getPlayerArtifactCardList().size();i++) {

            ImageIcon icon = GlobalIcons.getArtifactCardImage(player.getInventory().getPlayerArtifactCardList().get(i).getName());
            JButton button = new JButton();
            button.setContentAreaFilled(false);
            button.setIcon(icon);
            button.setPreferredSize(new Dimension(100, 160));
            button.setBorder(new EmptyBorder(0, 0, 0, 0));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            ArtifactCard currentCard=player.getInventory().getPlayerArtifactCardList().get(i);
            button.addActionListener(e -> {
                currentCard.useCard();
                System.out.println("Used card named "+ currentCard.getName());

                // This area is to create the process if the card is the elixir of insight.


                if(currentCard.getName().equals("Elixir Of Insight")) {

                    ArrayList<IngredientCard> cards= (ArrayList<IngredientCard>) currentCard.useCard();
                    String s="The top 3 cards are: \n";
                    for(int j=0;j<3;j++) {
                        s+=(j+1)+".";
                        s+=(cards.get(j).getName());
                        s+="\n";
                    }
                    s+="Please write the new order of cards, separated by comma";

                    JTextArea jta = new JTextArea(10, 30);
                    jta.setText(s);
                    jta.setEditable(false);
                    JScrollPane jsp = new JScrollPane(jta);
                    String order= JOptionPane.showInputDialog(null, jsp);

                    try {
                        String[] numString = order.split(",");
                        int[] arr = new int[numString.length];
                        for (int j = 0; j < numString.length; j++) {
                            arr[j] = Integer.valueOf(numString[j]);
                        }
                        ElixirOfInsight elixCard= (ElixirOfInsight) currentCard;
                        elixCard.changeCards(arr);


                    }catch(Exception exc) {
                        System.out.println("Something went wrong");
                    }

                }
                GameController.getInstance().getCurrentPlayer().getInventory().getPlayerArtifactCardList().remove(currentCard);
                GameController.getInstance().getCurrentPlayer().getPlayerToken().reducePlayerAction();

            });

            viewPort_1.add(button);

        }

        //TODO same for artifact cards
    }
    }

