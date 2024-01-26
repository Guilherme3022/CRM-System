import Clients.Client;

public class Main {
    public static void main(String[] args) {
        Client clientG = new Client("Guilherme Baptista da Silva","11/12/2001","00978074947","gui.bap22@gmail.com", "47997225195","rua joao");
        System.out.println(clientG);
        Client clientGu = new Client("Gustavo Baptista da Silva","10/26/2010","009781234947","gu.bap22@gmail.com", "479912325195","rua joao");
        System.out.println("\n"+clientGu);

    }
}