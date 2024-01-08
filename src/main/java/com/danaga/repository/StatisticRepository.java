package com.danaga.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaga.entity.Statistic;


public interface StatisticRepository extends JpaRepository<Statistic, String>{
	// N일 총 판매수량
	@Query(value = "SELECT IFNULL(COUNT(create_date), 0) FROM orders WHERE DATE_FORMAT(create_date, '%Y%m%d') = :findDate", nativeQuery = true)
	Long countTotSalesOn(@Param("findDate") String findDate);
	
	// N일 총 판매수익
	@Query(value = "SELECT IFNULL(SUM(price), 0) FROM orders WHERE DATE_FORMAT(create_date, '%Y%m%d') = :findDate", nativeQuery = true)
	Long countTotRevenueOn(@Param("findDate") String findDate);
	
	// N일 가입한 신규회원 수
	@Query(value = "SELECT IFNULL(COUNT(id), 0) FROM member WHERE DATE_FORMAT(join_date, '%Y%m%d') = :findDate", nativeQuery = true)
	Long countNewMembersOn(@Param("findDate") String findDate);
	
	// N일 작성된 게시글 수 완료
	@Query(value = "SELECT IFNULL(COUNT(id), 0) FROM board WHERE DATE_FORMAT(create_time, '%Y%m%d') = :findDate", nativeQuery = true)
	Long countNewBoardsOn(@Param("findDate") String findDate);
	
	//최근 7일간의 통계 기록
	List<Statistic> findTop7ByOrderByIdDesc();
	
	// YYYYMM월의 기록
	List<Statistic> findByIdStartsWith(String id);
	
	// M월 총 주문 건수
	@Query(value = "SELECT IFNULL(COUNT(create_date), 0) FROM orders WHERE DATE_FORMAT(create_date, '%Y%m') = :findMonth", nativeQuery = true)
	Long countTotSalesThisMonth(@Param("findMonth") String findMonth);
	
	// M월 배송중+입금대기 건수
	@Query(value = "SELECT IFNULL(COUNT(create_date), 0) FROM orders WHERE DATE_FORMAT(create_date, '%Y%m') = :findMonth AND (statement = '배송중' OR statement = '입금대기중')", nativeQuery = true)
	Long countToSalesThisMonth(@Param("findMonth") String findMonth);
	
	// 환불대기+환불완료+취소 건수
	@Query(value = "SELECT COALESCE(COUNT(create_date), 0) FROM orders WHERE DATE_FORMAT(create_date, '%Y%m') = :findMonth AND (statement = '환불대기중' OR statement = '환불완료' OR statement = '취소')", nativeQuery = true)
	Long countFailSalesThisMonth(@Param("findMonth") String findMonth);

}
