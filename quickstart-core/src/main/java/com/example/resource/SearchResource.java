package com.example.resource;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Path("/{elasticsearch}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    private JestClient jestClient;

    @Inject
    public SearchResource(JestClient jestClient) {
        this.jestClient = jestClient;
    }

    @GET
    @Path("/search/{text_id}")
    public Response getUser(@PathParam("text_id") String text_id) throws IOException {
        Map<String, String> source = new LinkedHashMap<String,String>();
        source.put("user", "kimchy2");
        Index index = new Index.Builder(source).index("twitter").type("tweet").id("1").build();
        jestClient.execute(index);

        return Response.ok()
                .build();
    }
}
