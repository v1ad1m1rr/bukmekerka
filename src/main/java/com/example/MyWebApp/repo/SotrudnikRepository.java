package com.example.MyWebApp.repo;

import com.example.MyWebApp.models.Sotrudnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SotrudnikRepository extends JpaRepository<Sotrudnik, Integer> {
    Sotrudnik findByEmail(String username);

    @Query(value = """
            select c.id_client, c.second_name, c.first_name, c.otchestvo, t1.name, t2.name, m.match_date, s.summa, s.koef, s.date, v.summa, v.date from bukmekerka.stavka s
                                   join bukmekerka.client c on s.id_client = c.id_client
                                   join bukmekerka.matches m on s.id_match = m.id_match
                                   left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                                   join bukmekerka.team t1 on m.id_team1 = t1.id
                                   join bukmekerka.team t2 on m.id_team2 = t2.id
                       where day(v.date) = :day and month(v.date) = :month and year(v.date) = :year
                                   order by 1, 10
            """, nativeQuery = true)
    List<String> findClientsAndStavki(@Param("day") int day, @Param("month") int month, @Param("year") int year);

    @Query(value = """
            select sum(s.summa), sum(v.summa) from bukmekerka.stavka s
            join bukmekerka.client c on s.id_client = c.id_client
            join bukmekerka.matches m on s.id_match = m.id_match
            left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
            order by 1
            """, nativeQuery = true)
    List<String> findStavkiViplati();

    @Query(value = """
            select count(s.summa) from bukmekerka.stavka s
            			join bukmekerka.client c on s.id_client = c.id_client
                        join bukmekerka.matches m on s.id_match = m.id_match
                        left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                        union all
                        select count(v.summa) from bukmekerka.stavka s
                        join bukmekerka.client c on s.id_client = c.id_client
                        join bukmekerka.matches m on s.id_match = m.id_match
                        left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                        where v.summa > 0
                        union all
                        select count(v.summa) from bukmekerka.stavka s
                        join bukmekerka.client c on s.id_client = c.id_client
                        join bukmekerka.matches m on s.id_match = m.id_match
                        left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                        where v.summa = 0
                        union all
                        select count(*) from bukmekerka.stavka s
                        join bukmekerka.client c on s.id_client = c.id_client
                        join bukmekerka.matches m on s.id_match = m.id_match
                        left join bukmekerka.viplata v on s.id_stavka = v.id_stavka
                        where v.summa is null
            """, nativeQuery = true)
    List<String> findCountSucAnsLos();

    @Query(value = """
            with months as (
                SELECT 1 AS month UNION ALL
                SELECT 2 UNION ALL
                SELECT 3 UNION ALL
                SELECT 4 UNION ALL
                SELECT 5 UNION ALL
                SELECT 6 UNION ALL
                SELECT 7 UNION ALL
                SELECT 8 UNION ALL
                SELECT 9 UNION ALL
                SELECT 10 UNION ALL
                SELECT 11 UNION ALL
                SELECT 12
            )

            select m.month, ifnull(sum(s.summa), 0), ifnull(vip.total_sum_vip, 0) from months m
            left join bukmekerka.stavka s on m.month = month(s.date)
            left join (select\s
            				m.month as mon,
            				sum(v.summa) as total_sum_vip
            			from months m\s
            			left join bukmekerka.viplata v on m.month = month(v.date)
            			group by m.month
            			order by m.month) vip on m.month = vip.mon
            group by m.month
            order by m.month
            """, nativeQuery = true)
    List<String> findStavVipAtMonth();

    @Query(value = """
            select nazvanie, ifnull(sum(s.summa), 0) from bukmekerka.stavka s
            join bukmekerka.matches m on s.id_match = m.id_match
            right join bukmekerka.kat_match k on m.kat_match = k.id_kat_match
            group by k.nazvanie
            """, nativeQuery = true)
    List<String> findBestKatMatch();
}
