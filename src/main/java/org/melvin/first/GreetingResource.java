package org.melvin.first;

import lombok.AllArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/niko-ajg")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloToo() {
        @AllArgsConstructor
        class HelloWorld {
            String greetings;
            String name;

            String sayHello() {
                return this.greetings + " " + this.name;
            }
        }

        HelloWorld helloWorld = new HelloWorld("Hello", "niko ajg");
        String hello = "hello";
        String nikoAjg = "niko-ajg";
        return helloWorld.sayHello();
    }
}