package com.example.automobileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "LOGIN_SUCCESS")
@Entity
public class LoginSuccess {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private UserEntity user;

    @Column(name = "SOURCE_IP")
    private String sourceIp;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private OffsetDateTime createdDate;


    @UpdateTimestamp
    @Column(name = "LAST_MODIFIED_AT")
    private OffsetDateTime lastModifiedDate;
}