package com.example.sheltervolunteer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "pets")
public class Pet {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nickname", nullable = false)
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min=2, max=30)
    private String nickname;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Category", nullable = false)
    private String category;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Gender", nullable = false)
    private String gender;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Breed", nullable = false)
    private String breed;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Color", nullable = false)
    private String color;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Size", nullable = false)
    private String size;

    @NotNull(message = "Примерный возраст не может быть равным нулю")
    @Min(1)
    @Column(name = "ApproximateAge", nullable = false)
    private int approximateAge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DateOfAdmission")
    private Date dateOfAdmission;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "RFIDChip", nullable = false, unique = true)
    private String rtidChip;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "ShelterRegNum", nullable = false, unique = true)
    private String shelterRegNum;

    @NotNull(message = "Номер вольера(клетки) не может быть равным 0")
    @Min(1)
    @Column(name = "EnclosureNum", nullable = false)
    private int enclosureNum;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "Status", nullable = false)
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Sterilization")
    private Date sterilization;

    @NotEmpty(message = "Это поле не может быть пустым")
    @Column(name = "VetClinicAddress")
    private String vetClinicAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FleaTreatment")
    private Date fleaTreatment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Deworming")
    private Date deworming;

    @Column(name = "NecessaryMedications")
    private String necessaryMedications;
}