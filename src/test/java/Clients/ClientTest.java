package Clients;

import Domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ClientTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client( "Gustavo Baptista da Silva", "01/30/2024", "009781234947",
                "gu.bap22@gmail.com", "479912325195", "rua joao");
    }

    @Test
    public void testGetSsn() {
        Assertions.assertEquals(client.getSsn(), "009781234947");
    }

    @Test
    public void testSetSsn() {
        client.setSsn("000000000000");
        Assertions.assertEquals(client.getSsn(), "000000000000");
    }

    @Test
    public void testGetFullName() {
        Assertions.assertEquals(client.getFullName(), "Gustavo Baptista da Silva");
    }

    @Test
    public void testSetFullName() {
        client.setFullName("Eduarda");
        Assertions.assertEquals(client.getFullName(), "Eduarda");
    }

    @Test
    public void testGetBirthDate() {
        Assertions.assertEquals(client.getBirthDate(), "01/30/2024");
    }

    @Test
    public void testSetBirthDate() {
        client.setBirthDate("09/20/2001");
        Assertions.assertEquals(client.getBirthDate(), "09/20/2001");
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals(client.getEmail(), "gu.bap22@gmail.com");
    }

    @Test
    public void testSetEmail() {
        client.setEmail("eduardasilveralima@gmail.com");
        Assertions.assertEquals(client.getEmail(), "eduardasilveralima@gmail.com");
    }

    @Test
    public void testGetAddress() {
        Assertions.assertEquals(client.getAddress(), "rua joao");
    }

    @Test
    public void testSetAddress() {
        client.setAddress("avenida principal");
        Assertions.assertEquals(client.getAddress(), "avenida principal");
    }

    @Test
    public void testGetPhoneNumber() {
        Assertions.assertEquals(client.getPhoneNumber(), "479912325195");
    }

    @Test
    public void testSetPhoneNumber() {
        client.setPhoneNumber("123456789");
        Assertions.assertEquals(client.getPhoneNumber(), "123456789");
    }

    @Test
    public void testGetAge() {
        Assertions.assertEquals(client.getAge(), 0); // Assuming default age is 0
    }

    @Test
    public void testSetAge() {
        client.setAge(25);
        Assertions.assertEquals(client.getAge(), 25);
    }

    @Test
    public void testToString() {
        String expectedString = "Client's data:\nID: 1:\nName: Gustavo Baptista da Silva\nBirth date: 01/30/2024\nAge: 0\n" +
                "SSN: 009781234947\nEmail address: gu.bap22@gmail.com\nPhone number: 479912325195\nZip address: rua joao";
        Assertions.assertEquals(client.toString(), expectedString);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }
}
