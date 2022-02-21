package gr.hua.team19.armypostponement.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Application {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int application_id;

    @NotNull(message = "First name is compulsory")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is compulsory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Father's name is compulsory")
    @Column(name = "father_name")
    private String fatherName;

    @NotNull(message = "Mother's name is compulsory")
    @Column(name = "mother_name")
    private String motherName;

    @NotNull(message = "AFM number is compulsory")
    @Column(name = "afm_number")
    private String afm;

    @NotNull(message = "AMKA number is compulsory")
    @Column(name = "amka_number")
    private String amka;

    @NotNull(message = "military_number number is compulsory")
    @Column(name = "military_number")
    private String military_number;

    @NotNull(message = "Email is compulsory")
    @Email(message = "Email is not valid")
    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;


    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getMilitary_number() {
        return military_number;
    }

    public void setMilitary_number(String military_number) {
        this.military_number = military_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
