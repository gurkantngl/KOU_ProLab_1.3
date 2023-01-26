package mainPackage;

import java.util.*;

public class NodeTree implements Tree{
    private static PersonNode root = null;
    static List <PersonNode> people = new ArrayList<>();
    static ArrayList <Node> cocuguOlmayanlar = new ArrayList<>();
    private Node searchByName(Node currentNode, String name, List<Node> result){
        if(currentNode.getIsim().equals(name)){
            result.add(currentNode);
        }
        for(Node child : currentNode.getChildren()){
            searchByName(child, name, result);
        }
        return !result.isEmpty() ? result.get(0) : null;
    }

    private Node searchById(Node currentNode, String id, List<Node> result){
        if(currentNode.getId().equals(id)){
            result.add(currentNode);
        }
        for(Node child : currentNode.getChildren()){
            searchById(child, id, result);
        }
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public Node getNodeByName(String name) {
        return searchByName(root, name, new ArrayList<>());
    }

    @Override
    public Node getNodeById(String Id) {
        return searchById(root, Id, new ArrayList<>());
    }


    public static PersonNode getRoot() {
        return root;
    }

    public static void setRoot(PersonNode r) {
        root = r;
    }

}