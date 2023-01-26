package mainPackage;
import java.io.*;
import java.text.*;
import java.util.*;


public class Main {
    public static int secim, nesilSayisi = 1, idIndex=0, sayac = 0;
    public static String idInput = "";
    public static void main(String[] args) throws IOException, ParseException { 
        Scanner scan = new Scanner(System.in);
    try{
        // Kişiler ArrayList e kaydediliyor
        TreeMethods.kisiKaydet();
        // Eş Bulma
        TreeMethods.esBulma();
        // Kişilerin çocukları Children ArrayListine ekleniyor
        TreeMethods.cocukBulma();

        NodeTree.setRoot(NodeTree.people.get(0));
        System.out.println("Root: " + NodeTree.getRoot().getIsim() + " " + NodeTree.getRoot().getSoyIsim());


        System.out.println("\n");

        boolean dongu = true;
    while(dongu){
        // MENÜ
        System.out.println("\n1- Soy ağacı gösterimi");
        System.out.println("2- Çocuğu olmayan düğümlerin yaş sıralamasına göre sıralaması");
        System.out.println("3- Üvey kardeşlerin alfabetik sıralaması");
        System.out.println("4- Kan grubu aynı olanların gösterimi");
        System.out.println("5- Aynı mesleği yapan çocuklar veya torunların gösterimi");
        System.out.println("6- Aynı isme sahip kişilerin isim ve yaşlarının gösterimi");
        System.out.println("7- İki kişinin yakınlık bilgisini gösterme");
        System.out.println("8- Kişiye ait soy ağacını gösterme");
        System.out.println("9- Soy ağacının derinliği");
        System.out.println("10- Kişiden sonra gelen derinliği bulma");
        System.out.print("İşlemi seçin: ");
        secim = scan.nextInt();
        switch(secim){
            case 0:
                scan.nextLine();
                int rootIndex;
                System.out.print("Root id girin: ");
                String rootİd = scan.nextLine();
                for(PersonNode node : NodeTree.people){
                    if(node.getId().equals(rootİd)){
                        rootIndex = NodeTree.people.indexOf(node);
                        NodeTree.setRoot(NodeTree.people.get(rootIndex));
                        break;
                    }
                } 
                break;
            case 1:  // soy ağacı
                GuiRun.main(args);
                dongu = false;
                break;
            case 2: // çocuğu olmayan düğümleri sıralama - Depth First Algoritması (Pre Order) - Sıralama Bubble Sort
                System.out.println("\n\nDepth First Search Algoritması ( Pre-Order )\n");
                Stack<PersonNode> stack = new Stack<>();
                ArrayList<PersonNode> cocuguOlmayanlar = new ArrayList<>();
                List<Long> yaslar = new ArrayList<>();
                Set <String> id = new LinkedHashSet<>();
                stack.add(NodeTree.getRoot());
                // Traverse in tree with DFS (Pre-Order)
                while(!stack.isEmpty()){
                    PersonNode currNode = stack.pop();
                    if(!id.contains(currNode.getId())){
                        System.out.println(currNode.getId() + " " + currNode.getIsim() + " " + currNode.getSoyIsim()+ ": " + currNode.getChildren().size());
                        if(currNode.getChildren().size() == 0)
                            cocuguOlmayanlar.add(currNode);
                    }
                    stack.addAll(currNode.getChildren());
                }
                System.out.println("\n\n\n");
                //Çocuğu olmayanların yaş hesaplanması
                System.out.print("Sıralamadan önce: ");
                for(PersonNode node : cocuguOlmayanlar){
                    node.yasHesapla();
                    yaslar.add(node.getYas());
                    System.out.print(node.getYas() + " ");
                }
                for(int i=0; i<cocuguOlmayanlar.size()-1; i++){
                    for(int j=0; j < cocuguOlmayanlar.size()-i-1; j++){
                        if(cocuguOlmayanlar.get(j+1).getYas() < cocuguOlmayanlar.get(j).getYas()){
                            PersonNode tmp = cocuguOlmayanlar.get(j);
                            cocuguOlmayanlar.set(j, cocuguOlmayanlar.get(j+1));
                            cocuguOlmayanlar.set(j+1, tmp);
                        } 
                    }
                        System.out.print("\n" + (i+1) + ". adım: ");
                        for(int k = 0; k < cocuguOlmayanlar.size(); k++){
                            System.out.print(cocuguOlmayanlar.get(k).getYas() + " ");
                        }
                }
                System.out.println("\n\nSıralanmış yaşlar: ");
                for(PersonNode node : cocuguOlmayanlar){
                    System.out.println(node.getId() + " " + node.getIsim() + " " + node.getSoyIsim() + ": " + node.getYas());
                }
                dongu = false;
                break;
            case 3: // üvey Kardeşlerin alfabetik sıralaması - Breath First Algoritması
                Queue<PersonNode> queue = new ArrayDeque<>();
                Set<String> idler = new LinkedHashSet<>();
                Set<PersonNode> uveyKardesler = new LinkedHashSet<>();
                queue.add(NodeTree.getRoot());
                while(!queue.isEmpty()){
                    PersonNode currentNode = queue.remove();
                    currentNode.yasHesapla();
                    if(!idler.contains(currentNode.getId())){
                        idler.add(currentNode.getId());
                        if(currentNode.Es != null){
                            for(PersonNode node : currentNode.getChildren()){
                                if(!currentNode.Es.getChildren().contains(node) && currentNode.Es.getKanGrubu() != null){
                                    System.out.println(node.getIsim());
                                    uveyKardesler.addAll(currentNode.getChildren());
                                }
                            }
                            for(PersonNode node : currentNode.Es.getChildren()){
                                if(!currentNode.getChildren().contains(node) && currentNode.getKanGrubu() != null){
                                    System.out.println(node.getIsim());
                                    uveyKardesler.addAll(currentNode.Es.getChildren());
                                }
                            }
                            if(currentNode.getChildren().size() != 0){
                                queue.addAll(currentNode.getChildren());
                            }
                        }
                    }
                }
                System.out.print("Üvey Kardeşler: " );
                for(PersonNode node : NodeTree.people){                    
                    if(node.uveyKardes.isEmpty())
                        continue;
                    System.out.print(node.getIsim() + ": ");
                    for(PersonNode node2 : node.uveyKardes){
                        System.out.print(node2.getIsim() + ", ");
                    }
                    System.out.println("\n");
                }
                for(PersonNode node : uveyKardesler){
                    System.out.print(node.getId() + node.getIsim() + " ");
                }

                System.out.print("\n" + "Sıralama Adımları: ");
                ArrayList<PersonNode> uveyList = new ArrayList<>(uveyKardesler);
                for(int i=0; i < uveyKardesler.size()-1; i++){
                    for(int j=0; j < uveyKardesler.size()-i-1; j++){
                        if(uveyList.get(j+1).getIsim().compareTo(uveyList.get(j).getIsim()) < 0){
                            PersonNode tmp = uveyList.get(j);
                            uveyList.set(j, uveyList.get(j+1));
                            uveyList.set(j+1, tmp);
                        }  
                    }
                    System.out.print("\n" + (i+1) + ". adım: ");
                    for(int k=0; k<uveyList.size(); k++){
                        System.out.print(uveyList.get(k).getIsim() + " ");
                    }
                }
                System.out.println("\n\nSıralanmış İsimler: ");
                for(PersonNode node : uveyList){
                    System.out.print(node.getId() + " " + node.getIsim() + " - ");
                }
                dongu = false;
                break;
            case 4: // Kan grubu aynı olanları gösterme
                //Kan grubu a olanlar
                scan.nextLine();
                System.out.print("Kan Grubunu girin: ");
                String kanGrubu = scan.nextLine();
                GuiKangrubu.main(args, kanGrubu);
                dongu = false;
                break;
            case 5: // Aynı mesleği yapan çocuk ve torunların gösterimi
            String kisiidsi = scan.nextLine();
            PersonNode yeniroot = NodeTree.getRoot();
            int kisisayi = 0;
            int agakisisayi = 0;
            for (PersonNode a : NodeTree.people) {
                if (a.getId().equals(kisiidsi)) {
                    yeniroot = a;
                    break;
                }
                kisisayi++;
            }
            //agakisisayi = Agackisisayisi(NodeTree.getRoot(), agakisisayi);
            if (kisisayi >= agakisisayi) {
                yeniroot = NodeTree.people.get(0);
            }
            Queue<PersonNode> kuyruks = new ArrayDeque<>();
            Queue<PersonNode> kuyruks2 = new ArrayDeque<>();
            Set<String> idds =new LinkedHashSet<>();
            ArrayList<PersonNode> aynimeslek = new ArrayList<>();
            kuyruks.add(yeniroot);
            sayac = 0;
            while (!kuyruks.isEmpty()) {
                kuyruks2.add(yeniroot);
                PersonNode a = kuyruks.remove();
                while (!kuyruks2.isEmpty()) {
                    PersonNode a22 = kuyruks2.remove();
                    if (!idds.contains(a22.getId())) {
                        if (a.getMeslek().equals(a22.getMeslek()) && a.getId() != a22.getId()
                                && a.getMeslek().length() != 0) {
                            if (sayac == 0) {
                                aynimeslek.add(a);
                                idds.add(a.getId());
                                sayac++;
                            }
                            aynimeslek.add(a22);
                            idds.add(a22.getId());
                        }
                    }
                    if (!a22.getChildren().isEmpty()) {
                        kuyruks2.addAll(a22.getChildren());
                    }
                }
                if (a.getChildren().size() != 0) {
                    kuyruks.addAll(a.getChildren());
                }
            }
                GuiMeslek.main(args, aynimeslek);
                dongu = false;
                break;
            case 6: // Aynı isme sahip kişilerin isim ve yaşlarının gösterimi
            Queue<PersonNode> queue6 = new ArrayDeque<>();
            Queue<PersonNode> queue6_2 = new ArrayDeque<>();
            Set<String> id6 = new LinkedHashSet<>();
            Set<String> id6_2 = new LinkedHashSet<>();
            queue6.add(NodeTree.getRoot());
            queue6_2.add(NodeTree.getRoot());
            while(!queue6.isEmpty()){
                PersonNode currentNode = queue6.remove();
                if(!id6.contains(currentNode.getId())){
                    id6.add(currentNode.getId());
                    while(!queue6_2.isEmpty()){
                        PersonNode currentNode2 = queue6_2.remove();
                        if(!id6_2.contains(currentNode2.getId())){
                            id6_2.add(currentNode2.getId());
                            if(currentNode.getIsim().equals(currentNode2.getIsim()) && !currentNode.getId().equals(currentNode2.getId())){
                                currentNode.yasHesapla();
                                currentNode2.yasHesapla();
                                System.out.println(currentNode.getYas() + " " + currentNode.getIsim() + " - " + currentNode2.getYas() + " " + currentNode2.getIsim());
                            }
                            if(currentNode2.getChildren().size() != 0){
                                queue6_2.addAll(currentNode2.getChildren());
                            }
                        }else{
                            continue;
                        }
                    }
                    if(currentNode.getChildren().size() != 0){
                        queue6.addAll(currentNode.getChildren());
                    }
                }else{
                    continue;
                }
            }
                dongu = false;
                break;
            case 7: // İki kişinin yakınlık bilgisini gösterme
                System.out.print("1.kişinin id sini girin: ");
                scan.nextLine();
                String id1 = scan.nextLine();
                int id1Index = -1;
                for(PersonNode node : NodeTree.people){
                    if(id1.equals(node.getId())){
                        id1Index = NodeTree.people.indexOf(node);
                        break;
                    }
                }
                if(id1Index == -1){
                    System.out.println("Hatalı id girdiniz!!!");
                    System.exit(-1);
                }
                System.out.print("2. kişinin id sini girin: ");
                String id2 = scan.nextLine();
                int id2Index = -1;
                for(PersonNode node : NodeTree.people){
                    if(id2.equals(node.getId())){
                        id2Index = NodeTree.people.indexOf(node);
                        break;
                    }
                }
                if(id2Index == -1){
                    System.out.println("Hatalı id girdiniz!!!");
                    System.exit(-1);
                }
                NodeTree.people.get(id1Index).yasHesapla();
                NodeTree.people.get(id2Index).yasHesapla();
                if(NodeTree.people.get(id2Index).getYas() > NodeTree.people.get(id1Index).getYas()){
                    int tmp = id1Index;
                    id1Index = id2Index;
                    id2Index = tmp;
                }
                TreeMethods.yakinlikbul(NodeTree.people.get(id1Index), NodeTree.people.get(id2Index));
                dongu = false;
                break;
            case 8: // Kişiye ait soy ağacını gösterme
                System.out.print("Kişi id si giriniz: ");
                scan.nextLine();
                idInput = scan.nextLine();
                for(PersonNode node : NodeTree.people){
                    if(node.getId().equals(idInput)){
                        idIndex = NodeTree.people.indexOf(node);
                    }
                }
                NodeTree.setRoot(NodeTree.people.get(idIndex));
                GuiRun.main(args);
                dongu = false;
                break;
            case 9: // Soy ağacının derinliği
                List<Integer> nesilsayi = new ArrayList<>();
                TreeMethods.derinlik(NodeTree.getRoot(), nesilSayisi, nesilsayi);
                System.out.println("\n\tSoy ağacının derinliği: " + Collections.max(nesilsayi));
                dongu = false;
                break;
            case 10: // Kişiden sonra gelen derinliği bulma
                System.out.print("Kişi id si giriniz: ");
                nesilSayisi = 0;
                scan.nextLine();
                idInput = scan.nextLine();
                for(PersonNode node : NodeTree.people){
                    if(node.getId().equals(idInput)){
                        idIndex = NodeTree.people.indexOf(node);
                        break;
                    }
                }
                List<Integer> nesilSayi = new ArrayList<>();
                TreeMethods.derinlik(NodeTree.people.get(idIndex), nesilSayisi, nesilSayi);
                System.out.println("\n\t" + idInput + " " + NodeTree.people.get(idIndex).getIsim() + " " + NodeTree.people.get(idIndex).getSoyIsim() + " kişisinden sonraki derinlik: " + Collections.max(nesilSayi));
                dongu = false;
                break;
        }
    }
        scan.close();
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }
}
}
