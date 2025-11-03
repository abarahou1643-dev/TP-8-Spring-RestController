package ma.rest.spring.services;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;

import java.util.List;
import java.util.Optional;

/**
 * Interface de service pour la gestion des comptes
 */
public interface ICompteService {

    Compte createCompte(Compte compte);

    List<Compte> getAllComptes();

    Optional<Compte> getCompteById(Long id);

    Compte updateCompte(Long id, Compte compteDetails);

    boolean deleteCompte(Long id);

    List<Compte> getComptesByType(TypeCompte type);

    Compte deposer(Long compteId, Double montant);

    Compte retirer(Long compteId, Double montant);
}