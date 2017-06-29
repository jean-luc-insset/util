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
public interface AbstractFactory<T> {

    public <T> T     newInstance(Object... inArgs) throws InstantiationException;
    public Class<T>  getBuiltClass();

}
