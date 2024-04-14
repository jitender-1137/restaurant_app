package com.ms.restaurant.dto;

import com.ms.restaurant.enums.ComparativeRelation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComparativeRelationAndValue {
	private Object value;
	private ComparativeRelation comparativeRelation;
}