package fr.isep.francois.projetapplication;

import java.util.ArrayList;

public class Tache {

    // Attributs:
    private String etat;
    private String priorite;
    private String description;
    private String nom;  // Nom de la tâche
    private ArrayList<Employe> liste_employe_projet;

    // Constructeur mis à jour pour inclure le nom de la tâche
    public Tache(String nom, String etat, String priorite, String description, ArrayList<Employe> liste_employe_projet) {
        this.nom = nom;  // Initialisation du nom de la tâche
        this.etat = etat;
        this.priorite = priorite;
        this.description = description;
        this.liste_employe_projet = liste_employe_projet;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Employe> getListeEmployeProjet() {
        return liste_employe_projet;
    }

    public void setListeEmployeProjet(ArrayList<Employe> liste_employe_projet) {
        this.liste_employe_projet = liste_employe_projet;
    }

    // Méthode pour créer une nouvelle tâche
    public static Tache creerTache(String nom, String etat, String priorite, String description, ArrayList<Employe> liste_employe_projet) {
        Tache nouvelleTache = new Tache(nom, etat, priorite, description, liste_employe_projet);
        return nouvelleTache;
    }

    // Méthode pour modifier une tâche existante
    public void modifierTache(String nom, String etat, String priorite, String description, ArrayList<Employe> liste_employe_projet) {
        this.nom = nom;
        this.etat = etat;
        this.priorite = priorite;
        this.description = description;
        this.liste_employe_projet = liste_employe_projet;
    }

    // Méthode pour supprimer une tâche d'une liste de tâches
    public static void supprimerTache(ArrayList<Tache> listeTaches, Tache tache) {
        if (listeTaches.contains(tache)) {
            listeTaches.remove(tache);
            //System.out.println("Tâche supprimée.");
        } else {
            //System.out.println("Tâche non trouvée.");
        }
    }

    // Méthode pour afficher les informations de la tâche (optionnel)
    public void afficherTache() {
        System.out.println("Nom de la tâche: " + nom);  // Affichage du nom
        System.out.println("Etat: " + etat);
        System.out.println("Priorité: " + priorite);
        System.out.println("Description: " + description);
        System.out.println("Liste des employés:");
        for (Employe employe : liste_employe_projet) {
            System.out.println("- " + employe.getNom() + " " + employe.getPrenom());
        }
    }

    public void attribuerTache(Employe employe) {
        if (!this.liste_employe_projet.contains(employe)) {
            this.liste_employe_projet.add(employe);
        }
    }
    // Méthode pour désaffecter une tâche d'un employé
    public void desaffecterTache(Employe employe) {
        if (this.liste_employe_projet.contains(employe)) {
            this.liste_employe_projet.remove(employe);
            System.out.println("L'employé " + employe.getNom() + " a été désaffecté de la tâche " + this.nom);
        } else {
            System.out.println("L'employé " + employe.getNom() + " n'est pas affecté à cette tâche.");
        }
    }
}
