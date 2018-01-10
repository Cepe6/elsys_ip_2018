package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Country;

import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CountryRepository {
  private static List<Country> countryList = new ArrayList<>(
    Arrays.asList(
        new Country("Bulgaria", "Sofia","Sofia","Europe", "Bulgarian", "Bulgarian", "Unitary parliamentary constitutional republic", "President Rumen Radev", "Christianity"),
        new Country("Turkey", "Ankara", "Istanbul", "Europe", "Turkish", "Turks", "Unitary parliamentary constitutional republic", "President Recep Tayyip Erdoğan", "Muslim"),
        new Country("USA", "Washington D.C.", "New York City",  "North America", "English", "Ethnically diverse", "Federal parliamentary constitutional republic", "President Donald Trump", "Christianity"),
        new Country("Japan", "Tokyo", "Tokyo",  "Asia", "Japanese", "Japanese", "Unitary parliamentary constitutional republic", "Emperor Akihito", "Shinto/Buddhism"),
        new Country("United Kingdom", "London", "London",  "Europe", "English", "British", "Unitary parliament constitutional monarchy", "Monarch Queen Elizabeth II", "Christianity")
    ));

    public static List<Country> getCountryList() {
    return countryList;
  }

    public Optional<Country> getCountryById(Integer id) {
        return countryList.stream().filter(country -> country.getId().equals(id)).findFirst();
    }

    public Optional<Country> getCountryByName(String name) {
        return countryList.stream().filter(country -> country.getName().equals(name)).findFirst();
    }

    public Country saveCountry(Country country) {
        countryList.add(country);
        return country;
     }

    public Country updateCountry(Integer id, Country country) {
        int realId = id - 1;
        countryList.set(realId, country);
        return country;
    }

    public void deleteCountry(Integer id) {
        countryList.removeIf(country -> country.getId().equals(id));
    }

    public List<Country> searchCountries(MultivaluedMap searchParameters) {
        //Get the class fields which will later be used for autonomous country filter-by-field
        Field[] countryClassFields = Country.class.getDeclaredFields();

        //Initialize an array of the fields that we will be filtering by
        List<Field> searchedFields = new ArrayList<>();
        for (Object key : searchParameters.keySet()) {
            for (Field field : countryClassFields) {
                if(field.getName().equals(key.toString())) {
                    searchedFields.add(field);
                }
            }
        }

        //Declare the result array from the search
        List<Country> result = new ArrayList<> ();

        //Initialize the result array
        outer:
        for (Country country : countryList) {
            for (Field searchedField : searchedFields) {
                //For every searched field we make an array of the values in the query parameters which key is equal to the field name (For example if we have ?name=Bulgaria&name=Turkey
                //this will return array - [Bulgaria, Turkey] which values will be used for filtering
                String[] values = searchParameters.get(searchedField.getName()).toString().split(", ");

                //Some string formatting due to difference in parameter-to-string formats (For example if the array is larger than 1 element, the first element will start with "[..."
                //and the last element will end with "...]", which means that there will be difference in the values and the field values of the class instance
                if(values.length > 1) {
                    values[0] = values[0].substring(1);
                    values[values.length - 1] = values[values.length - 1].substring(0, values[values.length - 1].length() - 1);
                } else {
                    values[0] = values[0].substring(1, values[0].length() - 1);
                }

                try {
                    //We set the searchedField to accessible so we can see its value and use it in the filtering process
                    searchedField.setAccessible(true);
                    boolean match = false;
                    for (String value : values) {
                        //For every value in the search attribute we check the type of the corresponding class field and then cast it to this type so there will be no comparison between different types
                        if ((searchedField.getType() == Integer.class && Integer.parseInt(searchedField.get(country).toString()) == Integer.parseInt(value))
                                || (searchedField.getType() == String.class && searchedField.get(country).toString().equals(value))) {
                            //If even one of the values for that field match then we break and add it to the result (For example if the field is for the name and its value is either Bulgaria or Turkey
                            //we say its a match and break the loop and proceed to checking the next field for filtering and so on
                            match = true;
                            break;
                        }
                    }

                    //If however there is no match in values of the field and the searched values, we continue the outer loop so we skip adding that country to the result list and proceed to the next country
                    if (!match)
                        continue outer;
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } finally {
                    //Set the search field inaccessible again for security reasons
                    searchedField.setAccessible(false);
                }
            }
            result.add(country);
        }
        return result;
    }
}
