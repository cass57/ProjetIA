package awele.bot.BotAriolef;

public class Arbre {
    Noeud racine;
    
    public Arbre(){
        racine = new Noeud();
    }
    
    public Arbre(Noeud racine){
        this.racine = racine;
    }

    public void setRacine(Noeud racine) {
        this.racine = racine;
    }

    public Noeud getRacine() {
        return racine;
    }
    
    public void ajoutEnfant(Noeud parent, Noeud enfant){
        parent.getListeEnfant().add(enfant);
    }
}
