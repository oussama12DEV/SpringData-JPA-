package com.emsi.jpaap.entiti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class patient {

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(length = 50)
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date datenaissance;
    private boolean malade;
    private int score;

}
