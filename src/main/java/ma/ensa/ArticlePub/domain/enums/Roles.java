package ma.ensa.ArticlePub.domain.enums;

public enum Roles {
	COMITE("COMITE"), 
	REFERE("REFERE"),
	AUTEUR("AUTEUR"), 
	SUBSCRIBED("SUBSCRIBED");
 
    private String role;
 
    Roles(String role) {
        this.role = role;
    }
 
    public String getRole() {
        return role;
    }

}
