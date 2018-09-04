package mahoerhold;

import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class AcesAndKingsFoundationController extends SolitaireReleasedAdapter {
	
	PileView src;
	Deck deck;
	
	
	public AcesAndKingsFoundationController(Solitaire game, PileView found, Deck d) {
		super(game);
		this.src = found;
		this.deck = d;
		
	}
	
public void mousePressed(MouseEvent me){
		
		Container c = theGame.getContainer();
		
		Pile foundPile = (Pile) src.getModelElement();
		
		if(foundPile.count() == 0){
			c.releaseDraggingObject();
			return;
		}
		
		CardView cardView = src.getCardViewForTopCard(me);
		
		if(cardView == null){
			c.releaseDraggingObject();
			return;
		}
		
		Widget w = c.getActiveDraggingObject();
		if(w != Container.getNothingBeingDragged()){
			System.err.println("WastePileController error");
			return;
		}
		
		c.setActiveDraggingObject(cardView, me);
		c.setDragSource(src);
		
		src.redraw();
		
	}
	
	public void mouseReleased(MouseEvent me) {
		Container c = theGame.getContainer();
		
		Widget fromWidget = c.getDragSource();
		Widget draggingWidget = c.getActiveDraggingObject();
		
		if(fromWidget == null){
			System.err.println("FoundationController error");
			c.releaseDraggingObject();
			return;
		}
		
		Pile foundation = (Pile) src.getModelElement();
		Stack from = (Stack) fromWidget.getModelElement();
		
		CardView cardView = (CardView) draggingWidget;
		Card theCard = (Card) cardView.getModelElement();
		
		boolean acepass = (foundation.getName().substring(0, 3).equalsIgnoreCase("ace"));
		boolean tabpass = (from.getName().substring(0, 3).equalsIgnoreCase("tab"));
		
		Move move = new FoundationMove(from, foundation,theCard,acepass, tabpass, deck);
		if(move.doMove(theGame)){
			theGame.pushMove(move);
			theGame.refreshWidgets();
		}
		else{
			fromWidget.returnWidget(draggingWidget);
		}
		
		c.releaseDraggingObject();
		c.repaint();
		
		
	}

}
