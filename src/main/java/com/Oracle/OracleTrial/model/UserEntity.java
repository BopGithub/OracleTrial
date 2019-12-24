package com.Oracle.OracleTrial.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class UserEntity {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_NAME", nullable = true, length = 255)
    private String name;

    @Column(name = "USER_SALARY", nullable = true, length = 10)
    private Integer salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public UserEntity() {

    }

    public UserEntity(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }
}


