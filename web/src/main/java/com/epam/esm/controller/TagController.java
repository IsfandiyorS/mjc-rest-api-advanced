package com.epam.esm.controller;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.hateoas.impl.TagHateoasAdderImpl;
import com.epam.esm.response.DataResponse;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Class {@code TagController} is an endpoint of the API which allows to perform CRD operations on tags.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/tag".
 * So that {@code TagController} is accessed by sending request to /tag.
 *
 * @author Sultonov Isfandiyor
 * @since 1.0
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagServiceImpl tagService;
    private final TagHateoasAdderImpl tagHateoasAdder;

    @Autowired
    public TagController(TagServiceImpl tagService, TagHateoasAdderImpl tagHateoasAdder) {
        this.tagService = tagService;
        this.tagHateoasAdder = tagHateoasAdder;
    }

    /**
     * Method for getting tag by ID.
     *
     * @param id ID of tag to get
     * @return ResponseEntity with found tag entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<TagDto>> getTagById(@PathVariable("id") Long id) {
        TagDto tagDto = tagService.getById(id);
        tagHateoasAdder.addLink(tagDto);
        return ResponseEntity.ok(new DataResponse<>(tagDto));
    }

    /**
     * Method for getting all tags from data source.
     *
     * @return ResponseEntity body with list of found tags
     */
    @GetMapping("/")
    public ResponseEntity<DataResponse<List<TagDto>>> getAll(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        List<TagDto> tagDtoList = tagService.getAll(PageRequest.of(pageNumber, pageSize));
        tagHateoasAdder.addLink(tagDtoList);
        return ResponseEntity.ok(new DataResponse<>(tagDtoList));
    }

    /**
     * Method for saving new tag.
     *
     * @param entity tag for saving
     * @return ResponseEntity with Long value which returns ID of created
     */
    @PostMapping("/create")
    public ResponseEntity<DataResponse<TagDto>> create(@Valid @RequestBody TagCreateDto entity) {
        TagDto tagDto = tagService.create(entity);
        tagHateoasAdder.addLink(tagDto);
        return ResponseEntity.ok(new DataResponse<>(tagDto));
    }

    /**
     * Method for removing tag by ID.
     *
     * @param id ID of tag to remove
     * @return ResponseEntity with Boolean body
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<Integer>> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new DataResponse<>(tagService.delete(id)));
    }

    /**
     * Method for getting list of tags from data source by special filter.
     *
     * @param criteria request parameters, which include the information needed for the search
     * @return ResponseEntity with List of found tags
     */
    @GetMapping("/filter")
    public ResponseEntity<DataResponse<List<TagDto>>> getTagsByFilterParams(
            @RequestBody TagCriteria criteria,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        List<TagDto> tagDtoList = tagService.doFilter(criteria, PageRequest.of(pageNumber, pageSize));
        tagHateoasAdder.addLink(tagDtoList);
        return ResponseEntity.ok(new DataResponse<>(tagDtoList));
    }

    @GetMapping("/most_popular")
    public ResponseEntity<DataResponse<List<TagDto>>> findMostPopular() {
        List<TagDto> certificateDtoList = tagService.findMostTags();
        tagHateoasAdder.addLink(certificateDtoList);
        return ResponseEntity.ok(new DataResponse<>(certificateDtoList));
    }
}
