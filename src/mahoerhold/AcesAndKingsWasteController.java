package mahoerhold;


import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;

public class AcesAndKingsWasteController extends SolitaireReleasedAdapter {
	
	PileView src;
	
	public AcesAndKingsWasteController(Solitaire theGame, PileView src) {
		super(theGame);
		this.src = src;
	}
	
	public void mousePressed(MouseEvent me){
		
		Container c = theGame.getContainer();
		
		Pile wastePile = (Pile) src.getModelElement();
		
		if(wastePile.count() == 0){
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
