//package awele.bot.BotAriolef;
//
//import awele.bot.DemoBot;
//import awele.core.Board;
//import awele.core.InvalidBotException;
//
//import java.util.ArrayList;
//
//public class AriolefUCT extends DemoBot{
//    private static final int WIN_SCORE = 10;
//    private int bot;
//    private int adversaire;
//    /**
//     * @throws InvalidBotException
//     **/
//    public AriolefUCT() throws InvalidBotException {
//        this.setBotName("BOT UCT");
//        this.addAuthor("ARIOLI Vincent");
//        this.addAuthor("MEKHILEF Cassandra");
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
//    public double[] getDecision(Board board) {
//        this.bot = board.getCurrentPlayer();
//        this.adversaire = Board.otherPlayer(board.getCurrentPlayer());
//
//        Arbre arbre = new Arbre();
//        Noeud racine = arbre.getRacine();
//        racine.getEtat().setBoard(board);
//        racine.getEtat().setJoueur(this.adversaire);
//        Noeud meilleurNoeud = selectMeilleurNoeud(racine);
//        explorationNoeud(meilleurNoeud);
//        Noeud noeudExplore = meilleurNoeud;
//
//        if (meilleurNoeud.getListeEnfant().size() > 0) {
//            noeudExplore = meilleurNoeud.getRandomNoeudEnfant();
//        }
//        int resultat = simulateRandomPlay(noeudExplore);
//        propagationArrière(noeudExplore, resultat);
//        Noeud noeudVainqueur = racine.getEnfantMaxScore();
//        arbre.setRacine(noeudVainqueur);
//        double[] decision = new double[Board.NB_HOLES];
//        decision[noeudVainqueur.getEtat().getNumTrou()] = 1;
//        return decision;
//    }
//
//    private Noeud selectMeilleurNoeud(Noeud racine) {
//        Noeud noeud = racine;
//        while(noeud.getListeEnfant().size() != 0){
//            noeud = UCT.meilleurNoeud(noeud);
//        }
//        return noeud;
//    }
//
//    private void explorationNoeud(Noeud noeud) {
//        ArrayList<Etat> etatsPossibles = noeud.getEtat().getEtatsPossibles();
//        etatsPossibles.forEach(e -> {
//            Noeud newNoeud = new Noeud(e);
//            newNoeud.setParent(noeud);
//            newNoeud.getEtat().setJoueur(noeud.getEtat().getAdversaire());
//            noeud.getListeEnfant().add(newNoeud);
//        });
//    }
//
//    private void propagationArrière(Noeud noeudExplore, int resultat) {
//        Noeud noeudTmp = noeudExplore;
//        while(noeudTmp != null){
//            noeudTmp.getEtat().newVisite();
//            if(noeudTmp.getEtat().getJoueur() == resultat){
//                noeudTmp.getEtat().ajoutScore(WIN_SCORE);
//            }
//            noeudTmp = noeudTmp.getParent();
//        }
//    }
//
//    private int simulateRandomPlay(Noeud noeud) {
//        Noeud noeudTmp = new Noeud(noeud);
//        Etat etatTmp = noeudTmp.getEtat();
//        Board copy = etatTmp.getBoard();
//        // Si on perd
//        if (infoPartie(copy) == this.adversaire){
//            noeudTmp.getParent().getEtat().setScoreVic(Integer.MIN_VALUE);
//            return this.adversaire;
//        }
//        // Partie toujours en cours
//        while(infoPartie(copy) == -1){
//            etatTmp.chgmtJoueur();
//            copy = etatTmp.randomCoup();
//        }
//        return infoPartie(copy);
//    }
//
//    private int infoPartie(Board board){
//        int scoreBot = board.getScore(this.bot);
//        int scoreAdversaire = board.getScore(this.adversaire);
//        boolean botFini = true;
//        boolean adversaireFini = true;
//        // Verif coup impossible
//        for (int i = 0; i < Board.NB_HOLES; i++){
//            if(this.bot == board.getCurrentPlayer()){
//                if(board.getPlayerHoles()[i] != 0){
//                    botFini = false;
//                }
//                if (board.getOpponentHoles()[i] != 0){
//                    adversaireFini = false;
//                }
//            }
//            else{
//                if(board.getOpponentHoles()[i] != 0){
//                    botFini = false;
//                }
//                if(board.getPlayerHoles()[i] != 0){
//                    adversaireFini = false;
//                }
//            }
//        }
//        // Victoire adverse
//        if(botFini || scoreAdversaire >= 25 || ((board.getNbSeeds() <= 6) && (scoreBot < scoreAdversaire))){
//            return this.adversaire;
//        }
//        // Victoire de nous
//        else if(adversaireFini || scoreBot >= 25 || ((board.getNbSeeds() <= 6) && (scoreAdversaire < scoreBot))){
//            return this.bot;
//        }
//        // Egalite
//        else if(board.getNbSeeds() <= 6 && scoreAdversaire == scoreBot){
//            return -2;
//        }
//        // Partie en cours
//        else{
//            return -1;
//        }
//    }
//
//    @Override
//    public void learn() {
//
//    }
//}
