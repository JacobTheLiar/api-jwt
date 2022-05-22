package pl.jacob_the_liar.api.jwt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * * @date : 2022-05-22 18:46
 * *
 * * @className: ConfirmToken
 * *
 * *
 ******************************************************/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "${model.confirm.table}", schema = "${model.schema}")
public class ConfirmToken{
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "${model.confirm.id}", updatable = false)
    private Long id;
    
    @Column(updatable = false)
    private String token;
    
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime created;
    
    private LocalDateTime confirmed;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
