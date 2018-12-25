/**
 *
 */
package com.ldr.core.user.daos.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.daos.impl.DefaultUserDao;

import org.apache.commons.collections.CollectionUtils;

import com.ldr.core.user.daos.ApparelUserDao;


/**
 * @author devreddy
 *
 */
public class ApparelUserDaoImpl extends DefaultUserDao implements ApparelUserDao
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.user.daos.ApparelUserDao#isNewCustomer(de.hybris.platform.core.model.user.UserModel)
	 */
	@Override
	public Boolean isNewCustomer(final UserModel userModel)
	{

		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {pk} FROM {Customer} WHERE {uid}= ?emailId");
		query.addQueryParameter("emailId", userModel.getUid());

		final SearchResult<CustomerModel> searchResults = getFlexibleSearchService().search(query);

		if (CollectionUtils.isNotEmpty(searchResults.getResult())
				&& CollectionUtils.isEmpty(searchResults.getResult().get(0).getOrders()))
		{
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
