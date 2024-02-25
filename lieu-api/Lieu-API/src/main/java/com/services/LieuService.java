package com.services;

import com.dtos.LieuDto;

import java.util.List;

public interface LieuService {
    /**
     * Sauve a dog
     */
    LieuDto saveLieu(LieuDto lieruDto);

    /**
     * Get a dog by it's id
     */
    LieuDto getLieuById(Long lieuId);

    /**
     * Delete a dog by it's id
     */
    boolean deleteLieu(Long lieuId);

    /**
     * Get all the dogs
     */
    List<LieuDto> getAllLieu();


}
