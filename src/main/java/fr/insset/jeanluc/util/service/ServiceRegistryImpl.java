package fr.insset.jeanluc.util.service;


import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author jldeleage
 */
public class ServiceRegistryImpl implements ServiceRegistry {


    private ServiceRegistryImpl() {
        
    }


    public static ServiceRegistryImpl getRegistry() {
        return registry;
    }


    @Override
    public  void    registerService(String inName, Object inService) {
        services.put(inName, inService);
    }


    @Override
    public  Object  getService(String inName) {
        return services.get(inName);
    }



    private static  ServiceRegistryImpl     registry = new ServiceRegistryImpl();

    private         Map<String, Object> services = new HashMap<>();


}       // ServiceRegistry

