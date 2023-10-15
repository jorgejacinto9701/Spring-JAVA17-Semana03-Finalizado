package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {

	@Query("select e from Pais e order by e.nombre asc")
	public List<Pais> listaPais();

}
