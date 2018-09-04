package mahoerhold;

import java.awt.event.MouseEvent;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.games.Solitaire;
import ks.common.model.Column;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.RowView;
import ks.common.view.Widget;

public class AcesAndKingsColumnController extends SolitaireReleasedAdapter {
	
	RowView src;
	
	public AcesAndKingsColumnController(Solitaire game, RowView src) {
		super(game);
		this.src = src;
	}
	
	public void mousePressed(MouseEvent me){
		
		Container c = theGame.getContainer();
		
		Column col = (Column) src.getModelElement();
		
		if(col.count() == 0){
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
