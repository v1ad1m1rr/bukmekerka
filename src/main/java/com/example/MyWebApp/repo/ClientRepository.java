package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String username);

    @Query(value = """
            select t1.name, t2.name, m.match_date, s.summa, s.koef, s.date, ifnull(v.summa, 'в процессе'), ifnull(v.date, 'в процессе') from bukmekerka.stavka s
                     join bukmekerka.client c on s.id_client = c.id_client
                     join bukmekerka.matches m on s.id_match = m.id_match
                     left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                     join bukmekerka.team t1 on m.id_team1 = t1.id
                     join bukmekerka.team t2 on m.id_team2 = t2.id
            where c.id_client = :clientId
            order by 6
            """, nativeQuery = true)
    List<String> findByCustomQuery(@Param("clientId") int clientId);

    @Query(value = """
            select sum(s.summa), sum(v.summa) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            where c.id_client = :clientId""", nativeQuery = true)
    List<String> findSumStavkaViplata(@Param("clientId") int clientId);

    @Query(value = """
            select count(s.summa) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            where c.id_client = :client_id
            union all
            select count(v.summa) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            where c.id_client = :client_id and v.summa > 0
            union all
            select count(v.summa) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            where c.id_client = :client_id and v.summa = 0
            union all
            select count(*) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            where c.id_client = :client_id and v.summa is null
            """, nativeQuery = true)
    List<String> findCountsAnyStavok(@Param("client_id") int client_id);

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
            where c.id_client = :client_id and v.summa is null
            order by 10
            """, nativeQuery = true)
    List<String> findBetsViplataNull(@Param("client_id") int client_id);
}

