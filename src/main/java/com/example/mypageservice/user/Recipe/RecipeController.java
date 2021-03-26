package com.example.mypageservice.user.Recipe;

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

import com.example.mypageservice.user.Category.Category;
import com.example.mypageservice.user.Category.CategoryRepository;
import com.example.mypageservice.user.RecipeProcedure.RecipeProcedureFile;
import com.example.mypageservice.user.RecipeProcedure.RecipeProcedureFileRepository;
import com.example.mypageservice.user.RecipeProcedure.RecipeProcedureRepository;
import com.example.mypageservice.user.Stuff.Stuff;
import com.example.mypageservice.user.Stuff.StuffRepository;
import com.example.mypageservice.user.configuration.ApiConfiguration;

@RestController
public class RecipeController {
	private RecipeProcedureRepository proRepo;
	private RecipeProcedureFileRepository procedureRepo;
	private RecipeRepository RecipeRepo;
	private StuffRepository StuffRepo;
	private CategoryRepository CateRepo;
	private RecipeFileRepsitory ReFiRepo;
	private final Path FILE_PATH = Paths.get("recipe_file");

	@Autowired
	private ApiConfiguration apiConfig;

	@Autowired // UserRepository UserRepo,

	public RecipeController(RecipeRepository RecipeRepo, StuffRepository StuffRepo, CategoryRepository CateRepo,
			RecipeProcedureFileRepository procedureRepo, RecipeFileRepsitory ReFiRepo,
			RecipeProcedureRepository proRepo) {
		this.procedureRepo = procedureRepo;
		this.ReFiRepo = ReFiRepo;
		this.StuffRepo = StuffRepo;
		this.RecipeRepo = RecipeRepo;
		this.CateRepo = CateRepo;
		this.proRepo = proRepo;
	}

	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public List<Recipe> getRecipeList(HttpServletRequest req) {
		List<Recipe> list = RecipeRepo.findAll();
		for (Recipe recipe : list) {
			for (RecipeFile Recipefile : recipe.getRecipefile()) {
				Recipefile.setDataUrl(apiConfig.getBasePath() + "/recipe-files/" + Recipefile.getId());

			}
		}
		return list;

	}

	@RequestMapping(value = "/recipe-files/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFeedFile(@PathVariable("id") long id, HttpServletResponse res) throws IOException {
		RecipeFile feedFile = ReFiRepo.findById(id).orElse(null);

		if (feedFile == null) {
			return ResponseEntity.notFound().build();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", feedFile.getContentType() + ";charset=UTF-8");
		// inline: 뷰어로, attachement: 내려받기
		responseHeaders.set("Content-Disposition",
				"inline; filename=" + URLEncoder.encode(feedFile.getFileName(), "UTF-8"));

		return ResponseEntity.ok().headers(responseHeaders)
				.body(Files.readAllBytes(FILE_PATH.resolve(feedFile.getFileName())));
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
		return RecipeRepo.findByRecipeId(recipeId);

	}

	@RequestMapping(value = "/recipe", method = RequestMethod.POST)
	public Recipe addRecipe(@RequestBody Recipe recipe) {

		RecipeRepo.save(recipe);
		return recipe;

	}

	@RequestMapping(value = "/recipe/{id}/recipe-files", method = RequestMethod.POST)
	public RecipeFile addRecipeFile(@PathVariable("id") long id, @RequestPart("data") MultipartFile file,
			HttpServletResponse res) throws IOException {

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

	@RequestMapping(value = "/recipe/{id}/ProcedureFile-files", method = RequestMethod.POST)
	public RecipeProcedureFile addRecipeProcedureFile(@PathVariable("id") long id,
			@RequestPart("data2") MultipartFile file, HttpServletResponse res) throws IOException {

		if (proRepo.findById(id).orElse(null) == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (!Files.exists(FILE_PATH)) {
			Files.createDirectories(FILE_PATH);
		}

		FileCopyUtils.copy(file.getBytes(), new File(FILE_PATH.resolve(file.getOriginalFilename()).toString()));

		RecipeProcedureFile recipeProcedureFile = RecipeProcedureFile.builder().recipeId(id)
				.fileName(file.getOriginalFilename()).contentType(file.getContentType()).build();

		procedureRepo.save(recipeProcedureFile);

		return recipeProcedureFile;

	}

//	@RequestMapping(value = "/recipe-files/{id}", method = RequestMethod.GET)
//	public List<RecipeFile> getFeedFiles(@PathVariable("id") long id, HttpServletResponse res) {
//
//		if (RecipeRepo.findById(id).orElse(null) == null) {
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//
//		List<RecipeFile> feedFiles = ReFiRepo.findByRecipeId(id);
//
//		return feedFiles;
//	}

//	@RequestMapping(value = "/recipe/{id}/recipe-files", method = RequestMethod.DELETE)
//	public boolean removeRecipeFiles(@PathVariable("id") long id, HttpServletResponse res) {
//
//		if (RecipeRepo.findById(id).orElse(null) == null) {
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return false;
//		}
//
//		List<RecipeFile> recipeFiles = ReFiRepo.findByRecipeId(id);
//		for (RecipeFile recipeFile : recipeFiles) {
//			ReFiRepo.delete(recipeFile);
//			File file = new File(recipeFile.getFileName());
//			if (file.exists()) {
//				file.delete();
//			}
//		}
//
//		return true;
//	}
//
//	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.DELETE)
//	public boolean removeRecipe(@PathVariable("id") long id, HttpServletResponse res) {
//		System.out.println(id);
//
//		Recipe recipe = RecipeRepo.findById(id).orElse(null);
//
//		if (recipe == null) {
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return false;
//		}
//
//		List<RecipeFile> files = ReFiRepo.findByRecipeId(id);
//		for (RecipeFile file : files) {
//			ReFiRepo.delete(file);
//		}
//
//		RecipeRepo.deleteById(id);
//
//		return true;
//	}

}
