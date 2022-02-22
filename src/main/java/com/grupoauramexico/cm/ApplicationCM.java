/**
 * 
 */
package com.grupoauramexico.cm;


import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author vaguirre
 *
 */
@OpenAPIDefinition( info = @Info(title = "cm", description = "CM API @ micronaut", version = "1.0.0", contact = @Contact(email = "vlad.pax@gmail.com", name = "Vladimir Aguirre") ) )
public class ApplicationCM {

	public static void main(String[] args) {
		System.out.println("                              ");
		System.out.println("                              ");
		System.out.println(" /   /        _/_/_/  _/_/_/  _/_/      \\   \\ ");
		System.out.println("/   /      _/        _/    _/    _/      \\   \\");
		System.out.println("\\   \\     _/        _/    _/    _/       /   /");
		System.out.println(" \\   \\     _/_/_/  _/    _/    _/       /   / ");
		System.out.println("                                              .");
		System.out.println("                                              .");
		Micronaut.run(ApplicationCM.class, args);

	}

}
