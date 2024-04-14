package com.ms.restaurant.dao;

import com.ms.restaurant.domains.Role;
import com.ms.restaurant.enums.QueriesCombinationType;
import com.ms.restaurant.jpaRepo.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RoleDao {

    @Autowired
    RoleRepository roleRepository;
    @PersistenceContext
    EntityManager entityManager;

    public Role save(Role Role) {
        return roleRepository.save(Role);
    }

    public List<Role> save(List<Role> Roles) {
        return roleRepository.saveAll(Roles);
    }

//    public EntryItem<Role> findByCriteriaFields(GenericSearchFilter genericSearchFilter,
//                                                   Integer pageNumber, Integer pageSize) {
//        List<Role> Roles;
//        Map<String, ComparativeRelationAndValue> searchParams = genericSearchFilter.getSearchParams();
//        log.info("Finding by criteria fields in dao as passed");
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
//        Root<Role> RoleRoot = cq.from(Role.class);
//        CriteriaQuery<Long> countCQ = cb.createQuery(Long.class);
//        countCQ.select(cb.count(countCQ.from(Role.class)));
//        List<Predicate> predicates = new LinkedList<>();
//        for (Map.Entry<String, ComparativeRelationAndValue> entry : searchParams.entrySet()) {
//            UtilityMethods.fillCriteriaQuery(cb, entry, RoleRoot, predicates);
//        }
//        processQueriesCombination(genericSearchFilter.getCombinationType(), predicates, cb, cq, countCQ);
//        List<Order> orderBys = UtilityMethods.getOrderBys(cb, RoleRoot, genericSearchFilter.getOrderBy());
//        cq.orderBy(orderBys);
//        TypedQuery<Role> query = entityManager.createQuery(cq);
//        Long count = entityManager.createQuery(countCQ).getSingleResult();
//        if (pageNumber == null) pageNumber = 0;
//        if (pageSize == null || pageSize == 0) pageSize = 10;
//        int offset = pageNumber * pageSize;
//        Roles = query.setFirstResult(offset).setMaxResults(pageSize).getResultList();
//        return new EntryItem<>(count, Roles);
//    }

    private void processQueriesCombination(QueriesCombinationType combinationType, List<Predicate> predicates, CriteriaBuilder cb, CriteriaQuery<Role> cq, CriteriaQuery<Long> countCQ) {

        if (combinationType== QueriesCombinationType.all_and) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            countCQ.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        } else {
            cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
            countCQ.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        }
    }


    public Role findById(Long id) {
        return roleRepository.findById(Math.toIntExact(id)).orElse(null);
    }
}
