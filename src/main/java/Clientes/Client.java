package Clientes;

public class Client {
    private String ssn;
    private String name;
    private String email;
    private char gender;

    public Client(String ssn, String name, String email, char gender) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
    public String getSsn(){
        return this.ssn;
    }
    public void setSsn(String ssn){
        this.ssn = ssn;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name =name;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email =email;
    }
    public char getGender(){
        return this.gender;
    }
    public void setGender(char gender){
        this.gender =gender;
    }
}
