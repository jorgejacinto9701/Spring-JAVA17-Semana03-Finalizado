<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>Ejemplos de CIBERTEC - Jorge Jacinto </title>
</head>
<body>

<div class="container">
<h3>Registra Empleado</h3>
	
	<form  id="id_form" method="post"> 
	 <div class="col-md-12" style="margin-top: 2%">
			<div class="row">
				<div class="form-group col-md-6">
					<label class="control-label" for="id_nombre">Nombres</label>
					<input class="form-control" type="text" id="id_nombre" name="nombres" placeholder="Ingrese los nombres" maxlength="40">
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="id_apellido">Apellidos</label>
					<input class="form-control" id="id_apellido" name="apellidos" placeholder="Ingrese los apellidos" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-3">
					<label class="control-label" for="id_fec_nac">Fecha Nacimiento</label>
					<input class="form-control" id="id_fec_nac" name="fechaNacimiento" type="date"/>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="id_pais">País</label>
					<select id="id_pais" name="pais" class='form-control'>
						<option value=" ">[Seleccione]</option>    
					</select>
			    </div>
				
		    </div>
		    <div class="row">
				<div class="form-group col-md-12" align="center">
					<button id="id_registrar" type="button" class="btn btn-primary" >Registra</button>
				</div>
			</div>
	</div>
	</form>
	
</div>

<script type="text/javascript">

$.getJSON("listaPais", {}, function(data){
	$.each(data, function(index,item){
		$("#id_pais").append("<option value="+item.idPais +">"+ item.nombre +"</option>");
	});
});

$("#id_registrar").click(function (){ 

	var validator = $('#id_form').data('bootstrapValidator');
	validator.validate();

	if (validator.isValid()){
		$.ajax({
			type: 'POST',  
			data: $("#id_form").serialize(),
			url: 'registraEmpleado',
			success: function(data){
				mostrarMensaje(data.MENSAJE);
				limpiar();
				validator.resetForm();
			},
			error: function(){
				mostrarMensaje(MSG_ERROR);
			}
		});
	}
});

function limpiar(){
	$("#id_nombre").val('');
	$("#id_apellido").val('');
	$("#id_fec_nac").val('');
	$("#id_pais").val(' ');
}

$('#id_form').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	nombres: {
    		selector : '#id_nombre',
            validators: {
                notEmpty: {
                    message: 'El nombre es un campo obligatorio'
                },
                stringLength :{
                	message:'El nombre es de 3 a 40 caracteres',
                	min : 3,
                	max : 40
                },
            }
        },
        apellidos: {
    		selector : '#id_apellido',
            validators: {
                notEmpty: {
                    message: 'El apellido es un campo obligatorio'
                },
                stringLength :{
                	message:'El apellido es de 3 a 40 caracteres',
                	min : 3,
                	max : 40
                },
            }
        },
        fechaNacimiento: {
    		selector : '#id_fec_nac',
            validators: {
                notEmpty: {
                    message: 'La fecha Nacimiento es un campo obligatorio'
                },
                remote :{
                	delay   : 1000,
                	url     : 'buscaEmpleadoMayorEdad',
                	message : 'El empleado no es mayor de edad'
                }
            }
        },
        pais: {
    		selector : '#id_pais',
            validators: {
            	notEmpty: {
                    message: 'País es un campo obligatorio'
                },
            }
        },
    	
    }   
});
</script>


</body>
</html>




