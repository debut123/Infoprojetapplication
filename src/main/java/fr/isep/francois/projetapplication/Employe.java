package fr.isep.francois.projetapplication;


import java.util.ArrayList;
import java.time.LocalDate;


public class Employe {

    // initialisation de la liste des employés.

static ArrayList<Employe> liste_employe=new ArrayList<>();

// initialisation des attributs.

 int identifiant;

    String nom;

   String prenom;

   int age;


  String type;

    ArrayList<String>liste_projet_realise;

    static LocalDate date_embauche;

    public Employe(int identifiant, String nom, String prenom,
                   int age, String type, ArrayList<String> liste_projet_realise,
                   LocalDate date_embauche) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.type = type;
        this.liste_projet_realise = liste_projet_realise;
        this.date_embauche = date_embauche;
    }
    // Getter et setter:

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getListe_projet_realise() {
        return liste_projet_realise;
    }

    public void setListe_projet_realise(ArrayList<String> liste_projet_realise) {
        this.liste_projet_realise = liste_projet_realise;
    }

    public ArrayList<Employe> getListe_employe() {
        return liste_employe;
    }

    public void setListe_employe(ArrayList<Employe> liste_employe) {
        this.liste_employe = liste_employe;
    }

    public LocalDate getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(LocalDate date_embauche) {
        this.date_embauche = date_embauche;
    }
// principales fonctions:

    public void ajouterProjet(String projet) {
        // Ajouter le projet à la liste des projets réalisés
        this.liste_projet_realise.add(projet);
    }

public void ajouter_employe(int identifiant, String nom, String prenom,
                                   int age, String type, ArrayList<String> liste_projet_realise,
                                   LocalDate date_emboche){

        Employe employe=new Employe(identifiant, nom, prenom, age, type, liste_projet_realise, date_emboche);
        liste_employe.add(employe);
}

public void suprimer_employe(int identifiant){

    Employe employeASupprimer = null;
    for (Employe employe : liste_employe) {
        if (employe.getIdentifiant() == identifiant) {
            employeASupprimer = employe;
            break; // Arrêter dès que l'employé est trouvé
        }
    }

    // Si l'employé est trouvé, on le supprime
    if (employeASupprimer != null) {
        liste_employe.remove(employeASupprimer);

    }
    }
    public void modifier_employe(int identifiant, String nom, String prenom,
                                int age, String type, ArrayList<String> liste_projet_realise,
                                LocalDate date_emboche){

        Employe employe=new Employe(identifiant, nom, prenom, age, type, liste_projet_realise, date_embauche);
        suprimer_employe(identifiant);
        liste_employe.add(employe);
    }



}
