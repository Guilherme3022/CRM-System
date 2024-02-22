package Clients;

import Domain.ClientBuilder;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ClientBuilderTest extends TestCase {
    private ClientBuilder clientBuilder;
    private ClientBuilder clientBuilder2;

    @Before
    public void setUp() throws Exception {
        clientBuilder = new ClientBuilder().personalInformation("Gustavo Baptista da Silva",
                "01/30/2024","009781234947");
        clientBuilder2 = new ClientBuilder().onlyAdresses("gu.bap22@gmail.com", "479912325195",
                "rua joao");
    }

    @Test
    public void testGetSsn() {
        Assertions.assertEquals(clientBuilder.getSsn(), "009781234947");
        Assertions.assertNull(clientBuilder2.getSsn());
    }

    @Test
    public void testSetSsn() {
        clientBuilder.setSsn("000000000000");
        clientBuilder2.setSsn("00978074947");
        Assertions.assertEquals(clientBuilder.getSsn(), "000000000000");
        Assertions.assertEquals(clientBuilder2.getSsn(), "00978074947");
    }

    @Test
    public void testGetFullName() {
        Assertions.assertEquals(clientBuilder.getFullName(), "Gustavo Baptista da Silva");
        Assertions.assertNull(clientBuilder2.getFullName());
    }

    @Test
    public void testSetFullName() {
        clientBuilder.setFullName("Eduarda");
        clientBuilder2.setFullName("Guilherme");
        Assertions.assertEquals(clientBuilder.getFullName(), "Eduarda");
        Assertions.assertEquals(clientBuilder2.getFullName(), "Guilherme");
    }
    @Test
    public void testGetBirthDate() {
        Assertions.assertEquals(clientBuilder.getBirthDate(), "01/30/2024");
        Assertions.assertNull(clientBuilder2.getBirthDate());
    }

    @Test
    public void testSetBirthDate() {
        clientBuilder.setBirthDate("09/20/2001");
        clientBuilder2.setBirthDate("11/12/2001");
        Assertions.assertEquals(clientBuilder.getBirthDate(), "09/20/2001");
        Assertions.assertEquals(clientBuilder2.getBirthDate(), "11/12/2001");
    }
    @Test
    public void testGetEmail() {
        Assertions.assertNull(clientBuilder.getEmail());
        Assertions.assertEquals(clientBuilder2.getEmail(), "gu.bap22@gmail.com");
    }

    @Test
    public void testSetEmail() {
        clientBuilder.setEmail("eduardasilveralima@gmail.com");
        clientBuilder2.setEmail("gui.bap22@gmail.com");
        Assertions.assertEquals(clientBuilder.getEmail(), "eduardasilveralima@gmail.com");
        Assertions.assertEquals(clientBuilder2.getEmail(), "gui.bap22@gmail.com");
    }

    @Test
    public void testGetAddress() {
        Assertions.assertNull(clientBuilder.getAddress());
        Assertions.assertEquals(clientBuilder2.getAddress(), "rua joao");
    }

    @Test
    public void testSetAddress() {
        clientBuilder.setAddress("avenida principal");
        clientBuilder2.setAddress("rua lateral");
        Assertions.assertEquals(clientBuilder.getAddress(), "avenida principal");
        Assertions.assertEquals(clientBuilder2.getAddress(), "rua lateral");
    }

    @Test
    public void testGetPhoneNumber() {
        Assertions.assertNull(clientBuilder.getPhoneNumber());
        Assertions.assertEquals(clientBuilder2.getPhoneNumber(), "479912325195");
    }

    @Test
    public void testSetPhoneNumber() {
        clientBuilder.setPhoneNumber("123456789");
        clientBuilder2.setPhoneNumber("987654321");
        Assertions.assertEquals(clientBuilder.getPhoneNumber(), "123456789");
        Assertions.assertEquals(clientBuilder2.getPhoneNumber(), "987654321");
    }

    @Test
    public void testGetAge() {
        Assertions.assertEquals(clientBuilder.getAge(), 0);
        Assertions.assertEquals(clientBuilder2.getAge(), 0);
    }

    @Test
    public void testSetAge() {
        clientBuilder.setAge(25);
        clientBuilder2.setAge(30);
        Assertions.assertEquals(clientBuilder.getAge(), 25);
        Assertions.assertEquals(clientBuilder2.getAge(), 30);
    }

    @Test
    public void testToString() {
        String expectedString = "Client's data:\nName: Gustavo Baptista da Silva\nBirth date: 01/30/2024\nAge: 0\n" +
                "SSN: 009781234947\nEmail address: null\nPhone number: null\nZip address: null";
        Assertions.assertEquals(clientBuilder.toString(), expectedString);
    }

    @Test
    public void testToStringPersonalinformation() {
        String expectedString = "Client's data:\nName: Gustavo Baptista da Silva\nBirth date: 01/30/2024\nAge: 0\n" +
                "SSN: 009781234947";
        Assertions.assertEquals(clientBuilder.toStringPersonalinformation(), expectedString);
    }

    @Test
    public void testToStringAdresses() {
        String expectedString = "Email address: gu.bap22@gmail.com\nPhone number: 479912325195\nZip address: rua joao";
        Assertions.assertEquals(clientBuilder2.toStringAdresses(), expectedString);
    }

    @After
    public void tearDown() throws Exception {
        clientBuilder = null;
        clientBuilder2 = null;
    }

}