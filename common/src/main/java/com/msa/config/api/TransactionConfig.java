package com.msa.config.api;

//import java.util.HashMap;
//import java.util.Map;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
//import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.RequiredArgsConstructor;

//@Configuration
@RequiredArgsConstructor
public class TransactionConfig {

   private final TransactionManager transactionManager;

   @Bean
   public TransactionInterceptor txAdvice() {
       // 읽기 전용 트랜잭션
       RuleBasedTransactionAttribute readOnlyTxAttr = new RuleBasedTransactionAttribute();
       readOnlyTxAttr.setReadOnly(true);

       // 쓰기 전용 트랜잭션
       RuleBasedTransactionAttribute writeTxAttr = new RuleBasedTransactionAttribute();
       //txAttr.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
       writeTxAttr.getRollbackRules().add(new RollbackRuleAttribute(Exception.class));

       NameMatchTransactionAttributeSource nameTxAttrSource = new NameMatchTransactionAttributeSource();

       // 방법1
       nameTxAttrSource.addTransactionalMethod("select*", readOnlyTxAttr);
       nameTxAttrSource.addTransactionalMethod("insert*", writeTxAttr);
       nameTxAttrSource.addTransactionalMethod("update*", writeTxAttr);
       nameTxAttrSource.addTransactionalMethod("delete*", writeTxAttr);
       // 그 외엔 토랜잭션 걸기
       //nameTxAttrSource.addTransactionalMethod("*", writeTxAttr);

       // 방법2
//       Map<String, TransactionAttribute> txMethods = new HashMap<>();
//       txMethods.put("select*", readOnlyTxAttr);
//       txMethods.put("insert*", writeTxAttr);
//       txMethods.put("update*", writeTxAttr);
//       txMethods.put("delete*", writeTxAttr);
//       nameTxAttrSource.setNameMap(txMethods);

       return new TransactionInterceptor(transactionManager, nameTxAttrSource);
   }

   @Bean
   public Advisor transactionAdviceManager() {
       AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
       pointcut.setExpression("execution( * com..service.impl.*ServiceImpl.*(..))"); // 복잡도가 높다

       return new DefaultPointcutAdvisor(pointcut, txAdvice());
   }
}
