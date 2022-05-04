package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Mvo;

public interface MvoDao extends GenericDao<Mvo> {
	public List<Mvo> geefAlleMvos() throws EntityNotFoundException;
}
