package ma.rest.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Classe entité représentant un compte bancaire
 */
@Entity
@Table(name = "compte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le solde ne peut pas être null")
    @Min(value = 0, message = "Le solde ne peut pas être négatif")
    private Double solde;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "La date de création ne peut pas être null")
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de compte ne peut pas être null")
    private TypeCompte type;

    public Compte(Double solde, Date dateCreation, TypeCompte type) {
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }
}