package awele.bot.BotAriolef;

import awele.bot.DemoBot;
import awele.bot.BotAriolef.MaxNodeAriolef;
import awele.bot.BotAriolef.MinMaxNodeAriolef;
import awele.core.Board;
import awele.core.InvalidBotException;

import java.util.ArrayList;

/**
 * @author Alexandre Blansché
 * Bot qui prend ses décisions selon le MinMax
 */
public class MinMaxBotAriolef extends DemoBot {
    /**
     * Profondeur maximale
     */
    private static int MAX_DEPTH = 5;
    private int precedent = -2;

    /**
     * @throws InvalidBotException
     */
    public MinMaxBotAriolef() throws InvalidBotException {
        this.setBotName("MinMax3.0");
        this.addAuthor("A");
    }

    /**
     * Rien à faire
     */
    @Override
    public void initialize() {
    }

    /**
     * Pas d'apprentissage
     */
    @Override
    public void learn() {
    }

    /**
     * Sélection du coup selon l'algorithme MinMax
     */
    @Override
    public double[] getDecision(Board board) {
        int scoreIA = board.getScore(board.getCurrentPlayer());
        int scoreAutre = board.getScore(1 - board.getCurrentPlayer());
        if (scoreIA < scoreAutre) {
            if(board.getNbSeeds() > scoreAutre - scoreIA){
                if (board.getNbSeeds() < 20) {
                    MinMaxBotAriolef.MAX_DEPTH = 7;
                } else {
                    MinMaxBotAriolef.MAX_DEPTH = 6;
                }
            }
            else{
                MinMaxBotAriolef.MAX_DEPTH = 5;
            }
        } else {
            MinMaxBotAriolef.MAX_DEPTH = 5;
        }
        MinMaxNodeAriolef.initialize(board, MinMaxBotAriolef.MAX_DEPTH);
        double[] decision = new MaxNodeAriolef(board).getDecision();
        //double max = 0.0;
        int cpt = 0;
        ArrayList<Integer> indice = new ArrayList<>();
        if(board.getNbSeeds() > 45){
            do {
                //max = -50;
                if (cpt != 0 && this.precedent != -2) {
                    decision[indice.get(indice.size() - 1)] = -50;
                }
                indice = chercheMax(decision, board, indice);
//                for (int i = 0; i < Board.NB_HOLES; i++) {
//                    if (decision[i] > max) {
//                        if(board.getPlayerHoles()[i] > 0){
//                            max = decision[i];
//                        }
//                    }
//                }
//                indice.clear();
//                for (int i = 0; i < Board.NB_HOLES; i++) {
//                    if (decision[i] >= max) {
//                        if(board.getPlayerHoles()[i] > 0){
//                            indice.add(i);
//                        }
//                    }
//                }
                cpt++;
                if(cpt == 6){
                    break;
                }
            }
            while (indice.get(indice.size() - 1) == this.precedent + 1 || indice.get(indice.size() - 1) == this.precedent - 1);
            decision[indice.get(indice.size() - 1)] = Double.MAX_VALUE;
            this.precedent = indice.get(indice.size() - 1);
        }
        else if (board.getNbSeeds() > 10){
            indice = chercheMax(decision, board, indice);
            boolean krou = false;
            if(board.getPlayerHoles()[indice.get(0)] > 3 * Board.NB_HOLES + 2 - indice.get(0)){
                krou = true;
            }
            int min = board.getPlayerHoles()[indice.get(0)];
            int indiceMin = indice.get(0);
            if(indice.size() > 1 && !krou){
                for (int i = 1; i < indice.size(); i++){
                    if(board.getPlayerHoles()[indice.get(i)] > 3 * Board.NB_HOLES + 2 - indice.get(i)){
                        indiceMin = i;
                        break;
                    }
                    if (board.getPlayerHoles()[indice.get(i)] <= min){
                        min = board.getPlayerHoles()[indice.get(i)];
                        indiceMin = i;
                    }
                }
            }
            decision[indiceMin] = Double.MAX_VALUE;
        }
        else{
            //max = -50;
//            for (int i = 0; i < Board.NB_HOLES; i++) {
//                if (decision[i] > max) {
//                    if(board.getPlayerHoles()[i] > 0){
//                        max = decision[i];
//                    }
//                }
//            }
//            indice.clear();
//            for (int i = 0; i < Board.NB_HOLES; i++) {
//                if (decision[i] >= max) {
//                    if(board.getPlayerHoles()[i] > 0){
//                        indice.add(i);
//                    }
//                }
//            }
            indice = chercheMax(decision, board, indice);
            decision[indice.get(indice.size() - 1)] = Double.MAX_VALUE;
        }
        return decision;
    }

    /**
     * Rien à faire
     */
    @Override
    public void finish() {
    }

    public ArrayList<Integer> chercheMax(double [] decision, Board board, ArrayList<Integer> indice){
        double max = -50;
        for (int i = 0; i < Board.NB_HOLES; i++) {
            if (decision[i] > max) {
                if(board.getPlayerHoles()[i] > 0){
                    max = decision[i];
                }
            }
        }
        indice.clear();
        for (int i = 0; i < Board.NB_HOLES; i++) {
            if (decision[i] >= max) {
                if(board.getPlayerHoles()[i] > 0){
                    indice.add(i);
                }
            }
        }
        return indice;
    }
}
