//package awele.bot.BotAriolef;
//
//import awele.bot.DemoBot;
//import awele.core.Board;
//import awele.core.InvalidBotException;
//import it.unimi.dsi.fastutil.floats.FloatOpenCustomHashSet;
//
//public class AriolefStrategies extends DemoBot {
//
//    private boolean milieu = false;
//    /** Profondeur maximale */
//    private static final int PROF = 12;
//    private int bot;
//    private int adversaire;
//
//    public AriolefStrategies() throws InvalidBotException {
//        this.setBotName("AriolefStrategies");
//        this.addAuthor("Pas Blansché");
//    }
//
//    @Override
//    public void initialize() {
//
//    }
//
//    @Override
//    public void finish() {
//
//    }
//
//    @Override
//    public double[] getDecision(Board board) throws InvalidBotException {
//        this.bot = board.getCurrentPlayer();
//        this.adversaire = 1 - this.bot;
//        //Debut de partie -> Tant qu'on peut faire un last pair
//        int cpt = 0;
//        double[] decision = new double[Board.NB_HOLES];
//        if (!milieu) {
//            for (int i = Board.NB_HOLES-1; i >= 0; i -= 2) {
//                if (board.getPlayerHoles()[i] != 0) {
//                    decision[i] = 1;
//                    break;
//                } else {
//                    cpt++;
//                }
//            }
//            if (cpt == Board.NB_HOLES / 2) {
//                milieu = true;
//            }
//        }
//        //Strategie milieu et fin
//        else {
//            //Strategie milieu
//            if (board.getNbSeeds() > 15) {
//                int indexTrou = -1;
//                int indexMenacant = -1;
//                //Attaquer la case menacante
//                loop:
//                for (int i = 0; i < Board.NB_HOLES; i++) {
//                    if (board.getPlayerHoles()[i] <= 2 && board.getPlayerHoles()[i] > 0) {
//                        indexTrou = i + 6;
//                        for (int j = Board.NB_HOLES-1; j >= 0; j--) {
//                            //Si l'adversaire peut atteindre la case menacée
//                            if (board.getOpponentHoles()[j] + j == indexTrou) {
//                                indexMenacant = j + 6;
//                                break loop;
//                            }
//                        }
//                    }
//                }
//                if (indexTrou != -1) {
//                    if (indexMenacant != -1) {
//                        for (int i = 0; i < Board.NB_HOLES; i++) {
//                            //Si on peut attaquer le menacant
//                            if (board.getPlayerHoles()[i] + i == indexMenacant) {
//                                decision[i] = 1;
//                                break;
//                            }
//                        }
//                    } else {
//                        for (int i = 0; i < Board.NB_HOLES; i++) {
//                            //Jouer la case menacée
//                            if (board.getPlayerHoles()[i] == 1) {
//                                decision[i] = 1;
//                                break;
//                            }
//                            //Sauver la case menacée
//                            else if (board.getPlayerHoles()[i] == 2) {
//                                if (i > 0) {
//                                    if (board.getPlayerHoles()[i - 1] != 0) {
//                                        decision[i - 1] = 1;
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                //MinMax
//                else {
//                    for(int i = 0; i < Board.NB_HOLES; i++){
//                        decision[i] = minMax(board, i, false, Integer.MIN_VALUE, Integer.MAX_VALUE, this.bot, 1);
//                    }
//                }
//            }
//
//            //Strategie fin
//            else {
//                boolean affame = false;
//                //Si on gagne ou egalité
//                if(board.getScore(bot) >= board.getScore(adversaire)){
//                    //Affamé l'adversaire
//                    //TODO : Essayer dans l'autre sens
//                    for(int i = 0; i < Board.NB_HOLES-1; i++) {
//                        if(board.getPlayerHoles()[i] + i < Board.NB_HOLES){
//                            decision[i] = 1;
//                            affame = true;
//                            break;
//                        }
//                    }
//                    //TODO : Normalement piège mais Cass a pas compris
//                    if(!affame){
//                        for(int i = 0; i < Board.NB_HOLES; i++){
//                            decision[i] = minMax(board, i, false, Integer.MIN_VALUE, Integer.MAX_VALUE, this.bot, 1);
//                        }
//                    }
//                }
//                //Sinon MinMax
//                else {
//                    for(int i = 0; i < Board.NB_HOLES; i++){
//                        decision[i] = minMax(board, i, false, Integer.MIN_VALUE, Integer.MAX_VALUE, this.bot, 1);
//                    }
//                }
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
//                return (double) copy.getScore(this.bot) - copy.getScore(this.adversaire);
//            }
//
//        } catch (InvalidBotException e) {
//            e.printStackTrace();
//        }
//        return 0.0;
//    }
//
//    @Override
//    public void learn() {
//
//    }
//}
