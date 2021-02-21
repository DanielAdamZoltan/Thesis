package jarmukezeloSzoftver;

public interface Adatbazis {

    String IP_LOCAL = "localhost", DATABASE_NAME = "jarmuvek";
    String DRIVER = "com.mysql.jdbc.Driver",
            URL = "jdbc:mysql://" + IP_LOCAL + ":3306/" + DATABASE_NAME,
            USER = "root",
            PASSWORD = "";

    String SQL_FELHASZNALO_ADATOK = "SELECT * FROM felhasznalo";
    String SQL_SZERVIZ_MARKA = "SELECT mkod, marka FROM marka ORDER BY marka";
    String SQL_UGYFEL = "SELECT szemelyazon_okmany_szam,"
            + " szemelyazon_okmany_tipus, nev, telszam, lakcim FROM ugyfel ORDER BY nev";
    //String SQL_KEDVEZMENY = "SELECT kedvezmeny_azonosito, osszeg FROM kedvezmenysav";
    String SQL_RENDSZAM = "SELECT rendszam FROM auto ORDER BY rendszam";
    String SQL_MUNKALAP_SZAM = "SELECT munkalap_szam "
            + "FROM munkalap ORDER BY munkalap_szam";
    String SQL_DOLGOZO = "SELECT dkod, nev FROM dolgozo ORDER BY nev";
    String SQL_FELADAT = "SELECT feladat, elnevezes FROM feladat ORDER BY feladat";
    String SQL_BESZERZES_MUNKALAP_SZAM = "SELECT munkalap_szam "
            + "FROM szereles ORDER BY munkalap_szam";
    String SQL_ALKATRESZ = "SELECT megnevezes FROM alkatresz ORDER BY megnevezes";

    String SQL_AUTO_ADATOK_MODOSIT_TABLAZAT = "SELECT * FROM kereskedes_auto";
    String SQL_AUTO_MARKA = "SELECT DISTINCT marka FROM kereskedes_auto ORDER BY marka";
    String SQL_AUTO_UZEMANYAG = "SELECT DISTINCT uzemanyag"
            + " FROM kereskedes_auto ORDER BY uzemanyag";
    String SQL_AUTO_KIVITEL = "SELECT DISTINCT kivitel FROM kereskedes_auto ORDER BY kivitel";
    String SQL_AUTO_ALLAPOT = "SELECT DISTINCT allapot FROM kereskedes_auto ORDER BY allapot";

    String SQL_MOTOR_ADATOK_MODOSIT_TABLAZAT = "SELECT * FROM kereskedes_motor";
    String SQL_MOTOR_MARKA = "SELECT DISTINCT marka FROM kereskedes_motor ORDER BY marka";
    String SQL_MOTOR_UZEMANYAG = "SELECT DISTINCT uzemanyag"
            + " FROM kereskedes_motor ORDER BY uzemanyag";
    String SQL_MOTOR_KIVITEL = "SELECT DISTINCT kivitel FROM kereskedes_motor ORDER BY kivitel";
    String SQL_MOTOR_ALLAPOT = "SELECT DISTINCT allapot FROM kereskedes_motor ORDER BY allapot";

    String SQL_KAMION_ADATOK_MODOSIT_TABLAZAT = "SELECT * FROM kereskedes_kamion";
    String SQL_KAMION_MARKA = "SELECT DISTINCT marka FROM kereskedes_kamion ORDER BY marka";
    String SQL_KAMION_UZEMANYAG = "SELECT DISTINCT uzemanyag "
            + "FROM kereskedes_kamion ORDER BY uzemanyag";
    String SQL_KAMION_KIVITEL = "SELECT DISTINCT kivitel FROM kereskedes_kamion ORDER BY kivitel";
    String SQL_KAMION_ALLAPOT = "SELECT DISTINCT allapot FROM kereskedes_kamion ORDER BY allapot";

    String SQL_INSERT_FELHASZNALO = "INSERT INTO felhasznalo("
            + "vezetek_nev, kereszt_nev, felhasznalo_nev, jelszo) VALUES (?,?,?,?)";
    String SQL_INSERT_UGYFEL = "INSERT INTO ugyfel(szemelyazon_okmany_szam,"
            + " szemelyazon_okmany_tipus, nev, telszam, lakcim) VALUES (?,?,?,?,?)";
    String SQL_INSERT_MARKA = "INSERT INTO marka(mkod, marka) VALUES(?,?)";
    String SQL_INSERT_AUTO = "INSERT INTO auto(rendszam, ugyfel_azon, marka, "
            + "evjarat, tipus, szin, motorszam, alvazszam) VALUES(?,?,?,?,?,?,?,?)";
    String SQL_INSERT_MUNKALAP = "INSERT INTO munkalap(munkalap_szam, "
            + "rendszam, hibajegyzek, munkadij, anyagdij, vallalasi_hatarido) VALUES (?,?,?,?,?,?)";
    String SQL_INSERT_FELADAT = "INSERT INTO feladat(feladat, munkadij,"
            + " elnevezes, tipus) VALUES(?,?,?,?)";
    String SQL_INSERT_SZERELES = "INSERT INTO szereles(munkalap_szam, "
            + "szerelo, feladat, kezdes, befejezes, munkaora) VALUES(?,?,?,?,?,?)";
    String SQL_INSERT_ALKATRESZ = "INSERT INTO alkatresz(kod, megnevezes)"
            + " VALUES(?, ?)";
    String SQL_INSERT_BESZERZES = "INSERT INTO beszerzes(besz_kod,"
            + " munklap_szam, alkatresz, aron, megrendelve) VALUES(?,?,?,?,?)";

    String SQL_INSERT_KERESKEDES_AUTO = "INSERT INTO kereskedes_auto("
            + "rendszam, marka, modell, tipus, evjarat, honap, kivitel, allapot, uzemanyag,"
            + " hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto,"
            + " jarmu_leirasa, jarmu_hibai, telephely, vetelar) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String SQL_INSERT_KERESKEDES_MOTOR = "INSERT INTO kereskedes_motor("
            + "rendszam, marka, modell, tipus, evjarat, honap, kivitel, allapot, uzemanyag,"
            + " hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto,"
            + " jarmu_leirasa, jarmu_hibai, telephely, vetelar) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String SQL_INSERT_KERESKEDES_KAMION = "INSERT INTO kereskedes_kamion("
            + "rendszam, marka, modell, tipus, evjarat, honap, kivitel, allapot, uzemanyag,"
            + " hengerur_tart, kilometerora_all, sajat_tomeg, teljesitmeny, szin, seb_valto,"
            + " jarmu_leirasa, jarmu_hibai, telephely, vetelar) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String SQL_UPDATE_KERESKEDES_AUTO = "UPDATE kereskedes_auto SET"
            + "marka=?, modell=?, tipus=?, evjarat=?, honap=?, "
            + "kivitel=?, allapot=?, uzemanyag=?, hengerur_tart=?, "
            + "kilometerora_all=?, sajat_tomeg=?, teljesitmeny=?, "
            + "szin=?, seb_valto=?, jarmu_leirasa=?, jarmu_hibai=?, "
            + "telephely=?, vetelar=? WHERE rendszam=?";

    String SQL_UPDATE_KERESKEDES_MOTOR = "UPDATE kereskedes_motor SET"
            + "marka=?, modell=?, tipus=?, evjarat=?, honap=?, "
            + "kivitel=?, allapot=?, uzemanyag=?, hengerur_tart=?, "
            + "kilometerora_all=?, sajat_tomeg=?, teljesitmeny=?, "
            + "szin=?, seb_valto=?, jarmu_leirasa=?, jarmu_hibai=?, "
            + "telephely=?, vetelar=? WHERE rendszam=?";

    String SQL_UPDATE_KERESKEDES_KAMION = "UPDATE kereskedes_kamion SET"
            + "marka=?, modell=?, tipus=?, evjarat=?, honap=?, "
            + "kivitel=?, allapot=?, uzemanyag=?, hengerur_tart=?, "
            + "kilometerora_all=?, sajat_tomeg=?, teljesitmeny=?, "
            + "szin=?, seb_valto=?, jarmu_leirasa=?, jarmu_hibai=?, "
            + "telephely=?, vetelar=? WHERE rendszam=?";

    String SQL_DELETE_KERESKEDES_AUTO = "DELETE FROM kereskedes_auto WHERE rendszam=?";

    String SQL_DELETE_KERESKEDES_MOTOR = "DELETE FROM kereskedes_motor WHERE rendszam=?";

    String SQL_DELETE_KERESKEDES_KAMION = "DELETE FROM kereskedes_kamion WHERE rendszam=?";

    String SQL_UPDATE_UGYFEL_ADATOK
            = "UPDATE `ugyfel` SET `szemelyazon_okmany_tipus`=?,`nev`=?,`telszam`=?,`lakcim`=?"
            + " WHERE `szemelyazon_okmany_szam`=?";

    String SQL_BESZERZES_ADATOK_TABLAZAT
            = "SELECT besz_kod , munklap_szam, alkatresz, aron, megrendelve FROM beszerzes ORDER BY besz_kod";

    String SQL_UPDATE_BESZERZES_ADATOK
            = "UPDATE `beszerzes` SET `munklap_szam`=?,"
            + "`alkatresz`=?,`aron`=?,`megrendelve`=? WHERE `besz_kod`=?";

}
