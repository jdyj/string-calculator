package com.string.calculator.input;

import com.string.calculator.InputValue;
import com.string.calculator.web.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

final class WebInput implements Input {

  private final String[] args;

  public WebInput(String[] args) {
    this.args = args;
  }

  /**
   * 서버가 안닫힘. 이건 증상
   * <p>
   * 문제가 뭔지 ? 아무것도 모르는 사람이 들었을 때 알아듣도록 한문장으로
   * <p>
   * jvm 역할, 내부 동작
   * <p>
   * 문제 : main은 실행 시켰을 때, thread in, jvm 관점에서 설명 Process finished with exit code 0 process and
   * thread 차이 main thread 와 process 관계
   */


  @Override
  public String enter() {
    Thread springBootThread = new Thread(() -> {
      ConfigurableApplicationContext context = SpringApplication.run(SpringBootApplication.class,
          args);
      System.out.println("POST http://localhost:8080/");
      InputValue inputValue = InputValue.getInstance();
      while (true) {
        if (inputValue.getValue() != null) {
          break;
        }
      }
      context.close();
    });

    springBootThread.start();

    try {
      springBootThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return InputValue.getInstance().getValue();
  }
}
