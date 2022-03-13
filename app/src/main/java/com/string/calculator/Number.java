package com.string.calculator;

import com.string.calculator.calculate.CalculateFactory;
import java.util.List;
import java.util.Stack;

/**
 * 얘는 딱 인풋을 받으면 숫자들은 숫자 스택에, 연산자는 연산자스택에 넣어주는 역할만 하고싶은데 <-- 이것도 책임이 많은편인건가
 * 내가 설계한 계산기 특성상 높은 우선순위의 연산자가 있으면 연산을 해줘야 하는 상황....
 */
public class Number {

  private Stack<String> numberStack = new Stack<>();
  private Stack<OperatorSign> operatorSignStack = new Stack<>();
  private final StringBuilder stringBuilder = new StringBuilder();
  private final Operator operator;

  public Number() {
    this.operator = new Operator(new CalculateFactory());
  }

  public String calculate(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (Character c : chars) {
      ifExistHighOperatorCalculate();

      addOperatorSignToStack(c);
      addNumberToStack(c);
      addNumberToStringBuilder(c);
    }

    addNumberToStackWithStringBuilder();
    ifExistHighOperatorCalculate();

    return calculateLeftover();
  }

  public OperatorSign getOperatorSign() {
    return operatorSignStack.pop();
  }

  public void reverseNumberStack() {
    Stack<String> temp = new Stack<>();

    while (!numberStack.isEmpty()) {
      String pop = numberStack.pop();
      temp.add(pop);
    }
    numberStack = temp;
  }

  public void reverseOperatorStack() {
    Stack<OperatorSign> temp = new Stack<>();

    while (!operatorSignStack.isEmpty()) {
      OperatorSign pop = operatorSignStack.pop();
      temp.add(pop);
    }
    operatorSignStack = temp;
  }

  public String getNumber() {
    return numberStack.pop();
  }

  public int size() {
    return numberStack.size();
  }

  public void addOperatorSignToStack(Character c) {
    if (OperatorSign.isSupportedOperator(c)) {
      operatorSignStack.add(OperatorSign.valueOf(c));
    }
  }

  private void addNumberToStringBuilder(Character c) {
    if ((c >= '0' && c <= '9')) {
      stringBuilder.append(c);
    }
  }


  /**
   * 한가지 동작은 기준에 따라 다르다.
   * 보통 그 기준은 추상화 레벨에 따라 달라진다.
   */
  // if 3개를 하나의 의미로 합치면 높은 우선순위의 연산자가 있는지 확인하는 절차
  // 메서드 이름과 실제 메서드 동작이 일치하지 않는다.
  // calculate 메서드안에 calculateOne이 또 나온다
  // high operator에 대한 의미가 숨겨져있다. (타입이나 메서드 이름 등으로 나왔으면 좋겠다.)
  private void ifExistHighOperatorCalculate() {
    if (isNotHighOperator()) {
      return;
    }

    String leftValue = getNumber();
    String rightValue = getNumber();
    OperatorSign operatorSign = getOperatorSign();
    String result = operator.calculateOne(leftValue, rightValue, operatorSign);

    numberStack.add(result);
  }

  private boolean isNotHighOperator() {
    if (operatorSignStack.isEmpty()) {
      return true;
    }

    // 빠져야 하는 코드인데 현재 코드를 고치면 안됨...
    if (operatorSignStack.size() >= numberStack.size()) {
      return true;
    }

    OperatorSign lastOperator = operatorSignStack.peek();
    if (!(lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply)) {
      return true;
    }
    return false;
  }

  private void addNumberToStackWithStringBuilder() {
    if (!stringBuilder.isEmpty()) {
      numberStack.add(stringBuilder.toString());
      stringBuilder.setLength(0);
    }
  }

  private void addNumberToStack(char c) {
    /**
     * 빈칸인지 체크, 스트링빌더에 숫자가 있는지 확인후 스택에 추가
     * 메서드 이름 ifBlankCheckNumberInStringBuilder를 보았을때 빈칸이면 스트링빌더에 숫자를 체크한다는 것으로만 보임. 수정 필요.
     * 결국 이 메서드가 하는 동작은 스트링빌더에 있는 숫자를 스택에 넣으려고 한 것이니, 스택에 넣을수 있을지 없을지만 확인하면 되는걸로 보임.
     * 그러면 원래 빈칸인지?, 숫자가 있는지? 묻던 걸 하나로 묶어 추상화 레벨을 맞춰주면 될거 같음.
     * 근데 어떻게 묶어볼까 --> private 메서드
     * 이유는? --> 빈칸인지 확인하는 for 문 부분도 해당 클래스에 있고 숫자를 저장해두는 StringBuilder도 해당 클래스의 필드 부분에 있으므로
     * 응집성이 높아질 것이라고 판단.
     *
     */
    if (canAddNumberToStack(c)) {
      return;
    }

    numberStack.add(stringBuilder.toString());
    stringBuilder.setLength(0);
  }

  /**
   * 이름만 보면 스택에 넘버를 추가할수 있는지에 관해 묻는 메서드인데
   * 사용을 하는 부분에선 true를 하면 return 해버려서 수정할 필요가 있어보인다.
   */
  private boolean canAddNumberToStack(char c) {
    // 빈칸인지?
    if (c != ' ') {
      return true;
    }
    // 숫자가 있는지
    return stringBuilder.length() <= 0;
  }

  private String calculateLeftover() {

    reverseNumberStack();
    reverseOperatorStack();

    while (size() > 1) {
      String leftValue = getNumber();
      String rightValue = getNumber();

      OperatorSign operatorSign = getOperatorSign();

      String result = operator.calculateOne(leftValue, rightValue, operatorSign);
      numberStack.add(result);
    }

    return getNumber();
  }

}
