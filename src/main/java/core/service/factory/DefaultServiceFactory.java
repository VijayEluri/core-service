package core.service.factory;

import java.util.HashMap;
import java.util.Map;

import core.service.config.DefaultServiceConfig;
import core.service.config.ServiceConfig;
import core.service.exception.ServiceException;
import core.tooling.logging.LogFactory;
import core.tooling.logging.Logger;

/**
 * @author cworley
 *
 */
public class DefaultServiceFactory implements ServiceFactory {
	
	/** logger for this class */
	private Logger logger = LogFactory.getLogger(DefaultServiceFactory.class);
	
	/** registered services for this factory instance */
	private Map<Class, Class> services;
	
	/** service configuration for this factory */
	private ServiceConfig serviceConfig;

	/**
	 * construct factory with given service configuration.
	 * 
	 * Construct factory instance with empty service register map.
	 * 
	 */
	public DefaultServiceFactory(ServiceConfig serviceConfig) {
		super();
		services = new HashMap<Class, Class>();
		this.serviceConfig = serviceConfig;
	}

	/**
	 * Default constructor for factory.
	 */
	public DefaultServiceFactory() {
		this(new DefaultServiceConfig());
	}

	/**
	 * Add service to factory registry.
	 * 
	 * Add service implementation class to registry for the given interface class.  If the
	 * implementation class is null a <code>ServiceException</code> will be thrown.
	 * 
	 * If the interface class already exist, the current entry will be overwritten.  This event
	 * will be logged at level DEBUG.
	 * 
	 * @param interfaceClass
	 * @param implClass
	 */
	public void addService(Class interfaceClass, Class implClass) {
		if (implClass == null) {
			throw new ServiceException("Implementation class cannot be null (interfaceClass={0}).", interfaceClass.getName());
		}
		
		Class existingImplClass = services.get(interfaceClass);
		if (existingImplClass != null) {
			logger.debug("Service interface already exist, overwriting implementation {0} with {1}.",
					interfaceClass.getName(),
					implClass.getName());
		}
		services.put(interfaceClass, implClass);
	}

	@Override
	public Object createService(Class serviceInterface) {
		Class implClass = services.get(serviceInterface);
		
		if (implClass == null) {
			throw new ServiceException("No service registered with factory for interface {0}.", serviceInterface.getName());
		}
		
		Object instance;
		try {
			instance = implClass.newInstance();
		} catch (InstantiationException e) {
			throw new ServiceException("Cannot instantiate service class {0} for interface {1}.", implClass.getName(), serviceInterface.getName());
		} catch (IllegalAccessException e) {
			throw new ServiceException("Cannot instantiate service class {0} for interface {1}.", implClass.getName(), serviceInterface.getName());
		}
		
		return instance;
	}

	/* (non-Javadoc)
	 * @see core.service.factory.ServiceFactory#getServiceConfig()
	 */
	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	/**
	 * @param serviceInterface
	 */
	public void removeService(Class serviceInterface) {
		services.remove(serviceInterface);
	}
}
