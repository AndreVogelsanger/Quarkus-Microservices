package org.acme.service;

import org.acme.client.CustomerClient;
import org.acme.client.ProductClient;
import org.acme.dto.CustomerDTO;
import org.acme.dto.OrderDTO;
import org.acme.entity.OrderEntity;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    @RestClient
    CustomerClient customerClient;

    @Inject
    @RestClient
    ProductClient productClient;


    public List<OrderDTO> getAllOrders(){

        List<OrderDTO> orders = new ArrayList<>();

        orderRepository.findAll().stream().forEach(item ->{
            orders.add(mapOrderEntityToDto(item));
        });

        return orders;
    }


    public void saveNewOrder(OrderDTO orderDTO){

        var customerDto = customerClient.getCustomerById(orderDTO.getCustomerId());

        if( customerDto.getName().equals(orderDTO.getCustomerName())
            && productClient.getCustomerById(orderDTO.getCustomerId()) != null ){
            orderRepository.persist(mapOrderDtoToEntity(orderDTO));
        }else {
            throw new NotFoundException();
        }
    }


    private OrderDTO mapOrderEntityToDto(OrderEntity orderEntity){

        var orderDto = new OrderDTO();

        orderDto.setCustomerId(orderEntity.getCustomerId());
        orderDto.setCustomerName(orderEntity.getCustomerName());
        orderDto.setProductId(orderEntity.getProductId());
        orderDto.setOrderValue(orderEntity.getOrderValue());

        return orderDto;

    }

    private OrderEntity mapOrderDtoToEntity(OrderDTO orderDto){

        var orderEntity = new OrderEntity();

        orderEntity.setCustomerId(orderDto.getCustomerId());
        orderEntity.setCustomerName(orderDto.getCustomerName());
        orderEntity.setProductId(orderDto.getProductId());
        orderEntity.setOrderValue(orderDto.getOrderValue());

        return orderEntity;

    }



}
