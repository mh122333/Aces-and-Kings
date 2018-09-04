package mahoerhold;

import ks.common.controller.*;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.*;
import ks.common.view.*;
import ks.launcher.Main;

public class AcesAndKings extends Solitaire {
	
	//model
	MultiDeck deck;
	Pile acePile1;
	Pile acePile2;
	Pile acePile3;
	Pile acePile4;
	Pile kingPile1;
	Pile kingPile2;
	Pile kingPile3;
	Pile kingPile4;
	Pile tabPile1;
	Pile tabPile2;
	Pile tabPile3;
	Pile tabPile4;
	Pile wastePile;
	Column rightCol;
	Column leftCol;
	
	//View
	DeckView deckView;
	PileView acePV1;
	PileView acePV2;
	PileView acePV3;
	PileView acePV4;
	PileView kingPV1;
	PileView kingPV2;
	PileView kingPV3;
	PileView kingPV4;
	PileView tabPV1;
	PileView tabPV2;
	PileView tabPV3;
	PileView tabPV4;
	PileView wastePV;
	RowView rightCV;
	RowView leftCV;
	IntegerView scoreView;
	IntegerView numLeftView;
	
	

	@Override
	public String getName() {
		return "mahoerhold-AcesAndKings";
	}

	@Override
	public boolean hasWon() {
		return (getScore().getValue() == 104);
	}

	@Override
	public void initialize() {
		// initialize model
		initializeModel(getSeed());
		initializeView();
		initializeControler();
		
		
		
	}

	private void initializeView() {
		CardImages ci  = getCardImages();
		
		int h = ci.getHeight();
		int w = ci.getWidth();
		
		deckView = new DeckView(deck);
		deckView.setBounds(20, 60+(2 *h), w, h);
		container.addWidget(deckView);
		
		acePV1 = new PileView(acePile1);
		acePV1.setBounds(20, 40+h, w, h);
		container.addWidget(acePV1);
		acePV2 = new PileView(acePile2);
		acePV2.setBounds(30+w, 40+h, w, h);
		container.addWidget(acePV2);
		acePV3 = new PileView(acePile3);
		acePV3.setBounds(40 + (2*w), 40+h, w, h);
		container.addWidget(acePV3);
		acePV4 = new PileView(acePile4);
		acePV4.setBounds(50 + (3*w), 40+h, w, h);
		container.addWidget(acePV4);
		
		kingPV1 = new PileView(kingPile1);
		kingPV1.setBounds(70 + (4*w), 40+h, w, h);
		container.addWidget(kingPV1);
		kingPV2 = new PileView(kingPile2);
		kingPV2.setBounds(80 + (5*w), 40+h, w, h);
		container.addWidget(kingPV2);
		kingPV3 = new PileView(kingPile3);
		kingPV3.setBounds(90 + (6*w), 40+h, w, h);
		container.addWidget(kingPV3);
		kingPV4 = new PileView(kingPile4);
		kingPV4.setBounds(100 + (7*w), 40+h, w, h);
		container.addWidget(kingPV4);
		
		tabPV1 = new PileView(tabPile1);
		tabPV1.setBounds(70 + (4*w), 60+(2*h), w, h);
		container.addWidget(tabPV1);
		tabPV2 = new PileView(tabPile2);
		tabPV2.setBounds(80 + (5*w), 60+(2*h), w, h);
		container.addWidget(tabPV2);
		tabPV3 = new PileView(tabPile3);
		tabPV3.setBounds(90 + (6*w), 60+(2*h), w, h);
		container.addWidget(tabPV3);
		tabPV4 = new PileView(tabPile4);
		tabPV4.setBounds(100 + (7*w), 60+(2*h), w, h);
		container.addWidget(tabPV4);
		
		wastePV = new PileView(wastePile);
		wastePV.setBounds(30 + w, 60+(2*h), w, h);
		container.addWidget(wastePV);
		
		leftCV = new RowView(leftCol);
		leftCV.setBounds(20, 20, 30+(4*w), (h));
		container.addWidget(leftCV);
		rightCV = new RowView(rightCol);
		rightCV.setBounds(70 + (4*w), 20, 30+(4*w), (h));
		container.addWidget(rightCV);
		
		scoreView = new IntegerView(getScore());
		scoreView.setBounds(40 + (2*w), 60+(2*h), w, (h/2));
		scoreView.setFontSize(20);
		container.addWidget(scoreView);
		
		numLeftView = new IntegerView(getNumLeft());
		numLeftView.setBounds(40 + (2*w), 60+((5*h)/2), w, (h/2));
		numLeftView.setFontSize(20);
		container.addWidget(numLeftView);
	}

	private void initializeControler() {
		deckView.setMouseAdapter(new AcesAndKingsDeckController(this, deck, wastePile));
		deckView.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		deckView.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		wastePV.setMouseAdapter(new AcesAndKingsWasteController(this, wastePV));
		wastePV.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		wastePV.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		acePV1.setMouseAdapter(new AcesAndKingsFoundationController(this, acePV1, deck));
		acePV1.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		acePV1.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		acePV2.setMouseAdapter(new AcesAndKingsFoundationController(this, acePV2, deck));
		acePV2.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		acePV2.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		acePV3.setMouseAdapter(new AcesAndKingsFoundationController(this, acePV3, deck));
		acePV3.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		acePV3.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		acePV4.setMouseAdapter(new AcesAndKingsFoundationController(this, acePV4, deck));
		acePV4.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		acePV4.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		kingPV1.setMouseAdapter(new AcesAndKingsFoundationController(this, kingPV1, deck));
		kingPV1.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		kingPV1.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		kingPV2.setMouseAdapter(new AcesAndKingsFoundationController(this, kingPV2, deck));
		kingPV2.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		kingPV2.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		kingPV3.setMouseAdapter(new AcesAndKingsFoundationController(this, kingPV3, deck));
		kingPV3.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		kingPV3.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		kingPV4.setMouseAdapter(new AcesAndKingsFoundationController(this, kingPV4, deck));
		kingPV4.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		kingPV4.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		tabPV1.setMouseAdapter(new AcesAndKingsTableuController(this, tabPV1));
		tabPV1.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		tabPV1.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		tabPV2.setMouseAdapter(new AcesAndKingsTableuController(this, tabPV2));
		tabPV2.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		tabPV2.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		tabPV3.setMouseAdapter(new AcesAndKingsTableuController(this, tabPV3));
		tabPV3.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		tabPV3.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		tabPV4.setMouseAdapter(new AcesAndKingsTableuController(this, tabPV4));
		tabPV4.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		tabPV4.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		leftCV.setMouseAdapter(new AcesAndKingsColumnController(this, leftCV));
		leftCV.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		leftCV.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		rightCV.setMouseAdapter(new AcesAndKingsColumnController(this, rightCV));
		rightCV.setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
		rightCV.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		
		
	}

	private void initializeModel(int seed) {
		deck = new MultiDeck("d", 2);
		deck.create(seed);
		model.addElement(deck);
		
		acePile1 = new Pile("ace1");
		model.addElement(acePile1);
		acePile2 = new Pile("ace2");
		model.addElement(acePile2);
		acePile3 = new Pile("ace3");
		model.addElement(acePile3);
		acePile4 = new Pile("ace4");
		model.addElement(acePile4);
		
		kingPile1 = new Pile("king1");
		model.addElement(kingPile1);
		kingPile2 = new Pile("king2");
		model.addElement(kingPile2);
		kingPile3 = new Pile("king3");
		model.addElement(kingPile3);
		kingPile4 = new Pile("king4");
		model.addElement(kingPile4);
		
		tabPile1 = new Pile("tab1");
		model.addElement(tabPile1);
		tabPile2 = new Pile("tab2");
		model.addElement(tabPile2);
		tabPile3 = new Pile("tab3");
		model.addElement(tabPile3);
		tabPile4 = new Pile("tab4");
		model.addElement(tabPile4);
		
		wastePile = new Pile("waste");
		model.addElement(wastePile);
		
		rightCol = new Column("right");
		model.addElement(rightCol);
		leftCol = new Column("left");
		model.addElement(leftCol);
		
		tabPile1.add(deck.get());
		tabPile2.add(deck.get());
		tabPile3.add(deck.get());
		tabPile4.add(deck.get());
		
		int colset = 0;
		
		while(colset<13){
			rightCol.add(deck.get());
			leftCol.add(deck.get());
			colset++;
		}
		
		
		updateNumberCardsLeft(74);
		updateScore(0);
	}
	
	public static void main(String[] args) {
		Main.generateWindow(new AcesAndKings(), Deck.OrderBySuit);
	}

}
