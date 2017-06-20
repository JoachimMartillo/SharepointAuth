package edu.webdev

/**
  * Created by algotrader on 6/19/17.
  */

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.GenericType
import com.sun.jersey.api.client.WebResource
import com.sun.jersey.api.client.config.DefaultClientConfig
import org.codehaus.jackson.jaxrs.JacksonJsonProvider

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.List

object CoursesClientRequest {
  //private static final Logger logger = LoggerFactory.getLogger( CoursesClientRequest.class );
  val logger: Logger = LoggerFactory.getLogger(CoursesClientRequest.getClass().toString(): String)
  var client: Client = null
  val applicationServiceURI: String = "http://localhost:3000/courses"
  // should be configurable

  def main(args: Array[String]): Unit = {
    logger.info("Starting the test client")
    System.out.println("Class Path is " + System.getProperty("java.class.path"))

    initializeClient()

    try {
      logger.info("curl 'http://localhost:3000/courses/area/Cs' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/area/Cs' -H 'Content-Type: application/json'")
      sendGetRequest("/area/CS")

      logger.info("curl 'http://localhost:3000/courses/area/Ac' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/area/Ac' -H 'Content-Type: application/json'")
      sendGetRequest("/area/Ac")

      logger.info("curl 'http://localhost:3000/courses/area/AD' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/area/AD' -H 'Content-Type: application/json'")
      sendGetRequest("/area/AD")

      logger.info("curl 'http://localhost:3000/courses/area/*' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/area/*' -H 'Content-Type: application/json'")
      sendGetRequest("/area/*")

      logger.info("curl 'http://localhost:3000/courses/area/C' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/area/C' -H 'Content-Type: application/json'")
      sendGetRequest("/area/C")

      logger.info("curl 'http://localhost:3000/courses/instructor/Barbara' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/Barbara' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/Barbara")

      logger.info("curl 'http://localhost:3000/courses/instructor/Da' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/Da' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/Da")

      logger.info("curl 'http://localhost:3000/courses/instructor/ixzy' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/ixzy' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/ixzya")

      logger.info("curl 'http://localhost:3000/courses/instructor/*' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/*' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/*")

      logger.info("curl 'http://localhost:3000/courses/instructor/z' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/z' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/z")

      logger.info("curl 'http://localhost:3000/courses/instructor/q' -H 'Content-Type: application/json'")
      System.out.println("curl 'http://localhost:3000/courses/instructor/q' -H 'Content-Type: application/json'")
      sendGetRequest("/instructor/q")
    } catch {
      case e: Exception => {
        e.printStackTrace()
        System.err.println("Something went wrong!")
      }
    }
  }

  def initializeClient(): Unit = {
    var defaultClientConfig: DefaultClientConfig = new DefaultClientConfig
    defaultClientConfig.getClasses().add(classOf[JacksonJsonProvider])
    client = Client.create(defaultClientConfig)
  }

  /*
      static public void initializeClient() {
          DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
          defaultClientConfig.getClasses().add( JacksonJsonProvider.class );
          client = Client.create( defaultClientConfig );
      }
   */

  @throws(classOf[RuntimeException])
  def sendGetRequest(str: String): Unit = {
    // note that formal paramters are immutable in Scala.
    // hence the assignment of the value to s
    var s: String = str
    logger.debug("argument: {}", str)
    if (s == null) s = ""
    try {
      var webResource: WebResource = client.resource(applicationServiceURI + s)
      var response: ClientResponse = webResource.accept("application/json").get(classOf[ClientResponse])
      if (response.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
          + response.getStatus())
      }
      var slist: List[String] = response.getEntity(new GenericType[List[String]]() {})

      System.out.println(slist)
    } catch {
      case e: Exception => e.printStackTrace()
    }

  }

  /*
    static void sendGetRequest(String s) throws RuntimeException {
        logger.debug( "argument: {}", s );

        if (s == null) {
            s = "";
        }

        try {
            WebResource webResource = client.resource( applicationServiceURI + s );
            ClientResponse response = webResource.accept( "application/json" )
                    .get( ClientResponse.class );
            if (response.getStatus() != 200) {
                throw new RuntimeException( "Failed : HTTP error code : "
                        + response.getStatus() );
            }
            List<String> slist = response.getEntity( new GenericType<List<String>>() {
            } );

            System.out.println( slist );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  */
}
