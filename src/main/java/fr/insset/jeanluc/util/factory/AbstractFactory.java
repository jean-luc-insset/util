/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.factory;

/**
 *
 * @author jldeleage
 */
public interface AbstractFactory {

    public Object   newInstance(Object... inArgs) throws InstantiationException;

}
