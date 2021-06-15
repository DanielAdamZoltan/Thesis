-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Már 29. 23:59
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `jarmuvek`
--
CREATE DATABASE IF NOT EXISTS `jarmuvek` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `jarmuvek`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `alkatresz`
--

DROP TABLE IF EXISTS `alkatresz`;
CREATE TABLE IF NOT EXISTS `alkatresz` (
  `kod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `megnevezes` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`kod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `alkatresz`:
--

--
-- A tábla adatainak kiíratása `alkatresz`
--

INSERT INTO `alkatresz` (`kod`, `megnevezes`) VALUES
('al/000', 'féktárcsa'),
('al/001', 'fékbetét'),
('al/002', 'olajszűrő'),
('al/003', 'légszűrő'),
('al/004', 'pollenszűrő'),
('al/005', 'komplett motorblokk'),
('al/006', 'turbó'),
('al/007', 'manuális ötfokozatú váltó');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `auto`
--

DROP TABLE IF EXISTS `auto`;
CREATE TABLE IF NOT EXISTS `auto` (
  `rendszam` char(7) COLLATE utf8_hungarian_ci NOT NULL,
  `ugyfel_azon` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `marka` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `evjarat` char(4) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `szin` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `motorszam` char(20) COLLATE utf8_hungarian_ci NOT NULL,
  `alvazszam` char(30) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`rendszam`),
  KEY `ugyfel_szam` (`ugyfel_azon`),
  KEY `marka` (`marka`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `auto`:
--   `ugyfel_azon`
--       `ugyfel` -> `szemelyazon_okmany_szam`
--   `marka`
--       `marka` -> `mkod`
--

--
-- A tábla adatainak kiíratása `auto`
--

INSERT INTO `auto` (`rendszam`, `ugyfel_azon`, `marka`, `evjarat`, `tipus`, `szin`, `motorszam`, `alvazszam`) VALUES
('FCW-998', '112511TR', 'ma/000', '2010', 'A3', '01', 'G65H789598', 'JFJIDF789F5687513'),
('JSH-723', '112511TR', 'ma/004', '2007', 'FIESTA 1.0 EcoBoost ', '05', 'D152FEE1546', 'KSDOGM467G7165794'),
('JVZ-260', '564678DA', 'ma/003', '2015', 'Octavia 1.8 Turbo El', '04', 'S545SSDS545', 'WHUSDS4545S4479431'),
('NRG-493', '564678DA', 'ma/002', '2006', 'Swift 1.3 GL', '03', 'JSHCKS54545', 'HCIWEJC454D4464648'),
('SFG-762', '459632PO', 'ma/001', '2003', '3 (E46)', '02', 'KSJ565SD', 'MCIUEI5956S565655');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `beszerzes`
--

DROP TABLE IF EXISTS `beszerzes`;
CREATE TABLE IF NOT EXISTS `beszerzes` (
  `besz_kod` char(12) COLLATE utf8_hungarian_ci NOT NULL,
  `munklap_szam` char(12) COLLATE utf8_hungarian_ci NOT NULL,
  `alkatresz` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `aron` mediumint(9) NOT NULL,
  `megrendelve` date NOT NULL,
  PRIMARY KEY (`besz_kod`),
  KEY `munklap_szam` (`munklap_szam`),
  KEY `alkatresz` (`alkatresz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `beszerzes`:
--   `munklap_szam`
--       `szereles` -> `munkalap_szam`
--   `alkatresz`
--       `alkatresz` -> `kod`
--

--
-- A tábla adatainak kiíratása `beszerzes`
--

INSERT INTO `beszerzes` (`besz_kod`, `munklap_szam`, `alkatresz`, `aron`, `megrendelve`) VALUES
('be/2020/0000', 'mu/2020/0000', 'al/005', 150000, '2020-03-14'),
('be/2020/0001', 'mu/2020/0001', 'al/007', 50000, '2020-03-14'),
('be/2020/0002', 'mu/2020/0003', 'al/006', 40000, '2020-03-14');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `dolgozo`
--

DROP TABLE IF EXISTS `dolgozo`;
CREATE TABLE IF NOT EXISTS `dolgozo` (
  `dkod` int(11) NOT NULL AUTO_INCREMENT,
  `nev` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `munkakor` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`dkod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `dolgozo`:
--

--
-- A tábla adatainak kiíratása `dolgozo`
--

INSERT INTO `dolgozo` (`dkod`, `nev`, `munkakor`) VALUES
(1, 'Dobos Géza', 'autószerelő De.'),
(2, 'Mikszáth Kálmán', 'autószerelő De.'),
(3, 'Ady Endre', 'autószerelő Du.');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `engedelyek`
--

DROP TABLE IF EXISTS `engedelyek`;
CREATE TABLE IF NOT EXISTS `engedelyek` (
  `engedely_kod` int(11) NOT NULL AUTO_INCREMENT,
  `elnevezes` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`engedely_kod`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `engedelyek`:
--

--
-- A tábla adatainak kiíratása `engedelyek`
--

INSERT INTO `engedelyek` (`engedely_kod`, `elnevezes`) VALUES
(1, 'Admin'),
(2, '\r\nMenedzser'),
(3, 'Szerkesztő'),
(4, '\r\nNéző');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `feladat`
--

DROP TABLE IF EXISTS `feladat`;
CREATE TABLE IF NOT EXISTS `feladat` (
  `feladat` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `munkadij` mediumint(9) NOT NULL,
  `elnevezes` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` char(1) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`feladat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `feladat`:
--

--
-- A tábla adatainak kiíratása `feladat`
--

INSERT INTO `feladat` (`feladat`, `munkadij`, `elnevezes`, `tipus`) VALUES
('fe/000', 40000, 'motor csere', 'c'),
('fe/001', 20000, 'oldalsó küszöb', 'j'),
('fe/002', 10000, 'felkészítés műszaki vizsgára', 'f'),
('fe/003', 25000, 'műszaki vizsgáztatás', 'v'),
('fe/004', 30000, 'váltó csere', 'c'),
('fe/005', 15000, 'fékcsere', 'c'),
('fe/006', 30000, 'turbó csere', 'c');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

DROP TABLE IF EXISTS `felhasznalo`;
CREATE TABLE IF NOT EXISTS `felhasznalo` (
  `felhasznalo_kod` int(11) NOT NULL AUTO_INCREMENT,
  `vezetek_nev` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `kereszt_nev` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `felhasznalo_nev` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `jelszo` varchar(128) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`felhasznalo_kod`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `felhasznalo`:
--

--
-- A tábla adatainak kiíratása `felhasznalo`
--

INSERT INTO `felhasznalo` (`felhasznalo_kod`, `vezetek_nev`, `kereszt_nev`, `felhasznalo_nev`, `jelszo`) VALUES
(16, 'A', 'A', 'Admin', '3ece34947b362b2a4dee099c9b5a77be85ed912f41e3448d1f40e38a9bbae51b85351e06b657f83f8515502ffbe4e0318ac2df2a4a61d2dec737588d780089dc');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felh_enged`
--

DROP TABLE IF EXISTS `felh_enged`;
CREATE TABLE IF NOT EXISTS `felh_enged` (
  `felhasznalo` int(11) NOT NULL,
  `engedely` int(11) NOT NULL,
  PRIMARY KEY (`felhasznalo`,`engedely`),
  KEY `engedelyek` (`engedely`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `felh_enged`:
--   `felhasznalo`
--       `felhasznalo` -> `felhasznalo_kod`
--   `engedely`
--       `engedelyek` -> `engedely_kod`
--

--
-- A tábla adatainak kiíratása `felh_enged`
--

INSERT INTO `felh_enged` (`felhasznalo`, `engedely`) VALUES
(16, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kedvezmenysav`
--

DROP TABLE IF EXISTS `kedvezmenysav`;
CREATE TABLE IF NOT EXISTS `kedvezmenysav` (
  `kedvezmeny_azonosito` int(3) NOT NULL AUTO_INCREMENT,
  `osszeg` mediumint(9) NOT NULL,
  PRIMARY KEY (`kedvezmeny_azonosito`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `kedvezmenysav`:
--

--
-- A tábla adatainak kiíratása `kedvezmenysav`
--

INSERT INTO `kedvezmenysav` (`kedvezmeny_azonosito`, `osszeg`) VALUES
(1, 10000),
(2, 20000),
(3, 30000),
(4, 40000),
(5, 50000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kereskedes_auto`
--

DROP TABLE IF EXISTS `kereskedes_auto`;
CREATE TABLE IF NOT EXISTS `kereskedes_auto` (
  `rendszam` char(7) COLLATE utf8_hungarian_ci NOT NULL,
  `marka` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `modell` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(10) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `evjarat` char(4) COLLATE utf8_hungarian_ci NOT NULL,
  `honap` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `kivitel` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `allapot` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `uzemanyag` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `hengerur_tart` varchar(5) COLLATE utf8_hungarian_ci NOT NULL,
  `kilometerora_all` varchar(7) COLLATE utf8_hungarian_ci NOT NULL,
  `sajat_tomeg` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `teljesitmeny` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `szin` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `seb_valto` char(1) COLLATE utf8_hungarian_ci NOT NULL,
  `jarmu_leirasa` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `jarmu_hibai` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `telephely` tinyint(1) NOT NULL,
  `vetelar` int(8) NOT NULL,
  PRIMARY KEY (`rendszam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `kereskedes_auto`:
--

--
-- A tábla adatainak kiíratása `kereskedes_auto`
--

INSERT INTO `kereskedes_auto` (`rendszam`, `marka`, `modell`, `tipus`, `evjarat`, `honap`, `kivitel`, `allapot`, `uzemanyag`, `hengerur_tart`, `kilometerora_all`, `sajat_tomeg`, `teljesitmeny`, `szin`, `seb_valto`, `jarmu_leirasa`, `jarmu_hibai`, `telephely`, `vetelar`) VALUES
('KDA-593', 'Audi', 'A3', 'Sportback ', '2006', '06', 'kombi', 'újszerű', 'benzin', '1968', '77711', '1320', '110', '08', '2', 'Ivinosc Kft-nél új gépjárműbe beszámított Audi A3 Sportback. Újonnan Magyarországon üzembe helyezett, végig márkaszervizben szervizelt, keveset futott gépkocsi. 1 év CarGarantie írásos garanciával.', 'Hátsólökhárító kopott, vezetőoldali ülés kivan szakadva, hátsó szélvédőmosó nem működik.', 0, 2000000),
('MTS-124', 'Alfa Romeo', '156', 'TS', '2008', '03', 'sedan', 'megkímélt', 'benzin', '1598', '200300', '1280', '88', '08', '0', 'Eladásra kínálom Alfa Romeo 156 1.6TS típusú autómat. Több, mint 10 évig szolgált hűségesen és megbízhatóan. Garantált 200300 km, a tavaly április műszaki vizsga óta összesen nem ment 5000 km-et. Folyamatosan karbantartott.', 'eszi az olajat, első lökhárító sérült a bal oldalon', 0, 1500000),
('NOW-867', 'Citroen', 'Berlingo', 'Family', '2007', '11', 'egyterű', 'normál', 'benzin', '1360', '310000', '1130', '55', '01', '0', 'Az új Citroën Berlingo határozott és merész karakterével, dinamikus külsejével, stílusos Airbump elemeivel és élénk színfoltjaival maga a megtestesült kompakt egyterű ikon. A kényelmes üléseknek és felfüggesztéseknek, valamint a kiváló hangszigetelésnek köszönhetően az utazás élménnyé válik.', 'hátsó féklámpa nem működik', 0, 690000),
('PCU-977', 'BMW', '330', 'D', '2010', '03', 'cabrio', 'sérülésmentes', 'dízel', '2993', '209000', '1495', '180', '10', '2', 'Eladó kihasználatlanság miatt BMW 330D típusú autóm valós leinformálható km-el! A kocsi teljesen költségmentes, megbízható.', 'Oldalsó küszöbnél a festék kopott.', 0, 2400000),
('PYL-909', 'Chrysler', 'Voyager', 'CRD LX', '2005', '12', 'egyterű', 'megkímélt', 'dízel', '2776', '194562', '1855', '110', '08', '2', 'lx! gyártás, modell legfelszereltebb változata kivételesen megkímélt sérülésmentes, korróziómentes újszerű állapotban! bőrbelső, motorosan nyíló-csukódó tolóajtók, gyári tolatóradar, stb! minden kifogástalanul működik!', '', 0, 1499999),
('UIG-469', 'Honda', 'CBF 600', 'SA', '2007', '03', 'Túra', 'kitűnő', 'benzin', '599', '43105', '368', '57', '09', '3', 'Megkímélt, rendszeresen szervizelt, hobbi célra használt CBF600 SA típus váltás miatt eladó. Extrák: 56 literes túradoboz háttámlával, Hurrican sport dob hangtompítóval (adom a gyárit is), MRA Vario túraplexi színre fújt Puig hátsó sárvédő, szivargyújtó aljzat, K&N sport légszűrő, 1, 5 cm-es kormány kiemelő, bukócső.', 'A motorkerékpár a fényezési hibákat leszámítva, kitűnő állapotban van!', 0, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kereskedes_kamion`
--

DROP TABLE IF EXISTS `kereskedes_kamion`;
CREATE TABLE IF NOT EXISTS `kereskedes_kamion` (
  `rendszam` char(7) COLLATE utf8_hungarian_ci NOT NULL,
  `marka` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `modell` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(10) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `evjarat` char(4) COLLATE utf8_hungarian_ci NOT NULL,
  `honap` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `kivitel` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `allapot` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `uzemanyag` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `hengerur_tart` varchar(5) COLLATE utf8_hungarian_ci NOT NULL,
  `kilometerora_all` varchar(7) COLLATE utf8_hungarian_ci NOT NULL,
  `sajat_tomeg` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `teljesitmeny` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `szin` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `seb_valto` char(1) COLLATE utf8_hungarian_ci NOT NULL,
  `jarmu_leirasa` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `jarmu_hibai` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `telephely` tinyint(1) NOT NULL,
  `vetelar` int(8) NOT NULL,
  PRIMARY KEY (`rendszam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `kereskedes_kamion`:
--

--
-- A tábla adatainak kiíratása `kereskedes_kamion`
--

INSERT INTO `kereskedes_kamion` (`rendszam`, `marka`, `modell`, `tipus`, `evjarat`, `honap`, `kivitel`, `allapot`, `uzemanyag`, `hengerur_tart`, `kilometerora_all`, `sajat_tomeg`, `teljesitmeny`, `szin`, `seb_valto`, `jarmu_leirasa`, `jarmu_hibai`, `telephely`, `vetelar`) VALUES
('MTT-344', 'DAF', 'XF', '105', '2010', '04', 'nyerges', 'megkímélt', 'benzin', '12902', '920000', '7385', '300', '02', '2', 'Gumik 4-5 mm, 5db hidraulika rendszer eladó állapottól és típustól függően 200000-250000 Ft/db, bizományos értékesítésre járműveket átveszek (jármű átvételekor a vételár 80-90 %-át azonnal kifizetem), a járműveket csak előre egyeztetett időpontban lehet megnézni!', 'Rossz felfüggesztés, a jobb oldali ajtónál kopott a fényezést, mindegyik fékben 25000 kilométer van, cserés.', 1, 3000000),
('NDU-405', 'DAF', '95 XF', '430', '2014', '08', 'konténerszállító', 'sérülésmentes', 'dízel', '12580', '1000000', '7000', '316', '05', '2', 'Euro-3, klíma, állófűtés, automata váltó, 2 tank, jó gumik, 315/70 R22, 5 gumiméret, jó állapotban tulajdonostól eladó!', 'Mindkét hátsó féklámpa nem működik, az első felfüggesztések rugói nem működnek megfelelően!', 1, 6000000),
('NNY-305', 'DAF', 'CF', '85.410', '2017', '07', 'duplakabinos', 'megkímélt', 'benzin/gáz', '12902', '866700', '8400', '300', '01', '2', 'Friss műszakival, új felépítménnyel eladó a képeken látható DAF billencs teherautó. Motor, váltó, futómű jó állapotban. Első gumik kb. 70%, hátsók 50%. Klíma és állófűtés. A hirdetés szövegében esetlegesen felmerülő hibákért felelősséget nem vállalunk. Köszönjük, hogy megtekintette hirdetésünket!', 'A vezetőfülkében lévő vészjelző és indexkar nem működik.', 1, 9000000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kereskedes_motor`
--

DROP TABLE IF EXISTS `kereskedes_motor`;
CREATE TABLE IF NOT EXISTS `kereskedes_motor` (
  `rendszam` varchar(7) COLLATE utf8_hungarian_ci NOT NULL,
  `marka` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `modell` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(10) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `evjarat` char(4) COLLATE utf8_hungarian_ci NOT NULL,
  `honap` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `kivitel` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `allapot` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `uzemanyag` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `hengerur_tart` varchar(5) COLLATE utf8_hungarian_ci NOT NULL,
  `kilometerora_all` varchar(7) COLLATE utf8_hungarian_ci NOT NULL,
  `sajat_tomeg` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `teljesitmeny` varchar(4) COLLATE utf8_hungarian_ci NOT NULL,
  `szin` char(2) COLLATE utf8_hungarian_ci NOT NULL,
  `seb_valto` char(1) COLLATE utf8_hungarian_ci NOT NULL,
  `jarmu_leirasa` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `jarmu_hibai` varchar(1000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `telephely` tinyint(1) NOT NULL,
  `vetelar` int(8) NOT NULL,
  PRIMARY KEY (`rendszam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `kereskedes_motor`:
--

--
-- A tábla adatainak kiíratása `kereskedes_motor`
--

INSERT INTO `kereskedes_motor` (`rendszam`, `marka`, `modell`, `tipus`, `evjarat`, `honap`, `kivitel`, `allapot`, `uzemanyag`, `hengerur_tart`, `kilometerora_all`, `sajat_tomeg`, `teljesitmeny`, `szin`, `seb_valto`, `jarmu_leirasa`, `jarmu_hibai`, `telephely`, `vetelar`) VALUES
('UWB-306', 'Suzuki', 'GSF 1200 S', 'Bandit', '2010', '04', 'túra', 'normál', 'benzin', '1156', '28071', '400', '71', '06', '3', 'Szelephézagok, karburátor beállítva. GSX-R hátsó rugóstag, túrakormány, egyedi ülés. Általános jó állapot.', 'hibátlan', 0, 1350000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `marka`
--

DROP TABLE IF EXISTS `marka`;
CREATE TABLE IF NOT EXISTS `marka` (
  `mkod` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `marka` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`mkod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `marka`:
--

--
-- A tábla adatainak kiíratása `marka`
--

INSERT INTO `marka` (`mkod`, `marka`) VALUES
('ma/000', 'Audi'),
('ma/001', 'BMW'),
('ma/002', 'Suzuki'),
('ma/003', 'Skoda'),
('ma/004', 'Ford'),
('ma/005', 'Alfa Romeo'),
('ma/006', 'Chrysler'),
('ma/007', 'Citroen'),
('ma/008', 'Fiat'),
('ma/009', 'Mercedes-Benz'),
('ma/010', 'Honda'),
('ma/011', 'Hyundai'),
('ma/012', 'Kia'),
('ma/013', 'Opel'),
('ma/014', 'Peugeot'),
('ma/015', 'Seat'),
('ma/016', 'Smart'),
('ma/017', 'Toyota'),
('ma/018', 'Renault'),
('ma/019', 'Volvo'),
('ma/020', 'Volkswagen');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `munkalap`
--

DROP TABLE IF EXISTS `munkalap`;
CREATE TABLE IF NOT EXISTS `munkalap` (
  `munkalap_szam` char(12) COLLATE utf8_hungarian_ci NOT NULL,
  `rendszam` char(7) COLLATE utf8_hungarian_ci NOT NULL,
  `hibajegyzek` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `munkadij` mediumint(9) NOT NULL DEFAULT 0,
  `anyagdij` mediumint(9) NOT NULL DEFAULT 0,
  `vallalasi_hatarido` datetime NOT NULL,
  PRIMARY KEY (`munkalap_szam`),
  KEY `rendszam` (`rendszam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `munkalap`:
--   `rendszam`
--       `auto` -> `rendszam`
--

--
-- A tábla adatainak kiíratása `munkalap`
--

INSERT INTO `munkalap` (`munkalap_szam`, `rendszam`, `hibajegyzek`, `munkadij`, `anyagdij`, `vallalasi_hatarido`) VALUES
('mu/2020/0000', 'SFG-762', 'Nem indul a motor többszöri indítás ellenére.', 0, 0, '2020-04-11 14:00:00'),
('mu/2020/0001', 'NRG-493', 'Motor beindul, de nem lehet sebességbe tenni az autót.', 0, 0, '2020-04-15 14:00:00'),
('mu/2020/0003', 'JVZ-260', 'Turbó nem kap elég nagy kompressziót.', 0, 0, '2020-04-13 12:00:00');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szereles`
--

DROP TABLE IF EXISTS `szereles`;
CREATE TABLE IF NOT EXISTS `szereles` (
  `munkalap_szam` char(12) COLLATE utf8_hungarian_ci NOT NULL,
  `szerelo` int(11) NOT NULL,
  `feladat` char(6) COLLATE utf8_hungarian_ci NOT NULL,
  `kezdes` date NOT NULL,
  `befejezes` date DEFAULT NULL,
  `munkaora` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`munkalap_szam`),
  KEY `szerelo` (`szerelo`),
  KEY `feladat` (`feladat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `szereles`:
--   `munkalap_szam`
--       `munkalap` -> `munkalap_szam`
--   `szerelo`
--       `dolgozo` -> `dkod`
--   `feladat`
--       `feladat` -> `feladat`
--

--
-- A tábla adatainak kiíratása `szereles`
--

INSERT INTO `szereles` (`munkalap_szam`, `szerelo`, `feladat`, `kezdes`, `befejezes`, `munkaora`) VALUES
('mu/2020/0000', 1, 'fe/000', '2020-03-19', '0000-00-00', 0),
('mu/2020/0001', 2, 'fe/004', '2020-03-20', '0000-00-00', 0),
('mu/2020/0003', 3, 'fe/006', '2020-03-21', '0000-00-00', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ugyfel`
--

DROP TABLE IF EXISTS `ugyfel`;
CREATE TABLE IF NOT EXISTS `ugyfel` (
  `szemelyazon_okmany_szam` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `szemelyazon_okmany_tipus` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `nev` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `telszam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `lakcim` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
  PRIMARY KEY (`szemelyazon_okmany_szam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- TÁBLA KAPCSOLATAI `ugyfel`:
--

--
-- A tábla adatainak kiíratása `ugyfel`
--

INSERT INTO `ugyfel` (`szemelyazon_okmany_szam`, `szemelyazon_okmany_tipus`, `nev`, `telszam`, `lakcim`) VALUES
('112511TR', 'Személy azonosító', 'Kiss Emánuel', '+36707531579', 'Bp'),
('459632PO', 'Személy azonosító', 'Nagy Norbert', '+36307893648', 'Budapest 1111 Virág utca 45.'),
('564678DA', 'Személy azonosító', 'Kovács József', '+36204589678', 'Budapest 1145 Nagy utca 32.');

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `auto`
--
ALTER TABLE `auto`
  ADD CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`ugyfel_azon`) REFERENCES `ugyfel` (`szemelyazon_okmany_szam`),
  ADD CONSTRAINT `auto_ibfk_2` FOREIGN KEY (`marka`) REFERENCES `marka` (`mkod`);

--
-- Megkötések a táblához `beszerzes`
--
ALTER TABLE `beszerzes`
  ADD CONSTRAINT `beszerzes_ibfk_3` FOREIGN KEY (`munklap_szam`) REFERENCES `szereles` (`munkalap_szam`),
  ADD CONSTRAINT `beszerzes_ibfk_4` FOREIGN KEY (`alkatresz`) REFERENCES `alkatresz` (`kod`);

--
-- Megkötések a táblához `felh_enged`
--
ALTER TABLE `felh_enged`
  ADD CONSTRAINT `felh_enged_ibfk_1` FOREIGN KEY (`felhasznalo`) REFERENCES `felhasznalo` (`felhasznalo_kod`),
  ADD CONSTRAINT `felh_enged_ibfk_2` FOREIGN KEY (`engedely`) REFERENCES `engedelyek` (`engedely_kod`);

--
-- Megkötések a táblához `munkalap`
--
ALTER TABLE `munkalap`
  ADD CONSTRAINT `munkalap_ibfk_1` FOREIGN KEY (`rendszam`) REFERENCES `auto` (`rendszam`);

--
-- Megkötések a táblához `szereles`
--
ALTER TABLE `szereles`
  ADD CONSTRAINT `szereles_ibfk_4` FOREIGN KEY (`munkalap_szam`) REFERENCES `munkalap` (`munkalap_szam`),
  ADD CONSTRAINT `szereles_ibfk_5` FOREIGN KEY (`szerelo`) REFERENCES `dolgozo` (`dkod`),
  ADD CONSTRAINT `szereles_ibfk_6` FOREIGN KEY (`feladat`) REFERENCES `feladat` (`feladat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
