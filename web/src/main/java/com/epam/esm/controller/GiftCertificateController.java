package com.epam.esm.controller;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;
import com.epam.esm.hateoas.domain.GiftCertificateHateoasAdder;
import com.epam.esm.response.DataResponse;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class {@code GiftCertificateController} is an endpoint of the API
 * which allows to perform CRUD operations on gift certificates.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/gift".
 * So that {@code GiftCertificateController} is accessed by sending request to /gift.
 *
 * @author Sultonov Isfandiyor
 * @since 1.0
 */
@RestController
@RequestMapping("/gift")
public class GiftCertificateController {

    private final GiftCertificateServiceImpl giftCertificateService;
    private final GiftCertificateHateoasAdder giftCertificateHateoasAdder;

    @Autowired
    public GiftCertificateController(GiftCertificateServiceImpl giftCertificateService, GiftCertificateHateoasAdder giftCertificateHateoasAdder) {
        this.giftCertificateService = giftCertificateService;
        this.giftCertificateHateoasAdder = giftCertificateHateoasAdder;
    }

    /**
     * Method for getting gift certificate by ID.
     *
     * @param id ID of gift certificate to get.
     * @return ResponseEntity body with found gift certificate.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<GiftCertificateDto>> getById(@PathVariable("id") Long id) {
        GiftCertificateDto giftCertificateDto = giftCertificateService.getById(id);
        giftCertificateHateoasAdder.addLink(giftCertificateDto);
        return ResponseEntity.ok(new DataResponse<>(giftCertificateDto));
    }

    /**
     * Method for getting all gift certificates from data source.
     *
     * @return ResponseEntity body with list of found gift certificates
     */
    @GetMapping("/")
    public ResponseEntity<DataResponse<List<GiftCertificateDto>>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        List<GiftCertificateDto> giftCertificateDtoList = giftCertificateService.getAll(PageRequest.of(pageNumber, pageSize));
        giftCertificateHateoasAdder.addLink(giftCertificateDtoList);
        return ResponseEntity.ok(new DataResponse<>(giftCertificateDtoList));
    }


    /**
     * Method for saving new gift certificate.
     *
     * @param entity gift certificate for saving
     * @return ResponseEntity with Long value which returns ID of created gift
     */
    @PostMapping("/create")
    public ResponseEntity<DataResponse<GiftCertificateDto>> create(@Valid @RequestBody GiftCertificateCreateDto entity) {
        GiftCertificateDto giftCertificateDto = giftCertificateService.create(entity);
        giftCertificateHateoasAdder.addLink(giftCertificateDto);
        return ResponseEntity.ok(new DataResponse<>(giftCertificateDto));
    }

    /**
     * Method for updating tags from the gift certificate.
     *
     * @param updateEntity gift certificate entity, which include information to update with its ID
     * @return ResponseEntity with Boolean which indicate the gift is created
     */
    @PutMapping("/update")
    public ResponseEntity<DataResponse<GiftCertificateDto>> update(@Valid @RequestBody GiftCertificateUpdateDto updateEntity) {
        GiftCertificateDto updatedCertificate = giftCertificateService.update(updateEntity);
        giftCertificateHateoasAdder.addLink(updatedCertificate);
        return ResponseEntity.ok(new DataResponse<>(updatedCertificate));
    }

    /**
     * Method for removing gift certificate by ID.
     *
     * @param id ID of gift certificate to remove
     * @return ResponseEntity with Boolean body
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<Integer>> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new DataResponse<>(giftCertificateService.delete(id)));
    }

    /**
     * Method for getting list of gift certificates from data source by special filter.
     *
     * @param criteria request parameters, which include the information needed for the search
     * @return ResponseEntity with List of found gift certificates
     */
    @GetMapping("/filter")
    public ResponseEntity<DataResponse<List<GiftCertificateDto>>> getGiftCertificateByFilterParams(
            @RequestBody GiftCertificateCriteria criteria,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        List<GiftCertificateDto> certificateDtoList = giftCertificateService.doFilter(criteria, PageRequest.of(pageNumber, pageSize));
        giftCertificateHateoasAdder.addLink(certificateDtoList);
        return ResponseEntity.ok(new DataResponse<>(certificateDtoList));
    }
}
