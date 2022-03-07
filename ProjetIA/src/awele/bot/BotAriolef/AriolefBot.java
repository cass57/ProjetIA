/*
package awele.bot.BotAriolef;

import awele.bot.CompetitorBot;
import awele.bot.DemoBot;
import awele.core.Board;
import awele.core.InvalidBotException;

import java.util.Random;

public class AriolefBot extends DemoBot {

    private Random random;
    */
/**
     * @throws InvalidBotException
     *//*

    public AriolefBot() throws InvalidBotException {
        this.setBotName("BotAriolef");
        this.addAuthor("ARIOLI Vincent");
        this.addAuthor("MEKHILEF Cassandra");
    }

    */
/**
     * Fonction d'initalisation du bot
     * Cette fonction est appelée avant chaque affrontement
     *//*

    public void initialize (){

    }

    */
/**
     * Fonction de finalisation du bot
     * Cette fonction est appelée après chaque affrontement
     *//*

    public void finish (){

    }

    */
/**
     * Fonction de prise de décision du bot
     * @param board État du plateau de jeu
     * @return Un tableau de six réels indiquant l'efficacité supposée de chacun des six coups possibles
     *//*

    public double [] getDecision (Board board)  {
        // On récupère les joueurs
        int playerBot = board.getCurrentPlayer();
        int adversaire = Board.otherPlayer(board.getCurrentPlayer());
        double [] decision = new double [Board.NB_HOLES];
        */
/* On crée un tableau des évaluations des coups à jouer pour chaque situation possible *//*

        loop:
        // Pour chaque coup possible
        for (int i = 0; i < Board.NB_HOLES; i++){
            // Si le trou n'est pas vide
            if(board.getPlayerHoles () [i] != 0){
                // tableau de coup à jouer pour la simulation de la fin de partie
                double [] decision2 = new double [Board.NB_HOLES];
                // coup à tester actuel à 1 pour qu'il soit choisi
                decision2 [i] = 1;
                try{
                    double nbVic = 0.0;
                    // Simulation de 100 fin de parties aléatoires
                    for(int j = 0; j < 100 ;j++){
                        Board copy = (Board) board.clone ();
                        // simulation du coup : on récupère le score et le nouveau board
                        int score = copy.playMoveSimulationScore (copy.getCurrentPlayer (), decision2);
                        copy = copy.playMoveSimulationBoard(copy.getCurrentPlayer(),decision2);
                        // Tant que la partie n'est pas finie
                        while(!((score < 0) ||
                                (copy.getScore (Board.otherPlayer (copy.getCurrentPlayer ())) >= 25) ||
                                (copy.getNbSeeds () <= 6))){
                            decision2 = new double [Board.NB_HOLES];
                            // on associe des valeurs aléatoires aux coups suivants
                            for (int z = 0; z < decision2.length; z++){
                                decision2 [z] = this.random.nextDouble ();
                            }
                            // On simule le coup
                            score = copy.playMoveSimulationScore (copy.getCurrentPlayer (), decision2);
                            copy = copy.playMoveSimulationBoard(copy.getCurrentPlayer(),decision2);
                        }
                        // On comptabilise les victoires
                        if(copy.getScore(playerBot) > copy.getScore(adversaire)){
                            nbVic+=1;
                        }
                    }
                    // On fait un ratio de victoires pour chaque coups possibles
                    decision[i] = nbVic/100;
                    // On coupe la boucle si on trouve un coup avec 100% de victoires
                    if(decision[i] == 1.00){
                        break loop;
                    }
                }catch (InvalidBotException e)
                {
                    decision [i] = 0;
                }

            }
        }
        return decision;
    }

    */
/**
     * Apprentissage du bot
     * Cette fonction est appelée une fois (au chargement du bot)
     *//*

    public void learn (){
        this.random = new Random (System.currentTimeMillis ());
    }
}
*/
