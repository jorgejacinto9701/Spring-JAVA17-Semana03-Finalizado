package com.empresa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Empleado;
import com.empresa.service.EmpleadoService;
import com.empresa.util.FunctionUtil;

@Controller
public class RegistraEmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping(value = "/verRegistraEmpleado" )
	public String verEmpleado() {return "registraEmpleado";}
	
	@PostMapping("/registraEmpleado")
	@ResponseBody
	public Map<?, ?> registra(Empleado obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		obj.setFechaActualizacion(new Date());
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		
		//Validación de nombre y apellido único
		List<Empleado> lstSalida = empleadoService.listaNombreApellidoIgual(obj.getNombres().trim(), obj.getApellidos().trim());
		if(!CollectionUtils.isEmpty(lstSalida)) {
			map.put("MENSAJE", "El empleado " + obj.getNombres() + " "+ obj.getApellidos() + " ya existe.");
			return map;
		}
		
		Empleado objSalida = empleadoService.registraEmpleado(obj);
		
		if (objSalida == null) {
			map.put("MENSAJE", "Error en el registro");
		} else {
			map.put("MENSAJE", "Registro exitoso");
		}
		return map;
	}
	
	
	@GetMapping("/buscaEmpleadoMayorEdad")
	@ResponseBody
	public String validaDni(String fechaNacimiento) {
		if(FunctionUtil.isMayorEdad(fechaNacimiento)) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}
	
}
