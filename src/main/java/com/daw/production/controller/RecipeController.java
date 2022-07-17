package com.daw.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.production.exception.ResourceNotFoundException;
import com.daw.production.model.Recipe;
import com.daw.production.repository.ProductRepository;
import com.daw.production.repository.RecipeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping("/products/{productId}/recipes")
	public ResponseEntity<List<Recipe>> getAllRecipesByProductId(@PathVariable(value = "productId") Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new ResourceNotFoundException("Not found Product with id = " + productId);
		}

		List<Recipe> recipes = recipeRepository.findByProductId(productId);
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}

	@GetMapping("/recipes/{id}")
	public ResponseEntity<Recipe> getRecipesByProductId(@PathVariable(value = "id") Long id) {
		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found recipe with id = " + id));

		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	@PostMapping("/products/{productId}/recipes")
	public ResponseEntity<Recipe> createRecipe(@PathVariable(value = "productId") Long productId,
			@RequestBody Recipe recipeRequest) {
		Recipe recipe = productRepository.findById(productId).map(product -> {
			recipeRequest.setProduct(product);
			return recipeRepository.save(recipeRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
		return new ResponseEntity<>(recipe, HttpStatus.CREATED);
	}

	@PutMapping("/recipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipeRequest) {
		Recipe recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + "not found"));

		recipe.setInsumo(recipeRequest.getInsumo());
		recipe.setCantidad(recipeRequest.getCantidad());
		recipe.setUnidad(recipeRequest.getUnidad());

		return new ResponseEntity<>(recipeRepository.save(recipe), HttpStatus.OK);
	}

	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") long id) {
		recipeRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/products/{productId}/recipes")
	public ResponseEntity<List<Recipe>> deleteAllRecipesOfProduct(@PathVariable(value = "productId") Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new ResourceNotFoundException("Not found Product with id = " + productId);
		}
		recipeRepository.deleteByProductId(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
