package Domain.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Client {
    private int id;
    private int live;

    private String ssn;
    private String fullName;
    private String email;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private int age;

    public Client(int id, String fullName, String birthDate, String ssn, String email, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.age = ageCalculator(birthDate);
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Client(String fullName, String birthDate, String ssn, String email, String phoneNumber, String address) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.age = ageCalculator(birthDate);
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Client (){

    }

    private int ageCalculator(String birthDate) {
        try {
            DateTimeFormatter americanFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(birthDate, americanFormat);
            LocalDate currentDate = LocalDate.now();

            if (dateOfBirth.isAfter(currentDate)) {
                throw new RuntimeException("Birth date in the future is not valid");
            }

            int age = currentDate.getYear() - dateOfBirth.getYear();

            // Verifica se o aniversário já ocorreu este ano
            if (dateOfBirth.getMonthValue() > currentDate.getMonthValue() ||
                    (dateOfBirth.getMonthValue() == currentDate.getMonthValue() &&
                            dateOfBirth.getDayOfMonth() > currentDate.getDayOfMonth())) {
                age--;
            }

            return age;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format. Please use the format YYYY/MM/DD.");
        }
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        this.age = ageCalculator(birthDate);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-12s | %-30s | %-30s | %-15s | %-50s | %-4d |",
                getId(), getFullName(), getBirthDate(), getSsn(), getEmail(), getPhoneNumber(), getAddress(), getAge());
    }
}
