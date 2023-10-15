package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Categoria;
import com.empresa.entity.Deporte;
import com.empresa.entity.Pais;
import com.empresa.entity.Tipo;
import com.empresa.service.CategoriaService;
import com.empresa.service.DeporteService;
import com.empresa.service.PaisService;
import com.empresa.service.TipoService;

@Controller
public class UtilController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private DeporteService deporteService;

	@Autowired
	private PaisService paisService;
	
	@Autowired
	private TipoService tipoService;
	
	@GetMapping(value = "/listaCategoria")
	@ResponseBody
	public List<Categoria> cargaCategoria(){
		return categoriaService.listarTodo();
	}
	
	@GetMapping(value = "/listaDeporte")
	@ResponseBody
	public List<Deporte> cargaDeporte() {
		return deporteService.listarTodos();
	}
	
	@GetMapping(value = "/listaPais")
	@ResponseBody
	public List<Pais> cargaPais() {
		return paisService.listaPais();
	}
	
	@GetMapping(value = "/listaTipo")
	@ResponseBody
	public List<Tipo> cargaTipo(){
		return tipoService.listaTipo();
	}
}




