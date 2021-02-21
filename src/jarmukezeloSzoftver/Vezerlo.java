package jarmukezeloSzoftver;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Vezerlo implements ActionListener {

    private Modell modell = null;
    private Nezet nezet = null;
    Timer t = new Timer(400, this);

    //vezérlő konstruktor
    public Vezerlo() {
        modell = new Modell();
        nezet = new Nezet(this);
        nezet.setVisible(true);
        t.start();

        try {
            nezet.szervizMarkaFrissit(szervizMarkaListaKeszit(modell.getSzervizMarkaLista()));
            nezet.ugyfelFrissit(ugyfelListaKeszit(modell.getUgyfelLista()));
            nezet.rendszamFrissit(rendszamListaKeszit(modell.getRendszamLista()));
            nezet.munkalapSzamFrissit(munkalapSzamListaKeszit(modell.getMunkalapSzamLista()));
            nezet.dolgozoFrissit(dolgozoListaKeszit(modell.getDolgozoLista()));
            nezet.feladatFrissit(feladatListaKeszit(modell.getFeladatLista()));
            nezet.beszerzesMunkalapSzamFrissit(munkalapSzamListaKeszit(modell.getBeszerzesMunkalapSzamLista()));
            nezet.alkatreszFrissit(alkatreszListaKeszit(modell.getAlkatreszLista()));
            nezet.autoMarkaFrissit(markaListaKeszit(modell.getAutoMarkaLista()));
            nezet.autoUzemanyagFrissit(uzemanyagListaKeszit(modell.getAutoUzemanyagLista()));
            nezet.autoKivitelFrissit(kivitelListaKeszit(modell.getAutoKivitelLista()));
            nezet.autoAllapotFrissit(allapotListaKeszit(modell.getAutoAllapotLista()));
            nezet.motorMarkaFrissit(markaListaKeszit(modell.getMotorMarkaLista()));
            nezet.motorUzemanyagFrissit(uzemanyagListaKeszit(modell.getMotorUzemanyagLista()));
            nezet.motorKivitelFrissit(kivitelListaKeszit(modell.getMotorKivitelLista()));
            nezet.motorAllapotFrissit(allapotListaKeszit(modell.getMotorAllapotLista()));
            nezet.kamionMarkaFrissit(markaListaKeszit(modell.getKamionMarkaLista()));
            nezet.kamionUzemanyagFrissit(uzemanyagListaKeszit(modell.getKamionUzemanyagLista()));
            nezet.kamionKivitelFrissit(kivitelListaKeszit(modell.getKamionKivitelLista()));
            nezet.kamionAllapotFrissit(allapotListaKeszit(modell.getKamionAllapotLista()));
            nezet.setUgyfelModositTablazat(modell.getUgyfelAdatokTablazat());
            nezet.setBeszerzesModositTablazat(modell.getBeszerzesAdatokTablazat());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Vezérlő konstruktor nem tudott lefutni!): " + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
    }

    //Személyautó adatok táblázatba módosításhoz
    public void setAutoAdatokTablazat() {
        try {
            nezet.setJarmuModositTablazat(modell.getAutoAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Autó adatok táblázat metódus nem tudott lefutni!): " + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
    }

    //Motorkerékpár adatok táblázatba módosításhoz
    public void setMotorAdatokTablazat() {
        try {
            nezet.setJarmuModositTablazat(modell.getMotorAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Motor adatok táblázat metódus nem tudott lefutni!): " + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
    }

    //Haszonjármű adatok táblázatba módosításhoz
    public void setKamionAdatokTablazat() {
        try {
            nezet.setJarmuModositTablazat(modell.getKamionAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Kamion adatok táblázat metódus nem tudott lefutni!): " + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
    }

    //Felhasználó segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> felhasznaloLista(String vezetekNev,
            String keresztNev, String felhasznalonev, String jelszo) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(vezetekNev);
        lista.add(keresztNev);
        lista.add(felhasznalonev);
        lista.add(jelszo);
        return lista;
    }

    //Ügyfél segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> ugyfelLista(String okmanyAzon,
            String okmanyTipus, String nev, String elerhetoseg, String lakcim) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(okmanyAzon);
        lista.add(okmanyTipus);
        lista.add(nev);
        lista.add(elerhetoseg);
        lista.add(lakcim);
        return lista;
    }

    //Márka segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> markaLista(String kod, String elnevezes) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(kod);
        lista.add(elnevezes);
        return lista;
    }

    //Autó segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> autoLista(String rendszam, String ugyfelAzon,
            String marka, String evjarat, String tipus, String szin, String motorszam,
            String alvazszam) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(rendszam);
        lista.add(ugyfelAzon);
        lista.add(marka);
        lista.add(evjarat);
        lista.add(tipus);
        lista.add(szin);
        lista.add(motorszam);
        lista.add(alvazszam);
        return lista;
    }

    //Munkalap segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> munkalapLista(String munkalapSzam, String rendszam,
            String hiba, String munkaDij, String anyagDij, String valHatarido) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(munkalapSzam);
        lista.add(rendszam);
        lista.add(hiba);
        lista.add(munkaDij);
        lista.add(anyagDij);
        lista.add(valHatarido);
        return lista;
    }

    //Feladat segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> feladatLista(String feladat, String munkadij,
            String elnevezes, String tipus) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(feladat);
        lista.add(munkadij);
        lista.add(elnevezes);
        lista.add(tipus);
        return lista;
    }

    //Szerelés segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> szerelesLista(String munkalapSzam, String szerelo,
            String feladat, String kezdes, String befejezes, String munkaora) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(munkalapSzam);
        lista.add(szerelo);
        lista.add(feladat);
        lista.add(kezdes);
        lista.add(befejezes);
        lista.add(munkaora);
        return lista;
    }

    //Alkatrész segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> alkatreszLista(String kod, String megnevezes) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(kod);
        lista.add(megnevezes);
        return lista;
    }

    //Beszerzés segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> beszerzesLista(String kod, String munkalapSzam,
            String alkatresz, String ar, String megrendelve) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(kod);
        lista.add(munkalapSzam);
        lista.add(alkatresz);
        lista.add(ar);
        lista.add(megrendelve);
        return lista;
    }

    //Személyautó segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> szemelyautoLista(String rendszam, String marka,
            String modell, String tipus, String evjarat, String honap, String kivetel,
            String allapot, String uzemanyag, String henger, String kilometer,
            String sajat, String teljesitmeny, String szin, String sebValto,
            String leiras, String hiba, String telephely, String ar) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(rendszam);
        lista.add(marka);
        lista.add(modell);
        lista.add(tipus);
        lista.add(evjarat);
        lista.add(honap);
        lista.add(kivetel);
        lista.add(allapot);
        lista.add(uzemanyag);
        lista.add(henger);
        lista.add(kilometer);
        lista.add(sajat);
        lista.add(teljesitmeny);
        lista.add(szin);
        lista.add(sebValto);
        lista.add(leiras);
        lista.add(hiba);
        lista.add(telephely);
        lista.add(ar);
        return lista;
    }

    //motorkerékpár segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> motorkerekparLista(String rendszam, String marka,
            String modell, String tipus, String evjarat, String honap, String kivetel,
            String allapot, String uzemanyag, String henger, String kilometer,
            String sajat, String teljesitmeny, String szin, String sebValto,
            String leiras, String hiba, String telephely, String ar) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(rendszam);
        lista.add(marka);
        lista.add(modell);
        lista.add(tipus);
        lista.add(evjarat);
        lista.add(honap);
        lista.add(kivetel);
        lista.add(allapot);
        lista.add(uzemanyag);
        lista.add(henger);
        lista.add(kilometer);
        lista.add(sajat);
        lista.add(teljesitmeny);
        lista.add(szin);
        lista.add(sebValto);
        lista.add(leiras);
        lista.add(hiba);
        lista.add(telephely);
        lista.add(ar);
        return lista;
    }

    //haszonjármű segédlista az adatbázisba feltöltött adatokhoz
    public ArrayList<String> haszonjarmuLista(String rendszam, String marka,
            String modell, String tipus, String evjarat, String honap, String kivetel,
            String allapot, String uzemanyag, String henger, String kilometer,
            String sajat, String teljesitmeny, String szin, String sebValto,
            String leiras, String hiba, String telephely, String ar) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(rendszam);
        lista.add(marka);
        lista.add(modell);
        lista.add(tipus);
        lista.add(evjarat);
        lista.add(honap);
        lista.add(kivetel);
        lista.add(allapot);
        lista.add(uzemanyag);
        lista.add(henger);
        lista.add(kilometer);
        lista.add(sajat);
        lista.add(teljesitmeny);
        lista.add(szin);
        lista.add(sebValto);
        lista.add(leiras);
        lista.add(hiba);
        lista.add(telephely);
        lista.add(ar);
        return lista;
    }

    //Felhasználók adatainak adatbázisba való feltöltése
    public void felhasznaloAdatokFeltolt() {
        try {
            modell.felhasznaloAdatokFeltolt(felhasznaloLista(nezet.getVezeteknev(),
                    nezet.getKeresztnev(), nezet.getFelhasznaloNev(), nezet.getJelszo()));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Felhasználó Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Ügyfelek adatainak adatbázisba való feltöltése
    public void ugyfelAdatokFeltolt() {
        try {
            modell.ugyfelAdatokFeltolt(ugyfelLista(nezet.getUgyfelOkmanyAzonosito(),
                    nezet.getUgyfelOkmanyTipus(), nezet.getUgyfelNev(), nezet.getUgyfelElerhetoseg(),
                    nezet.getUgyfelLakcim()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Ügyfel Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Ügyfelek adatainak módosítása
    public void ugyfelAdatokModosit() {
        try {
            modell.ugyfelAdatokModosit(ugyfelLista(nezet.getTxtUgyfelModositOkmanyAzonosito(),
                    nezet.getTxtUgyfelModositOkmanyTipus(), nezet.getTxtUgyfelModositNev(),
                    nezet.getTxtUgyfelModositElerhetoseg(),
                    nezet.getTxtUgyfelModositLakcim()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Ügyfel Adatok Módosít metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Márkák adatainak adatbázisba való feltöltése
    public void markaAdatokFeltolt() {
        try {
            modell.markaAdatokFeltolt(markaLista(nezet.getMarkaKod(), nezet.getMarkaElnevezes()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Márka Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Autók adatainak adatbázisba való feltöltése
    public void autoAdatokFeltolt() {
        try {
            modell.autoAdatokFeltolt(autoLista(nezet.getAutoRendszam(),
                    nezet.getAutoUgyfel(), nezet.getAutoMarka(), nezet.getAutoEvjarat(),
                    nezet.getAutoTipus(), nezet.getAutoSzin(), nezet.getAutoMotorszam(),
                    nezet.getAutoAlvazszam()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Autó Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Munkalap adatainak adatbázisba való feltöltése
    public void munkalapAdatokFeltolt() {
        try {
            modell.munkalapAdatokFeltolt(munkalapLista(nezet.getMunkalapszam(),
                    nezet.getMunkalapRendszam(), nezet.getMunkalapHiba(), "1000",
                    "2000", nezet.getMunkalapHatarido()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Munkalap Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Feladat adatainak adatbázisba való feltöltése
    public void feladatAdatokFeltolt() {
        try {
            modell.feladatAdatokFeltolt(feladatLista(nezet.getFeladat(),
                    nezet.getFeladatMunkaDij(), nezet.getFeladatElnevezes(),
                    nezet.getFeladatTipus()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Feladat Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Szerelés adatainak adatbázisba való feltöltése
    public void szerelesAdatokFeltolt() {
        try {
            modell.szerelesAdatokFeltolt(szerelesLista(nezet.getSzerelesMunkalapSzam(),
                    nezet.getSzerelesSzerelo(), nezet.getSzerelesFeladat(),
                    nezet.getSzerelesKezesIdo(), nezet.getSzerelesBefejezIdo(),
                    nezet.getSzerelesMunkaora()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Szerelés Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Alkatrész adatainak adatbázisba való feltöltése
    public void alkatreszAdatokFeltolt() {
        try {
            modell.alkatreszAdatokFeltolt(alkatreszLista(nezet.getAlkatreszKod(),
                    nezet.getAlkatreszMegnevezes()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Alkatrész Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Pdf cella tulajdonságainak beállítása
    private PdfPCell cella(String szoveg, int betumeret, boolean felkover,
            int igazitas) throws DocumentException, IOException {
        PdfPCell cella = new PdfPCell();
        BaseFont betutipus = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Paragraph bekezdes = new Paragraph(szoveg,
                new Font(betutipus, betumeret, felkover ? Font.BOLD : Font.NORMAL));
        bekezdes.setAlignment(igazitas);
        cella.addElement(bekezdes);
        cella.setPadding(2);
        cella.setVerticalAlignment(Paragraph.ALIGN_CENTER);
        return cella;
    }

    //Pdf Generálása
    public void pdfGeneral() {
        try {
            Document pdfFajl = new Document(PageSize.A4, 15, 15, 30, 20);
            PdfWriter.getInstance(pdfFajl,
                    new FileOutputStream("./files/Munkalap.pdf"));
            pdfFajl.open();
            //oldal fejléce
            BaseFont betutípus = BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Paragraph fejlec
                    = new Paragraph("Munkalap", new Font(betutípus, 20, Font.BOLD));
            fejlec.setAlignment(Paragraph.ALIGN_CENTER);
            pdfFajl.add(fejlec);
            Paragraph ugyfelFejlec
                    = new Paragraph("Ügyfél", new Font(betutípus, 20, Font.BOLD));
            ugyfelFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(ugyfelFejlec);
            //táblázat
            PdfPTable ugyfel = new PdfPTable(4);
            ugyfel.setWidthPercentage(85);
            ugyfel.setSpacingBefore(40);
            ugyfel.setWidths(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
            //táblázat fejléce
            ugyfel.addCell(cella("Okmány azonosítója", 14, true, Paragraph.ALIGN_CENTER));
            ugyfel.addCell(cella("Név", 14, true, Paragraph.ALIGN_CENTER));
            ugyfel.addCell(cella("Elérhetőség", 14, true, Paragraph.ALIGN_CENTER));
            ugyfel.addCell(cella("Lakcím", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            ugyfel.addCell(cella(nezet.ugyfelAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            ugyfel.addCell(cella(nezet.ugyfelAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            ugyfel.addCell(cella(nezet.ugyfelAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            ugyfel.addCell(cella(nezet.ugyfelAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(ugyfel);

            Paragraph autoFejlec
                    = new Paragraph("Autó", new Font(betutípus, 20, Font.BOLD));
            ugyfelFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(autoFejlec);
            //táblázat
            PdfPTable auto = new PdfPTable(8);
            auto.setWidthPercentage(85);
            auto.setSpacingBefore(40);
            auto.setWidths(new float[]{0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f, 0.125f});
            //táblázat fejléce
            auto.addCell(cella("Rendszám", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Ügyfél azonosító", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Márka", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Típus", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Évjárat", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Szín", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Motorszám", 14, true, Paragraph.ALIGN_CENTER));
            auto.addCell(cella("Alvázszám", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            auto.addCell(cella(nezet.autoAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(4), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(5), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(6), 12, false, Paragraph.ALIGN_LEFT));
            auto.addCell(cella(nezet.autoAdatLista().get(7), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(auto);

            Paragraph munkalapFejlec
                    = new Paragraph("Munkalap", new Font(betutípus, 20, Font.BOLD));
            munkalapFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(munkalapFejlec);
            //táblázat
            PdfPTable munkalap = new PdfPTable(4);
            munkalap.setWidthPercentage(85);
            munkalap.setSpacingBefore(40);
            munkalap.setWidths(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
            //táblázat fejléce
            munkalap.addCell(cella("Munkalap Szám", 14, true, Paragraph.ALIGN_CENTER));
            munkalap.addCell(cella("Rendszám", 14, true, Paragraph.ALIGN_CENTER));
            munkalap.addCell(cella("Vállalási idő", 14, true, Paragraph.ALIGN_CENTER));
            munkalap.addCell(cella("Hiba", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            munkalap.addCell(cella(nezet.munkalapAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            munkalap.addCell(cella(nezet.munkalapAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            munkalap.addCell(cella(nezet.munkalapAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            munkalap.addCell(cella(nezet.munkalapAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(munkalap);

            Paragraph feladatFejlec
                    = new Paragraph("Feladat", new Font(betutípus, 20, Font.BOLD));
            feladatFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(feladatFejlec);
            //táblázat
            PdfPTable feladat = new PdfPTable(4);
            feladat.setWidthPercentage(85);
            feladat.setSpacingBefore(40);
            feladat.setWidths(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
            //táblázat fejléce
            feladat.addCell(cella("Feladat kód", 14, true, Paragraph.ALIGN_CENTER));
            feladat.addCell(cella("Munkadíj", 14, true, Paragraph.ALIGN_CENTER));
            feladat.addCell(cella("Típus", 14, true, Paragraph.ALIGN_CENTER));
            feladat.addCell(cella("Elnevezés", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            feladat.addCell(cella(nezet.feladatAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            feladat.addCell(cella(nezet.feladatAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            feladat.addCell(cella(nezet.feladatAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            feladat.addCell(cella(nezet.feladatAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(feladat);

            Paragraph szerelesFejlec
                    = new Paragraph("Szerelés", new Font(betutípus, 20, Font.BOLD));
            szerelesFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(szerelesFejlec);
            //táblázat
            PdfPTable szereles = new PdfPTable(5);
            szereles.setWidthPercentage(85);
            szereles.setSpacingBefore(40);
            szereles.setWidths(new float[]{0.20f, 0.20f, 0.20f, 0.20f, 0.20f});
            //táblázat fejléce
            szereles.addCell(cella("Munkalap szám", 14, true, Paragraph.ALIGN_CENTER));
            szereles.addCell(cella("Feladat", 14, true, Paragraph.ALIGN_CENTER));
            szereles.addCell(cella("Szerelés kezdési idő", 14, true, Paragraph.ALIGN_CENTER));
            szereles.addCell(cella("Szerelés kezdési idő", 14, true, Paragraph.ALIGN_CENTER));
            szereles.addCell(cella("Munka óra", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            szereles.addCell(cella(nezet.szerelesAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            szereles.addCell(cella(nezet.szerelesAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            szereles.addCell(cella(nezet.szerelesAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            szereles.addCell(cella(nezet.szerelesAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            szereles.addCell(cella(nezet.szerelesAdatLista().get(4), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(szereles);

            Paragraph beszerzesFejlec
                    = new Paragraph("Beszerzés", new Font(betutípus, 20, Font.BOLD));
            beszerzesFejlec.setAlignment(Paragraph.ALIGN_LEFT);
            pdfFajl.add(beszerzesFejlec);
            //táblázat
            PdfPTable beszerzes = new PdfPTable(5);
            beszerzes.setWidthPercentage(85);
            beszerzes.setSpacingBefore(40);
            beszerzes.setWidths(new float[]{0.20f, 0.20f, 0.20f, 0.20f, 0.20f});
            //táblázat fejléce
            beszerzes.addCell(cella("Beszerzési kód", 14, true, Paragraph.ALIGN_CENTER));
            beszerzes.addCell(cella("Munkalap szám", 14, true, Paragraph.ALIGN_CENTER));
            beszerzes.addCell(cella("Alkatrész", 14, true, Paragraph.ALIGN_CENTER));
            beszerzes.addCell(cella("Alkatrész ára", 14, true, Paragraph.ALIGN_CENTER));
            beszerzes.addCell(cella("Megrendelési idő", 14, true, Paragraph.ALIGN_CENTER));
            //táblázat adatai      
            beszerzes.addCell(cella(nezet.beszerzesAdatLista().get(0), 12, false, Paragraph.ALIGN_LEFT));
            beszerzes.addCell(cella(nezet.beszerzesAdatLista().get(1), 12, false, Paragraph.ALIGN_LEFT));
            beszerzes.addCell(cella(nezet.beszerzesAdatLista().get(2), 12, false, Paragraph.ALIGN_LEFT));
            beszerzes.addCell(cella(nezet.beszerzesAdatLista().get(3), 12, false, Paragraph.ALIGN_LEFT));
            beszerzes.addCell(cella(nezet.beszerzesAdatLista().get(4), 12, false, Paragraph.ALIGN_LEFT));
            pdfFajl.add(ugyfel);

            pdfFajl.close();
            JOptionPane.showMessageDialog(nezet, "Sikeres PDF generálás!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(nezet, "A PDF generálás sikertelen volt!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Beszerzés adatainak adatbázisba való feltöltése
    public void beszerzesAdatokFeltolt() {
        try {
            modell.beszerzesAdatokFeltolt(beszerzesLista(nezet.getBeszerzesKod(),
                    nezet.getBeszerzesMunkalap(), nezet.getBeszerzesAlkatresz(),
                    nezet.getBeszerzesAlkatreszAr(), nezet.getBeszerzesMegrendeles()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Beszerzés Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Beszerzés adatainak módosítása az adatbázisban
    public void beszerzesAdatokModosit() {
        try {
            modell.beszerzesAdatokModosit(beszerzesLista(nezet.getTxtBeszerzesModositBeszer(),
                    nezet.getCmbBeszerzesModositMunkalap(), nezet.getCmbBeszerzesModositAlkatresz(),
                    nezet.getTxtBeszerzesModositAlkAr(), nezet.getTxtBeszerzesModositMegrend()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Beszerzés Adatok Módosít metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Személyautó adatainak adatbázisba való feltöltése
    public void jarmuAutoAdatokFeltolt() {
        try {
            modell.szemelyautoAdatokFeltolt(szemelyautoLista(nezet.getJarmuHozzRendszam(),
                    nezet.getJarmuHozzMarka(), nezet.getJarmuHozzModell(),
                    nezet.getJarmuHozzTipus(), nezet.getJarmuHozzEvjarat(),
                    nezet.getJarmuHozzHonap(), nezet.getJarmuHozzKivitel(),
                    nezet.getJarmuHozzAllapot(), nezet.getJarmuHozzUzemanyag(),
                    nezet.getJarmuHozzHenger(), nezet.getJarmuHozzKilometer(),
                    nezet.getJarmuHozzSajatTomeg(), nezet.getJarmuHozzTeljesitmeny(),
                    nezet.getJarmuHozzSzin(), nezet.getJarmuHozzSebesseg(),
                    nezet.getJarmuHozzLeiras(), nezet.getJarmuHozzHiba(),
                    nezet.getJarmuTelephely(), nezet.getJarmuHozzAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű autó Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Személyautó adatainak módosítása
    public void jarmuAutoAdatokModosit() {
        try {
            modell.szemelyautoAdatokModosit(szemelyautoLista(nezet.getJarmuModositRendszam(),
                    nezet.getJarmuModositMarka(), nezet.getJarmuModositModell(),
                    nezet.getJarmuModositTipus(), nezet.getJarmuModositEvjarat(),
                    nezet.getJarmuModositHonap(), nezet.getJarmuModositKivitel(),
                    nezet.getJarmuModositAllapot(), nezet.getJarmuModositUzemanyag(),
                    nezet.getJarmuModositHenger(), nezet.getJarmuModositKilometer(),
                    nezet.getJarmuModositSajatTomeg(), nezet.getJarmuModositTeljesitmeny(),
                    nezet.getJarmuModositSzin(), nezet.getJarmuModositSebesseg(),
                    nezet.getJarmuModositLeiras(), nezet.getJarmuModositHiba(),
                    nezet.getJarmuModositTelephely(), nezet.getJarmuModositAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű autó Adatok Módosít metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Személyautó törlése az adatbázisból
    public void jarmuAutoAdatokTorol() {
        try {
            modell.szemelyautoAdatokTorol(nezet.getJarmuModositRendszam());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű autó Adatok Törlés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Motorkerékpár adatainak adatbázisba való feltöltése
    public void jarmuMotorAdatokFeltolt() {
        try {
            modell.motorkerekparAdatokFeltolt(motorkerekparLista(nezet.getJarmuHozzRendszam(),
                    nezet.getJarmuHozzMarka(), nezet.getJarmuHozzModell(),
                    nezet.getJarmuHozzTipus(), nezet.getJarmuHozzEvjarat(),
                    nezet.getJarmuHozzHonap(), nezet.getJarmuHozzKivitel(),
                    nezet.getJarmuHozzAllapot(), nezet.getJarmuHozzUzemanyag(),
                    nezet.getJarmuHozzHenger(), nezet.getJarmuHozzKilometer(),
                    nezet.getJarmuHozzSajatTomeg(), nezet.getJarmuHozzTeljesitmeny(),
                    nezet.getJarmuHozzSzin(), nezet.getJarmuHozzSebesseg(),
                    nezet.getJarmuHozzLeiras(), nezet.getJarmuHozzHiba(),
                    nezet.getJarmuTelephely(), nezet.getJarmuHozzAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű motor Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Motorkerékpár adatainak módosítása
    public void jarmuMotorAdatokModosit() {
        try {
            modell.motorkerekparAdatokModosit(motorkerekparLista(nezet.getJarmuModositRendszam(),
                    nezet.getJarmuModositMarka(), nezet.getJarmuModositModell(),
                    nezet.getJarmuModositTipus(), nezet.getJarmuModositEvjarat(),
                    nezet.getJarmuModositHonap(), nezet.getJarmuModositKivitel(),
                    nezet.getJarmuModositAllapot(), nezet.getJarmuModositUzemanyag(),
                    nezet.getJarmuModositHenger(), nezet.getJarmuModositKilometer(),
                    nezet.getJarmuModositSajatTomeg(), nezet.getJarmuModositTeljesitmeny(),
                    nezet.getJarmuModositSzin(), nezet.getJarmuModositSebesseg(),
                    nezet.getJarmuModositLeiras(), nezet.getJarmuModositHiba(),
                    nezet.getJarmuModositTelephely(), nezet.getJarmuModositAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű motor Adatok Módosít metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Motorkerékpár törlése az adatbázisból
    public void jarmuMotorAdatokTorol() {
        try {
            modell.motorkerekparAdatokTorol(nezet.getJarmuModositRendszam());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű motor Adatok Törlés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Haszonjármű adatainak adatbázisba való feltöltése
    public void jarmuKamionAdatokFeltolt() {
        try {
            modell.haszonjarmuAdatokFeltolt(haszonjarmuLista(nezet.getJarmuHozzRendszam(),
                    nezet.getJarmuHozzMarka(), nezet.getJarmuHozzModell(),
                    nezet.getJarmuHozzTipus(), nezet.getJarmuHozzEvjarat(),
                    nezet.getJarmuHozzHonap(), nezet.getJarmuHozzKivitel(),
                    nezet.getJarmuHozzAllapot(), nezet.getJarmuHozzUzemanyag(),
                    nezet.getJarmuHozzHenger(), nezet.getJarmuHozzKilometer(),
                    nezet.getJarmuHozzSajatTomeg(), nezet.getJarmuHozzTeljesitmeny(),
                    nezet.getJarmuHozzSzin(), nezet.getJarmuHozzSebesseg(),
                    nezet.getJarmuHozzLeiras(), nezet.getJarmuHozzHiba(),
                    nezet.getJarmuTelephely(), nezet.getJarmuHozzAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű kamion Adatok Feltölt metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Haszonjármű adatainak módosítása
    public void jarmuKamionAdatokModosit() {
        try {
            modell.haszonjarmuAdatokModosit(haszonjarmuLista(nezet.getJarmuModositRendszam(),
                    nezet.getJarmuModositMarka(), nezet.getJarmuModositModell(),
                    nezet.getJarmuModositTipus(), nezet.getJarmuModositEvjarat(),
                    nezet.getJarmuModositHonap(), nezet.getJarmuModositKivitel(),
                    nezet.getJarmuModositAllapot(), nezet.getJarmuModositUzemanyag(),
                    nezet.getJarmuModositHenger(), nezet.getJarmuModositKilometer(),
                    nezet.getJarmuModositSajatTomeg(), nezet.getJarmuModositTeljesitmeny(),
                    nezet.getJarmuModositSzin(), nezet.getJarmuModositSebesseg(),
                    nezet.getJarmuModositLeiras(), nezet.getJarmuModositHiba(),
                    nezet.getJarmuModositTelephely(), nezet.getJarmuModositAr()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Jármű kamion Adatok Módosít metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Haszonjármű törlése az adatbázisból
    public void jarmuKamionAdatokTorol() {
        try {
            modell.haszonjarmuAdatokTorol(nezet.getJarmuModositRendszam());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet, //null,
                    "Hiba! (Jármű autó Adatok Törlés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //A metódus ellenőrzi a felhasználónevet és a jelszót
    public boolean belephet() {
        ArrayList<Felhasznalo> felhasznaloLista;
        ArrayList<String> lista = new ArrayList<>();
        try {
            felhasznaloLista = modell.belepesAdatokFeltolt();
            for (Felhasznalo felhasznalo : felhasznaloLista) {
//                String fNev = felhasznalo.getFelhasznaloNev();
//                String jelszo = felhasznalo.getJelszo();
//                System.out.println("vezerlo fnev: " + fNev);
//                System.out.println("vezerlo jsz: " + jelszo);
                lista.add(felhasznalo.getFelhasznaloNev());
                lista.add(felhasznalo.getJelszo());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Beléphet metódus nem tudott lefutni!): " + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            //System.exit(0);
        }
//        System.out.println("teljes lista: " + lista);
//        csak az elsö felhasználó adatait adja
        String fnv = lista.get(0);
        String jsz = lista.get(1);
        String nFnv = nezet.getBejelentkezesFelhasznaloNev();
        String nJsz = nezet.getNezetJelszo();
//        System.out.println("modell felhasználónév: " + fnv);
//        System.out.println("modell jelszo: " + jsz);
//        System.out.println("nezet felhasználónév: " + nFnv);
//        System.out.println("nezet jelszó: " + nJsz);
        return (fnv.equals(nFnv)) && (jsz.equals(nJsz));
    }

    //Főmenü pontos idő beállitása
    public String ido() {
        Calendar now = Calendar.getInstance();
        int ora = now.get(Calendar.HOUR_OF_DAY);
        int perc = now.get(Calendar.MINUTE);
        int masodperc = now.get(Calendar.SECOND);
        if (perc < 10 && masodperc < 10) {
            return ora + ":0" + perc + ":0" + masodperc;
        }
        if (perc < 10 && masodperc > 10) {
            return ora + ":0" + perc + ":" + masodperc;
        }
        if (perc > 10 && masodperc < 10) {
            return ora + ":" + perc + ":0" + masodperc;
        } else {
            return ora + ":" + perc + ":" + masodperc;
        }
    }

    //Idő beállítása esemény
    @Override
    public void actionPerformed(ActionEvent ae) {
        nezet.lblIdoKijelzes.setText(ido());
    }

    //Kivitel lista készítése
    public DefaultComboBoxModel kivitelListaKeszit(ArrayList<Kivitel> kivitelLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("Mindegy");
        for (Kivitel kivitel : kivitelLista) {
            dcbm.addElement(kivitel);
        }
        return dcbm;
    }

    //Szervízben lévő márkák listájának készítése
    public DefaultComboBoxModel szervizMarkaListaKeszit(ArrayList<SzervizMarka> szervizMarkaLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (SzervizMarka marka : szervizMarkaLista) {
            dcbm.addElement(marka.getMarkaKod());
        }
        return dcbm;
    }

    //Ügyfel lista készítése
    public DefaultComboBoxModel ugyfelListaKeszit(ArrayList<Ugyfel> ugyfelLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Ugyfel ugyfel : ugyfelLista) {
            dcbm.addElement(ugyfel.getSzemelyazonOkmanySzam());
        }
        return dcbm;
    }

    //Rendszám lista készítése
    public DefaultComboBoxModel rendszamListaKeszit(ArrayList<Rendszam> rendszamLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Rendszam rendszam : rendszamLista) {
            dcbm.addElement(rendszam);
        }
        return dcbm;
    }

    //Munkalap szám lista készítése
    public DefaultComboBoxModel munkalapSzamListaKeszit(ArrayList<MunkalapSzam> munkalapSzamLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (MunkalapSzam munkalapSzam : munkalapSzamLista) {
            dcbm.addElement(munkalapSzam);
        }
        return dcbm;
    }

    //Dolgozó lista készítése
    public DefaultComboBoxModel dolgozoListaKeszit(ArrayList<Dolgozo> dolgozoLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Dolgozo dolgozo : dolgozoLista) {
            dcbm.addElement(dolgozo.getDkod());
        }
        return dcbm;
    }

    //Feladat lista készítése
    public DefaultComboBoxModel feladatListaKeszit(ArrayList<Feladat> feladatLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Feladat feladat : feladatLista) {
            dcbm.addElement(feladat.getKod());
        }
        return dcbm;
    }

    //Alkatrész lista készítése
    public DefaultComboBoxModel alkatreszListaKeszit(ArrayList<Alkatresz> alkatreszLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Alkatresz alkatresz : alkatreszLista) {
            dcbm.addElement(alkatresz);
        }
        return dcbm;
    }

    //Jármű keresés márka listájának készítése
    public DefaultComboBoxModel markaListaKeszit(ArrayList<Marka> markaLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("Mindegy");
        for (Marka marka : markaLista) {
            dcbm.addElement(marka);
        }
        return dcbm;
    }

    //Jármű keresés üzemanyag listájának készítése
    public DefaultComboBoxModel uzemanyagListaKeszit(ArrayList<Uzemanyag> uzemanyagLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("Mindegy");
        for (Uzemanyag uzemanyag : uzemanyagLista) {
            dcbm.addElement(uzemanyag);
        }
        return dcbm;
    }

    //Jármű keresés állapot listájának készítése
    public DefaultComboBoxModel allapotListaKeszit(ArrayList<Allapot> allapotLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Allapot allapot : allapotLista) {
            dcbm.addElement(allapot);
        }
        return dcbm;
    }

    //Ügyfel Módosít lista készítése
    public DefaultComboBoxModel ugyfelModositListaKeszit(ArrayList<Ugyfel> ugyfelLista) {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        for (Ugyfel ugyfel : ugyfelLista) {
            dcbm.addElement(ugyfel.getNev());
        }
        return dcbm;
    }

    //Személyautók megjelenítése
    public void jarmuKeresesAuto() {
        try {
            nezet.setTalalatTablazat(modell.getAutoAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Autó keresés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Motorkerékpárok megjelenítése
    public void jarmuKeresesMotor() {
        try {
            nezet.setTalalatTablazat(modell.getMotorAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Motor keresés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //Haszonjárművek megjelenítése
    public void jarmuKeresesKamion() {
        try {
            nezet.setTalalatTablazat(modell.getKamionAdatok());
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(nezet,
                    "Hiba! (Kamion keresés metódus nem tudott lefutni!): "
                    + e.getMessage(), "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}
