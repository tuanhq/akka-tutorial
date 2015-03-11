package model.com.fpt.su11.routeAndFaulthandle;

import model.com.fpt.su11.routeAndFaulthandle.DefintionExp.ResumeExp;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.InputObj;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.OutputObj;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.WorkApi;

public class WorkPi implements WorkApi {
  private int state = 0;

  @Override
  public OutputObj executeWork(InputObj input) throws ResumeExp {
    // TODO Auto-generated method stub
    InputPi inputPi = (InputPi)input;
    int start = inputPi.getStart();
    int numberElement = inputPi.getNumberElement();    
    double reulsut = 0.00;
    for (int i = start * numberElement; i <= ((start + 1) * numberElement - 1); i++) {
//      if( i == 99999 && state == 0 ) {      
//        System.out.println("state=:" + state);
//         state =1;
//        throw new DefintionExp.ResumeExp();
//      }
      reulsut += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
    }
    OutputPi outputPi = new OutputPi(reulsut,start,numberElement);    
    return outputPi;
  }

  

}
