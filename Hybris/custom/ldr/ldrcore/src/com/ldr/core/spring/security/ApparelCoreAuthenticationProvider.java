/**
 *
 */
package com.ldr.core.spring.security;

import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.spring.security.CoreAuthenticationProvider;
import de.hybris.platform.spring.security.CoreUserDetails;

import java.util.Collections;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @author devreddy
 *
 */
public class ApparelCoreAuthenticationProvider extends CoreAuthenticationProvider
{
	private final UserDetailsChecker postAuthenticationChecks = new ApparelCoreAuthenticationProvider.DefaultPostAuthenticationChecks();

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.spring.security.CoreAuthenticationProvider#authenticate(org.springframework.security.core.
	 * Authentication)
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException
	{
		if (Registry.hasCurrentTenant() && JaloConnection.getInstance().isSystemInitialized())
		{// 102
			final String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();// 104
			UserDetails userDetails = null;// 106

			try
			{
				userDetails = this.retrieveUser(username);// 110
			}
			catch (final UsernameNotFoundException arg5)
			{// 112
				throw new BadCredentialsException(
						this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"), arg5);// 114 115
			}

			this.getPreAuthenticationChecks().check(userDetails);// 118
			final User user = UserManager.getInstance().getCustomerByLogin(userDetails.getUsername());// 120
			final Object credential = authentication.getCredentials();// 122
			if (credential instanceof String)
			{// 124
				if (!user.checkPassword((String) credential))
				{// 127
					throw new BadCredentialsException(
							this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));// 130 131
				}
			}
			else
			{
				if (!(credential instanceof LoginToken))
				{// 134
					throw new BadCredentialsException(
							this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));// 146
				}

				if (!user.checkPassword((LoginToken) credential))
				{// 137
					throw new BadCredentialsException(
							this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));// 140 141
				}
			}

			this.additionalAuthenticationChecks(userDetails, (AbstractAuthenticationToken) authentication);// 149
			this.postAuthenticationChecks.check(userDetails);// 150
			JaloSession.getCurrentSession().setUser(user);// 153
			return this.createSuccessAuthentication(authentication, userDetails);// 154
		}
		else
		{
			return this.createSuccessAuthentication(authentication, new CoreUserDetails("systemNotInitialized",
					"systemNotInitialized", true, false, true, true, Collections.EMPTY_LIST, (String) null));// 158 160 161
		}
	}

	private class DefaultPostAuthenticationChecks implements UserDetailsChecker
	{
		private DefaultPostAuthenticationChecks()
		{
		}// 247

		public void check(final UserDetails user)
		{
			if (!user.isCredentialsNonExpired())
			{// 252
				throw new CredentialsExpiredException(ApparelCoreAuthenticationProvider.this.messages
						.getMessage("CoreAuthenticationProvider.credentialsExpired", "User credentials have expired"));// 254 255
			}
		}// 257
	}


}
