package fr.isep.francois.projetapplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Projet {

    // Attributs
    private String nom;
    private ArrayList<Tache> liste_tache_projet;
    private LocalDate date_limite;
    private Map<Employe, String> liste2_employe_projet; // Mappage Employé -> Rôle
    private ArrayList<Projet> listeProjets;

    // Constructeur mis à jour : Prend en compte un mappage Employé -> Rôle
    public Projet(String nom, ArrayList<Tache> liste_tache_projet, LocalDate date_limite,
                  Map<Employe, String> liste2_employe_projet) {
        this.nom = nom;
        this.liste_tache_projet = liste_tache_projet;
        this.date_limite = date_limite;
        this.liste2_employe_projet = liste2_employe_projet; // Initialisation du mappage
        this.listeProjets = new ArrayList<>();
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Tache> getListe_tache_projet() {
        return liste_tache_projet;
    }

    public void setListe_tache_projet(ArrayList<Tache> liste_tache_projet) {
        this.liste_tache_projet = liste_tache_projet;
    }

    public LocalDate getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(LocalDate date_limite) {
        this.date_limite = date_limite;
    }

    public Map<Employe, String> getListe2_employe_projet() {
        return liste2_employe_projet;
    }

    public void setListe2_employe_projet(Map<Employe, String> liste2_employe_projet) {
        this.liste2_employe_projet = liste2_employe_projet;
    }

    // Méthodes de gestion des employés et rôles
    public void ajouterEmploye(Employe employe, String role) {
        if (!this.liste2_employe_projet.containsKey(employe)) {
            this.liste2_employe_projet.put(employe, role);
            System.out.println("L'employé a été ajouté avec le rôle: " + role);
        } else {
            System.out.println("L'employé est déjà dans le projet.");
        }
    }

    public void supprimerEmploye(Employe employe) {
        if (this.liste2_employe_projet.containsKey(employe)) {
            this.liste2_employe_projet.remove(employe);
            System.out.println("L'employé a été supprimé du projet.");
        } else {
            System.out.println("L'employé n'a pas été trouvé dans ce projet.");
        }
    }

    public void modifierRoleEmploye(Employe employe, String nouveauRole) {
        if (this.liste2_employe_projet.containsKey(employe)) {
            this.liste2_employe_projet.put(employe, nouveauRole);
            System.out.println("Le rôle de l'employé a été modifié en : " + nouveauRole);
        } else {
            System.out.println("L'employé n'existe pas dans ce projet.");
        }
    }

    // Méthodes pour gérer les tâches
    public void ajouterTache(Tache tache) {
        if (!this.liste_tache_projet.contains(tache)) {
            this.liste_tache_projet.add(tache);
        }
    }

    public void supprimerTache(Tache tache) {
        if (this.liste_tache_projet.contains(tache)) {
            this.liste_tache_projet.remove(tache);
        }
    }

    public void modifierTache(Tache tacheExistante, Tache tacheModifiee) {
        int index = this.liste_tache_projet.indexOf(tacheExistante);
        if (index != -1) {
            this.liste_tache_projet.set(index, tacheModifiee);
        }
    }

    // Méthodes pour gérer les projets
    public void ajouterProjet(Projet projet) {
        if (!this.listeProjets.contains(projet)) {
            this.listeProjets.add(projet);
        }
    }

    public void supprimerProjet(String nomProjet) {
        for (int i = 0; i < this.listeProjets.size(); i++) {
            Projet projet = this.listeProjets.get(i);
            if (projet.getNom().equals(nomProjet)) {
                this.listeProjets.remove(i);
                break;
            }
        }
    }
    // Fonction pour modifier les informations d'un projet
    public void modifierProjet(String nouveauNom, ArrayList<Tache> nouvellesTaches,
                               LocalDate nouvelleDateLimite,
                               Map<Employe, String> nouveauxEmployesAvecRoles) {
        // Mettre à jour les informations du projet
        if (nouveauNom != null && !nouveauNom.isEmpty()) {
            this.nom = nouveauNom;
        }
        if (nouvellesTaches != null) {
            this.liste_tache_projet = nouvellesTaches;
        }
        if (nouvelleDateLimite != null) {
            this.date_limite = nouvelleDateLimite;
        }
        if (nouveauxEmployesAvecRoles != null) {
            this.liste2_employe_projet = nouveauxEmployesAvecRoles;
        }

        System.out.println("Le projet a été modifié avec succès.");
    }

    // Méthode pour afficher les employés et leurs rôles dans un projet
    public void afficherEmployes() {
        if (this.liste2_employe_projet.isEmpty()) {
            System.out.println("Aucun employé n'a été affecté à ce projet.");
        } else {
            for (Map.Entry<Employe, String> entry : this.liste2_employe_projet.entrySet()) {
                Employe employe = entry.getKey();
                String role = entry.getValue();
                System.out.println("Employé : " + employe.getNom() + " " + employe.getPrenom() +
                        " - Rôle : " + role);
            }
        }
    }
}
