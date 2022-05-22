package pl.jacob_the_liar.api.jwt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * * @date : 2022-04-05 19:43
 * *
 * * @className: User
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
public class User{
    
    public static final String UNIQUE_USERNAME = "username";
    public static final String UNIQUE_EMAIL = "email";
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "${model.user.id}", updatable = false)
    private Long id;
    
    @NotBlank
    @Size(max = 50, min = 3)
    private String username;
    
    @NotBlank
    @Size(max = 50, min = 5)
    @Email
    private String email;
    
    @NotBlank
    @Size(max = 250)
    private String password;
    
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime created;
    
    @LastModifiedDate
    private LocalDateTime modified;
    
    private LocalDateTime lastInvalidPassword;
    
    private LocalDateTime lastLocked;
    
    private LocalDateTime disabled;
    
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "${model.user.table}" + "_" + "${model.role.table}",
               joinColumns = {@JoinColumn(name = "${model.user.id}", updatable = false, nullable = false)},
               inverseJoinColumns = {@JoinColumn(name = "${model.role.id}", updatable = false, nullable = false)})
    private Set<Role> roles;
}
