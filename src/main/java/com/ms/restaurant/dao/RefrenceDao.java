//package com.ms.restaurant.dao;
//
//import com.ms.restaurant.domains.Address;
//import com.ms.restaurant.dto.ComparativeRelationAndValue;
//import com.ms.restaurant.dto.EntryItem;
//import com.ms.restaurant.dto.GenericSearchFilter;
//import com.ms.restaurant.enums.QueriesCombinationType;
//import com.ms.restaurant.jparepositories.AddressRepository;
//import com.ms.restaurant.utility.UtilityMethods;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.persistence.criteria.*;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class RefrenceDao {
//	@Autowired
//	AddressRepository addressRepository;
//	@PersistenceContext
//	EntityManager entityManager;
//
//	public Address save(Address address) {
//		return addressRepository.save(address);
//	}
//
//	public List<Address> save(List<Address> addresss) {
//		return addressRepository.saveAll(addresss);
//	}
//
//	public EntryItem<Address> findByCriteriaFields(GenericSearchFilter genericSearchFilter,
//																Integer pageNumber, Integer pageSize) {
//		List<Address> addresss;
//		Map<String, ComparativeRelationAndValue> searchParams = genericSearchFilter.getSearchParams();
//		log.info("Finding by criteria fields in dao as passed");
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
//		Root<Address> addressRoot = cq.from(Address.class);
//		CriteriaQuery<Long> countCQ = cb.createQuery(Long.class);
//		countCQ.select(cb.count(countCQ.from(Address.class)));
//		List<Predicate> predicates = new LinkedList<>();
//		for (Map.Entry<String, ComparativeRelationAndValue> entry : searchParams.entrySet()) {
//			UtilityMethods.fillCriteriaQuery(cb, entry, addressRoot, predicates);
//		}
//		processQueriesCombination(genericSearchFilter.getCombinationType(), predicates, cb, cq, countCQ);
//		List<Order> orderBys = UtilityMethods.getOrderBys(cb, addressRoot, genericSearchFilter.getOrderBy());
//		cq.orderBy(orderBys);
//		TypedQuery<Address> query = entityManager.createQuery(cq);
//		Long count = entityManager.createQuery(countCQ).getSingleResult();
//		if (pageNumber == null) pageNumber = 0;
//		if (pageSize == null || pageSize == 0) pageSize = 10;
//		int offset = pageNumber * pageSize;
//		addresss = query.setFirstResult(offset).setMaxResults(pageSize).getResultList();
//		return new EntryItem<>(count, addresss);
//	}
//
//	private void processQueriesCombination(QueriesCombinationType combinationType, List<Predicate> predicates, CriteriaBuilder cb, CriteriaQuery<Address> cq, CriteriaQuery<Long> countCQ) {
//
//		if (combinationType== QueriesCombinationType.all_and) {
//			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//			countCQ.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//		} else {
//			cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
//			countCQ.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
//		}
//	}
//
//
//	public Address findById(Long id) {
//		return addressRepository.findById(id).orElse(null);
//	}
//}
