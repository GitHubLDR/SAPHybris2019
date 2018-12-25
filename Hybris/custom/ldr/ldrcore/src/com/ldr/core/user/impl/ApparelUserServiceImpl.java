/**
 *
 */
package com.ldr.core.user.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import com.ldr.core.user.ApparelUserService;
import com.ldr.core.user.daos.ApparelUserDao;


/**
 * @author devreddy
 *
 */
public class ApparelUserServiceImpl extends DefaultUserService implements ApparelUserService
{
	private ApparelUserDao apparelUserDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.user.ApparelUserService#isNewCustomer(de.hybris.platform.core.model.user.UserModel)
	 */
	@Override
	public Boolean isNewCustomer(final UserModel userModel)
	{
		return getApparelUserDao().isNewCustomer(userModel);
	}

	/**
	 * @return the apparelUserDao
	 */
	public ApparelUserDao getApparelUserDao()
	{
		return apparelUserDao;
	}

	/**
	 * @param apparelUserDao
	 *           the apparelUserDao to set
	 */
	public void setApparelUserDao(final ApparelUserDao apparelUserDao)
	{
		this.apparelUserDao = apparelUserDao;
	}
}
