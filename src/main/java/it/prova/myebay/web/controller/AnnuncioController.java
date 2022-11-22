package it.prova.myebay.web.controller;


import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.dto.CategoriaDTO;
import it.prova.myebay.dto.RuoloDTO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.exception.CreditoInsifficiente;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.AnnuncioService;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.RuoloService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private AcquistoService acquistoService;

	@GetMapping
	public ModelAndView listAllAnnunci() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("annunci_list_attribute",
				AnnuncioDTO.createAnnuncioDTOFromModelList(annuncioService.listAll(), false, false));
		mv.setViewName("annuncio/list");
		return mv;
	}

	@RequestMapping("/list")
	public String listAnnunci(Annuncio annuncioExample, ModelMap model) {

		model.addAttribute("annunci_list_attribute", AnnuncioDTO
				.createAnnuncioDTOFromModelList(annuncioService.findByExample(annuncioExample), false, false));

		return "annuncio/list";
	}

	@GetMapping("/show/{idAnnuncio}")
	public String showAnnuncio(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloElementoConCategorie(idAnnuncio);
		AnnuncioDTO result = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioModel, false, true);
		model.addAttribute("show_annuncio_attr", result);
		model.addAttribute("categorie_annuncio_attr", CategoriaDTO
				.createCategoriaDTOListFromModelList(categoriaService.cercaCategorieByIds(result.getCategorieIds())));
		return "annuncio/show";
	}

	@GetMapping("/search")
	public String searchUtente(Model model) {
		model.addAttribute("categorie_list_attribute",
				CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		return "annuncio/search";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_annuncio_attr")@RequestParam(name = "utenteId")Long utenteId, AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request, Principal principal) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "annuncio/insert";
		}
		
		annuncioService.inserisciNuovo(annuncioDTO.buildAnnuncioModel(true), principal.getName());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/list";
	}
	
	@GetMapping("/delete/{idAnnuncio}")
	public String delete(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), false, true);
		model.addAttribute("delete_annuncio_attr", annuncioDTO);
		return "annuncio/delete";
	}
	
	@PostMapping("/executeDelete")
	public String remove(@RequestParam Long idAnnuncio, RedirectAttributes redirectAttrs) {

		annuncioService.rimuovi(idAnnuncio);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/list";
	}
	
	@GetMapping("/edit/{idAnnuncio}")
	public String edit(@PathVariable(required = true) Long idAnnuncio, Model model, HttpServletRequest request) {
		AnnuncioDTO annuncioDTO = AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioService.caricaSingoloElementoConCategorie(idAnnuncio), false, true);
		model.addAttribute("edit_annuncio_attr", annuncioDTO);
		model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
		return "annuncio/edit";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("edit_annuncio_attr") AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request, Principal principal) {

		if (result.hasErrors()) {
			model.addAttribute("categorie_totali_attr", CategoriaDTO.createCategoriaDTOListFromModelList(categoriaService.listAll()));
			return "annuncio/edit";
		}
		annuncioService.aggiorna(annuncioDTO.buildAnnuncioModel(true), principal.getName());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/annuncio/list";
	}
	
	@PostMapping("/acquista")
	public String acquisto(@RequestParam Long idAnnuncioForAcquisto, Model model, RedirectAttributes redirectAttrs,
			HttpServletRequest request, Principal principal) {


		try {
			annuncioService.acquista(idAnnuncioForAcquisto, principal.getName());
		} catch (CreditoInsifficiente ex) {
			redirectAttrs.addFlashAttribute("errorMessage", "Credito insufficiente.");
			return "redirect:/annuncio";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "Si e' verificato un errore.");
			return "redirect:/annuncio";
		}

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		model.addAttribute("acquisti_list_attribute", AcquistoDTO.createAcquistoDTOFromModelList(
				acquistoService.findAllAcquistiEagerUtente(principal.getName()), true));
		return "acquisto/list";
	}

	@GetMapping("/acquistaWithoutAuth")
	public String acquistaWithoutAuth(@RequestParam(required = true) Long idAnnuncioWithNoAuth,
			Model model, RedirectAttributes redirectAttrs,HttpServletRequest request, Principal principal) {
		System.out.println("maledetto   "+idAnnuncioWithNoAuth);
		if (principal != null) {
			return this.acquisto(idAnnuncioWithNoAuth, model, redirectAttrs, request, principal);
		}
		model.addAttribute("idAnnuncioWithNoAuth", idAnnuncioWithNoAuth);
		return "/login";
	}

	
	

}