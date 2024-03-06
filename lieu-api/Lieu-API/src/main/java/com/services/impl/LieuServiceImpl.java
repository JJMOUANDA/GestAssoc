package com.services.impl;

import com.dtos.LieuDto;
import com.entities.Lieu;
import com.repositories.LieuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("lieuService")
public class LieuServiceImpl {

    private final LieuRepository lieuRepository;

    public LieuServiceImpl(LieuRepository lieuRepository){
        this.lieuRepository = lieuRepository;
    }

    public List<LieuDto> findAll() {
        return lieuRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LieuDto findById(Long id) {
        Lieu lieu = lieuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lieu.java not found with id " + id));
        return convertToDto(lieu);
    }

    public LieuDto save(LieuDto lieuDto) {
        Lieu lieu = convertToEntity(lieuDto);
        lieu = lieuRepository.save(lieu);
        return convertToDto(lieu);
    }

    public void deleteById(Long id) {
        lieuRepository.deleteById(id);
    }

    private LieuDto convertToDto(Lieu lieu) {
        LieuDto lieuDto = new LieuDto();
        BeanUtils.copyProperties(lieu, lieuDto);
        return lieuDto;
    }

    private Lieu convertToEntity(LieuDto lieuDto) {
        Lieu lieu = new Lieu();
        BeanUtils.copyProperties(lieuDto, lieu);
        return lieu;
    }
}
