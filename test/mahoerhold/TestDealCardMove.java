package mahoerhold;

import junit.framework.TestCase;
import ks.common.model.*;
import ks.launcher.Main;

public class TestDealCardMove extends TestCase {
	
	public void testStuff() {
		AcesAndKings acesAndKings = new AcesAndKings();
		Main.generateWindow(acesAndKings, Deck.OrderBySuit);
		
		Card top = acesAndKings.deck.peek();
		DealCardMove dcm = new DealCardMove(acesAndKings.deck, acesAndKings.wastePile);
		
		assertTrue(dcm.valid(acesAndKings));
		
		dcm.doMove(acesAndKings);
		
		assertEquals(73, acesAndKings.deck.count());
		assertEquals(top, acesAndKings.wastePile.peek());
		
		int nleft = acesAndKings.getNumLeft().getValue();
		assertEquals(73, nleft);
		
		dcm.undo(acesAndKings);
		
		assertEquals(74, acesAndKings.deck.count());
		
		Card tabcard = acesAndKings.tabPile1.peek();
		FoundationMove fm1 = new FoundationMove(acesAndKings.tabPile1, acesAndKings.kingPile1, tabcard, false, true,acesAndKings.deck );
		
		assertTrue(fm1.valid(acesAndKings));
		
		
		fm1.doMove(acesAndKings);
		Card foundcard = acesAndKings.kingPile1.peek();
		FoundationMove fm2 = new FoundationMove(acesAndKings.kingPile1, acesAndKings.kingPile2, foundcard, false, false,acesAndKings.deck );
		
		assertTrue(fm2.valid(acesAndKings));
		
		fm2.doMove(acesAndKings);
		assertEquals(13, acesAndKings.kingPile2.peek().getRank());
		
		fm2.undo(acesAndKings);
		assertTrue(acesAndKings.kingPile2.empty());
		assertEquals(13, acesAndKings.kingPile1.peek().getRank());
		
		Card leftcard = acesAndKings.leftCol.peek();
		FoundationMove failmove1 = new FoundationMove(acesAndKings.leftCol, acesAndKings.acePile2, leftcard, true, false,acesAndKings.deck );
		
		assertTrue(!failmove1.valid(acesAndKings));
		
		acesAndKings.updateScore(+103);
		assertTrue(acesAndKings.hasWon());
		
	}
	
	public void testMoreStuff() {
		
		AcesAndKings acesAndKings = new AcesAndKings();
		Main.generateWindow(acesAndKings, Deck.OrderBySuit);
		
		DealCardMove dcm = new DealCardMove(acesAndKings.deck, acesAndKings.wastePile);
		int count = 0;
		while(count < 9){
			dcm.doMove(acesAndKings);
			count++;
		}
		
		Card wc1 = acesAndKings.wastePile.peek();
		FoundationMove apm1 = new FoundationMove(acesAndKings.wastePile, acesAndKings.acePile3, wc1, true, false,acesAndKings.deck );
		assertTrue(apm1.valid(acesAndKings));
		apm1.doMove(acesAndKings);
		
		Card wc2 = acesAndKings.rightCol.peek();
		FoundationMove apmfail = new FoundationMove(acesAndKings.wastePile, acesAndKings.acePile3, wc2, true, false,acesAndKings.deck );
		assertTrue(!apmfail.valid(acesAndKings));
		apmfail.doMove(acesAndKings);
		
	}

}
