package com.example.asm1.search;

import com.example.asm1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getOperation().equalsIgnoreCase(">=")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), String.valueOf(searchCriteria.getValue()));
        } else if (searchCriteria.getOperation().equalsIgnoreCase("<=")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), String.valueOf(searchCriteria.getValue()));
        } else if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), String.valueOf(searchCriteria.getValue()));
        } else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), String.valueOf(searchCriteria.getValue()));
        } else if (searchCriteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }
        }
        return null;
    }
}
