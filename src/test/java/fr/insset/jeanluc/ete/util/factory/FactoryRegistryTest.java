package fr.insset.jeanluc.ete.util.factory;


import fr.insset.jeanluc.util.factory.FactoryRegistryImpl;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
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
public class FactoryRegistryTest {


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
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of getRegistry method, of interface FactoryRegistry.
     */
    @Test
    public void testGetRegistry() {
        System.out.println("getRegistry");
        FactoryRegistry.setRegistry(new FactoryRegistryImpl(null));
        FactoryRegistry result = FactoryRegistry.getRegistry();
        assertNotNull(result);
    }

    /**
     * Test of getFactory method, of interface FactoryRegistry.
     */
    @Test
    public void testGetFactory() {
        System.out.println("getFactory");
        String inName = "";
        FactoryRegistryImpl instance = null;
        AbstractFactory expResult = null;
    }


    /**
     * Test of registerFactory method, of class FactoryRegistry.
     */
    @Test
    public void testRegisterFactory_String_AbstractFactory() {
        System.out.println("registerFactory");
        String inName = "";
        AbstractFactory inFactory = null;
        FactoryRegistryImpl instance = null;
//        instance.registerFactory(inName, inFactory);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerFactory method, of class FactoryRegistry.
     */
    @Test
    public void testRegisterFactory_String_Class() throws InstantiationException {
        System.out.println("registerFactory");
        FactoryRegistry root = FactoryRegistry.getRegistry();
        root.registerFactory("test", TestClass1.class);
        root.registerFactory("other", Other.class);
        FactoryRegistry child = root.createChild();
        child.registerFactory("test", TestClass2.class);
        AbstractFactory factory = root.getFactory("test");
        Object instance1 = factory.newInstance();
        assertEquals(instance1.getClass(), TestClass1.class);
        factory = child.getFactory("test");
        Object instance2 = factory.newInstance();
        assertEquals(instance2.getClass(), TestClass2.class);
        factory = child.getFactory("other");
        Object otherInstance = factory.newInstance();
        assertEquals(otherInstance.getClass(), Other.class);
//        instance.registerFactory(null);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDefaultFactory method, of class FactoryRegistry.
     */
    @Test
    public void testRegisterDefaultFactory_String_AbstractFactory() {
        System.out.println("registerDefaultFactory");
        String inName = "";
        AbstractFactory inFactory = null;
        FactoryRegistryImpl instance = null;
//        instance.registerDefaultFactory(inName, inFactory);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDefaultFactory method, of class FactoryRegistry.
     */
    @Test
    public void testRegisterDefaultFactory_String_Class() {
        System.out.println("registerDefaultFactory");
        FactoryRegistryImpl instance = null;
//        instance.registerDefaultFactory(null);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerHierarchyFactory method, of class FactoryRegistry.
     */
    @Test
    public void testRegisterHierarchyFactory() {
        System.out.println("registerHierarchyFactory");
        FactoryRegistryImpl instance = null;
//        instance.registerHierarchyFactory(null);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
