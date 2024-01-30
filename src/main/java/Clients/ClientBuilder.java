package Clients;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClientBuilder {
    private String ssn;
    private String fullName;
    private String email;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private int age;

    public ClientBuilder personalInformation(String fullName, String birthDate, String ssn) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.age =ageCalculator(birthDate);
        this.ssn = ssn;
        return this;
    }
    public ClientBuilder onlyAdresses(String email, String phoneNumber, String address){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        return this;
    }
    private LocalDate validateAndParseDate(String birthDate) {
        try {
            DateTimeFormatter americanFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate dateOfBirth = LocalDate.parse(birthDate, americanFormat);

            if (dateOfBirth.isAfter(LocalDate.now())) {
                throw new RuntimeException("Birth date in the future is not valid");
            }

            return dateOfBirth;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format. Please use the format MM/dd/yyyy.");
        }
    }

    private int ageCalculator(String birthDate) {
        try {
            LocalDate dateOfBirth = validateAndParseDate(birthDate);
            Period period = Period.between(dateOfBirth, LocalDate.now());
            return period.getYears();
        }catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }
    public String getSsn(){
        return this.ssn;
    }
    public void setSsn(String ssn){
        this.ssn = ssn;
    }

    public String getFullName(){
        return this.fullName;
    }
    public void setFullName(String fullName){
        this.fullName =fullName;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email =email;
    }
    public String getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Client's data:\nName: "+getFullName()+"\nBirth date: "+getBirthDate()+"\nAge: "+getAge()+"\nSSN: "
                +getSsn()+"\nEmail address: "+getEmail()+"\nPhone number: "+getPhoneNumber()+"\nZip address: "+getAddress();
    }
    public String toStringPersonalinformation(){
        return "Client's data:\nName: "+getFullName()+"\nBirth date: "+getBirthDate()+"\nAge: "+getAge()+"\nSSN: "
                +getSsn();
    }
    public String toStringAdresses(){
        return "Email address: "+getEmail()+"\nPhone number: "+getPhoneNumber()+"\nZip address: "+getAddress();
    }
}


