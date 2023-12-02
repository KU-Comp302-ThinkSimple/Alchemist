package domain.cards;

import java.util.*;

public class ArtifactDeck {
	
	private static ArtifactDeck instance;
	private ArrayDeque<ArtifactCard> artifactCards;
	
	private ArtifactDeck() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArtifactDeck getInstance() {
		if(instance == null) {
			instance = new ArtifactDeck();
		}
		return instance;
	}
	
	public ArtifactCard removeArtifact() {
		return artifactCards.pop();
	}
	
	public boolean addArtifact(ArtifactCard artifact) {
		return artifactCards.add(artifact);
	}

}
