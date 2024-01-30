import Clients.Client;
import Clients.ClientBuilder;

public class Main {
    public static void main(String[] args) {
        Client clientG = new Client("Guilherme Baptista da Silva","11/12/2001","00978074947",
                "gui.bap22@gmail.com", "47997225195","rua joao");
        System.out.println(clientG);
        System.out.println("\n"+"____________________________________________________________________________________________");

        Client clientGu = new Client("Gustavo Baptista da Silva","10/26/2010","009781234947",
                "gu.bap22@gmail.com", "479912325195","rua joao");
        System.out.println("\n"+clientGu);
        System.out.println("\n"+"____________________________________________________________________________________________");

        ClientBuilder clientComplete= new ClientBuilder().personalInformation("Gustavo Baptista da Silva",
                "01/30/2024","009781234947").onlyAdresses("gu.bap22@gmail.com", "479912325195",
                "rua joao");
        System.out.println("\n"+clientComplete);
        System.out.println("\n"+"____________________________________________________________________________________________");

        ClientBuilder clientaddress= new ClientBuilder().onlyAdresses("gu.bap22@gmail.com", "479912325195",
                "rua joao");
        System.out.println("\n"+clientaddress.toStringAdresses());
        System.out.println("\n"+"____________________________________________________________________________________________");
        ClientBuilder anyCostumer = new ClientBuilder();


    }
}