package com.example.mypageservice.user.recipe;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mypageservice.user.category.Category;
import com.example.mypageservice.user.category.CategoryRepository;
import com.example.mypageservice.user.configuration.ApiConfiguration;
import com.example.mypageservice.user.recipeprocedure.RecipeProcedure;
import com.example.mypageservice.user.recipeprocedure.RecipeProcedureFile;
import com.example.mypageservice.user.recipeprocedure.RecipeProcedureFileRepository;
import com.example.mypageservice.user.recipeprocedure.RecipeProcedureRepository;
import com.example.mypageservice.user.stuff.Stuff;
import com.example.mypageservice.user.stuff.StuffRepository;

@RestController
public class RecipeController {
	private RecipeProcedureRepository proRepo;
	private RecipeProcedureFileRepository procedureRepo;
	private RecipeRepository RecipeRepo;
	private StuffRepository StuffRepo;
	private CategoryRepository CateRepo;
	private RecipeFileRepsitory ReFiRepo;
	private final Path FILE_PATH = Paths.get("recipe_file");
	private RecipeOrderService service;

	@Autowired
	private ApiConfiguration apiConfig;

	@Autowired // UserRepository UserRepo,

	public RecipeController(RecipeRepository RecipeRepo, StuffRepository StuffRepo, CategoryRepository CateRepo,
			RecipeFileRepsitory ReFiRepo, RecipeProcedureRepository proRepo, RecipeOrderService service,
			RecipeProcedureFileRepository procedureRepo) {
		this.procedureRepo = procedureRepo;
		this.ReFiRepo = ReFiRepo;
		this.StuffRepo = StuffRepo;
		this.RecipeRepo = RecipeRepo;
		this.CateRepo = CateRepo;
		this.proRepo = proRepo;
		this.service = service;
	}

	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public List<Recipe> getRecipeList(HttpServletRequest req) {
		List<Recipe> list = RecipeRepo.findAll();
		for (Recipe recipe : list) {
			for (RecipeFile Recipefile : recipe.getRecipefile()) {
				Recipefile.setDataUrl(apiConfig.getBasePath() + "/recipe-files/" + Recipefile.getId());

			}
		}
		return list;

	}

	@RequestMapping(value = "/recipe-files/{recipeId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getRecipeFile(@PathVariable("recipeId") long recipeId, HttpServletResponse res)
			throws IOException {
		RecipeFile recipeFile = ReFiRepo.findById(recipeId).orElse(null);

		if (recipeFile == null) {
			return ResponseEntity.notFound().build();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", recipeFile.getContentType() + ";charset=UTF-8");

		responseHeaders.set("Content-Disposition",
				"inline; filename=" + URLEncoder.encode(recipeFile.getFileName(), "UTF-8"));

		return ResponseEntity.ok().headers(responseHeaders)
				.body(Files.readAllBytes(FILE_PATH.resolve(recipeFile.getFileName())));
	}

	@RequestMapping(value = "/procedure-files/{procedureId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getProcedureFile(@PathVariable("procedureId") long procedureId,
			HttpServletResponse res) throws IOException {
		RecipeProcedureFile recipeProcedureFile = procedureRepo.findById(procedureId).orElse(null);

		if (recipeProcedureFile == null) {
			return ResponseEntity.notFound().build();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", recipeProcedureFile.getContentType() + ";charset=UTF-8");

		responseHeaders.set("Content-Disposition",
				"inline; filename=" + URLEncoder.encode(recipeProcedureFile.getFileName(), "UTF-8"));

		return ResponseEntity.ok().headers(responseHeaders)
				.body(Files.readAllBytes(FILE_PATH.resolve(recipeProcedureFile.getFileName())));
	}

	@RequestMapping(value = "/stuff", method = RequestMethod.GET)
	public List<Stuff> getStuffList() {
		return StuffRepo.findAll();

	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public List<Category> getcategoryList() {
		return CateRepo.findAll();

	}

	@RequestMapping(value = "/recipe/{userId}", method = RequestMethod.GET)
	public List<Recipe> getRecipeByUserId(@PathVariable("userId") long userId) {

		return RecipeRepo.findByUserId(userId);

	}

	@RequestMapping(value = "/recipes/{recipeId}", method = RequestMethod.GET)
	public List<Recipe> getRecipeByRecipeId(@PathVariable("recipeId") long recipeId) {
		List<Recipe> list = RecipeRepo.findByRecipeId(recipeId);

		for (Recipe recipe : list) {
			for (RecipeFile Recipefile : recipe.getRecipefile()) {
				for (RecipeProcedure recipeProcedure : recipe.getRecipeProcedure()) {
					for (RecipeProcedureFile recipeProcedureFile : recipeProcedure.getRecipeProcedurefile()) {
						recipeProcedureFile.setDataUrl(
								apiConfig.getBasePath() + "/procedure-files/" + recipeProcedureFile.getId());
					}
				}
				Recipefile.setDataUrl(apiConfig.getBasePath() + "/recipe-files/" + Recipefile.getId());
			}

		}

		return list;

	}

//	@RequestMapping(value = "/RecipeProcedureFile/{recipeId}", method = RequestMethod.GET)
//	public List<Recipe> getRecipeProcedureFileRecipeId(@PathVariable("recipeId") long recipeId) {
//		List<Recipe> list = RecipeRepo.findByRecipeId(recipeId);
//
//		for (Recipe recipe : list) {
//
//			for (RecipeProcedure recipeProcedure : recipe.getRecipeProcedure()) {
//				for (RecipeProcedureFile recipeProcedureFile : recipeProcedure.getRecipeProcedurefile()) {
//					recipeProcedureFile
//							.setDataUrl(apiConfig.getBasePath() + "/recipe-files/" + recipeProcedureFile.getId());
//				}
//			}
//
//		}
//
//		return list;
//
//	}

	@RequestMapping(value = "/recipe", method = RequestMethod.POST)
	public Recipe addRecipe(@RequestBody Recipe recipe) {
		RecipeRepo.save(recipe);
//		service.sendsOrder(recipe.getRecipeId(), recipe.getRecipeName(), recipe.getCategory(), recipe.getStuffRecipe());

//		service.sendOrder(recipe);
		System.out.println("----------------" + recipe);
		return recipe;

	}

	@RequestMapping(value = "/recipe/{id}/recipe-files", method = RequestMethod.POST)
	public RecipeFile addRecipeFile(@PathVariable("id") long id, @RequestPart("data") MultipartFile file,
			String filePath, HttpServletResponse res) throws IOException {

		if (RecipeRepo.findById(id).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (!Files.exists(FILE_PATH)) {
			Files.createDirectories(FILE_PATH);
		}

		FileCopyUtils.copy(file.getBytes(), new File(FILE_PATH.resolve(file.getOriginalFilename()).toString()));

		RecipeFile recipeFile = RecipeFile.builder().recipeId(id).fileName(file.getOriginalFilename())
				.contentType(file.getContentType()).build();

		ReFiRepo.save(recipeFile);
		return recipeFile;

	}

	@RequestMapping(value = "/procedure/{id}/files", method = RequestMethod.POST)
	public RecipeProcedureFile addRecipeProcedureFile(@PathVariable("id") long id,
			@RequestPart("data") MultipartFile file, String filePath, HttpServletResponse res) throws IOException {

		if (proRepo.findById(id).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (!Files.exists(FILE_PATH)) {
			Files.createDirectories(FILE_PATH);
		}

		FileCopyUtils.copy(file.getBytes(), new File(FILE_PATH.resolve(file.getOriginalFilename()).toString()));

		RecipeProcedureFile recipeProcedureFile = RecipeProcedureFile.builder().procedureId(id)
				.fileName(file.getOriginalFilename()).contentType(file.getContentType()).build();

		procedureRepo.save(recipeProcedureFile);

		return recipeProcedureFile;

	}

	@RequestMapping(value = "/recipe/{id}/recipe-files", method = RequestMethod.DELETE)
	public boolean removeRecipeFiles(@PathVariable("id") long recipeId, HttpServletResponse res) {

		if (RecipeRepo.findById(recipeId).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		List<RecipeFile> recipeFiles = ReFiRepo.findByRecipeId(recipeId);
		for (RecipeFile recipeFile : recipeFiles) {
			ReFiRepo.delete(recipeFile);
			File file = new File(recipeFile.getFileName());
			if (file.exists()) {
				file.delete();
			}
		}

		return true;
	}

	@RequestMapping(value = "/procedure/{id}/files", method = RequestMethod.DELETE)
	public boolean removePeocedureFiles(@PathVariable("id") long id, HttpServletResponse res) {

		if (RecipeRepo.findById(id).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		List<RecipeProcedureFile> RecipeProcedureFile = procedureRepo.findByProcedureId(id);
		for (RecipeProcedureFile recipeProcedureFile : RecipeProcedureFile) {
			procedureRepo.delete(recipeProcedureFile);
			File file = new File(recipeProcedureFile.getFileName());
			if (file.exists()) {
				file.delete();
			}
		}

		return true;
	}

	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.DELETE)
	public boolean removeRecipe(@PathVariable("id") long id, HttpServletResponse res) {
		System.out.println(id);

		Recipe recipe = RecipeRepo.findById(id).orElse(null);

		if (recipe == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		List<RecipeProcedureFile> RecipeProcedureFile = procedureRepo.findByProcedureId(id);
		for (RecipeProcedureFile file : RecipeProcedureFile) {
			procedureRepo.delete(file);
		}
		List<RecipeFile> files = ReFiRepo.findByRecipeId(id);
		for (RecipeFile file : files) {
			ReFiRepo.delete(file);
		}

//		service.sendsOrder(recipe.getRecipeId());
		RecipeRepo.deleteById(id);

		return true;
	}

}