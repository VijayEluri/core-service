
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
 */package core.service.exception;


/**
 * Unexpected problem occured with determing the return type
 * in the <code>ServiceProxy</code>
 * 
 * @author worleyc
 *
 */
public class ServiceResultException extends ServiceException
{

    public ServiceResultException()
    {
        super();
    }

    public ServiceResultException(String message)
    {
        super(message);
    }

    public ServiceResultException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ServiceResultException(Throwable cause)
    {
        super(cause);
    }

}
