/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techevents.views;

import java.util.ArrayList;
import java.util.List;
import techevents.entities.club;
import techevents.entities.formation;
import techevents.services.formationcrud;

/**
 *
 * @author ahmed
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        formation a = new formation ();
        a.setNom("soukeh");
        club cl = new club ();
        cl.setNom("soukeh");
        List<formation> fl = new ArrayList<>();
        fl.add(a);
        cl.setFormationscrees(fl);
        formationcrud fc = new formationcrud();
        
        fc.afficherformationparetudiant1(1);
        
    }
    
}
