package cc.kokoko.server.ibutler.service.util;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class OrderUtil
{
  public static void main(String[] args)
    throws InterruptedException
  {
    SerialNumber serial = new FileEveryDaySerialNumber(8, "EveryDaySerialNumber.dat");
    while (true)
    {
      System.out.println(serial.getSerialNumber());
      TimeUnit.SECONDS.sleep(2L);
    }
  }
}

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.OrderUtil
 * JD-Core Version:    0.6.0
 */