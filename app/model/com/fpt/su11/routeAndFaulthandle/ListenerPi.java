package model.com.fpt.su11.routeAndFaulthandle;



public  class ListenerPi extends Listener {
  
  private int counter = 0;
  private int totalMessage;
  private double total = 0.0;
  
  public ListenerPi(int totalMessage) {
    this.totalMessage = totalMessage;
  }
  @Override
  public void summaryResult(Object mesage) {
    if (mesage instanceof OutputPi) {
      // System.out.println("start receive message from master");
      OutputPi outputPi = (OutputPi) mesage;
      System.out.println("counter:" + counter + " total message:" + totalMessage +" start:" + outputPi.getStart() + " numberElement:"
          + outputPi.getNumberElement() + " result:" + outputPi.getResult());
      outputPi.getResult();
      counter++;
      // System.out.println("TOTAL :" + total + " counter:" + counter +
      // " totalMessage:" + totalMessage);
      total += outputPi.getResult();
      if (counter < totalMessage) {

      } else {
        System.out.println("Result caculate PI :" + total);
      }
    }

  }

  @Override
  public boolean checkCondition() {
    // TODO Auto-generated method stub
    return false;
  }
  
}


