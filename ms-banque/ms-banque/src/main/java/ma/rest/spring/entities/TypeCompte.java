package ma.rest.spring.entities;

/**
 * Enumération représentant les types de comptes bancaires
 */
public enum TypeCompte {

    COURANT,
    EPARGNE;

    public static TypeCompte fromString(String type) {
        if (type != null) {
            for (TypeCompte typeCompte : TypeCompte.values()) {
                if (type.equalsIgnoreCase(typeCompte.name())) {
                    return typeCompte;
                }
            }
        }
        throw new IllegalArgumentException("Type de compte invalide: " + type);
    }
}