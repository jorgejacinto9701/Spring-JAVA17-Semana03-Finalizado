package com.empresa.service;

import java.util.List;

import com.empresa.entity.Empleado;

public interface EmpleadoService {

	public abstract Empleado registraEmpleado(Empleado obj);
	public abstract List<Empleado> listaNombreApellidoIgual(String nombre, String apellido);
	
	
}
