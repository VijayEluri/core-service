package core.service.security;

import java.lang.reflect.Method;

import core.service.annotation.Security;
import core.service.exception.ServiceSecurityException;
import core.service.session.ClientServiceSession;

public class ServiceSecurityImpl implements ServiceSecurity
{

	@Override
	public void authenticate(ClientServiceSession session, Class serviceInterface, Method method, Object[] args)
	{
        Security security = method.getAnnotation(Security.class);
        if (security == null)
        {
            throw new IllegalStateException("Failed to find Security annotation on service method.");
        }
        if (session == null) 
        {
            throw new ServiceSecurityException("Cannot invoke service, no security entity available (requiredPermission="
                    + security.permissionKey()
                    + ").");
        }
        if (session.getSecurityEntity() == null) 
        {
            throw new ServiceSecurityException("Cannot invoke service, no session available (entity="
                    + session.getSecurityEntity()
                    + ",requiredPermission="
                    + security.permissionKey()
                    + ").");
        }
		
        if (session.getSecurityEntity().getPermissionIds() != null) 
        {
        	String[] permissions = session.getSecurityEntity().getPermissionIds();
        	for (int index = 0; index < permissions.length; index++) 
        	{
        		if (security.permissionKey().equals(permissions[index])) 
        		{
        			return;
        		}
        	}
        }
        throw new ServiceSecurityException("Security entity does not have permission to invoke service (entity="
                + session.getSecurityEntity()
                + ",requiredPermission="
                + security.permissionKey()
                + ").");
	}
}
