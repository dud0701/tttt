package net.vivans.emsmw.emsmw.cmg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fileHeader",
        "measData",
        "fileFooter"
})
@XmlRootElement(name = "measCollecFile")
public class MeasCollecFile {

    @XmlElement(required = true)
    protected MeasCollecFile.FileHeader fileHeader;
    @XmlElement(required = true)
    protected MeasCollecFile.MeasData measData;
    @XmlElement(required = true)
    protected MeasCollecFile.FileFooter fileFooter;

    @JsonIgnoreProperties
    public void set60MinuteBeginTime(){
        this.fileHeader.getMeasCollec().getBeginTime().setMinute(0);
        this.fileHeader.getMeasCollec().getBeginTime().setSecond(0);
    }

    /**
     * fileHeader �Ӽ��� ���� �����ɴϴ�.
     *
     * @return
     *     possible object is
     *     {@link MeasCollecFile.FileHeader }
     *
     */
    public MeasCollecFile.FileHeader getFileHeader() {
        return fileHeader;
    }

    /**
     * fileHeader �Ӽ��� ���� �����մϴ�.
     *
     * @param value
     *     allowed object is
     *     {@link MeasCollecFile.FileHeader }
     *
     */
    public void setFileHeader(MeasCollecFile.FileHeader value) {
        this.fileHeader = value;
    }

    /**
     * measData �Ӽ��� ���� �����ɴϴ�.
     *
     * @return
     *     possible object is
     *     {@link MeasCollecFile.MeasData }
     *
     */
    public MeasCollecFile.MeasData getMeasData() {

//        if(measData == null){
//            return new MeasData();
//        }
        return measData;
    }

    /**
     * measData �Ӽ��� ���� �����մϴ�.
     *
     * @param value
     *     allowed object is
     *     {@link MeasCollecFile.MeasData }
     *
     */
    public void setMeasData(MeasCollecFile.MeasData value) {
        this.measData = value;
    }

    /**
     * fileFooter �Ӽ��� ���� �����ɴϴ�.
     *
     * @return
     *     possible object is
     *     {@link MeasCollecFile.FileFooter }
     *
     */
    public MeasCollecFile.FileFooter getFileFooter() {
        return fileFooter;
    }

    /**
     * fileFooter �Ӽ��� ���� �����մϴ�.
     *
     * @param value
     *     allowed object is
     *     {@link MeasCollecFile.FileFooter }
     *
     */
    public void setFileFooter(MeasCollecFile.FileFooter value) {
        this.fileFooter = value;
    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     *
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="measCollec">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "measCollec"
    })
    public static class FileFooter {

        @XmlElement(required = true)
        protected MeasCollecFile.FileFooter.MeasCollec measCollec;

        @JsonIgnoreProperties
        public void set60MinuteEndtime() throws DatatypeConfigurationException {
            GregorianCalendar calendar = this.measCollec.getEndTime().toGregorianCalendar();
            calendar.add(Calendar.HOUR, 1);
            this.measCollec.setEndTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
            this.measCollec.getEndTime().setMinute(0);
            this.measCollec.getEndTime().setSecond(0);

        }

        /**
         * measCollec �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link MeasCollecFile.FileFooter.MeasCollec }
         *
         */
        public MeasCollecFile.FileFooter.MeasCollec getMeasCollec() {
            return measCollec;
        }

        /**
         * measCollec �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link MeasCollecFile.FileFooter.MeasCollec }
         *
         */
        public void setMeasCollec(MeasCollecFile.FileFooter.MeasCollec value) {
            this.measCollec = value;
        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         *
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class MeasCollec {

            @XmlAttribute(name = "endTime", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar endTime;

            /**
             * endTime �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getEndTime() {
                return endTime;
            }

            /**
             * endTime �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setEndTime(XMLGregorianCalendar value) {
                this.endTime = value;
            }

        }

    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     *
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fileSender">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="localDn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="elementType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="measCollec">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="beginTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="fileFormatVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="vendorName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "fileSender",
            "measCollec"
    })
    public static class FileHeader {

        @XmlElement(required = true)
        protected MeasCollecFile.FileHeader.FileSender fileSender;
        @XmlElement(required = true)
        protected MeasCollecFile.FileHeader.MeasCollec measCollec;
        @XmlAttribute(name = "fileFormatVersion", required = true)
        protected String fileFormatVersion;
        @XmlAttribute(name = "vendorName", required = true)
        protected String vendorName;

        /**
         * fileSender �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link MeasCollecFile.FileHeader.FileSender }
         *
         */
        public MeasCollecFile.FileHeader.FileSender getFileSender() {
            return fileSender;
        }

        /**
         * fileSender �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link MeasCollecFile.FileHeader.FileSender }
         *
         */
        public void setFileSender(MeasCollecFile.FileHeader.FileSender value) {
            this.fileSender = value;
        }

        /**
         * measCollec �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link MeasCollecFile.FileHeader.MeasCollec }
         *
         */
        public MeasCollecFile.FileHeader.MeasCollec getMeasCollec() {
            return measCollec;
        }

        /**
         * measCollec �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link MeasCollecFile.FileHeader.MeasCollec }
         *
         */
        public void setMeasCollec(MeasCollecFile.FileHeader.MeasCollec value) {
            this.measCollec = value;
        }

        /**
         * fileFormatVersion �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getFileFormatVersion() {
            return fileFormatVersion;
        }

        /**
         * fileFormatVersion �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setFileFormatVersion(String value) {
            this.fileFormatVersion = value;
        }

        /**
         * vendorName �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVendorName() {
            return vendorName;
        }

        /**
         * vendorName �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVendorName(String value) {
            this.vendorName = value;
        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         *
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="localDn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="elementType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class FileSender {

            @XmlAttribute(name = "localDn", required = true)
            protected String localDn;
            @XmlAttribute(name = "elementType", required = true)
            protected String elementType;

            /**
             * localDn �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLocalDn() {
                return localDn;
            }

            /**
             * localDn �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLocalDn(String value) {
                this.localDn = value;
            }

            /**
             * elementType �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getElementType() {
                return elementType;
            }

            /**
             * elementType �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setElementType(String value) {
                this.elementType = value;
            }

        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         *
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="beginTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class MeasCollec {

            @XmlAttribute(name = "beginTime", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar beginTime;

            /**
             * beginTime �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getBeginTime() {
                return beginTime;
            }

            /**
             * beginTime �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setBeginTime(XMLGregorianCalendar value) {
                this.beginTime = value;
            }

        }

    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     *
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="managedElement">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="localDn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="swVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="measInfo" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="granPeriod">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="duration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                           &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="measType" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="measValue" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="r" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;simpleContent>
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
     *                                     &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
     *                                   &lt;/extension>
     *                                 &lt;/simpleContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="suspect" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="measObjLdn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="measInfoId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "managedElement",
            "measInfo"
    })
    public static class MeasData {

        @XmlElement(required = true)
        protected MeasCollecFile.MeasData.ManagedElement managedElement;
        @XmlElement(required = true)
        protected List<MeasCollecFile.MeasData.MeasInfo> measInfo;

        @JsonIgnoreProperties
        public MeasInfo findByMeasInfoId(String measInfoId){
            MeasInfo result = null;
            for(MeasInfo measInfo:this.measInfo){
                if(measInfo.getMeasInfoId().equals(measInfoId)){
                    result = measInfo;
                    break;
                }
            }
            return result;
        }

        /**
         * managedElement �Ӽ��� ���� �����ɴϴ�.
         *
         * @return
         *     possible object is
         *     {@link MeasCollecFile.MeasData.ManagedElement }
         *
         */
        public MeasCollecFile.MeasData.ManagedElement getManagedElement() {
            return managedElement;
        }

        /**
         * managedElement �Ӽ��� ���� �����մϴ�.
         *
         * @param value
         *     allowed object is
         *     {@link MeasCollecFile.MeasData.ManagedElement }
         *
         */
        public void setManagedElement(MeasCollecFile.MeasData.ManagedElement value) {
            this.managedElement = value;
        }

        /**
         * Gets the value of the measInfo property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the measInfo property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMeasInfo().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MeasCollecFile.MeasData.MeasInfo }
         *
         *
         */
        public List<MeasCollecFile.MeasData.MeasInfo> getMeasInfo() {
            if (measInfo == null) {
                measInfo = new ArrayList<MeasCollecFile.MeasData.MeasInfo>();
            }
            return this.measInfo;
        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         *
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="localDn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="swVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ManagedElement {

            @XmlAttribute(name = "localDn", required = true)
            protected String localDn;
            @XmlAttribute(name = "swVersion", required = true)
            protected String swVersion;

            /**
             * localDn �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLocalDn() {
                return localDn;
            }

            /**
             * localDn �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLocalDn(String value) {
                this.localDn = value;
            }

            /**
             * swVersion �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getSwVersion() {
                return swVersion;
            }

            /**
             * swVersion �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setSwVersion(String value) {
                this.swVersion = value;
            }

        }


        /**
         * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
         *
         * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="granPeriod">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="duration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                 &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="measType" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="measValue" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="r" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
         *                           &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="suspect" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="measObjLdn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="measInfoId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "granPeriod",
                "measType",
                "measValue"
        })
        public static class MeasInfo {

            @XmlElement(required = true)
            protected MeasCollecFile.MeasData.MeasInfo.GranPeriod granPeriod;
            @XmlElement(required = true)
            protected List<MeasCollecFile.MeasData.MeasInfo.MeasType> measType;
            @XmlElement(required = true)
            protected List<MeasCollecFile.MeasData.MeasInfo.MeasValue> measValue;
            @XmlAttribute(name = "measInfoId", required = true)
            protected String measInfoId;

            @JsonIgnoreProperties
            public MeasValue findByMeasObjLdn(String measObjLdn){
                MeasValue result = null;
                for(MeasValue measValue:this.measValue){
                    if(measValue.getMeasObjLdn().equals(measObjLdn)){
                        result = measValue;
                        break;
                    }
                }
                return result;
            }


            /**
             * granPeriod �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link MeasCollecFile.MeasData.MeasInfo.GranPeriod }
             *
             */
            public MeasCollecFile.MeasData.MeasInfo.GranPeriod getGranPeriod() {
                return granPeriod;
            }

            /**
             * granPeriod �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link MeasCollecFile.MeasData.MeasInfo.GranPeriod }
             *
             */
            public void setGranPeriod(MeasCollecFile.MeasData.MeasInfo.GranPeriod value) {
                this.granPeriod = value;
            }

            /**
             * Gets the value of the measType property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the measType property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMeasType().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link MeasCollecFile.MeasData.MeasInfo.MeasType }
             *
             *
             */
            public List<MeasCollecFile.MeasData.MeasInfo.MeasType> getMeasType() {
                if (measType == null) {
                    measType = new ArrayList<MeasCollecFile.MeasData.MeasInfo.MeasType>();
                }
                return this.measType;
            }

            /**
             * Gets the value of the measValue property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the measValue property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMeasValue().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link MeasCollecFile.MeasData.MeasInfo.MeasValue }
             *
             *
             */
            public List<MeasCollecFile.MeasData.MeasInfo.MeasValue> getMeasValue() {
                if (measValue == null) {
                    measValue = new ArrayList<MeasCollecFile.MeasData.MeasInfo.MeasValue>();
                }
                return this.measValue;
            }

            /**
             * measInfoId �Ӽ��� ���� �����ɴϴ�.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getMeasInfoId() {
                return measInfoId;
            }

            /**
             * measInfoId �Ӽ��� ���� �����մϴ�.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setMeasInfoId(String value) {
                this.measInfoId = value;
            }



            /**
             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
             *
             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="duration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *       &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class GranPeriod {

                @XmlAttribute(name = "duration", required = true)
                protected Duration duration;
                @XmlAttribute(name = "endTime", required = true)
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar endTime;

                @JsonIgnoreProperties
                public void sete60Minutedurationwendtime() throws DatatypeConfigurationException {
                    Duration hourDur = DatatypeFactory.newInstance().newDuration("PT3600S");
                    this.setDuration(hourDur);
                    //this.getEndTime().setHour(this.getEndTime().getHour()+1);
                    GregorianCalendar calendar = this.endTime.toGregorianCalendar();
                    calendar.add(Calendar.HOUR, 1);
                    this.endTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
                    this.getEndTime().setMinute(0);
                    this.getEndTime().setSecond(0);

                    this.getEndTime().setTimezone(9/60000);
                }

                /**
                 * duration �Ӽ��� ���� �����ɴϴ�.
                 *
                 * @return
                 *     possible object is
                 *     {@link Duration }
                 *
                 */
                public Duration getDuration() {
                    return duration;
                }

                /**
                 * duration �Ӽ��� ���� �����մϴ�.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link Duration }
                 *
                 */
                public void setDuration(Duration value) {
                    this.duration = value;
                }

                /**
                 * endTime �Ӽ��� ���� �����ɴϴ�.
                 *
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *
                 */
                public XMLGregorianCalendar getEndTime() {
                    return endTime;
                }

                /**
                 * endTime �Ӽ��� ���� �����մϴ�.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *
                 */
                public void setEndTime(XMLGregorianCalendar value) {
                    this.endTime = value;
                }

            }


            /**
             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
             *
             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "value"
            })
            public static class MeasType {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "p", required = true)
                @XmlSchemaType(name = "unsignedByte")
                protected short p;

                /**
                 * value �Ӽ��� ���� �����ɴϴ�.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * value �Ӽ��� ���� �����մϴ�.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * p �Ӽ��� ���� �����ɴϴ�.
                 *
                 */
                public short getP() {
                    return p;
                }

                /**
                 * p �Ӽ��� ���� �����մϴ�.
                 *
                 */
                public void setP(short value) {
                    this.p = value;
                }

            }


            /**
             * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
             *
             * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="r" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
             *                 &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="suspect" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
             *       &lt;/sequence>
             *       &lt;attribute name="measObjLdn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "r",
                    "suspect"
            })
            public static class MeasValue {

                @XmlElement(required = true)
                protected List<MeasCollecFile.MeasData.MeasInfo.MeasValue.R> r;
                protected boolean suspect;
                @XmlAttribute(name = "measObjLdn", required = true)
                protected String measObjLdn;



                /**
                 * Gets the value of the r property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the r property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getR().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link MeasCollecFile.MeasData.MeasInfo.MeasValue.R }
                 *
                 *
                 */
                public List<MeasCollecFile.MeasData.MeasInfo.MeasValue.R> getR() {
                    if (r == null) {
                        r = new ArrayList<MeasCollecFile.MeasData.MeasInfo.MeasValue.R>();
                    }
                    return this.r;
                }

                /**
                 * suspect �Ӽ��� ���� �����ɴϴ�.
                 *
                 */
                public boolean isSuspect() {
                    return suspect;
                }

                /**
                 * suspect �Ӽ��� ���� �����մϴ�.
                 *
                 */
                public void setSuspect(boolean value) {
                    this.suspect = value;
                }

                /**
                 * measObjLdn �Ӽ��� ���� �����ɴϴ�.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getMeasObjLdn() {
                    return measObjLdn;
                }

                /**
                 * measObjLdn �Ӽ��� ���� �����մϴ�.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setMeasObjLdn(String value) {
                    this.measObjLdn = value;
                }


                /**
                 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
                 *
                 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
                 *
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
                 *       &lt;attribute name="p" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 *
                 *
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "value"
                })
                public static class R {

                    @XmlValue
                    protected BigDecimal value;
                    @XmlAttribute(name = "p", required = true)
                    @XmlSchemaType(name = "unsignedByte")
                    protected short p;

                    /**
                     * value �Ӽ��� ���� �����ɴϴ�.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getValue() {
                        if(value == null){
                            return new BigDecimal(0);
                        }
                        return value;
                    }

                    /**
                     * value �Ӽ��� ���� �����մϴ�.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setValue(BigDecimal value) {
                        this.value = value;
                    }

                    /**
                     * p �Ӽ��� ���� �����ɴϴ�.
                     *
                     */
                    public short getP() {
                        return p;
                    }

                    /**
                     * p �Ӽ��� ���� �����մϴ�.
                     *
                     */
                    public void setP(short value) {
                        this.p = value;
                    }

                }

            }

        }

    }

}
