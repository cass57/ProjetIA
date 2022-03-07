//package awele.bot.BotAriolef;
//
//import awele.bot.DemoBot;
//import awele.core.Board;
//import awele.core.InvalidBotException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class AriolefMinMax extends DemoBot
//{
//    /** Profondeur maximale */
//    private static final int PROF = 12;
//    private int bot;
//    private int adversaire;
//
//    /**
//     * @throws InvalidBotException
//     */
//    public AriolefMinMax () throws InvalidBotException
//    {
//        this.setBotName ("AriolefMinMax");
//        this.addAuthor ("Pas Blansché");
//    }
//
//    /**
//     * Rien à faire
//     */
//    @Override
//    public void initialize ()
//    {
//    }
//
//    /**
//     * Pas d'apprentissage
//     */
//    @Override
//    public void learn ()
//    {
//    }
//
//    /**
//     * Sélection du coup selon l'algorithme MinMax
//     */
//    @Override
//    public double [] getDecision (Board board) throws InvalidBotException {
//        this.bot = board.getCurrentPlayer();
//        this.adversaire = 1 - this.bot;
//        int noeuds = 0;
//        HashMap<Long, Double> visite = new HashMap<>();
//
//        int n = Board.NB_HOLES;
//        double[] decision = new double[n];
//
//        boolean recupPoint = false;
//        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
//        // recherche les trous à 1 ou 2 de l'adversaire
//        for (int j = 0; j < Board.NB_HOLES; j++) {
//            if(board.getOpponentHoles()[j] == 2 || board.getOpponentHoles()[j] == 1){
//                recupPoint = true;
//                index.put(j, board.getOpponentHoles()[j]);
//            }
//        }
//
//        for(int i = 0; i < n; i++){
//            if(board.getPlayerHoles()[i] != 0){
////                if(recupPoint){
////                    // pour heuristique 2
////                    int scoreOui = 6;
////                    int indexOui = -1;
////                    // heuristique 1
////                    // teste si on peut atteindre les trous à 1 ou 2 de l'adversaire avec ce coup
////                    /*for(Map.Entry<Integer, Integer> entry : index.entrySet()){
////                        int ind = entry.getKey() + Board.NB_HOLES;
////                        int nb = entry.getValue();
////
////                        double[] dec = new double[Board.NB_HOLES];
////                        if(i+board.getPlayerHoles()[i] == ind && nb == 2){
////                            dec[i] = 1;
////                            // TODO : changer en >=
////                            if(board.playMoveSimulationScore(bot, dec) > scoreOui){
////                                scoreOui = board.playMoveSimulationScore(bot, dec);
////                                indexOui = i;
////                            }
////                        }
////                    }*/
////                    //  heuristique 3
////                    for(Map.Entry<Integer, Integer> entry : index.entrySet()){
////                        int ind = entry.getKey() + Board.NB_HOLES;
////                        int nb = entry.getValue();
////
////                        double[] dec = new double[Board.NB_HOLES];
////                        if(i+board.getPlayerHoles()[i] == ind && nb == 2){
////                            indexOui = i;
////                        }
////                    }
////                    if(indexOui != -1)
////                    {
////                        decision[i] = Double.MAX_VALUE;
////                    }
////                    else {
////                        for(int j = Board.NB_HOLES-1; j >= 0; j--){
////                            if(board.getPlayerHoles()[j] != 0){
////                                decision[j] = Double.MAX_VALUE;
////                                break;
////                            }
////                        }
////                    }
////                }
////                else {
//                    decision[i] = minMax(board, i, false, Integer.MIN_VALUE, Integer.MAX_VALUE, this.bot, 1);
////                }
//            }
//            else {
//                decision[i] = Double.MIN_VALUE;
//            }
//        }
//        return decision;
//    }
//
//    private double minMax(Board board, int i, boolean isMax, int minValue, int maxValue, int joueur, int prof) {
//        Board copy = (Board) board.clone();
//        //System.out.println(copy.toString());
//        //System.out.println(board.getOpponentHoles()[0]);
//        //System.out.println(board.getPlayerHoles()[0]);
//
//        double[] decision2 = new double[Board.NB_HOLES];
//        for(int j = 0; j < Board.NB_HOLES; j++){
//            if(i == j){
//                decision2[j] = Integer.MAX_VALUE;
//            }
//            else {
//                decision2[j] = Integer.MIN_VALUE;
//            }
//        }
//        int score = 0;
//        try {
//            score = copy.playMoveSimulationScore(joueur, decision2);
//            copy = copy.playMoveSimulationBoard(joueur, decision2);
//            if ((score < 0) ||
//                    (copy.getScore (Board.otherPlayer (copy.getCurrentPlayer ())) >= 25) ||
//                    (copy.getNbSeeds () <= 6)) {
//                if(copy.getScore(this.bot) <= copy.getScore(this.adversaire)) {
//                    return Double.MIN_VALUE;
//                }
//                else
//                {
//                    return (double) copy.getScore(this.bot) - copy.getScore(this.adversaire);
//                }
//
//            }
//            else if(prof < this.PROF) {
//                if (isMax) {
//                    double pire = -Double.MIN_VALUE;
//
//                    for (int j = 0; j < Board.NB_HOLES; j++) {
//                        if(copy.getPlayerHoles()[j] != 0){
//                            pire = Math.max(pire, minMax(copy, j, false, minValue, maxValue, 1 - joueur, prof++));
//                        }
//                        else return pire;
//                    }
//                    return pire;
//                } else {
//                    double pire = Double.MAX_VALUE;
//                    for (int j = 0; j < Board.NB_HOLES; j++) {
//                        if(copy.getPlayerHoles()[j] != 0){
//                            pire = Math.min(pire, minMax(copy, j, true, minValue, maxValue, 1 - joueur, prof++));
//                        }
//                        else return pire;
//                    }
//                    return pire;
//                }
//            }
//            else {
//               return (double) copy.getScore(this.bot) - copy.getScore(this.adversaire);
//            }
//
//        } catch (InvalidBotException e) {
//            e.printStackTrace();
//        }
//        return 0.0;
//    }
//
//
//    /**
//     * Rien à faire
//     */
//    @Override
//    public void finish ()
//    {
//    }
//}
