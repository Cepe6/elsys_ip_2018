package org.elsys.ip.rest.resource;

import org.elsys.ip.rest.model.Test;
import org.elsys.ip.rest.service.TestService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Path("test")
public class TestResource {

  private TestService testService = new TestService();

  /**
   * Returns all the available objects.
   *
   * @return List<Test>
   */
  @GET
  @Produces("application/json")
  public List<Test> getTestList() {
    List<Test> testList = testService.getTestList();
    return testList;
  }

  /**
   * Returns an object with the given ID.
   *
   * @param id unique identifier of an object
   * @return Test
   */
  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Test getTest(@PathParam("id") Integer id) {
    return testService.getTestById(id);
  }

  /**
   * Endpoint, which when accessed by a browser enables file download.
   *
   * @return Response
   */
  @GET
  @Path("/download")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response downloadTest() {
    File file = new File("/Users/milko.mitropolitsky/Desktop/testCsv2.csv");
    return Response
      .ok(file)
      .header("Content-Disposition", "attachment; filename=" + file.getName())
      .build();
  }

  /**
   * Save endpoint
   *
   * @param test - JSON object with id and name - the fields of Test
   * @return the saved object
   */
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Test saveTest(Test test) {
    return testService.saveTest(test);
  }

  /**
   * Updates (in this case replaces) Test object with the given ID with the one in the parameters list.
   *
   * @param id
   * @param test
   * @return
   */
  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Test updateTest(@PathParam("id") Integer id, Test test) {
    return testService.updateTest(id, test);
  }

  /**
   * Deletes Test with the given ID
   *
   * @param id
   */
  @DELETE
  @Path("/{id}")
  public void deleteTest(@PathParam("id") Integer id) {
    testService.deleteTest(id);
  }

}
