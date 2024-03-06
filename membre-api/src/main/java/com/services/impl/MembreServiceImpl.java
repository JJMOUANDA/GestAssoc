package com.services.impl;

import com.dtos.MembreDto;
import com.entities.Membre;
import com.repositories.MembreRepository;
import com.services.MembreService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("membreService")
public class MembreServiceImpl implements MembreService {

	private final MembreRepository membreRepository;
  private final BCryptPasswordEncoder passwordEncoder;

    public MembreServiceImpl(MembreRepository membreRepository, BCryptPasswordEncoder passwordEncoder){
        this.membreRepository = membreRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MembreDto saveMembre(MembreDto membreDto) {
        // Converts the dto to the membre entity
        Membre membre = membreDtoToEntity(membreDto);
        // Save the membre entity
        membre = membreRepository.save(membre);
        // Return the new dto
        return membreEntityToDto(membre);
    }

    @Override
    public MembreDto getMembreById(Long membreId) {
        Membre membre = membreRepository.findById(membreId).orElseThrow(() -> new EntityNotFoundException("Membre not found"));
        return membreEntityToDto(membre);
    }

    @Override
    public boolean deleteMembre(Long membreId) {
        membreRepository.deleteById(membreId);
        return true;
    }

    @Override
    public List<MembreDto> getAllMembres() {
        List<MembreDto> membreDtos = new ArrayList<>();
        List<Membre> membres = membreRepository.findAll();
        membres.forEach(membre -> {
            membreDtos.add(membreEntityToDto(membre));
        });
        return membreDtos;
    }

    /**
     * Map membre dto to membre entity
     */
    private MembreDto membreEntityToDto(Membre membre){
        MembreDto membreDto = new MembreDto();
        membreDto.setId(membre.getId());
        membreDto.setNom(membre.getNom());
        membreDto.setPrenom(membre.getPrenom());
        membreDto.setDateNaissance(membre.getDateNaissance());
        membreDto.setAdresse(membre.getAdresse());
        membreDto.setMail(membre.getMail());
        membreDto.setMotDePasse(membre.getMotDePasse());
        return membreDto;
    }

    /**
     * Map membre entity to membre dto
     */
    private Membre membreDtoToEntity(MembreDto membreDto){
        Membre membre = new Membre();
        membre.setId(membreDto.getId());
        membre.setNom(membreDto.getNom());
        membre.setPrenom(membreDto.getPrenom());
        membre.setDateNaissance(membreDto.getDateNaissance());
        membre.setAdresse(membreDto.getAdresse());
        membre.setMail(membreDto.getMail());
        String hashedPassword = passwordEncoder.encode(membreDto.getMotDePasse());
        membre.setMotDePasse(hashedPassword);
        return membre;
    }
}
