/**
 *
 */
package com.ldr.core.user;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;


/**
 * @author devreddy
 *
 */
public interface ApparelUserService extends UserService
{

	public Boolean isNewCustomer(UserModel userModel);
}
