package dao.interfaces.jpa;

import model.Locatie;

/**
 * Created by Berry-PC on 06/03/2017.
 */
public interface LocatieDAO extends GenericDao<Locatie> {
    Locatie getByPlaatsnaam(String plaatsnaam);
}
