package com.ms.restaurant.dao;

import com.ms.restaurant.domains.User;
import com.ms.restaurant.enums.QueriesCombinationType;
import com.ms.restaurant.jpaRepo.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserDao {

    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager entityManager;

    public User save(User User) {
        return userRepository.save(User);
    }

    public List<User> save(List<User> Users) {
        return userRepository.saveAll(Users);
    }

//    public EntryItem<User> findByCriteriaFields(GenericSearchFilter genericSearchFilter,
//                                                   Integer pageNumber, Integer pageSize) {
//        List<User> Users;
//        Map<String, ComparativeRelationAndValue> searchParams = genericSearchFilter.getSearchParams();
//        log.info("Finding by criteria fields in dao as passed");
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> UserRoot = cq.from(User.class);
//        CriteriaQuery<Long> countCQ = cb.createQuery(Long.class);
//        countCQ.select(cb.count(countCQ.from(User.class)));
//        List<Predicate> predicates = new LinkedList<>();
//        for (Map.Entry<String, ComparativeRelationAndValue> entry : searchParams.entrySet()) {
//            UtilityMethods.fillCriteriaQuery(cb, entry, UserRoot, predicates);
//        }
//        processQueriesCombination(genericSearchFilter.getCombinationType(), predicates, cb, cq, countCQ);
//        List<Order> orderBys = UtilityMethods.getOrderBys(cb, UserRoot, genericSearchFilter.getOrderBy());
//        cq.orderBy(orderBys);
//        TypedQuery<User> query = entityManager.createQuery(cq);
//        Long count = entityManager.createQuery(countCQ).getSingleResult();
//        if (pageNumber == null) pageNumber = 0;
//        if (pageSize == null || pageSize == 0) pageSize = 10;
//        int offset = pageNumber * pageSize;
//        Users = query.setFirstResult(offset).setMaxResults(pageSize).getResultList();
//        return new EntryItem<>(count, Users);
//    }

    private void processQueriesCombination(QueriesCombinationType combinationType, List<Predicate> predicates, CriteriaBuilder cb, CriteriaQuery<User> cq, CriteriaQuery<Long> countCQ) {

        if (combinationType== QueriesCombinationType.all_and) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            countCQ.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        } else {
            cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
            countCQ.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        }
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
