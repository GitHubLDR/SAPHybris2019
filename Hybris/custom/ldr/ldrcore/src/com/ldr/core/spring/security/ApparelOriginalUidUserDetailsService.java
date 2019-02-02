/**
 *
 */
package com.ldr.core.spring.security;

import de.hybris.platform.commerceservices.spring.security.OriginalUidUserDetailsService;
import de.hybris.platform.jalo.JaloItemNotFoundException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.security.PrincipalGroup;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.spring.security.CoreUserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ldr.core.jalo.user.ApparelUserManager;


/**
 * @author devreddy
 *
 */
public class ApparelOriginalUidUserDetailsService extends OriginalUidUserDetailsService
{

	private final String rolePrefix = "ROLE_";

	@Override
	public CoreUserDetails loadUserByUsername(final String username)
	{
		if (username == null)
		{

			return null;


		}
		else
		{
			User user;
			try
			{
				user = ApparelUserManager.getInstance().getCustomerByLogin(username);


			}
			catch (final JaloItemNotFoundException arg5)
			{
				throw new UsernameNotFoundException("User not found!");
			}

			final boolean enabled = this.isNotAnonymousOrAnonymousLoginIsAllowed(user) && !user.isLoginDisabledAsPrimitive();





			String password = user.getEncodedPassword(JaloSession.getCurrentSession().getSessionContext());


			if (password == null)
			{

				password = "";
			}

			final CoreUserDetails userDetails = new CoreUserDetails(user.getLogin(), password, enabled, true, true, true,
					this.getAuthorities(user), JaloSession.getCurrentSession().getSessionContext().getLanguage().getIsoCode());



			return userDetails;
		}
	}


	private Collection<GrantedAuthority> getAuthorities(final User user)
	{
		final Set groups = user.getGroups();
		final ArrayList authorities = new ArrayList(groups.size());
		final Iterator itr = groups.iterator();
		while (itr.hasNext())
		{

			final PrincipalGroup group = (PrincipalGroup) itr.next();
			authorities.add(new SimpleGrantedAuthority(this.rolePrefix + group.getUID().toUpperCase()));
			final Iterator arg6 = group.getAllGroups().iterator();
			while (arg6.hasNext())
			{
				final PrincipalGroup gr = (PrincipalGroup) arg6.next();
				authorities.add(new SimpleGrantedAuthority(this.rolePrefix + gr.getUID().toUpperCase()));
			}
		}

		return authorities;
	}

}
