/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author Gareth
 */
public class Game {//Badly named, should be BlackJack and main is Game. 
    //Was thinking of adding other classes and then in main being able to
    //choose which game to play/class to implement in main.
    private DeckOfCartas deck, dealer, player;
    private int gameNumber, wins;
    
    public Game() {
        
        deck = new DeckOfCartas();
        deck.createFullDeck();
        dealer = new DeckOfCartas();
        player = new DeckOfCartas();
        gameNumber = 0;
        wins = 0;
    }
    
 
    
    public void setUp() {
        this.deck.shuffle(); 
       for (int i=0; i<2; i++){
        player.takeCard(deck.draw());
        dealer.takeCard(deck.draw());
        }   
    } 
    
    public void playerHit(){
        player.takeCard(deck.draw());
    }
    
    public void dealerHit(){
        dealer.takeCard(deck.draw());
    }
    
    public String gameStatus(){
        String status;
        status = this.playerStatus();
        status += "\n" + this.dealerStatus();
        return status;
    }
    
    public String playerStatus(){
        String status; 
        status = "Your Hand: ";
        for (int i=0; i < player.getPack().size(); i++)
            status += player.getPack().get(i).toString() + " ";
        status += "\n" + "Value of: " + player.cardsValue();
        return status;}
    
    public String dealerStatus(){
        String status;
        status = "Dealers Hand: "; //Hide one card later
        for (int i=0; i<dealer.getPack().size(); i++)
            status += dealer.getPack().get(i).toString() + " "; 
        status += "\n" + "Value of: " + dealer.cardsValue();
        return status;//Could be replaced with System.out.println if want to be fully autonomous
        }
    
    public DeckOfCartas getPlayer(){
        return player;
    }
    
    public boolean checkPlayerBust(){ //I think I've done over kill here because forgot
        //about using this, cleanup later. Maybe make this return BUST! string. 
        return player.checkBust();
    }
    
    public boolean checkDealerBust(){
        return dealer.checkBust();
    }
    
    public void DealerPlays(){
        int average, threshold;
        average = (380 - (this.dealer.cardsValue() + this.player.cardsValue()))/(deck.getPack().size());
        //Average of a card in the rest of the pack, ALL aces set to 11
        //Try with ALL aces set to one OR averaging aces OR if aces are showing....
        threshold = average + dealer.cardsValue();//Threshold with which dealer will hit in case of a draw
        while ((this.checkPlayerBust()==false && player.cardsValue()>dealer.cardsValue()) 
                || (this.checkPlayerBust()==false && player.cardsValue()==dealer.cardsValue()
                && threshold<=21))
            this.dealerHit();
        } 
       
    public String result(){
        String result;
        
        if ((this.checkDealerBust()==false && this.checkPlayerBust()==true) 
                || ((this.checkDealerBust()==false)&&(player.cardsValue()<dealer.cardsValue())))
        result = "Dealer wins";
        else if ((this.checkDealerBust()==true && this.checkPlayerBust()==true)//Perhaps easier to do last? 
                    || ((this.checkDealerBust()==false && this.checkPlayerBust()==false) && 
                (player.cardsValue()==dealer.cardsValue())))
            result = "Draw";
        else {
         result = "You win";
         this.wins++;
        }
        this.gameNumber++;
        return result;
    }
    
    public void collectHands(){
        deck.moveAllToDeck(player); //In real blackjack the cards are discarded. 
        deck.moveAllToDeck(dealer);
    }

    public String endRound(){
            String closingStatement; //LOL
            closingStatement = "Number of games won: " + this.wins;
            closingStatement += "\n" + "Number of games lost: " + (this.gameNumber-this.wins);
            closingStatement += "\n" + "Number of games played: " + (this.gameNumber);
            return closingStatement;
        }
    
    public void runBlackJack(){
        Scanner scan = new Scanner(System.in);
        
        int hit, again;
        do{
            this.setUp();
            System.out.println(this.gameStatus());
            do{
        System.out.println("Do you want to Hit(1) or Stick(2): ");
        hit = scan.nextInt();
        if (hit == 1){
            System.out.println("Hit ME!");
            this.playerHit();
            System.out.println(this.playerStatus());
            if (this.checkPlayerBust()==true){
                System.out.println("BUST!"); 
                hit = 2;}
            }
        else
            System.out.println("Stick");
       
        } while (hit == 1);
        
        System.out.println("Dealer's turn");
        this.DealerPlays();
        
        System.out.println(this.dealerStatus());
        System.out.println(this.result());
        System.out.println(this.endRound());
        
        System.out.println("Do you want to play again? Yes(1) No(2)");
                
        again = scan.nextInt();
        
        this.collectHands();
        
        } while (again == 1);
        
        System.out.println("End session");
}
    }
    

