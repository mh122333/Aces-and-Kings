package mahoerhold;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.*;

public class AcesAndKingsDeckController extends SolitaireReleasedAdapter {
	
	protected AcesAndKings theGame;
	protected Pile wastePile;
	protected Deck deck;
	
	public AcesAndKingsDeckController(AcesAndKings theGame, Deck d, Pile wastePile) {
		super(theGame);
		this.theGame = theGame;
		this.deck = d;
		this.wastePile = wastePile;
	}
	
	public void mousePressed(java.awt.event.MouseEvent me) {
		
		Move m = new DealCardMove(deck, wastePile);
		if(m.doMove(theGame)) {
			theGame.pushMove(m);
			theGame.refreshWidgets();
		}
		
	}

}
