package com.danaga.entity;

import java.sql.*;
import java.time.*;

import org.hibernate.annotations.*;

import com.danaga.dto.*;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "orderItem")  
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Long id;
	@Column(length = 10)
	private Integer qty;

@ToString.Exclude	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders orders;
	
@ToString.Exclude	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "optionSetId")
	private OptionSet optionSet;

	public static OrderItem toResponseEntity(OrderItemDto orderItemDto) {
		return OrderItem.builder()
						.qty(orderItemDto.getQty())
						.build();
	}
}
