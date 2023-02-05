//package com.string.calculator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class CalculationHistoryTest {
//
//  CalculationHistory calculationHistory = new CalculationHistory();
//
//  @Test
//  void calculationHistoryTest1() {
//    calculationHistory.중이야(
//        new History(new Number("1"), OperatorSign.plus, new Number("2"), new Number("3")));
//
//    assertEquals("1. 1 + 2\n2. 3", calculationHistory.toString());
//  }
//
//  @Test
//  void calculationHistoryTest2() {
//    calculationHistory.중이야(
//        new History(new Number("1"), OperatorSign.plus, new Number("1"), new Number("2")));
//
//    calculationHistory.중이야(
//        new History(new Number("1"), OperatorSign.plus, new Number("1"), new Number("2")));
//
//    assertEquals("1. 1 + 1 + 1\n2. 1 + 2", calculationHistory.toString());
//  }
//
//  @Test
//  void calculationHistoryTest3() {
//    calculationHistory.중이야(
//        new History(new Number("1"), OperatorSign.plus, new Number("1"), new Number("2")));
//
//    calculationHistory.중이야(
//        new History(new Number("1"), OperatorSign.plus, new Number("1"), new Number("2")));
//
//    assertEquals("1. 1 + 1 + 1\n2. 1 + 2", calculationHistory.toString());
//  }
//
//}