package com.g3.order.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.g3.order.controller.dto.OrderDTO;
import com.g3.order.controller.form.OrderForm;
import com.g3.order.service.interfaces.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> ordersDTOs = orderService.getAllOrders();
		return ResponseEntity.ok(ordersDTOs);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderForm orderForm, UriComponentsBuilder uriBuilder){
		OrderDTO orderDTO = orderService.createOrder(orderForm);
		URI uri = uriBuilder.path("/order/{id}").buildAndExpand(orderDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(orderDTO);
	}
}