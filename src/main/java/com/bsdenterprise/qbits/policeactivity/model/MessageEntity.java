package com.bsdenterprise.qbits.policeactivity.model;

import com.bsdenterprise.qbits.policeactivity.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Message", schema = "qbits_police_activity")
public class MessageEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name="module_id", nullable = false)
    private ModuleEntity module;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String activityId;

    @Column(nullable = false)
    private String environment;

}
