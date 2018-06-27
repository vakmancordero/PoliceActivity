package com.bsdenterprise.qbits.policeactivity.persistence.message.impl;

import com.bsdenterprise.qbits.policeactivity.dto.ActivityDTO;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepositoryCustom;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ActivityDTO> findActivities() {

        TypedQuery<ActivityDTO> typedQuery = entityManager.createQuery(
                "select distinct new com.bsdenterprise.qbits.policeactivity.dto.ActivityDTO(m.activityId, m.environment) from MessageEntity m",
                ActivityDTO.class
        );

        return typedQuery.getResultList();
    }

    @Override
    public Optional<ActivityDTO> findActivityById(String activityId) {
        TypedQuery<ActivityDTO> query = entityManager.createQuery("SELECT m.activityId, m.environment FROM MessageEntity m WHERE m.activityId = :activityId", ActivityDTO.class);
        return Optional.of(query.setParameter("activityId", activityId).getSingleResult());
    }

    @Override
    public List<MessageEntity> findMessageDetail(String activityId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> criteriaQuery = criteriaBuilder.createQuery(MessageEntity.class);
        Root<MessageEntity> messageRoot = criteriaQuery.from(MessageEntity.class);
        criteriaQuery.select(messageRoot).where(criteriaBuilder.equal(messageRoot.get("activityId"), activityId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /*
    private ActivityMessages outputMessageDetail(String activity) {

        ActivityMessages messageDetail = new ActivityMessages(activity);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> q = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> c = q.from(MessageEntity.class);

        ParameterExpression<String> activityParameter = cb.parameter(String.class, "activity");
        q.select(c).where(cb.equal(c.get("activity"), activityParameter));
        List<MessageEntity> messages = entityManager.createQuery(q).setParameter("activity", activity).getResultList();

        messageDetail.getMessages().addAll(
                convertUtils.convert(messages, OutputMessage.class)
        );

        return messageDetail;
    }
    */

}
