package com.epam.esm.service.impl;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.handler.AlreadyExistException;
import com.epam.esm.handler.ObjectNotFoundException;
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

import static com.epam.esm.utils.BaseUtils.isEmptyList;
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
    public GiftCertificateDto create(GiftCertificateCreateDto createEntity) {

        Optional<GiftCertificate> certificateByName = giftCertificateRepository.findByName(createEntity.getName());

        if (certificateByName.isPresent()) {
            throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
        }

        GiftCertificate giftCertificate = giftCertificateMapper.fromCreateDto(createEntity);

        if (!isEmptyList(createEntity.getTagCreateDtoList())) {
            List<Tag> tagEntity = new ArrayList<>();
            List<TagCreateDto> createdTagList = createEntity.getTagCreateDtoList();
            for (TagCreateDto tagCreateDto : createdTagList) {
                addTagToList(tagEntity, tagCreateDto);
            }
            giftCertificate.setTagList(tagEntity);
        }
        return giftCertificateMapper.toDto(giftCertificateRepository.save(giftCertificate));
    }

    @Override
    public GiftCertificateDto update(GiftCertificateUpdateDto updateEntity) {
        Optional<GiftCertificate> giftCertificate = giftCertificateRepository.findById(updateEntity.getId());
        validate(giftCertificate);

        GiftCertificate updatedEntity = giftCertificateMapper.fromUpdateDto(updateEntity, giftCertificate.get());

        if (updateEntity.getTagCreateDtoList() != null) {
            List<Tag> allCreatedTags = updatedEntity.getTagList();
            List<Tag> tagCreatedList = getNotAttachedTags(updateEntity.getTagCreateDtoList(), allCreatedTags);
            updatedEntity.setTagList(tagCreatedList);
        }

        return giftCertificateMapper.toDto(giftCertificateRepository.update(updatedEntity));
    }

    @Override
    public int delete(Long id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(id);
        validate(optionalGiftCertificate);
        Long deleteId = giftCertificateRepository.delete(optionalGiftCertificate.get());
        return Objects.equals(optionalGiftCertificate.get().getId(), deleteId) ? 1 : 0;
    }

    @Override
    public List<GiftCertificateDto> doFilter(GiftCertificateCriteria criteria, PageRequest pageable) {
        return giftCertificateMapper.toDtoList(giftCertificateRepository.find(criteria, pageable));
    }

    @Transactional
    List<Tag> getNotAttachedTags(List<TagCreateDto> tagCreateDtoList, List<Tag> alreadyCreatedTags) {
        if (isEmptyList(tagCreateDtoList)) {
            return null;
        }
        if (!isEmptyList(alreadyCreatedTags)) {
            for (TagCreateDto request : tagCreateDtoList) {
                boolean isExist = false;
                for (Tag created : alreadyCreatedTags) {
                    if (Objects.equals(request.getName(), created.getName())) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
                }
                addTagToList(alreadyCreatedTags, request);
            }
        }
        return alreadyCreatedTags;
    }

    private void addTagToList(List<Tag> tagEntityList, TagCreateDto tag) {
        Optional<Tag> optionalTag = tagRepository.findByName(tag.getName());
        if (optionalTag.isEmpty()) {
            Tag savedTag = tagRepository.save(tagMapper.fromCreateDto(tag));
            tagEntityList.add(tagRepository.findById(savedTag.getId()).get());
        } else {
            tagEntityList.add(optionalTag.get());
        }
    }

    @Override
    public void validate(Optional<GiftCertificate> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, "Gift certificate"));
        }
    }
}
