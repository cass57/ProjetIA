package awele.bot.BotAriolef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Noeud {
    private Etat etat;
    private Noeud parent;
    private ArrayList<Noeud> listeEnfant;

    public Noeud(){
        this.etat = new Etat();
        this.listeEnfant = new ArrayList<Noeud>();
    }

    public Noeud(Etat etat){
        this.etat = etat;
        this.listeEnfant = new ArrayList<Noeud>();
    }

    public Noeud(Etat etat, Noeud parent, ArrayList<Noeud> listeEnfant){
        this.etat = etat;
        this.parent = parent;
        this.listeEnfant = new ArrayList<Noeud>();
    }

    public Noeud(Noeud noeud){
        this.listeEnfant = new ArrayList<Noeud>();
        this.etat = new Etat(noeud.getEtat());
        if(noeud.getParent() != null){
            this.parent = noeud.getParent();
        }
        ArrayList<Noeud> listeEnfant = noeud.getListeEnfant();
        for(Noeud enfant : listeEnfant){
            this.listeEnfant.add(new Noeud(enfant));
        }
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Noeud getParent() {
        return parent;
    }

    public void setParent(Noeud parent) {
        this.parent = parent;
    }

    public ArrayList<Noeud> getListeEnfant() {
        return listeEnfant;
    }

    public void setListeEnfant(ArrayList<Noeud> listeEnfant) {
        this.listeEnfant = listeEnfant;
    }

    public Noeud getRandomNoeudEnfant(){
        int nbCoupPossible = this.listeEnfant.size();
        int selectRandom = (int) (Math.random() * nbCoupPossible);
        return this.listeEnfant.get(selectRandom);
    }

    public Noeud getEnfantMaxScore(){
        return Collections.max(this.listeEnfant, Comparator.comparing(c -> {
            return c.getEtat().getNbVisite();
        }));
    }
}
