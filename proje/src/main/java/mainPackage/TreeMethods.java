package mainPackage;

import java.io.*;
import java.text.*;
import java.util.*;
import com.opencsv.bean.CsvToBeanBuilder;;

public class TreeMethods{
    static void kisiKaydet() throws IllegalStateException, FileNotFoundException {
        Set <String> id = new LinkedHashSet<>();
        List<PersonNode> aile1 = new CsvToBeanBuilder<PersonNode>(
                new FileReader("c:/Users/ASUS/Desktop/Prolab3/Sayfa1.csv"))
                .withType(PersonNode.class).build().parse();
        for (PersonNode kisi : aile1) {
            if(!id.contains(kisi.getId())){
                NodeTree.people.add(kisi);
                id.add(kisi.getId());
            }
        }

        List<PersonNode> aile2 = new CsvToBeanBuilder<PersonNode>(
                new FileReader("c:/Users/ASUS/Desktop/Prolab3/Sayfa2.csv"))
                .withType(PersonNode.class).build().parse();
        for (PersonNode kisi : aile2) {
            if(!id.contains(kisi.getId())){
                NodeTree.people.add(kisi);
                id.add(kisi.getId());
            }
        }

        List<PersonNode> aile3 = new CsvToBeanBuilder<PersonNode>(
                new FileReader("c:/Users/ASUS/Desktop/Prolab3/Sayfa3.csv"))
                .withType(PersonNode.class).build().parse();
        for (PersonNode kisi : aile3) {
            if(!id.contains(kisi.getId())){
                NodeTree.people.add(kisi);
                id.add(kisi.getId());
            }
        }

        List<PersonNode> aile4 = new CsvToBeanBuilder<PersonNode>(
                new FileReader("c:/Users/ASUS/Desktop/Prolab3/Sayfa4.csv"))
                .withType(PersonNode.class).build().parse();
        for (PersonNode kisi : aile4) {
            if(!id.contains(kisi.getId())){
                NodeTree.people.add(kisi);
                id.add(kisi.getId());
            }
        }

    }

    static void esBulma() {
        for (PersonNode node : NodeTree.people) {
            if (node.getMedeniHal().equals("Evli")) {
                for(PersonNode node3 : NodeTree.people){
                    if(node3.getId().equals(node.getEsId())){
                        node.Es = node3;
                        break;
                    }
                }
                 if(node.Es == null){
                    String[] esIsım = node.getEs().split(" ");
                    node.Es = new PersonNode();
                    node.Es.setIsim(esIsım[0]);
                    node.Es.setId(node.getEsId());
                    if (esIsım.length > 1) {
                        node.Es.setSoyIsim(esIsım[1]);
                    }
                }
            }
        }
    }

    static void cocukBulma() throws ParseException{
        for(PersonNode node : NodeTree.people){
            for(PersonNode node2 : NodeTree.people){
                node.yasHesapla();
                node2.yasHesapla();
                if(node2.Es != null){
                    if(node.getKizlikSoyIsmi().length() != 0){
                        if(node.getAnneAdi().equals(node2.getIsim()) && node.getKizlikSoyIsmi().equals(node2.getSoyIsim()) && (node.getYas() < node2.getYas())){
                            node2.getChildren().add(node);
                            node.Anne = node2;
                        }else if(node.getBabaAdi().equals(node2.getIsim()) && node.getKizlikSoyIsmi().equals(node2.getSoyIsim()) && (node.getYas() < node2.getYas())){
                            node2.getChildren().add(node);
                            node.Baba = node2;
                        }
                    }
                    else{
                        if(node.getAnneAdi().equals(node2.getIsim()) && node.getSoyIsim().equals(node2.getSoyIsim()) && (node.getYas() < node2.getYas())){
                            node2.getChildren().add(node);
                            node.Anne = node2;
                        }else if(node.getBabaAdi().equals(node2.getIsim()) && node.getSoyIsim().equals(node2.getSoyIsim()) && (node.getYas() < node2.getYas())){
                            node2.getChildren().add(node);
                            node.Baba = node2;
                        } 
                    }
                }
            }
        }
    }

    static void derinlik(PersonNode kisi, int nesilSayisi, List<Integer> nesilSayi){
        if(kisi.getChildren().size() != 0){
            nesilSayisi++;
            for(PersonNode node : kisi.getChildren()){
                derinlik(node, nesilSayisi, nesilSayi);
            }
        }
        nesilSayi.add(nesilSayisi);
    }
    static void yakinlikbul(PersonNode node1, PersonNode node2) {
        String result  = "";
        if (node1.getCinsiyet().equals("Erkek")) {
            result = result.concat("Babası");
            while (true) {
                if (node2.Anne != null) {
                    if (node2.Anne.Anne != null) {
                        if (node2.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())
                                || node2.Anne.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())) {
                            node2 = node2.Anne;
                            result = "Annesinin".concat(result);
                        } else {
                            node2 = node2.Baba;
                            result = "Babasının".concat(result);
                        }
                        if (node2.Baba != null) {
                            if (node2.Baba.getId().equals(node1.getId())) {
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                    if (node2.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())) {
                        node2 = node2.Anne;
                        result = "Annesinin".concat(result);
                    } else {
                        node2 = node2.Baba;
                        result = "Babasının".concat(result);
                    }
                }
                if (node2.Baba != null && node2.Baba.getId().equals(node1.getId())) {
                        break;
                }
            }
        } else {
            result = result.concat("Annesi");
            while (true) {
                if (node2.Anne != null) {
                    if (node2.Anne.Anne != null) {
                        if (node2.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())
                                || node2.Anne.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())) {
                            node2 = node2.Anne;
                            result = "Annesinin".concat(result);
                        } else {
                            node2 = node2.Baba;
                            result = "Babasının".concat(result);
                        }
                        if (node2.Anne != null && node2.Anne.getId().equals(node1.getId())) {
                                break;
                        }
                    }else{
                        break;
                    }
                    if (node2.Anne.getKizlikSoyIsmi().equals(node1.getSoyIsim())) {
                        node2 = node2.Anne;
                        result = "Annesinin".concat(result);
                    } else {
                        node2 = node2.Baba;
                        result = "Babasının".concat(result);
                    }
                }
                if (node2.Anne != null && node2.Anne.getId().equals(node1.getId())) {
                        break;
                }
            }
        }
        if(result.length() ==0)
            System.out.println("Yakınlık derecesi bulunamadı!");
        else
            System.out.println("\n\t" + node1.getId() + "-" + node1.getIsim() + " kişisinin " + node2.getId() + "-" + node2.getIsim() + " kişisine yakınlık derecesi: " + result);
    }
}
