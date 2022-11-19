package com.epam.esm.service.impl;

import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.TagMapper;
import com.epam.esm.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.service.impl.model.TagModel.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @InjectMocks
    private TagServiceImpl tagService;

    @Spy
    private TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

    @Mock
    private TagRepository tagRepository = Mockito.mock(TagRepository.class);

    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 5);

    @Test
    void testCreate() {
        when(tagRepository.findByName(TAG_CREATE_DTO.getName())).thenReturn(Optional.empty());
        when(tagRepository.save(any(Tag.class))).thenReturn(CREATED_NEW_TAG_ENTITY);

        TagDto actualId = tagService.create(TAG_CREATE_DTO);

        assertEquals(CREATED_NEW_TAG_ENTITY.getName(), actualId.getName());
        verify(tagRepository, times(1)).save(any(Tag.class));
    }

    @Test
    void testGetById() {
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.of(TAG_ENTITY));
        TagDto actualTagDto = tagService.getById(TAG_ID);
        assertEquals(TAG_DTO, actualTagDto);
    }

    @Test
    void testGetAll() {
        when(tagRepository.findAll(PAGE_REQUEST)).thenReturn(List.of(TAG_ENTITY));

        List<TagDto> actualList = tagService.getAll(PAGE_REQUEST);

        assertNotNull(actualList);
        verify(tagRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void testDelete() {
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.of(TAG_ENTITY));
        when(tagRepository.delete(any(Tag.class))).thenReturn(TAG_ID);

        int actualId = tagService.delete(TAG_ID);

        assertEquals(TAG_ID, actualId);
        verify(tagRepository, times(1)).delete(any());
    }

    @Test
    void testDoFilter() {
        when(tagRepository.find(TAG_CRITERIA, PAGE_REQUEST)).thenReturn(List.of(TAG_ENTITY));

        List<TagDto> actualList = tagService.doFilter(TAG_CRITERIA, PAGE_REQUEST);

        assertNotNull(actualList);
        verify(tagRepository, times(1)).find(TAG_CRITERIA, PAGE_REQUEST);
    }

    @Test
    void testFindMostTags() {
        when(tagRepository.findMostPopular()).thenReturn(List.of(TAG_ENTITY));

        List<TagDto> actualList = tagService.findMostTags();

        assertNotNull(actualList);
        verify(tagRepository, times(1)).findMostPopular();
    }

    @Test()
    void testValidateTagThrowsException() {
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> tagService.validate(Optional.empty()));
        String expectedException = "Tag is not found with provided id";
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }
}