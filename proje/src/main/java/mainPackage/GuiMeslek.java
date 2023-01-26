package mainPackage;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class GuiMeslek extends JComponent {
    static Random rand = new Random();
    public static void main(String[] args, ArrayList<PersonNode> ayniMeslek) throws IOException, ParseException { 
        ArrayList <JButton> butunButonlar = new ArrayList<>();
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        int width = frame.getWidth();
        // Root Çizdirme
        if (NodeTree.getRoot().Es == null) {
            JButton btn1 = new JButton();
            btn1.setLocation(width / 2 - 250, 50);
            btn1.setSize(100, 100);
            if(NodeTree.getRoot().getCinsiyet().equals("Erkek"))
                btn1.setBackground(Color.white);
            else
                btn1.setBackground(Color.white);
            btn1.setForeground(Color.WHITE);
            btn1.setEnabled(false);
            btn1.setText(NodeTree.getRoot().getIsim());
            btn1.setText("<html>" + "ID: " + NodeTree.getRoot().getId() + "<br/>" + NodeTree.getRoot().getIsim() + " "
                    + NodeTree.getRoot().getSoyIsim() + "<br/>" + NodeTree.getRoot().getDogumTarihi() + "<br/>"
                    + NodeTree.getRoot().getKanGrubu() + "<br/>" + NodeTree.getRoot().getMeslek() + "</html>");
            btn1.setBorder(new LineBorder(Color.BLACK));
            frame.getContentPane().add(btn1);
        } else {
            JButton btn1 = new JButton();
            NodeTree.getRoot().nodeBtn = btn1;
            JButton btn2 = new JButton();
            NodeTree.getRoot().Es.nodeBtn = btn2;
            btn1.setLocation(1920 / 2 - 350, 20);
            btn2.setLocation(1920 / 2 - 150, 20);
            btn1.setSize(100, 100);
            btn2.setSize(100, 100);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn1.setText("<html>" + "<font color = \"white\">" + "ID: " + NodeTree.getRoot().getId() + "<br/>"
                    + NodeTree.getRoot().getIsim() + " " + NodeTree.getRoot().getSoyIsim() + "<br/>"
                    + NodeTree.getRoot().getDogumTarihi() + "<br/>" + NodeTree.getRoot().getKanGrubu() + "<br/>"
                    + NodeTree.getRoot().getMeslek() + "</font>" + "</html>");
            btn2.setText("<html>" + "<font color = \"white\">" + "ID: " + NodeTree.getRoot().Es.getId() + "<br/>"
                    + NodeTree.getRoot().Es.getIsim() + " " + NodeTree.getRoot().Es.getSoyIsim() + "<br/>"
                    + NodeTree.getRoot().Es.getDogumTarihi() + "<br/>" + NodeTree.getRoot().Es.getKanGrubu() + "<br/>"
                    + NodeTree.getRoot().Es.getMeslek() + "</font>" + "</html>");
            if(NodeTree.getRoot().getCinsiyet().equals("Erkek")){
                btn1.setBackground(Color.white);
                btn2.setBackground(Color.white);
            }
            else{
                btn2.setBackground(Color.white);
                btn1.setBackground(Color.white);
            }
            btn1.setForeground(Color.WHITE);
            btn1.setBorder(new LineBorder(Color.BLACK));
            btn2.setForeground(Color.WHITE);
            btn2.setBorder(new LineBorder(Color.BLACK));
            frame.getContentPane().add(btn1);
            frame.getContentPane().add(btn2);
            List <JButton> btnList = new ArrayList<>();
            int spacing = 0;
            int endLine = 0;
            Queue<PersonNode> queue = new ArrayDeque<>();
            queue.addAll(NodeTree.getRoot().getChildren());
            PersonNode currNode = NodeTree.getRoot().getChildren().get(0);
            int dongu = NodeTree.getRoot().getChildren().size();
            ArrayList<PersonNode> cizilecekler = new ArrayList<>();
            int y = 170;
            while (!queue.isEmpty()) {
                for (int i = 0; i < dongu; i++) {
                    currNode = queue.remove();
                    cizilecekler.add(currNode);
                    queue.addAll(currNode.getChildren());
                }
                if (cizilecekler.size() == 1) {
                    spacing = 1920 / 2 - 50;
                } else if (cizilecekler.size() % 2 == 0) {
                    spacing = 1920 / 2 - (140 * cizilecekler.size());
                    endLine = spacing + (120 * cizilecekler.size()) + 70
                            + ((cizilecekler.size() - 2) * 120);
                } else if (cizilecekler.size() % 2 != 0) {
                    spacing = (1920 / 2) - 50 - ((cizilecekler.size() - 1) * 120);
                    endLine = spacing + 50 + ((cizilecekler.size() - 1) * 120)
                            + ((cizilecekler.size() - 2) * 120) + 70;
                }
                for (int j = 0; j < cizilecekler.size(); j++) {
                    JButton btn = new JButton();
                    btnList.add(btn);
                    butunButonlar.add(btn);
                    cizilecekler.get(j).nodeBtn = btn;
                }
                for (int k = 0; k < cizilecekler.size(); k++) {
                    if (cizilecekler.size() % 2 == 0) {
                        btnList.get(k).setLocation(
                                spacing + (k * 280), y);
                    } else {
                        btnList.get(k).setLocation(spacing + ((cizilecekler.size() - 1) * 120) * (k-1), y);
                    }
                    btnList.get(k).setSize(100, 100);
                    if(cizilecekler.get(k).getCinsiyet().equals("Erkek"))
                        btnList.get(k).setBackground(Color.white);
                    else
                        btnList.get(k).setBackground(Color.WHITE);
                    btnList.get(k)
                            .setText("<html>" + "<font color = \"white\">" + "ID: "
                                    + cizilecekler.get(k).getId() + "<br/>"
                                    + cizilecekler.get(k).getIsim() + " "
                                    + cizilecekler.get(k).getSoyIsim() + "<br/>"
                                    + cizilecekler.get(k).getDogumTarihi() + "<br/>"
                                    + cizilecekler.get(k).getKanGrubu() + "<br/>"
                                    + cizilecekler.get(k).getMeslek() + "</font>" + "</html>");
                    btnList.get(k).setEnabled(false);
                    if(k == cizilecekler.size()-1){
                        JButton btnciz = new JButton();
                        if(cizilecekler.get(k).Anne != null){
                            btnciz.setSize(cizilecekler.get(k).nodeBtn.getLocation().x - cizilecekler.get(k).Anne.nodeBtn.getLocation().x, 5);
                            btnciz.setLocation(cizilecekler.get(k).Anne.nodeBtn.getLocation().x + 50, y - 25);
                        }else if(cizilecekler.get(k).Baba != null){
                            btnciz.setSize(cizilecekler.get(k).nodeBtn.getLocation().x - cizilecekler.get(k).Baba.nodeBtn.getLocation().x, 5);
                            btnciz.setLocation(cizilecekler.get(k).Baba.nodeBtn.getLocation().x + 50, y - 25);
                        }
                        btnciz.setEnabled(false);
                        btnciz.setBackground(Color.white);
                        btnciz.setVisible(true);
                        frame.add(btnciz);
                        for(int i = 0; i < cizilecekler.size(); i++){
                            JButton btnParentCiz = new JButton();
                            btnParentCiz.setEnabled(false);
                            if(cizilecekler.get(i).Baba.cocukCizgi != null){
                                btnParentCiz.setBackground(cizilecekler.get(i).Baba.cocukCizgi.getBackground());
                            }else if(cizilecekler.get(i).Anne.cocukCizgi != null){
                                btnParentCiz.setBackground(cizilecekler.get(i).Anne.cocukCizgi.getBackground());
                            }
                            btnParentCiz.setVisible(true);
                            frame.add(btnParentCiz);
                            btnParentCiz.setLocation(btnList.get(i).getLocation().x + 50, y-25);
                            btnParentCiz.setSize(5, btnList.get(i).getLocation().y - (y-25));
                            if(cizilecekler.get(i).getChildren().size() != 0){
                                JButton btnCocukCizgisi = new JButton();
                                cizilecekler.get(i).cocukCizgi = btnCocukCizgisi;
                                frame.add(btnCocukCizgisi);
                                btnCocukCizgisi.setEnabled(false);
                                float r = rand.nextFloat();
                                float g = rand.nextFloat();
                                float b = rand.nextFloat();
                                btnCocukCizgisi.setBackground(new Color(r,g,b));
                                btnCocukCizgisi.setVisible(true);
                                btnCocukCizgisi.setLocation(btnList.get(i).getLocation().x + 50 ,btnList.get(i).getLocation().y + 100);
                                btnCocukCizgisi.setSize(5, 25);
                            }
                            if(cizilecekler.get(i).getMedeniHal().equals("Evli")){
                                JButton btnesCiz = new JButton();
                                frame.add(btnesCiz);
                                btnesCiz.setBackground(Color.white);
                                btnesCiz.setVisible(true);
                                btnesCiz.setLocation(btnList.get(i).getLocation().x + 100, btnList.get(i).getLocation().y + 50);
                                btnesCiz.setSize(20,5);
                            }
                        }
                    }
                    if (cizilecekler.get(k).Es != null) {
                        JButton btnEs = new JButton();
                        cizilecekler.get(k).Es.nodeBtn = btnEs;
                        if (cizilecekler.size() % 2 == 0) {
                            btnEs.setLocation(
                                    spacing + (k * 270) + 120, y);
                        } else {
                            btnEs.setLocation(spacing + ((cizilecekler.size() - 1) * 120) * (k-1) + 120, y);
                        }
                        btnEs.setSize(100, 100);
                        btnEs.setEnabled(false);
                        
                        if(cizilecekler.get(k).getCinsiyet().equals("Erkek"))
                            btnEs.setBackground(Color.white);
                        else
                            btnEs.setBackground(Color.white);
                        btnEs.setText("<html>" + "<font color = \"white\">" + "ID: "
                                + cizilecekler.get(k).Es.getId() + "<br/>"
                                + cizilecekler.get(k).Es.getIsim() + " "
                                + cizilecekler.get(k).Es.getSoyIsim() + "<br/>"
                                + cizilecekler.get(k).Es.getDogumTarihi() + "<br/>"
                                + cizilecekler.get(k).Es.getKanGrubu() + "<br/>"
                                + cizilecekler.get(k).Es.getMeslek() + "</font>" + "</html>");
                        frame.add(btnEs);
                    }
                    frame.add(btnList.get(k));
                }
                btnList.clear();
                cizilecekler.clear();
                dongu = queue.size();
                y += 150;
            }
        }
        frame.setSize(1920, 1080);
        frame.setTitle("SOY AĞACI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        GuiRun component = new GuiRun();
        frame.add(component);
        frame.setVisible(true);
        renklendir(ayniMeslek);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.white);
        if (NodeTree.getRoot().getMedeniHal().equals("Evli"))
            g2d.drawLine(1920 / 2 - 250, 70, 1920 / 2 - 150, 70);
            g2d.drawLine(1920 / 2 - 200, 70, 1920 / 2 - 200, 145);
            g2d.drawLine(NodeTree.getRoot().getChildren().get(0).nodeBtn.getLocation().x + 50, 145,NodeTree.getRoot().getChildren().get(NodeTree.getRoot().getChildren().size()-1).nodeBtn.getLocation().x + 50,145);;

    }
    static void renklendir(ArrayList<PersonNode> meslekler){
        Set<String> meslek = new LinkedHashSet<>();
        for(PersonNode node : meslekler){
            meslek.add(node.getMeslek());
        }
        for(String job : meslek){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            for(PersonNode node : meslekler){
                if(node.getMeslek().equals(job)){
                    node.nodeBtn.setBackground(new Color(r,g,b));
                }
            }
        }
    }
}
