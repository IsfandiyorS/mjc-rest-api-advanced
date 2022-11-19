package com.epam.esm.service.impl;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.GiftCertificateMapper;
import com.epam.esm.mapper.auth.GiftCertificateMapperImpl;
import com.epam.esm.mapper.auth.TagMapper;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.impl.model.TagModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.service.impl.model.GiftCertificateModel.*;
import static com.epam.esm.service.impl.model.TagModel.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    @InjectMocks
    private GiftCertificateServiceImpl service;

    @Mock
    private GiftCertificateRepository giftCertificateRepository = mock(GiftCertificateRepository.class);

    @Mock
    private TagRepository tagRepository = mock(TagRepository.class);

    @Spy
    private TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

    @Spy
    private GiftCertificateMapper giftCertificateMapper = new GiftCertificateMapperImpl(tagMapper);

    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 5);

    @Test
    void getById() {
        when(giftCertificateRepository.findById(CERTIFICATE_ID)).thenReturn(Optional.of(ENTITY));

        GiftCertificateDto actualId = service.getById(CERTIFICATE_ID);
        assertNotNull(actualId);
        verify(giftCertificateRepository, times(1)).findById(CERTIFICATE_ID);
    }

    @Test
    void getAll() {
        when(giftCertificateRepository.findAll(PAGE_REQUEST)).thenReturn(anyList());

        List<GiftCertificateDto> actualDto = service.getAll(PAGE_REQUEST);

        assertNotNull(actualDto);
        verify(giftCertificateRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void create() {
        when(giftCertificateRepository.findByName(NEW_GIFT_CERTIFICATE.getName())).thenReturn(Optional.empty());
        when(giftCertificateRepository.save(any(GiftCertificate.class))).thenReturn(CREATED_GIFT_CERTIFICATE);

        GiftCertificateDto actualDto = service.create(CREATE_DTO);

        assertEquals(CREATED_GIFT_CERTIFICATE.getName(), actualDto.getName());
        verify(giftCertificateRepository, times(1)).save(any(GiftCertificate.class));
    }

    @Test
    void testUpdate() {
        when(giftCertificateRepository.findById(GIFT_CERTIFICATE_UPDATE_DTO.getId())).thenReturn(Optional.of(ENTITY));
        when(giftCertificateRepository.update(any(GiftCertificate.class))).thenReturn(UPDATED_GIFT_CERTIFICATE);

        GiftCertificateDto actualDto = service.update(GIFT_CERTIFICATE_UPDATE_DTO);
        assertEquals(UPDATED_GIFT_CERTIFICATE.getDescription(), actualDto.getDescription());
        verify(giftCertificateRepository, times(1)).update(any(GiftCertificate.class));
    }

    @Test
    void delete() {
        when(giftCertificateRepository.findById(CERTIFICATE_ID)).thenReturn(Optional.of(ENTITY));
        when(giftCertificateRepository.delete(ENTITY)).thenReturn(CERTIFICATE_ID);

        int actualId = service.delete(CERTIFICATE_ID);
        assertEquals(1, actualId);
        verify(giftCertificateRepository, times(1)).delete(ENTITY);
    }

    @Test
    void doFilter() {
        when(giftCertificateRepository.find(GIFT_CERTIFICATE_CRITERIA, PAGE_REQUEST)).thenReturn(List.of(ENTITY));

        List<GiftCertificateDto> actualDtoList = service.doFilter(GIFT_CERTIFICATE_CRITERIA, PAGE_REQUEST);

        assertNotNull(actualDtoList);
        verify(giftCertificateRepository, times(1)).find(GIFT_CERTIFICATE_CRITERIA, PAGE_REQUEST);
    }

    @Test
    void validate() {
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> service.validate(Optional.empty()));
        String expectedException = "Gift certificate is not found with provided id";
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }

}