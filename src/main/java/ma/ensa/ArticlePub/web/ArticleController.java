package ma.ensa.ArticlePub.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.ArticlePub.domain.Article;
import ma.ensa.ArticlePub.domain.CoAuteur;
import ma.ensa.ArticlePub.domain.Controle;
import ma.ensa.ArticlePub.domain.Etat;
import ma.ensa.ArticlePub.domain.Revue;
import ma.ensa.ArticlePub.domain.Role;
import ma.ensa.ArticlePub.domain.User;
import ma.ensa.ArticlePub.domain.enums.Etats;
import ma.ensa.ArticlePub.domain.enums.Roles;
import ma.ensa.ArticlePub.service.ArticleService;
import ma.ensa.ArticlePub.service.ControleService;
import ma.ensa.ArticlePub.service.EtatService;
import ma.ensa.ArticlePub.service.RefereService;
import ma.ensa.ArticlePub.service.RevueService;
import ma.ensa.ArticlePub.service.RoleService;
import ma.ensa.ArticlePub.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/article")
public class ArticleController {
	/**
	 * @author Khalid Oukssim
	 *
	 */

	@Autowired
	private ControleService controlService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private RevueService revueService;
	@Autowired
	private EtatService etatService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RefereService refereService;

	// Create Article by User

	@PreAuthorize("hasRole('AUTEUR')")
	@PostMapping("/add/{revue}")
	public ResponseEntity<?> addArticle(@RequestBody Article article, @PathVariable String revue, Principal principal) {
		User aut = userService.findByUsername(principal.getName());
		// affecter un revue
		Revue rv = revueService.findByName(revue);
		rv.setNmbreArticle(rv.getNmbreArticle() + 1);
		revueService.save(rv);
		article.setRevue(rv);

		// auteur
		article.setAuteur(aut);

		// auteur

		// coAteurs
		List<CoAuteur> coAuteurs = new ArrayList<CoAuteur>();
		for (Long id : article.getCoAuteursId()) {
			User user = userService.findById(id);
			CoAuteur c = new CoAuteur(user);
			coAuteurs.add(c);
		}
		article.setCoAuteur(coAuteurs);

		// save Article
		Article art = articleService.save(article);

		// set etat to 'enAttente'
		Etat et = new Etat(Etats.ATTENTE.getEtat(), art);
		et = etatService.save(et);

		return new ResponseEntity<Article>(art, HttpStatus.CREATED);
	}

	// refere peux savoir les articles qui sont afffecté par la commité

	@PreAuthorize("hasRole('REFERE')")
	@GetMapping("/affecteToRefere")
	public List<Article> getArticlesToJudge(Principal principal) {

		User user = userService.findByUsername(principal.getName());
		List<Controle> controles = controlService.getMyArticles(user.getRefere().getId());
		List<Article> articles = new ArrayList<Article>();

		if (!controles.isEmpty()) {
			controles.forEach((e) -> {
				e.getArticle().setAuteur(null);
				e.getArticle().setCoAuteursId(null);
				articles.add(e.getArticle());
			});
		}
		return articles;
	}

	@PreAuthorize("hasRole('COMITE')")
	@GetMapping("/attente")
	public List<Article> getArticlesEnAttente() {
		return articleService.getArticlesEnAttente();
	}

	@PreAuthorize("hasRole('COMITE')")
	@GetMapping("/accepte")
	public List<Article> getArticlesAccepte() {
		return articleService.getArticlesAccepte();
	}

	// acces par tous

	@GetMapping("/publie")
	public List<Article> getArticlesPublie(Principal principal) {
		if (principal == null || userService.findByUsername(principal.getName()) == null) {
			List<Article> arts = new ArrayList<>();
			articleService.getArticlesPublie().forEach(e -> {
				e.setAffiliation(null);
				e.setContenu(null);
				arts.add(e);
			});
			return arts;
		} else {
			return articleService.getArticlesPublie();
		}

	}

	// auteur peux modifier son article
	@PreAuthorize("hasRole('AUTEUR')")
	@PutMapping("/modify")
	public ResponseEntity<?> modifierArticle(@RequestBody Article article, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		if (user.getId() == article.getAuteur().getId()) {

		}
		Article art = articleService.save(article);
		return new ResponseEntity<Article>(art, HttpStatus.CREATED);
	}

	// selectionner par nom auteur

	@GetMapping("/author/{name}")
	public List<Article> getArticlesByAuthor(@PathVariable String name) {
		return articleService.getArticlesByAuthor(name);
	}

	// selectionner par mot cle

	@GetMapping("/mot/{keyword}")
	public List<Article> getArticlesByMotCle(@PathVariable String keyword) {
		return articleService.getArticlesByMotCle(keyword);
	}

	// user peut supprimer avant le modif

	@PreAuthorize("hasRole('AUTEUR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> supprimerArticle(@PathVariable Long id, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Article article = articleService.findById(id);
		if (user.getId() == article.getAuteur().getId()) {
			articleService.supprimerArticle(id);
		}
		return new ResponseEntity<String>("Deleted with id: " + id, HttpStatus.CREATED);
	}

	// publier un article accepté par les jurés

	@PreAuthorize("hasRole('COMITE')")
	@PostMapping("/publie/{id}")
	public ResponseEntity<?> publierArticleAccepte(@PathVariable long id) {
		Article article = articleService.findById(id);
		article.getControleArticle().getReferes().forEach(e -> {
			Set<Role> roles = new HashSet<>();
			roles.add(roleService.findByName(Roles.AUTEUR.getRole()));
			e.getRefere().setRoles(roles);
			refereService.delete(e.getId());
		});
		article.getEtatArticle().setEtat(Etats.PUBLIE.getEtat());
		article = articleService.save(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

}
