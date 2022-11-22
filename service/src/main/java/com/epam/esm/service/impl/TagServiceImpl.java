package com.epam.esm.service.impl;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.handler.AlreadyExistException;
import com.epam.esm.handler.ObjectNotFoundException;
import com.epam.esm.mapper.auth.TagMapper;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Class {@code TagServiceImpl} is implementation of interface {@link TagService}
 * and intended to work with {@link Tag} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public TagDto getById(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        validate(optionalTag);
        return tagMapper.toDto(optionalTag.get());
    }

    @Override
    public List<TagDto> getAll(PageRequest pageRequest) {
        List<Tag> all = tagRepository.findAll(pageRequest);
        return tagMapper.toDtoList(all);
    }

    @Override
    public TagDto create(TagCreateDto createEntity) {
        Optional<Tag> optionalTag = tagRepository.findByName(createEntity.getName());
        if (optionalTag.isPresent()) {
            throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
        }
        Tag savedTag = tagRepository.save(tagMapper.fromCreateDto(createEntity));
        return tagMapper.toDto(savedTag);
    }

    @Override
    public int delete(Long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        validate(optionalTag);
        Long deleteId = tagRepository.delete(optionalTag.get());
        return Objects.equals(optionalTag.get().getId(), deleteId) ? 1 : 0;
    }

    @Override
    public List<TagDto> doFilter(TagCriteria criteria, PageRequest pageable) {
        return tagMapper.toDtoList(tagRepository.find(criteria, pageable));
    }

    public List<TagDto> findMostTags() {
        return tagMapper.toDtoList(tagRepository.findMostPopular());
    }

    @Override
    public void validate(Optional<Tag> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, "Tag"));
        }
    }

}
