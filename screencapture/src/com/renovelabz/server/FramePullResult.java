
package com.renovelabz.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FramePullResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FramePullResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bufferOverRun" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nextFrameId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segmentIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FramePullResult", propOrder = {
    "bufferOverRun",
    "data",
    "nextFrameId",
    "segmentIndex"
})
public class FramePullResult {

    protected boolean bufferOverRun;
    protected List<byte[]> data;
    protected int nextFrameId;
    protected int segmentIndex;

    /**
     * Gets the value of the bufferOverRun property.
     * 
     */
    public boolean isBufferOverRun() {
        return bufferOverRun;
    }

    /**
     * Sets the value of the bufferOverRun property.
     * 
     */
    public void setBufferOverRun(boolean value) {
        this.bufferOverRun = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    public List<byte[]> getData() {
        if (data == null) {
            data = new ArrayList<byte[]>();
        }
        return this.data;
    }

    /**
     * Gets the value of the nextFrameId property.
     * 
     */
    public int getNextFrameId() {
        return nextFrameId;
    }

    /**
     * Sets the value of the nextFrameId property.
     * 
     */
    public void setNextFrameId(int value) {
        this.nextFrameId = value;
    }

    /**
     * Gets the value of the segmentIndex property.
     * 
     */
    public int getSegmentIndex() {
        return segmentIndex;
    }

    /**
     * Sets the value of the segmentIndex property.
     * 
     */
    public void setSegmentIndex(int value) {
        this.segmentIndex = value;
    }

}
