/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.service;

/**
 *
 * @author jldeleage
 */
public interface ServiceRegistry {

    Object getService(String inName);

    void registerService(String inName, Object inService);
    
}
