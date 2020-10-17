package ma.ensa.ArticlePub.domain.enums;

public enum Etats 
{
    ATTENTE("En Attente"), 
    MODIFIABLE("En Modification"),
    ACCEPTE("Accepté"), 
    REFUSE("refusé"),
	PUBLIE("publié");
 
    private String etat;
 
    Etats(String etat) {
        this.etat = etat;
    }
 
    public String getEtat() {
        return etat;
    }
}
