package com.string.calculator.format;

import org.json.simple.JSONObject;

final class JsonFormat implements Format {

  private final String result;

  public JsonFormat(String result) {
    this.result = result;
  }

  @Override
  public String make() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("result", result);
    return jsonObject.toJSONString();
  }


}
