package org.acme.client;


import org.acme.dto.CustomerDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/products")
@RegisterRestClient
@ApplicationScoped
public interface ProductClient {

    @GET
    @Path("/{id}")
    CustomerDTO getCustomerById(@PathParam("id") Long id);

}
