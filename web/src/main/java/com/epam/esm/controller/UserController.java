package com.epam.esm.controller;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;
import com.epam.esm.hateoas.impl.UserHateoasAdderImpl;
import com.epam.esm.response.DataResponse;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserHateoasAdderImpl userHateoasAdder;

    @Autowired
    public UserController(UserService userService, UserHateoasAdderImpl userHateoasAdder) {
        this.userService = userService;
        this.userHateoasAdder = userHateoasAdder;
    }

    /**
     * Method for getting gift certificate by ID.
     *
     * @param id ID of gift certificate to get.
     * @return ResponseEntity body with found gift certificate.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<UserDto>> getById(@PathVariable("id") Long id) {
        UserDto userDto = userService.getById(id);
        userHateoasAdder.addLink(userDto);
        return ResponseEntity.ok(new DataResponse<>(userDto));
    }

    /**
     * Method for getting all gift certificates from data source.
     *
     * @return ResponseEntity body with list of found gift certificates
     */
    @GetMapping("/")
    public ResponseEntity<DataResponse<List<UserDto>>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<UserDto> userDtoList = userService.getAll(PageRequest.of(pageNumber, pageSize));
        userHateoasAdder.addLink(userDtoList);
        return ResponseEntity.ok(new DataResponse<>(userDtoList));
    }


    /**
     * Method for saving new gift certificate.
     *
     * @param entity gift certificate for saving
     * @return ResponseEntity with Long value which returns ID of created gift
     */
    @PostMapping("/create")
    public ResponseEntity<DataResponse<UserDto>> create(@Valid @RequestBody UserCreateDto entity) {
        UserDto userDto = userService.create(entity);
        userHateoasAdder.addLink(userDto);
        return ResponseEntity.ok(new DataResponse<>(userDto));
    }

    /**
     * Method for updating tags from the gift certificate.
     *
     * @param updateEntity gift certificate entity, which include information to update with its ID
     * @return ResponseEntity with Boolean which indicate the gift is created
     */
    @PutMapping("/update")
    public ResponseEntity<DataResponse<UserDto>> update(@Valid @RequestBody UserUpdateDto updateEntity) {
        UserDto updatedUser = userService.update(updateEntity);
        userHateoasAdder.addLink(updatedUser);
        return ResponseEntity.ok(new DataResponse<>(updatedUser));
    }

    /**
     * Method for removing gift certificate by ID.
     *
     * @param id ID of gift certificate to remove
     * @return ResponseEntity with Boolean body
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<Integer>> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new DataResponse<>(userService.delete(id)));
    }

    /**
     * Method for getting list of gift certificates from data source by special filter.
     *
     * @param criteria request parameters, which include the information needed for the search
     * @return ResponseEntity with List of found gift certificates
     */
    @GetMapping("/filter")
    public ResponseEntity<DataResponse<List<UserDto>>> getUserByFilterParams(
            @RequestBody UserCriteria criteria,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        List<UserDto> userDtoList = userService.doFilter(criteria, PageRequest.of(pageNumber, pageSize));
        userHateoasAdder.addLink(userDtoList);
        return ResponseEntity.ok(new DataResponse<>(userDtoList));
    }

}
