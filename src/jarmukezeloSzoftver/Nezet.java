package jarmukezeloSzoftver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel adam zoltan
 */
public class Nezet extends javax.swing.JFrame {

    private Vezerlo vezerlo = null;
    private final ImageIcon autoKep = new ImageIcon("./images/carIcon.png");
    private final ImageIcon motorKep = new ImageIcon("./images/motorCycleIcon.png");
    private final ImageIcon kamionKep = new ImageIcon("./images/truckIcon.png");
    private final ImageIcon felhasznaloKep = new ImageIcon("./images/userIcon.png");
    private final ImageIcon jelszoKep = new ImageIcon("./images/passwordIcon.png");
    private final JFileChooser openFile;
    private final JFileChooser saveFile;
    private BufferedImage bf;
    private String jelszo;
    private String[] autoKivitel = {"cabrio", "coupe", "egyterű", "ferdehátú", "kisbusz", "kombi", "lépcsőshátú",
        "pickup", "sedan", "sport", "terepjáró", "városi terepjáró", "egyéb"};
    private String[] motorKivitel = {"chopper", "classic(veterán)", "cross", "enduro", "gyerekmotor",
        "gyorsasági(sport)", "oldalkocsis",
        "quad", "robogó", "segédmotoros kerékpár", "supermoto", "trial", "túra",
        "versenymotor", "egyéb"};
    private String[] kamionKivitel = {"billencs", "darugémes", "darus", "dobozos", "duplakabinos",
        "harci jármű", "hűtős", "járműszállító",
        "konténerszállító", "mentő", "nyerges szerelvény", "platós", "ponyvás", "tartályos",
        "tűzoltó", "egyéb"};
    private String[] motorUzemanyag = {"benzin", "dízel", "elektromos"};
    private String[] autoKamionUzemanyag = {"benzin", "dízel", "benzin/gáz", "hibrid", "hibrid(benzin)",
        "hibrid(dízel)", "elektromos", "etanol", "biodízel", "gáz"};

    //Nézet konstruktor
    public Nezet(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
        initComponents();
        openFile = new JFileChooser();
        openFile.setCurrentDirectory(new File("\\"));
        openFile.setFileFilter(new FileNameExtensionFilter("jpg", "JPG"));
        saveFile = new JFileChooser();
        saveFile.setCurrentDirectory(new File("\\"));
        saveFile.setFileFilter(new FileNameExtensionFilter("jpg", "JPG"));
        pnlSzerviz.setVisible(false);
        pnlFomenu.setVisible(false);
        pnlJarmuHozzaAdasa.setVisible(false);
        pnlJarmuKereses.setVisible(false);
        pnlSzervizModositas.setVisible(false);
        pnlFelhasznalo.setVisible(false);
        pnlTalalat.setVisible(false);
        pnlKep.setVisible(false);
        cmbSzemelyautoModell.setEnabled(false);
        cmbMotorkerekparModell.setEnabled(false);
        cmbHaszonjarmuModell.setEnabled(false);

        lblKedvez.setVisible(false);
        cmbUgyfelKedvezmeny.setVisible(false);

        tblJarmuModosit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblUgyfel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblBeszerzes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTalalat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    //Idő megjelenítése
    public void setLblIdoKijelzes() {
        lblIdoKijelzes.setText(vezerlo.ido());
    }

    //Szerviz ügyfél név validálás
    public boolean SzervizUgyfelNevValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,50}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelNev.getText());
        return m.find();
    }

    //Szerviz ügyfél lakcim validálás
    public boolean SzervizUgyfelLakcimValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí\\d\\.\\/]{1,50}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelLakcim.getText());
        return m.find();
    }

    //Szerviz ügyfél elérhetőség validálás
    public boolean SzervizUgyfelElerhetosegValidalas() {
        String pattern = "^[\\d+\\/()-]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelElerhetoseg.getText());
        return m.find();
    }

    //Szerviz ügyfél okmány típus validálás
    public boolean SzervizUgyfelOkmanyTipusValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelOkmanyTipus.getText());
        return m.find();
    }

    //Szerviz ügyfél okmányazonosító validálás
    public boolean SzervizUgyfelOkmanyAzonValidalas() {
        String pattern = "^[A-Z\\d]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelOkmanyAzonosito.getText());
        return m.find();
    }

    //Szerviz ügyfél név módosítás validálás
    public boolean SzervizUgyfelModositNevValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,50}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelModositNev.getText());
        return m.find();
    }

    //Szerviz ügyfél lakcim módosítás validálás
    public boolean SzervizUgyfelModositLakcimValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí\\d\\.\\/]{1,50}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelModositLakcim.getText());
        return m.find();
    }

    //Szerviz ügyfél elérhetőség módosítás validálás
    public boolean SzervizUgyfelModositElerhetosegValidalas() {
        String pattern = "^[\\s\\d+\\/()-]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelModositElerhetoseg.getText());
        return m.find();
    }

    //Szerviz ügyfél okmány típus módosítás validálás
    public boolean SzervizUgyfelModositOkmanyTipusValidalas() {
        String pattern = "^[A-Za-z\\sÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelModositOkmanyTipus.getText());
        return m.find();
    }

    //Szerviz ügyfél okmányazonosító módosítás validálás
    public boolean SzervizUgyfelModositOkmanyAzonValidalas() {
        String pattern = "^[A-Z\\d]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtUgyfelModositOkmanyAzonosito.getText());
        return m.find();
    }

    //Szerviz márka kód validálás
    public boolean SzervizMarkaKodValidalas() {
        String pattern = "^ma[\\/]{1}[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMarkaMarkKod.getText());
        return m.find();
    }

    //Szerviz márka elnevezés validálás
    public boolean SzervizMarkaElnevezesValidalas() {
        String pattern = "^[a-zA-Z\\s-]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMarkaElnevezes.getText());
        return m.find();
    }

    //Szerviz autó rendszám validálás
    public boolean SzervizAutoRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAutoRendszam.getText());
        return m.find();
    }

    //Szerviz autó típus validálás
    public boolean SzervizAutoTipusValidalas() {
        String pattern = "^[a-zA-Z\\s\\.\\d]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAutoTipus.getText());
        return m.find();
    }

    //Szerviz autó Motorszám validálás
    public boolean SzervizAutoMotorszamValidalas() {
        String pattern = "^[A-Z\\d]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAutoMSzam.getText());
        return m.find();
    }

    //Szerviz autó Alvázszám validálás
    public boolean SzervizAutoAlvazszamValidalas() {
        String pattern = "^[A-Z\\d]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAutoASzam.getText());
        return m.find();
    }

    //Szerviz munkalap munkalap szám validálás
    public boolean SzervizMunkalapSzamValidalas() {
        String pattern = "^mu[\\/]{1}(?:19|20)\\d{2}[\\/]{1}[\\d]{4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMunkMunkalap.getText());
        return m.find();
    }

    //Szerviz munkalap vállalási határidő validálás
    public boolean SzervizMunkalapValHataridoValidalas() {
        String pattern = "^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]"
                + "?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]"
                + "|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468]"
                + "[048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29)) ((00|[0-9]|1[0-9]|2[0-3]):([0-9]"
                + "|[0-5][0-9]):([0-9]|[0-5][0-9]))$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMunkHatarido.getText());
        return m.find();
    }

    //Szerviz munkalap hiba jegyzék validálás
    public boolean SzervizMunkalapHibaValidalas() {
        String pattern = "^[A-Za-z\\s\\,\\-ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,100}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txaMunkHibaJegyzek.getText());
        return m.find();
    }

    //Szerviz feladat validálás
    public boolean SzervizFeladatValidalas() {
        String pattern = "^fe[\\/]{1}[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtFeladatFelad.getText());
        return m.find();
    }

    //Szerviz feladat munka díj validálás
    public boolean SzervizFeladatMunkaDijValidalas() {
        String pattern = "^[\\d]{1,9}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtFeladatMDij.getText());
        return m.find();
    }

    //Szerviz feladat elnevezés validálás
    public boolean SzervizFeladatElnevezesValidalas() {
        String pattern = "^[A-Za-z\\s\\,ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txaFeladatElnevezes.getText());
        return m.find();
    }

    //Szerviz szerelés kezdés ideje validálás
    public boolean SzervizSzerelesKezdesIdoValidalas() {
        String pattern = "^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]"
                + "?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]"
                + "|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468]"
                + "[048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29))$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtSzerelesKezdIdo.getText());
        return m.find();
    }

    //Szerviz szerelés befejezés ideje validálás
    public boolean SzervizSzerelesBefejezesIdoValidalas() {
        String pattern = "^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]"
                + "?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]"
                + "|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468]"
                + "[048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29))$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtSzerelesBefIdo.getText());
        return m.find();
    }

    //Szerviz szerelés tényleges munka óra validálás
    public boolean SzervizSzerelesMunkaOraValidalas() {
        String pattern = "^(\\d?[1-9]|[1-9]0)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtSzerelesMOra.getText());
        return m.find();
    }

    //Szerviz alkatrész kód validálás
    public boolean SzervizAlkatreszKodValidalas() {
        String pattern = "^al[\\/]{1}[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAlkatreszKod.getText());
        return m.find();
    }

    //Szerviz alkatrész megnevezése validálás
    public boolean SzervizAlkatreszMegnevezesValidalas() {
        String pattern = "^[A-Za-z\\s+,\\/()ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAlkatreszMegnevezes.getText());
        return m.find();
    }

    //Szerviz beszerzés kód validálás
    public boolean SzervizBeszerzesKodValidalas() {
        String pattern = "^be[\\/]{1}(?:19|20)\\d{2}[\\/]{1}[\\d]{4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtBeszerzesBeszer.getText());
        return m.find();
    }

    //Szerviz beszerzés alkatrész ára validálás
    public boolean SzervizBeszerzesAlkatreszArValidalas() {
        String pattern = "^[\\d]{1,9}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtBeszerzesAlkAr.getText());
        return m.find();
    }

    //Szerviz beszerzés megrendelés ideje validálás
    public boolean SzervizBeszerzesMegrendelesValidalas() {
        String pattern = "^(?:((?:19|20)[0-9]{2})[\\/\\-. ]?(?:(0[1-9]|1[0-2])[\\/\\-. ]"
                + "?([0-2][1-8]|[12]0|09|19)|(0[13-9]|1[0-2])[\\/\\-. ]?(29|30)|(0[13578]"
                + "|1[02])[\\/\\-. ]?(31))|(19(?:[0][48]|[2468][048]|[13579][26])|20(?:[02468]"
                + "[048]|[13579][26]))[\\/\\-. ]?(02)[\\/\\-. ]?(29))$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtBeszerzesMegrend.getText());
        return m.find();
    }

    //Jármű hozzáadása rendszám validálása
    public boolean JarmuHozzRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtRendszam.getText());
        return m.find();
    }

    //Jármű hozzáadása márka validálása
    public boolean JarmuHozzMarkaValidalas() {
        String pattern = "^[a-zA-Z\\s-]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMarka.getText());
        return m.find();
    }

    //Jármű hozzáadása modell validálása
    public boolean JarmuHozzModellValidalas() {
        String pattern = "^[a-zA-Z\\s\\d-+]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModell.getText());
        return m.find();
    }

    //Jármű hozzáadása típus validálása
    public boolean JarmuHozzTipusValidalas() {
        String pattern = "^[a-zA-Z\\s\\d.]{1,10}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtTipus.getText());
        return m.find();
    }

    //Jármű hozzáadása jármű leírása validálás
    public boolean JarmuHozzLeirasValidalas() {
        String pattern = "^[A-Za-z\\s\\d\\,\\.\\!\\-\\(\\)ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,1000}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txaLeiras.getText());
        return m.find();
    }

    //Jármű hozzáadása jármű típushibái validálás
    public boolean JarmuHozzHibaValidalas() {
        String pattern = "^[A-Za-z\\s\\d\\,\\.\\!\\-\\(\\)ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,1000}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txaHibak.getText());
        return m.find();
    }

    //Jármű hozzáadása hengerűrtartalom validálás
    public boolean JarmuHozzHengerValidalas() {
        String pattern = "^[\\d]{2,5}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtHenger.getText());
        return m.find();
    }

    //Jármű hozzáadása kilóméteróra állás validálás
    public boolean JarmuHozzKilometerValidalas() {
        String pattern = "^[\\d]{1,7}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtKilom.getText());
        return m.find();
    }

    //Jármű hozzáadása teljesítmény validálás
    public boolean JarmuHozzTeljesitmenyValidalas() {
        String pattern = "^[\\d]{1,4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtTeljes.getText());
        return m.find();
    }

    //Jármű hozzáadása vételár validálás
    public boolean JarmuHozzArValidalas() {
        String pattern = "^[\\d]{1,8}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtAr.getText());
        return m.find();
    }

    //Jármű hozzáadása saját tömeg validálás
    public boolean JarmuHozzSajatTomegValidalas() {
        String pattern = "^[\\d]{1,4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtSajat.getText());
        return m.find();
    }

    //Jármű módosítás rendszám validálása
    public boolean JarmuModositasRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositRendszam.getText());
        return m.find();
    }

    //Jármű módosítás márka validálása
    public boolean JarmuModositasMarkaValidalas() {
        String pattern = "^[a-zA-Z\\s-]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositMarka.getText());
        return m.find();
    }

    //Jármű módosítás modell validálása
    public boolean JarmuModositasModellValidalas() {
        String pattern = "^[a-zA-Z\\s\\d-+]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositModell.getText());
        return m.find();
    }

    //Jármű módosítás típus validálása
    public boolean JarmuModositasTipusValidalas() {
        String pattern = "^[a-zA-Z\\s\\d.]{1,10}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositTipus.getText());
        return m.find();
    }

    //Jármű módosítás jármű leírása validálás
    public boolean JarmuModositasLeirasValidalas() {
        String pattern = "^[A-Za-z\\s\\d\\,\\.\\!\\-\\(\\)ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,1000}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositLeiras.getText());
        return m.find();
    }

    //Jármű módosítás jármű típushibái validálás
    public boolean JarmuModositasHibaValidalas() {
        String pattern = "^[A-Za-z\\s\\d\\,\\.\\!\\-\\(\\)ÖÜÓŐÚŰÁÉÍ-öüóőúéáűí]{1,1000}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositHiba.getText());
        return m.find();
    }

    //Jármű módosítás hengerűrtartalom validálás
    public boolean JarmuModositasHengerValidalas() {
        String pattern = "^[\\d]{2,5}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositHenger.getText());
        return m.find();
    }

    //Jármű módosítás kilóméteróra állás validálás
    public boolean JarmuModositasKilometerValidalas() {
        String pattern = "^[\\d]{1,7}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositKilometer.getText());
        return m.find();
    }

    //Jármű módosítás teljesítmény validálás
    public boolean JarmuModositasTeljesitmenyValidalas() {
        String pattern = "^[\\d]{1,4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositTeljesitmeny.getText());
        return m.find();
    }

    //Jármű módosítás vételár validálás
    public boolean JarmuModositasArValidalas() {
        String pattern = "^[\\d]{1,8}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositAr.getText());
        return m.find();
    }

    //Jármű módosítás saját tömeg validálás
    public boolean JarmuModositasSajatTomegValidalas() {
        String pattern = "^[\\d]{1,4}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtModositSajat.getText());
        return m.find();
    }

    //Jármű keresés autó rendszám validálás
    public boolean JarmuKeresesAutoRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtSzemelyautoRendszam.getText());
        return m.find();
    }

    //Jármű keresés motor rendszám validálás
    public boolean JarmuKeresesMotorRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtMotorkerekparRendszam.getText());
        return m.find();
    }

    //Jármű keresés kamion rendszám validálás
    public boolean JarmuKeresesKamionRendszamValidalas() {
        String pattern = "^[A-Z]{3}\\-[\\d]{3}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtHaszonjarmuRendszam.getText());
        return m.find();
    }

    //Regisztrációs vezetéknév validálás
    public boolean vezetekNevValidalas() {
        String pattern = "^[A-Za-z]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtVezetek.getText());
        return m.find();
    }

    //Regisztrációs keresztnév validálás
    public boolean keresztNevValidalas() {
        String pattern = "^[A-Za-z]{1,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtKereszt.getText());
        return m.find();
    }

    //Regisztrációs felhasználónév text field validálás
    public boolean regFelhasznaloNevValidalas() {
        String pattern = "^[A-Za-z.]{1,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtRegFelhasznalo.getText());
        return m.find();
    }

    //Regisztrációs jelszó text field validálás
    public boolean regJelszoValidalas() {
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtRegJelszo.getText());
        return m.find();
    }

    //Regisztrációs jelszó ismétlés text field validálás
    public boolean regJelszoUjraValidalas() {
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(txtregJelszoUjra.getText());
        return m.find();
    }

    //Bejelentkezés felhasználónév text field getere
    public String getBejelentkezesFelhasznaloNev() {
        String felhasznaloNev = txtFelhasznalo.getText();
        return felhasznaloNev;
    }

    //Nézet jelszó titkosítása
    public String getNezetJelszo() {
        jelszo = (String.valueOf(pwfJelszo.getPassword()));
        String titkositottJelszo = jelszoTitkosita(jelszo, "XFva~n0i%-ai9px3@#g#MYyp3K "
                + "=HXFcjvg!w(hXA4UTP20e:[7_>dp.5SzpP#I&");
        return titkositottJelszo;
    }

    //nézet jelszó titkósítási metódus
    public String jelszoTitkosita(String passwordToHash, String salt) {
        String nezetTitkositottJelszo = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            nezetTitkositottJelszo = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return nezetTitkositottJelszo;
    }

    //Jármű hozzáadása után szöveges mezők törlése
    public void jarmuHozzaAdasaSzovegesMezokTorlese() {
        txtRendszam.setText("");
        txtMarka.setText("");
        txtModell.setText("");
        txtTipus.setText("");
        txaLeiras.setText("");
        txaHibak.setText("");
        txtHenger.setText("");
        txtKilom.setText("");
        txtTeljes.setText("");
        txtAr.setText("");
        txtSajat.setText("");
    }

    //Jármű módosítása után szöveges mezők törlése
    public void jarmuModositasSzovegesMezokTorlese() {
        txtModositRendszam.setText("");
        txtModositMarka.setText("");
        txtModositModell.setText("");
        txtModositTipus.setText("");
        txtModositLeiras.setText("");
        txtModositHiba.setText("");
        txtModositHenger.setText("");
        txtModositKilometer.setText("");
        txtModositTeljesitmeny.setText("");
        txtModositAr.setText("");
        txtModositSajat.setText("");
    }

    //Jármű törlése után rendszám mező törlése
    public void jarmuModositRendszamSzovegesMezoTorlese() {
        txtModositRendszam.setText("");
    }

    //Szerviz menü szöveges mező adatainak törlése
    public void szervizSzovegesMezokTorlese() {
        //Ügyfél
        txtUgyfelNev.setText("");
        txtUgyfelLakcim.setText("");
        txtUgyfelElerhetoseg.setText("");
        txtUgyfelOkmanyTipus.setText("");
        txtUgyfelOkmanyAzonosito.setText("");
        //Autó
        txtAutoRendszam.setText("");
        txtAutoTipus.setText("");
        txtAutoMSzam.setText("");
        txtAutoASzam.setText("");
        //Munkalap
        txtMunkMunkalap.setText("");
        txtMunkHatarido.setText("");
        txaMunkHibaJegyzek.setText("");
        //Feladat
        txtFeladatFelad.setText("");
        txtFeladatMDij.setText("");
        txaFeladatElnevezes.setText("");
        //Szerelés
        txtSzerelesKezdIdo.setText("");
        txtSzerelesBefIdo.setText("");
        txtSzerelesMOra.setText("");
        //Beszerzés
        txtBeszerzesBeszer.setText("");
        txtBeszerzesAlkAr.setText("");
        txtBeszerzesMegrend.setText("");
    }

    //Ügyfél segédlista a PDF generáláshoz
    public ArrayList<String> ugyfelAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String azon = getUgyfelOkmanyAzonosito();
        String nev = getUgyfelNev();
        String eler = getUgyfelElerhetoseg();
        String lakcim = getUgyfelLakcim();
        lista.add(azon);
        lista.add(nev);
        lista.add(eler);
        lista.add(lakcim);
        return lista;
    }

    //Autó segédlista a PDF generáláshoz
    public ArrayList<String> autoAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String rsz = getAutoRendszam();
        String ugyfel = getAutoUgyfel();
        String marka = getAutoMarka();
        String tipus = getAutoTipus();
        String evjarat = getAutoEvjarat();
        String szin = getAutoSzin();
        String mszam = getAutoMotorszam();
        String aszam = getAutoAlvazszam();
        lista.add(rsz);
        lista.add(ugyfel);
        lista.add(marka);
        lista.add(tipus);
        lista.add(evjarat);
        lista.add(szin);
        lista.add(mszam);
        lista.add(aszam);
        return lista;
    }

    //Munkalap segédlista a PDF generáláshoz
    public ArrayList<String> munkalapAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String munkalapSzam = getUgyfelOkmanyAzonosito();
        String rsz = getMunkalapRendszam();
        String ido = getMunkalapHatarido();
        String hiba = getMunkalapHiba();
        lista.add(munkalapSzam);
        lista.add(rsz);
        lista.add(ido);
        lista.add(hiba);
        return lista;
    }

    //Feladat segédlista a PDF generáláshoz
    public ArrayList<String> feladatAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String feladat = getFeladat();
        String munkaDij = getFeladatMunkaDij();
        String tipus = getFeladatTipus();
        String elnevezes = getFeladatElnevezes();
        lista.add(feladat);
        lista.add(munkaDij);
        lista.add(tipus);
        lista.add(elnevezes);
        return lista;
    }

    //Szerelés segédlista a PDF generáláshoz
    public ArrayList<String> szerelesAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String munkalap = getSzerelesMunkalapSzam();
        String feladat = getSzerelesFeladat();
        String kezdes = getSzerelesKezesIdo();
        String befejezes = getSzerelesBefejezIdo();
        String ora = getSzerelesMunkaora();
        lista.add(munkalap);
        lista.add(feladat);
        lista.add(kezdes);
        lista.add(befejezes);
        lista.add(ora);
        return lista;
    }

    //Beszerzés segédlista a PDF generáláshoz
    public ArrayList<String> beszerzesAdatLista() {
        ArrayList<String> lista = new ArrayList<>();
        String beszerzes = getBeszerzesKod();
        String munkalap = getBeszerzesMunkalap();
        String alkatresz = getBeszerzesAlkatresz();
        String ar = getBeszerzesAlkatreszAr();
        String ido = getBeszerzesMegrendeles();
        lista.add(beszerzes);
        lista.add(munkalap);
        lista.add(alkatresz);
        lista.add(ar);
        lista.add(ido);
        return lista;
    }

    //Kép átmeretez metódus
    public static BufferedImage kepAtmeretez(BufferedImage kep, int szeles, int magas) {
        BufferedImage segedKep
                = new BufferedImage(szeles, magas, BufferedImage.SCALE_SMOOTH);
        int x, y;
        int ww = kep.getWidth();
        int hh = kep.getHeight();
        int[] ys = new int[magas];
        for (y = 0; y < magas; y++) {
            ys[y] = y * hh / magas;
        }
        for (x = 0; x < szeles; x++) {
            int newX = x * ww / szeles;
            for (y = 0; y < magas; y++) {
                int col = kep.getRGB(newX, ys[y]);
                segedKep.setRGB(x, y, col);
            }
        }
        return segedKep;
    }

    //Kép betöltése metódus
    private void kepBetolt() {
        int returnValue = openFile.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                bf = ImageIO.read(openFile.getSelectedFile());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "A kép betöltése sikertelen volt", "Üzenet",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nincs kép kiválasztva!", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Kép mentése metódus
    private void kepMent() {
        int returnValue = saveFile.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(kepAtmeretez(bf, 800, 600), "JPG", saveFile.getSelectedFile());
                JOptionPane.showMessageDialog(this, "A kép sikeresen mentve", "Üzenet",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "A kép mentése sikertelen volt", "Üzenet",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nincs kép kiválasztva!", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Kilowatt átváltása lóerőre
    public void loEroBeallit() {
        int kilowatt = (int) (Integer.parseInt(txtTeljes.getText()) * 1.359621617304);
        String loero = String.valueOf(kilowatt);
        lblLoero.setText(loero);
    }

    //belépés utáni következő ablak
    public void belepes() {
        pnlFomenu.setVisible(true);
        pnlBejelentkezes.setVisible(false);
    }

    //szerviz combobox feltöltése
    public void szervizMarkaFrissit(DefaultComboBoxModel dcbm) {
        cmbAutoMarka.setModel(dcbm);
    }

    //ügyfél combobox feltöltése
    public void ugyfelFrissit(DefaultComboBoxModel dcbm) {
        cmbAutoUgyfel.setModel(dcbm);
    }

    //rendszám combobox feltöltése
    public void rendszamFrissit(DefaultComboBoxModel dcbm) {
        cmbMunkRendszam.setModel(dcbm);
    }

    //munkalap szám combobox feltöltése
    public void munkalapSzamFrissit(DefaultComboBoxModel dcbm) {
        cmbSzerelesMunkalap.setModel(dcbm);
    }

    //szerelés combobox feltöltése
    public void dolgozoFrissit(DefaultComboBoxModel dcbm) {
        cmbSzerelesSzerelo.setModel(dcbm);
    }

    //feldata combobox feltöltése
    public void feladatFrissit(DefaultComboBoxModel dcbm) {
        cmbSzerelesFeladat.setModel(dcbm);
    }

    //beszerzés combobox feltöltése
    public void beszerzesMunkalapSzamFrissit(DefaultComboBoxModel dcbm) {
        cmbBeszerzesMunkalap.setModel(dcbm);
    }

    //alkatrész combobox feltöltése
    public void alkatreszFrissit(DefaultComboBoxModel dcbm) {
        cmbBeszerzesAlkatresz.setModel(dcbm);
    }

    //kivitel combobox feltöltése
    public void jarmuKivitelFrissit(DefaultComboBoxModel dcbm) {
        cmbKivitel.setModel(dcbm);
    }

    //auto keresés márka combobox feltöltése
    public void autoMarkaFrissit(DefaultComboBoxModel dcbm) {
        cmbSzemelyautoMarka.setModel(dcbm);
    }

    //auto keresés üzemanyag combobox feltöltése
    public void autoUzemanyagFrissit(DefaultComboBoxModel dcbm) {
        cmbSzemelyautoUzemanyag.setModel(dcbm);
    }

    //auto keresés kivitel combobox feltöltése
    public void autoKivitelFrissit(DefaultComboBoxModel dcbm) {
        cmbSzemelyautoKivitel.setModel(dcbm);
    }

    //auto keresés állapot combobox feltöltése
    public void autoAllapotFrissit(DefaultComboBoxModel dcbm) {
        cmbSzemelyautoAllapot.setModel(dcbm);
    }

    //motor keresés márka combobox feltöltése
    public void motorMarkaFrissit(DefaultComboBoxModel dcbm) {
        cmbMotorkerekparMarka.setModel(dcbm);
    }

    //motor keresés üzemanyag combobox feltöltése
    public void motorUzemanyagFrissit(DefaultComboBoxModel dcbm) {
        cmbMotorkerekparUzemanyag.setModel(dcbm);
    }

    //motor keresés kivitel combobox feltöltése
    public void motorKivitelFrissit(DefaultComboBoxModel dcbm) {
        cmbMotorkerekparKivitel.setModel(dcbm);
    }

    //motor keresés állapot combobox feltöltése
    public void motorAllapotFrissit(DefaultComboBoxModel dcbm) {
        cmbMotorkerekparAllapot.setModel(dcbm);
    }

    //kamion keresés márka combobox feltöltése
    public void kamionMarkaFrissit(DefaultComboBoxModel dcbm) {
        cmbHaszonjarmuMarka.setModel(dcbm);
    }

    //kamion keresés üzemanyag combobox feltöltése
    public void kamionUzemanyagFrissit(DefaultComboBoxModel dcbm) {
        cmbHaszonjarmuUzemanyag.setModel(dcbm);
    }

    //kamion keresés kivitel combobox feltöltése
    public void kamionKivitelFrissit(DefaultComboBoxModel dcbm) {
        cmbHaszonjarmuKivitel.setModel(dcbm);
    }

    //kamion keresés állapot combobox feltöltése
    public void kamionAllapotFrissit(DefaultComboBoxModel dcbm) {
        cmbHaszonjarmuAllapot.setModel(dcbm);
    }

    //Jármű keresés találati táblázat
    public void setTalalatTablazat(DefaultTableModel dtm) {
        tblTalalat.setModel(dtm);
    }

    //Jármű módosítás táblázat
    public void setJarmuModositTablazat(DefaultTableModel dtm) {
        tblJarmuModosit.setModel(dtm);
    }

    //Ügyfél módosítás táblázat
    public void setUgyfelModositTablazat(DefaultTableModel dtm) {
        tblUgyfel.setModel(dtm);
    }

    //Beszerzés módosítás táblázat
    public void setBeszerzesModositTablazat(DefaultTableModel dtm) {
        tblBeszerzes.setModel(dtm);
    }

    //ügyfél név mező értékének tartalma
    public String getUgyfelNev() {
        String nev = txtUgyfelNev.getText();
        return nev;
    }

    //ügyfél lakcím mező értékének tartalma
    public String getUgyfelLakcim() {
        String lakcim = txtUgyfelLakcim.getText();
        return lakcim;
    }

    //ügyfél elérhetőség mező értékének tartalma
    public String getUgyfelElerhetoseg() {
        String elerhetoseg = txtUgyfelElerhetoseg.getText();
        return elerhetoseg;
    }

    //ügyfél okmány azonosító mező értékének tartalma
    public String getUgyfelOkmanyAzonosito() {
        String azonosito = txtUgyfelOkmanyAzonosito.getText();
        return azonosito;
    }

    //ügyfél okmány típus mező értékének tartalma
    public String getUgyfelOkmanyTipus() {
        String tipus = txtUgyfelOkmanyTipus.getText();
        return tipus;
    }

    //márka kód mező értékének tartalma
    public String getMarkaKod() {
        String kod = txtMarkaMarkKod.getText();
        return kod;
    }

    //márka elnevezés mező értékének tartalma
    public String getMarkaElnevezes() {
        String kod = txtMarkaElnevezes.getText();
        return kod;
    }

    //autó rendszám mező értékének tartalma
    public String getAutoRendszam() {
        String rendszam = txtAutoRendszam.getText();
        return rendszam;
    }

    //autó ügyfél combobox tartalma
    public String getAutoUgyfel() {
        String ugyfel = cmbAutoUgyfel.getSelectedItem().toString();

        return ugyfel;
    }

    //autó márka combobox tartalma
    public String getAutoMarka() {
        String marka = cmbAutoMarka.getSelectedItem().toString();
        return marka;
    }

    //autó évjárat combobox tartalma
    public String getAutoEvjarat() {
        String evjarat = cmbAutoEvjarat.getSelectedItem().toString();
        return evjarat;
    }
//

    //autó típus mező értékének tartalma
    public String getAutoTipus() {
        String tipus = txtAutoTipus.getText();
        return tipus;
    }

    //autó szín combobox tartalma
    public String getAutoSzin() {
        switch (cmbAutoSzin.getSelectedItem().toString()) {
            case "fehér":
                return "01";
            case "sárga":
                return "02";
            case "narancs":
                return "03";
            case "piros":
                return "04";
            case "bíbor / lila":
                return "05";
            case "kék":
                return "06";
            case "zöld":
                return "07";
            case "szürke":
                return "08";
            case "barna":
                return "09";
            default:
                return "10";
        }
    }

    //autó motorszám mező értékének tartalma
    public String getAutoMotorszam() {
        String mszam = txtAutoMSzam.getText();
        return mszam;
    }

    //autó alvázszám mező értékének tartalma
    public String getAutoAlvazszam() {
        String tipus = txtAutoASzam.getText();
        return tipus;
    }

    //munkalap munkalapszám mező értékének tartalma
    public String getMunkalapszam() {
        String munkszam = txtMunkMunkalap.getText();
        return munkszam;
    }

    //munkalap rendszam combobox tartalma
    public String getMunkalapRendszam() {
        String rsz = cmbMunkRendszam.getSelectedItem().toString();
        return rsz;
    }

    //munkalap vállalási határidő mező értékének tartalma
    public String getMunkalapHatarido() {
        String hatarido = txtMunkHatarido.getText();
        return hatarido;
    }

    //munkalap hiba jegyzék mező értékének tartalma
    public String getMunkalapHiba() {
        String hiba = txaMunkHibaJegyzek.getText();
        return hiba;
    }

    //feladat mező értékének tartalma
    public String getFeladat() {
        String feladat = txtFeladatFelad.getText();
        return feladat;
    }

    //feladat munkadij mező értékének tartalma
    public String getFeladatMunkaDij() {
        String munkadij = txtFeladatMDij.getText();
        return munkadij;
    }

    //feladat típus combobox tartalma
    public String getFeladatTipus() {
        switch (cmbFeladatTipus.getSelectedItem().toString()) {
            case "javítás":
                return "j";
            case "alkatrész csere":
                return "c";
            case "felkészítés vizsgára":
                return "f";
            default:
                return "v";
        }
    }

    //feladat elnevezés mező értékének tartalma
    public String getFeladatElnevezes() {
        String elnevezes = txaFeladatElnevezes.getText();
        return elnevezes;
    }

    //szerelés munkalap szám combobox tartalma
    public String getSzerelesMunkalapSzam() {
        String munkalap = cmbSzerelesMunkalap.getSelectedItem().toString();
        return munkalap;
    }

    //szerelés szerelő combobox tartalma
    public String getSzerelesSzerelo() {
        String szerelo = cmbSzerelesSzerelo.getSelectedItem().toString();
        return szerelo;
    }

    //szerelés feladat combobox tartalma
    public String getSzerelesFeladat() {
        String feladat = cmbSzerelesFeladat.getSelectedItem().toString();
        return feladat;
    }

    //szerelés kezdési idő mező értékének tartalma
    public String getSzerelesKezesIdo() {
        String kezdIdo = txtSzerelesKezdIdo.getText();
        return kezdIdo;
    }

    //szerelés befejezési idő mező értékének tartalma
    public String getSzerelesBefejezIdo() {
        String befejezIdo = txtSzerelesBefIdo.getText();
        return befejezIdo;
    }

    //szerelés munkaóra mező értékének tartalma
    public String getSzerelesMunkaora() {
        String munka = txtSzerelesMOra.getText();
        return munka;
    }

    //alkatrész kód mező értékének tartalma
    public String getAlkatreszKod() {
        String kod = txtAlkatreszKod.getText();
        return kod;
    }

    //alkatrész megnevezés mező értékének tartalma
    public String getAlkatreszMegnevezes() {
        String megnevezes = txtAlkatreszMegnevezes.getText();
        return megnevezes;
    }

    //beszerzési kód mező értékének tartalma
    public String getBeszerzesKod() {
        String kod = txtBeszerzesBeszer.getText();
        return kod;
    }

    //beszerzés munkalap szám combobox tartalma
    public String getBeszerzesMunkalap() {
        String munkalap = cmbBeszerzesMunkalap.getSelectedItem().toString();
        return munkalap;
    }

    //beszerzés alkatrész combobox tartalma
    public String getBeszerzesAlkatresz() {
        String alkatresz = cmbBeszerzesAlkatresz.getSelectedItem().toString();
        return alkatresz;
    }

    //beszerzési alkatrész ár mező értékének tartalma
    public String getBeszerzesAlkatreszAr() {
        String ar = txtBeszerzesAlkAr.getText();
        return ar;
    }

    //beszerzés megrendelés ideje mező értékének tartalma
    public String getBeszerzesMegrendeles() {
        String megrendel = txtBeszerzesMegrend.getText();
        return megrendel;
    }

    //jármű hozzáadása rendszám mező értékének tartalma
    public String getJarmuHozzRendszam() {
        String rendszam = txtRendszam.getText();
        return rendszam;
    }

    //jármű hozzáadása márka mező értékének tartalma
    public String getJarmuHozzMarka() {
        String marka = txtMarka.getText();
        return marka;
    }

    //jármű hozzáadása modell mező értékének tartalma
    public String getJarmuHozzModell() {
        String modell = txtModell.getText();
        return modell;
    }

    //jármű hozzáadása tipus mező értékének tartalma
    public String getJarmuHozzTipus() {
        String tipus = txtTipus.getText();
        return tipus;
    }

    //jármű hozzáadása évjárat combobox tartalma
    public String getJarmuHozzEvjarat() {
        String evjarat = cmbEvjarat.getSelectedItem().toString();
        return evjarat;
    }

    //jarmű hozzáadása hónap combobox tartalma
    public String getJarmuHozzHonap() {
        String honap = cmbHonap.getSelectedItem().toString();
        return honap;
    }

    //jármű hozzáadása kivitel combobox tartalma
    public String getJarmuHozzKivitel() {
        String kivitel = cmbKivitel.getSelectedItem().toString();
        return kivitel;
    }

    //jármű hozzáadása állapot combobox tartalma
    public String getJarmuHozzAllapot() {
        String allapot = cmbAllapot.getSelectedItem().toString();
        return allapot;
    }

    //jármű hozzáadása jármű leírása mező értékének tartalma
    public String getJarmuHozzLeiras() {
        String leiras = txaLeiras.getText();
        return leiras;
    }

    //jármű hozzáadása jármű hibák mező értékének tartalma
    public String getJarmuHozzHiba() {
        String hiba = txaHibak.getText();
        return hiba;
    }

    //Telephely chechbox érték
    public String getJarmuTelephely() {
        if (chkTelephely.isSelected()) {
            return "1";
        } else {
            return "0";
        }
    }

    //jármű hozzáadása üzemanyag combobox tartalma
    public String getJarmuHozzUzemanyag() {
        String uzemanyag = cmbUzemanyag.getSelectedItem().toString();
        return uzemanyag;
    }

    //jármű hozzáadása hengerűrtartalom mező értékének tartalma
    public String getJarmuHozzHenger() {
        String henger = txtHenger.getText();
        return henger;
    }

    //jármű hozzáadása kilóméter óra mező értékének tartalma
    public String getJarmuHozzKilometer() {
        String kilometer = txtKilom.getText();
        return kilometer;
    }

    //jármű hozzáadása teljesítmény mező értékének tartalma
    public String getJarmuHozzTeljesitmeny() {
        String teljes = txtTeljes.getText();
        return teljes;
    }

    //jármű hozzáadása szín combobox tartalma
    public String getJarmuHozzSzin() {
        switch (cmbSzin.getSelectedItem().toString()) {
            case "fehér":
                return "01";
            case "sárga":
                return "02";
            case "narancs":
                return "03";
            case "piros":
                return "04";
            case "bíbor / lila":
                return "05";
            case "kék":
                return "06";
            case "zöld":
                return "07";
            case "szürke":
                return "08";
            case "barna":
                return "09";
            default:
                return "10";
        }
    }

    //jármű hozzáadása sebbeség váltó combobox tartalma
    public String getJarmuHozzSebesseg() {
        switch (cmbSebess.getSelectedItem().toString()) {
            case "mechanikus":
                return "0";
            case "félautomata":
                return "1";
            case "automata":
                return "2";
            default:
                return "3";
        }
    }

    //jármű hozzáadása ár mező értékének tartalma
    public String getJarmuHozzAr() {
        String ar = txtAr.getText();
        return ar;
    }

    //jármű módosítása saját tömeg mező értékének tartalma
    public String getJarmuHozzSajatTomeg() {
        String sajat = txtSajat.getText();
        return sajat;
    }

    //jármű módosítása rendszám mező értékének tartalma
    public String getJarmuModositRendszam() {
        String rendszam = txtModositRendszam.getText();
        return rendszam;
    }

    //jármű módosítása márka mező értékének tartalma
    public String getJarmuModositMarka() {
        String marka = txtModositMarka.getText();
        return marka;
    }

    //jármű módosítása modell mező értékének tartalma
    public String getJarmuModositModell() {
        String modell = txtModositModell.getText();
        return modell;
    }

    //jármű módosítása tipus mező értékének tartalma
    public String getJarmuModositTipus() {
        String tipus = txtModositTipus.getText();
        return tipus;
    }

    //jármű módosítása évjárat combobox tartalma
    public String getJarmuModositEvjarat() {
        String evjarat = cmbModositEvjarat.getSelectedItem().toString();
        return evjarat;
    }

    //jarmű módosítása hónap combobox tartalma
    public String getJarmuModositHonap() {
        String honap = cmbModositHonap.getSelectedItem().toString();
        return honap;
    }

    //jármű módosítása kivitel combobox tartalma
    public String getJarmuModositKivitel() {
        String kivitel = cmbModositKivitel.getSelectedItem().toString();
        return kivitel;
    }

    //jármű módosítása állapot combobox tartalma
    public String getJarmuModositAllapot() {
        String allapot = cmbModositAllapot.getSelectedItem().toString();
        return allapot;
    }

    //jármű módosítása jármű leírása mező értékének tartalma
    public String getJarmuModositLeiras() {
        String leiras = txtModositLeiras.getText();
        return leiras;
    }

    //jármű módosítása jármű hibák mező értékének tartalma
    public String getJarmuModositHiba() {
        String hiba = txtModositHiba.getText();
        return hiba;
    }

    //jármű módosítása checkbox mező
    public String getJarmuModositTelephely() {
        if (chkModositTelephely.isSelected()) {
            return "1";
        } else {
            return "0";
        }
    }

    //jármű módosítása üzemanyag combobox tartalma
    public String getJarmuModositUzemanyag() {
        String uzemanyag = cmbModositUzemanyag.getSelectedItem().toString();
        return uzemanyag;
    }

    //jármű módosítása hengerűrtartalom mező értékének tartalma
    public String getJarmuModositHenger() {
        String henger = txtModositHenger.getText();
        return henger;
    }

    //jármű módosítása kilóméter óra mező értékének tartalma
    public String getJarmuModositKilometer() {
        String kilometer = txtModositKilometer.getText();
        return kilometer;
    }

    //jármű módosítása teljesítmény mező értékének tartalma
    public String getJarmuModositTeljesitmeny() {
        String teljes = txtModositTeljesitmeny.getText();
        return teljes;
    }

    //jármű módosítása szín combobox tartalma
    public String getJarmuModositSzin() {
        switch (cmbModositSzin.getSelectedItem().toString()) {
            case "fehér":
                return "01";
            case "sárga":
                return "02";
            case "narancs":
                return "03";
            case "piros":
                return "04";
            case "bíbor / lila":
                return "05";
            case "kék":
                return "06";
            case "zöld":
                return "07";
            case "szürke":
                return "08";
            case "barna":
                return "09";
            default:
                return "10";
        }
    }

    //jármű módosítása sebbeség váltó combobox tartalma
    public String getJarmuModositSebesseg() {
        switch (cmbModositSebess.getSelectedItem().toString()) {
            case "mechanikus":
                return "0";
            case "félautomata":
                return "1";
            case "automata":
                return "2";
            default:
                return "3";
        }
    }

    //jármű módosítása ár mező értékének tartalma
    public String getJarmuModositAr() {
        String ar = txtModositAr.getText();
        return ar;
    }

    //jármű módosítása saját tömeg mező értékének tartalma
    public String getJarmuModositSajatTomeg() {
        String sajat = txtModositSajat.getText();
        return sajat;
    }

    //Keresés személyautó rendszám mező értékének tartalma
    public String getAutoKeresRendszam() {
        String rsz = txtSzemelyautoRendszam.getText();
        return rsz;
    }

    //Keresés személyautó márka combobox tartalma
    public String getAutoKeresMarka() {
        String marka = cmbSzemelyautoMarka.getSelectedItem().toString();
        return marka;
    }

    //Keresés személyautó márka combobox tartalma
    public String getAutoKeresModell() {
        String modell = cmbSzemelyautoModell.getSelectedItem().toString();
        return modell;
    }

    //Keresés személyautó kivitel combobox tartalma
    public String getAutoKeresKivitel() {
        String kivitel = cmbSzemelyautoKivitel.getSelectedItem().toString();
        return kivitel;
    }

    //Keresés személyautó állapot combobox tartalma
    public String getAutoKeresAllapot() {
        String allapot = cmbSzemelyautoAllapot.getSelectedItem().toString();
        return allapot;
    }

    //Keresés személyautó üzemanyag combobox tartalma
    public String getAutoKeresUzemanyag() {
        String uzemanyag = cmbSzemelyautoUzemanyag.getSelectedItem().toString();
        return uzemanyag;
    }

    //Keresés személyautó évjárat-tól combobox tartalma
    public String getAutoKeresEvjaratTol() {
        String evTol = cmbSzemelyautoEvjaratTol.getSelectedItem().toString();
        return evTol;
    }

    //Keresés személyautó hengerűrtartalom-tól mező értékének tartalma
    public String getAutoKeresHengerTol() {
        String hengerTol = txtSzemelyautoHengerTol.getText();
        return hengerTol;
    }

    //Keresés személyautó kimométeróra állás-tól mező értékének tartalma
    public String getAutoKeresKilometerTol() {
        String kilometerTol = txtSzemelyautoKilometerTol.getText();
        return kilometerTol;
    }

    //Keresés személyautó vételár-tól mező értékének tartalma
    public int getAutoKeresVetelarTol() {
        int arTol = Integer.parseInt(txtSzemelyautoVetalarTol.getText());
        return arTol;
    }

    //felhasznaló vezeték név mező értékének tartalma
    public String getVezeteknev() {
        String vezetekNev = txtVezetek.getText();
        return vezetekNev;
    }

    //felhasználó kereszt név mező értékének tartalma
    public String getKeresztnev() {
        String keresztNev = txtKereszt.getText();
        return keresztNev;
    }

    //felhasználó felhasználónév mező értékének tartalma
    public String getFelhasznaloNev() {
        String felhasznaloNev = txtRegFelhasznalo.getText();
        return felhasznaloNev;
    }

    //felhasználó jelszó mező értékének tartalma
    public String getJelszo() {
        String jsz = txtRegJelszo.getText();
        return jsz;
    }

    //Ügyfél módosítás név beállít
    public void setUgyfelModositNev(String nev) {
        txtUgyfelModositNev.setText(nev);
    }

    //Ügyfél módosítás lakcím beállít
    public void setUgyfelModositLakcim(String lakcim) {
        txtUgyfelModositLakcim.setText(lakcim);
    }

    //Ügyfél módosítás elérhetőség beállít
    public void setUgyfelModositElerhetoseg(String tel) {
        txtUgyfelModositElerhetoseg.setText(tel);
    }

    //Ügyfél módosítás okmány típus beállít
    public void setUgyfelModositOkmanyTipus(String tipus) {
        txtUgyfelModositOkmanyTipus.setText(tipus);
    }

    //Ügyfél módosítás okmány azonosító beállít
    public void setUgyfelModositOkmanyAzonosito(String azon) {
        txtUgyfelModositOkmanyAzonosito.setText(azon);
    }

    //Beszerzés alkatrész combobox tartalma
    public String getCmbBeszerzesModositAlkatresz() {
        String alkatresz = cmbBeszerzesModositAlkatresz.getSelectedItem().toString();
        return alkatresz;
    }

    ////Beszerzés munkalap combobox tartalma
    public String getCmbBeszerzesModositMunkalap() {
        String munkalap = cmbBeszerzesModositMunkalap.getSelectedItem().toString();
        return munkalap;
    }

    //Beszerzés módosítás alkatrész ár szöveges mező taralma
    public String getTxtBeszerzesModositAlkAr() {
        String ar = txtBeszerzesModositAlkAr.getText();
        return ar;
    }

    //Beszerzés módosítás beszerzési kód szöveges mező taralma
    public String getTxtBeszerzesModositBeszer() {
        String kod = txtBeszerzesModositBeszer.getText();
        return kod;
    }

    //Beszerzés módosítás megrendelési idő szöveges mező taralma
    public String getTxtBeszerzesModositMegrend() {
        String ido = txtBeszerzesModositMegrend.getText();
        return ido;
    }

    //Ügyfél módosít elérhetőség szöveges mező tartalma
    public String getTxtUgyfelModositElerhetoseg() {
        String tel = txtUgyfelModositElerhetoseg.getText();
        return tel;
    }

    //Ügyfél módosít lakcím szöveges mező tartalma
    public String getTxtUgyfelModositLakcim() {
        String lakcim = txtUgyfelModositLakcim.getText();
        return lakcim;
    }

    //Ügyfél módosít név szöveges mező tartalma
    public String getTxtUgyfelModositNev() {
        String nev = txtUgyfelModositNev.getText();
        return nev;
    }

    //Ügyfél módosít okmány azonosító szöveges mező tartalma
    public String getTxtUgyfelModositOkmanyAzonosito() {
        String azon = txtUgyfelModositOkmanyAzonosito.getText();
        return azon;
    }

    //Ügyfél módosít okmány típus szöveges mező tartalma
    public String getTxtUgyfelModositOkmanyTipus() {
        String tipus = txtUgyfelModositOkmanyTipus.getText();
        return tipus;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNezet = new javax.swing.JPanel();
        pnlBejelentkezes = new javax.swing.JPanel();
        lblFelhasznalo = new javax.swing.JLabel();
        lblJelszo = new javax.swing.JLabel();
        txtFelhasznalo = new javax.swing.JTextField();
        pwfJelszo = new javax.swing.JPasswordField();
        btnBelepes = new javax.swing.JButton();
        pnlSzerviz = new javax.swing.JPanel();
        tabSzervizValaszto = new javax.swing.JTabbedPane();
        pnlUgyfel = new javax.swing.JPanel();
        lblNev = new javax.swing.JLabel();
        txtUgyfelNev = new javax.swing.JTextField();
        lblLakcim = new javax.swing.JLabel();
        txtUgyfelLakcim = new javax.swing.JTextField();
        lblEler = new javax.swing.JLabel();
        txtUgyfelElerhetoseg = new javax.swing.JTextField();
        lblOkmanyAzonosito = new javax.swing.JLabel();
        txtUgyfelOkmanyAzonosito = new javax.swing.JTextField();
        lblKedvez = new javax.swing.JLabel();
        btnUgyfelFelvitel = new javax.swing.JButton();
        cmbUgyfelKedvezmeny = new javax.swing.JComboBox<>();
        lblOkmanyTipus = new javax.swing.JLabel();
        txtUgyfelOkmanyTipus = new javax.swing.JTextField();
        pnlMarka = new javax.swing.JPanel();
        lblMarkaMarkKod = new javax.swing.JLabel();
        txtMarkaElnevezes = new javax.swing.JTextField();
        lblMarkaElnevezes = new javax.swing.JLabel();
        txtMarkaMarkKod = new javax.swing.JTextField();
        btnMarkaFelvitel = new javax.swing.JButton();
        pnlAuto = new javax.swing.JPanel();
        lblAutoRendszam = new javax.swing.JLabel();
        lblAutoUgyfSzam = new javax.swing.JLabel();
        lblAutoMarka = new javax.swing.JLabel();
        lblAutoEvjarat = new javax.swing.JLabel();
        lblAutoTipus = new javax.swing.JLabel();
        lblAutoMSzam = new javax.swing.JLabel();
        lblAutoASzam = new javax.swing.JLabel();
        lblAutoSzin = new javax.swing.JLabel();
        txtAutoTipus = new javax.swing.JTextField();
        txtAutoMSzam = new javax.swing.JTextField();
        txtAutoASzam = new javax.swing.JTextField();
        txtAutoRendszam = new javax.swing.JTextField();
        cmbAutoUgyfel = new javax.swing.JComboBox<>();
        cmbAutoMarka = new javax.swing.JComboBox<>();
        cmbAutoEvjarat = new javax.swing.JComboBox<>();
        cmbAutoSzin = new javax.swing.JComboBox<>();
        btnAutoFelvitel = new javax.swing.JButton();
        lblAutoMarkaMegjelenit = new javax.swing.JLabel();
        lblAutoUgyfelMegjelenit = new javax.swing.JLabel();
        pnlMunkalap = new javax.swing.JPanel();
        lblMunkHatarido = new javax.swing.JLabel();
        lblMunkMunkalap = new javax.swing.JLabel();
        lblMunkRendszam = new javax.swing.JLabel();
        lblMunkHiba = new javax.swing.JLabel();
        txtMunkMunkalap = new javax.swing.JTextField();
        txtMunkHatarido = new javax.swing.JTextField();
        cmbMunkRendszam = new javax.swing.JComboBox<>();
        scrHibaJegyzek = new javax.swing.JScrollPane();
        txaMunkHibaJegyzek = new javax.swing.JTextArea();
        btnMunkFelvitel = new javax.swing.JButton();
        pnlFeladat = new javax.swing.JPanel();
        lblFeladatFelad = new javax.swing.JLabel();
        txtFeladatMDij = new javax.swing.JTextField();
        btnFeladatFelvitel = new javax.swing.JButton();
        lblFeladatMDij = new javax.swing.JLabel();
        txtFeladatFelad = new javax.swing.JTextField();
        lblFeladatTipus = new javax.swing.JLabel();
        cmbFeladatTipus = new javax.swing.JComboBox<>();
        lblFeladatElnevezes = new javax.swing.JLabel();
        scrFeladatGorg = new javax.swing.JScrollPane();
        txaFeladatElnevezes = new javax.swing.JTextArea();
        pnlSzereles = new javax.swing.JPanel();
        lblSzerelesSzerelo = new javax.swing.JLabel();
        lblSzerelesKezdIdo = new javax.swing.JLabel();
        lblSzerelesBefIdo = new javax.swing.JLabel();
        lblSzerelesMOra = new javax.swing.JLabel();
        lblSzerelesMunkalap = new javax.swing.JLabel();
        lblSzerelesFeladat = new javax.swing.JLabel();
        cmbSzerelesMunkalap = new javax.swing.JComboBox<>();
        cmbSzerelesFeladat = new javax.swing.JComboBox<>();
        txtSzerelesMOra = new javax.swing.JTextField();
        txtSzerelesKezdIdo = new javax.swing.JTextField();
        txtSzerelesBefIdo = new javax.swing.JTextField();
        btnSzerelesFelvitel = new javax.swing.JButton();
        cmbSzerelesSzerelo = new javax.swing.JComboBox<>();
        lblSzerelesFeladatMegnevezes = new javax.swing.JLabel();
        lblSzerelesSzereloNev = new javax.swing.JLabel();
        pnlAlkatresz = new javax.swing.JPanel();
        lblAlkatreszKod = new javax.swing.JLabel();
        txtAlkatreszKod = new javax.swing.JTextField();
        lblAlkatreszMegnevezes = new javax.swing.JLabel();
        txtAlkatreszMegnevezes = new javax.swing.JTextField();
        btnAlkatreszFelvitel = new javax.swing.JButton();
        pnlBeszerzes = new javax.swing.JPanel();
        lblBeszerzesBeszer = new javax.swing.JLabel();
        lblBeszerzesMunkalap = new javax.swing.JLabel();
        txtBeszerzesBeszer = new javax.swing.JTextField();
        lblBeszerzesAlkAr = new javax.swing.JLabel();
        cmbBeszerzesMunkalap = new javax.swing.JComboBox<>();
        txtBeszerzesAlkAr = new javax.swing.JTextField();
        lblBeszerzesMegrend = new javax.swing.JLabel();
        txtBeszerzesMegrend = new javax.swing.JTextField();
        btnMunkalapKeszit = new javax.swing.JButton();
        btnBeszerzesFelvitel = new javax.swing.JButton();
        lblBeszerzesAlkatresz = new javax.swing.JLabel();
        cmbBeszerzesAlkatresz = new javax.swing.JComboBox<>();
        lblBeszerzesAlkatreszMegnevezes = new javax.swing.JLabel();
        btnSzervizFomenu = new javax.swing.JButton();
        pnlJarmuHozzaAdasa = new javax.swing.JPanel();
        tabJarmuHozzValaszto = new javax.swing.JTabbedPane();
        pnlJarmuHozzElso = new javax.swing.JPanel();
        lblRendszam = new javax.swing.JLabel();
        txtRendszam = new javax.swing.JTextField();
        lblMarka = new javax.swing.JLabel();
        lblModell = new javax.swing.JLabel();
        lblTipus = new javax.swing.JLabel();
        txtTipus = new javax.swing.JTextField();
        lblEvjarat = new javax.swing.JLabel();
        cmbEvjarat = new javax.swing.JComboBox<>();
        lblHonap = new javax.swing.JLabel();
        cmbHonap = new javax.swing.JComboBox<>();
        lblKivitel = new javax.swing.JLabel();
        cmbKivitel = new javax.swing.JComboBox<>();
        lblAllapot = new javax.swing.JLabel();
        cmbAllapot = new javax.swing.JComboBox<>();
        lblLeiras = new javax.swing.JLabel();
        scrLeiras = new javax.swing.JScrollPane();
        txaLeiras = new javax.swing.JTextArea();
        lblJarmuTipusa = new javax.swing.JLabel();
        cmbJarmuTipusa = new javax.swing.JComboBox<>();
        lblHiba = new javax.swing.JLabel();
        scrHibak = new javax.swing.JScrollPane();
        txaHibak = new javax.swing.JTextArea();
        txtModell = new javax.swing.JTextField();
        txtMarka = new javax.swing.JTextField();
        chkTelephely = new javax.swing.JCheckBox();
        pnlJarmuHozzMasodik = new javax.swing.JPanel();
        lblUzemanyag = new javax.swing.JLabel();
        cmbUzemanyag = new javax.swing.JComboBox<>();
        lblHenger = new javax.swing.JLabel();
        txtHenger = new javax.swing.JTextField();
        lblKilom = new javax.swing.JLabel();
        txtKilom = new javax.swing.JTextField();
        lblSajat = new javax.swing.JLabel();
        txtSajat = new javax.swing.JTextField();
        lblTeljes = new javax.swing.JLabel();
        txtTeljes = new javax.swing.JTextField();
        lblSzin = new javax.swing.JLabel();
        cmbSzin = new javax.swing.JComboBox<>();
        lblSebess = new javax.swing.JLabel();
        cmbSebess = new javax.swing.JComboBox<>();
        lblAr = new javax.swing.JLabel();
        txtAr = new javax.swing.JTextField();
        lblHengerEgyseg = new javax.swing.JLabel();
        lblKilometerEgyseg = new javax.swing.JLabel();
        lblSajatEgyseg = new javax.swing.JLabel();
        lblTeljesEgyseg = new javax.swing.JLabel();
        lblLoeroEgyseg = new javax.swing.JLabel();
        lblLoero = new javax.swing.JLabel();
        lblArEgyseg = new javax.swing.JLabel();
        pnlJarmuvekMegjelenites = new javax.swing.JPanel();
        scrTablazat = new javax.swing.JScrollPane();
        tblJarmuModosit = new javax.swing.JTable();
        cmbJarmuTipusaModosit = new javax.swing.JComboBox<>();
        pnlJarmuModositAdatok = new javax.swing.JPanel();
        tabJarmuModositAdatok = new javax.swing.JTabbedPane();
        pnlJarmuModositAdatokElso = new javax.swing.JPanel();
        lblModositRendszam = new javax.swing.JLabel();
        txtModositRendszam = new javax.swing.JTextField();
        lblModositMarka = new javax.swing.JLabel();
        txtModositMarka = new javax.swing.JTextField();
        lblModositModell = new javax.swing.JLabel();
        txtModositModell = new javax.swing.JTextField();
        lblModositTipus = new javax.swing.JLabel();
        txtModositTipus = new javax.swing.JTextField();
        lblModositEvjarat = new javax.swing.JLabel();
        lblModositHonap = new javax.swing.JLabel();
        lblModositKivitel = new javax.swing.JLabel();
        lblModositAllapot = new javax.swing.JLabel();
        cmbModositEvjarat = new javax.swing.JComboBox<>();
        cmbModositHonap = new javax.swing.JComboBox<>();
        cmbModositKivitel = new javax.swing.JComboBox<>();
        cmbModositAllapot = new javax.swing.JComboBox<>();
        pnlJarmuModositAdatokMasodik = new javax.swing.JPanel();
        lblModositUzemanyag = new javax.swing.JLabel();
        lblModositHenger = new javax.swing.JLabel();
        lblModositKilom = new javax.swing.JLabel();
        txtModositHenger = new javax.swing.JTextField();
        txtModositKilometer = new javax.swing.JTextField();
        cmbModositUzemanyag = new javax.swing.JComboBox<>();
        lblModositSajat = new javax.swing.JLabel();
        txtModositSajat = new javax.swing.JTextField();
        lblModositTeljes = new javax.swing.JLabel();
        txtModositTeljesitmeny = new javax.swing.JTextField();
        pnlJarmuModositAdatokHarmadik = new javax.swing.JPanel();
        lblModositSzin = new javax.swing.JLabel();
        lblModositSebess = new javax.swing.JLabel();
        lblModositAr = new javax.swing.JLabel();
        txtModositAr = new javax.swing.JTextField();
        cmbModositSzin = new javax.swing.JComboBox<>();
        cmbModositSebess = new javax.swing.JComboBox<>();
        lblModositLeiras = new javax.swing.JLabel();
        txtModositLeiras = new javax.swing.JTextField();
        lblModositHiba = new javax.swing.JLabel();
        txtModositHiba = new javax.swing.JTextField();
        chkModositTelephely = new javax.swing.JCheckBox();
        btnJarmuHozzaAdasa = new javax.swing.JButton();
        btnKep = new javax.swing.JButton();
        btnJarmuHozFomenu = new javax.swing.JButton();
        btnJarmuModositasa = new javax.swing.JButton();
        btnJarmuTorlese = new javax.swing.JButton();
        pnlFomenu = new javax.swing.JPanel();
        btnJarmuHozz = new javax.swing.JButton();
        btnJarmuvek = new javax.swing.JButton();
        btnSzerviz = new javax.swing.JButton();
        btnKilepes = new javax.swing.JButton();
        lblIdoKijelzes = new javax.swing.JLabel();
        lblIdo = new javax.swing.JLabel();
        btnFelhasznalo = new javax.swing.JButton();
        btnSzervizModosit = new javax.swing.JButton();
        pnlJarmuKereses = new javax.swing.JPanel();
        tapJarmuValaszto = new javax.swing.JTabbedPane();
        pnlSzemelyauto = new javax.swing.JPanel();
        lblSzemelyautoMarka = new javax.swing.JLabel();
        cmbSzemelyautoMarka = new javax.swing.JComboBox<>();
        lblSzemelyautoModell = new javax.swing.JLabel();
        cmbSzemelyautoModell = new javax.swing.JComboBox<>();
        lblSzemelyautoEvjarat = new javax.swing.JLabel();
        cmbSzemelyautoEvjaratTol = new javax.swing.JComboBox<>();
        cmbSzemelyautoEvjaratIg = new javax.swing.JComboBox<>();
        lblSzemelyautoKivitel = new javax.swing.JLabel();
        cmbSzemelyautoKivitel = new javax.swing.JComboBox<>();
        lblSzemelyautoVetelar = new javax.swing.JLabel();
        txtSzemelyautoVetalarTol = new javax.swing.JTextField();
        txtSzemelyautoVetelarIg = new javax.swing.JTextField();
        lblSzemelyautoUzemanyag = new javax.swing.JLabel();
        cmbSzemelyautoUzemanyag = new javax.swing.JComboBox<>();
        lblSzemelyautoKilometer = new javax.swing.JLabel();
        txtSzemelyautoKilometerIg = new javax.swing.JTextField();
        txtSzemelyautoHengerTol = new javax.swing.JTextField();
        lblSzemelyautoHenger = new javax.swing.JLabel();
        txtSzemelyautoKilometerTol = new javax.swing.JTextField();
        txtSzemelyautoHengerIg = new javax.swing.JTextField();
        lblSzemelyautoRendszam = new javax.swing.JLabel();
        txtSzemelyautoRendszam = new javax.swing.JTextField();
        lblSzemelyautoAllapot = new javax.swing.JLabel();
        cmbSzemelyautoAllapot = new javax.swing.JComboBox<>();
        btnAutoKereses = new javax.swing.JButton();
        pnlMotorkerekpar = new javax.swing.JPanel();
        lblMotorkerekparMarka = new javax.swing.JLabel();
        cmbMotorkerekparMarka = new javax.swing.JComboBox<>();
        lblMotorkerekparModell = new javax.swing.JLabel();
        cmbMotorkerekparModell = new javax.swing.JComboBox<>();
        lblMotorkerekparEvjarat = new javax.swing.JLabel();
        cmbMotorkerekparEvjaratTol = new javax.swing.JComboBox<>();
        cmbMotorkerekparEvjaratIg = new javax.swing.JComboBox<>();
        lblMotorkerekparKivitel = new javax.swing.JLabel();
        cmbMotorkerekparKivitel = new javax.swing.JComboBox<>();
        lblMotorkerekparVetelar = new javax.swing.JLabel();
        txtMotorkerekparVetalarTol = new javax.swing.JTextField();
        txtMotorkerekparVetelarIg = new javax.swing.JTextField();
        lblMotorkerekparUzemanyag = new javax.swing.JLabel();
        cmbMotorkerekparUzemanyag = new javax.swing.JComboBox<>();
        lblMotorkerekparKilometer = new javax.swing.JLabel();
        txtMotorkerekparKilometerIg = new javax.swing.JTextField();
        txtMotorkerekparHengerTol = new javax.swing.JTextField();
        lblMotorkerekparHenger = new javax.swing.JLabel();
        txtMotorkerekparKilometerTol = new javax.swing.JTextField();
        txtMotorkerekparHengerIg = new javax.swing.JTextField();
        lblMotorkerekparRendszam = new javax.swing.JLabel();
        txtMotorkerekparRendszam = new javax.swing.JTextField();
        lblMotorkerekparAllapot = new javax.swing.JLabel();
        cmbMotorkerekparAllapot = new javax.swing.JComboBox<>();
        btnMotorKereses = new javax.swing.JButton();
        pnlHaszonjarmu = new javax.swing.JPanel();
        lblHaszonjarmuMarka = new javax.swing.JLabel();
        cmbHaszonjarmuMarka = new javax.swing.JComboBox<>();
        lblHaszonjarmuModell = new javax.swing.JLabel();
        cmbHaszonjarmuModell = new javax.swing.JComboBox<>();
        lblHaszonjarmuEvjarat = new javax.swing.JLabel();
        cmbHaszonjarmuEvjaratTol = new javax.swing.JComboBox<>();
        cmbHaszonjarmuEvjaratIg = new javax.swing.JComboBox<>();
        lblHaszonjarmuKivitel = new javax.swing.JLabel();
        cmbHaszonjarmuKivitel = new javax.swing.JComboBox<>();
        lblHaszonjarmuVetelar = new javax.swing.JLabel();
        txtHaszonjarmuVetalarTol = new javax.swing.JTextField();
        txtHaszonjarmuVetelarIg = new javax.swing.JTextField();
        lblHaszonjarmuUzemanyag = new javax.swing.JLabel();
        cmbHaszonjarmuUzemanyag = new javax.swing.JComboBox<>();
        lblHaszonjarmuKilometer = new javax.swing.JLabel();
        txtHaszonjarmuKilometerIg = new javax.swing.JTextField();
        txtHaszonjarmuHengerTol = new javax.swing.JTextField();
        lblHaszonjarmuHenger = new javax.swing.JLabel();
        txtHaszonjarmuKilometerTol = new javax.swing.JTextField();
        txtHaszonjarmuHengerIg = new javax.swing.JTextField();
        lblHaszonjarmuRendszam = new javax.swing.JLabel();
        txtHaszonjarmuRendszam = new javax.swing.JTextField();
        lblHaszonjarmuAllapot = new javax.swing.JLabel();
        cmbHaszonjarmuAllapot = new javax.swing.JComboBox<>();
        btnKamionKereses = new javax.swing.JButton();
        btnJarmuvekFomenu = new javax.swing.JButton();
        pnlFelhasznalo = new javax.swing.JPanel();
        lblVezeteknev = new javax.swing.JLabel();
        lblKeresztnev = new javax.swing.JLabel();
        lblRegFelhasznalo = new javax.swing.JLabel();
        lblRegJelszo = new javax.swing.JLabel();
        lblregJelszoUjra = new javax.swing.JLabel();
        txtVezetek = new javax.swing.JTextField();
        txtKereszt = new javax.swing.JTextField();
        txtRegFelhasznalo = new javax.swing.JTextField();
        txtRegJelszo = new javax.swing.JTextField();
        txtregJelszoUjra = new javax.swing.JTextField();
        btnRegFomenu = new javax.swing.JButton();
        btnRegFelvitel = new javax.swing.JButton();
        pnlSzervizModositas = new javax.swing.JPanel();
        tabSzervizModositValaszto = new javax.swing.JTabbedPane();
        pnlUgyfelModosit = new javax.swing.JPanel();
        lblUgyfelModositNev = new javax.swing.JLabel();
        txtUgyfelModositNev = new javax.swing.JTextField();
        lblUgyfelModositLakcim = new javax.swing.JLabel();
        txtUgyfelModositLakcim = new javax.swing.JTextField();
        lblUgyfelModositEler = new javax.swing.JLabel();
        txtUgyfelModositElerhetoseg = new javax.swing.JTextField();
        lblUgyfelModositOkmanyTipus = new javax.swing.JLabel();
        txtUgyfelModositOkmanyTipus = new javax.swing.JTextField();
        lblUgyfelModositOkmanyAzonosito = new javax.swing.JLabel();
        txtUgyfelModositOkmanyAzonosito = new javax.swing.JTextField();
        btnUgyfelModosit = new javax.swing.JButton();
        scrUgyfel = new javax.swing.JScrollPane();
        tblUgyfel = new javax.swing.JTable();
        pnlBeszerzesModosit = new javax.swing.JPanel();
        lblBeszerzesModositBeszer = new javax.swing.JLabel();
        txtBeszerzesModositBeszer = new javax.swing.JTextField();
        lblBeszerzesModositMunkalap = new javax.swing.JLabel();
        lblBeszerzesModositAlkatresz = new javax.swing.JLabel();
        lblBeszerzesModositAlkAr = new javax.swing.JLabel();
        txtBeszerzesModositAlkAr = new javax.swing.JTextField();
        lblBeszerzesModositMegrend = new javax.swing.JLabel();
        txtBeszerzesModositMegrend = new javax.swing.JTextField();
        cmbBeszerzesModositMunkalap = new javax.swing.JComboBox<>();
        cmbBeszerzesModositAlkatresz = new javax.swing.JComboBox<>();
        btnBeszerzesModosit = new javax.swing.JButton();
        scrBeszerzes = new javax.swing.JScrollPane();
        tblBeszerzes = new javax.swing.JTable();
        btnSzervizModositFomenu = new javax.swing.JButton();
        pnlTalalat = new javax.swing.JPanel();
        btnTalalatFomenu = new javax.swing.JButton();
        pnlKeresett = new javax.swing.JPanel();
        scrTalalat = new javax.swing.JScrollPane();
        tblTalalat = new javax.swing.JTable();
        btnUjJarmuKereses = new javax.swing.JButton();
        pnlKep = new javax.swing.JPanel();
        btnBetolt = new javax.swing.JButton();
        btnMent = new javax.swing.JButton();
        btnVissza = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Járműkezelő");
        setBackground(new java.awt.Color(64, 137, 186));
        setLocation(new java.awt.Point(50, 50));
        setLocationByPlatform(true);
        setResizable(false);

        pnlNezet.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        pnlNezet.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlNezet.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlNezet.setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlBejelentkezes.setBackground(new java.awt.Color(64, 137, 186));

        lblFelhasznalo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblFelhasznalo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFelhasznalo.setIcon(felhasznaloKep);

        lblJelszo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblJelszo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblJelszo.setIcon(jelszoKep);

        txtFelhasznalo.setBackground(new java.awt.Color(255, 255, 255));
        txtFelhasznalo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtFelhasznalo.setForeground(new java.awt.Color(57, 57, 57));
        txtFelhasznalo.setText("Felhasználónév");
        txtFelhasznalo.setToolTipText("");
        txtFelhasznalo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFelhasznaloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFelhasznaloFocusLost(evt);
            }
        });

        pwfJelszo.setBackground(new java.awt.Color(255, 255, 255));
        pwfJelszo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        pwfJelszo.setForeground(new java.awt.Color(57, 57, 57));
        pwfJelszo.setText("00000000");
        pwfJelszo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwfJelszoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwfJelszoFocusLost(evt);
            }
        });

        btnBelepes.setBackground(new java.awt.Color(48, 181, 81));
        btnBelepes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnBelepes.setText("Belépés...");
        btnBelepes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));
        btnBelepes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBelepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBelepesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBejelentkezesLayout = new javax.swing.GroupLayout(pnlBejelentkezes);
        pnlBejelentkezes.setLayout(pnlBejelentkezesLayout);
        pnlBejelentkezesLayout.setHorizontalGroup(
            pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBejelentkezesLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFelhasznalo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(lblJelszo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFelhasznalo)
                    .addComponent(pwfJelszo))
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBejelentkezesLayout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(btnBelepes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );
        pnlBejelentkezesLayout.setVerticalGroup(
            pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBejelentkezesLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBejelentkezesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJelszo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwfJelszo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(btnBelepes)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pnlSzerviz.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        pnlSzerviz.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlSzerviz.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlSzerviz.setPreferredSize(new java.awt.Dimension(1024, 768));

        tabSzervizValaszto.setBackground(new java.awt.Color(64, 137, 186));
        tabSzervizValaszto.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tabSzervizValaszto.setMaximumSize(new java.awt.Dimension(1024, 768));
        tabSzervizValaszto.setMinimumSize(new java.awt.Dimension(800, 700));
        tabSzervizValaszto.setPreferredSize(new java.awt.Dimension(895, 730));

        pnlUgyfel.setBackground(new java.awt.Color(64, 137, 186));
        pnlUgyfel.setForeground(new java.awt.Color(255, 255, 153));
        pnlUgyfel.setAutoscrolls(true);
        pnlUgyfel.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlUgyfel.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlUgyfel.setPreferredSize(new java.awt.Dimension(930, 690));

        lblNev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblNev.setText("Név");

        txtUgyfelNev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblLakcim.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblLakcim.setText("Lakcím");

        txtUgyfelLakcim.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblEler.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEler.setText("Elérhetőség");

        txtUgyfelElerhetoseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblOkmanyAzonosito.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblOkmanyAzonosito.setText("Okmány azonosító");

        txtUgyfelOkmanyAzonosito.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblKedvez.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblKedvez.setText("Kedvezmény");

        btnUgyfelFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnUgyfelFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnUgyfelFelvitel.setText("Felvitel");
        btnUgyfelFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUgyfelFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUgyfelFelvitelActionPerformed(evt);
            }
        });

        cmbUgyfelKedvezmeny.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblOkmanyTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblOkmanyTipus.setText("Okmány típusa");

        txtUgyfelOkmanyTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlUgyfelLayout = new javax.swing.GroupLayout(pnlUgyfel);
        pnlUgyfel.setLayout(pnlUgyfelLayout);
        pnlUgyfelLayout.setHorizontalGroup(
            pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUgyfelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNev, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEler, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOkmanyTipus)
                    .addComponent(lblOkmanyAzonosito)
                    .addComponent(lblKedvez))
                .addGap(114, 114, 114)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUgyfelOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelNev, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelElerhetoseg, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnUgyfelFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbUgyfelKedvezmeny, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtUgyfelOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 139, Short.MAX_VALUE))
        );
        pnlUgyfelLayout.setVerticalGroup(
            pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUgyfelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelNev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUgyfelLayout.createSequentialGroup()
                        .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUgyfelLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))
                    .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEler, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUgyfelElerhetoseg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(pnlUgyfelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKedvez, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUgyfelKedvezmeny, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUgyfelFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        tabSzervizValaszto.addTab("Ügyfél", pnlUgyfel);

        pnlMarka.setBackground(new java.awt.Color(64, 137, 186));

        lblMarkaMarkKod.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMarkaMarkKod.setText("Márka kód");

        txtMarkaElnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMarkaElnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMarkaElnevezes.setText("Elnevezés");

        txtMarkaMarkKod.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnMarkaFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnMarkaFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMarkaFelvitel.setText("Felvitel");
        btnMarkaFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMarkaFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkaFelvitelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMarkaLayout = new javax.swing.GroupLayout(pnlMarka);
        pnlMarka.setLayout(pnlMarkaLayout);
        pnlMarkaLayout.setHorizontalGroup(
            pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarkaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMarkaFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMarkaLayout.createSequentialGroup()
                        .addGroup(pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarkaElnevezes)
                            .addComponent(lblMarkaMarkKod))
                        .addGap(103, 103, 103)
                        .addGroup(pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMarkaMarkKod, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txtMarkaElnevezes))))
                .addContainerGap(440, Short.MAX_VALUE))
        );
        pnlMarkaLayout.setVerticalGroup(
            pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarkaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarkaMarkKod)
                    .addComponent(txtMarkaMarkKod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(pnlMarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarkaElnevezes)
                    .addComponent(txtMarkaElnevezes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnMarkaFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(427, Short.MAX_VALUE))
        );

        tabSzervizValaszto.addTab("Márka", pnlMarka);

        pnlAuto.setBackground(new java.awt.Color(64, 137, 186));
        pnlAuto.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlAuto.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlAuto.setName(""); // NOI18N
        pnlAuto.setPreferredSize(new java.awt.Dimension(930, 690));

        lblAutoRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoRendszam.setText("Rendszám");

        lblAutoUgyfSzam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoUgyfSzam.setText("Ügyfél");

        lblAutoMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoMarka.setText("Márka");

        lblAutoEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoEvjarat.setText("Évjárat");

        lblAutoTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoTipus.setText("Típus");

        lblAutoMSzam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoMSzam.setText("Motorszám");

        lblAutoASzam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoASzam.setText("Alvázszám");

        lblAutoSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAutoSzin.setText("Szín");

        txtAutoTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtAutoMSzam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtAutoASzam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtAutoRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbAutoUgyfel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbAutoMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbAutoEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbAutoEvjarat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        cmbAutoSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbAutoSzin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fehér", "sárga", "narancs", "piros", "bíbor/lila", "kék", "zöld", "szürke", "barna", "fekete " }));

        btnAutoFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnAutoFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnAutoFelvitel.setText("Felvitel");
        btnAutoFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAutoFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoFelvitelActionPerformed(evt);
            }
        });

        lblAutoMarkaMegjelenit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblAutoUgyfelMegjelenit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlAutoLayout = new javax.swing.GroupLayout(pnlAuto);
        pnlAuto.setLayout(pnlAutoLayout);
        pnlAutoLayout.setHorizontalGroup(
            pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAutoRendszam)
                    .addComponent(lblAutoUgyfSzam)
                    .addComponent(lblAutoMarka)
                    .addComponent(lblAutoEvjarat))
                .addGap(52, 52, 52)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAutoMarkaMegjelenit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbAutoMarka, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbAutoUgyfel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAutoRendszam)
                    .addComponent(cmbAutoEvjarat, javax.swing.GroupLayout.Alignment.TRAILING, 0, 163, Short.MAX_VALUE)
                    .addComponent(lblAutoUgyfelMegjelenit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAutoLayout.createSequentialGroup()
                        .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAutoMSzam)
                            .addComponent(lblAutoASzam))
                        .addGap(18, 18, 18))
                    .addGroup(pnlAutoLayout.createSequentialGroup()
                        .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAutoSzin)
                            .addComponent(lblAutoTipus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAutoLayout.createSequentialGroup()
                            .addComponent(btnAutoFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(83, 83, 83))
                        .addGroup(pnlAutoLayout.createSequentialGroup()
                            .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAutoMSzam, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addComponent(txtAutoASzam))
                            .addGap(33, 33, 33)))
                    .addGroup(pnlAutoLayout.createSequentialGroup()
                        .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbAutoSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAutoTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        pnlAutoLayout.setVerticalGroup(
            pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAutoTipus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAutoTipus)
                    .addComponent(txtAutoRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAutoRendszam))
                .addGap(59, 59, 59)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutoUgyfSzam)
                    .addComponent(cmbAutoUgyfel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAutoSzin)
                    .addComponent(cmbAutoSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblAutoUgyfelMegjelenit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutoMarka)
                    .addComponent(lblAutoMSzam)
                    .addComponent(txtAutoMSzam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAutoMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(lblAutoMarkaMegjelenit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAutoEvjarat)
                    .addGroup(pnlAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAutoASzam)
                        .addComponent(txtAutoASzam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAutoEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(btnAutoFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        tabSzervizValaszto.addTab("Autó", pnlAuto);

        pnlMunkalap.setBackground(new java.awt.Color(64, 137, 186));
        pnlMunkalap.setMaximumSize(new java.awt.Dimension(1100, 700));
        pnlMunkalap.setPreferredSize(new java.awt.Dimension(1000, 600));

        lblMunkHatarido.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMunkHatarido.setText("Vállalási határidő");

        lblMunkMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMunkMunkalap.setText("Munkalap szám");

        lblMunkRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMunkRendszam.setText("Rendszám");

        lblMunkHiba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMunkHiba.setText("Hiba jegyzék");

        txtMunkMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtMunkHatarido.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbMunkRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txaMunkHibaJegyzek.setColumns(20);
        txaMunkHibaJegyzek.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txaMunkHibaJegyzek.setRows(5);
        scrHibaJegyzek.setViewportView(txaMunkHibaJegyzek);

        btnMunkFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnMunkFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMunkFelvitel.setText("Felvitel");
        btnMunkFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMunkFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMunkFelvitelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMunkalapLayout = new javax.swing.GroupLayout(pnlMunkalap);
        pnlMunkalap.setLayout(pnlMunkalapLayout);
        pnlMunkalapLayout.setHorizontalGroup(
            pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMunkalapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMunkHiba)
                    .addGroup(pnlMunkalapLayout.createSequentialGroup()
                        .addComponent(scrHibaJegyzek, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnMunkFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMunkalapLayout.createSequentialGroup()
                            .addComponent(lblMunkHatarido)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMunkHatarido, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMunkalapLayout.createSequentialGroup()
                            .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMunkMunkalap)
                                .addComponent(lblMunkRendszam))
                            .addGap(57, 57, 57)
                            .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMunkMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbMunkRendszam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        pnlMunkalapLayout.setVerticalGroup(
            pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMunkalapLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMunkFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMunkalapLayout.createSequentialGroup()
                        .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMunkMunkalap)
                            .addComponent(txtMunkMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMunkRendszam)
                            .addComponent(cmbMunkRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(pnlMunkalapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMunkHatarido)
                            .addComponent(txtMunkHatarido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addComponent(lblMunkHiba)
                        .addGap(28, 28, 28)
                        .addComponent(scrHibaJegyzek, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        tabSzervizValaszto.addTab("Munkalap", pnlMunkalap);

        pnlFeladat.setBackground(new java.awt.Color(64, 137, 186));
        pnlFeladat.setMaximumSize(new java.awt.Dimension(1150, 700));
        pnlFeladat.setPreferredSize(new java.awt.Dimension(1100, 600));

        lblFeladatFelad.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblFeladatFelad.setText("Feladat");

        txtFeladatMDij.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnFeladatFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnFeladatFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnFeladatFelvitel.setText("Felvitel");
        btnFeladatFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFeladatFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeladatFelvitelActionPerformed(evt);
            }
        });

        lblFeladatMDij.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblFeladatMDij.setText("Munka díj");

        txtFeladatFelad.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblFeladatTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblFeladatTipus.setText("Típus");

        cmbFeladatTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbFeladatTipus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "javítás", "alkatrész csere", "felkészítés vizsgára", "vizsgáztatás" }));

        lblFeladatElnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblFeladatElnevezes.setText("Elnevezés");

        txaFeladatElnevezes.setColumns(20);
        txaFeladatElnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txaFeladatElnevezes.setRows(5);
        scrFeladatGorg.setViewportView(txaFeladatElnevezes);

        javax.swing.GroupLayout pnlFeladatLayout = new javax.swing.GroupLayout(pnlFeladat);
        pnlFeladat.setLayout(pnlFeladatLayout);
        pnlFeladatLayout.setHorizontalGroup(
            pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFeladatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFeladatLayout.createSequentialGroup()
                        .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFeladatElnevezes)
                            .addComponent(scrFeladatGorg, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(btnFeladatFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFeladatLayout.createSequentialGroup()
                            .addComponent(lblFeladatTipus)
                            .addGap(113, 113, 113)
                            .addComponent(cmbFeladatTipus, 0, 210, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFeladatLayout.createSequentialGroup()
                            .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFeladatFelad)
                                .addComponent(lblFeladatMDij))
                            .addGap(65, 65, 65)
                            .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFeladatFelad, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(txtFeladatMDij)))))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        pnlFeladatLayout.setVerticalGroup(
            pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFeladatLayout.createSequentialGroup()
                .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFeladatLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFeladatFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFeladatLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFeladatFelad)
                            .addComponent(txtFeladatFelad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFeladatMDij)
                            .addComponent(txtFeladatMDij, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(pnlFeladatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFeladatTipus)
                            .addComponent(cmbFeladatTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(lblFeladatElnevezes)
                        .addGap(18, 18, 18)
                        .addComponent(scrFeladatGorg, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(143, 143, 143))
        );

        tabSzervizValaszto.addTab("Feladat", pnlFeladat);

        pnlSzereles.setBackground(new java.awt.Color(64, 137, 186));
        pnlSzereles.setMaximumSize(new java.awt.Dimension(1100, 700));
        pnlSzereles.setPreferredSize(new java.awt.Dimension(1000, 600));

        lblSzerelesSzerelo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesSzerelo.setText("Szerelő");

        lblSzerelesKezdIdo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesKezdIdo.setText("Szerelés kezdés ideje");

        lblSzerelesBefIdo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesBefIdo.setText("Szerelés befejezés ideje");

        lblSzerelesMOra.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesMOra.setText("Munkaóra");

        lblSzerelesMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesMunkalap.setText("Munkalap szám");

        lblSzerelesFeladat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzerelesFeladat.setText("Feladat");

        cmbSzerelesMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbSzerelesFeladat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtSzerelesMOra.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtSzerelesKezdIdo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtSzerelesBefIdo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnSzerelesFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnSzerelesFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnSzerelesFelvitel.setText("Felvitel");
        btnSzerelesFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSzerelesFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzerelesFelvitelActionPerformed(evt);
            }
        });

        cmbSzerelesSzerelo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzerelesFeladatMegnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzerelesSzereloNev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlSzerelesLayout = new javax.swing.GroupLayout(pnlSzereles);
        pnlSzereles.setLayout(pnlSzerelesLayout);
        pnlSzerelesLayout.setHorizontalGroup(
            pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzerelesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSzerelesLayout.createSequentialGroup()
                        .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSzerelesSzerelo)
                            .addComponent(lblSzerelesMunkalap)
                            .addComponent(lblSzerelesFeladat)
                            .addComponent(lblSzerelesKezdIdo))
                        .addGap(84, 84, 84)
                        .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSzerelesMunkalap, 0, 204, Short.MAX_VALUE)
                            .addComponent(cmbSzerelesSzerelo, 0, 204, Short.MAX_VALUE)
                            .addComponent(txtSzerelesKezdIdo, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(cmbSzerelesFeladat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSzerelesFeladatMegnevezes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSzerelesSzereloNev, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlSzerelesLayout.createSequentialGroup()
                        .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSzerelesLayout.createSequentialGroup()
                                .addComponent(lblSzerelesBefIdo)
                                .addGap(56, 56, 56)
                                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSzerelesMOra, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(txtSzerelesBefIdo)))
                            .addComponent(lblSzerelesMOra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(btnSzerelesFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
        );
        pnlSzerelesLayout.setVerticalGroup(
            pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzerelesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzerelesMunkalap)
                    .addComponent(cmbSzerelesMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzerelesSzerelo)
                    .addComponent(cmbSzerelesSzerelo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSzerelesSzereloNev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzerelesFeladat)
                    .addComponent(cmbSzerelesFeladat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSzerelesFeladatMegnevezes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzerelesKezdIdo)
                    .addComponent(txtSzerelesKezdIdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzerelesBefIdo)
                    .addComponent(txtSzerelesBefIdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzerelesLayout.createSequentialGroup()
                        .addGroup(pnlSzerelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSzerelesMOra)
                            .addComponent(txtSzerelesMOra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzerelesLayout.createSequentialGroup()
                        .addComponent(btnSzerelesFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
        );

        tabSzervizValaszto.addTab("Szerelés", pnlSzereles);

        pnlAlkatresz.setBackground(new java.awt.Color(64, 137, 186));

        lblAlkatreszKod.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAlkatreszKod.setText("Alkatrész kód");

        txtAlkatreszKod.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblAlkatreszMegnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAlkatreszMegnevezes.setText("Alkatrész megnevezése");

        txtAlkatreszMegnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnAlkatreszFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnAlkatreszFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnAlkatreszFelvitel.setText("Felvitel");
        btnAlkatreszFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlkatreszFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlkatreszFelvitelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAlkatreszLayout = new javax.swing.GroupLayout(pnlAlkatresz);
        pnlAlkatresz.setLayout(pnlAlkatreszLayout);
        pnlAlkatreszLayout.setHorizontalGroup(
            pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlkatreszLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlkatreszKod)
                    .addComponent(lblAlkatreszMegnevezes))
                .addGap(26, 26, 26)
                .addGroup(pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAlkatreszLayout.createSequentialGroup()
                        .addComponent(txtAlkatreszKod, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addGap(325, 325, 325))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAlkatreszLayout.createSequentialGroup()
                        .addGroup(pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlAlkatreszLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAlkatreszFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAlkatreszMegnevezes))
                        .addGap(323, 323, 323))))
        );
        pnlAlkatreszLayout.setVerticalGroup(
            pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlkatreszLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlkatreszKod)
                    .addComponent(txtAlkatreszKod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(pnlAlkatreszLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlkatreszMegnevezes)
                    .addComponent(txtAlkatreszMegnevezes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(btnAlkatreszFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        tabSzervizValaszto.addTab("Alkatrész", pnlAlkatresz);

        pnlBeszerzes.setBackground(new java.awt.Color(64, 137, 186));
        pnlBeszerzes.setMaximumSize(new java.awt.Dimension(1100, 700));
        pnlBeszerzes.setPreferredSize(new java.awt.Dimension(1000, 600));

        lblBeszerzesBeszer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesBeszer.setText("Beszerzési kód");

        lblBeszerzesMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesMunkalap.setText("Munkalap szám");

        txtBeszerzesBeszer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblBeszerzesAlkAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesAlkAr.setText("Alkatrész ára");

        cmbBeszerzesMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtBeszerzesAlkAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblBeszerzesMegrend.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesMegrend.setText("Megrendelés ideje");

        txtBeszerzesMegrend.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnMunkalapKeszit.setBackground(new java.awt.Color(48, 181, 81));
        btnMunkalapKeszit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMunkalapKeszit.setText("Munkalap készít");
        btnMunkalapKeszit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMunkalapKeszit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMunkalapKeszitActionPerformed(evt);
            }
        });

        btnBeszerzesFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnBeszerzesFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnBeszerzesFelvitel.setText("Felvitel");
        btnBeszerzesFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeszerzesFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeszerzesFelvitelActionPerformed(evt);
            }
        });

        lblBeszerzesAlkatresz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesAlkatresz.setText("Alkatrész");

        cmbBeszerzesAlkatresz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblBeszerzesAlkatreszMegnevezes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlBeszerzesLayout = new javax.swing.GroupLayout(pnlBeszerzes);
        pnlBeszerzes.setLayout(pnlBeszerzesLayout);
        pnlBeszerzesLayout.setHorizontalGroup(
            pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBeszerzesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBeszerzesBeszer)
                    .addComponent(lblBeszerzesMunkalap)
                    .addComponent(lblBeszerzesAlkatresz)
                    .addComponent(lblBeszerzesAlkAr)
                    .addComponent(lblBeszerzesMegrend))
                .addGap(161, 161, 161)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBeszerzesMegrend, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBeszerzesAlkAr, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBeszerzesBeszer)
                        .addComponent(cmbBeszerzesMunkalap, 0, 259, Short.MAX_VALUE))
                    .addGroup(pnlBeszerzesLayout.createSequentialGroup()
                        .addComponent(btnBeszerzesFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnMunkalapKeszit))
                    .addGroup(pnlBeszerzesLayout.createSequentialGroup()
                        .addComponent(cmbBeszerzesAlkatresz, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBeszerzesAlkatreszMegnevezes, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlBeszerzesLayout.setVerticalGroup(
            pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBeszerzesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesBeszer)
                    .addComponent(txtBeszerzesBeszer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesMunkalap)
                    .addComponent(cmbBeszerzesMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBeszerzesLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBeszerzesAlkatresz)
                            .addComponent(cmbBeszerzesAlkatresz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBeszerzesLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblBeszerzesAlkatreszMegnevezes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesAlkAr)
                    .addComponent(txtBeszerzesAlkAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesMegrend)
                    .addComponent(txtBeszerzesMegrend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129)
                .addGroup(pnlBeszerzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMunkalapKeszit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBeszerzesFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        tabSzervizValaszto.addTab("Beszerzés", pnlBeszerzes);

        btnSzervizFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnSzervizFomenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnSzervizFomenu.setText("Főmenü");
        btnSzervizFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSzervizFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzervizFomenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSzervizLayout = new javax.swing.GroupLayout(pnlSzerviz);
        pnlSzerviz.setLayout(pnlSzervizLayout);
        pnlSzervizLayout.setHorizontalGroup(
            pnlSzervizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzervizLayout.createSequentialGroup()
                .addComponent(tabSzervizValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSzervizFomenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSzervizLayout.setVerticalGroup(
            pnlSzervizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzervizLayout.createSequentialGroup()
                .addComponent(tabSzervizValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzervizLayout.createSequentialGroup()
                .addGap(0, 358, Short.MAX_VALUE)
                .addComponent(btnSzervizFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(346, 346, 346))
        );

        pnlJarmuHozzaAdasa.setBackground(new java.awt.Color(64, 137, 186));
        pnlJarmuHozzaAdasa.setAutoscrolls(true);
        pnlJarmuHozzaAdasa.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlJarmuHozzaAdasa.setPreferredSize(new java.awt.Dimension(1024, 768));

        tabJarmuHozzValaszto.setBackground(new java.awt.Color(64, 137, 186));
        tabJarmuHozzValaszto.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tabJarmuHozzValaszto.setMaximumSize(new java.awt.Dimension(1024, 768));
        tabJarmuHozzValaszto.setPreferredSize(new java.awt.Dimension(950, 730));

        pnlJarmuHozzElso.setBackground(new java.awt.Color(64, 137, 186));
        pnlJarmuHozzElso.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlJarmuHozzElso.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlJarmuHozzElso.setPreferredSize(new java.awt.Dimension(930, 690));

        lblRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblRendszam.setText("Rendszám");

        txtRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMarka.setText("Márka");

        lblModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModell.setText("Modell");

        lblTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTipus.setText("Típus");

        txtTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblEvjarat.setText("Évjárat");

        cmbEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbEvjarat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        lblHonap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHonap.setText("Hónap");

        cmbHonap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbHonap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        lblKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblKivitel.setText("Kivitel");

        cmbKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAllapot.setText("Állapot");

        cmbAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbAllapot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "normál", "kitűnő", "megkímélt", "újszerű", "sérülésmentes", "sérült", "enyhén sérült", "eleje sérült", "hátulja sérült", "baloldala sérült", "jobboldala sérült", "hiányos", "hibás" }));

        lblLeiras.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblLeiras.setText("Jármű leírása: ");

        txaLeiras.setColumns(20);
        txaLeiras.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txaLeiras.setRows(5);
        scrLeiras.setViewportView(txaLeiras);

        lblJarmuTipusa.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblJarmuTipusa.setText("jármű típusa");

        cmbJarmuTipusa.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbJarmuTipusa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Válasszon!", "Személyautó", "Motorkerékpár", "Haszonjármű" }));
        cmbJarmuTipusa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJarmuTipusaActionPerformed(evt);
            }
        });

        lblHiba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHiba.setText("Jármű típushibái: ");

        txaHibak.setColumns(20);
        txaHibak.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txaHibak.setRows(5);
        scrHibak.setViewportView(txaHibak);

        txtModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        chkTelephely.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        chkTelephely.setSelected(true);
        chkTelephely.setText("Telephelyen van");

        javax.swing.GroupLayout pnlJarmuHozzElsoLayout = new javax.swing.GroupLayout(pnlJarmuHozzElso);
        pnlJarmuHozzElso.setLayout(pnlJarmuHozzElsoLayout);
        pnlJarmuHozzElsoLayout.setHorizontalGroup(
            pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                .addComponent(lblKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(cmbKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblAllapot)
                                        .addGap(81, 81, 81)
                                        .addComponent(cmbAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblHonap)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbHonap, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblModell, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtModell, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addComponent(lblRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(53, 53, 53)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLeiras)
                                    .addComponent(scrLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHiba)
                                    .addComponent(scrHibak, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(chkTelephely))))))
                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblJarmuTipusa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmbJarmuTipusa, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        pnlJarmuHozzElsoLayout.setVerticalGroup(
            pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJarmuTipusa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cmbJarmuTipusa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblModell, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtModell))
                                .addGap(36, 36, 36)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTipus)
                                    .addComponent(txtTipus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHonap)
                                    .addComponent(cmbHonap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblAllapot)
                                            .addComponent(cmbAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkTelephely, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlJarmuHozzElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(scrLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(lblHiba)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrHibak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))
                    .addGroup(pnlJarmuHozzElsoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLeiras)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabJarmuHozzValaszto.addTab("Jármű hozzáadása I", pnlJarmuHozzElso);

        pnlJarmuHozzMasodik.setBackground(new java.awt.Color(64, 137, 186));
        pnlJarmuHozzMasodik.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlJarmuHozzMasodik.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlJarmuHozzMasodik.setPreferredSize(new java.awt.Dimension(930, 690));

        lblUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUzemanyag.setText("Üzemanyag");

        cmbUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHenger.setText("Hengerűrtartalom");

        txtHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHenger.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblKilom.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblKilom.setText("Kilométeróra állás");

        txtKilom.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSajat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSajat.setText("Saját tömeg");

        txtSajat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblTeljes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTeljes.setText("Teljesítmény");

        txtTeljes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtTeljes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTeljesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTeljesFocusLost(evt);
            }
        });
        txtTeljes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTeljesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeljesKeyTyped(evt);
            }
        });

        lblSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzin.setText("Szín");

        cmbSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbSzin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fehér", "sárga", "narancs", "piros", "bíbor/lila", "kék", "zöld", "szürke", "barna", "fekete" }));

        lblSebess.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSebess.setText("Sebességváltó fajtája");

        cmbSebess.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbSebess.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mechanikus", "félautomata", "automata", "szekvenciális" }));

        lblAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAr.setText("Vételár");

        txtAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHengerEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHengerEgyseg.setText("<html>cm<sup>3</sup></html>");

        lblKilometerEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblKilometerEgyseg.setText("km");

        lblSajatEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSajatEgyseg.setText("kg");

        lblTeljesEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTeljesEgyseg.setText("kW");

        lblLoeroEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblLoeroEgyseg.setText("Lóerő");

        lblLoero.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblArEgyseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblArEgyseg.setText("Ft");

        javax.swing.GroupLayout pnlJarmuHozzMasodikLayout = new javax.swing.GroupLayout(pnlJarmuHozzMasodik);
        pnlJarmuHozzMasodik.setLayout(pnlJarmuHozzMasodikLayout);
        pnlJarmuHozzMasodikLayout.setHorizontalGroup(
            pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                        .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                                        .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblKilom))
                                        .addGap(77, 77, 77)
                                        .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtHenger, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(cmbUzemanyag, 0, 198, Short.MAX_VALUE)
                                            .addComponent(txtTeljes, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtKilom)))
                                    .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                                        .addComponent(lblSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(194, 194, 194)
                                        .addComponent(cmbSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlJarmuHozzMasodikLayout.createSequentialGroup()
                                        .addComponent(lblSebess)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblTeljes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlJarmuHozzMasodikLayout.createSequentialGroup()
                                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAr, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(132, 132, 132)
                                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAr))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHengerEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKilometerEgyseg)
                            .addComponent(lblArEgyseg)
                            .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                                .addComponent(lblTeljesEgyseg)
                                .addGap(18, 18, 18)
                                .addComponent(lblLoero, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblLoeroEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSajatEgyseg))))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        pnlJarmuHozzMasodikLayout.setVerticalGroup(
            pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuHozzMasodikLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuHozzMasodikLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblHengerEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKilom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKilom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKilometerEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTeljes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTeljes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTeljesEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLoero, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoeroEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzin))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuHozzMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSajatEgyseg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(216, 216, 216))
        );

        tabJarmuHozzValaszto.addTab("Jármű hozzáadása II", pnlJarmuHozzMasodik);

        pnlJarmuvekMegjelenites.setBackground(new java.awt.Color(64, 137, 186));

        scrTablazat.setAutoscrolls(true);
        scrTablazat.setVerifyInputWhenFocusTarget(false);

        tblJarmuModosit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tblJarmuModosit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblJarmuModosit.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblJarmuModosit.setDragEnabled(true);
        tblJarmuModosit.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblJarmuModosit.setName(""); // NOI18N
        tblJarmuModosit.setPreferredSize(new java.awt.Dimension(3000, 200));
        tblJarmuModosit.setRowHeight(30);
        scrTablazat.setViewportView(tblJarmuModosit);

        cmbJarmuTipusaModosit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbJarmuTipusaModosit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Válasszon!", "Személyautó", "Motorkerékpár", "Haszonjármű" }));
        cmbJarmuTipusaModosit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJarmuTipusaModositItemStateChanged(evt);
            }
        });
        cmbJarmuTipusaModosit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJarmuTipusaModositActionPerformed(evt);
            }
        });

        pnlJarmuModositAdatok.setBackground(new java.awt.Color(64, 137, 186));

        tabJarmuModositAdatok.setBackground(new java.awt.Color(64, 137, 186));
        tabJarmuModositAdatok.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        pnlJarmuModositAdatokElso.setBackground(new java.awt.Color(64, 137, 186));

        lblModositRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositRendszam.setText("Rendszám");

        txtModositRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositMarka.setText("Márka");

        txtModositMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositModell.setText("Modell");

        txtModositModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositTipus.setText("Típus");

        txtModositTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositEvjarat.setText("Évjárat");

        lblModositHonap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositHonap.setText("Hónap");

        lblModositKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositKivitel.setText("Kivitel");

        lblModositAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositAllapot.setText("Állapot");

        cmbModositEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbModositEvjarat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        cmbModositHonap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbModositHonap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbModositKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbModositAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbModositAllapot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "normál", "kitűnő", "megkímélt", "újszerű", "sérülésmentes", "sérült", "enyhén sérült", "eleje sérült", "hátulja sérült", "baloldala sérült", "jobboldala sérült", "hiányos", "hibás" }));

        javax.swing.GroupLayout pnlJarmuModositAdatokElsoLayout = new javax.swing.GroupLayout(pnlJarmuModositAdatokElso);
        pnlJarmuModositAdatokElso.setLayout(pnlJarmuModositAdatokElsoLayout);
        pnlJarmuModositAdatokElsoLayout.setHorizontalGroup(
            pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addComponent(lblModositTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtModositTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addComponent(lblModositModell, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(txtModositModell, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addComponent(lblModositMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtModositMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addComponent(lblModositRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtModositRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModositHonap)
                            .addComponent(lblModositEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModositAllapot))
                        .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbModositHonap, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbModositEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cmbModositAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addComponent(lblModositKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbModositKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        pnlJarmuModositAdatokElsoLayout.setVerticalGroup(
            pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createSequentialGroup()
                        .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblModositRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModositRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblModositMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModositMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModositHonap)
                            .addComponent(cmbModositHonap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblModositEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbModositEvjarat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositModell, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositModell)
                    .addComponent(lblModositKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModositKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlJarmuModositAdatokElsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositTipus)
                    .addComponent(txtModositTipus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModositAllapot)
                    .addComponent(cmbModositAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113))
        );

        tabJarmuModositAdatok.addTab("Jármű módosítása I", pnlJarmuModositAdatokElso);

        pnlJarmuModositAdatokMasodik.setBackground(new java.awt.Color(64, 137, 186));

        lblModositUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositUzemanyag.setText("Üzemanyag");

        lblModositHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositHenger.setText("Hengerűrtartalom");

        lblModositKilom.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositKilom.setText("Kilométeróra állás");

        txtModositHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtModositKilometer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbModositUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositSajat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositSajat.setText("Saját tömeg");

        txtModositSajat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositTeljes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositTeljes.setText("Teljesítmény");

        txtModositTeljesitmeny.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlJarmuModositAdatokMasodikLayout = new javax.swing.GroupLayout(pnlJarmuModositAdatokMasodik);
        pnlJarmuModositAdatokMasodik.setLayout(pnlJarmuModositAdatokMasodikLayout);
        pnlJarmuModositAdatokMasodikLayout.setHorizontalGroup(
            pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuModositAdatokMasodikLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModositHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModositUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModositKilom)
                    .addComponent(lblModositSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModositTeljes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(241, 241, 241)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModositHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModositUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositKilometer, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositTeljesitmeny))
                .addGap(0, 191, Short.MAX_VALUE))
        );
        pnlJarmuModositAdatokMasodikLayout.setVerticalGroup(
            pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuModositAdatokMasodikLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModositUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositHenger, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositHenger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositKilom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositKilometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositSajat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositSajat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlJarmuModositAdatokMasodikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositTeljes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModositTeljesitmeny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        tabJarmuModositAdatok.addTab("Jármű módosítása II", pnlJarmuModositAdatokMasodik);

        pnlJarmuModositAdatokHarmadik.setBackground(new java.awt.Color(64, 137, 186));
        pnlJarmuModositAdatokHarmadik.setMaximumSize(new java.awt.Dimension(838, 339));

        lblModositSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositSzin.setText("Szín");

        lblModositSebess.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositSebess.setText("Sebességváltó fajtája");

        lblModositAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositAr.setText("Vételár");

        txtModositAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbModositSzin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbModositSzin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fehér", "sárga", "narancs", "piros", "bíbor/lila", "kék", "zöld", "szürke", "barna", "fekete" }));

        cmbModositSebess.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbModositSebess.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mechanikus", "félautomata", "automata", "szekvenciális" }));

        lblModositLeiras.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositLeiras.setText("Jármű leírása: ");

        txtModositLeiras.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblModositHiba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblModositHiba.setText("Jármű típushibái: ");

        txtModositHiba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        chkModositTelephely.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        chkModositTelephely.setSelected(true);
        chkModositTelephely.setText("Telephelyen van");

        javax.swing.GroupLayout pnlJarmuModositAdatokHarmadikLayout = new javax.swing.GroupLayout(pnlJarmuModositAdatokHarmadik);
        pnlJarmuModositAdatokHarmadik.setLayout(pnlJarmuModositAdatokHarmadikLayout);
        pnlJarmuModositAdatokHarmadikLayout.setHorizontalGroup(
            pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuModositAdatokHarmadikLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlJarmuModositAdatokHarmadikLayout.createSequentialGroup()
                            .addComponent(lblModositSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(148, 148, 148))
                        .addComponent(lblModositSebess, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lblModositLeiras)
                    .addComponent(lblModositHiba)
                    .addComponent(lblModositAr, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlJarmuModositAdatokHarmadikLayout.createSequentialGroup()
                        .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModositAr, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbModositSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModositLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModositHiba, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140))
                    .addGroup(pnlJarmuModositAdatokHarmadikLayout.createSequentialGroup()
                        .addComponent(cmbModositSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkModositTelephely)
                        .addGap(17, 17, 17))))
        );
        pnlJarmuModositAdatokHarmadikLayout.setVerticalGroup(
            pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuModositAdatokHarmadikLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositSzin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModositSzin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkModositTelephely, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModositSebess, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositLeiras)
                    .addComponent(txtModositLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModositHiba)
                    .addComponent(txtModositHiba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(pnlJarmuModositAdatokHarmadikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModositAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModositAr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        tabJarmuModositAdatok.addTab("Jármű módosítása III", pnlJarmuModositAdatokHarmadik);

        javax.swing.GroupLayout pnlJarmuModositAdatokLayout = new javax.swing.GroupLayout(pnlJarmuModositAdatok);
        pnlJarmuModositAdatok.setLayout(pnlJarmuModositAdatokLayout);
        pnlJarmuModositAdatokLayout.setHorizontalGroup(
            pnlJarmuModositAdatokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabJarmuModositAdatok)
        );
        pnlJarmuModositAdatokLayout.setVerticalGroup(
            pnlJarmuModositAdatokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabJarmuModositAdatok, javax.swing.GroupLayout.PREFERRED_SIZE, 395, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlJarmuvekMegjelenitesLayout = new javax.swing.GroupLayout(pnlJarmuvekMegjelenites);
        pnlJarmuvekMegjelenites.setLayout(pnlJarmuvekMegjelenitesLayout);
        pnlJarmuvekMegjelenitesLayout.setHorizontalGroup(
            pnlJarmuvekMegjelenitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuvekMegjelenitesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlJarmuvekMegjelenitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrTablazat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlJarmuvekMegjelenitesLayout.createSequentialGroup()
                        .addComponent(cmbJarmuTipusaModosit, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlJarmuModositAdatok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlJarmuvekMegjelenitesLayout.setVerticalGroup(
            pnlJarmuvekMegjelenitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuvekMegjelenitesLayout.createSequentialGroup()
                .addComponent(cmbJarmuTipusaModosit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrTablazat, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlJarmuModositAdatok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabJarmuHozzValaszto.addTab("Módosítás / Törlés", pnlJarmuvekMegjelenites);

        btnJarmuHozzaAdasa.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuHozzaAdasa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnJarmuHozzaAdasa.setText("<html><p align=\"center\">Jármű<br>Hozzáadása...</p><html>");
        btnJarmuHozzaAdasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuHozzaAdasa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnJarmuHozzaAdasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuHozzaAdasaActionPerformed(evt);
            }
        });

        btnKep.setBackground(new java.awt.Color(48, 181, 81));
        btnKep.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnKep.setText("<html><p align=\"center\">Kép<br>hozzáadása...</p><html>");
        btnKep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKepActionPerformed(evt);
            }
        });

        btnJarmuHozFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuHozFomenu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnJarmuHozFomenu.setText("Főmenü..");
        btnJarmuHozFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuHozFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuHozFomenuActionPerformed(evt);
            }
        });

        btnJarmuModositasa.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuModositasa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnJarmuModositasa.setText("<html><p align=\"center\">Jármű<br>Módosítása...</p><html>");
        btnJarmuModositasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuModositasa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnJarmuModositasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuModositasaActionPerformed(evt);
            }
        });

        btnJarmuTorlese.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuTorlese.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnJarmuTorlese.setText("<html><p align=\"center\">Jármű<br>Törlése...</p><html>");
        btnJarmuTorlese.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuTorlese.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnJarmuTorlese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuTorleseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlJarmuHozzaAdasaLayout = new javax.swing.GroupLayout(pnlJarmuHozzaAdasa);
        pnlJarmuHozzaAdasa.setLayout(pnlJarmuHozzaAdasaLayout);
        pnlJarmuHozzaAdasaLayout.setHorizontalGroup(
            pnlJarmuHozzaAdasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJarmuHozzaAdasaLayout.createSequentialGroup()
                .addComponent(tabJarmuHozzValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlJarmuHozzaAdasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJarmuHozFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJarmuHozzaAdasa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJarmuModositasa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJarmuTorlese, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlJarmuHozzaAdasaLayout.setVerticalGroup(
            pnlJarmuHozzaAdasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabJarmuHozzValaszto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(pnlJarmuHozzaAdasaLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(btnJarmuHozzaAdasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnJarmuModositasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnJarmuTorlese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnJarmuHozFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFomenu.setBackground(new java.awt.Color(64, 137, 186));

        btnJarmuHozz.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuHozz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnJarmuHozz.setText("Jármű hozzáadása/módosítása...");
        btnJarmuHozz.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuHozz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuHozzActionPerformed(evt);
            }
        });

        btnJarmuvek.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuvek.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnJarmuvek.setText("Járművek...");
        btnJarmuvek.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuvek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuvekActionPerformed(evt);
            }
        });

        btnSzerviz.setBackground(new java.awt.Color(48, 181, 81));
        btnSzerviz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnSzerviz.setText("Szerviz...");
        btnSzerviz.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSzerviz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzervizActionPerformed(evt);
            }
        });

        btnKilepes.setBackground(new java.awt.Color(48, 181, 81));
        btnKilepes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnKilepes.setText("Kilépés");
        btnKilepes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKilepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesActionPerformed(evt);
            }
        });

        lblIdoKijelzes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblIdo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblIdo.setText("Pontos Idő");

        btnFelhasznalo.setBackground(new java.awt.Color(48, 181, 81));
        btnFelhasznalo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnFelhasznalo.setText("Felhasználói fiókok kezelése..");
        btnFelhasznalo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFelhasznalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFelhasznaloActionPerformed(evt);
            }
        });

        btnSzervizModosit.setBackground(new java.awt.Color(48, 181, 81));
        btnSzervizModosit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnSzervizModosit.setText("Szerviz adatok módosít...");
        btnSzervizModosit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSzervizModosit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzervizModositActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFomenuLayout = new javax.swing.GroupLayout(pnlFomenu);
        pnlFomenu.setLayout(pnlFomenuLayout);
        pnlFomenuLayout.setHorizontalGroup(
            pnlFomenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFomenuLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(pnlFomenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSzerviz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnJarmuvek, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnJarmuHozz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFelhasznalo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addComponent(btnSzervizModosit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(pnlFomenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdo, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(lblIdoKijelzes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlFomenuLayout.setVerticalGroup(
            pnlFomenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFomenuLayout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(pnlFomenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFomenuLayout.createSequentialGroup()
                        .addComponent(lblIdo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdoKijelzes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFomenuLayout.createSequentialGroup()
                        .addComponent(btnJarmuvek, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnJarmuHozz, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSzerviz, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSzervizModosit, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnKilepes, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pnlJarmuKereses.setBackground(new java.awt.Color(64, 137, 186));
        pnlJarmuKereses.setMaximumSize(new java.awt.Dimension(1600, 900));
        pnlJarmuKereses.setMinimumSize(new java.awt.Dimension(900, 700));
        pnlJarmuKereses.setPreferredSize(new java.awt.Dimension(1024, 768));

        tapJarmuValaszto.setBackground(new java.awt.Color(64, 137, 186));
        tapJarmuValaszto.setMaximumSize(new java.awt.Dimension(1024, 768));
        tapJarmuValaszto.setMinimumSize(new java.awt.Dimension(900, 700));
        tapJarmuValaszto.setPreferredSize(new java.awt.Dimension(950, 730));

        pnlSzemelyauto.setBackground(new java.awt.Color(64, 137, 186));
        pnlSzemelyauto.setMaximumSize(new java.awt.Dimension(1600, 900));
        pnlSzemelyauto.setPreferredSize(new java.awt.Dimension(930, 690));

        lblSzemelyautoMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoMarka.setText("Márka");

        cmbSzemelyautoMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbSzemelyautoMarka.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Válasszon!" }));

        lblSzemelyautoModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoModell.setText("Modell");

        cmbSzemelyautoModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzemelyautoEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoEvjarat.setText("Évjárat(-tól -ig)");

        cmbSzemelyautoEvjaratTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbSzemelyautoEvjaratTol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        cmbSzemelyautoEvjaratIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbSzemelyautoEvjaratIg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        lblSzemelyautoKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoKivitel.setText("Kivitel");

        cmbSzemelyautoKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzemelyautoVetelar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoVetelar.setText("Vételár(-tól -ig)");

        txtSzemelyautoVetalarTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoVetalarTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoVetalarTolKeyTyped(evt);
            }
        });

        txtSzemelyautoVetelarIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoVetelarIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoVetelarIgKeyTyped(evt);
            }
        });

        lblSzemelyautoUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoUzemanyag.setText("Üzemanyag");

        cmbSzemelyautoUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzemelyautoKilometer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoKilometer.setText("Kilométeróra állása (-tól -ig)");

        txtSzemelyautoKilometerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoKilometerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoKilometerIgKeyTyped(evt);
            }
        });

        txtSzemelyautoHengerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoHengerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoHengerTolKeyTyped(evt);
            }
        });

        lblSzemelyautoHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoHenger.setText("Hengerűrtartalom (-tól -ig)");

        txtSzemelyautoKilometerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoKilometerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoKilometerTolKeyTyped(evt);
            }
        });

        txtSzemelyautoHengerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSzemelyautoHengerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSzemelyautoHengerIgKeyTyped(evt);
            }
        });

        lblSzemelyautoRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoRendszam.setText("Rendszám");

        txtSzemelyautoRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblSzemelyautoAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblSzemelyautoAllapot.setText("Állapot");

        cmbSzemelyautoAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnAutoKereses.setBackground(new java.awt.Color(48, 181, 81));
        btnAutoKereses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnAutoKereses.setText("Keresés...");
        btnAutoKereses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAutoKereses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoKeresesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSzemelyautoLayout = new javax.swing.GroupLayout(pnlSzemelyauto);
        pnlSzemelyauto.setLayout(pnlSzemelyautoLayout);
        pnlSzemelyautoLayout.setHorizontalGroup(
            pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzemelyautoLayout.createSequentialGroup()
                        .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                                            .addComponent(lblSzemelyautoMarka)
                                            .addGap(136, 136, 136))
                                        .addComponent(cmbSzemelyautoMarka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbSzemelyautoKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSzemelyautoRendszam)
                                    .addComponent(txtSzemelyautoRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSzemelyautoEvjarat)
                                    .addComponent(lblSzemelyautoAllapot)
                                    .addComponent(lblSzemelyautoModell)
                                    .addComponent(cmbSzemelyautoModell, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSzemelyautoAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblSzemelyautoKivitel))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSzemelyautoUzemanyag)
                            .addComponent(cmbSzemelyautoUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(253, 253, 253))
                    .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                        .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSzemelyautoVetelar)
                            .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSzemelyautoLayout.createSequentialGroup()
                                    .addComponent(txtSzemelyautoVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSzemelyautoVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAutoKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSzemelyautoLayout.createSequentialGroup()
                                    .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSzemelyautoHenger)
                                        .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                                            .addComponent(txtSzemelyautoHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSzemelyautoHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                                            .addGap(248, 248, 248)
                                            .addComponent(cmbSzemelyautoEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(38, 38, 38)
                                    .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                                            .addComponent(txtSzemelyautoKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSzemelyautoKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblSzemelyautoKilometer)
                                        .addComponent(cmbSzemelyautoEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlSzemelyautoLayout.setVerticalGroup(
            pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzemelyautoMarka)
                    .addComponent(lblSzemelyautoModell))
                .addGap(18, 18, 18)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSzemelyautoMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzemelyautoModell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSzemelyautoKivitel)
                    .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSzemelyautoAllapot)
                        .addComponent(lblSzemelyautoUzemanyag)))
                .addGap(18, 18, 18)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSzemelyautoKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzemelyautoAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzemelyautoUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzemelyautoRendszam)
                    .addComponent(lblSzemelyautoEvjarat))
                .addGap(18, 18, 18)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSzemelyautoRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzemelyautoEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSzemelyautoEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSzemelyautoHenger)
                    .addComponent(lblSzemelyautoKilometer))
                .addGap(18, 18, 18)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSzemelyautoHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSzemelyautoHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSzemelyautoKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSzemelyautoKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(lblSzemelyautoVetelar)
                .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSzemelyautoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlSzemelyautoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSzemelyautoVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSzemelyautoVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzemelyautoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAutoKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        tapJarmuValaszto.addTab("", autoKep, pnlSzemelyauto);

        pnlMotorkerekpar.setBackground(new java.awt.Color(64, 137, 186));
        pnlMotorkerekpar.setMaximumSize(new java.awt.Dimension(1600, 900));
        pnlMotorkerekpar.setPreferredSize(new java.awt.Dimension(930, 690));

        lblMotorkerekparMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparMarka.setText("Márka");

        cmbMotorkerekparMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMotorkerekparModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparModell.setText("Modell");

        cmbMotorkerekparModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMotorkerekparEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparEvjarat.setText("Évjárat(-tól -ig)");

        cmbMotorkerekparEvjaratTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbMotorkerekparEvjaratTol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        cmbMotorkerekparEvjaratIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbMotorkerekparEvjaratIg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        lblMotorkerekparKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparKivitel.setText("Kivitel");

        cmbMotorkerekparKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMotorkerekparVetelar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparVetelar.setText("Vételár(-tól -ig)");

        txtMotorkerekparVetalarTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparVetalarTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparVetalarTolKeyTyped(evt);
            }
        });

        txtMotorkerekparVetelarIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparVetelarIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparVetelarIgKeyTyped(evt);
            }
        });

        lblMotorkerekparUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparUzemanyag.setText("Üzemanyag");

        cmbMotorkerekparUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMotorkerekparKilometer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparKilometer.setText("Kilométeróra állása (-tól -ig)");

        txtMotorkerekparKilometerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparKilometerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparKilometerIgKeyTyped(evt);
            }
        });

        txtMotorkerekparHengerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparHengerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparHengerTolKeyTyped(evt);
            }
        });

        lblMotorkerekparHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparHenger.setText("Hengerűrtartalom (-tól -ig)");

        txtMotorkerekparKilometerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparKilometerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparKilometerTolKeyTyped(evt);
            }
        });

        txtMotorkerekparHengerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtMotorkerekparHengerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotorkerekparHengerIgKeyTyped(evt);
            }
        });

        lblMotorkerekparRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparRendszam.setText("Rendszám");

        txtMotorkerekparRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblMotorkerekparAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMotorkerekparAllapot.setText("Állapot");

        cmbMotorkerekparAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnMotorKereses.setBackground(new java.awt.Color(48, 181, 81));
        btnMotorKereses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMotorKereses.setText("Keresés...");
        btnMotorKereses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMotorKereses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotorKeresesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMotorkerekparLayout = new javax.swing.GroupLayout(pnlMotorkerekpar);
        pnlMotorkerekpar.setLayout(pnlMotorkerekparLayout);
        pnlMotorkerekparLayout.setHorizontalGroup(
            pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMotorkerekparLayout.createSequentialGroup()
                        .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                                            .addComponent(lblMotorkerekparMarka)
                                            .addGap(136, 136, 136))
                                        .addComponent(cmbMotorkerekparMarka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbMotorkerekparKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMotorkerekparRendszam)
                                    .addComponent(txtMotorkerekparRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMotorkerekparEvjarat)
                                    .addComponent(lblMotorkerekparAllapot)
                                    .addComponent(lblMotorkerekparModell)
                                    .addComponent(cmbMotorkerekparModell, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbMotorkerekparAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMotorkerekparKivitel))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotorkerekparUzemanyag)
                            .addComponent(cmbMotorkerekparUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(253, 253, 253))
                    .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                        .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotorkerekparVetelar)
                            .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMotorkerekparLayout.createSequentialGroup()
                                    .addComponent(txtMotorkerekparVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtMotorkerekparVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMotorKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMotorkerekparLayout.createSequentialGroup()
                                    .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMotorkerekparHenger)
                                        .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                                            .addComponent(txtMotorkerekparHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtMotorkerekparHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                                            .addGap(248, 248, 248)
                                            .addComponent(cmbMotorkerekparEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(38, 38, 38)
                                    .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                                            .addComponent(txtMotorkerekparKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtMotorkerekparKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblMotorkerekparKilometer)
                                        .addComponent(cmbMotorkerekparEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlMotorkerekparLayout.setVerticalGroup(
            pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotorkerekparMarka)
                    .addComponent(lblMotorkerekparModell))
                .addGap(18, 18, 18)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMotorkerekparMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotorkerekparModell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMotorkerekparKivitel)
                    .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMotorkerekparAllapot)
                        .addComponent(lblMotorkerekparUzemanyag)))
                .addGap(18, 18, 18)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMotorkerekparKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotorkerekparAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotorkerekparUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotorkerekparRendszam)
                    .addComponent(lblMotorkerekparEvjarat))
                .addGap(18, 18, 18)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotorkerekparRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotorkerekparEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotorkerekparEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotorkerekparHenger)
                    .addComponent(lblMotorkerekparKilometer))
                .addGap(18, 18, 18)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotorkerekparHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotorkerekparHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotorkerekparKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotorkerekparKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(lblMotorkerekparVetelar)
                .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMotorkerekparLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlMotorkerekparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMotorkerekparVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMotorkerekparVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMotorkerekparLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMotorKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        tapJarmuValaszto.addTab("", motorKep, pnlMotorkerekpar);

        pnlHaszonjarmu.setBackground(new java.awt.Color(64, 137, 186));
        pnlHaszonjarmu.setMaximumSize(new java.awt.Dimension(1600, 900));
        pnlHaszonjarmu.setPreferredSize(new java.awt.Dimension(930, 690));

        lblHaszonjarmuMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuMarka.setText("Márka");

        cmbHaszonjarmuMarka.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHaszonjarmuModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuModell.setText("Modell");

        cmbHaszonjarmuModell.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHaszonjarmuEvjarat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuEvjarat.setText("Évjárat(-tól -ig)");

        cmbHaszonjarmuEvjaratTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbHaszonjarmuEvjaratTol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        cmbHaszonjarmuEvjaratIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        cmbHaszonjarmuEvjaratIg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mindegy", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980" }));

        lblHaszonjarmuKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuKivitel.setText("Kivitel");

        cmbHaszonjarmuKivitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHaszonjarmuVetelar.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuVetelar.setText("Vételár(-tól -ig)");

        txtHaszonjarmuVetalarTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuVetalarTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuVetalarTolKeyTyped(evt);
            }
        });

        txtHaszonjarmuVetelarIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuVetelarIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuVetelarIgKeyTyped(evt);
            }
        });

        lblHaszonjarmuUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuUzemanyag.setText("Üzemanyag");

        cmbHaszonjarmuUzemanyag.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHaszonjarmuKilometer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuKilometer.setText("Kilométeróra állása (-tól -ig)");

        txtHaszonjarmuKilometerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuKilometerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuKilometerIgKeyTyped(evt);
            }
        });

        txtHaszonjarmuHengerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuHengerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuHengerTolKeyTyped(evt);
            }
        });

        lblHaszonjarmuHenger.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuHenger.setText("Hengerűrtartalom (-tól -ig)");

        txtHaszonjarmuKilometerTol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuKilometerTol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuKilometerTolKeyTyped(evt);
            }
        });

        txtHaszonjarmuHengerIg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtHaszonjarmuHengerIg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHaszonjarmuHengerIgKeyTyped(evt);
            }
        });

        lblHaszonjarmuRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuRendszam.setText("Rendszám");

        txtHaszonjarmuRendszam.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHaszonjarmuAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHaszonjarmuAllapot.setText("Állapot");

        cmbHaszonjarmuAllapot.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnKamionKereses.setBackground(new java.awt.Color(48, 181, 81));
        btnKamionKereses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnKamionKereses.setText("Keresés...");
        btnKamionKereses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKamionKereses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamionKeresesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHaszonjarmuLayout = new javax.swing.GroupLayout(pnlHaszonjarmu);
        pnlHaszonjarmu.setLayout(pnlHaszonjarmuLayout);
        pnlHaszonjarmuLayout.setHorizontalGroup(
            pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHaszonjarmuLayout.createSequentialGroup()
                        .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                                            .addComponent(lblHaszonjarmuMarka)
                                            .addGap(136, 136, 136))
                                        .addComponent(cmbHaszonjarmuMarka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbHaszonjarmuKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHaszonjarmuRendszam)
                                    .addComponent(txtHaszonjarmuRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHaszonjarmuEvjarat)
                                    .addComponent(lblHaszonjarmuAllapot)
                                    .addComponent(lblHaszonjarmuModell)
                                    .addComponent(cmbHaszonjarmuModell, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbHaszonjarmuAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblHaszonjarmuKivitel))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHaszonjarmuUzemanyag)
                            .addComponent(cmbHaszonjarmuUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(253, 253, 253))
                    .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                        .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHaszonjarmuVetelar)
                            .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlHaszonjarmuLayout.createSequentialGroup()
                                    .addComponent(txtHaszonjarmuVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtHaszonjarmuVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKamionKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlHaszonjarmuLayout.createSequentialGroup()
                                    .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblHaszonjarmuHenger)
                                        .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                                            .addComponent(txtHaszonjarmuHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtHaszonjarmuHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                                            .addGap(248, 248, 248)
                                            .addComponent(cmbHaszonjarmuEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(38, 38, 38)
                                    .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                                            .addComponent(txtHaszonjarmuKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtHaszonjarmuKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblHaszonjarmuKilometer)
                                        .addComponent(cmbHaszonjarmuEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlHaszonjarmuLayout.setVerticalGroup(
            pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHaszonjarmuMarka)
                    .addComponent(lblHaszonjarmuModell))
                .addGap(18, 18, 18)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbHaszonjarmuMarka, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHaszonjarmuModell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHaszonjarmuKivitel)
                    .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHaszonjarmuAllapot)
                        .addComponent(lblHaszonjarmuUzemanyag)))
                .addGap(18, 18, 18)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbHaszonjarmuKivitel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHaszonjarmuAllapot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHaszonjarmuUzemanyag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHaszonjarmuRendszam)
                    .addComponent(lblHaszonjarmuEvjarat))
                .addGap(18, 18, 18)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHaszonjarmuRendszam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHaszonjarmuEvjaratTol, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHaszonjarmuEvjaratIg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHaszonjarmuHenger)
                    .addComponent(lblHaszonjarmuKilometer))
                .addGap(18, 18, 18)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHaszonjarmuHengerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHaszonjarmuHengerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHaszonjarmuKilometerTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHaszonjarmuKilometerIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(lblHaszonjarmuVetelar)
                .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlHaszonjarmuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHaszonjarmuVetalarTol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHaszonjarmuVetelarIg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlHaszonjarmuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKamionKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tapJarmuValaszto.addTab("", kamionKep, pnlHaszonjarmu);

        btnJarmuvekFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnJarmuvekFomenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnJarmuvekFomenu.setText("Főmenü...");
        btnJarmuvekFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnJarmuvekFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJarmuvekFomenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlJarmuKeresesLayout = new javax.swing.GroupLayout(pnlJarmuKereses);
        pnlJarmuKereses.setLayout(pnlJarmuKeresesLayout);
        pnlJarmuKeresesLayout.setHorizontalGroup(
            pnlJarmuKeresesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuKeresesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tapJarmuValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnJarmuvekFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlJarmuKeresesLayout.setVerticalGroup(
            pnlJarmuKeresesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlJarmuKeresesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnJarmuvekFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(330, 330, 330))
            .addGroup(pnlJarmuKeresesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tapJarmuValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pnlFelhasznalo.setBackground(new java.awt.Color(64, 137, 186));
        pnlFelhasznalo.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlFelhasznalo.setMinimumSize(new java.awt.Dimension(700, 500));
        pnlFelhasznalo.setPreferredSize(new java.awt.Dimension(700, 500));

        lblVezeteknev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblVezeteknev.setText("Vezetéknév");

        lblKeresztnev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblKeresztnev.setText("Keresztnév");

        lblRegFelhasznalo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblRegFelhasznalo.setText("Felhasználónév");

        lblRegJelszo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblRegJelszo.setText("Jelszó");

        lblregJelszoUjra.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblregJelszoUjra.setText("Jelszó újra");

        txtVezetek.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtKereszt.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtRegFelhasznalo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtRegJelszo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtregJelszoUjra.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnRegFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnRegFomenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnRegFomenu.setText("Főmenü");
        btnRegFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegFomenuActionPerformed(evt);
            }
        });

        btnRegFelvitel.setBackground(new java.awt.Color(48, 181, 81));
        btnRegFelvitel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnRegFelvitel.setText("Felvitel");
        btnRegFelvitel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegFelvitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegFelvitelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFelhasznaloLayout = new javax.swing.GroupLayout(pnlFelhasznalo);
        pnlFelhasznalo.setLayout(pnlFelhasznaloLayout);
        pnlFelhasznaloLayout.setHorizontalGroup(
            pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFelhasznaloLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFelhasznaloLayout.createSequentialGroup()
                        .addComponent(btnRegFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFelhasznaloLayout.createSequentialGroup()
                        .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVezeteknev)
                            .addComponent(lblKeresztnev)
                            .addComponent(lblRegFelhasznalo)
                            .addComponent(lblRegJelszo)
                            .addComponent(lblregJelszoUjra))
                        .addGap(47, 47, 47)
                        .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtregJelszoUjra, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegJelszo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKereszt, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVezetek, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        pnlFelhasznaloLayout.setVerticalGroup(
            pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFelhasznaloLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVezeteknev)
                    .addComponent(txtVezetek, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKeresztnev)
                    .addComponent(txtKereszt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegFelhasznalo)
                    .addComponent(txtRegFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegJelszo)
                    .addComponent(txtRegJelszo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblregJelszoUjra)
                    .addComponent(txtregJelszoUjra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(pnlFelhasznaloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegFelvitel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pnlSzervizModositas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        pnlSzervizModositas.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlSzervizModositas.setMinimumSize(new java.awt.Dimension(800, 700));
        pnlSzervizModositas.setPreferredSize(new java.awt.Dimension(1024, 768));

        tabSzervizModositValaszto.setBackground(new java.awt.Color(64, 137, 186));
        tabSzervizModositValaszto.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tabSzervizModositValaszto.setMinimumSize(new java.awt.Dimension(850, 700));
        tabSzervizModositValaszto.setPreferredSize(new java.awt.Dimension(895, 730));

        pnlUgyfelModosit.setBackground(new java.awt.Color(64, 137, 186));
        pnlUgyfelModosit.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlUgyfelModosit.setMinimumSize(new java.awt.Dimension(800, 680));
        pnlUgyfelModosit.setPreferredSize(new java.awt.Dimension(930, 690));

        lblUgyfelModositNev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUgyfelModositNev.setText("Név");

        txtUgyfelModositNev.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblUgyfelModositLakcim.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUgyfelModositLakcim.setText("Lakcím");

        txtUgyfelModositLakcim.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblUgyfelModositEler.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUgyfelModositEler.setText("Elérhetőség");

        txtUgyfelModositElerhetoseg.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblUgyfelModositOkmanyTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUgyfelModositOkmanyTipus.setText("Okmány típusa");

        txtUgyfelModositOkmanyTipus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblUgyfelModositOkmanyAzonosito.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblUgyfelModositOkmanyAzonosito.setText("Okmány azonosító");

        txtUgyfelModositOkmanyAzonosito.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnUgyfelModosit.setBackground(new java.awt.Color(48, 181, 81));
        btnUgyfelModosit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnUgyfelModosit.setText("Módosítás");
        btnUgyfelModosit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUgyfelModosit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUgyfelModositActionPerformed(evt);
            }
        });

        scrUgyfel.setAutoscrolls(true);

        tblUgyfel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tblUgyfel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUgyfel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblUgyfel.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblUgyfel.setPreferredSize(new java.awt.Dimension(1500, 120));
        tblUgyfel.setRowHeight(30);
        scrUgyfel.setViewportView(tblUgyfel);

        javax.swing.GroupLayout pnlUgyfelModositLayout = new javax.swing.GroupLayout(pnlUgyfelModosit);
        pnlUgyfelModosit.setLayout(pnlUgyfelModositLayout);
        pnlUgyfelModositLayout.setHorizontalGroup(
            pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUgyfelModositEler, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                        .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrUgyfel)
                            .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUgyfelModositOkmanyTipus)
                                    .addComponent(lblUgyfelModositOkmanyAzonosito))
                                .addGap(61, 61, 61)
                                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUgyfelModositOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUgyfelModositOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(107, 107, 107)))
                        .addGap(18, 18, 18)
                        .addComponent(btnUgyfelModosit))
                    .addComponent(lblUgyfelModositLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                        .addComponent(lblUgyfelModositNev, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUgyfelModositNev, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUgyfelModositElerhetoseg, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUgyfelModositLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlUgyfelModositLayout.setVerticalGroup(
            pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrUgyfel, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUgyfelModosit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUgyfelModositOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelModositOkmanyAzonosito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUgyfelModositOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUgyfelModositOkmanyTipus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUgyfelModositNev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUgyfelModositNev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUgyfelModositLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUgyfelModositLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUgyfelModositLakcim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlUgyfelModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUgyfelModositEler, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUgyfelModositElerhetoseg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        tabSzervizModositValaszto.addTab("Ügyfél módosítása", pnlUgyfelModosit);

        pnlBeszerzesModosit.setBackground(new java.awt.Color(64, 137, 186));
        pnlBeszerzesModosit.setMaximumSize(new java.awt.Dimension(1024, 768));
        pnlBeszerzesModosit.setMinimumSize(new java.awt.Dimension(700, 700));
        pnlBeszerzesModosit.setPreferredSize(new java.awt.Dimension(930, 690));

        lblBeszerzesModositBeszer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesModositBeszer.setText("Beszerzési kód");

        txtBeszerzesModositBeszer.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblBeszerzesModositMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesModositMunkalap.setText("Munkalap szám");

        lblBeszerzesModositAlkatresz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesModositAlkatresz.setText("Alkatrész");

        lblBeszerzesModositAlkAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesModositAlkAr.setText("Alkatrész ára");

        txtBeszerzesModositAlkAr.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblBeszerzesModositMegrend.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBeszerzesModositMegrend.setText("Megrendelés ideje");

        txtBeszerzesModositMegrend.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbBeszerzesModositMunkalap.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        cmbBeszerzesModositAlkatresz.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnBeszerzesModosit.setBackground(new java.awt.Color(48, 181, 81));
        btnBeszerzesModosit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnBeszerzesModosit.setText("Módosítás");
        btnBeszerzesModosit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tblBeszerzes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tblBeszerzes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBeszerzes.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblBeszerzes.setPreferredSize(new java.awt.Dimension(1100, 120));
        tblBeszerzes.setRowHeight(30);
        scrBeszerzes.setViewportView(tblBeszerzes);

        javax.swing.GroupLayout pnlBeszerzesModositLayout = new javax.swing.GroupLayout(pnlBeszerzesModosit);
        pnlBeszerzesModosit.setLayout(pnlBeszerzesModositLayout);
        pnlBeszerzesModositLayout.setHorizontalGroup(
            pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBeszerzesModositLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBeszerzesModositMegrend)
                    .addComponent(lblBeszerzesModositAlkAr)
                    .addComponent(lblBeszerzesModositAlkatresz)
                    .addComponent(lblBeszerzesModositMunkalap)
                    .addComponent(lblBeszerzesModositBeszer))
                .addGap(31, 31, 31)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBeszerzesModositMegrend, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBeszerzesModositMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBeszerzesModositAlkatresz, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBeszerzesModositAlkAr, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBeszerzesModositBeszer, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBeszerzesModositLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrBeszerzes, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBeszerzesModosit))
        );
        pnlBeszerzesModositLayout.setVerticalGroup(
            pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBeszerzesModositLayout.createSequentialGroup()
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBeszerzesModositLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrBeszerzes, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(pnlBeszerzesModositLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(btnBeszerzesModosit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesModositBeszer)
                    .addComponent(txtBeszerzesModositBeszer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesModositMunkalap)
                    .addComponent(cmbBeszerzesModositMunkalap, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesModositAlkatresz)
                    .addComponent(cmbBeszerzesModositAlkatresz, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeszerzesModositAlkAr)
                    .addComponent(txtBeszerzesModositAlkAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlBeszerzesModositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBeszerzesModositMegrend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBeszerzesModositMegrend))
                .addContainerGap())
        );

        tabSzervizModositValaszto.addTab("Beszerzés módosítása", pnlBeszerzesModosit);

        btnSzervizModositFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnSzervizModositFomenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnSzervizModositFomenu.setText("Főmenü");
        btnSzervizModositFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSzervizModositFomenu.setMinimumSize(new java.awt.Dimension(50, 50));
        btnSzervizModositFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSzervizModositFomenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSzervizModositasLayout = new javax.swing.GroupLayout(pnlSzervizModositas);
        pnlSzervizModositas.setLayout(pnlSzervizModositasLayout);
        pnlSzervizModositasLayout.setHorizontalGroup(
            pnlSzervizModositasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSzervizModositasLayout.createSequentialGroup()
                .addComponent(tabSzervizModositValaszto, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSzervizModositFomenu, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        pnlSzervizModositasLayout.setVerticalGroup(
            pnlSzervizModositasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabSzervizModositValaszto, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSzervizModositasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSzervizModositFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );

        pnlTalalat.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));

        btnTalalatFomenu.setBackground(new java.awt.Color(48, 181, 81));
        btnTalalatFomenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnTalalatFomenu.setText("Főmenü...");
        btnTalalatFomenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTalalatFomenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTalalatFomenuActionPerformed(evt);
            }
        });

        pnlKeresett.setBackground(new java.awt.Color(64, 137, 186));

        tblTalalat.setAutoCreateRowSorter(true);
        tblTalalat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tblTalalat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Márka", "Modell", "Rendszám"
            }
        ));
        tblTalalat.setPreferredSize(new java.awt.Dimension(3000, 300));
        tblTalalat.setRowHeight(25);
        scrTalalat.setViewportView(tblTalalat);

        javax.swing.GroupLayout pnlKeresettLayout = new javax.swing.GroupLayout(pnlKeresett);
        pnlKeresett.setLayout(pnlKeresettLayout);
        pnlKeresettLayout.setHorizontalGroup(
            pnlKeresettLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeresettLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(scrTalalat, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlKeresettLayout.setVerticalGroup(
            pnlKeresettLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeresettLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(scrTalalat, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        btnUjJarmuKereses.setBackground(new java.awt.Color(48, 181, 81));
        btnUjJarmuKereses.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnUjJarmuKereses.setText("<html><p align=\"center\">Új jármű<br>keresése...</p></html>");
        btnUjJarmuKereses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUjJarmuKereses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUjJarmuKeresesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTalalatLayout = new javax.swing.GroupLayout(pnlTalalat);
        pnlTalalat.setLayout(pnlTalalatLayout);
        pnlTalalatLayout.setHorizontalGroup(
            pnlTalalatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTalalatLayout.createSequentialGroup()
                .addComponent(pnlKeresett, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlTalalatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTalalatFomenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUjJarmuKereses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlTalalatLayout.setVerticalGroup(
            pnlTalalatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTalalatLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(btnUjJarmuKereses, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTalalatFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlTalalatLayout.createSequentialGroup()
                .addComponent(pnlKeresett, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlKep.setBackground(new java.awt.Color(64, 137, 186));

        btnBetolt.setBackground(new java.awt.Color(48, 181, 81));
        btnBetolt.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnBetolt.setText("Betölt");
        btnBetolt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBetolt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBetoltActionPerformed(evt);
            }
        });

        btnMent.setBackground(new java.awt.Color(48, 181, 81));
        btnMent.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMent.setText("Ment");
        btnMent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMentActionPerformed(evt);
            }
        });

        btnVissza.setBackground(new java.awt.Color(48, 181, 81));
        btnVissza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnVissza.setText("Vissza");
        btnVissza.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVissza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisszaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKepLayout = new javax.swing.GroupLayout(pnlKep);
        pnlKep.setLayout(pnlKepLayout);
        pnlKepLayout.setHorizontalGroup(
            pnlKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKepLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(pnlKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVissza, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMent, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBetolt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        pnlKepLayout.setVerticalGroup(
            pnlKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKepLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnBetolt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnMent, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnVissza, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNezetLayout = new javax.swing.GroupLayout(pnlNezet);
        pnlNezet.setLayout(pnlNezetLayout);
        pnlNezetLayout.setHorizontalGroup(
            pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNezetLayout.createSequentialGroup()
                .addComponent(pnlJarmuKereses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(239, 239, 239)
                    .addComponent(pnlBejelentkezes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(508, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlSzerviz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(165, 165, 165)
                    .addComponent(pnlFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(206, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlJarmuHozzaAdasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlSzervizModositas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlTalalat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 124, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNezetLayout.createSequentialGroup()
                    .addContainerGap(310, Short.MAX_VALUE)
                    .addComponent(pnlKep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(434, Short.MAX_VALUE)))
        );
        pnlNezetLayout.setVerticalGroup(
            pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNezetLayout.createSequentialGroup()
                .addComponent(pnlJarmuKereses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(156, 156, 156)
                    .addComponent(pnlBejelentkezes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(393, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlSzerviz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(pnlFomenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlJarmuHozzaAdasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlFelhasznalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlSzervizModositas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlNezetLayout.createSequentialGroup()
                    .addComponent(pnlTalalat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 18, Short.MAX_VALUE)))
            .addGroup(pnlNezetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNezetLayout.createSequentialGroup()
                    .addContainerGap(219, Short.MAX_VALUE)
                    .addComponent(pnlKep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
        );

        pnlJarmuHozzaAdasa.getAccessibleContext().setAccessibleParent(pnlNezet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlNezet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlNezet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Járművek keresése menüből a főmenübe gomb
    private void btnJarmuvekFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuvekFomenuActionPerformed
        pnlFomenu.setVisible(true);
        pnlJarmuKereses.setVisible(false);
    }//GEN-LAST:event_btnJarmuvekFomenuActionPerformed

    //Szerviz menüből a főmenübe gomb
    private void btnSzervizFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzervizFomenuActionPerformed
        pnlFomenu.setVisible(true);
        pnlSzerviz.setVisible(false);
    }//GEN-LAST:event_btnSzervizFomenuActionPerformed

    //Főmenüben lévő kilépés gomb
    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnKilepesActionPerformed

    //Főmenüből a szerviz menübe gomb
    private void btnSzervizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzervizActionPerformed
        pnlSzerviz.setVisible(true);
        pnlFomenu.setVisible(false);
    }//GEN-LAST:event_btnSzervizActionPerformed

    //Főmenüből a járművek keresése menübe gomb
    private void btnJarmuvekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuvekActionPerformed
        pnlJarmuKereses.setVisible(true);
        pnlFomenu.setVisible(false);
    }//GEN-LAST:event_btnJarmuvekActionPerformed

    //Főmenüből a járművek hozzáadása menübe gomb
    private void btnJarmuHozzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuHozzActionPerformed
        pnlJarmuHozzaAdasa.setVisible(true);
        pnlFomenu.setVisible(false);
    }//GEN-LAST:event_btnJarmuHozzActionPerformed

    //Belépés gomb, bejelentkezés panelből a főmenübe gomb
    private void btnBelepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBelepesActionPerformed
        String jsz;
        jsz = (String.valueOf(pwfJelszo.getPassword()));
        if ((txtFelhasznalo.getText()).equals("")) {
            JOptionPane.showMessageDialog(this, "Adja meg a felhasználónevet!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (jsz.equals("")) {
            JOptionPane.showMessageDialog(this, "Adja meg a keresztnevet!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!vezerlo.belephet()) {
            JOptionPane.showMessageDialog(this, "Rossz felhasználónév vagy jelszó!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            belepes();
        }
    }//GEN-LAST:event_btnBelepesActionPerformed

    //jelszó mező fókusz elveszett
    private void pwfJelszoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfJelszoFocusLost
        String pass = String.valueOf(pwfJelszo.getPassword());

        if (pass.trim().equals("") || pass.trim().toLowerCase().equals("00000000")) {
            pwfJelszo.setText("00000000");
            pwfJelszo.setForeground(new Color(153, 153, 153));
        }
        pwfJelszo.setBorder(null);
    }//GEN-LAST:event_pwfJelszoFocusLost

    //jelszó mező fókusz megtalált
    private void pwfJelszoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfJelszoFocusGained
        String pass = String.valueOf(pwfJelszo.getPassword());

        if (pass.trim().toLowerCase().equals("00000000")) {
            pwfJelszo.setText("");
            pwfJelszo.setForeground(Color.BLACK);
        }
        MatteBorder txtJelszoKeret = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.ORANGE);
        pwfJelszo.setBorder(txtJelszoKeret);
    }//GEN-LAST:event_pwfJelszoFocusGained

    //felhasználónév fókusz elveszett
    private void txtFelhasznaloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFelhasznaloFocusLost
        if (txtFelhasznalo.getText().trim().equals("")
                || txtFelhasznalo.getText().trim().toLowerCase().equals("felhasználónév")) {
            txtFelhasznalo.setText("felhasználónév");
            txtFelhasznalo.setForeground(new Color(153, 153, 153));
        }
        txtFelhasznalo.setBorder(null);
    }//GEN-LAST:event_txtFelhasznaloFocusLost

    //felhasználónév fókusz megtalált
    private void txtFelhasznaloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFelhasznaloFocusGained
        if (txtFelhasznalo.getText().trim().toLowerCase().equals("felhasználónév")) {
            txtFelhasznalo.setText("");
        }
        MatteBorder txtFelhasznaloKeret = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.ORANGE);
        txtFelhasznalo.setBorder(txtFelhasznaloKeret);
    }//GEN-LAST:event_txtFelhasznaloFocusGained

    //Jármű hozzáadása menüből a főmenübe gomb
    private void btnJarmuHozFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuHozFomenuActionPerformed
        pnlJarmuHozzaAdasa.setVisible(false);
        pnlFomenu.setVisible(true);
    }//GEN-LAST:event_btnJarmuHozFomenuActionPerformed

    //Jármű keresése gomb, megnyítja a webböngészőben a találatokat
    private void btnAutoKeresesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoKeresesActionPerformed
        pnlTalalat.setVisible(true);
        pnlJarmuKereses.setVisible(false);
        vezerlo.jarmuKeresesAuto();
    }//GEN-LAST:event_btnAutoKeresesActionPerformed

    //Adott jármű típus alapján beállítja az alapértelmezett kivitel, és üzemanyag típusokat
    private void cmbJarmuTipusaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJarmuTipusaActionPerformed
        String ertek = (String) cmbJarmuTipusa.getSelectedItem();
        DefaultComboBoxModel autoKivitelModel = new DefaultComboBoxModel(autoKivitel);
        DefaultComboBoxModel motorKivitelModel = new DefaultComboBoxModel(motorKivitel);
        DefaultComboBoxModel kamionKivitelModel = new DefaultComboBoxModel(kamionKivitel);
        DefaultComboBoxModel motorUzemanyagModel = new DefaultComboBoxModel(motorUzemanyag);
        DefaultComboBoxModel autoKamionUzemanyagModel
                = new DefaultComboBoxModel(autoKamionUzemanyag);
        switch (ertek) {
            case "Személyautó":
                for (String auto : autoKivitel) {
                    cmbKivitel.setModel(autoKivitelModel);
                }
                for (String auto : autoKamionUzemanyag) {
                    cmbUzemanyag.setModel(autoKamionUzemanyagModel);
                }
                break;
            case "Motorkerékpár":
                for (String motor : motorKivitel) {
                    cmbKivitel.setModel(motorKivitelModel);
                }
                for (String motor : motorUzemanyag) {
                    cmbUzemanyag.setModel(motorUzemanyagModel);
                }
                break;
            case "Haszonjármű":
                for (String kamion : kamionKivitel) {
                    cmbKivitel.setModel(kamionKivitelModel);
                }
                for (String kamion : autoKamionUzemanyag) {
                    cmbUzemanyag.setModel(autoKamionUzemanyagModel);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbJarmuTipusaActionPerformed

    //Főmenüből a felhasználó hozzáadása menübe gomb
    private void btnFelhasznaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFelhasznaloActionPerformed
        pnlFomenu.setVisible(false);
        pnlFelhasznalo.setVisible(true);
    }//GEN-LAST:event_btnFelhasznaloActionPerformed

    //Felhasználó regisztráció menüből a főmenübe gomb
    private void btnRegFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegFomenuActionPerformed
        pnlFomenu.setVisible(true);
        pnlFelhasznalo.setVisible(false);
    }//GEN-LAST:event_btnRegFomenuActionPerformed

    //Felhasználó felvitele gomb
    private void btnRegFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegFelvitelActionPerformed

        if (!vezetekNevValidalas()) {
            JOptionPane.showMessageDialog(this, "A vezetéknév formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!keresztNevValidalas()) {
            JOptionPane.showMessageDialog(this, "A keresztnév formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!regFelhasznaloNevValidalas()) {
            JOptionPane.showMessageDialog(this, "A felhasználónév formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!regJelszoValidalas()) {
            JOptionPane.showMessageDialog(this, "A jelszó formátuma nem megfelelő!"
                    + " Legalább 8 karaktert használjon, és szerepeljenek köztük betűk, és számok!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!regJelszoUjraValidalas()) {
            JOptionPane.showMessageDialog(this, "A jelszó ismétlésének formátuma nem megfelelő! "
                    + "Legalább 8 karaktert használjon, és szerepeljenek köztük betűk, és számok!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!(txtRegJelszo.getText()).equals((txtregJelszoUjra.getText()))) {
            JOptionPane.showMessageDialog(this, "A két jelszó nem egyezik", "Hibaüzenet",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.felhasznaloAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
            txtVezetek.setText("");
            txtKereszt.setText("");
            txtRegFelhasznalo.setText("");
            txtRegJelszo.setText("");
            txtregJelszoUjra.setText("");
        }
    }//GEN-LAST:event_btnRegFelvitelActionPerformed

    //Jármű hozzáadása gomb
    private void btnJarmuHozzaAdasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuHozzaAdasaActionPerformed
        if (!JarmuHozzRendszamValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű rendszámának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzMarkaValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű márkájának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzModellValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű modelljének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzTipusValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű típusának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzLeirasValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű leírásának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzHibaValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű típus hibáinak/hibáinak formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzHengerValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű hengerűrtartalmának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzKilometerValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű kilométeróra állásának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzTeljesitmenyValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű teljesítményének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzArValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű árának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuHozzSajatTomegValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű saját tömegének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (cmbJarmuTipusa.getSelectedItem().toString()) {
                case "Személyautó":
                    vezerlo.jarmuAutoAdatokFeltolt();
                    JOptionPane.showMessageDialog(this, "Új személyautó felvitele sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuHozzaAdasaSzovegesMezokTorlese();
                    break;
                case "Motorkerékpár":
                    vezerlo.jarmuMotorAdatokFeltolt();
                    JOptionPane.showMessageDialog(this, "Új motorkerékpár felvitele sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuHozzaAdasaSzovegesMezokTorlese();
                    break;
                case "Haszonjármű":
                    vezerlo.jarmuKamionAdatokFeltolt();
                    JOptionPane.showMessageDialog(this, "Új haszonjármű felvitele sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuHozzaAdasaSzovegesMezokTorlese();
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnJarmuHozzaAdasaActionPerformed

    //Ügyfél felvitele gomb
    private void btnUgyfelFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUgyfelFelvitelActionPerformed
        if (!SzervizUgyfelNevValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél nevének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelLakcimValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél lakcímének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelElerhetosegValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél elérhetőségének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelOkmanyTipusValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél okmány típusának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelOkmanyAzonValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél okmány azonosítójának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.ugyfelAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnUgyfelFelvitelActionPerformed

    //Jármű módosítása gomb
    private void btnJarmuModositasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuModositasaActionPerformed
        if (!JarmuModositasRendszamValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű rendszámának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasMarkaValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű márkájának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasModellValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű modelljének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasTipusValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű típusának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasLeirasValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű leírásának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasHibaValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű típus hibáinak/hibáinak formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasHengerValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű hengerűrtartalmának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasKilometerValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű kilométeróra állásának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasTeljesitmenyValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű teljesítményének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasArValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű árának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!JarmuModositasSajatTomegValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű saját tömegének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (cmbJarmuTipusaModosit.getSelectedItem().toString()) {
                case "Személyautó":
                    vezerlo.jarmuAutoAdatokModosit();
                    JOptionPane.showMessageDialog(this, "Személyautó módosítása sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositasSzovegesMezokTorlese();
                    break;
                case "Motorkerékpár":
                    vezerlo.jarmuMotorAdatokModosit();
                    JOptionPane.showMessageDialog(this, "Motorkerékpár módosítása sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositasSzovegesMezokTorlese();
                    break;
                case "Haszonjármű":
                    vezerlo.jarmuKamionAdatokModosit();
                    JOptionPane.showMessageDialog(this, "Haszonjármű módosítása sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositasSzovegesMezokTorlese();
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnJarmuModositasaActionPerformed

    //Jármű törlése gomb
    private void btnJarmuTorleseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJarmuTorleseActionPerformed
        if (!JarmuModositasRendszamValidalas()) {
            JOptionPane.showMessageDialog(this, "A jármű rendszámának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (cmbJarmuTipusaModosit.getSelectedItem().toString()) {
                case "Személyautó":
                    vezerlo.jarmuAutoAdatokTorol();
                    JOptionPane.showMessageDialog(this, "Személyautó törlése sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositRendszamSzovegesMezoTorlese();
                    jarmuModositasSzovegesMezokTorlese();
                    break;
                case "Motorkerékpár":
                    vezerlo.jarmuMotorAdatokTorol();
                    JOptionPane.showMessageDialog(this, "Motorkerékpár törlése sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositRendszamSzovegesMezoTorlese();
                    break;
                case "Haszonjármű":
                    vezerlo.jarmuKamionAdatokTorol();
                    JOptionPane.showMessageDialog(this, "Haszonjármű törlése sikeres volt!", "Üzenet",
                            JOptionPane.INFORMATION_MESSAGE);
                    jarmuModositRendszamSzovegesMezoTorlese();
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_btnJarmuTorleseActionPerformed

    //Márka felvitele gomb
    private void btnMarkaFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkaFelvitelActionPerformed
        if (!SzervizMarkaKodValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autómárka kódjának formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: ma/000-999",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizMarkaElnevezesValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autómárka elnevezésének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.markaAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
            txtMarkaMarkKod.setText("");
            txtMarkaElnevezes.setText("");
        }
    }//GEN-LAST:event_btnMarkaFelvitelActionPerformed

    //Autó felvitele gomb
    private void btnAutoFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoFelvitelActionPerformed
        if (!SzervizAutoRendszamValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autó rendszámának formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: XXX-000",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizAutoTipusValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autó típusának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizAutoMotorszamValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autó motorszámának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizAutoAlvazszamValidalas()) {
            JOptionPane.showMessageDialog(this, "Az autó alvázszámának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.autoAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAutoFelvitelActionPerformed

    //Munkalap felvitele gomb
    private void btnMunkFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMunkFelvitelActionPerformed
        if (!SzervizMunkalapSzamValidalas()) {
            JOptionPane.showMessageDialog(this, "A munkalap szám formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: mu/Év/0000-9999",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizMunkalapValHataridoValidalas()) {
            JOptionPane.showMessageDialog(this, "A munkalap vállalási határidő formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: Év-Hó-Nap Óra:Perc:Másodperc",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizMunkalapHibaValidalas()) {
            JOptionPane.showMessageDialog(this, "A munkalap hiba jegyzék formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.munkalapAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnMunkFelvitelActionPerformed

    //Feladat felvitele gomb
    private void btnFeladatFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeladatFelvitelActionPerformed
        if (!SzervizFeladatValidalas()) {
            JOptionPane.showMessageDialog(this, "A feladat kód formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: fe/000-999",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizFeladatMunkaDijValidalas()) {
            JOptionPane.showMessageDialog(this, "A feladat munka díjának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizFeladatElnevezesValidalas()) {
            JOptionPane.showMessageDialog(this, "A feladat elnevezésének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.feladatAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnFeladatFelvitelActionPerformed

    //Szerelés felvitele gomb
    private void btnSzerelesFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzerelesFelvitelActionPerformed
        if (!SzervizSzerelesKezdesIdoValidalas()) {
            JOptionPane.showMessageDialog(this, "A szerelés kezdés idejének formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: Év-Hó-Nap",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizSzerelesBefejezesIdoValidalas() && ((Integer.parseInt(txtSzerelesBefIdo.getText())) > (Integer.parseInt(txtSzerelesKezdIdo.getText())))) {
            JOptionPane.showMessageDialog(this, "A szerelés befejezés idejének formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: Év-Hó-Nap",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizSzerelesMunkaOraValidalas()) {
            JOptionPane.showMessageDialog(this, "A szerelés munka órájának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.szerelesAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSzerelesFelvitelActionPerformed

    //Alkatrész felvitele gomb
    private void btnAlkatreszFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlkatreszFelvitelActionPerformed
        if (!SzervizAlkatreszKodValidalas()) {
            JOptionPane.showMessageDialog(this, "Az alkatrész kódjának formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: al/000-999",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizAlkatreszMegnevezesValidalas()) {
            JOptionPane.showMessageDialog(this, "Az alkatrész megnevezésének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.alkatreszAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
            txtAlkatreszKod.setText("");
            txtAlkatreszMegnevezes.setText("");
        }
    }//GEN-LAST:event_btnAlkatreszFelvitelActionPerformed

    //Beszerzés felvitele gomb
    private void btnBeszerzesFelvitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeszerzesFelvitelActionPerformed
        if (!SzervizBeszerzesKodValidalas()) {
            JOptionPane.showMessageDialog(this, "A beszerzési kód formátuma nem megfelelő!"
                    + "\nA helyes formátum a következő: be/Év/0000-9999",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizBeszerzesAlkatreszArValidalas()) {
            JOptionPane.showMessageDialog(this, "Az alkatrész árának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizBeszerzesMegrendelesValidalas()) {
            JOptionPane.showMessageDialog(this, "Az alkatrész megrendelés idejének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.beszerzesAdatokFeltolt();
            JOptionPane.showMessageDialog(this, "Sikeres adatfelvitel", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBeszerzesFelvitelActionPerformed

    //Hengerűrtartalom mező validálás
    private void txtSzemelyautoHengerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoHengerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoHengerTolKeyTyped

    //Személyautó Hengerűrtartalom mező validálás
    private void txtSzemelyautoHengerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoHengerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoHengerIgKeyTyped

    //Személyautó Kilométeróra mező validálás
    private void txtSzemelyautoKilometerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoKilometerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoKilometerTolKeyTyped

    //Személyautó Kilométeróra mező validálás
    private void txtSzemelyautoKilometerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoKilometerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoKilometerIgKeyTyped

    //Személyautó Vételár mező validálás
    private void txtSzemelyautoVetalarTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoVetalarTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoVetalarTolKeyTyped

    //Személyautó Vételár mező validálás
    private void txtSzemelyautoVetelarIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSzemelyautoVetelarIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtSzemelyautoVetelarIgKeyTyped

    //Motorkerékpár Hengerűrtartalom mező validálás
    private void txtMotorkerekparHengerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparHengerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparHengerTolKeyTyped

    //Motorkerékpár Hengerűrtartalom mező validálás
    private void txtMotorkerekparHengerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparHengerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparHengerIgKeyTyped

    //Motorkerékpár Kilométeróra mező validálás
    private void txtMotorkerekparKilometerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparKilometerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparKilometerTolKeyTyped

    //Motorkerékpár Kilométeróra mező validálás
    private void txtMotorkerekparKilometerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparKilometerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparKilometerIgKeyTyped

    //Motorkerékpár Vételár mező validálás
    private void txtMotorkerekparVetalarTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparVetalarTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparVetalarTolKeyTyped

    //Motorkerékpár Vételár mező validálás
    private void txtMotorkerekparVetelarIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotorkerekparVetelarIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotorkerekparVetelarIgKeyTyped

    //Haszonjármű Hengerűrtartalom mező validálás
    private void txtHaszonjarmuHengerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuHengerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuHengerTolKeyTyped

    //Haszonjármű Hengerűrtartalom mező validálás
    private void txtHaszonjarmuHengerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuHengerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuHengerIgKeyTyped

    //Haszonjármű Kilométeróra mező validálás
    private void txtHaszonjarmuKilometerTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuKilometerTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuKilometerTolKeyTyped

    //Haszonjármű Kilométeróra mező validálás
    private void txtHaszonjarmuKilometerIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuKilometerIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuKilometerIgKeyTyped

    //Haszonjármű Vételár mező validálás
    private void txtHaszonjarmuVetalarTolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuVetalarTolKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuVetalarTolKeyTyped

    //Haszonjármű Vételár mező validálás
    private void txtHaszonjarmuVetelarIgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHaszonjarmuVetelarIgKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtHaszonjarmuVetelarIgKeyTyped

    //Munkalapot generál PDF- formátumba
    private void btnMunkalapKeszitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMunkalapKeszitActionPerformed
        //vezerlo.setSaveFile(evt);
        vezerlo.pdfGeneral();
    }//GEN-LAST:event_btnMunkalapKeszitActionPerformed

    //Kép hozzáadása panel megnyítása gomb
    private void btnKepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKepActionPerformed
        pnlKep.setVisible(true);
        pnlJarmuHozzaAdasa.setVisible(false);
    }//GEN-LAST:event_btnKepActionPerformed

    //Jármű hozzáadása teljesítmény szöveges mező valídálás
    private void txtTeljesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeljesKeyTyped
        char input = evt.getKeyChar();
        if ((input < '0' || input > '9') && input != '\b') {
            evt.consume();
        }
    }//GEN-LAST:event_txtTeljesKeyTyped

    //Teljesitmény átváltása
    private void txtTeljesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeljesKeyReleased
        loEroBeallit();
    }//GEN-LAST:event_txtTeljesKeyReleased

    //Teljesítmény fókusz elveszett
    private void txtTeljesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTeljesFocusLost
        lblLoero.setText("");
    }//GEN-LAST:event_txtTeljesFocusLost

    //Teljesítmény fókusz megtalált
    private void txtTeljesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTeljesFocusGained
        loEroBeallit();
    }//GEN-LAST:event_txtTeljesFocusGained

    //Ügyfél módosít gomb
    private void btnUgyfelModositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUgyfelModositActionPerformed

        if (!SzervizUgyfelModositOkmanyAzonValidalas()) {

            JOptionPane.showMessageDialog(this, "Az ügyfél okmány azonosítójának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelModositOkmanyTipusValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél okmány típusának formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelModositNevValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél nevének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelModositElerhetosegValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél elérhetőségének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else if (!SzervizUgyfelModositLakcimValidalas()) {
            JOptionPane.showMessageDialog(this, "Az ügyfél lakcímének formátuma nem megfelelő!",
                    "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
        } else {
            vezerlo.ugyfelAdatokModosit();
            JOptionPane.showMessageDialog(this, "Sikeres módosítás!", "Üzenet",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        txtUgyfelModositElerhetoseg.setText("");
        txtUgyfelModositLakcim.setText("");
        txtUgyfelModositNev.setText("");
        txtUgyfelModositOkmanyAzonosito.setText("");
        txtUgyfelModositOkmanyTipus.setText("");
    }//GEN-LAST:event_btnUgyfelModositActionPerformed

    //Szerviz módosítás menüből a főmenübe gomb
    private void btnSzervizModositFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzervizModositFomenuActionPerformed
        pnlFomenu.setVisible(true);
        pnlSzervizModositas.setVisible(false);
    }//GEN-LAST:event_btnSzervizModositFomenuActionPerformed

    //Szerviz módosítás menübe a főmenüből gomb
    private void btnSzervizModositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSzervizModositActionPerformed
        pnlSzervizModositas.setVisible(true);
        pnlFomenu.setVisible(false);
    }//GEN-LAST:event_btnSzervizModositActionPerformed

    //Jármű találat menüből a főmenübe gomb
    private void btnTalalatFomenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTalalatFomenuActionPerformed
        pnlFomenu.setVisible(true);
        pnlTalalat.setVisible(false);
    }//GEN-LAST:event_btnTalalatFomenuActionPerformed

    //Találat menüből a jármű keresés menübe gomb
    private void btnUjJarmuKeresesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUjJarmuKeresesActionPerformed
        pnlJarmuKereses.setVisible(true);
        pnlTalalat.setVisible(false);
    }//GEN-LAST:event_btnUjJarmuKeresesActionPerformed

    //Találat menübe a motorkerékpár keresése panelről
    private void btnMotorKeresesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotorKeresesActionPerformed
        pnlTalalat.setVisible(true);
        pnlJarmuKereses.setVisible(false);
        vezerlo.jarmuKeresesMotor();
    }//GEN-LAST:event_btnMotorKeresesActionPerformed

    //Találat menübe a haszonjármű keresése panelről
    private void btnKamionKeresesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamionKeresesActionPerformed
        pnlTalalat.setVisible(true);
        pnlJarmuKereses.setVisible(false);
        vezerlo.jarmuKeresesKamion();
    }//GEN-LAST:event_btnKamionKeresesActionPerformed

    //Jármű módosítása menüben járműválasztó combobox művelet
    private void cmbJarmuTipusaModositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJarmuTipusaModositActionPerformed
        String ertek = (String) cmbJarmuTipusaModosit.getSelectedItem();
        DefaultComboBoxModel autoKivitelModel = new DefaultComboBoxModel(autoKivitel);
        DefaultComboBoxModel motorKivitelModel = new DefaultComboBoxModel(motorKivitel);
        DefaultComboBoxModel kamionKivitelModel = new DefaultComboBoxModel(kamionKivitel);
        DefaultComboBoxModel motorUzemanyagModel = new DefaultComboBoxModel(motorUzemanyag);
        DefaultComboBoxModel autoKamionUzemanyagModel
                = new DefaultComboBoxModel(autoKamionUzemanyag);
        switch (ertek) {
            case "Személyautó":
                for (String auto : autoKivitel) {
                    cmbModositKivitel.setModel(autoKivitelModel);
                }
                for (String auto : autoKamionUzemanyag) {
                    cmbModositUzemanyag.setModel(autoKamionUzemanyagModel);
                }
                break;
            case "Motorkerékpár":
                for (String motor : motorKivitel) {
                    cmbModositKivitel.setModel(motorKivitelModel);
                }
                for (String motor : motorUzemanyag) {
                    cmbModositUzemanyag.setModel(motorUzemanyagModel);
                }
                break;
            case "Haszonjármű":
                for (String kamion : kamionKivitel) {
                    cmbModositKivitel.setModel(kamionKivitelModel);
                }
                for (String kamion : autoKamionUzemanyag) {
                    cmbModositUzemanyag.setModel(autoKamionUzemanyagModel);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbJarmuTipusaModositActionPerformed

    //Jármű módosítása menüben járműválasztó combobox művelet
    private void cmbJarmuTipusaModositItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJarmuTipusaModositItemStateChanged
        String ertek = (String) cmbJarmuTipusaModosit.getSelectedItem();
        switch (ertek) {
            case "Személyautó":
                vezerlo.setAutoAdatokTablazat();
                break;
            case "Motorkerékpár":
                vezerlo.setMotorAdatokTablazat();
                break;
            case "Haszonjármű":
                vezerlo.setKamionAdatokTablazat();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbJarmuTipusaModositItemStateChanged

    //Új jármű keresése gomb
    private void btnVisszaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisszaActionPerformed
        pnlJarmuHozzaAdasa.setVisible(true);
        pnlKep.setVisible(false);
    }//GEN-LAST:event_btnVisszaActionPerformed

    //Kép mentlse gomb
    private void btnMentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMentActionPerformed
        kepMent();
    }//GEN-LAST:event_btnMentActionPerformed

    //Kép betöltése gomb
    private void btnBetoltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBetoltActionPerformed
        kepBetolt();
    }//GEN-LAST:event_btnBetoltActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlkatreszFelvitel;
    private javax.swing.JButton btnAutoFelvitel;
    private javax.swing.JButton btnAutoKereses;
    private javax.swing.JButton btnBelepes;
    private javax.swing.JButton btnBeszerzesFelvitel;
    private javax.swing.JButton btnBeszerzesModosit;
    private javax.swing.JButton btnBetolt;
    private javax.swing.JButton btnFeladatFelvitel;
    private javax.swing.JButton btnFelhasznalo;
    private javax.swing.JButton btnJarmuHozFomenu;
    private javax.swing.JButton btnJarmuHozz;
    private javax.swing.JButton btnJarmuHozzaAdasa;
    private javax.swing.JButton btnJarmuModositasa;
    private javax.swing.JButton btnJarmuTorlese;
    private javax.swing.JButton btnJarmuvek;
    private javax.swing.JButton btnJarmuvekFomenu;
    private javax.swing.JButton btnKamionKereses;
    private javax.swing.JButton btnKep;
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnMarkaFelvitel;
    private javax.swing.JButton btnMent;
    private javax.swing.JButton btnMotorKereses;
    private javax.swing.JButton btnMunkFelvitel;
    private javax.swing.JButton btnMunkalapKeszit;
    private javax.swing.JButton btnRegFelvitel;
    private javax.swing.JButton btnRegFomenu;
    private javax.swing.JButton btnSzerelesFelvitel;
    private javax.swing.JButton btnSzerviz;
    private javax.swing.JButton btnSzervizFomenu;
    private javax.swing.JButton btnSzervizModosit;
    private javax.swing.JButton btnSzervizModositFomenu;
    private javax.swing.JButton btnTalalatFomenu;
    private javax.swing.JButton btnUgyfelFelvitel;
    private javax.swing.JButton btnUgyfelModosit;
    private javax.swing.JButton btnUjJarmuKereses;
    private javax.swing.JButton btnVissza;
    private javax.swing.JCheckBox chkModositTelephely;
    private javax.swing.JCheckBox chkTelephely;
    private javax.swing.JComboBox<String> cmbAllapot;
    private javax.swing.JComboBox<String> cmbAutoEvjarat;
    private javax.swing.JComboBox<String> cmbAutoMarka;
    private javax.swing.JComboBox<String> cmbAutoSzin;
    private javax.swing.JComboBox<String> cmbAutoUgyfel;
    private javax.swing.JComboBox<String> cmbBeszerzesAlkatresz;
    private javax.swing.JComboBox<String> cmbBeszerzesModositAlkatresz;
    private javax.swing.JComboBox<String> cmbBeszerzesModositMunkalap;
    private javax.swing.JComboBox<String> cmbBeszerzesMunkalap;
    private javax.swing.JComboBox<String> cmbEvjarat;
    private javax.swing.JComboBox<String> cmbFeladatTipus;
    private javax.swing.JComboBox<String> cmbHaszonjarmuAllapot;
    private javax.swing.JComboBox<String> cmbHaszonjarmuEvjaratIg;
    private javax.swing.JComboBox<String> cmbHaszonjarmuEvjaratTol;
    private javax.swing.JComboBox<String> cmbHaszonjarmuKivitel;
    private javax.swing.JComboBox<String> cmbHaszonjarmuMarka;
    private javax.swing.JComboBox<String> cmbHaszonjarmuModell;
    private javax.swing.JComboBox<String> cmbHaszonjarmuUzemanyag;
    private javax.swing.JComboBox<String> cmbHonap;
    private javax.swing.JComboBox<String> cmbJarmuTipusa;
    private javax.swing.JComboBox<String> cmbJarmuTipusaModosit;
    private javax.swing.JComboBox<String> cmbKivitel;
    private javax.swing.JComboBox<String> cmbModositAllapot;
    private javax.swing.JComboBox<String> cmbModositEvjarat;
    private javax.swing.JComboBox<String> cmbModositHonap;
    private javax.swing.JComboBox<String> cmbModositKivitel;
    private javax.swing.JComboBox<String> cmbModositSebess;
    private javax.swing.JComboBox<String> cmbModositSzin;
    private javax.swing.JComboBox<String> cmbModositUzemanyag;
    private javax.swing.JComboBox<String> cmbMotorkerekparAllapot;
    private javax.swing.JComboBox<String> cmbMotorkerekparEvjaratIg;
    private javax.swing.JComboBox<String> cmbMotorkerekparEvjaratTol;
    private javax.swing.JComboBox<String> cmbMotorkerekparKivitel;
    private javax.swing.JComboBox<String> cmbMotorkerekparMarka;
    private javax.swing.JComboBox<String> cmbMotorkerekparModell;
    private javax.swing.JComboBox<String> cmbMotorkerekparUzemanyag;
    private javax.swing.JComboBox<String> cmbMunkRendszam;
    private javax.swing.JComboBox<String> cmbSebess;
    private javax.swing.JComboBox<String> cmbSzemelyautoAllapot;
    private javax.swing.JComboBox<String> cmbSzemelyautoEvjaratIg;
    private javax.swing.JComboBox<String> cmbSzemelyautoEvjaratTol;
    private javax.swing.JComboBox<String> cmbSzemelyautoKivitel;
    private javax.swing.JComboBox<String> cmbSzemelyautoMarka;
    private javax.swing.JComboBox<String> cmbSzemelyautoModell;
    private javax.swing.JComboBox<String> cmbSzemelyautoUzemanyag;
    private javax.swing.JComboBox<String> cmbSzerelesFeladat;
    private javax.swing.JComboBox<String> cmbSzerelesMunkalap;
    private javax.swing.JComboBox<String> cmbSzerelesSzerelo;
    private javax.swing.JComboBox<String> cmbSzin;
    private javax.swing.JComboBox<String> cmbUgyfelKedvezmeny;
    private javax.swing.JComboBox<String> cmbUzemanyag;
    private javax.swing.JLabel lblAlkatreszKod;
    private javax.swing.JLabel lblAlkatreszMegnevezes;
    private javax.swing.JLabel lblAllapot;
    private javax.swing.JLabel lblAr;
    private javax.swing.JLabel lblArEgyseg;
    private javax.swing.JLabel lblAutoASzam;
    private javax.swing.JLabel lblAutoEvjarat;
    private javax.swing.JLabel lblAutoMSzam;
    private javax.swing.JLabel lblAutoMarka;
    private javax.swing.JLabel lblAutoMarkaMegjelenit;
    private javax.swing.JLabel lblAutoRendszam;
    private javax.swing.JLabel lblAutoSzin;
    private javax.swing.JLabel lblAutoTipus;
    private javax.swing.JLabel lblAutoUgyfSzam;
    private javax.swing.JLabel lblAutoUgyfelMegjelenit;
    private javax.swing.JLabel lblBeszerzesAlkAr;
    private javax.swing.JLabel lblBeszerzesAlkatresz;
    private javax.swing.JLabel lblBeszerzesAlkatreszMegnevezes;
    private javax.swing.JLabel lblBeszerzesBeszer;
    private javax.swing.JLabel lblBeszerzesMegrend;
    private javax.swing.JLabel lblBeszerzesModositAlkAr;
    private javax.swing.JLabel lblBeszerzesModositAlkatresz;
    private javax.swing.JLabel lblBeszerzesModositBeszer;
    private javax.swing.JLabel lblBeszerzesModositMegrend;
    private javax.swing.JLabel lblBeszerzesModositMunkalap;
    private javax.swing.JLabel lblBeszerzesMunkalap;
    private javax.swing.JLabel lblEler;
    private javax.swing.JLabel lblEvjarat;
    private javax.swing.JLabel lblFeladatElnevezes;
    private javax.swing.JLabel lblFeladatFelad;
    private javax.swing.JLabel lblFeladatMDij;
    private javax.swing.JLabel lblFeladatTipus;
    private javax.swing.JLabel lblFelhasznalo;
    private javax.swing.JLabel lblHaszonjarmuAllapot;
    private javax.swing.JLabel lblHaszonjarmuEvjarat;
    private javax.swing.JLabel lblHaszonjarmuHenger;
    private javax.swing.JLabel lblHaszonjarmuKilometer;
    private javax.swing.JLabel lblHaszonjarmuKivitel;
    private javax.swing.JLabel lblHaszonjarmuMarka;
    private javax.swing.JLabel lblHaszonjarmuModell;
    private javax.swing.JLabel lblHaszonjarmuRendszam;
    private javax.swing.JLabel lblHaszonjarmuUzemanyag;
    private javax.swing.JLabel lblHaszonjarmuVetelar;
    private javax.swing.JLabel lblHenger;
    private javax.swing.JLabel lblHengerEgyseg;
    private javax.swing.JLabel lblHiba;
    private javax.swing.JLabel lblHonap;
    private javax.swing.JLabel lblIdo;
    public javax.swing.JLabel lblIdoKijelzes;
    private javax.swing.JLabel lblJarmuTipusa;
    private javax.swing.JLabel lblJelszo;
    private javax.swing.JLabel lblKedvez;
    private javax.swing.JLabel lblKeresztnev;
    private javax.swing.JLabel lblKilom;
    private javax.swing.JLabel lblKilometerEgyseg;
    private javax.swing.JLabel lblKivitel;
    private javax.swing.JLabel lblLakcim;
    private javax.swing.JLabel lblLeiras;
    private javax.swing.JLabel lblLoero;
    private javax.swing.JLabel lblLoeroEgyseg;
    private javax.swing.JLabel lblMarka;
    private javax.swing.JLabel lblMarkaElnevezes;
    private javax.swing.JLabel lblMarkaMarkKod;
    private javax.swing.JLabel lblModell;
    private javax.swing.JLabel lblModositAllapot;
    private javax.swing.JLabel lblModositAr;
    private javax.swing.JLabel lblModositEvjarat;
    private javax.swing.JLabel lblModositHenger;
    private javax.swing.JLabel lblModositHiba;
    private javax.swing.JLabel lblModositHonap;
    private javax.swing.JLabel lblModositKilom;
    private javax.swing.JLabel lblModositKivitel;
    private javax.swing.JLabel lblModositLeiras;
    private javax.swing.JLabel lblModositMarka;
    private javax.swing.JLabel lblModositModell;
    private javax.swing.JLabel lblModositRendszam;
    private javax.swing.JLabel lblModositSajat;
    private javax.swing.JLabel lblModositSebess;
    private javax.swing.JLabel lblModositSzin;
    private javax.swing.JLabel lblModositTeljes;
    private javax.swing.JLabel lblModositTipus;
    private javax.swing.JLabel lblModositUzemanyag;
    private javax.swing.JLabel lblMotorkerekparAllapot;
    private javax.swing.JLabel lblMotorkerekparEvjarat;
    private javax.swing.JLabel lblMotorkerekparHenger;
    private javax.swing.JLabel lblMotorkerekparKilometer;
    private javax.swing.JLabel lblMotorkerekparKivitel;
    private javax.swing.JLabel lblMotorkerekparMarka;
    private javax.swing.JLabel lblMotorkerekparModell;
    private javax.swing.JLabel lblMotorkerekparRendszam;
    private javax.swing.JLabel lblMotorkerekparUzemanyag;
    private javax.swing.JLabel lblMotorkerekparVetelar;
    private javax.swing.JLabel lblMunkHatarido;
    private javax.swing.JLabel lblMunkHiba;
    private javax.swing.JLabel lblMunkMunkalap;
    private javax.swing.JLabel lblMunkRendszam;
    private javax.swing.JLabel lblNev;
    private javax.swing.JLabel lblOkmanyAzonosito;
    private javax.swing.JLabel lblOkmanyTipus;
    private javax.swing.JLabel lblRegFelhasznalo;
    private javax.swing.JLabel lblRegJelszo;
    private javax.swing.JLabel lblRendszam;
    private javax.swing.JLabel lblSajat;
    private javax.swing.JLabel lblSajatEgyseg;
    private javax.swing.JLabel lblSebess;
    private javax.swing.JLabel lblSzemelyautoAllapot;
    private javax.swing.JLabel lblSzemelyautoEvjarat;
    private javax.swing.JLabel lblSzemelyautoHenger;
    private javax.swing.JLabel lblSzemelyautoKilometer;
    private javax.swing.JLabel lblSzemelyautoKivitel;
    private javax.swing.JLabel lblSzemelyautoMarka;
    private javax.swing.JLabel lblSzemelyautoModell;
    private javax.swing.JLabel lblSzemelyautoRendszam;
    private javax.swing.JLabel lblSzemelyautoUzemanyag;
    private javax.swing.JLabel lblSzemelyautoVetelar;
    private javax.swing.JLabel lblSzerelesBefIdo;
    private javax.swing.JLabel lblSzerelesFeladat;
    private javax.swing.JLabel lblSzerelesFeladatMegnevezes;
    private javax.swing.JLabel lblSzerelesKezdIdo;
    private javax.swing.JLabel lblSzerelesMOra;
    private javax.swing.JLabel lblSzerelesMunkalap;
    private javax.swing.JLabel lblSzerelesSzerelo;
    private javax.swing.JLabel lblSzerelesSzereloNev;
    private javax.swing.JLabel lblSzin;
    private javax.swing.JLabel lblTeljes;
    private javax.swing.JLabel lblTeljesEgyseg;
    private javax.swing.JLabel lblTipus;
    private javax.swing.JLabel lblUgyfelModositEler;
    private javax.swing.JLabel lblUgyfelModositLakcim;
    private javax.swing.JLabel lblUgyfelModositNev;
    private javax.swing.JLabel lblUgyfelModositOkmanyAzonosito;
    private javax.swing.JLabel lblUgyfelModositOkmanyTipus;
    private javax.swing.JLabel lblUzemanyag;
    private javax.swing.JLabel lblVezeteknev;
    private javax.swing.JLabel lblregJelszoUjra;
    private javax.swing.JPanel pnlAlkatresz;
    private javax.swing.JPanel pnlAuto;
    private static javax.swing.JPanel pnlBejelentkezes;
    private javax.swing.JPanel pnlBeszerzes;
    private javax.swing.JPanel pnlBeszerzesModosit;
    private javax.swing.JPanel pnlFeladat;
    private javax.swing.JPanel pnlFelhasznalo;
    private static javax.swing.JPanel pnlFomenu;
    private javax.swing.JPanel pnlHaszonjarmu;
    private javax.swing.JPanel pnlJarmuHozzElso;
    private javax.swing.JPanel pnlJarmuHozzMasodik;
    private static javax.swing.JPanel pnlJarmuHozzaAdasa;
    private static javax.swing.JPanel pnlJarmuKereses;
    private javax.swing.JPanel pnlJarmuModositAdatok;
    private javax.swing.JPanel pnlJarmuModositAdatokElso;
    private javax.swing.JPanel pnlJarmuModositAdatokHarmadik;
    private javax.swing.JPanel pnlJarmuModositAdatokMasodik;
    private javax.swing.JPanel pnlJarmuvekMegjelenites;
    private javax.swing.JPanel pnlKep;
    private javax.swing.JPanel pnlKeresett;
    private javax.swing.JPanel pnlMarka;
    private javax.swing.JPanel pnlMotorkerekpar;
    private javax.swing.JPanel pnlMunkalap;
    private javax.swing.JPanel pnlNezet;
    private javax.swing.JPanel pnlSzemelyauto;
    private javax.swing.JPanel pnlSzereles;
    private static javax.swing.JPanel pnlSzerviz;
    private javax.swing.JPanel pnlSzervizModositas;
    private javax.swing.JPanel pnlTalalat;
    private javax.swing.JPanel pnlUgyfel;
    private javax.swing.JPanel pnlUgyfelModosit;
    private javax.swing.JPasswordField pwfJelszo;
    private javax.swing.JScrollPane scrBeszerzes;
    private javax.swing.JScrollPane scrFeladatGorg;
    private javax.swing.JScrollPane scrHibaJegyzek;
    private javax.swing.JScrollPane scrHibak;
    private javax.swing.JScrollPane scrLeiras;
    private javax.swing.JScrollPane scrTablazat;
    private javax.swing.JScrollPane scrTalalat;
    private javax.swing.JScrollPane scrUgyfel;
    private javax.swing.JTabbedPane tabJarmuHozzValaszto;
    private javax.swing.JTabbedPane tabJarmuModositAdatok;
    private javax.swing.JTabbedPane tabSzervizModositValaszto;
    private javax.swing.JTabbedPane tabSzervizValaszto;
    private javax.swing.JTabbedPane tapJarmuValaszto;
    private javax.swing.JTable tblBeszerzes;
    private javax.swing.JTable tblJarmuModosit;
    private javax.swing.JTable tblTalalat;
    private javax.swing.JTable tblUgyfel;
    private javax.swing.JTextArea txaFeladatElnevezes;
    private javax.swing.JTextArea txaHibak;
    private javax.swing.JTextArea txaLeiras;
    private javax.swing.JTextArea txaMunkHibaJegyzek;
    private javax.swing.JTextField txtAlkatreszKod;
    private javax.swing.JTextField txtAlkatreszMegnevezes;
    private javax.swing.JTextField txtAr;
    private javax.swing.JTextField txtAutoASzam;
    private javax.swing.JTextField txtAutoMSzam;
    private javax.swing.JTextField txtAutoRendszam;
    private javax.swing.JTextField txtAutoTipus;
    private javax.swing.JTextField txtBeszerzesAlkAr;
    private javax.swing.JTextField txtBeszerzesBeszer;
    private javax.swing.JTextField txtBeszerzesMegrend;
    private javax.swing.JTextField txtBeszerzesModositAlkAr;
    private javax.swing.JTextField txtBeszerzesModositBeszer;
    private javax.swing.JTextField txtBeszerzesModositMegrend;
    private javax.swing.JTextField txtFeladatFelad;
    private javax.swing.JTextField txtFeladatMDij;
    private javax.swing.JTextField txtFelhasznalo;
    private javax.swing.JTextField txtHaszonjarmuHengerIg;
    private javax.swing.JTextField txtHaszonjarmuHengerTol;
    private javax.swing.JTextField txtHaszonjarmuKilometerIg;
    private javax.swing.JTextField txtHaszonjarmuKilometerTol;
    private javax.swing.JTextField txtHaszonjarmuRendszam;
    private javax.swing.JTextField txtHaszonjarmuVetalarTol;
    private javax.swing.JTextField txtHaszonjarmuVetelarIg;
    private javax.swing.JTextField txtHenger;
    private javax.swing.JTextField txtKereszt;
    private javax.swing.JTextField txtKilom;
    private javax.swing.JTextField txtMarka;
    private javax.swing.JTextField txtMarkaElnevezes;
    private javax.swing.JTextField txtMarkaMarkKod;
    private javax.swing.JTextField txtModell;
    private javax.swing.JTextField txtModositAr;
    private javax.swing.JTextField txtModositHenger;
    private javax.swing.JTextField txtModositHiba;
    private javax.swing.JTextField txtModositKilometer;
    private javax.swing.JTextField txtModositLeiras;
    private javax.swing.JTextField txtModositMarka;
    private javax.swing.JTextField txtModositModell;
    private javax.swing.JTextField txtModositRendszam;
    private javax.swing.JTextField txtModositSajat;
    private javax.swing.JTextField txtModositTeljesitmeny;
    private javax.swing.JTextField txtModositTipus;
    private javax.swing.JTextField txtMotorkerekparHengerIg;
    private javax.swing.JTextField txtMotorkerekparHengerTol;
    private javax.swing.JTextField txtMotorkerekparKilometerIg;
    private javax.swing.JTextField txtMotorkerekparKilometerTol;
    private javax.swing.JTextField txtMotorkerekparRendszam;
    private javax.swing.JTextField txtMotorkerekparVetalarTol;
    private javax.swing.JTextField txtMotorkerekparVetelarIg;
    private javax.swing.JTextField txtMunkHatarido;
    private javax.swing.JTextField txtMunkMunkalap;
    private javax.swing.JTextField txtRegFelhasznalo;
    private javax.swing.JTextField txtRegJelszo;
    private javax.swing.JTextField txtRendszam;
    private javax.swing.JTextField txtSajat;
    private javax.swing.JTextField txtSzemelyautoHengerIg;
    private javax.swing.JTextField txtSzemelyautoHengerTol;
    private javax.swing.JTextField txtSzemelyautoKilometerIg;
    private javax.swing.JTextField txtSzemelyautoKilometerTol;
    private javax.swing.JTextField txtSzemelyautoRendszam;
    private javax.swing.JTextField txtSzemelyautoVetalarTol;
    private javax.swing.JTextField txtSzemelyautoVetelarIg;
    private javax.swing.JTextField txtSzerelesBefIdo;
    private javax.swing.JTextField txtSzerelesKezdIdo;
    private javax.swing.JTextField txtSzerelesMOra;
    private javax.swing.JTextField txtTeljes;
    private javax.swing.JTextField txtTipus;
    private javax.swing.JTextField txtUgyfelElerhetoseg;
    private javax.swing.JTextField txtUgyfelLakcim;
    private javax.swing.JTextField txtUgyfelModositElerhetoseg;
    private javax.swing.JTextField txtUgyfelModositLakcim;
    private javax.swing.JTextField txtUgyfelModositNev;
    private javax.swing.JTextField txtUgyfelModositOkmanyAzonosito;
    private javax.swing.JTextField txtUgyfelModositOkmanyTipus;
    private javax.swing.JTextField txtUgyfelNev;
    private javax.swing.JTextField txtUgyfelOkmanyAzonosito;
    private javax.swing.JTextField txtUgyfelOkmanyTipus;
    private javax.swing.JTextField txtVezetek;
    private javax.swing.JTextField txtregJelszoUjra;
    // End of variables declaration//GEN-END:variables
}
