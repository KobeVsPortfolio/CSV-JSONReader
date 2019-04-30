create table wasdapp_entry (
    id int8 auto_increment primary key not null,
    name varchar(255),
    locatie varchar(64),
    straat varchar(96),
    nummer varchar(10),
    post_Code varchar(8),
    gemeente varchar(64),
    land varchar(64),
    omschrijving varchar(2048),
    wiki_Link varchar(64),
    website varchar(64),
    telefoon_Nummer varchar(64),
    email varchar(64),
    prijs double(64),
    persoon varchar(64),
    aanmaak_Datum timestamp,
    wijzig_Datum timestamp,
    lat double precision,
    lon double precision
);

insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Kevin De Clerck','Waarschijnlijk op zijn stoel.','testlaan','69A','9000','Gent','België','Dit is een omschrijving over Kevin waar niet veel over te zeggen valt. Sorry Kevin.','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Larry ','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Joren','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Jasper','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Kobe','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Shmerik','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);
insert into wasdapp_entry(name,locatie,straat,nummer,post_Code,gemeente,land,omschrijving,wiki_Link,website,telefoon_Nummer,email,prijs,persoon,aanmaak_Datum,wijzig_Datum,lat,lon) values ('Shelter','Gent','testlaan','69A','9000','Gent','België','nlablabla','wasdapp.wiki','wasdapp.be','0485967415','wasdapp@gmail.com',10.25,'Kevin','2010-01-01 11:00:00','2010-01-01 13:00:00',10.10,10.05);

create table gebruiker (
                      user_id int8 auto_increment primary key not null,
                      email varchar(64) not null,
                      password varchar(64) not null,
                      name varchar(64),
                      achternaam varchar(64),
                      role varchar(20)
);

insert into gebruiker(email, password, name, achternaam, role) values ('admin@gmail.com', 'password', 'larry', 'jeremiah', 'admin');
insert into gebruiker(email, password, name, achternaam, role) values ('tetie787@gmail.com', 'neintje', 'kevin', 'de clerk', 'admin');
insert into gebruiker(email, password, name, achternaam, role) values ('test@gmail.com', 'password', 'jasper', 'marcel', 'user');
