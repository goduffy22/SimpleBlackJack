/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;



/**
 *
 * @author Gareth
 */
public class Carta {
    
    private String suit;
    private String royalnum;
    private String color;
    private int value;
   
    
    public Carta(String suit, String royalnum, String color) {
        this.suit = suit;
        this.royalnum = royalnum;
        this.color = color;
              
        value = 0;  
    } 
    
    public String getSuit(){ //could be enum
        return suit;
    }
    
    public String getSymbol(){ //could make enum
        return royalnum;
    }
    
    public void setSuit(String type){
        suit = type;
        
    }
    
    public void setSymbol(String symbol){
        royalnum = symbol;
    }
    
    public void setColor(String col){ //could make enum, not useful for blackjack
        color = col;
    }
        
    public void setValue(int points) {
        value = points;
    }
    
    public int getValue(){
        return value;
    }
    
    public String toString(){
        String card;
        card = royalnum + " of " + suit + " ";
        return card;
    }
    
    
}

