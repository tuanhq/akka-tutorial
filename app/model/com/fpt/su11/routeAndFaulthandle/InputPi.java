package model.com.fpt.su11.routeAndFaulthandle;

import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.InputObj;

public class InputPi implements InputObj {
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

  public InputPi() {

  }

  public InputPi(int start, int numberElement) {
    this.start = start;
    this.numberElement = numberElement;
  }

}
