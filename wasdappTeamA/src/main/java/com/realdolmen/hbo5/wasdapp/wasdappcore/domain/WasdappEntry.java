package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import com.realdolmen.hbo5.wasdapp.wasdappcore.util.Logger;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "wasdapp_entry")
public class WasdappEntry implements Serializable {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String name;
    @Column(length = 64)
    private String locatie;
    @Column(length = 96)
    private String straat;
    @Column(length = 10)
    private String nummer;
    @Column(length = 8)
    private String postCode;
    @Column(length = 64)
    private String gemeente;
    @Column(length = 64)
    private String land;
    @Column(length = 2048)
    private String omschrijving;
    @Column(length = 64)
    private String wikiLink;
    @Column(length = 64)
    private String website;
    @Column(length = 64)
    private String telefoonNummer;
    @Column(length = 64)
    @Email
    private String email;
    @Column(length = 64)
    private Double prijs;
    @Column(length = 64)
    private String persoon;
    @Column(nullable = false)
    @NotNull
    private Timestamp aanmaakDatum;
    @NotNull
    @Column(nullable = false)
    private Timestamp wijzigDatum;
    @Column
    private Double lat;
    @Column
    private Double lon;

    public static WasdappEntryBuilder builder() {
        return new WasdappEntryBuilder();
    }

    public static final class WasdappEntryBuilder {

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

        private WasdappEntryBuilder() {
        }

        public WasdappEntryBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WasdappEntryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WasdappEntryBuilder withOmschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
            return this;
        }

        public WasdappEntryBuilder withLocatie(String locatie) {
            this.locatie = locatie;
            return this;
        }

        public WasdappEntryBuilder withStraat(String straat) {
            this.straat = straat;
            return this;
        }

        public WasdappEntryBuilder withNummer(String nummer) {
            this.nummer = nummer;
            return this;
        }

        public WasdappEntryBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public WasdappEntryBuilder withGemeente(String gemeente) {
            this.gemeente = gemeente;
            return this;
        }

        public WasdappEntryBuilder withLand(String land) {
            this.land = land;
            return this;
        }

        public WasdappEntryBuilder withWikiLink(String wikiLink) {
            this.wikiLink = wikiLink;
            return this;
        }

        public WasdappEntryBuilder withWebsite(String website) {
            this.website = website;
            return this;
        }

        public WasdappEntryBuilder withTelefoonNummer(String telefoonNummer) {
            this.telefoonNummer = telefoonNummer;
            return this;
        }

        public WasdappEntryBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WasdappEntryBuilder withPrijs(Double prijs) {
            this.prijs = prijs;
            return this;
        }

        public WasdappEntryBuilder withPersoon(String persoon) {
            this.persoon = persoon;
            return this;
        }

        public WasdappEntryBuilder withAanmaakDatum(Timestamp aanmaakDatum) {
            this.aanmaakDatum = aanmaakDatum;
            return this;
        }

        public WasdappEntryBuilder withWijzigDatum(Timestamp wijzigDatum) {
            this.wijzigDatum = wijzigDatum;
            return this;
        }

        public WasdappEntryBuilder withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public WasdappEntryBuilder withLon(Double lon) {
            this.lon = lon;
            return this;
        }

        public WasdappEntry build() {
            WasdappEntry wasdappEntry = new WasdappEntry();
            wasdappEntry.setOmschrijving(this.omschrijving);
            wasdappEntry.setName(this.name);
            wasdappEntry.setId(this.id);
            wasdappEntry.setLocatie(this.locatie);
            wasdappEntry.setStraat(this.straat);
            wasdappEntry.setNummer(this.nummer);
            wasdappEntry.setPostCode(this.postCode);
            wasdappEntry.setGemeente(this.gemeente);
            wasdappEntry.setLand(this.land);
            wasdappEntry.setWikiLink(this.wikiLink);
            wasdappEntry.setWebsite(this.website);
            wasdappEntry.setTelefoonNummer(this.telefoonNummer);
            wasdappEntry.setEmail(this.email);
            wasdappEntry.setPrijs(this.prijs);
            wasdappEntry.setPersoon(this.persoon);
            wasdappEntry.setAanmaakDatum(this.aanmaakDatum);
            wasdappEntry.setWijzigDatum(this.wijzigDatum);
            wasdappEntry.setLat(this.lat);
            wasdappEntry.setLon(this.lon);
            return wasdappEntry;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id < 99999999L && id > -99999999L) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() <= 64) {
            this.name = name;
        }
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        if (locatie != null && locatie.length() <= 64) {
            this.locatie = locatie;
        }
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        if (straat != null && straat.length() <= 64) {
            this.straat = straat;
        }
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        if (nummer != null && nummer.length() <= 64) {
            this.nummer = nummer;
        }
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        if (postCode != null && postCode.length() <= 64) {
            this.postCode = postCode;
        }
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        if (gemeente != null && gemeente.length() <= 64) {
            this.gemeente = gemeente;
        }
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        if (land != null && land.length() <= 64) {
            this.land = land;
        }
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        if (omschrijving != null && omschrijving.length() <= 1024) {
            this.omschrijving = omschrijving;
        }
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        if (wikiLink != null && wikiLink.length() <= 64) {
            this.wikiLink = wikiLink;
        }
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        if (website != null && website.length() <= 64) {
            this.website = website;
        }
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        if (telefoonNummer != null && telefoonNummer.length() <= 64) {
            this.telefoonNummer = telefoonNummer;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() <= 64) {
            this.email = email;
        }
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        if (prijs != null && prijs < 99999999D && prijs > -99999999D) {
            this.prijs = prijs;
        }
    }

    public String getPersoon() {
        return persoon;
    }

    public void setPersoon(String persoon) {
        if (persoon != null && persoon.length() <= 64) {
            this.persoon = persoon;
        }
    }

    public Timestamp getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setAanmaakDatum(Timestamp aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
    }

    public Timestamp getWijzigDatum() {
        return wijzigDatum;
    }

    public void setWijzigDatum(Timestamp wijzigDatum) {
        this.wijzigDatum = wijzigDatum;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        if (lat != null && lat < 9999999999999999D && lat > -9999999999999999D) {
            this.lat = lat;
        }
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        if (lon != null && lon < 9999999999999999D && lon > -9999999999999999D) {
            this.lon = lon;
        }
    }
}
