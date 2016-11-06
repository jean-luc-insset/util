/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util;

/**
 *
 * @author jldeleage
 */
public class EteException extends Exception {

    public EteException() {
    }

    public EteException(String message) {
        super(message);
    }

    public EteException(String message, Throwable cause) {
        super(message, cause);
    }

    public EteException(Throwable cause) {
        super(cause);
    }

    public EteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}
