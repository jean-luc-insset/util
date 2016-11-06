/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.util.factory;

/**
 *
 * @author jldeleage
 */
public class MultipleArgs {
    
    public MultipleArgs() {
        System.out.println("Sans parametre");
    }

    public MultipleArgs(Object... valeurs) {
        System.out.println("Valeurs : " + valeurs);
        for (Object o : valeurs) {
            System.out.println("Arg : " + o);
        }
    }
    
}
