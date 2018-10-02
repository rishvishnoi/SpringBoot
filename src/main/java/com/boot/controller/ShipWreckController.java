package com.boot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipWreckController {
	@Autowired
	private ShipwreckRepository shipwreckrepository;
	@RequestMapping(value="shipwrecks",method=RequestMethod.GET)
	public List<Shipwreck> list(){
		return shipwreckrepository.findAll();
	}
	@RequestMapping(value="shipwrecks",method=RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck){
		return shipwreckrepository.saveAndFlush(shipwreck);
	}
	@RequestMapping(value="shipwrecks/{id}",method=RequestMethod.GET)
	public Shipwreck get(@PathVariable Long id){
		return shipwreckrepository.findOne(id);
	}
	@RequestMapping(value="shipwrecks/{id}",method=RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id,@RequestBody Shipwreck shipwreck){
		Shipwreck  existingshipwreck=shipwreckrepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingshipwreck);
		return shipwreckrepository.saveAndFlush(existingshipwreck);
	}
	@RequestMapping(value="shipwrecks/{id}",method=RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id){
		Shipwreck  existingshipwreck=shipwreckrepository.findOne(id);
		shipwreckrepository.delete(existingshipwreck);
		return existingshipwreck;
	}
}
