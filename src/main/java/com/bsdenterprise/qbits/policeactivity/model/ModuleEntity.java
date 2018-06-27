package com.bsdenterprise.qbits.policeactivity.model;

import com.bsdenterprise.qbits.policeactivity.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Module", schema = "qbits_police_activity")
public class ModuleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "module")
    private List<MessageEntity> messages;

}
