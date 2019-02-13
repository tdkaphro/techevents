/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevent.entities;

/**
 *
 * @author Hannachi
 */
public class Enseignant extends User
{   
    private Universite universiteenseignant;
    private String departement;

    public Enseignant() {
    }

    public Universite getUniversiteenseignant() {
        return universiteenseignant;
    }
    
    public void setUniversiteenseignant(Universite universiteenseignant) {
        this.universiteenseignant = universiteenseignant;
    }
    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
         
}
