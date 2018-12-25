/**
 *
 */
package com.ldr.core.user.daos;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.daos.UserDao;


/**
 * @author devreddy
 *
 */
public interface ApparelUserDao extends UserDao
{
	public Boolean isNewCustomer(UserModel userModel);
}
