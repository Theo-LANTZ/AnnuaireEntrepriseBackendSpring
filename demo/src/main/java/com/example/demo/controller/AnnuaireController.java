package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Annuaire;
import com.example.demo.repository.AnnuaireRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AnnuaireController {

	@Autowired
	AnnuaireRepository annuaireRepository;
	
	//Récupération de la liste de tout les annuaires stockés
	@GetMapping("/annuaires")
	public ResponseEntity<List<Annuaire>> getAllAnnuaire(@RequestParam(required = false) String name){
		try {
			List<Annuaire> annuaires = new ArrayList<Annuaire>();
			
			if (name == null)
				annuaireRepository.findAll().forEach(annuaires::add);
			else
				annuaireRepository.findByNameContaining(name).forEach(annuaires::add);
			
			if(annuaires.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(annuaires, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Recupération d'un annuaire dont l'id et passé en paramètres
	@GetMapping("/annuaires/{id}")
	public ResponseEntity<Annuaire> getAnnuaireById(@PathVariable("id") long id) {
		Optional<Annuaire> annuaireData = annuaireRepository.findById(id);
		
		if(annuaireData.isPresent()) {
			return new ResponseEntity<>(annuaireData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Ajout d'un nouvel annuaire
	@PostMapping("/annuaires")
	public ResponseEntity<Annuaire> createAnnuaire(@RequestBody Annuaire annuaire){
		try {
			Annuaire _annuaire = annuaireRepository
					.save(new Annuaire(0, annuaire.getNom(), annuaire.getAdressemail(), annuaire.getDate_arrive(), annuaire.getLogin(), annuaire.getPassword(), annuaire.getPrenom()));
		return new ResponseEntity<>(_annuaire, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Met à jour l'annuaire dont l'id est passé en paramètres
	@PutMapping("/annuaires/{id}")
	public ResponseEntity<Annuaire> updateAnnuaire(@PathVariable("id") long id, @RequestBody Annuaire annuaire){
		Optional<Annuaire> annuaireData = annuaireRepository.findById(id);
		
		if (annuaireData.isPresent()) {
			Annuaire _annuaire = annuaireData.get();
			_annuaire.setNom(annuaire.getNom());
			_annuaire.setAdressemail(annuaire.getAdressemail());
			_annuaire.setDate_arrive(annuaire.getDate_arrive());
			_annuaire.setLogin(annuaire.getLogin());
			_annuaire.setPassword(annuaire.getPassword());
			_annuaire.setPrenom(annuaire.getPrenom());
			return new ResponseEntity<>(annuaireRepository.save(_annuaire), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Supprime un annuaire dont l'identifiant est passé en paramètres
	@DeleteMapping("/annuaires/{id}")
	public ResponseEntity<HttpStatus> deleteAnnuaire(@PathVariable("id") long id) {
		try {
			annuaireRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//supprime tout les annuaires
	@DeleteMapping("/annuaires")
	public ResponseEntity<HttpStatus> deleteAllAnnuaires(){
		try {
			annuaireRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
