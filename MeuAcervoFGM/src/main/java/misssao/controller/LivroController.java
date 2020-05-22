package misssao.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import misssao.model.Livro;
import misssao.model.LivroRepo;

@Controller
public class LivroController {
	@Autowired
	private LivroRepo repo;
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("livro", new Livro());
		model.addAttribute("lista", repo.findAll());
		
		return "index";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute Livro livro, Model model) {
		if(livro.getId()!= 0) {
			Livro livroEditado = livro;
			livro = repo.findById(livroEditado.getId()).get();
			livro.setTitulo(livroEditado.getTitulo());
			livro.setDescricao(livroEditado.getDescricao());
			livro.setAno(livroEditado.getAno());
			livro.setAvaliacao(livroEditado.getAvaliacao());
			
			
		}
		repo.save(livro);
		 model.addAttribute("lista", repo.findAll());
		
		return "index";
	}
	
	@GetMapping("/list")
	public String listAll(Model model) {
		List<Livro> lista = repo.findAll();
		model.addAttribute("livro", new Livro());
		
		
		model.addAttribute("lista", lista);
		
		return "index";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model) {
		Livro livro = repo.findById(id).get();
		model.addAttribute("livro", livro);
		model.addAttribute("lista", repo.findAll());
		return "index";
		
		
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {	
		repo.deleteById(id);
		model.addAttribute("livro", new Livro());
		model.addAttribute("lista", repo.findAll());
		
		return "index";
		
	}
	
}