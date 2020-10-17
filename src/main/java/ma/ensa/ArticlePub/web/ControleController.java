package ma.ensa.ArticlePub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.ArticlePub.Exception.NumberException;
import ma.ensa.ArticlePub.domain.Article;
import ma.ensa.ArticlePub.domain.Controle;
import ma.ensa.ArticlePub.domain.Refere;
import ma.ensa.ArticlePub.domain.Role;
import ma.ensa.ArticlePub.domain.User;
import ma.ensa.ArticlePub.domain.enums.Etats;
import ma.ensa.ArticlePub.domain.enums.Roles;
import ma.ensa.ArticlePub.service.ArticleService;
import ma.ensa.ArticlePub.service.ControleService;
import ma.ensa.ArticlePub.service.EtatService;
import ma.ensa.ArticlePub.service.RefereService;
import ma.ensa.ArticlePub.service.RoleService;
import ma.ensa.ArticlePub.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/controle")
public class ControleController {

	@Autowired
	private ControleService controlService;
	@Autowired
	private RefereService refereService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private EtatService etatService;
	@Autowired
	private RoleService roleService;

	// phase de controle: comite affecte des referes a un article en attente

	@PreAuthorize("hasRole('COMITE')")
	@PostMapping("/affecte/{id}/{etatId}")
	public ResponseEntity<?> affecteReferé(@PathVariable Long id, @PathVariable int etatId,
			@RequestBody List<Long> ids) {
		Article article = articleService.findById(id);
		if (etatId == 0) {
			article.getEtatArticle().setEtat(Etats.REFUSE.getEtat());
			etatService.save(article.getEtatArticle());
			return new ResponseEntity<String>("Article with id" + article.getId() + "is Refused, OUT od Revue Context",
					HttpStatus.CREATED);
		} else {
			if (ids.size() != 3) {
				throw new NumberException("il fut choisir exactement 3 referes");
			}
			article.getEtatArticle().setEtat(Etats.MODIFIABLE.getEtat());
			etatService.save(article.getEtatArticle());
			Controle controle = new Controle();
			controle.setArticle(article);
			controle.setSommeVoters(0);
			controle.setSommeVotes(0);
			Controle c = controlService.save(controle);
			ids.forEach((e) -> {
				// add REfere to roles
				User user = userService.findById(e);
				Role role = roleService.findByName(Roles.REFERE.getRole());
				user.getRoles().add(role);
				user.setRoles(user.getRoles());
				Refere ref = new Refere(user);
				ref.setControle(c);
				refereService.save(ref);
			});

			return new ResponseEntity<Controle>(c, HttpStatus.CREATED);

		}

	}

}
