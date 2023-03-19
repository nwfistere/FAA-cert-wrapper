package com.nickfistere.airmendatabase.airmencertification.PilotBasic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public interface PilotBasicRepositoryQueryInterface extends JpaRepository<PilotBasicQueryModel, Long> {

//    @PersistenceContext
//    EntityManager entityManager;

//    Page<PilotBasicQueryModel> getPilotByQuery(Pageable pageable, Map<String, String> query) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<PilotBasicQueryModel> criteriaQuery = criteriaBuilder.createQuery(PilotBasicQueryModel.class);
//
//        Root<PilotBasicQueryModel> root = criteriaQuery.from(PilotBasicQueryModel.class);
//        List<Predicate> predicates = new ArrayList<>();
//        for (Map.Entry<String, String> e : query.entrySet()) {
//            Predicate equal = criteriaBuilder.equal(root.get(e.getKey()), e.getValue());
//            predicates.add(equal);
//        }
//        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//        entityManager.createQuery(criteriaQuery)
//            .setMaxResults(pageable.getPageSize())
//            .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
//            .getResultList();
//    }
}
