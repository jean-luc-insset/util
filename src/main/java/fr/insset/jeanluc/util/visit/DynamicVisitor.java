/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.visit;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author jldeleage
 */
public interface DynamicVisitor {

    public Object   genericVisit(Object inVisited, Object... inParameters)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;


}
