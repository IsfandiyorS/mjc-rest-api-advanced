package com.epam.esm.service.impl;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.GiftCertificateMapper;
import com.epam.esm.mapper.auth.GiftCertificateMapperImpl;
import com.epam.esm.mapper.auth.TagMapper;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.constant.GiftCertificateColumn.GIFT_CERTIFICATE;
import static com.epam.esm.constant.TagColumn.NAME;
import static com.epam.esm.enums.ErrorCodes.OBJECT_ALREADY_EXIST;
import static com.epam.esm.enums.ErrorCodes.OBJECT_NOT_FOUND_ID;
import static com.epam.esm.service.impl.domain.GiftCertificateModel.*;
import static com.epam.esm.service.impl.domain.TagModel.DELETED_STATE;
import static java.lang.String.format;
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

    @Test
    void getById() {
        when(giftCertificateRepository.findById(CERTIFICATE_ID)).thenReturn(Optional.of(ENTITY));

        GiftCertificateDto actualId = service.getById(CERTIFICATE_ID);
        assertEquals(GIFT_CERTIFICATE_DTO, actualId);
        verify(giftCertificateRepository, times(1)).findById(CERTIFICATE_ID);
    }

    @Test
    void testGetByIdMethodThrowObjectNotFoundException() {
        when(giftCertificateRepository.findById(NOT_AVAILABLE_GIFT_CERTIFICATE_ID))
                .thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException =
                assertThrows(ObjectNotFoundException.class, () -> service.getById(NOT_AVAILABLE_GIFT_CERTIFICATE_ID));

        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, GIFT_CERTIFICATE);
        String actualException = objectNotFoundException.getMessage();

        assertEquals(expectedException, actualException);

        verify(giftCertificateRepository, times(1)).findById(NOT_AVAILABLE_GIFT_CERTIFICATE_ID);
    }

    @Test
    void getAll() {
        when(giftCertificateRepository.findAll(PAGE_REQUEST)).thenReturn(anyList());

        List<GiftCertificateDto> actualDto = service.getAll(PAGE_REQUEST);

        assertNotNull(actualDto);
        verify(giftCertificateRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void testGetAllReturnEmptyList() {
        when(giftCertificateRepository.findAll(PAGE_REQUEST)).thenReturn(List.of());

        List<GiftCertificateDto> actualList = service.getAll(PAGE_REQUEST);

        assertTrue(actualList.isEmpty());
        verify(giftCertificateRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void testCreate() {
        when(giftCertificateRepository.findByName(NEW_GIFT_CERTIFICATE.getName())).thenReturn(Optional.empty());
        when(giftCertificateRepository.save(any(GiftCertificate.class))).thenReturn(CREATED_GIFT_CERTIFICATE);

        GiftCertificateDto actualDto = service.create(CREATE_DTO);

        assertEquals(CREATED_GIFT_CERTIFICATE_DTO, actualDto);
        verify(giftCertificateRepository, times(1)).save(any(GiftCertificate.class));
    }

    @Test
    void testCreateMethodThrowAlreadyExistException() {
        when(giftCertificateRepository.findByName(ALREADY_EXISTED_CERTIFICATE_DTO.getName()))
                .thenReturn(Optional.of(ENTITY));

        AlreadyExistException alreadyExistException = assertThrows(AlreadyExistException.class,
                () -> service.create(ALREADY_EXISTED_CERTIFICATE_DTO));

        String expectedException = format(OBJECT_ALREADY_EXIST.message, GIFT_CERTIFICATE, NAME);
        String actualException = alreadyExistException.getMessage();

        assertEquals(expectedException, actualException);

        verify(giftCertificateRepository, times(1)).findByName(ALREADY_EXISTED_CERTIFICATE_DTO.getName());
    }

    @Test
    void testUpdate() {
        when(giftCertificateRepository.findById(GIFT_CERTIFICATE_UPDATE_DTO.getId())).thenReturn(Optional.of(ENTITY));
        when(giftCertificateRepository.update(any(GiftCertificate.class))).thenReturn(UPDATE_GIFT_CERTIFICATE);

        GiftCertificateDto actualDto = service.update(GIFT_CERTIFICATE_UPDATE_DTO);
        assertEquals(UPDATED_GIFT_CERTIFICATE, actualDto);
        verify(giftCertificateRepository, times(1)).update(any(GiftCertificate.class));
    }

    @Test
    void delete() {
        when(giftCertificateRepository.findById(CERTIFICATE_ID)).thenReturn(Optional.of(ENTITY));
        when(giftCertificateRepository.delete(ENTITY)).thenReturn(DELETED_STATE);

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
        ObjectNotFoundException objectNotFoundException = assertThrows(
                ObjectNotFoundException.class, () -> service.validate(Optional.empty()));
        String expectedException = String.format(OBJECT_NOT_FOUND_ID.message, GIFT_CERTIFICATE);
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }

}