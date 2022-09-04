package com.string.calculator.format;

import org.json.simple.JSONObject;

final class JsonFormat implements Format {

  private final String result;
  private final String calculationProcess;

  public JsonFormat(String result, String calculationProcess) {
    this.result = result;
    this.calculationProcess = calculationProcess;
  }

  @Override
  public String make() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("result", result);
    if (calculationProcess != null) {
      jsonObject.put("process", calculationProcess);
    }
    return jsonObject.toJSONString();
  }


}
