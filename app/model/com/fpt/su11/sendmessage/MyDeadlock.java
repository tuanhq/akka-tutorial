package model.com.fpt.su11.sendmessage;

public class MyDeadlock {
  /*
   * MY example about deallock
   */
  
  String str1 = "Java";
  String str2 = "UNIX";
   
  Thread trd1 = new Thread("My Thread 1"){
      public void run(){
        System.out.println("Start run thread1");
          while(true){
              synchronized(str1){
                System.out.println("Start block str1");
                  synchronized(str2){
                    System.out.println("Start block str2");
                      System.out.println(str1 + str2);
                  }
              }
          }
      }
  };
   
  Thread trd2 = new Thread("My Thread 2"){
      public void run(){
         System.out.println("Start run thread2");
          while(true){
              synchronized(str2){
                System.out.println("Start block str2");
                  synchronized(str1){
                    System.out.println("Start block str1");
                      System.out.println(str2 + str1);
                  }
              }
          }
      }
  };
   
  public static void main(String a[]){
      MyDeadlock mdl = new MyDeadlock();
      mdl.trd1.start();
      mdl.trd2.start();
  }
}

