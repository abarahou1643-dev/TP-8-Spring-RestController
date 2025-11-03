package ma.rest.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des comptes bancaires
 */
@RestController
@RequestMapping("/api/banque")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@Slf4j
@Tag(name = "Gestion des Comptes", description = "API pour la gestion des comptes bancaires")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    @Operation(summary = "Obtenir tous les comptes", description = "Retourne la liste de tous les comptes bancaires")
    @GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Compte>> getAllComptes() {
        try {
            log.info("Récupération de tous les comptes");
            List<Compte> comptes = compteRepository.findAll();
            log.info("{} comptes récupérés", comptes.size());
            return ResponseEntity.ok(comptes);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des comptes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtenir un compte par ID", description = "Retourne un compte spécifique par son identifiant")
    @GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompteById(
            @Parameter(description = "ID du compte", required = true) @PathVariable Long id) {

        log.info("Recherche du compte avec ID: {}", id);

        if (id == null || id <= 0) {
            log.warn("ID de compte invalide: {}", id);
            return ResponseEntity.badRequest().build();
        }

        Optional<Compte> compte = compteRepository.findById(id);
        return compte.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau compte", description = "Crée un nouveau compte bancaire")
    @PostMapping(value = "/comptes",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> createCompte(
            @Parameter(description = "Compte à créer", required = true)
            @Valid @RequestBody Compte compte) {

        log.info("Création d'un nouveau compte: {}", compte);

        try {
            if (compte.getSolde() < 0) {
                return ResponseEntity.badRequest().build();
            }

            if (compte.getDateCreation() == null) {
                compte.setDateCreation(new Date());
            }

            Compte savedCompte = compteRepository.save(compte);
            log.info("Compte créé avec succès, ID: {}", savedCompte.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompte);

        } catch (Exception e) {
            log.error("Erreur lors de la création du compte", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Mettre à jour un compte", description = "Met à jour un compte existant")
    @PutMapping(value = "/comptes/{id}",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> updateCompte(
            @Parameter(description = "ID du compte à mettre à jour", required = true) @PathVariable Long id,
            @Parameter(description = "Nouvelles données du compte", required = true)
            @Valid @RequestBody Compte compteDetails) {

        log.info("Mise à jour du compte ID: {} avec les données: {}", id, compteDetails);

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Compte> compteOpt = compteRepository.findById(id);
        if (compteOpt.isPresent()) {
            Compte compte = compteOpt.get();
            compte.setSolde(compteDetails.getSolde());
            compte.setDateCreation(compteDetails.getDateCreation());
            compte.setType(compteDetails.getType());

            Compte updatedCompte = compteRepository.save(compte);
            log.info("Compte ID: {} mis à jour avec succès", id);
            return ResponseEntity.ok(updatedCompte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un compte", description = "Supprime un compte par son ID")
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(
            @Parameter(description = "ID du compte à supprimer", required = true) @PathVariable Long id) {

        log.info("Tentative de suppression du compte ID: {}", id);

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Compte> compteOpt = compteRepository.findById(id);
        if (compteOpt.isPresent()) {
            compteRepository.delete(compteOpt.get());
            log.info("Compte ID: {} supprimé avec succès", id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtenir les comptes par type", description = "Retourne les comptes filtrés par type")
    @GetMapping(value = "/comptes/type/{type}", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Compte>> getComptesByType(
            @Parameter(description = "Type de compte", required = true) @PathVariable TypeCompte type) {

        log.info("Recherche des comptes de type: {}", type);
        List<Compte> comptes = compteRepository.findByType(type);
        return ResponseEntity.ok(comptes);
    }

    @Operation(summary = "Effectuer un dépôt", description = "Dépose un montant sur un compte")
    @PostMapping("/comptes/{id}/depot")
    public ResponseEntity<Compte> deposer(
            @Parameter(description = "ID du compte", required = true) @PathVariable Long id,
            @Parameter(description = "Montant à déposer", required = true) @RequestParam Double montant) {

        log.info("Dépôt de {} sur le compte ID: {}", montant, id);

        if (montant <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Compte> compteOpt = compteRepository.findById(id);
        if (compteOpt.isPresent()) {
            Compte compte = compteOpt.get();
            double nouveauSolde = compte.getSolde() + montant;
            compte.setSolde(nouveauSolde);

            Compte updatedCompte = compteRepository.save(compte);
            log.info("Dépôt effectué. Nouveau solde: {}", nouveauSolde);
            return ResponseEntity.ok(updatedCompte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Effectuer un retrait", description = "Retire un montant d'un compte")
    @PostMapping("/comptes/{id}/retrait")
    public ResponseEntity<Compte> retirer(
            @Parameter(description = "ID du compte", required = true) @PathVariable Long id,
            @Parameter(description = "Montant à retirer", required = true) @RequestParam Double montant) {

        log.info("Retrait de {} du compte ID: {}", montant, id);

        if (montant <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Compte> compteOpt = compteRepository.findById(id);
        if (compteOpt.isPresent()) {
            Compte compte = compteOpt.get();
            if (compte.getSolde() < montant) {
                log.warn("Solde insuffisant pour le retrait. Solde: {}, Montant: {}",
                        compte.getSolde(), montant);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            double nouveauSolde = compte.getSolde() - montant;
            compte.setSolde(nouveauSolde);
            Compte updatedCompte = compteRepository.save(compte);
            log.info("Retrait effectué. Nouveau solde: {}", nouveauSolde);
            return ResponseEntity.ok(updatedCompte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}