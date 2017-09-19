/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;


import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Gareth
 */
public class DeckOfCartas { //Shorten this name, it's now going to represent the deck AND the hands
    private ArrayList<Carta> pack; //Next attempt make these public final so you 
    private int count, current; //don't have to create a method to access them!
    
    public DeckOfCartas(){ //Better to create empty deck then can use it for the deck and the hands
        ArrayList<Carta> p = new ArrayList<Carta>(52); 
        pack = p;
        current = 0;
        count = 0;
    }
    
    public ArrayList getPack(){
        return pack;
    }
    
    public Carta cardAt(int i){ 
        return pack.get(i); 
    }
    
    public void shuffle(){
        Collections.shuffle(pack);
    }
 
    public Carta draw(){
        return pack.remove(0); //0 or end of the list?? If change then must change when readding them 
        //to make sure they got to the bottom, i.e old cards inserted at 0, not merely added
    }
    
    public void takeCard(Carta x){
        pack.add(x);
    }
    
    public String toString(){ 
        String x = "";
        for (Carta y : this.pack)
            x += y.toString();
        return x;
    }
    
    public int deckSize(){
        return pack.size();
    }
    
    public int cardsValue(){
        int total = 0;
        for (Carta x : pack){
            total += x.getValue();
        }
        if (total > 21){ //Converts Ace to 1 if BUST
            for (Carta y : pack){
                if (y.getValue()==11)//Set aces to 1 if BUST
                    y.setValue(1);
                total = 0;
                    for (Carta z : pack) //Total is recalculated
                        total += z.getValue();
                }
            }
        return total;
        }
    
    
    public boolean checkBust(){
        if (this.cardsValue() > 21)
            return true;
        else
            return false;
    }//With Aces considered because of value calculation method
    

    public void moveAllToDeck(DeckOfCartas hand){
        for (Carta x : hand.pack){
            if (x.getValue()==1){
                x.setValue(11); //Reset aces to 11 before they go back in the pack
            }
        }
        pack.addAll(hand.getPack());
        hand.getPack().clear();
    }
    
    public void createFullDeck() { 

       for (int j=0; j<=3; j++){
        for (int i=2; i<=14; i++){        
            Carta card;
            card = new Carta("None", "Joker", "Red");
            //This is why enum is SO useful! Don't have to do these cases. 
            //Change on next attempt
            
            if(i<11){
                card.setValue(i);
                card.setSymbol("" + i);
            }
            else{
                switch(i){
                    case 11: card.setSymbol("jack");//When improving enum this and the numbers
                             card.setValue(10);
                    break;
                    case 12: card.setSymbol("queen");
                             card.setValue(10);
                    break;
                    case 13: card.setSymbol("king");
                             card.setValue(10);
                    break;
                    case 14: card.setSymbol("ace");
                             card.setValue(11); //revert it back to 1 if bust and recalculate
                }
            };
            switch(j){
                case 0: card.setSuit("clubs");
                        card.setColor("Black");
                break;
                case 1: card.setSuit("spades");
                        card.setColor("Black");
                break;
                case 2: card.setSuit("diamonds");
                        card.setColor("Red");
                break;
                case 3: card.setSuit("hearts");
                        card.setColor("Red");
                break;                                   
            }

            pack.add(card);
            count++;
        }
       }
       
    }

    
    
}


