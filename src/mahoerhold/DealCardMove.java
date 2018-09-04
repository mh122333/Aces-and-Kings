package mahoerhold;

import ks.common.games.Solitaire;
import ks.common.model.*;

public class DealCardMove extends Move {
	
	Deck deck;
	Pile pile;
	
	public DealCardMove(Deck deck, Pile wastePile) {
		this.deck = deck;
		this.pile = wastePile;
	}

	@Override
	public boolean doMove(Solitaire game) {
		
		if(valid(game)){
			Card card = deck.get();
			pile.add(card);
			game.updateNumberCardsLeft(-1);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean undo(Solitaire game) {
		Card c = pile.get();
		deck.add(c);
		game.updateNumberCardsLeft(+1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		
		return !(deck.empty());
	}

}
