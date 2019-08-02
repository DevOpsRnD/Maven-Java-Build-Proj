package com.cnk.siapi.model.book;

import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQAddons {
	
	@XmlPath("Addon/")
	private List<BookRQAddon> addon;

	public BookRQAddons() {
		super();
	}

	public BookRQAddons(List<BookRQAddon> addon) {
		super();
		this.addon = addon;
	}

	public List<BookRQAddon> getAddon() {
		return addon;
	}

	public void setAddon(List<BookRQAddon> addon) {
		this.addon = addon;
	}
	
}
