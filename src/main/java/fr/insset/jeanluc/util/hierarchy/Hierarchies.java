/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.hierarchy;

/**
 *
 * @author jldeleage
 */
public abstract class Hierarchies {


    public  static void    setParent(Object inChild, Object inParent) {
        ((Hierarchy)inChild).setParent(inParent);
    }

}
