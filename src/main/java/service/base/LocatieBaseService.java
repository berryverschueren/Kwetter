package service.base;

import dao.interfaces.jpa.LocatieDAO;
import model.Locatie;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class LocatieBaseService {
    private LocatieDAO locatieDao;

    @Inject
    public LocatieBaseService (LocatieDAO ld) {
        locatieDao = ld;
    }

    public List<Locatie> getLocaties() {
        return locatieDao.getAll();
    }

    public Locatie getLocatie(long id) {
        return locatieDao.find(id);
    }

    public Locatie insertLocatie(String plaatsnaam) {
        if (getExactlyMatchingLocatie(plaatsnaam) != null)
            return getExactlyMatchingLocatie(plaatsnaam);

        Locatie locatie = new Locatie();
        locatie.setPlaatsNaam(plaatsnaam);
        setGeolocation(locatie, plaatsnaam);
        return locatieDao.create(locatie);
    }

    public Locatie updateLocatie(long id, String plaatsnaam) {
        Locatie locatie = getLocatie(id);
        locatie.setPlaatsNaam(plaatsnaam);
        setGeolocation(locatie, plaatsnaam);
        return locatieDao.update(locatie);
    }

    public void deleteLocatie(long id) {
        locatieDao.delete(id);
    }

    public Locatie getExactlyMatchingLocatie(String plaatsnaam) {
        return locatieDao.getByPlaatsnaam(plaatsnaam);
    }

    public void setGeolocation(Locatie locatie, String plaatsnaam) {
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=\"" + URLEncoder.encode(plaatsnaam, "UTF-8") + "\"";
            callGoogleAPI(locatie, url);
        } catch (Exception x)
        {
            System.out.println(x);
        }
    }

    public void callGoogleAPI(Locatie locatie, String url) {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonString = sb.toString().replace("\n", "").replace(" ", "");
            String lonStart = "location\":{\"lat\":";
            String latStart = ",\"lng\":";
            String endStart = "}";
            int lonPoint = jsonString.indexOf(lonStart);
            jsonString = jsonString.substring(lonPoint + lonStart.length());
            int latPoint = jsonString.indexOf(latStart);
            String lonValue = jsonString.substring(0, latPoint);
            jsonString = jsonString.substring(latPoint + latStart.length());
            int endPoint = jsonString.indexOf(endStart);
            String latValue = jsonString.substring(0, endPoint);
            double lat = Double.parseDouble(latValue);
            double lon = Double.parseDouble(lonValue);
            locatie.setLatitude(lon);
            locatie.setLongitude(lat);
        } catch (Exception x) {
            System.out.println(x);
        }
    }

}
