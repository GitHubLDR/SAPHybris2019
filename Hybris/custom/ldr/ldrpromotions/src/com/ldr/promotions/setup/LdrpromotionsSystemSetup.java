/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.ldr.promotions.setup;

import static com.ldr.promotions.constants.LdrpromotionsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.ldr.promotions.constants.LdrpromotionsConstants;
import com.ldr.promotions.service.LdrpromotionsService;


@SystemSetup(extension = LdrpromotionsConstants.EXTENSIONNAME)
public class LdrpromotionsSystemSetup
{
	private final LdrpromotionsService ldrpromotionsService;

	public LdrpromotionsSystemSetup(final LdrpromotionsService ldrpromotionsService)
	{
		this.ldrpromotionsService = ldrpromotionsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		ldrpromotionsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return LdrpromotionsSystemSetup.class.getResourceAsStream("/ldrpromotions/sap-hybris-platform.png");
	}
}
