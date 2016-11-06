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
public interface Hierarchy<T> {
    
    public T getParent();
    public void setParent(T inParent);

}
