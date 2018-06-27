package com.bsdenterprise.qbits.policeactivity.persistence.message.impl;

import com.bsdenterprise.qbits.policeactivity.dto.OutputMessage;
import com.bsdenterprise.qbits.policeactivity.dto.ActivityMessages;
import com.bsdenterprise.qbits.policeactivity.model.MessageEntity;
import com.bsdenterprise.qbits.policeactivity.persistence.message.MessageRepositoryCustom;
import com.bsdenterprise.qbits.policeactivity.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ConvertUtils convertUtils;

    @Override
    public List<String> findActivities() {
        Query query = entityManager.createQuery("SELECT DISTINCT m.activityId FROM MessageEntity m");
        return query.getResultList();
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
    private ActivityMessages outputMessageDetail(String activityId) {

        ActivityMessages messageDetail = new ActivityMessages(activityId);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> q = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> c = q.from(MessageEntity.class);

        ParameterExpression<String> activityParameter = cb.parameter(String.class, "activityId");
        q.select(c).where(cb.equal(c.get("activityId"), activityParameter));
        List<MessageEntity> messages = entityManager.createQuery(q).setParameter("activityId", activityId).getResultList();

        messageDetail.getMessages().addAll(
                convertUtils.convert(messages, OutputMessage.class)
        );

        return messageDetail;
    }
    */

}
