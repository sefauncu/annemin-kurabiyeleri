package com.yilmazmehmet.anneminkurabiyeleri.controller;

import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.LoggerFactory;

import com.yilmazmehmet.anneminkurabiyeleribackend.dao.KategoriDAO;
import com.yilmazmehmet.anneminkurabiyeleribackend.dao.UrunDAO;
import com.yilmazmehmet.anneminkurabiyeleribackend.dto.Kategori;
import com.yilmazmehmet.anneminkurabiyeleribackend.dto.Urun;

@Controller
@RequestMapping("/yonetim")
public class YonetimController {
	
	@Autowired
	private KategoriDAO kategoriDAO;
	
	@Autowired
	private UrunDAO urunDA;
	private static Logger logger=LoggerFactory.getLogger(YonetimController.class);
	
	
	@RequestMapping(value="/urunler",method=RequestMethod.GET)
	public ModelAndView urunleriYonet(@RequestParam(name="operation",required=false)String operation){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("urunYonetimMi",true);
		mv.addObject("title","Urunleri Yonet");
		
		Urun yeniUrun= new Urun();
		yeniUrun.setSaticiId(1);
		yeniUrun.setAktifMi(true);
		mv.addObject("urun",yeniUrun);
		
		if(operation!=null){
			if(operation.equals("urun")){
				
				mv.addObject("mesaj","Urun Basarili Sekilde Gonderildi");
			}
			
		}
		return mv;
		
	}
	
	@RequestMapping(value="/urunler",method=RequestMethod.POST)
	public String urunlerPostEtme(@ModelAttribute("urun") Urun mUrun){
		logger.info(mUrun.toString());
		//yeni urun kaydet
				urunDA.ekle(mUrun);
		return "redirect:/yonetim/urunler?operation=urun"; 
	}
	
	@ModelAttribute("kategoriler")
	public List<Kategori> kategorileriGetir(){
		
		return kategoriDAO.listele();
	}

}