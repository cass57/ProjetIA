package awele.bot.BotAriolef;

import awele.core.Board;
import awele.core.InvalidBotException;

import java.util.ArrayList;

public class Etat {
    private Board board;
    private int joueur;
    private int nbVisite;
    private double scoreVic;
    private int numTrou;

    public Etat(){
        this.board = new Board();
    }

    public Etat(Etat etat){
        this.board = (Board) etat.getBoard().clone();
        this.joueur = etat.getJoueur();
        this.nbVisite = etat.getNbVisite();
        this.scoreVic = etat.getScoreVic();
    }

    public Etat(Etat etat, int numTrou){
        this.board = (Board) etat.getBoard().clone();
        this.joueur = etat.getJoueur();
        this.nbVisite = etat.getNbVisite();
        this.scoreVic = etat.getScoreVic();
        this.numTrou = numTrou;
    }

    public Etat(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public int getNbVisite() {
        return nbVisite;
    }

    public void setNbVisite(int nbVisite) {
        this.nbVisite = nbVisite;
    }

    public double getScoreVic() {
        return scoreVic;
    }

    public void setScoreVic(double scoreVic) {
        this.scoreVic = scoreVic;
    }

    public int getNumTrou() {
        return numTrou;
    }

    public void setNumTrou(int numTrou) {
        this.numTrou = numTrou;
    }

    public ArrayList<Etat> getEtatsPossibles(){
        ArrayList<Etat> etatsPossibles = new ArrayList<Etat>();
        ArrayList<Integer> coupsPossibles = new ArrayList<Integer>();
        for (int i = 0; i < Board.NB_HOLES; i++){
            if(board.getPlayerHoles()[i] != 0){
                coupsPossibles.add(i);
            }
        }
        coupsPossibles.forEach(c -> {
            double [] decision = new double [Board.NB_HOLES];
            decision [c] = 1;
            Etat newEtat = new Etat(this.board);
            newEtat.setJoueur(1-board.getCurrentPlayer());
            try {
                newEtat.getBoard().playMoveSimulationBoard(newEtat.getJoueur(), decision);
            } catch (InvalidBotException e) {
                e.printStackTrace();
            }
            newEtat.setNumTrou(c);
            etatsPossibles.add(newEtat);
        });
        return etatsPossibles;
    }

    public void newVisite(){
        this.nbVisite++;
    }

    public void ajoutScore(double score){
        if(this.scoreVic != Integer.MIN_VALUE){
            this.scoreVic += score;
        }
    }

    public Board randomCoup(){
        ArrayList<Integer> coupsPossibles = new ArrayList<Integer>();
        for (int i = 0; i < Board.NB_HOLES; i++){
            if(board.getPlayerHoles()[i] != 0){
                coupsPossibles.add(i);
            }
        }
        int total = coupsPossibles.size();
        int selectRandom = (int) (Math.random() * total);
        double [] decision = new double [Board.NB_HOLES];
        decision [selectRandom] = 1;
        try {
            this.board = this.board.playMoveSimulationBoard(this.joueur, decision);
        } catch (InvalidBotException e) {
            e.printStackTrace();
        }
        return this.board;
    }

    public void chgmtJoueur(){
        this.joueur = 1-this.joueur;
    }

    public int getAdversaire(){
        return 1-this.joueur;
    }
}
