package com.develhope.drbuddy.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "secretary")
public class Secretary extends Person {
    public Secretary(){};


}
