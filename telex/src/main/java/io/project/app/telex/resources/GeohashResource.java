package io.project.app.telex.resources;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class GeohashResource {

    @GetMapping("/geohash")
    public ResponseEntity getGeohash(@RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude) {

        String geohash = GeoHash.withCharacterPrecision(latitude, longitude, 10).toBase32();

        return ResponseEntity.status(HttpStatus.OK).body(geohash);
    }

    @GetMapping("/geohash/check")
    public boolean checkLocation(
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude,
            @RequestParam("geohash") String geohash) {
        GeoHash geo = GeoHash.fromGeohashString(geohash);
        WGS84Point point = new WGS84Point(latitude, longitude);
      
        return geo.contains(point);
    }

    @GetMapping("/location/by/geohash")
    public ResponseEntity getLocation(@RequestParam("geohash") String geohash) {
        GeoHash geo = GeoHash.fromGeohashString(geohash);
        double latitude = geo.getBoundingBox().getCenter().getLatitude();
        double longitude = geo.getBoundingBox().getCenter().getLongitude();
        String result = "Latitude: " + latitude + ", Longitude: " + longitude;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}


// Armenia 40.177200 44.503490 szpssdn3nk
// Yerevan 40.177200 44.503490 szpssdn3nk
// Vanadzor, Lori, Armenia (40.807400, 44.497028)
