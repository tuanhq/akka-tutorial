package model.com.fpt.su11.routeAndFaulthandle;

public interface DefintionExp {
  public static class ResumeExp extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public ResumeExp() {
      System.out.println("This is resume exception");
    }

  }

  public static class RestartExp extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public RestartExp() {
      System.out.println("This is resume exception");
    }

  }

  public static class StopExp extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public StopExp() {
      System.out.println("This is resume exception");
    }
  }
}
