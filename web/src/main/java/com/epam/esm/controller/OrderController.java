package com.epam.esm.controller;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.dto.certificate.OrderCreateDto;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.dto.certificate.OrderUpdateDto;
import com.epam.esm.hateoas.domain.OrderHateoasAdder;
import com.epam.esm.response.DataResponse;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderHateoasAdder orderHateoasAdder;

    @Autowired
    public OrderController(OrderService orderService, OrderHateoasAdder orderHateoasAdder) {
        this.orderService = orderService;
        this.orderHateoasAdder = orderHateoasAdder;
    }

    /**
     * Method for getting gift certificate by ID.
     *
     * @param id ID of gift certificate to get.
     * @return ResponseEntity body with found gift certificate.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<OrderDto>> getById(@PathVariable("id") Long id) {
        OrderDto orderDto = orderService.getById(id);
        orderHateoasAdder.addLink(orderDto);
        return ResponseEntity.ok(new DataResponse<>(orderDto));
    }

    /**
     * Method for getting all gift certificates from data source.
     *
     * @return ResponseEntity body with list of found gift certificates
     */
    @GetMapping("/")
    public ResponseEntity<DataResponse<List<OrderDto>>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<OrderDto> orderDtoList = orderService.getAll(PageRequest.of(pageNumber, pageSize));
        orderHateoasAdder.addLink(orderDtoList);
        return ResponseEntity.ok(new DataResponse<>(orderDtoList));
    }


    /**
     * Method for saving new gift certificate.
     *
     * @param entity gift certificate for saving
     * @return ResponseEntity with Long value which returns ID of created gift
     */
    @PostMapping("/create")
    public ResponseEntity<DataResponse<Long>> create(@Valid @RequestBody OrderCreateDto entity) {
        return ResponseEntity.ok(new DataResponse<>(orderService.create(entity)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<DataResponse<List<OrderDto>>> getOrdersByUserId(
            @PathVariable("id") Long userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<OrderDto> orderDtoList = orderService.getOrderByUserId(PageRequest.of(pageNumber, pageSize), userId);
        orderHateoasAdder.addLink(orderDtoList);
        return ResponseEntity.ok(new DataResponse<>(orderDtoList));
    }

    @GetMapping("/{order_id}/user/{user_id}")
    public ResponseEntity<DataResponse<OrderDto>> getOrderByIdAndUserId(
            @PathVariable("order_id") Long orderId,
            @PathVariable("user_id") Long userId) {
        OrderDto orderDtoList = orderService.getOrderByIdAndUserId(orderId, userId);
        orderHateoasAdder.addLink(orderDtoList);
        return ResponseEntity.ok(new DataResponse<>(orderDtoList));
    }

    /**
     * Method for updating tags from the gift certificate.
     *
     * @param updateEntity gift certificate entity, which include information to update with its ID
     * @return ResponseEntity with Boolean which indicate the gift is created
     */
    @PatchMapping("/update")
    public ResponseEntity<DataResponse<String>> update(@Valid @RequestBody OrderUpdateDto updateEntity) {
        orderService.update(updateEntity);
        return ResponseEntity.ok(new DataResponse<>("Successfully updated"));
    }

    /**
     * Method for removing gift certificate by ID.
     *
     * @param id ID of gift certificate to remove
     * @return ResponseEntity with Boolean body
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<Integer>> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new DataResponse<>(orderService.delete(id)));
    }


    // fixme it is not work

    /**
     * Method for getting list of gift certificates from data source by special filter.
     *
     * @param criteria request parameters, which include the information needed for the search
     * @return ResponseEntity with List of found gift certificates
     */
    @GetMapping("/filter")
    public ResponseEntity<DataResponse<List<OrderDto>>> getOrderByFilterParams(
            @RequestBody OrderCriteria criteria,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<OrderDto> orderDtoList = orderService.doFilter(criteria, PageRequest.of(pageNumber, pageSize));
        orderHateoasAdder.addLink(orderDtoList);
        return ResponseEntity.ok(new DataResponse<>(orderDtoList));
    }
}