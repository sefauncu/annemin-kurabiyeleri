package com.yilmazmehmet.anneminkurabiyeleri.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yilmazmehmet.anneminkurabiyeleri.exception.UrunBulunamadiException;
import com.yilmazmehmet.anneminkurabiyeleribackend.dao.KategoriDAO;
import com.yilmazmehmet.anneminkurabiyeleribackend.dao.UrunDAO;
import com.yilmazmehmet.anneminkurabiyeleribackend.dto.Kategori;
import com.yilmazmehmet.anneminkurabiyeleribackend.dto.Urun;

@Controller
public class PageController {
	private static final Logger logger = LoggerFactory
			.getLogger(PageController.class);
	@Autowired
	private KategoriDAO kategoriDAO;

	@Autowired
	private UrunDAO urunDAO;

	@RequestMapping(value = { "/", "/anasayfa", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Anasayfa");

		logger.info("Page Controller index metot -INFO");
		logger.debug("Page Controller index metot -DEBUG");
		// kategorilri getirme
		mv.addObject("kategoriler", kategoriDAO.listele());
		mv.addObject("urunler",urunDAO.listeleAnasayfa());
		mv.addObject("urunlerSlide",urunDAO.listeleSlide());

		mv.addObject("anasayfaMi", true);
		return mv;
	}

	@RequestMapping(value = "/hakkimizda")
	public ModelAndView hakkimizda() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Hakkimizda");
		mv.addObject("hakkimizdaMi", true);
		return mv;
	}

	@RequestMapping(value = "/iletisim")
	public ModelAndView iletisim() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Iletisim");
		mv.addObject("iletisimMi", true);
		return mv;
	}

	/*
	 * @RequestMapping(value="/test") public ModelAndView
	 * test(@RequestParam(value="greating",required=false)String greating){
	 * if(greating==null){ greating="Hello There"; } ModelAndView mv = new
	 * ModelAndView("page"); mv.addObject("greeting",greating); return mv; }
	 */

	@RequestMapping(value = "/test/{greating}")
	public ModelAndView test(@PathVariable("greating") String greating) {
		if (greating == null) {
			greating = "Hello There";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Anasayfa");
		return mv;
	}

	/* t�m �r�nleri y�kleyen method kategoride */
	@RequestMapping(value = "/goster/hepsi/urunler")
	public ModelAndView urunleriListele() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Tum Urunler");
		// kategorilri getirme
		mv.addObject("kategoriler", kategoriDAO.listele());

		mv.addObject("tumUrunleriListeleMi", true);
		return mv;
	}

	@RequestMapping(value = "/goster/kategori/{id}/urunler")
	public ModelAndView kategoriyeGoreListele(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");

		// tek kategoriye g�re kategoriDAO getirme
		Kategori kategori = null;
		kategori = kategoriDAO.get(id);
		mv.addObject("title", kategori.getAd());
		// kategori listesini g�nderme
		mv.addObject("kategoriler", kategoriDAO.listele());
		// tek kategori objesini g�nderme
		mv.addObject("kategori", kategori);
		mv.addObject("kategoriyeGoreListeliMi", true);
		return mv;
	}

	// �r�n detay

	@RequestMapping(value = "/goster/{id}/urun")
	public ModelAndView urunDetay(@PathVariable int id)
			throws UrunBulunamadiException {

		ModelAndView mv = new ModelAndView("page");
		Urun urun = urunDAO.get(id);

		if (urun == null)
			throw new UrunBulunamadiException();

		urun.setGoruntulenme(urun.getGoruntulenme() + 1);
		// goruntulenmey� gunncellestur

		urunDAO.guncelle(urun);

		mv.addObject("title", urun.getAd());
		mv.addObject("urun", urun);
		mv.addObject("urunDetayMi", true);

		return mv;

	}

	@RequestMapping(value = "/kayit")
	public ModelAndView kayit() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Iletisim");

		return mv;
	}

	/* giris sayfasi */
	@RequestMapping(value = "/login")
	public ModelAndView giris(
			@RequestParam(name = "error", required = false) String hata,

			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView mv = new ModelAndView("giris");

		if (hata != null) {

			mv.addObject("mesaj", "Hatali kullanici adi ve sifre");
		}

		if (logout != null) {

			mv.addObject("cikis", "Basarili Sekilde Cikis Yapildi!");
		}
		mv.addObject("title", "Giris");

		return mv;
	}

	// yetki yok sayfasi
	@RequestMapping(value = "/yetkiniz-yok")
	public ModelAndView yetkinizYok() {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 Yetkiniz Yok");
		mv.addObject("hataBasligi", "Uppss!");
		mv.addObject("hataAciklamasi",
				"Bu sayfayi goruntulemek icin yetkiniz yok !");

		return mv;
	}

	@RequestMapping(value = "/yap-logout")
	public String cikis(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

}
