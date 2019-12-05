package net.vivans.emsmw.emsmw.cmm;

import java.util.HashMap;

public class CmmMeasInfoIdTable {
    public static HashMap<Integer, String> measInfoTable = new HashMap<Integer, String>();
    static{
        measInfoTable.put(1, "memUsageMts_necc");
        measInfoTable.put(2, "diskioMts_necc");
        measInfoTable.put(3, "cpuUsageMts_necc");
        measInfoTable.put(4, "cpuUsageCoreMts");
        measInfoTable.put(5, "networkMts_necc");
        measInfoTable.put(6, "fileSysUsaveMts_necc");
        measInfoTable.put(7, "memUsageMts_dbs");
        measInfoTable.put(8, "diskioMts_dbs");
        measInfoTable.put(9, "cpuUsageMts _dbs");
        measInfoTable.put(10, "systemServiceMts_dbs");
        measInfoTable.put(11, "BaseCpuUsageMts _dbs");
        measInfoTable.put(12, "cpuUsageCoreMts_dbs");
        measInfoTable.put(13, "cpuUsageCoreSchedGapMts_dbs");
        measInfoTable.put(14, "memUsageMts_NE_dbs");
        measInfoTable.put(15, "networkMts_dbs");
        measInfoTable.put(16, "fileSysUsaveMts_dbs");
        measInfoTable.put(17, "cppsMts");
        measInfoTable.put(18, "diskioMts_cpps");
        measInfoTable.put(19, "cMiscApnniMts_cpps");
        measInfoTable.put(20, "cppsMts_TAI");
        measInfoTable.put(21, "cpuUsageMts _cpps");
        measInfoTable.put(22, "cppsMts_PLMN");
        measInfoTable.put(23, "BaseCpuUsageMts _cpps");
        measInfoTable.put(24, "cpuUsageCoreMts_cpps");
        measInfoTable.put(25, "cpuUsageCoreSchedGapMts_cpps");
        measInfoTable.put(26, "memUsageMts_cpps");
        measInfoTable.put(27, "networkMts_cpps");
        measInfoTable.put(28, "fileSysUsaveMts_cpps");
        measInfoTable.put(29, "ipdsMts");
        measInfoTable.put(30, "iISvcCmMts_ipds");
        measInfoTable.put(31, "diskioMts_ipds");
        measInfoTable.put(32, "cpuUsageMts _ipds");
        measInfoTable.put(33, "iIntItMts_ipds");
        measInfoTable.put(34, "systemServiceMts_ipds");
        measInfoTable.put(35, "iISvcCmMts_ipds_To_cpps");
        measInfoTable.put(36, "BaseCpuUsageMts _ipds");
        measInfoTable.put(37, "cpuUsageCoreMts_ipds");
        measInfoTable.put(38, "cpuUsageCoreSchedGapMts_ipds");
        measInfoTable.put(39, "memUsageMts_ipds");
        measInfoTable.put(40, "networkMts_ipds");
        measInfoTable.put(41, "fileSysUsaveMts_ipds");

    }
    private Integer code;
    private String measInfoId;

    public CmmMeasInfoIdTable(Integer code){
        this.code = code;
        this.measInfoId = measInfoTable.get(code);
    }
    public static CmmMeasInfoIdTable getCmmMeasInfoId(Integer code){ return new CmmMeasInfoIdTable(code);}
    public Integer getCode(){ return this.code;}
    public String getMeasInfoId() { return this.measInfoId; }


}
