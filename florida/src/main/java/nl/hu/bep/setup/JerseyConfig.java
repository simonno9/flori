package nl.hu.bep.setup;
import nl.hu.bep.shopping.filters.AuthenticationFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.hu.bep.shopping.webservices");
        register(AuthenticationFilter.class);
        register(RolesAllowedDynamicFeature.class);

    }
}
