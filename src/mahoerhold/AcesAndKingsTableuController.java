package mahoerhold;

import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class AcesAndKingsTableuController extends SolitaireReleasedAdapter {
	
	PileView src;
	
	public AcesAndKingsTableuController(Solitaire game, PileView src) {
		super(game);
		this.src = src;
	}
	
public void mousePressed(MouseEvent me){
		
		Container c = theGame.getContainer();
		
		Pile tabPile = (Pile) src.getModelElement();
		
		if(tabPile.count() == 0){
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

}
