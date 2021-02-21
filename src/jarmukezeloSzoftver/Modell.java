package jarmukezeloSzoftver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

class Felhasznalo { //POJO

    private int felhasznaloKod;
    private String vezetekNev;
    private String keresztNev;
    private String felhasznaloNev;
    private String jelszo;

    public Felhasznalo(int felhasznaloKod, String vezetekNev, String keresztNev, String felhasznaloNev, String jelszo) {
        this.felhasznaloKod = felhasznaloKod;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.felhasznaloNev = felhasznaloNev;
        this.jelszo = jelszo;
    }

    public int getFelhasznaloKod() {
        return felhasznaloKod;
    }

    public String getKeresztNev() {
        return keresztNev;
    }

    public String getVezetekNev() {
        return vezetekNev;
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }

    public String getJelszo() {
        return jelszo;
    }

    @Override
    public String toString() {
        return "";
    }
}

class SzervizMarka { //POJO

    private String markaKod;
    private String marka;

    public SzervizMarka(String markaKod, String marka) {
        this.markaKod = markaKod;
        this.marka = marka;
    }

    public String getMarkaKod() {
        return markaKod;
    }

    public String getMarka() {
        return marka;
    }

    @Override
    public String toString() {
        return marka;
    }
}

class Marka { //POJO

    String marka;

    public Marka(String marka) {
        this.marka = marka;
    }

    public String getMarka() {
        return marka;
    }

    @Override
    public String toString() {
        return marka;
    }
}

class Ugyfel { //POJO

    String szemelyazonOkmanySzam;
    String szemelyazonOkmanyTipus;
    String nev;
    String telefonszam;
    String lakcim;

    public Ugyfel(String szemelyazonOkmanySzam, String szemelyazonOkmanyTipus, String nev, String telefonszam, String lakcim) {
        this.szemelyazonOkmanySzam = szemelyazonOkmanySzam;
        this.szemelyazonOkmanyTipus = szemelyazonOkmanyTipus;
        this.nev = nev;
        this.telefonszam = telefonszam;
        this.lakcim = lakcim;
    }

    public String getSzemelyazonOkmanySzam() {
        return szemelyazonOkmanySzam;
    }

    public String getSzemelyazonOkmanyTipus() {
        return szemelyazonOkmanyTipus;
    }

    public String getNev() {
        return nev;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public String getLakcim() {
        return lakcim;
    }

    @Override
    public String toString() {
        return nev;
    }
}

class Kedvezmeny { //POJO

    int kedvezmenyAzon;
    int osszeg;

    public Kedvezmeny(int kedvezmenyAzon, int osszeg) {
        this.kedvezmenyAzon = kedvezmenyAzon;
        this.osszeg = osszeg;
    }

    public int getKedvezmenyAzon() {
        return kedvezmenyAzon;
    }

    public int getOsszeg() {
        return osszeg;
    }

    @Override
    public String toString() {
        return String.valueOf(osszeg);
    }
}

class Kivitel { //POJO

    String kivitel;

    public Kivitel(String kivitel) {
        this.kivitel = kivitel;
    }

    public String getKivitel() {
        return kivitel;
    }

    @Override
    public String toString() {
        return kivitel;
    }
}

class Uzemanyag { //POJO

    String uzemanyag;

    public Uzemanyag(String uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public String getUzemanyag() {
        return uzemanyag;
    }

    @Override
    public String toString() {
        return uzemanyag;
    }
}

class Allapot { //POJO

    String allapot;

    public Allapot(String allapot) {
        this.allapot = allapot;
    }

    public String getAllapot() {
        return allapot;
    }

    @Override
    public String toString() {
        return allapot;
    }
}

class Rendszam { //POJO

    private String rendszam;

    public Rendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    public String getRendszam() {
        return rendszam;
    }

    @Override
    public String toString() {
        return rendszam;
    }

}

class MunkalapSzam { //POJO

    private String munkalapSzam;

    public MunkalapSzam(String munkalapSzam) {
        this.munkalapSzam = munkalapSzam;
    }

    public String getMunkalapSzam() {
        return munkalapSzam;
    }

    @Override
    public String toString() {
        return munkalapSzam;
    }

}

class Dolgozo { //POJO

    int dkod;
    private String nev;

    public Dolgozo(int dkod, String nev) {
        this.dkod = dkod;
        this.nev = nev;
    }

    public int getDkod() {
        return dkod;
    }

    public String getNev() {
        return nev;
    }

    @Override
    public String toString() {
        return nev;
    }

}

class Feladat { //POJO

    private String kod;
    private String elnevezes;

    public Feladat(String kod, String elnevezes) {
        this.kod = kod;
        this.elnevezes = elnevezes;
    }

    public String getKod() {
        return kod;
    }

    public String getElnevezes() {
        return elnevezes;
    }

    @Override
    public String toString() {
        return elnevezes;
    }

}

class Alkatresz {

    private String alkatresz;

    public Alkatresz(String alkatresz) {
        this.alkatresz = alkatresz;
    }

    public String getAlkatresz() {
        return alkatresz;
    }

    @Override
    public String toString() {
        return alkatresz;
    }

}

public class Modell implements Adatbazis {

    private String jelszo;
    private String modellTitkositottJelszo;
    private boolean vanDriver = false;
    public File htmlFajl;
    private String html;

    //Konstruktor
    public Modell() {
        this.htmlFajl = new File("./files/index.html");
        this.html = "<html>\n"
                + "  <head>\n"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n"
                + "    <script type=\"text/javascript\">\n"
                + "      google.charts.load('current', {'packages':['table']});\n"
                + "      google.charts.setOnLoadCallback(drawTable);\n"
                + "\n"
                + "      function drawTable() {\n"
                + "        var data = new google.visualization.DataTable();\n"
                + "#TablazatFejlec#\n"
                + "        data.addRows([\n"
                + "#TablazatAdat#\n"
                + "        ]);\n"
                + "\n"
                + "        var table = new google.visualization.Table(document.getElementById('table_div'));\n"
                + "\n"
                + "        table.draw(data, {showRowNumber: true, width: '100%', height: '50%'});\n"
                + "      }\n"
                + "    </script>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <div id=\"table_div\"></div>\n"
                + "    <div id=\"images_div\"></div>\n"
                + "  </body>\n"
                + "</html>";
        vanDriver = driverBetolt();
    }

    //Driver betöltése
    private boolean driverBetolt() {
        boolean b = false;
        try {
            Class.forName(DRIVER);
            b = true;
        } catch (ClassNotFoundException e) {
            ;
        }
        return b;
    }

    //Html fájl generáló metódus
    public void HTMLFajltGeneral(String fejlec, String adat) {
        html = html.replace("#TablazatFejlec#", fejlec);
        html = html.replace("#TablazatAdat#", adat);
        try {
            Path path = Paths.get("./files/index.html");
            Files.write(path, html.getBytes());
            System.out.println("A html fájl létrejött.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Felhasználó regisztrálása az adatbázisba
    public ArrayList<Felhasznalo> belepesAdatokFeltolt() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_FELHASZNALO_ADATOK);
        ArrayList<Felhasznalo> lista = new ArrayList<>();
        while (rs.next()) {
            int fkod = rs.getInt(1);
            String vezNev = rs.getString(2);
            String kerNev = rs.getString(3);
            String fnev = rs.getString(4);
            String jelszo = rs.getString(5);
            //System.out.println("alap modell felhasználónév: " + fnev);
            //System.out.println("alap modell jelszó: " + jelszo);
            lista.add(new Felhasznalo(fkod, vezNev, kerNev, fnev, jelszo));
        }
        c.close();
        return lista;
    }

    //Szerviz márkák lekérdezése
    public ArrayList<SzervizMarka> getSzervizMarkaLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_SZERVIZ_MARKA);
        ArrayList<SzervizMarka> lista = new ArrayList<>();
        while (rs.next()) {
            String mkod = rs.getString("mkod");
            String marka = rs.getString("marka");
            lista.add(new SzervizMarka(mkod, marka));
        }
        c.close();
        return lista;
    }

    //Szerviz ügyfelek lekérdezése
    public ArrayList<Ugyfel> getUgyfelLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_UGYFEL);
        ArrayList<Ugyfel> lista = new ArrayList<>();
        while (rs.next()) {
            String azon = rs.getString("szemelyazon_okmany_szam");
            String tipus = rs.getString("szemelyazon_okmany_tipus");
            String nev = rs.getString("nev");
            String tel = rs.getString("telszam");
            String lakcim = rs.getString("lakcim");
            lista.add(new Ugyfel(azon, tipus, nev, tel, lakcim));
        }
        c.close();
        return lista;
    }

    //Rendszámok lekérdezése
    public ArrayList<Rendszam> getRendszamLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_RENDSZAM);
        ArrayList<Rendszam> lista = new ArrayList<>();
        while (rs.next()) {
            String rsz = rs.getString("rendszam");
            lista.add(new Rendszam(rsz));
        }
        c.close();
        return lista;
    }

    //Munkalapok lekérdezése
    public ArrayList<MunkalapSzam> getMunkalapSzamLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MUNKALAP_SZAM);
        ArrayList<MunkalapSzam> lista = new ArrayList<>();
        while (rs.next()) {
            String munkasz = rs.getString("munkalap_szam");
            lista.add(new MunkalapSzam(munkasz));
        }
        c.close();
        return lista;
    }

    //Dolgozók lekérdezése
    public ArrayList<Dolgozo> getDolgozoLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_DOLGOZO);
        ArrayList<Dolgozo> lista = new ArrayList<>();
        while (rs.next()) {
            int dkod = rs.getInt("dkod");
            String nev = rs.getString("nev");
            lista.add(new Dolgozo(dkod, nev));
        }
        c.close();
        return lista;
    }

    //Feladatok lekérdezése
    public ArrayList<Feladat> getFeladatLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_FELADAT);
        ArrayList<Feladat> lista = new ArrayList<>();
        while (rs.next()) {
            String kod = rs.getString("feladat");
            String elnevezes = rs.getString("elnevezes");
            lista.add(new Feladat(kod, elnevezes));
        }
        c.close();
        return lista;
    }

    //Beszerzes munkalapok lekérdezése
    public ArrayList<MunkalapSzam> getBeszerzesMunkalapSzamLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_BESZERZES_MUNKALAP_SZAM);
        ArrayList<MunkalapSzam> lista = new ArrayList<>();
        while (rs.next()) {
            String munkasz = rs.getString("munkalap_szam");
            lista.add(new MunkalapSzam(munkasz));
        }
        c.close();
        return lista;
    }

    //Alkatrészek lekérdezése
    public ArrayList<Alkatresz> getAlkatreszLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_ALKATRESZ);
        ArrayList<Alkatresz> lista = new ArrayList<>();
        while (rs.next()) {
            String alkatresz = rs.getString("megnevezes");
            lista.add(new Alkatresz(alkatresz));
        }
        c.close();
        return lista;
    }

    //Autó keresés márkák lekérdezés
    public ArrayList<Marka> getAutoMarkaLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_AUTO_MARKA);
        ArrayList<Marka> lista = new ArrayList<>();
        while (rs.next()) {
            String marka = rs.getString("marka");
            lista.add(new Marka(marka));
        }
        c.close();
        return lista;
    }

    //Autó keresés üzemanyagok lekérdezés
    public ArrayList<Uzemanyag> getAutoUzemanyagLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_AUTO_UZEMANYAG);
        ArrayList<Uzemanyag> lista = new ArrayList<>();
        while (rs.next()) {
            String uzemanyag = rs.getString("uzemanyag");
            lista.add(new Uzemanyag(uzemanyag));
        }
        c.close();
        return lista;
    }

    //Autó keresés kivitelek lekérdezés
    public ArrayList<Kivitel> getAutoKivitelLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_AUTO_KIVITEL);
        ArrayList<Kivitel> lista = new ArrayList<>();
        while (rs.next()) {
            String kivitel = rs.getString("kivitel");
            lista.add(new Kivitel(kivitel));
        }
        c.close();
        return lista;
    }

    //Autó keresés állapotok lekérdezés
    public ArrayList<Allapot> getAutoAllapotLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_AUTO_ALLAPOT);
        ArrayList<Allapot> lista = new ArrayList<>();
        while (rs.next()) {
            String allapot = rs.getString("allapot");
            lista.add(new Allapot(allapot));
        }
        c.close();
        return lista;
    }

    //Motor keresés márkák lekérdezés
    public ArrayList<Marka> getMotorMarkaLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MOTOR_MARKA);
        ArrayList<Marka> lista = new ArrayList<>();
        while (rs.next()) {
            String marka = rs.getString("marka");
            lista.add(new Marka(marka));
        }
        c.close();
        return lista;
    }

    //Motor keresés üzemanyagok lekérdezés
    public ArrayList<Uzemanyag> getMotorUzemanyagLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MOTOR_UZEMANYAG);
        ArrayList<Uzemanyag> lista = new ArrayList<>();
        while (rs.next()) {
            String uzemanyag = rs.getString("uzemanyag");
            lista.add(new Uzemanyag(uzemanyag));
        }
        c.close();
        return lista;
    }

    //Motor keresés kivitelek lekérdezés
    public ArrayList<Kivitel> getMotorKivitelLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MOTOR_KIVITEL);
        ArrayList<Kivitel> lista = new ArrayList<>();
        while (rs.next()) {
            String kivitel = rs.getString("kivitel");
            lista.add(new Kivitel(kivitel));
        }
        c.close();
        return lista;
    }

    //Motor keresés állapotok lekérdezés
    public ArrayList<Allapot> getMotorAllapotLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MOTOR_ALLAPOT);
        ArrayList<Allapot> lista = new ArrayList<>();
        while (rs.next()) {
            String allapot = rs.getString("allapot");
            lista.add(new Allapot(allapot));
        }
        c.close();
        return lista;
    }

    //Kamion keresés márkák lekérdezés
    public ArrayList<Marka> getKamionMarkaLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_KAMION_MARKA);
        ArrayList<Marka> lista = new ArrayList<>();
        while (rs.next()) {
            String marka = rs.getString("marka");
            lista.add(new Marka(marka));
        }
        c.close();
        return lista;
    }

    //Kamion keresés üzemanyagok lekérdezés
    public ArrayList<Uzemanyag> getKamionUzemanyagLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_KAMION_UZEMANYAG);
        ArrayList<Uzemanyag> lista = new ArrayList<>();
        while (rs.next()) {
            String uzemanyag = rs.getString("uzemanyag");
            lista.add(new Uzemanyag(uzemanyag));
        }
        c.close();
        return lista;
    }

    //Kamion keresés kivitelek lekérdezés
    public ArrayList<Kivitel> getKamionKivitelLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_KAMION_KIVITEL);
        ArrayList<Kivitel> lista = new ArrayList<>();
        while (rs.next()) {
            String kivitel = rs.getString("kivitel");
            lista.add(new Kivitel(kivitel));
        }
        c.close();
        return lista;
    }

    //Kamion keresés állapotok lekérdezés
    public ArrayList<Allapot> getKamionAllapotLista() throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_KAMION_ALLAPOT);
        ArrayList<Allapot> lista = new ArrayList<>();
        while (rs.next()) {
            String allapot = rs.getString("allapot");
            lista.add(new Allapot(allapot));
        }
        c.close();
        return lista;
    }

    //Felhasználó feltöltése az adatbázisba
    public void felhasznaloAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String veznev = lista.get(0);
        String kernev = lista.get(1);
        String fnev = lista.get(2);
        jelszo = lista.get(3);
        //System.out.println("Eredeti jelszó: " + jelszo);
        //jelszoTitkosit(jelszo);
        jelszoTitkositas(jelszo, "XFva~n0i%-ai9px3@#g#MYyp3K =HXFcjvg!w(hXA4UTP20e:[7_>dp.5SzpP#I&");
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_FELHASZNALO);
        ps.setString(1, veznev);
        ps.setString(2, kernev);
        ps.setString(3, fnev);
        ps.setString(4, modellTitkositottJelszo);
        ps.execute();
        //System.out.println("Titkos jelszó: " + modellTitkositottJelszo);

    }

    //Ügyfél feltöltése az adatbázisba
    public void ugyfelAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String okmanyAzon = lista.get(0);
        String okmanyTipus = lista.get(1);
        String nev = lista.get(2);
        String elerhetoseg = lista.get(3);
        String lakcim = lista.get(4);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_UGYFEL);
        ps.setString(1, okmanyAzon);
        ps.setString(2, okmanyTipus);
        ps.setString(3, nev);
        ps.setString(4, elerhetoseg);
        ps.setString(5, lakcim);
        ps.execute();
    }

    //Ügyfél módosítása az adatbázisba
    public void ugyfelAdatokModosit(ArrayList<String> lista) throws SQLException {
        String okmanyAzon = lista.get(0);
        String okmanyTipus = lista.get(1);
        String nev = lista.get(2);
        String elerhetoseg = lista.get(3);
        String lakcim = lista.get(4);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_UPDATE_UGYFEL_ADATOK);
        ps.setString(5, okmanyAzon);
        ps.setString(1, okmanyTipus);
        ps.setString(2, nev);
        ps.setString(3, elerhetoseg);
        ps.setString(4, lakcim);
        ps.executeUpdate();
    }

    //Márka feltöltése az adatbázisba
    public void markaAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String kod = lista.get(0);
        String elnevezes = lista.get(1);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_MARKA);
        ps.setString(1, kod);
        ps.setString(2, elnevezes);
        ps.execute();
    }

    //Autó feltöltése az adatbázisba
    public void autoAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String rsz = lista.get(0);
        String ugyfel = lista.get(1);
        String marka = lista.get(2);
        String evjarat = lista.get(3);
        String tipus = lista.get(4);
        String szin = lista.get(5);
        String mszam = lista.get(6);
        String aszam = lista.get(7);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_AUTO);
        ps.setString(1, rsz);
        ps.setString(2, ugyfel);
        ps.setString(3, marka);
        ps.setString(4, evjarat);
        ps.setString(5, tipus);
        ps.setString(6, szin);
        ps.setString(7, mszam);
        ps.setString(8, aszam);
        ps.execute();
    }

    //Munkalap feltöltése az adatbázisba
    public void munkalapAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String munkalapSzam = lista.get(0);
        String rsz = lista.get(1);
        String hiba = lista.get(2);
        int munkadij = Integer.parseInt(lista.get(3));
        int anyagdij = Integer.parseInt(lista.get(4));
        String vallHatarido = lista.get(5);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_MUNKALAP);
        ps.setString(1, munkalapSzam);
        ps.setString(2, rsz);
        ps.setString(3, hiba);
        ps.setInt(4, munkadij);
        ps.setInt(5, anyagdij);
        ps.setString(6, vallHatarido);
        ps.execute();
    }

    //Feladat feltöltése az adatbázisba
    public void feladatAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String feladat = lista.get(0);
        int munkadij = Integer.parseInt(lista.get(1));
        String elnevezes = lista.get(2);
        String tipus = lista.get(3);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_FELADAT);
        ps.setString(1, feladat);
        ps.setInt(2, munkadij);
        ps.setString(3, elnevezes);
        ps.setString(4, tipus);
        ps.execute();
    }

    //Szerelés feltöltése az adatbázisba
    public void szerelesAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String munkalap = lista.get(0);
        int szerelo = Integer.parseInt(lista.get(1));
        String feladat = lista.get(2);
        String kezdes = lista.get(3);
        String befejezes = lista.get(4);
        int munkaOra = Integer.parseInt(lista.get(5));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_SZERELES);
        ps.setString(1, munkalap);
        ps.setInt(2, szerelo);
        ps.setString(3, feladat);
        ps.setString(4, kezdes);
        ps.setString(5, befejezes);
        ps.setInt(6, munkaOra);
        ps.execute();
    }

    //Alkatresz feltöltése az adatbázisba
    public void alkatreszAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String kod = lista.get(0);
        String megnevezes = lista.get(1);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_ALKATRESZ);
        ps.setString(1, kod);
        ps.setString(2, megnevezes);
        ps.execute();
    }

    //Beszerzés feltöltése az adatbázisba
    public void beszerzesAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String kod = lista.get(0);
        String munkalap = lista.get(1);
        String alkatresz = lista.get(2);
        int ar = Integer.parseInt(lista.get(3));
        String megrendel = lista.get(4);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_BESZERZES);
        ps.setString(1, kod);
        ps.setString(2, munkalap);
        ps.setString(3, alkatresz);
        ps.setInt(4, ar);
        ps.setString(5, megrendel);
        ps.execute();
    }

    //Beszerzés módosítása az adatbázisban
    public void beszerzesAdatokModosit(ArrayList<String> lista) throws SQLException {
        String kod = lista.get(0);
        String munkalap = lista.get(1);
        String alkatresz = lista.get(2);
        int ar = Integer.parseInt(lista.get(3));
        String megrendel = lista.get(4);
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_UPDATE_BESZERZES_ADATOK);
        ps.setString(5, kod);
        ps.setString(1, munkalap);
        ps.setString(2, alkatresz);
        ps.setInt(3, ar);
        ps.setString(4, megrendel);
        ps.executeUpdate();
    }

    //Személyautó adatainak feltöltése az adatbázisba
    public void szemelyautoAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_KERESKEDES_AUTO);
        ps.setString(1, rendszam);
        ps.setString(2, marka);
        ps.setString(3, modell);
        ps.setString(4, tipus);
        ps.setString(5, evjarat);
        ps.setString(6, honap);
        ps.setString(7, kivitel);
        ps.setString(8, allapot);
        ps.setString(9, uzemanyag);
        ps.setString(10, henger);
        ps.setString(11, kilometer);
        ps.setString(12, sajat);
        ps.setString(13, teljesitmeny);
        ps.setString(14, szin);
        ps.setString(15, sebValto);
        ps.setString(16, leiras);
        ps.setString(17, hiba);
        ps.setInt(18, telephely);
        ps.setInt(19, ar);
        ps.execute();
    }

    //Motorkerékpár adatainak feltöltése az adatbázisba
    public void motorkerekparAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_KERESKEDES_MOTOR);
        ps.setString(1, rendszam);
        ps.setString(2, marka);
        ps.setString(3, modell);
        ps.setString(4, tipus);
        ps.setString(5, evjarat);
        ps.setString(6, honap);
        ps.setString(7, kivitel);
        ps.setString(8, allapot);
        ps.setString(9, uzemanyag);
        ps.setString(10, henger);
        ps.setString(11, kilometer);
        ps.setString(12, sajat);
        ps.setString(13, teljesitmeny);
        ps.setString(14, szin);
        ps.setString(15, sebValto);
        ps.setString(16, leiras);
        ps.setString(17, hiba);
        ps.setInt(18, telephely);
        ps.setInt(19, ar);
        ps.execute();
    }

    //Haszonjármű adatainak feltöltése az adatbázisba
    public void haszonjarmuAdatokFeltolt(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        // String hasznos = lista.get(12);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_INSERT_KERESKEDES_KAMION);
        ps.setString(1, rendszam);
        ps.setString(2, marka);
        ps.setString(3, modell);
        ps.setString(4, tipus);
        ps.setString(5, evjarat);
        ps.setString(6, honap);
        ps.setString(7, kivitel);
        ps.setString(8, allapot);
        ps.setString(9, uzemanyag);
        ps.setString(10, henger);
        ps.setString(11, kilometer);
        ps.setString(12, sajat);
        ps.setString(13, teljesitmeny);
        ps.setString(14, szin);
        ps.setString(15, sebValto);
        ps.setString(16, leiras);
        ps.setString(17, hiba);
        ps.setInt(18, telephely);
        ps.setInt(19, ar);
        ps.execute();
    }

    //Személyautó adatainak módosítása az adatbázisba
    public void szemelyautoAdatokModosit(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_UPDATE_KERESKEDES_AUTO);
        ps.setString(19, rendszam);
        ps.setString(1, marka);
        ps.setString(2, modell);
        ps.setString(3, tipus);
        ps.setString(4, evjarat);
        ps.setString(5, honap);
        ps.setString(6, kivitel);
        ps.setString(7, allapot);
        ps.setString(8, uzemanyag);
        ps.setString(9, henger);
        ps.setString(10, kilometer);
        ps.setString(11, sajat);
        ps.setString(12, teljesitmeny);
        ps.setString(13, szin);
        ps.setString(14, sebValto);
        ps.setString(15, leiras);
        ps.setString(16, hiba);
        ps.setInt(17, telephely);
        ps.setInt(18, ar);
        ps.executeUpdate();
    }

    //Motorkerékpár adatainak módosítása az adatbázisba
    public void motorkerekparAdatokModosit(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_UPDATE_KERESKEDES_MOTOR);
        ps.setString(19, rendszam);
        ps.setString(1, marka);
        ps.setString(2, modell);
        ps.setString(3, tipus);
        ps.setString(4, evjarat);
        ps.setString(5, honap);
        ps.setString(6, kivitel);
        ps.setString(7, allapot);
        ps.setString(8, uzemanyag);
        ps.setString(9, henger);
        ps.setString(10, kilometer);
        ps.setString(11, sajat);
        ps.setString(12, teljesitmeny);
        ps.setString(13, szin);
        ps.setString(14, sebValto);
        ps.setString(15, leiras);
        ps.setString(16, hiba);
        ps.setInt(17, telephely);
        ps.setInt(18, ar);
        ps.executeUpdate();
    }

    //Haszonjármű adatainak módosítása az adatbázisba
    public void haszonjarmuAdatokModosit(ArrayList<String> lista) throws SQLException {
        String rendszam = lista.get(0);
        String marka = lista.get(1);
        String modell = lista.get(2);
        String tipus = lista.get(3);
        String evjarat = lista.get(4);
        String honap = lista.get(5);
        String kivitel = lista.get(6);
        String allapot = lista.get(7);
        String uzemanyag = lista.get(8);
        String henger = lista.get(9);
        String kilometer = lista.get(10);
        String sajat = lista.get(11);
        String teljesitmeny = lista.get(12);
        String szin = lista.get(13);
        String sebValto = lista.get(14);
        String leiras = lista.get(15);
        String hiba = lista.get(16);
        int telephely = Integer.parseInt(lista.get(17));
        int ar = Integer.parseInt(lista.get(18));
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_UPDATE_KERESKEDES_KAMION);
        ps.setString(19, rendszam);
        ps.setString(1, marka);
        ps.setString(2, modell);
        ps.setString(3, tipus);
        ps.setString(4, evjarat);
        ps.setString(5, honap);
        ps.setString(6, kivitel);
        ps.setString(7, allapot);
        ps.setString(8, uzemanyag);
        ps.setString(9, henger);
        ps.setString(10, kilometer);
        ps.setString(11, sajat);
        ps.setString(12, teljesitmeny);
        ps.setString(13, szin);
        ps.setString(14, sebValto);
        ps.setString(15, leiras);
        ps.setString(16, hiba);
        ps.setInt(17, telephely);
        ps.setInt(18, ar);
        ps.executeUpdate();
    }

    //Személyautó törlése
    public void szemelyautoAdatokTorol(String rsz) throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_DELETE_KERESKEDES_AUTO);
        ps.setString(1, rsz);
        ps.executeUpdate();
    }

    //Motorkerékpár törlése
    public void motorkerekparAdatokTorol(String rsz) throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_DELETE_KERESKEDES_MOTOR);
        ps.setString(1, rsz);
        ps.executeUpdate();
    }

    //Haszonjármű törlése
    public void haszonjarmuAdatokTorol(String rsz) throws SQLException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = c.prepareStatement(SQL_DELETE_KERESKEDES_KAMION);
        ps.setString(1, rsz);
        ps.executeUpdate();
    }

    //Jelszó titkosítása
    public String jelszoTitkositas(String passwordToHash, String salt) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            modellTitkositottJelszo = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return modellTitkositottJelszo;
    }

    //Személyautó lekérdezése az adatbázisból
    public DefaultTableModel getAutoAdatok()
            throws SQLException, ClassNotFoundException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_AUTO_ADATOK_MODOSIT_TABLAZAT);
        ResultSetMetaData metaadat = rs.getMetaData();
        String[] feliratTomb = new String[metaadat.getColumnCount()];
        Class[] tipusTomb = new Class[feliratTomb.length];
        for (int i = 0; i < feliratTomb.length; i++) {
            feliratTomb[i] = metaadat.getColumnName(i + 1);
            tipusTomb[i] = Class.forName(metaadat.getColumnClassName(i + 1));
        }
        ArrayList<Object[]> adatLista = new ArrayList<>();
        while (rs.next()) {
            Object[] rekord = new Object[feliratTomb.length];
            for (int i = 0; i < rekord.length; i++) {
                rekord[i] = rs.getObject(i + 1);
            }
            adatLista.add(rekord);
        }
        Object[][] adatTomb = new Object[adatLista.size()][feliratTomb.length];
        for (int i = 0; i < adatLista.size(); i++) {
            adatTomb[i] = adatLista.get(i);
        }
        return new DefaultTableModel(adatTomb, feliratTomb) {
            Class[] oszlopTipusTomb = tipusTomb;

            @Override
            public Class getColumnClass(int columnIndex) {
                return oszlopTipusTomb[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    //Motorkerékpár lekérdezése az adatbázisból
    public DefaultTableModel getMotorAdatok()
            throws SQLException, ClassNotFoundException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_MOTOR_ADATOK_MODOSIT_TABLAZAT);
        ResultSetMetaData metaadat = rs.getMetaData();
        String[] feliratTomb = new String[metaadat.getColumnCount()];
        Class[] tipusTomb = new Class[feliratTomb.length];
        for (int i = 0; i < feliratTomb.length; i++) {
            feliratTomb[i] = metaadat.getColumnName(i + 1);
            tipusTomb[i] = Class.forName(metaadat.getColumnClassName(i + 1));
        }
        ArrayList<Object[]> adatLista = new ArrayList<>();
        while (rs.next()) {
            Object[] rekord = new Object[feliratTomb.length];
            for (int i = 0; i < rekord.length; i++) {
                rekord[i] = rs.getObject(i + 1);
            }
            adatLista.add(rekord);
        }
        Object[][] adatTomb = new Object[adatLista.size()][feliratTomb.length];
        for (int i = 0; i < adatLista.size(); i++) {
            adatTomb[i] = adatLista.get(i);
        }

        return new DefaultTableModel(adatTomb, feliratTomb) {
            Class[] oszlopTipusTomb = tipusTomb;

            @Override
            public Class getColumnClass(int columnIndex) {
                return oszlopTipusTomb[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    //Haszonjármű lekérdezése az adatbázisból
    public DefaultTableModel getKamionAdatok()
            throws SQLException, ClassNotFoundException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_KAMION_ADATOK_MODOSIT_TABLAZAT);
        ResultSetMetaData metaadat = rs.getMetaData();
        String[] feliratTomb = new String[metaadat.getColumnCount()];
        Class[] tipusTomb = new Class[feliratTomb.length];
        for (int i = 0; i < feliratTomb.length; i++) {
            feliratTomb[i] = metaadat.getColumnName(i + 1);
            tipusTomb[i] = Class.forName(metaadat.getColumnClassName(i + 1));
        }
        ArrayList<Object[]> adatLista = new ArrayList<>();
        while (rs.next()) {
            Object[] rekord = new Object[feliratTomb.length];
            for (int i = 0; i < rekord.length; i++) {
                rekord[i] = rs.getObject(i + 1);
            }
            adatLista.add(rekord);
        }
        Object[][] adatTomb = new Object[adatLista.size()][feliratTomb.length];
        for (int i = 0; i < adatLista.size(); i++) {
            adatTomb[i] = adatLista.get(i);
        }
        return new DefaultTableModel(adatTomb, feliratTomb) {
            Class[] oszlopTipusTomb = tipusTomb;

            @Override
            public Class getColumnClass(int columnIndex) {
                return oszlopTipusTomb[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    //Ügyfelek lekérdezése az adatbázisból a módosításhoz
    public DefaultTableModel getUgyfelAdatokTablazat()
            throws SQLException, ClassNotFoundException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_UGYFEL);
        ResultSetMetaData metaadat = rs.getMetaData();
        String[] feliratTomb = new String[metaadat.getColumnCount()];
        Class[] tipusTomb = new Class[feliratTomb.length];
        for (int i = 0; i < feliratTomb.length; i++) {
            feliratTomb[i] = metaadat.getColumnName(i + 1);
            tipusTomb[i] = Class.forName(metaadat.getColumnClassName(i + 1));
        }
        ArrayList<Object[]> adatLista = new ArrayList<>();
        while (rs.next()) {
            Object[] rekord = new Object[feliratTomb.length];
            for (int i = 0; i < rekord.length; i++) {
                rekord[i] = rs.getObject(i + 1);
            }
            adatLista.add(rekord);
        }
        Object[][] adatTomb = new Object[adatLista.size()][feliratTomb.length];
        for (int i = 0; i < adatLista.size(); i++) {
            adatTomb[i] = adatLista.get(i);
        }
        return new DefaultTableModel(adatTomb, feliratTomb) {
            Class[] oszlopTipusTomb = tipusTomb;

            @Override
            public Class getColumnClass(int columnIndex) {
                return oszlopTipusTomb[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    //Beszerzés adatainak lekérdezése az adatbázisból a módosításhoz
    public DefaultTableModel getBeszerzesAdatokTablazat()
            throws SQLException, ClassNotFoundException {
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        ResultSet rs = c.createStatement().executeQuery(SQL_BESZERZES_ADATOK_TABLAZAT);
        ResultSetMetaData metaadat = rs.getMetaData();
        String[] feliratTomb = new String[metaadat.getColumnCount()];
        Class[] tipusTomb = new Class[feliratTomb.length];
        for (int i = 0; i < feliratTomb.length; i++) {
            feliratTomb[i] = metaadat.getColumnName(i + 1);
            tipusTomb[i] = Class.forName(metaadat.getColumnClassName(i + 1));
        }
        ArrayList<Object[]> adatLista = new ArrayList<>();
        while (rs.next()) {
            Object[] rekord = new Object[feliratTomb.length];
            for (int i = 0; i < rekord.length; i++) {
                rekord[i] = rs.getObject(i + 1);
            }
            adatLista.add(rekord);
        }
        Object[][] adatTomb = new Object[adatLista.size()][feliratTomb.length];
        for (int i = 0; i < adatLista.size(); i++) {
            adatTomb[i] = adatLista.get(i);
        }
        return new DefaultTableModel(adatTomb, feliratTomb) {
            Class[] oszlopTipusTomb = tipusTomb;

            @Override
            public Class getColumnClass(int columnIndex) {
                return oszlopTipusTomb[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }
}
