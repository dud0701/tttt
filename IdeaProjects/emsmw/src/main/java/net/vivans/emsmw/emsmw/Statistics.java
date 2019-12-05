package net.vivans.emsmw.emsmw;

import lombok.extern.slf4j.Slf4j;
import net.vivans.emsmw.emsmw.service.AttachCmmMeasInfoId;
import net.vivans.emsmw.emsmw.service.CMGStatistics;
import net.vivans.emsmw.emsmw.service.CMMStatistics;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class Statistics implements CommandLineRunner {

    public static CMGStatistics cmgStatistics = new CMGStatistics();
    public static CMMStatistics cmmStatistics = new CMMStatistics();
    public static AttachCmmMeasInfoId attachCmmMeasInfoId = new AttachCmmMeasInfoId();
    @Override
    public void run(String... args) throws Exception {
        this.parseCommandLine(args);
    }

    public static void parseCommandLine(String[] aInArgs) throws Exception {
        //-cmm or -cmg  -inputDir, -ouputDir, -time
        log.info("aInArgs : " + aInArgs + ", len : " + aInArgs.length);

        int lIndex = 0;
        String lMessageType = "";
        String lInputDir = "";
        String lOutputDir = "";
        boolean lIsPersistent = false;
        String lTime = "";
        String lClientId = "";
        String[] argsParameter;
        while (lIndex < aInArgs.length){
            if (aInArgs[lIndex].equals("-cmg") || aInArgs[lIndex].equals("-cmm"))
            {
                lMessageType = aInArgs[lIndex];
                lIndex++;

            } else if(aInArgs[lIndex].equals("-inputDir"))
            {
                lInputDir = aInArgs[++lIndex];
                lIndex++;
            } else if(aInArgs[lIndex].equals("-outputDir"))
            {
                lOutputDir = aInArgs[++lIndex];
                lIndex++;
            }else if(aInArgs[lIndex].equals("-time")) {
                lTime = aInArgs[++lIndex];
                lIndex++;
            }
            else {
                lIndex++;
            }
        }

        argsParameter = new String[]{lMessageType, lInputDir, lOutputDir, lTime};

        if(lMessageType.equals("-cmg")){
            cmgStatistics.startCMG(argsParameter);
        }else if(lMessageType.equals("-cmm")){
            attachCmmMeasInfoId.getCMMFiles(argsParameter);
            //cmmStatistics.startCmm(argsParameter);
        }
    }

}
