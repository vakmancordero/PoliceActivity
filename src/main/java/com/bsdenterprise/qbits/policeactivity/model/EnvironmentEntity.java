package com.bsdenterprise.qbits.policeactivity.model;

import com.bsdenterprise.qbits.policeactivity.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Environment", schema = "qbits_police_activity")
public class EnvironmentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "environment")
    private List<MessageEntity> messages;

}
