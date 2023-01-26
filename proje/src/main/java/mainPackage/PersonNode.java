package mainPackage;
import java.text.*;
import java.util.*;

import javax.swing.JButton;

import com.opencsv.bean.CsvBindByName;

public class PersonNode implements Node {
    private List <PersonNode> children = new ArrayList<>();
    PersonNode Es = null;
    PersonNode Baba = null;
    PersonNode Anne = null;
    JButton nodeBtn = null;
    JButton cocukCizgi = null;
    List <PersonNode> uveyKardes = new ArrayList<>();


    void yasHesapla() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(this.getDogumTarihi());
        Date simdikiZaman = new Date();
        long zamanFarki = simdikiZaman.getTime() - date.getTime();
        this.yas = (zamanFarki / (1000l * 60 * 60 * 24 * 365));
    }

    @CsvBindByName(column = "id", required = true)
    private String id;

    @CsvBindByName(column = "İsim", required = true)
    private String isim;

    @CsvBindByName(column = "Soyisim", required = true)
    private String soyIsim;

    @CsvBindByName(column =  "Doğum Tarihi", required = true)
    private String dogumTarihi;

    private long yas;

    @CsvBindByName(column = "Eşi")
    private String es;

    @CsvBindByName(column = "Anne Adı", required = true)
    private String anneAdi;

    @CsvBindByName(column = "Baba Adı", required = true)
    private String babaAdi;

    @CsvBindByName(column = "Kan Grubu", required = true)
    private String kanGrubu;

    @CsvBindByName(column = "Meslek")
    private String meslek;

    @CsvBindByName(column = "Medeni Hali", required = true)
    private String medeniHal;

    @CsvBindByName(column = "Kızlık Soyismi")
    private String kizlikSoyIsmi;

    @CsvBindByName(column = "Cinsiyet", required = true)
    private String cinsiyet;

    @CsvBindByName(column = "EsId")
    private String esId;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getIsim() {
        return this.isim;
    }

    public void setIsim(String isim){
        this.isim = isim;
    }

    @Override
    public String getSoyIsim() {
        return this.soyIsim;
    }

    public void setSoyIsim(String soyIsim){
        this.soyIsim = soyIsim;
    }

    @Override
    public String getDogumTarihi() {
        return this.dogumTarihi;
    }

    @Override
    public String getEs() {
        return this.es;
    }

    @Override
    public String getAnneAdi() {
        return this.anneAdi;
    }

    @Override
    public String getBabaAdi() {
        return this.babaAdi;
    }

    @Override
    public String getKanGrubu() {
        return this.kanGrubu;
    }

    @Override
    public String getMeslek() {
        return this.meslek;
    }

    @Override
    public String getMedeniHal() {
        return this.medeniHal;
    }

    @Override
    public String getKizlikSoyIsmi() {
        return this.kizlikSoyIsmi;
    }

    @Override
    public String getCinsiyet() {
        return this.cinsiyet;
    }

    @Override
    public String getEsId() {
        return this.esId;
    }

    @Override
    public List<PersonNode> getChildren() {
        return this.children;
    }

    public long getYas() {
        return this.yas;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
