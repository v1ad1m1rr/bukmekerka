package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Matches, Integer> {

    @Query(value = """
            select k.nazvanie, t1.name, t2.name, m.match_date, m.koef1, m.koef2, m.id_match, ch.name, sm.status, m.count_team1, m.count_team2, t1.id, t2.id, "1" as logo1, "2" as logo2 from bukmekerka.matches m
                join bukmekerka.kat_match k on m.kat_match = k.id_kat_match
                join bukmekerka.championship ch on m.championship = ch.id
                join bukmekerka.team t1 on m.id_team1 = t1.id
                join bukmekerka.team t2 on m.id_team2 = t2.id
                join bukmekerka.status_match sm on m.id_status = sm.id_status_match
            where m.match_date > now() and m.id_status <> 3
            order by 4
            """, nativeQuery = true)
    List<String> findAllMatches();

    @Query(value = """
            select k.nazvanie, t1.name, t2.name, m.match_date, m.koef1, m.koef2, m.id_match, ch.name, sm.status, m.count_team1, m.count_team2, t1.id, t2.id, "1" as logo1, "2" as logo2 from bukmekerka.matches m
                join bukmekerka.kat_match k on m.kat_match = k.id_kat_match
                join bukmekerka.championship ch on m.championship = ch.id
                join bukmekerka.team t1 on m.id_team1 = t1.id
                join bukmekerka.team t2 on m.id_team2 = t2.id
                join bukmekerka.status_match sm on m.id_status = sm.id_status_match
            where m.match_date > now() and m.kat_match = :kat_match and m.id_status <> 3
            order by 4
            """, nativeQuery = true)
    List<String> findMatchByCategory(@Param("kat_match") int kat_match);

    @Query(value = """
            select t1.name, t2.name from bukmekerka.matches m
            	join bukmekerka.team t1 on m.id_team1 = t1.id
                join bukmekerka.team t2 on m.id_team2 = t2.id
            where m.id_match = :id_match
            """, nativeQuery = true)
    List<String> findNameTeams(@Param("id_match") int id_match);

    @Query(value = """
            select c.id_client, c.second_name, c.first_name, c.otchestvo, t1.name, t2.name, m.match_date, s.summa, s.koef, s.date, km.nazvanie, ch.name, bet_t.id, m.id_match, "" as sumSell, s.id_stavka from bukmekerka.stavka s
            	join bukmekerka.client c on s.id_client = c.id_client
            	join bukmekerka.matches m on s.id_match = m.id_match
            	left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            	join bukmekerka.team t1 on m.id_team1 = t1.id
            	join bukmekerka.team t2 on m.id_team2 = t2.id
                join bukmekerka.kat_match km on m.kat_match = km.id_kat_match
                join bukmekerka.championship ch on m.championship = ch.id
                join bukmekerka.team bet_t on s.id_bet_team = bet_t.id
            where m.id_match = :id_match and s.id_status = 1
            order by 10
            """, nativeQuery = true)
    List<String> findStavkaOnEndMatch(@Param("id_match") int id_match);
}
