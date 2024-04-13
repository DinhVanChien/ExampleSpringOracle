package com.chiendv.examplespringoracle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOG_ACTION")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LogAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "total")
    private Integer total;
}
