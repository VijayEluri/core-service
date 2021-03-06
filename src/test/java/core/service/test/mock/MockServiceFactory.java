
/**
 * Copyright 2009 Core Information Solutions LLC
 *
 * This file is part of Core Service Framework.
 *
 * Core Service Framework is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 *
 * Core Service Framework is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Core Service Framework.  If not, see 
 * <http://www.gnu.org/licenses/>.
 */package core.service.test.mock;

import core.service.exception.ServiceException;
import core.service.executor.local.ServiceInstantiator;

public class MockServiceFactory implements ServiceInstantiator
{

    @Override
    public Object instantiateService(Class interfaceClass)
    {
        if (interfaceClass.equals(MathService.class)) 
        {
            return new MathServiceImpl();
        }
        else if (interfaceClass.equals(SleepService.class))
        {
            return new SleepServiceImpl();
        }
        else if (interfaceClass.equals(ProcessApplicationService.class))
        {
            return new ProcessApplicationServiceImpl();
        }
        else if (interfaceClass.equals(ThrowExceptionService.class))
        {
            return new ThrowExceptionServiceImpl();
        }
        throw new ServiceException("No service found for: " + interfaceClass.getName());
    }

}
