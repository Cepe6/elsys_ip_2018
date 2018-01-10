package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Country;
import org.elsys.ip.rest.repository.CountryRepository;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class CountryService {

  private final CountryRepository countryRepository = new CountryRepository();

  public List<Country> getCountryList() {
    return countryRepository.getCountryList();
  }

  public Country getCountryById(Integer id) {
    return countryRepository.getCountryById(id).orElse(null);
  }

  public Country getCountryByName(String name) { return countryRepository.getCountryByName(name).orElse(null); }

  public Country saveCountry(Country country) {
    return countryRepository.saveCountry(country);
  }

  public Country updateCountry(Integer id, Country country) {
    return countryRepository.updateCountry(id, country);
  }

  public void deleteCountry(Integer id) {
    countryRepository.deleteCountry(id);
  }

  public List<Country> searchCountries(MultivaluedMap searchParameters) { return countryRepository.searchCountries(searchParameters); }
}
