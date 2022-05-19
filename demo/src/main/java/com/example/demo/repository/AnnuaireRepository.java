package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Annuaire;

public interface AnnuaireRepository extends JpaRepository<Annuaire, Long>{
	List<Annuaire> findByNameContaining(String nom);
}
