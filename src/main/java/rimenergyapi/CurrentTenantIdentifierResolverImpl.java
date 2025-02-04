package rimenergyapi;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import rimenergyapi.multitenancy.util.RequestContextHolderUtils;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {		
		return RequestContextHolderUtils.getCurrentTenantIdentifier();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
