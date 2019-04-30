package com.realdolmen.hbo5.wasdapp.wasdappcore.dto;

import java.sql.Timestamp;

public class WasdappEntryResponse {

    private Long id;
    private String name;
    private String locatie;
    private String straat;
    private String nummer;
    private String postCode;
    private String gemeente;
    private String land;
    private String omschrijving;
    private String wikiLink;
    private String website;
    private String telefoonNummer;
    private String email;
    private Double prijs;
    private String persoon;
    private Timestamp aanmaakDatum;
    private Timestamp wijzigDatum;
    private Double lat;
    private Double lon;

    public static WasdappEntryResponseBuilder builder() {
        return new WasdappEntryResponseBuilder();
    }

    public static final class WasdappEntryResponseBuilder {

        private Long id;
        private String name;
        private String locatie;
        private String straat;
        private String nummer;
        private String postCode;
        private String gemeente;
        private String land;
        private String omschrijving;
        private String wikiLink;
        private String website;
        private String telefoonNummer;
        private String email;
        private Double prijs;
        private String persoon;
        private Timestamp aanmaakDatum;
        private Timestamp wijzigDatum;
        private Double lat;
        private Double lon;

        private WasdappEntryResponseBuilder() {
        }

        public WasdappEntryResponseBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WasdappEntryResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WasdappEntryResponseBuilder withOmschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
            return this;
        }

        public WasdappEntryResponseBuilder withLocatie(String locatie) {
            this.locatie = locatie;
            return this;
        }

        public WasdappEntryResponseBuilder withStraat(String straat) {
            this.straat = straat;
            return this;
        }
        
        public WasdappEntryResponseBuilder withNummer(String nummer) {
            this.nummer = nummer;
            return this;
        }
        
        public WasdappEntryResponseBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }
        
        public WasdappEntryResponseBuilder withGemeente(String gemeente) {
            this.gemeente = gemeente;
            return this;
        }

        public WasdappEntryResponseBuilder withLand(String land) {
            this.land = land;
            return this;
        }

        public WasdappEntryResponseBuilder withWikiLink(String wikiLink) {
            this.wikiLink = wikiLink;
            return this;
        }

        public WasdappEntryResponseBuilder withWebsite(String website) {
            this.website = website;
            return this;
        }

        public WasdappEntryResponseBuilder withTelefoonNummer(String telefoonNummer) {
            this.telefoonNummer = telefoonNummer;
            return this;
        }

        public WasdappEntryResponseBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WasdappEntryResponseBuilder withPrijs(Double prijs) {
            this.prijs = prijs;
            return this;
        }

        public WasdappEntryResponseBuilder withPersoon(String persoon) {
            this.persoon = persoon;
            return this;
        }

        public WasdappEntryResponseBuilder withAanmaakDatum(Timestamp aanmaakDatum) {
            this.aanmaakDatum = aanmaakDatum;
            return this;
        }

        public WasdappEntryResponseBuilder withWijzigDatum(Timestamp wijzigDatum) {
            this.wijzigDatum = wijzigDatum;
            return this;
        }

        public WasdappEntryResponseBuilder withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public WasdappEntryResponseBuilder withLon(Double lon) {
            this.lon = lon;
            return this;
        }

        public WasdappEntryResponse build() {
            WasdappEntryResponse wasdappEntryResponse = new WasdappEntryResponse();
            wasdappEntryResponse.omschrijving = this.omschrijving;
            wasdappEntryResponse.name = this.name;
            wasdappEntryResponse.id = this.id;
            wasdappEntryResponse.locatie = this.locatie;
            wasdappEntryResponse.straat = this.straat;
            wasdappEntryResponse.nummer = this.nummer;
            wasdappEntryResponse.postCode = this.postCode;
            wasdappEntryResponse.gemeente = this.gemeente;
            wasdappEntryResponse.land = this.land;
            wasdappEntryResponse.wikiLink = this.wikiLink;
            wasdappEntryResponse.website = this.website;
            wasdappEntryResponse.telefoonNummer = this.telefoonNummer;
            wasdappEntryResponse.email = this.email;
            wasdappEntryResponse.prijs = this.prijs;
            wasdappEntryResponse.persoon = this.persoon;
            wasdappEntryResponse.aanmaakDatum = this.aanmaakDatum;
            wasdappEntryResponse.wijzigDatum = this.wijzigDatum;
            wasdappEntryResponse.lat = this.lat;
            wasdappEntryResponse.lon = this.lon;
            return wasdappEntryResponse;
        }
    }

    public String getName() {
        return name;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Long getId() {
        return id;
    }

    public String getLocatie() {
        return locatie;
    }

    public String getStraat() {
        return straat;
    }

    public String getNummer() {
        return nummer;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getLand() {
        return land;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getWebsite() {
        return website;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public String getEmail() {
        return email;
    }

    public Double getPrijs() {
        return prijs;
    }

    public String getPersoon() {
        return persoon;
    }

    public Timestamp getAanmaakDatum() {
        return aanmaakDatum;
    }

    public Timestamp getWijzigDatum() {
        return wijzigDatum;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public void setPersoon(String persoon) {
        this.persoon = persoon;
    }

    public void setAanmaakDatum(Timestamp aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
    }

    public void setWijzigDatum(Timestamp wijzigDatum) {
        this.wijzigDatum = wijzigDatum;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
    
    
}
