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
public class Admin extends User
{
    private Universite universiteadmin ;

    public Admin() {
        super();
    }
    
    public Universite getUniversiteadmin() {
        return universiteadmin;
    }

    public void setUniversiteadmin(Universite universiteadmin) {
        this.universiteadmin = universiteadmin;
    }   
}
