package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.javaacademy.afisha.entity.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlaceRepository {
    private final JdbcTemplate jdbcTemplate;

    public void create(Place place) {
        String sql = """
                insert into application.place (name, address, city) values (?, ?, ?)
                """;
        jdbcTemplate.update(sql, place.getName(), place.getAddress(), place.getCity());
    }

    public Optional<Place> findById(Integer id) {
        String sql = "select * from application.place where id = " + id;;

        try {
            Place place = jdbcTemplate.queryForObject(sql, this::placeMapper, id);
            return Optional.ofNullable(place);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Place> findAll() {
        String sql = """
                select * from application.place
                """;
        return jdbcTemplate.queryForStream(sql, this::placeMapper).toList();
    }

    private Place placeMapper(ResultSet rs) {
        try {
            Place place = new Place();
            place.setId(rs.getInt("id"));
            place.setName(rs.getString("name"));
            place.setName(rs.getString("address"));
            place.setName(rs.getString("city"));
            return place;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
