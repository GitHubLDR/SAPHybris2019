/**
 *
 */
package com.ldr.core.intrceptors;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.apache.log4j.Logger;

import com.ldr.core.model.BrandModel;


/**
 * @author devreddy
 *
 */
public class BrandInterceptor implements LoadInterceptor<BrandModel>, InitDefaultsInterceptor<BrandModel>,
		PrepareInterceptor<BrandModel>, ValidateInterceptor<BrandModel>, RemoveInterceptor<BrandModel>
{
	private static final Logger LOG = Logger.getLogger(BrandInterceptor.class);

	/*
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.RemoveInterceptor#onRemove(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onRemove(final BrandModel brandModel, final InterceptorContext interceptorContext) throws InterceptorException
	{
		LOG.info("=========RemoveInterceptor Called=========");

	}

	/*
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final BrandModel brandModel, final InterceptorContext interceptorContext) throws InterceptorException
	{
		LOG.info("=========PrepareInterceptor Called=========");

	}

	/*
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final BrandModel brandModel, final InterceptorContext interceptorContext) throws InterceptorException
	{
		LOG.info("=========ValidateInterceptor Called=========");

	}

	/*
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor#onInitDefaults(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onInitDefaults(final BrandModel brandModel, final InterceptorContext interceptorContext)
			throws InterceptorException
	{
		LOG.info("=========InitDefaultsInterceptor Called=========");

	}

	/*
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.LoadInterceptor#onLoad(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onLoad(final BrandModel arg0, final InterceptorContext arg1) throws InterceptorException
	{
		LOG.info("=========LoadInterceptor Called=========");
	}

}
