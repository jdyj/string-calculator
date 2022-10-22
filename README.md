# 문자열 계산기 ReadMe

# **문자열 계산기**

콘솔 애플리케이션

### **제약조건**

1. 테스트를 손으로 일일이 하지않고 코드를 작성하여 자동화된 테스트를 할 수 있도록 하기
2. 커밋 의미있게 해보기
3. 최대한 불변형태로 작성 setter 쓰지 않기
4. 인덴트 2안으로 하도록 해보아라
5. else 없도록 해보아라
6. 테스트 많이 꼼꼼하게 작성
7. 추상화 레벨을 맞춰라
8. private 메서드를 의미있게 짜보기
9. 상태와 비즈니스 로직을 위로 끌어올리기

### **테스트**

1. 의미있는 단위로 테스트 쪼개기
2. 테스트 메소드 이름, 설명 적기

### **기능**

1. 사칙 연산(덧셈, 뺄셈, 곱셈, 나눗셈)
2. 우선순위 연산자 (괄호)
3. 분수 계산
4. json, xml, plain-text 출력 포맷 지정
5. 표준, 파일, 웹 출력 위치 지정
6. 분수 출력 지원

    ```
      2     12
    - -     --
      5      5
    ```

7. 계산 과정 출력

## 리팩토링

### 관심사 분리

기존에 코드는 input을 받으면 차례대로 순회하며 기호(+, -, *, /) 기호끼리, 숫자는 숫자끼리 나눠서 스택에 저장해 계산을 하는 방식이었습니다.

스택에 저장은 Number와 Operator 클래스에서 했고 input을 순회하며 나오는 char 타입 하나씩 받아서 각 클래스의 메소드를 통해 처리했습니다.

```java
public String run(String input){

    Operator operator=new Operator(number);

    List<Character> chars=input.chars()
    .mapToObj(c->(char)c)
    .toList();

    for(Character c:chars){

    number.ifBlankCheckNumberInStringBuilder(c);
    operator.ifExistHighOperatorCalculate();

    number.addNumberToStringBuilder(c);
    operator.addOperatorSignToStack(c);

    }

    number.addNumberToStackWithStringBuilder();
    operator.ifExistHighOperatorCalculate();

    return operator.calculateLeftover();
    }

```

메소드를 얼핏 봤을 때 무슨 행동을 하는 메소드인지 한눈에 파악하기 힘들고 심지어 메소드의 이름 외의 작업을 하는 경우가 많았습니다.

즉, 각각의 메소드의 이름이 길면서 명확하지 않으니 이해가 어렵고 가독성이 떨어지는 것입니다.

상태 또한 각 Number, Operator 클래스가 가지고 있고 내부에서 상태변화가 일어나는데 중구난방으로 스택에 add가 되어 어디서 어떻게 상태가 추가되는지 제대로 알 수
없었습니다.

우선 상태를 한곳에서 관리하기 위해 Number와 Operator를 합쳤습니다. 그랬더니 결국 각 클래스에 존재하던 모든 메소드들이 하나의 클래스로 합쳐지면서 계산의 처음부터
끝까지 모든 일을 하는 클래스가 되었습니다.

```java
// 상태 코드
private final NumberCollection numberCollection=new NumberCollection();
private final OperatorCollection operatorCollection=new OperatorCollection();
private final NumberPiece numberPiece=new NumberPiece();

public String calculate(String input){
    List<Character> chars=input.chars()
    .mapToObj(c->(char)c)
    .toList();

    for(Character c:chars){
    execute(c);
    }
    checkLast();
    return getResult();
    }

private void execute(Character c){
    if(existHighOperatorSign()){
    addNumber();
    }

    if(OperatorSign.isSupportedOperator(c)){
    operatorCollection.add(OperatorSign.valueOf(c));
    }

    if(canAddNumberToCollection(c)){
    numberCollection.add(numberPiece.getNumber());
    }

    if(isNumberPiece(c)){
    numberPiece.add(c);
    }
    }
```

상태가 변하는 부분을 한 클래스에서 확인할 수 있다는 점 빼곤 코드의 복잡성은 더 올라갔습니다. 심지어 상태가 변하는 것도 내부 private 메소드를 여러번 들어가야 확인할 수
있었습니다.

지금까지 정적인 것. 즉, 데이터와 같은 것인 연산자, 피연산자로 분리를 해왔는데 여기서부터 동작에 집중을 하기 시작했습니다.

**해당 메소드는 어떤 동작을 하는 것인지, 더 깊게 들어가 해당 if문, while문 등은 어떤 동작을 위한 분기인지에 대해 주석을 달면서 각각 동작을 써내려갔습니다.**

```java
private void execute(Character c){
    // 높은 우선순위 연산자 확인
    if(existHighOperatorSign()){
    addNumber();
    }

    // 파싱 - 연산자
    if(OperatorSign.isSupportedOperator(c)){
    operatorCollection.add(OperatorSign.valueOf(c));
    }

    // 파싱 - 피연산자
    if(canAddNumberToCollection(c)){
    numberCollection.add(numberPiece.getNumber());
    }

    // 파싱 - 피연산자
    if(isNumberPiece(c)){
    numberPiece.add(c);
    }
    }
```

하나씩 써보니 크게 두 가지로 나누는 것을 볼 수 있었습니다.

하나는 연산자인지 피연산자인지 구분해주는 것 (파싱)

나머지 하나는 계산을 하고 스택에 넣는 것이었습니다.

각각의 관심으로 나누어진 것대로 메소드를 재구성 하였습니다. 파싱 담당하는 것은 파싱 담당하는 것 끼리, 나머지는 나머지끼리 나눈 결과

```java
// 상태 코드 생략

public String calculate(String input){
    List<Character> chars=input.chars()
    .mapToObj(c->(char)c)
    .toList();

    for(Character c:chars){
    // 높은 우선순위 연산자 확인
    if(existHighOperatorSign()){
    addNumber();
    }
    parse(c);
    }
    checkLast(); // for문의 마지막 인덱스를 확인하기 위해 존재하는 메소드
    return getResult();
    }

private void parse(Character c){
    // 파싱 - 연산자
    if(OperatorSign.isSupportedOperator(c)){
    operatorCollection.add(OperatorSign.valueOf(c));
    }
    // 파싱 - 피연산자
    if(canAddNumberToCollection(c)){
    numberCollection.add(numberPiece.getNumber());
    }
    // 파싱 - 피연산자
    if(isNumberPiece(c)){
    numberPiece.add(c);
    }
    }
```

다음으로 진행한 것은 **상태 변화가 일어나는 부분을 최상단으로 끌어올리는 것**이었습니다.

해당 부분을 함수형 인터페이스인 Consumer와 Runnable을 사용했습니다.

```java
public String calculate(String input){
    List<Character> chars=input.chars()
    .mapToObj(c->(char)c)
    .toList();

    for(int i=0;i<chars.size();i++){
    Character c=chars.get(i);
    boolean last=i==chars.size()-1;
    parse(c,last
    ,(ch)->operatorCollection.add(OperatorSign.valueOf(ch))
    ,()->numberCollection.add(numberPiece.getNumber()));

    if(existHighOperatorSign()){
    addNumber();
    }
    }
    return getResult();
    }

private void parse(Character c,boolean last,Consumer<Character> operationCollectionAdd,Runnable numberCollectionAdd){
    // 파싱 - 연산자
    if(OperatorSign.isSupportedOperator(c)){
    operatorCollection.add(OperatorSign.valueOf(c));
    operationCollectionAdd.accept(c);
    }

    // 파싱 - 피연산자
    if(canAddNumberToCollection(c)){
    numberCollection.add(numberPiece.getNumber());
    numberCollectionAdd.run();
    }

    // 파싱 - 피연산자
    if(isNumberPiece(c)){
    numberPiece.add(c);
    }

    if(last){
    if(numberPiece.hasNumber()){
    numberCollectionAdd.run();
    }
    }
    }
```

이제 최상단에서 parse메소드에서 operatorCollection과 numberCollection에 상태가 추가된다는 것을 확인할 수 있습니다.

여기서부터 파싱하는 부분만을 따로 빼내고 싶었습니다.

그래서 **해당 부분을 Parsing이라는 클래스에 담았습니다.**

```java
public class Parsing {

  private final Consumer<Character> operatorCollectionAdd;
  private final Consumer<String> numberCollectionAdd;
  private final NumberPiece numberPiece = new NumberPiece();

  public Parsing(Consumer<Character> operatorCollectionAdd, Consumer<String> numberCollectionAdd) {
    this.operatorCollectionAdd = operatorCollectionAdd;
    this.numberCollectionAdd = numberCollectionAdd;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      execute(c, last);
      // 우선순위 연산자 탐색
      if (machine.existHighOperatorSign()) { // 컴파일 에러
        machine.addStack();
      }
    }
  }

  private void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      operatorCollectionAdd.accept(c);
    }

    if (canAddNumberToCollection(c)) {
      numberCollectionAdd.accept(numberPiece.getNumber());
    }

    if (isNumberPiece(c)) {
      numberPiece.add(c);

      if (last) {
        if (numberPiece.hasNumber()) {
          numberCollectionAdd.accept(numberPiece.getNumber());
        }
      }
    }
  }
}
```

[//]: # (어째저째  **numberCollection, operatorCollection에 add를 해줘야하는 것**이 문제였습니다.)

[//]: # ()

[//]: # (numberCollection과 operatorCollection에 add 하려면 결국 해당 클래스들을 가져와야하는데 그러면 분리를 하기전 기존 클래스와 변함이 없기 때문에 의미가 없습니다.)

**여기서 인터페이스를 도입해 상태를 관리하는 클래스들과 Parsing 클래스의 관계를 느슨하게 만들었습니다.**

Parsing 입장에서는 누가 사용하는 메소드인지는 모르지만 일단 본인이 파싱한 피연산자, 연산자를 넘겨주는 **메시지**를 담당하는 인터페이스를 만들었습니다.

```java
public interface ParsingHandler {

  void operatorParsed(Operator operatorSign);

  void numberParsed(Number number);

}
```