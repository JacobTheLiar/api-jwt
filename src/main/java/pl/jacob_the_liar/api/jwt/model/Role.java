package pl.jacob_the_liar.api.jwt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * * @date : 2022-05-22 16:45
 * *
 * * @className: Role
 * *
 * *
 ******************************************************/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "${model.user.table}", schema = "${model.schema}",
       uniqueConstraints = {@UniqueConstraint(columnNames = {User.UNIQUE_USERNAME}), @UniqueConstraint(
               columnNames = {User.UNIQUE_EMAIL})})

public class Role{
    
    @Id
    @Column(name = "${model.role.id}", updatable = false)
    private String name;
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String description;
}
