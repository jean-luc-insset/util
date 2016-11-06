package fr.insset.jeanluc.ete.util;


import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.ete.util.factory.MultipleArgs;
import fr.insset.jeanluc.ete.util.factory.NoArgs;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.insset.jeanluc.util.factory.FactoryRegistry;



/**
 *
 * @author jldeleage
 */
public class FactoryRegistryTest {
    
    public final static String      NO_ARGS  = "noArgs";
    public final static String      MULTIPLE = "multiple";
    
    public FactoryRegistryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerFactory(NO_ARGS, NoArgs.class);
        registry.registerFactory(MULTIPLE, MultipleArgs.class);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of registerFactory method, of class FactoryRegistry.
     */
    @Test
    public void testNoArgs() throws InstantiationException {
        System.out.println("simple");
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(NO_ARGS);
        Object newInstance = factory.newInstance();
        assertNotNull(newInstance);
    }
    
    @Test
    public void testMultiple() throws InstantiationException {
        System.out.println("multiple");
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(MULTIPLE);
        Object newInstance = factory.newInstance();
        assertNotNull(newInstance);
    }

    @Test
    public void testMultiple2() throws InstantiationException {
        System.out.println("multiple 2");
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(MULTIPLE);
        Object newInstance = factory.newInstance("Un", "Deux", "Trois");
        assertNotNull(newInstance);
    }


}




