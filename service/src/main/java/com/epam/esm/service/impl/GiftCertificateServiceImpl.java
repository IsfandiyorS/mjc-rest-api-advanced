package com.epam.esm.service.impl;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.exceptions.ValidationException;
import com.epam.esm.mapper.auth.GiftCertificateMapper;
import com.epam.esm.mapper.auth.TagMapper;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.impl.TagRepositoryImpl;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Class {@code GiftCertificateServiceImpl} is implementation of interface {@link GiftCertificateService}
 * and intended to work with {@link GiftCertificate} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;
    private final TagRepository tagRepository;
    private final GiftCertificateMapper giftCertificateMapper;
    private final TagMapper tagMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository, TagRepositoryImpl tagRepository, GiftCertificateMapper giftCertificateMapper, TagMapper tagMapper) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagRepository;
        this.giftCertificateMapper = giftCertificateMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public GiftCertificateDto getById(Long id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(id);
        validate(optionalGiftCertificate);
        return giftCertificateMapper.toDto(optionalGiftCertificate.get());
    }

    @Override
    public List<GiftCertificateDto> getAll(PageRequest pageRequest) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository.findAll(pageRequest));
    }

    @Override
    public Long create(GiftCertificateCreateDto createEntity) {

        Optional<GiftCertificate> certificateByName = giftCertificateRepository.findByName(createEntity.getName());

        if (certificateByName.isPresent()) {
            throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
        }

        List<Tag> tagEntity = new ArrayList<>();
        if (!(createEntity.getTagCreateDtoList() == null || createEntity.getTagCreateDtoList().isEmpty())) {
            List<TagCreateDto> createdTagList = createEntity.getTagCreateDtoList();
            for (TagCreateDto tag : createdTagList) {
                Optional<Tag> optionalTag = tagRepository.findByName(tag.getName());

                if (optionalTag.isEmpty()) {
                    Long savedTagId = tagRepository.save(tagMapper.fromCreateDto(tag));
                    tagEntity.add(tagRepository.findById(savedTagId).get());
                } else {
                    tagEntity.add(optionalTag.get());
                }
            }
        }

        GiftCertificate giftCertificate = giftCertificateMapper.fromCreateDto(createEntity);
        giftCertificate.setTagList(tagEntity);
        return giftCertificateRepository.save(giftCertificate);
    }

    @Override
    public void update(GiftCertificateUpdateDto updateEntity) {

        if (updateEntity.getId() == null) {
            throw new ValidationException(format(ErrorCodes.OBJECT_ID_REQUIRED.message));
        }

        Optional<GiftCertificate> giftCertificate = giftCertificateRepository.findById(updateEntity.getId());
        validate(giftCertificate);

        GiftCertificate updatedEntity = giftCertificateMapper.fromUpdateDto(updateEntity, giftCertificate.get());

        List<Tag> tagCreatedList = new ArrayList<>();
        if (updateEntity.getTagCreateDtoList() != null) {
            List<Tag> allCreatedTags = updatedEntity.getTagList();
            tagCreatedList = getTagsNotAttachedTags(updateEntity.getTagCreateDtoList(), allCreatedTags);
        }

        giftCertificateRepository.update(updatedEntity);
        giftCertificateRepository.attachTagToGiftCertificate(tagCreatedList, updatedEntity.getId());
    }

    // fixme the result return the null value
    @Override
    public int delete(Long id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(id);
        validate(optionalGiftCertificate);
        Long deleteId = giftCertificateRepository.delete(optionalGiftCertificate.get());
        return Objects.equals(optionalGiftCertificate.get().getId(), deleteId)?1:0;
    }

    @Override
    public List<GiftCertificateDto> doFilter(GiftCertificateCriteria criteria, PageRequest pageable) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository.find(criteria, pageable));
    }

    @Override
    public void validate(Optional<GiftCertificate> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, "Gift certificate"));
        }
    }

    @Transactional
    List<Tag> getTagsNotAttachedTags(List<TagCreateDto> requestTags, List<Tag> allCreatedNewTags) {
        List<Tag> tagEntity = new ArrayList<>();
        if (requestTags == null) {
            return null;
        }
        if (allCreatedNewTags == null || allCreatedNewTags.isEmpty()) {
            List<Long> idList = new ArrayList<>();
            requestTags.forEach(tagCreateDto -> idList.add(tagRepository.save(tagMapper.fromCreateDto(tagCreateDto))));

            idList.forEach(id -> tagEntity.add(tagRepository.findById(id).get()));
            return tagEntity;
        } else {
            for (TagCreateDto request : requestTags) {
                boolean isExist = false;
                for (Tag created : allCreatedNewTags) {
                    if (Objects.equals(request.getName(), created.getName())) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
                }
                Optional<Tag> optionalTag = tagRepository.findByName(request.getName());
                Long savedTagId;
                if (optionalTag.isEmpty()) {
                    savedTagId = tagRepository.save(tagMapper.fromCreateDto(request));
                    tagEntity.add(tagRepository.findById(savedTagId).get());
                } else {
                    tagEntity.add(optionalTag.get());
                }
            }
        }
        return tagEntity;
    }
}
