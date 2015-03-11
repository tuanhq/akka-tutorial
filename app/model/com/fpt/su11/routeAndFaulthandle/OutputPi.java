package model.com.fpt.su11.routeAndFaulthandle;

import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.OutputObj;

public class OutputPi implements OutputObj{
  private double result;

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }
  private int start;
  private int numberElement;

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getNumberElement() {
    return numberElement;
  }

  public void setNumberElement(int numberElement) {
    this.numberElement = numberElement;
  }
  public OutputPi() {
   
  }
  public OutputPi(double result) {
    this.result = result;
  }
  public OutputPi(double result, int start, int numberElement) {
    this.numberElement = numberElement;
    this.start = start;
    this.result = result;
  }
}
