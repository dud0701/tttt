package net.vivans.emsmw.emsmw.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.vivans.emsmw.emsmw.cmg.CmgStatisticsInfoTable;
import net.vivans.emsmw.emsmw.cmg.MeasCollecFile;
import net.vivans.emsmw.emsmw.util.Calculator;
import net.vivans.emsmw.emsmw.util.FileUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class CMGStatistics {
    public static MeasCollecFile sourceCmg = null;
    public static int existFileCount = 0;
    public static boolean fileInitFlag = false;

    public static String inputDir;
    public static String outpurDir;
    public static String prameterTime;

    public static FileUtils fileUtils = new FileUtils();
    public static Map<String, Map<String, Integer>> measValueCountMap = new HashMap<String, Map<String, Integer>>();  //measinfoId, measObjLdn, count

    public void startCMG(String[] args) throws Exception
    {
        inputDir = args[1];
        outpurDir = args[2];
        prameterTime = args[3];
        //파일들을 가져와서
        List<File> files = this.getCMGFiles();
        //통계 계산
        this.calculateStatistics(files);
        //header의 beginTime 변경
        this.sourceCmg.set60MinuteBeginTime();
        this.sourceCmg.getFileFooter().set60MinuteEndtime();
        //obj -> xml
        this.javaObjToXmlFile(files.get(0).getName());
        System.out.println(measValueCountMap);
        System.out.println(measValueCountMap);


    }

    public void javaObjToXmlFile(String firstFileName) throws IOException {
        log.info("Writing CMGFiles 60Minutes Statistics file...");
        String newFileName = this.newFileName(firstFileName);
        String outputPath = outpurDir + "\\"+ newFileName;
        String outputPaths = outputPath.replace("\\", File.separator);
        fileUtils.objectMapper().writeValue(new File(outputPaths), sourceCmg);
        log.info("CMG Output : {}", outputPaths);

    }

    public String newFileName(String oldFileName){
        String[] fileNames = oldFileName.split("\\.");
        String date = fileNames[0];
        String time =fileNames[1];
        String extension =fileNames[2];

        //시간 변경
        String[] times = time.split("-");
        String beginTime = times[0];
        beginTime = beginTime.substring(0,2) + "00"+beginTime.substring(4);
        String endTime = times[1];
        String systemName = times[2];
        String hour = endTime.substring(0,2);
        int hourInt = Integer.parseInt(hour);
        hourInt++;

        hourInt = hourInt >= 24 ? hourInt - 24 + (hourInt % 24) : hourInt;

        String newHour = Integer.toString(hourInt);
        newHour = newHour.length() < 2 ? "0"+newHour : newHour;
        newHour = newHour + "00"+endTime.substring(4);

        //extension 변경
        extension = "fin";

        String newFileName = date + "." + beginTime +"-"+ newHour +"-"+ systemName +"." +extension;

        return newFileName;
    }

    public List<File> getCMGFiles() {
        log.info("Reading CMGFiles...");

        File dir = new File(inputDir);
        File[] rawFileList = dir.listFiles();
        List<File> fileList1 = new ArrayList<>();
        int index = 0;

        for(File file:rawFileList){
            String[] fileNames = file.getName().split("\\.");
            String date = fileNames[0];
            String time = fileNames[1];
            String extesion = fileNames[2];

            String[] times = time.split("-");
            String beginTime = times[0];
            String endTime = times[1];

            if(beginTime.substring(0,2).equals(prameterTime)){
                fileList1.add(file);
            }
        }

        fileList1.sort(null);

        return fileList1;
}
public void calculateStatistics(List<File> fileList) throws SAXException, TransformerException, ParserConfigurationException, IOException, DatatypeConfigurationException {
    log.info("Calculating CMGFiles Statistics...");
    int fullFile = 0;
    int emptyFile = 0;
    for(File file: fileList){
        if(file != null){
            if(file.isFile() && file.length() != 0){
                if(!fileInitFlag){
                   // log.info("First Exist File Name : {}", file.getName());
                    this.setInitFile(file);
                    fullFile++;
                }else{
                   // log.info("Exist File Name : {}", file.getName());
                    MeasCollecFile cmgObj = this.xmlToObj(file);
                    this.countStatistics(cmgObj);
                    existFileCount++;
                    fullFile++;
                }
            }else{
                emptyFile++;
                //log.info("Exist but Empty File Name : {}", file.getName());
            }
        }
    }
    log.info("Total Exist File : {}", existFileCount);
    log.info("Calculated File : {}, Empty File : {}", fullFile, emptyFile);
}

public void setInitFile(File firstFile) throws IOException, SAXException, ParserConfigurationException, TransformerException {
    fileInitFlag = true;
    Document firstDoc = fileUtils.fileToDoc(firstFile);
    String firstOutput = fileUtils.docToString(firstDoc);
    existFileCount++;
    this.sourceCmg = fileUtils.objectMapper().readValue(firstOutput, MeasCollecFile.class);

}


    public MeasCollecFile xmlToObj(File file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document doc = fileUtils.fileToDoc(file);
        String output = fileUtils.docToString(doc);
        MeasCollecFile cmgObj = fileUtils.objectMapper().readValue(output, MeasCollecFile.class);
        return cmgObj;
    }


    public void countStatistics(MeasCollecFile targetCmg) throws DatatypeConfigurationException {
        List<MeasCollecFile.MeasData.MeasInfo> measInfosSource = this.sourceCmg.getMeasData().getMeasInfo();

        MeasCollecFile.MeasData measDataSource = this.sourceCmg.getMeasData();
        List<MeasCollecFile.MeasData.MeasInfo> measInfosTarget = targetCmg.getMeasData().getMeasInfo();

        //System.out.println("measInfosTarget.size : " + measInfosTarget.size());
        for(int i=0; i < measInfosTarget.size(); i++){
            MeasCollecFile.MeasData.MeasInfo measInfo = measDataSource.findByMeasInfoId(measInfosTarget.get(i).getMeasInfoId());

            if(measInfo == null){
                //기존 데이터에 없는 measInfo 추가
                measDataSource.getMeasInfo().add(measInfosTarget.get(i));
                //각 measInfo의 endTime, duration 세팅
                measInfosTarget.get(i).getGranPeriod().sete60Minutedurationwendtime();
                measInfo = measInfosTarget.get(i);
               //this.calculateValues(measInfo, measInfosTarget.get(i));
            }else {
                //각 measInfo의 endTime, duration 세팅
                if(existFileCount==1){
                    measInfo.getGranPeriod().sete60Minutedurationwendtime();
                }
                //count 할 것
                //System.out.println("measInfos i : " + i);
                //System.out.println("=======================================================================================");
                //System.out.println("TargetmeasInfoId " + measInfosTarget.get(i).getMeasInfoId());
                //System.out.println("sourcemeasInfoId " + measInfosSource.get(i).getMeasInfoId());
                //System.out.println("=======================================================================================");

                //System.out.println(measInfo.getMeasInfoId() + " ??? " + measInfosTarget.get(i).getMeasInfoId());
                //this.calculateValues(measInfo, measInfosTarget.get(i));
            }
            this.calculateValues(measInfo, measInfosTarget.get(i));

        }
    }

    //sourceMeasInfo랑 targetMeasInfo는 measInfoId가 같은 객체
    public void calculateValues(MeasCollecFile.MeasData.MeasInfo sourceMeasInfo, MeasCollecFile.MeasData.MeasInfo targetMeasInfo){
        List<MeasCollecFile.MeasData.MeasInfo.MeasValue> measValuesTarget = targetMeasInfo.getMeasValue();
        //System.out.println("OUTERSIZE : " + measValuesTarget.size());
        List<MeasCollecFile.MeasData.MeasInfo.MeasType> measTypesTarget = targetMeasInfo.getMeasType();

        List<MeasCollecFile.MeasData.MeasInfo.MeasValue> measValuesSource = sourceMeasInfo.getMeasValue();

        for(int i=0, valuesSize=measValuesTarget.size(); i < valuesSize; i++){
            //value 있는 지 find 하고 null이면 add 해주고 있고, avg일 경우 map에 파일 카운트 저장 해줘야할 듯
            String currentObjLdn = measValuesTarget.get(i).getMeasObjLdn();
            String currentMeasInfoId = targetMeasInfo.getMeasInfoId();
            MeasCollecFile.MeasData.MeasInfo.MeasValue measValue = sourceMeasInfo.findByMeasObjLdn(measValuesTarget.get(i).getMeasObjLdn());


                System.out.println("-------------------------------------------------------------");
                System.out.println("INFO ID : " + targetMeasInfo.getMeasInfoId());
                System.out.println("Meas Type : " + targetMeasInfo.getMeasType());
               // System.out.println("measObjLdn : " + currentObjLdn);
                System.out.println("-------------------------------------------------------------");

                if(measValueCountMap.get(currentMeasInfoId) == null){
                    measValueCountMap.put(currentMeasInfoId, new HashMap<String, Integer>());
                    measValueCountMap.get(currentMeasInfoId).put(currentObjLdn, 2);
                }else{
                    if(measValueCountMap.get(currentMeasInfoId).get(currentObjLdn) == null){
                        measValueCountMap.get(currentMeasInfoId).put(currentObjLdn, 1);
                    }else{
                        int currentMeasValueCount = measValueCountMap.get(currentMeasInfoId).get(currentObjLdn);
                        //update
                        //measValueCountMap.put(currentObjLdn, currentMeasValueCount+1);
                        measValueCountMap.get(currentMeasInfoId).put(currentObjLdn, currentMeasValueCount+1);
                    }
                }
//
//            if(measValueCountMap.get(currentMeasInfoId).get(currentObjLdn) == null){
//                measValueCountMap.get(currentMeasInfoId).put(currentMeasInfoId, 1);
//
//            }else{
//                int currentMeasValueCount = measValueCountMap.get(currentMeasInfoId).get(currentObjLdn);
//                //update
//                //measValueCountMap.put(currentObjLdn, currentMeasValueCount+1);
//                measValueCountMap.get(currentMeasInfoId).put(currentObjLdn, currentMeasValueCount+1);
//            }




            if(measValue == null){
                sourceMeasInfo.getMeasValue().add(measValuesTarget.get(i));
                measValue = measValuesTarget.get(i);
            }//else{
                //System.out.println("OUTERSIZE : " + valuesSize);
                for(int j=0, rListSize = measValuesTarget.get(i).getR().size(); j < rListSize; j++){
                    // System.out.println("SIZE : " + rListSize);
                    String measType = measTypesTarget.get(j).getValue();
                    CmgStatisticsInfoTable cmg = CmgStatisticsInfoTable.getCmgTable(measType);
                    String countType = cmg.getGroupCount();

                    if(measValuesSource.get(i) == null){
                        System.out.println("NULL!!!!");
                    }else{
                        //String currentObjLdn = measValuesTarget.get(i).getMeasObjLdn();

                        BigDecimal sourceValue = measValue.getR().get(j).getValue();
                        BigDecimal targetValue = measValuesTarget.get(i).getR().get(j).getValue();

                        //test
                        if(!currentObjLdn.equals(measValuesTarget.get(i).getMeasObjLdn())){
                            System.out.println("NOT MATCHED");
                        }


                        //System.out.println("measType : " +measType);

//                        if(countType.equals("avg")){
//                            System.out.println("-------------------------------------------------------------");
//                            System.out.println("INFO ID : " + targetMeasInfo.getMeasInfoId());
//                            System.out.println("Meas Type : " + targetMeasInfo.getMeasType());
//                            System.out.println("measObjLdn : " + currentObjLdn);
//                            System.out.println("-------------------------------------------------------------");
//                            if(measValueCountMap.get(currentObjLdn) == null){
//                                measValueCountMap.put(currentObjLdn, 1);
//                            }else{
//                                int currentMeasValueCount = measValueCountMap.get(currentObjLdn);
//                                //update
//                                measValueCountMap.put(currentObjLdn, currentMeasValueCount+1);
//                            }
//
//                        }
                        int existValueCount = measValueCountMap.get(currentMeasInfoId).get(currentObjLdn) == null ? existFileCount : measValueCountMap.get(currentMeasInfoId).get(currentObjLdn);
                        Calculator calculator = new Calculator(sourceValue);
                        Calculator result = calculator.calculate(countType, new Calculator(targetValue), existValueCount);
                        sourceMeasInfo.getMeasValue().get(i).getR().get(j).setValue(result.getNo());
                    }
                }
            //}
        }
    }
}
