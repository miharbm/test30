package web.test30.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "user" , schema="public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not  be empty")
    @Size(min = 2, max = 30, message = "Name's length should be between 2 and 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Name should not  be empty")
    @Size(min = 2, max = 30, message = "Name's length should be between 2 and 30")
    @Column(name = "last_name")
    private String lastname;

    @Column(name = "birthday")
    @Temporal( TemporalType.DATE )
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;

    @Pattern( regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format: " +
            "Country, City, Postal Code")
    @Column(name = "address")
    private String address;

    public User(Long _id) {
        this.id = _id;
    }

    public User(){}

    public User(Long id, String name, String lastname, Date dateOfBirth, String email, String address) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
    }


    public void update(User updatedUser) {
        this.setName( updatedUser.name );
        this.setLastname( updatedUser.lastname );
        this.setDateOfBirth( updatedUser.dateOfBirth );
        this.setEmail( updatedUser.email );
        this.setAddress( updatedUser.address );
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id + '\'' +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

//    CREATE DATABASE test29;
//
//        CREATE TABLE IF NOT EXISTS "public.Users"(
//        "id" serial NOT NULL,
//        "name" varchar(255),
//        "last_name" varchar(255),
//        "birthday" DATE,
//        "email" varchar(255) UNIQUE,
//         "address" varchar(255) UNIQUE,
//        CONSTRAINT "Users_pk" PRIMARY KEY ("id")
//        )