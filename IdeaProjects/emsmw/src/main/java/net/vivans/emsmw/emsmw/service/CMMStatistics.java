package net.vivans.emsmw.emsmw.service;

import lombok.extern.slf4j.Slf4j;

import net.vivans.emsmw.emsmw.cmg.MeasCollecFile;
import net.vivans.emsmw.emsmw.cmm.CmmStatisticsInfoTable;
import net.vivans.emsmw.emsmw.util.Calculator;
import net.vivans.emsmw.emsmw.util.FileUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Slf4j
public class CMMStatistics {
    public static MeasCollecFile sourceCmm = null;
    public static int existFileCount = 0;
    public static boolean fileInitFlag = false;

    public static String inputDir;
    public static String outpurDir;
    public static String prameterTime;

    private AttachCmmMeasInfoId attachCmmMeasInfoId = new AttachCmmMeasInfoId();
    public static FileUtils fileUtils = new FileUtils();

    public void startCmm(String[] args) throws Exception {

        inputDir = args[1];
        outpurDir = args[2];
        prameterTime = args[3];
        //cmm 파일들에 measInfoId 붙여서 파일들 생성
        attachCmmMeasInfoId.getCMMFiles(args);

        File[] files = this.getCMMFiles();

        this.calculateStatistics(files);
        this.sourceCmm.set60MinuteBeginTime();
        this.javaObjToXmlFile(files[0].getName());

    }

    public void javaObjToXmlFile(String firstFileName) throws IOException {
        log.info("Writing CMMFiles 60Minutes Statistics file...");
        String newFileName = this.newFileName(firstFileName);
        String outputPath = outpurDir + "\\"+ newFileName;
        String outputPaths = outputPath.replace("\\", File.separator);
        fileUtils.objectMapper().writeValue(new File(outputPaths), sourceCmm);
        log.info("CMG Output : {}", outputPaths);

    }

    public String newFileName(String oldFileName){
        String[] fileNames = oldFileName.split("\\.");
        String date = fileNames[0];
        String time =fileNames[1];
        String extension =fileNames[2];

        String[] times = time.split("-");
        String beginTime = times[0];
        String endTime = times[1];
        String systemName = times[2];
        String hour = endTime.substring(0,2);
        int hourInt = Integer.parseInt(hour);
        hourInt++;
        String newHour = Integer.toString(hourInt);
        newHour = newHour + "00"+endTime.substring(4);
        String newFileName = date + "." + beginTime +"-"+ newHour +"-"+ systemName +"." +extension;

        return newFileName;
    }

    public File[] getCMMFiles() {
        log.info("Get CMMFiles...");

        File dir = new File(outpurDir);
        File[] rawFileList = dir.listFiles();
        //exception ?? 파일 개수가 12개 보다 많을 경우 log?
        File[] fileList = new File[12];
        int index = 0;

        for(File file:rawFileList){
            String[] fileNames = file.getName().split("\\.");
            String date = fileNames[0];
            String time =fileNames[1];
            String extension =fileNames[2];

            String[] times = time.split("-");
            String beginTime = times[0];
            String endTime = times[1];
            String systemName = times[2];

            int beginMinute = Integer.parseInt(times[0].substring(2,4));
            int endMinutes = Integer.parseInt(times[1].substring(2,4));

            int timeDiff = endMinutes - beginMinute;



            if(beginTime.substring(0,2).equals(prameterTime) && timeDiff == 5){
                fileList[index] = file;
                index++;
            }
        }
        return fileList;
    }

    public void calculateStatistics(File[] fileList) throws SAXException, TransformerException, ParserConfigurationException, IOException, DatatypeConfigurationException {
        log.info("Calculating CMMFiles Statistics...");

        for(File file: fileList){
            if(file != null){
                if(file.isFile() && file.length() != 0){
                    if(!fileInitFlag){
                        log.info("First Exist File Name : {}", file.getName());
                        this.setInitFile(file);
                    }else{
                        log.info("Exist File Name : {}", file.getName());
                        MeasCollecFile cmmObj = this.xmlToObj(file);
                        System.out.println(this.sourceCmm);
                        this.countStatistics(cmmObj);
                        existFileCount++;
                    }
                }else{
                    log.info("Exist but Empty File Name : {}", file.getName());
                }
            }
        }
    }


    public void setInitFile(File firstFile) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        //비교 파일 초기화 -> 파일 내용 있는지 없는지 체크 해서 없으면 ...???;;;;;;;;;
        fileInitFlag = true;
        Document firstDoc = fileUtils.fileToDoc(firstFile);
        String firstOutput = fileUtils.docToString(firstDoc);
        existFileCount++;
        this.sourceCmm = fileUtils.objectMapper().readValue(firstOutput, MeasCollecFile.class);

}


    public MeasCollecFile xmlToObj(File file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document doc = fileUtils.fileToDoc(file);
        String output = fileUtils.docToString(doc);
        MeasCollecFile cmmObj = fileUtils.objectMapper().readValue(output, MeasCollecFile.class);
        //System.out.println("File Name : " + file.getName());
        return cmmObj;
    }


    public void countStatistics(MeasCollecFile targetCmm) throws DatatypeConfigurationException {
        List<MeasCollecFile.MeasData.MeasInfo> measInfosSource = this.sourceCmm.getMeasData().getMeasInfo();

        MeasCollecFile.MeasData measDataSource = this.sourceCmm.getMeasData();
        List<MeasCollecFile.MeasData.MeasInfo> measInfosTarget = targetCmm.getMeasData().getMeasInfo();

        System.out.println("measInfosTarget.size : " + measInfosTarget.size());
        for(int i=0; i < measInfosTarget.size(); i++){
            MeasCollecFile.MeasData.MeasInfo measInfo = measDataSource.findByMeasInfoId(measInfosTarget.get(i).getMeasInfoId());

            if(measInfo == null){
//                System.out.println("measInfos i : " + i);
//                System.out.println("#######################################################################################");
//                System.out.println("TargetmeasInfoId " + measInfosTarget.get(i).getMeasInfoId());
//                //System.out.println("sourcemeasInfoId " + measInfosSource.get(i).getMeasInfoId());
//                System.out.println("#######################################################################################");
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
//                System.out.println("measInfos i : " + i);
//                System.out.println("=======================================================================================");
//                System.out.println("TargetmeasInfoId " + measInfosTarget.get(i).getMeasInfoId());
//                //System.out.println("sourcemeasInfoId " + measInfosSource.get(i).getMeasInfoId());
//                System.out.println("=======================================================================================");
//
//                System.out.println(measInfo.getMeasInfoId() + " ??? " + measInfosTarget.get(i).getMeasInfoId());
                this.calculateValues(measInfo, measInfosTarget.get(i));
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
            //System.out.println("OUTERSIZE : " + valuesSize);
            for(int j=0, rListSize = measValuesTarget.get(i).getR().size(); j < rListSize; j++){
               // System.out.println("SIZE : " + rListSize);
                String measType = measTypesTarget.get(j).getValue();
                CmmStatisticsInfoTable cmm = CmmStatisticsInfoTable.getCmmTable(measType);
                String countType = cmm.getGroupCount();

                BigDecimal sourceValue = measValuesSource.get(i).getR().get(j).getValue();
                BigDecimal targetValue = measValuesTarget.get(i).getR().get(j).getValue();

               // System.out.println("measType : " +measType);

                Calculator test = new Calculator(sourceValue);
                Calculator result = test.calculate(countType, new Calculator(targetValue), existFileCount);
//
//                System.out.println("fileIndex : " + this.existFileCount);
//                System.out.println("i : " + i);
//                System.out.println("j : " + j);
                sourceMeasInfo.getMeasValue().get(i).getR().get(j).setValue(result.getNo());
            }
        }
    }



}
