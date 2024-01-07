package domain.boards;

import domain.cards.artifactCards.ArtifactCard;

import java.util.ArrayList;
import java.util.Collection;

public class ArtifactCardDeckArrayList  extends RandomCardDeck<ArtifactCard>{
    public ArtifactCardDeckArrayList(int size, Collection<ArtifactCard> cardSource) {
        super(size, cardSource, ArrayList.class);
    }

    @Override
    ArtifactCard popCard() {
        ArtifactCard ret = (ArtifactCard)((ArrayList<ArtifactCard>) this.repr).remove(0);
        refill();
        return ret;
    }

    @Override
    boolean repOk() {
        return getSize() == repr.size();
    }

}
