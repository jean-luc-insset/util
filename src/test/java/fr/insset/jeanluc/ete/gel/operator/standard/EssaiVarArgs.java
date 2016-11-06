/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.gel.operator.standard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jldeleage
 */
public class EssaiVarArgs {
    
    public EssaiVarArgs() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void hello() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?>[] constructors = Essai.class.getConstructors();
        for (Constructor c : constructors) {
            switch (c.getParameterCount()) {
                case 0:
                    c.newInstance();
                    break;
                case 1:
                    Parameter p = c.getParameters()[0];
                    construis(c, "Un", "Deux", "Trois");
                    break;
            }
        }       // boucle sur les constructeurs
    }       // hello (test)


    protected Object construis(Constructor c, Object... args) {
        try {
            Object newInstance = c.newInstance(new Object[] { args });
            return newInstance;
        } catch (InstantiationException ex) {
            Logger.getLogger(EssaiVarArgs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EssaiVarArgs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EssaiVarArgs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EssaiVarArgs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}       // EssaiVarArgs (classe de test)



class   Essai {

    public Essai() {
        System.out.println("No parameter");
    }

    public Essai(Object... args) {
        System.out.println("Multiple parameters : " + args);
    }

}