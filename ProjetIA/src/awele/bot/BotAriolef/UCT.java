package awele.bot.BotAriolef;

import java.util.Collections;
import java.util.Comparator;

public class UCT {
    public static double uctValue(int totalVisites, double scoreNoeud, int visitesNoeud){
        if(visitesNoeud == 0){
            return Integer.MAX_VALUE;
        }
        return (scoreNoeud / (double) visitesNoeud) + 1.41 * Math.sqrt(Math.log(totalVisites)/(double)visitesNoeud);
    }

    static Noeud meilleurNoeud(Noeud noeud){
        int visitesParent = noeud.getEtat().getNbVisite();
        return Collections.max(
                noeud.getListeEnfant(),
                Comparator.comparing(c -> uctValue(visitesParent, c.getEtat().getScoreVic(), c.getEtat().getNbVisite())));
    }
}
