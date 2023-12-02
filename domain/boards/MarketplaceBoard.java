package domain.boards;

import domain.cards.*;
import domain.player.Player;
import exception.UserErrorException;

public class MarketplaceBoard extends Board {

	public MarketplaceBoard(int size, String name) {
		super(size, name);
	}

	public void buyArtifact(Player player) throws UserErrorException {
		if (!player.getPlayerToken().hasActionsLeft()) {
			throw new UserErrorException("The user does not have any actions left!");
		}
		if (player.getPlayerToken().getGold() < 3) {
			throw new UserErrorException("The user has insufficient gold!");
		}
		ArtifactCard artifactCard = ArtifactDeck.getInstance().removeArtifact();
		player.getPlayerToken().addArtifactCard(artifactCard);
		player.getPlayerToken().subtractGold(3);
		player.getPlayerToken().reducePlayerAction();
		// TODO: Apply artifact effects
		// artifactCard.apply();
	}

}
