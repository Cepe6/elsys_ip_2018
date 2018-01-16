package org.elsys.ip.rest.resource;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import org.elsys.ip.rest.model.Country;
import org.elsys.ip.rest.service.CountryService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("/country")
public class CountryResource {
    private final CountryService CountryService = new CountryService();

    /**
     * Returns all the available objects.
     *
     * @return List<Country>
     */
    @GET
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public List<Country> getCountryList(@Context UriInfo uriInfo) {
        List<Country> countryList;
        if(uriInfo.getQueryParameters().size() <= 0) {
            countryList = CountryService.getCountryList();
        } else {
            countryList = CountryService.searchCountries(uriInfo.getQueryParameters());
        }
        return countryList;
    }

    @GET
    @Path("/filter")
    @Produces("text/html")
    public Response filterCountries() {
        return Response.ok(new Viewable("/WEB-INF/filtered.jsp", CountryService.getCountryList())).build();
    }

    /**
     * Returns an object with the given name.
     *
     * @param id unique identifier of an object
     * @return Country
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Country getCountry(@PathParam("id") Integer id) {
        return CountryService.getCountryById(id);
    }

    /**
     * Endpoint, which when accessed by a browser enables file download.
     *
     * @return Response
     */
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadCountry() {
        File file = new File("/Users/Cepe6/Desktop/CountryCsv2.csv");
        try {
            FileWriter fw = new FileWriter(file);
            for (Country country : CountryService.getCountryList()) {
                try {
                    fw.write(country.CSVFormat() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response
                .ok(file)
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .build();
    }

    /**
     * Save endpoint
     *
     * @param country - JSON object with id and the fields of Country
     * @return the saved object
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Country saveCountry(Country country) {
        return CountryService.saveCountry(country);
    }

    /**
     * Updates (in this case replaces) Country object with the given ID with the one in the parameters list.
     *
     * @param id unique identifier of an object
     * @param country the country object used for updating
     * @return Country
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Country updateCountry(@PathParam("id") Integer id, Country country) {
        return CountryService.updateCountry(id, country);
    }

    /**
     * Deletes Country with the given ID
     *
     * @param id unique identifier of an object
     */
    @DELETE
    @Path("/{id}")
    public void deleteCountry(@PathParam("id") Integer id) {
        CountryService.deleteCountry(id);
    }

    @GET
    @Path("/upload")
    @Produces("text/html")
    public Viewable renderUpload() {
        return new Viewable("/WEB-INF/upload.jsp", null);
    }

    @POST
    @Path("/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Map<String, List<Country>> uploadCSVFile(@FormDataParam("file") InputStream fileInputStream,
                              @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        LinkedHashMap<String, List<Country>> resultMap = new LinkedHashMap<>();
        resultMap.put("Added", new ArrayList<> ());

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        try {
            while((line = fileReader.readLine()) != null) {
                String[] attributes = line.split(", ");
                if(CountryService.getCountryByName(attributes[0]) == null) {
                    Country countryToSave = new Country(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], attributes[7], attributes[8]);

                    CountryService.saveCountry(countryToSave);
                    resultMap.get("Added").add(countryToSave);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @POST
    @Path("/multiple")
    @Consumes("application/json")
    @Produces("application/json")
    @JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
    public Map<String, List<Country>> saveMultiple(ArrayList<Country> countries) {
        LinkedHashMap<String, List<Country>> resultMap = new LinkedHashMap<>();
        resultMap.put("Added", new ArrayList<> ());

        for (Country country : countries) {
            System.out.println(country.getName());
            if(CountryService.getCountryByName(country.getName()) == null) {
                Country countryToSave = new Country(country);
                CountryService.saveCountry(countryToSave);
                resultMap.get("Added").add(countryToSave);
            }
        }

        return resultMap;
    }
}
