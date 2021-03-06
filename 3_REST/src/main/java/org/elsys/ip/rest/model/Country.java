package org.elsys.ip.rest.model;

@javax.persistence.Entity(name = "Country")
public class Country {
    private static int latestId = 0;

    private Integer id;
    private String name;
    private String capital;
    private String biggestCity;
    private String continent;
    private String officialLanguage;
    private String ethnicGroup;
    private String government;
    private String ruler;
    private String religion;

    public static void setLatestId(int latestId) {
        Country.latestId = latestId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setBiggestCity(String biggestCity) {
        this.biggestCity = biggestCity;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setOfficialLanguage(String officialLanguage) {
        this.officialLanguage = officialLanguage;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public void setRuler(String ruler) {
        this.ruler = ruler;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Country(String name, String capital, String biggestCity, String continent, String officialLanguage, String ethnicGroup, String government, String ruler, String religion) {
        this.id = latestId;
        latestId++;
        this.name = name;
        this.capital = capital;

        this.biggestCity = biggestCity;
        this.continent = continent;
        this.officialLanguage = officialLanguage;
        this.ethnicGroup = ethnicGroup;
        this.government = government;
        this.ruler = ruler;
        this.religion = religion;
    }

    public Country(Country country) {
        this.id = latestId;
        latestId++;
        this.name = country.getName();
        this.capital = country.getCapital();
        this.biggestCity = country.getBiggestCity();
        this.continent = country.getContinent();
        this.officialLanguage = country.getOfficialLanguage();
        this.ethnicGroup = country.getEthnicGroup();
        this.government = country.getGovernment();
        this.ruler = country.getRuler();
        this.religion = country.getReligion();
    }

    public Country () {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getBiggestCity() {
        return biggestCity;
    }

    public String getContinent() {
        return continent;
    }

    public String getOfficialLanguage() {
        return officialLanguage;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public String getGovernment() {
        return government;
    }

    public String getRuler() {
        return ruler;
    }

    public String getReligion() {
        return religion;
    }

    public String CSVFormat() {
        return getId().toString() + ", "
                + getName() + ", "
                + getCapital() + ", "
                + getBiggestCity() + ", "
                + getContinent() + ", "
                + getOfficialLanguage() + ", "
                + getEthnicGroup() + ", "
                + getGovernment() + ", "
                + getRuler() + ", "
                + getReligion();
    }
}
