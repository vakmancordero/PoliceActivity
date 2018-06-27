package com.bsdenterprise.qbits.policeactivity.persistence.message;

import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>, MessageRepositoryCustom {

}
