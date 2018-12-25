/**
 *
 */
package com.ldr.promotions.ruleengineservices.converters.populator;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ruleengineservices.converters.populator.CartRaoPopulator;
import de.hybris.platform.ruleengineservices.rao.CartRAO;

import com.ldr.core.user.ApparelUserService;


/**
 * @author devreddy
 *
 */
public class ApparelCartRaoPopulator extends CartRaoPopulator
{
	private ApparelUserService userservice;


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.ruleengineservices.converters.populator.CartRaoPopulator#populate(de.hybris.platform.core.model
	 * .order.AbstractOrderModel, de.hybris.platform.ruleengineservices.rao.CartRAO)
	 */
	@Override
	public void populate(final AbstractOrderModel source, final CartRAO target)
	{
		super.populate(source, target);
		target.setNewCustomer(isNewCustomer(source));
	}

	/**
	 * @param source
	 * @return
	 */
	private Boolean isNewCustomer(final AbstractOrderModel source)
	{
		if (!getUserservice().isAnonymousUser(source.getUser()))
		{
			return getUserservice().isNewCustomer(source.getUser());
		}
		return Boolean.FALSE;
	}

	/**
	 * @return the userservice
	 */
	public ApparelUserService getUserservice()
	{
		return userservice;
	}

	/**
	 * @param userservice
	 *           the userservice to set
	 */
	public void setUserservice(final ApparelUserService userservice)
	{
		this.userservice = userservice;
	}
}
