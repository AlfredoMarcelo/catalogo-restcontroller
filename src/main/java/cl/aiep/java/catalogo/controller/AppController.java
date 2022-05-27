package cl.aiep.java.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.aiep.java.catalogo.model.Producto;
import cl.aiep.java.catalogo.repository.ProductoRepository;



@RestController //para app rest se debe usar esta anotacion
@CrossOrigin(origins = "*")//Con esta anotacion le damos permiso CORS
public class AppController {
	
	/*/En un proyecto se puede tener clases con 
	anotaciones @RestController y @Controller, pero deben estar separados*/
	
	
	@Autowired
	ProductoRepository productoRepository;
	
	//para que no se vuelva a instalar nuevamente los usuarios
	@GetMapping("/")
	public String instalar() {
	long count = productoRepository.count();
	if(count == 0) {
		
			Producto producto1 = Producto.builder()
					.nombre("Correa paseo")
					.precio(9990)
					.build()
					;
			Producto producto2 = Producto.builder()
					.nombre("Hueso juguete")
					.precio(8000)
					.build()
					;
			Producto producto3 = Producto.builder()
					.nombre("Comida perro")
					.precio(20000)
					.build()
					;
			productoRepository.saveAndFlush(producto1);
			productoRepository.saveAndFlush(producto2);
			productoRepository.saveAndFlush(producto3);
			
			
			return "ok";
	}else {
		return "ya estan generados los usuarios";
	}
	
	}
	
	
	@GetMapping("/productos/destacados")
	public List<Producto> productosDestacados(){
		List<Producto> productos = productoRepository.findAll();
		return productos;
	}
	
}
