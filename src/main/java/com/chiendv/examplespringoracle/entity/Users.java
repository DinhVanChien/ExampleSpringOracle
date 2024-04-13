package com.chiendv.examplespringoracle.entity;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "GROUPS")
    private String groups;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
