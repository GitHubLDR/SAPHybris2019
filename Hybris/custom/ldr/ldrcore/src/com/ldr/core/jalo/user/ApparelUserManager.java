/**
 *
 */
package com.ldr.core.jalo.user;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.SearchResult;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.UserManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;


/**
 * @author devreddy
 *
 */
public class ApparelUserManager extends UserManager
{
	@Override
	public Collection<? extends Customer> findUsers(final ComposedType type, final String uid, final String name,
			final String description, final boolean disableRestrictions)
	{
		final String typeCode = type != null ? type.getCode() : this.getUserTypeCode();// 342
		final HashMap values = Maps.newHashMapWithExpectedSize(3);// 343
		final String query = this.createQueryForPrincipalWithValues(typeCode, uid, name, description, values);// 344
		return this.getSearchResult(query, values, disableRestrictions, Customer.class);// 346
	}



	private String createQueryForPrincipalWithValues(final String typeCode, final String uid, final String name,
			final String description, final Map values)
	{
		final StringBuilder query = (new StringBuilder("SELECT {")).append(Item.PK).append("} FROM {").append(typeCode).append("}");// 353
		boolean firstCondition = true;// 354
		if (uid != null)
		{// 355
			if (firstCondition)
			{// 357
				query.append(" WHERE ");// 359
			}

			query.append("{").append("uid").append("} = ?uid");

			if (typeCode.equals("Customer"))
			{
				query.append(" OR ");
				query.append("{").append("mobileNumber").append("} = ?uid");
			}

			// 361
			values.put("uid", uid);// 362
			firstCondition = false;// 363
		}

		if (name != null)
		{// 365
			if (firstCondition)
			{// 367
				query.append(" WHERE ");// 369
			}
			else
			{
				query.append(" AND ");// 373
			}

			query.append("{").append("name").append("} = ?name");// 375
			values.put("name", name);// 376
			firstCondition = false;// 377
		}

		if (description != null)
		{// 379
			if (firstCondition)
			{// 381
				query.append(" WHERE ");// 383
			}
			else
			{
				query.append(" AND ");// 387
			}

			query.append("{").append("description").append("} = ?description");// 389
			values.put("description", description);// 390
		}

		//query.append(" ORDER BY {uid}");// 392
		return query.toString();// 394
	}

	private <T> Collection<? extends T> getSearchResult(final String query, final Map values, final boolean disableRestrictions,
			final Class<T> clazz)
	{
		List arg7;
		try
		{
			SessionContext ctx = this.getSession().getSessionContext();// 402
			if (disableRestrictions)
			{// 403
				ctx = this.getSession().createLocalSessionContext(ctx);// 405
				ctx.setAttribute("disableRestrictions", Boolean.TRUE);// 406
			}

			final SearchResult res = this.getSession().getFlexibleSearch().search(ctx, query, values,
					Collections.singletonList(clazz), true, true, 0, -1);// 409 410 415 416
			arg7 = res.getResult();// 418
		}
		finally
		{
			if (disableRestrictions)
			{// 422
				this.getSession().removeLocalSessionContext();// 424
			}

		}

		return arg7;
	}
}
