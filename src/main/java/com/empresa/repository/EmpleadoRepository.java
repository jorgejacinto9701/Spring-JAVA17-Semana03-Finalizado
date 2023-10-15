package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

	@Query("select p from Empleado p where p.nombres  = ?1 and p.apellidos = ?2")
	public List<Empleado> listaEmpleadoNombreApellidoIgual(String nombre, String apellido);
	
}
