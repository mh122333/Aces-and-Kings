package mahoerhold;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.model.Stack;

public class FoundationMove extends Move{
	
	
	Stack from;
	Card dragged;
	Pile foundation;
	boolean toAcePile;
	boolean fromTableu;
	Deck deck;
	
	public FoundationMove(Stack from, Pile found, Card dragged, boolean ace, boolean tab,Deck d ) {
		this.foundation = found;
		this.dragged = dragged;
		this.from = from;
		this.toAcePile = ace;
		this.fromTableu = tab;
		this.deck = d;
	}

	@Override
	public boolean doMove(Solitaire game) {
		
		if(valid(game)){
			foundation.add(dragged);
			//check if was moving between foundations
			if(from.getName().substring(0,3).equalsIgnoreCase("rig") || from.getName().substring(0,3).equalsIgnoreCase("was") || fromTableu  || from.getName().substring(0,3).equalsIgnoreCase("lef")){
				game.updateScore(+1);
			}
			
			if(fromTableu && !(deck.empty())){
				from.add(deck.get());
			}
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean undo(Solitaire game) {
		from.add(foundation.get());
		if(( !(from.getName().substring(0, 3).equalsIgnoreCase("ace")) && !(from.getName().substring(0, 3).equalsIgnoreCase("kin")))){  //check if was moving between foundations
			game.updateScore(-1);
		}
		
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		
		/*
		if(from.empty()){
			return false;
		}
		*/
		
		if(foundation.empty() && toAcePile){
			return dragged.isAce();
		}
		
		else if(foundation.empty() && !toAcePile){
			return (dragged.getRank() == 13);
		}
		
		else if(toAcePile){
			return (dragged.getRank() == (foundation.peek().getRank() + 1));
		}
		
		else{
			return (dragged.getRank() == (foundation.peek().getRank() - 1));
		}
		
	}

}
