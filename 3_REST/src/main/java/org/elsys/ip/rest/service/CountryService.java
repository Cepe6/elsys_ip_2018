package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Country;
import org.elsys.ip.rest.repository.CountryRepository;
import org.elsys.ip.rest.repository.CountryRepositoryJDBC;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class CountryService {

  private final CountryRepository countryRepository = new CountryRepository();
  private final CountryRepositoryJDBC countryRepositoryJDBC = new CountryRepositoryJDBC();

  public List<Country> getCountryList() {
    return CountryRepositoryJDBC.getCountryList();
  }

  public Country getCountryById(Integer id) {
    return countryRepository.getCountryById(id).orElse(null);
  }

  public Country getCountryByName(String name) { return countryRepository.getCountryByName(name).orElse(null); }

  public Country saveCountry(Country country) {
    return CountryRepositoryJDBC.saveCountry(country);
  }

  public Country updateCountry(Integer id, Country country) {
    return countryRepository.updateCountry(id, country);
  }

  public void deleteCountry(Integer id) {
    countryRepository.deleteCountry(id);
  }

  public List<Country> searchCountries(MultivaluedMap searchParameters) { return countryRepository.searchCountries(searchParameters); }
}
