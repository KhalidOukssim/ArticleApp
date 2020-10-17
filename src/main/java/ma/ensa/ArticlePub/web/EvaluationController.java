package ma.ensa.ArticlePub.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.ArticlePub.Exception.NumberException;
import ma.ensa.ArticlePub.Exception.UserNotAutorisedException;
import ma.ensa.ArticlePub.domain.Article;
import ma.ensa.ArticlePub.domain.Evaluation;
import ma.ensa.ArticlePub.domain.Refere;
import ma.ensa.ArticlePub.domain.User;
import ma.ensa.ArticlePub.domain.enums.Etats;
import ma.ensa.ArticlePub.service.ArticleService;
import ma.ensa.ArticlePub.service.ControleService;
import ma.ensa.ArticlePub.service.EtatService;
import ma.ensa.ArticlePub.service.EvaluationService;
import ma.ensa.ArticlePub.service.RefereService;
import ma.ensa.ArticlePub.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {

	@Autowired
	private RefereService refereService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private EvaluationService evalService;
	@Autowired
	private EtatService etatService;
	@Autowired
	private ControleService controleService;

	// voir les remarques de referes
	@PreAuthorize("hasRole('AUTEUR')")
	@GetMapping("/{idArt}")
	public List<Evaluation> getEvaluationsById(Principal principal, @PathVariable Long idArt) {
		User user = userService.findByUsername(principal.getName());
		Article article = articleService.findById(idArt);
		if (user.getId() != article.getAuteur().getId()) {
			throw new UserNotAutorisedException("Vous avez deja evalué le projet");
		}
		List<Evaluation> evs = new ArrayList<Evaluation>();

		article.getControleArticle().getEvaluation().forEach((e) -> {
			e.setRefere(null);
			evs.add(e);
		});

		return evs;
	}

	// refere evalue un article
	@PreAuthorize("hasRole('REFERE')")
	@PostMapping("")
	public ResponseEntity<?> addJujement(@RequestBody Evaluation evaluation, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Refere ref = refereService.findById(user.getRefere().getId());
		evaluation.setControle(evaluation.getRefere().getControle());
		if (evaluation.getControle().getEvaluation() != null) {
			evaluation.getControle().getEvaluation().forEach(e -> {
				if (e.getRefere().getId() == ref.getId()) {
					throw new UserNotAutorisedException("Vous avez deja evalué le projet");
				}
			});
		}
		evaluation.setRefere(ref);
		if (evaluation.getControle().getSommeVoters() > 3) {
			throw new NumberException("Le max c'est 3 evaluation pour chaque article");
		}

		if (evaluation.getControle().getSommeVoters() == 3) {
			if (evaluation.getControle().getSommeVotes() >= 2) {
				evaluation.getControle().getArticle().getEtatArticle().setEtat(Etats.ACCEPTE.getEtat());
				etatService.save(evaluation.getControle().getArticle().getEtatArticle());
			} else {
				evaluation.getControle().getArticle().getEtatArticle().setEtat(Etats.REFUSE.getEtat());
				etatService.save(evaluation.getControle().getArticle().getEtatArticle());
			}
		} else {
			evaluation.getControle().setSommeVoters(evaluation.getControle().getSommeVotes() + 1);
			evaluation.getControle()
					.setSommeVotes(evaluation.getControle().getSommeVotes() + (evaluation.isAccepte() ? 1 : 0));
		}
		controleService.save(evaluation.getControle());
		evaluation = evalService.save(evaluation);

		return new ResponseEntity<Evaluation>(evaluation, HttpStatus.OK);
	}

}
