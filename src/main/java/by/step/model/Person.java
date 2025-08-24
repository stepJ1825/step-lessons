package by.step.model;

import java.time.LocalDate;

public class Person {

    private int id;
    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;
    private String email;

    private String gender;
    private int recruitmentGroup;
    private String city;
    private String occupation;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, LocalDate dateOfBirth, String email, String gender,
                  int recruitmentGroup, String city, String occupation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.recruitmentGroup = recruitmentGroup;
        this.city = city;
        this.occupation = occupation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRecruitmentGroup() {
        return recruitmentGroup;
    }

    public void setRecruitmentGroup(int recruitmentGroup) {
        this.recruitmentGroup = recruitmentGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", firstName='" + firstName + ", lastName='" + lastName + ", dateOfBirth="
            + dateOfBirth + ", email='" + email + ", gender='" + gender + ", recruitmentGroup=" + recruitmentGroup
            + ", city='" + city + ", occupation='" + occupation;
    }
}
