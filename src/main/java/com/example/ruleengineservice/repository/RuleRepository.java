package com.example.ruleengineservice.repository;
import com.example.ruleengineservice.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {

}