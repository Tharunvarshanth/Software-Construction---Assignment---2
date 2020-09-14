package kln.se.ass2.logfile;

import kln.se.ass2.SendMail;
import kln.se.ass2.logvariable.Log;
import kln.se.ass2.logvariable.Logvariables;
import kln.se.ass2.Textfilehandler;

import java.util.List;

public class Firstreadinglog extends Logfilehandler {
    Textfilehandler textfilehandler;
    Log logvariables;

    public Firstreadinglog(Textfilehandler textfilehandler, Log logvariables) {
      this.textfilehandler=textfilehandler;
      this.logvariables=logvariables;
    }

    public void errorchecking(String logfilepath) {

        int errorstate=0;
        List<Logvariables> loglines = super.readlogfile(logfilepath);
        for (Logvariables s : loglines) {
            if ((s.getLoglevel()).contains("ERROR")) {


                if(errorstate==0) {
                    System.out.println("Error Found");
                    errorstate = 1;
                    SendMail.sendmail();

                }

            }
        }

        logvariables = loglines.get(loglines.size() - 1);
        textfilehandler.writetofile(logvariables.getTimestatmp());
    }




    }

