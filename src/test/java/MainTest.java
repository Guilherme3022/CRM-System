import Clients.ClientBuilderTest;
import Clients.ClientTest;
import org.junit.Test;

public class MainTest {
    @Test
    public static void main(String[] args) {
        ClientTest clientG = new ClientTest("Guilherme Baptista da Silva","11/12/2001","00978074947",
                "gui.bap22@gmail.com", "47997225195","rua joao");
        System.out.println(clientG);
        System.out.println("\n"+"____________________________________________________________________________________________");

        ClientTest clientGu = new ClientTest("Gustavo Baptista da Silva","10/20/2010","009781234947",
                "gu.bap22@gmail.com", "479912325195","rua joao");
        System.out.println("\n"+clientGu);
        System.out.println("\n"+"____________________________________________________________________________________________");

        ClientBuilderTest clientComplete= new ClientBuilderTest().personalInformation("Gustavo Baptista da Silva",
                "10/20/2010","009781234947").onlyAdresses("gu.bap22@gmail.com", "479912325195",
                "rua joao");
        System.out.println("\n"+clientComplete);
        System.out.println("\n"+"____________________________________________________________________________________________");

        ClientBuilderTest clientaddress= new ClientBuilderTest().onlyAdresses("gu.bap22@gmail.com", "479912325195",
                "rua joao");
        System.out.println("\n"+clientaddress.toStringAdresses());
        System.out.println("\n"+"____________________________________________________________________________________________");
        ClientBuilderTest anyCostumer = new ClientBuilderTest();


    }
}