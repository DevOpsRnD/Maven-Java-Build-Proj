package com.cnk.siapi.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseHeader",
    "responseBody"
})
@XmlRootElement(name = "SightSeeingInterfaceRS")
public class CommonSearchRS {

    @XmlElement(name = "ResponseHeader", required = true)
    protected CommonSearchRS.ResponseHeader responseHeader;
    @XmlElement(name = "ResponseBody", required = true)
    protected CommonSearchRS.ResponseBody responseBody;

    
    public CommonSearchRS.ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    
    public void setResponseHeader(CommonSearchRS.ResponseHeader value) {
        this.responseHeader = value;
    }

    
    public CommonSearchRS.ResponseBody getResponseBody() {
        return responseBody;
    }

    
    public void setResponseBody(CommonSearchRS.ResponseBody value) {
        this.responseBody = value;
    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "otaTourActivityAvailRSWrapper"
    })
    public static class ResponseBody {

        @XmlElement(name = "OTA_TourActivityAvailRSWrapper", required = true)
        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper otaTourActivityAvailRSWrapper;

        
        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper getOTATourActivityAvailRSWrapper() {
            return otaTourActivityAvailRSWrapper;
        }

        
        public void setOTATourActivityAvailRSWrapper(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper value) {
            this.otaTourActivityAvailRSWrapper = value;
        }


        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "supplierID",
            "sequence",
            "otaTourActivityAvailRS"
        })
        public static class OTATourActivityAvailRSWrapper {

            @XmlElement(name = "SupplierID", required = true)
            protected String supplierID;
            @XmlElement(name = "Sequence")
            protected int sequence;
            @XmlElement(name = "OTA_TourActivityAvailRS", required = true)
            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS otaTourActivityAvailRS;

            
            public String getSupplierID() {
                return supplierID;
            }

            
            public void setSupplierID(String value) {
                this.supplierID = value;
            }

            
            public int getSequence() {
                return sequence;
            }

            
            public void setSequence(int value) {
                this.sequence = value;
            }

            
            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS getOTATourActivityAvailRS() {
                return otaTourActivityAvailRS;
            }

            
            public void setOTATourActivityAvailRS(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS value) {
                this.otaTourActivityAvailRS = value;
            }


            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "success",
                "tourActivityInfo",
                "errors"
            })
            public static class OTATourActivityAvailRS {

                @XmlElement(name = "Success", required = true)
                protected String success;
                @XmlElement(name = "TourActivityInfo")
                protected List<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo> tourActivityInfo;
                @XmlElement(name = "Errors", required = true)
                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors errors;

                
                public String getSuccess() {
                    return success;
                }

                
                public void setSuccess(String value) {
                    this.success = value;
                }

                
                public List<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo> getTourActivityInfo() {
                    if (tourActivityInfo == null) {
                        tourActivityInfo = new ArrayList<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo>();
                    }
                    return this.tourActivityInfo;
                }

                
                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors getErrors() {
                    return errors;
                }

                
                public void setErrors(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors value) {
                    this.errors = value;
                }


                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "error"
                })
                public static class Errors {

                    @XmlElement(name = "Error", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors.Error error;

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors.Error getError() {
                        return error;
                    }

                    
                    public void setError(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.Errors.Error value) {
                        this.error = value;
                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "value"
                    })
                    public static class Error {

                        @XmlValue
                        protected String value;
                        @XmlAttribute(name = "Language")
                        protected String language;
                        @XmlAttribute(name = "Type")
                        protected String type;
                        @XmlAttribute(name = "ShortText")
                        protected String shortText;
                        @XmlAttribute(name = "Code")
                        protected String code;
                        @XmlAttribute(name = "RecordID")
                        protected String recordID;

                        
                        public String getValue() {
                            return value;
                        }

                        
                        public void setValue(String value) {
                            this.value = value;
                        }

                        
                        public String getLanguage() {
                            return language;
                        }

                        
                        public void setLanguage(String value) {
                            this.language = value;
                        }

                        
                        public String getType() {
                            return type;
                        }

                        
                        public void setType(String value) {
                            this.type = value;
                        }

                        
                        public String getShortText() {
                            return shortText;
                        }

                        
                        public void setShortText(String value) {
                            this.shortText = value;
                        }

                        
                        public String getCode() {
                            return code;
                        }

                        
                        public void setCode(String value) {
                            this.code = value;
                        }

                        
                        public String getRecordID() {
                            return recordID;
                        }

                        
                        public void setRecordID(String value) {
                            this.recordID = value;
                        }

                    }

                }


                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "basicInfo",
                    "schedule",
                    "description",
                    "extras",
                    "location",
                    "pricing"
                })
                public static class TourActivityInfo {

                    @XmlElement(name = "BasicInfo", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo basicInfo;
                    @XmlElement(name = "Schedule", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule schedule;
                    @XmlElement(name = "Description", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Description description;
                    @XmlElement(name = "Extras", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Extras extras;
                    @XmlElement(name = "Location", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location location;
                    @XmlElement(name = "Pricing", required = true)
                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing pricing;

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo getBasicInfo() {
                        return basicInfo;
                    }

                    
                    public void setBasicInfo(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo value) {
                        this.basicInfo = value;
                    }

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule getSchedule() {
                        return schedule;
                    }

                    
                    public void setSchedule(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule value) {
                        this.schedule = value;
                    }

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Description getDescription() {
                        return description;
                    }

                    
                    public void setDescription(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Description value) {
                        this.description = value;
                    }

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Extras getExtras() {
                        return extras;
                    }

                    
                    public void setExtras(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Extras value) {
                        this.extras = value;
                    }

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location getLocation() {
                        return location;
                    }

                    
                    public void setLocation(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location value) {
                        this.location = value;
                    }

                    
                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing getPricing() {
                        return pricing;
                    }

                    
                    public void setPricing(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing value) {
                        this.pricing = value;
                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "tpaExtensions"
                    })
                    public static class BasicInfo {

                        @XmlElement(name = "TPA_Extensions", required = true)
                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions tpaExtensions;
                        @XmlAttribute(name = "SupplierProductCode")
                        protected String supplierProductCode;
                        @XmlAttribute(name = "Name")
                        protected String name;
                        @XmlAttribute(name = "ShortName")
                        protected String shortName;
                        @XmlAttribute(name = "SupplierBrandCode")
                        protected String supplierBrandCode;

                        
                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions getTPAExtensions() {
                            return tpaExtensions;
                        }

                        
                        public void setTPAExtensions(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions value) {
                            this.tpaExtensions = value;
                        }

                        
                        public String getSupplierProductCode() {
                            return supplierProductCode;
                        }

                        
                        public void setSupplierProductCode(String value) {
                            this.supplierProductCode = value;
                        }

                        
                        public String getName() {
                            return name;
                        }

                        
                        public void setName(String value) {
                            this.name = value;
                        }

                        
                        public String getShortName() {
                            return shortName;
                        }

                        
                        public void setShortName(String value) {
                            this.shortName = value;
                        }

                        
                        public String getSupplierBrandCode() {
                            return supplierBrandCode;
                        }

                        
                        public void setSupplierBrandCode(String value) {
                            this.supplierBrandCode = value;
                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "activityTPA"
                        })
                        public static class TPAExtensions {

                            @XmlElement(name = "Activity_TPA", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA activityTPA;

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA getActivityTPA() {
                                return activityTPA;
                            }

                            
                            public void setActivityTPA(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA value) {
                                this.activityTPA = value;
                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "availabilityStatus",
                                "shippingDetails",
                                "participant",
                                "supplierDetails",
                                "tourLanguage",
                                "timeSlots",
                                "vouchers",
                                "questions"
                            })
                            public static class ActivityTPA {

                                @XmlElement(name = "Availability_Status")
                                protected List<String> availabilityStatus;
                                @XmlElement(name = "Shipping_Details", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails shippingDetails;
                                @XmlElement(name = "Participant", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant participant;
                                @XmlElement(name = "Supplier_Details", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.SupplierDetails supplierDetails;
                                @XmlElement(name = "TourLanguage", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TourLanguage tourLanguage;
                                @XmlElement(name = "TimeSlots", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TimeSlots timeSlots;
                                @XmlElement(name = "Vouchers", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Vouchers vouchers;
                                @XmlElement(name = "Questions", required = true)
                                protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Questions questions;

                                
                                public List<String> getAvailabilityStatus() {
                                    if (availabilityStatus == null) {
                                        availabilityStatus = new ArrayList<String>();
                                    }
                                    return this.availabilityStatus;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails getShippingDetails() {
                                    return shippingDetails;
                                }

                                
                                public void setShippingDetails(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails value) {
                                    this.shippingDetails = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant getParticipant() {
                                    return participant;
                                }

                                
                                public void setParticipant(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant value) {
                                    this.participant = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.SupplierDetails getSupplierDetails() {
                                    return supplierDetails;
                                }

                                
                                public void setSupplierDetails(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.SupplierDetails value) {
                                    this.supplierDetails = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TourLanguage getTourLanguage() {
                                    return tourLanguage;
                                }

                                
                                public void setTourLanguage(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TourLanguage value) {
                                    this.tourLanguage = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TimeSlots getTimeSlots() {
                                    return timeSlots;
                                }

                                
                                public void setTimeSlots(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.TimeSlots value) {
                                    this.timeSlots = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Vouchers getVouchers() {
                                    return vouchers;
                                }

                                
                                public void setVouchers(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Vouchers value) {
                                    this.vouchers = value;
                                }

                                
                                public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Questions getQuestions() {
                                    return questions;
                                }

                                
                                public void setQuestions(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Questions value) {
                                    this.questions = value;
                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "qualifierInfo"
                                })
                                public static class Participant {

                                    @XmlElement(name = "QualifierInfo", required = true)
                                    protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant.QualifierInfo qualifierInfo;
                                    @XmlAttribute(name = "available")
                                    protected String available;
                                    @XmlAttribute(name = "minAdultAge")
                                    protected String minAdultAge;
                                    @XmlAttribute(name = "maxIndividuals")
                                    protected String maxIndividuals;
                                    @XmlAttribute(name = "maxChildren")
                                    protected String maxChildren;
                                    @XmlAttribute(name = "maxAdults")
                                    protected String maxAdults;
                                    @XmlAttribute(name = "maxChildAge")
                                    protected String maxChildAge;
                                    @XmlAttribute(name = "minChildAge")
                                    protected String minChildAge;
                                    @XmlAttribute(name = "maxAdultAge")
                                    protected String maxAdultAge;

                                    
                                    public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant.QualifierInfo getQualifierInfo() {
                                        return qualifierInfo;
                                    }

                                    
                                    public void setQualifierInfo(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.Participant.QualifierInfo value) {
                                        this.qualifierInfo = value;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }

                                    
                                    public String getMinAdultAge() {
                                        return minAdultAge;
                                    }

                                    
                                    public void setMinAdultAge(String value) {
                                        this.minAdultAge = value;
                                    }

                                    
                                    public String getMaxIndividuals() {
                                        return maxIndividuals;
                                    }

                                    
                                    public void setMaxIndividuals(String value) {
                                        this.maxIndividuals = value;
                                    }

                                    
                                    public String getMaxChildren() {
                                        return maxChildren;
                                    }

                                    
                                    public void setMaxChildren(String value) {
                                        this.maxChildren = value;
                                    }

                                    
                                    public String getMaxAdults() {
                                        return maxAdults;
                                    }

                                    
                                    public void setMaxAdults(String value) {
                                        this.maxAdults = value;
                                    }

                                    
                                    public String getMaxChildAge() {
                                        return maxChildAge;
                                    }

                                    
                                    public void setMaxChildAge(String value) {
                                        this.maxChildAge = value;
                                    }

                                    
                                    public String getMinChildAge() {
                                        return minChildAge;
                                    }

                                    
                                    public void setMinChildAge(String value) {
                                        this.minChildAge = value;
                                    }

                                    
                                    public String getMaxAdultAge() {
                                        return maxAdultAge;
                                    }

                                    
                                    public void setMaxAdultAge(String value) {
                                        this.maxAdultAge = value;
                                    }


                                    
                                    @XmlAccessorType(XmlAccessType.FIELD)
                                    @XmlType(name = "", propOrder = {
                                        "value"
                                    })
                                    public static class QualifierInfo {

                                        @XmlValue
                                        protected String value;
                                        @XmlAttribute(name = "Extension")
                                        protected String extension;

                                        
                                        public String getValue() {
                                            return value;
                                        }

                                        
                                        public void setValue(String value) {
                                            this.value = value;
                                        }

                                        
                                        public String getExtension() {
                                            return extension;
                                        }

                                        
                                        public void setExtension(String value) {
                                            this.extension = value;
                                        }

                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class Questions {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "RequiredInBook")
                                    protected String requiredInBook;

                                    
                                    public String getValue() {
                                        return value;
                                    }

                                    
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    
                                    public String getRequiredInBook() {
                                        return requiredInBook;
                                    }

                                    
                                    public void setRequiredInBook(String value) {
                                        this.requiredInBook = value;
                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "content"
                                })
                                public static class ShippingDetails {

                                    @XmlElementRefs({
                                        @XmlElementRef(name = "Details", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "ShippingAreas", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "ServiceFee", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "Currency", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "TotalCost", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "ID", type = JAXBElement.class, required = false),
                                        @XmlElementRef(name = "OptionName", type = JAXBElement.class, required = false)
                                    })
                                    @XmlMixed
                                    protected List<Serializable> content;
                                    @XmlAttribute(name = "available")
                                    protected String available;

                                    
                                    public List<Serializable> getContent() {
                                        if (content == null) {
                                            content = new ArrayList<Serializable>();
                                        }
                                        return this.content;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }


                                    
                                    @XmlAccessorType(XmlAccessType.FIELD)
                                    @XmlType(name = "", propOrder = {
                                        "rateList"
                                    })
                                    public static class ShippingAreas {

                                        @XmlElement(name = "RateList", required = true)
                                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails.ShippingAreas.RateList rateList;

                                        
                                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails.ShippingAreas.RateList getRateList() {
                                            return rateList;
                                        }

                                        
                                        public void setRateList(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.TPAExtensions.ActivityTPA.ShippingDetails.ShippingAreas.RateList value) {
                                            this.rateList = value;
                                        }


                                        
                                        @XmlAccessorType(XmlAccessType.FIELD)
                                        @XmlType(name = "", propOrder = {
                                            "areaID",
                                            "name",
                                            "cost"
                                        })
                                        public static class RateList {

                                            @XmlElement(name = "AreaID")
                                            protected short areaID;
                                            @XmlElement(name = "Name", required = true)
                                            protected String name;
                                            @XmlElement(name = "Cost")
                                            protected int cost;

                                            
                                            public short getAreaID() {
                                                return areaID;
                                            }

                                            
                                            public void setAreaID(short value) {
                                                this.areaID = value;
                                            }

                                            
                                            public String getName() {
                                                return name;
                                            }

                                            
                                            public void setName(String value) {
                                                this.name = value;
                                            }

                                            
                                            public int getCost() {
                                                return cost;
                                            }

                                            
                                            public void setCost(int value) {
                                                this.cost = value;
                                            }

                                        }

                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class SupplierDetails {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "available")
                                    protected String available;

                                    
                                    public String getValue() {
                                        return value;
                                    }

                                    
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class TimeSlots {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "available")
                                    protected String available;

                                    
                                    public String getValue() {
                                        return value;
                                    }

                                    
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class TourLanguage {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "available")
                                    protected String available;
                                    @XmlAttribute(name = "Code")
                                    protected String code;

                                    
                                    public String getValue() {
                                        return value;
                                    }

                                    
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }

                                    
                                    public String getCode() {
                                        return code;
                                    }

                                    
                                    public void setCode(String value) {
                                        this.code = value;
                                    }

                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "content"
                                })
                                public static class Vouchers {

                                    @XmlElementRef(name = "Info", type = JAXBElement.class, required = false)
                                    @XmlMixed
                                    protected List<Serializable> content;
                                    @XmlAttribute(name = "available")
                                    protected String available;

                                    
                                    public List<Serializable> getContent() {
                                        if (content == null) {
                                            content = new ArrayList<Serializable>();
                                        }
                                        return this.content;
                                    }

                                    
                                    public String getAvailable() {
                                        return available;
                                    }

                                    
                                    public void setAvailable(String value) {
                                        this.available = value;
                                    }

                                }

                            }

                        }

                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "shortDescription",
                        "longDescription"
                    })
                    public static class Description {

                        @XmlElement(name = "ShortDescription", required = true)
                        protected String shortDescription;
                        @XmlElement(name = "LongDescription", required = true)
                        protected String longDescription;
                        @XmlAttribute(name = "available")
                        protected String available;

                        
                        public String getShortDescription() {
                            return shortDescription;
                        }

                        
                        public void setShortDescription(String value) {
                            this.shortDescription = value;
                        }

                        
                        public String getLongDescription() {
                            return longDescription;
                        }

                        
                        public void setLongDescription(String value) {
                            this.longDescription = value;
                        }

                        
                        public String getAvailable() {
                            return available;
                        }

                        
                        public void setAvailable(String value) {
                            this.available = value;
                        }

                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "content"
                    })
                    public static class Extras {

                        @XmlElementRef(name = "Extra", type = JAXBElement.class, required = false)
                        @XmlMixed
                        protected List<Serializable> content;
                        @XmlAttribute(name = "available")
                        protected String available;

                        
                        public List<Serializable> getContent() {
                            if (content == null) {
                                content = new ArrayList<Serializable>();
                            }
                            return this.content;
                        }

                        
                        public String getAvailable() {
                            return available;
                        }

                        
                        public void setAvailable(String value) {
                            this.available = value;
                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class Extra {

                            @XmlValue
                            protected String value;
                            @XmlAttribute(name = "Name")
                            protected String name;
                            @XmlAttribute(name = "Description")
                            protected String description;

                            
                            public String getValue() {
                                return value;
                            }

                            
                            public void setValue(String value) {
                                this.value = value;
                            }

                            
                            public String getName() {
                                return name;
                            }

                            
                            public void setName(String value) {
                                this.name = value;
                            }

                            
                            public String getDescription() {
                                return description;
                            }

                            
                            public void setDescription(String value) {
                                this.description = value;
                            }

                        }

                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "address",
                        "region"
                    })
                    public static class Location {

                        @XmlElement(name = "Address", required = true)
                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address address;
                        @XmlElement(name = "Region", required = true)
                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Region region;

                        
                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address getAddress() {
                            return address;
                        }

                        
                        public void setAddress(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address value) {
                            this.address = value;
                        }

                        
                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Region getRegion() {
                            return region;
                        }

                        
                        public void setRegion(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Region value) {
                            this.region = value;
                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "countryName"
                        })
                        public static class Address {

                            @XmlElement(name = "CountryName", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address.CountryName countryName;

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address.CountryName getCountryName() {
                                return countryName;
                            }

                            
                            public void setCountryName(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Location.Address.CountryName value) {
                                this.countryName = value;
                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "value"
                            })
                            public static class CountryName {

                                @XmlValue
                                protected String value;
                                @XmlAttribute(name = "Code")
                                protected String code;

                                
                                public String getValue() {
                                    return value;
                                }

                                
                                public void setValue(String value) {
                                    this.value = value;
                                }

                                
                                public String getCode() {
                                    return code;
                                }

                                
                                public void setCode(String value) {
                                    this.code = value;
                                }

                            }

                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class Region {

                            @XmlValue
                            protected String value;
                            @XmlAttribute(name = "RegionCode")
                            protected String regionCode;

                            
                            public String getValue() {
                                return value;
                            }

                            
                            public void setValue(String value) {
                                this.value = value;
                            }

                            
                            public String getRegionCode() {
                                return regionCode;
                            }

                            
                            public void setRegionCode(String value) {
                                this.regionCode = value;
                            }

                        }

                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "summary",
                        "participantCategory"
                    })
                    public static class Pricing {

                        @XmlElement(name = "Summary", required = true)
                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary summary;
                        @XmlElement(name = "ParticipantCategory")
                        protected List<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory> participantCategory;
                        @XmlAttribute(name = "available")
                        protected String available;
                        @XmlAttribute(name = "PerPaxPriceInd")
                        protected String perPaxPriceInd;

                        
                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary getSummary() {
                            return summary;
                        }

                        
                        public void setSummary(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary value) {
                            this.summary = value;
                        }

                        
                        public List<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory> getParticipantCategory() {
                            if (participantCategory == null) {
                                participantCategory = new ArrayList<CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory>();
                            }
                            return this.participantCategory;
                        }

                        
                        public String getAvailable() {
                            return available;
                        }

                        
                        public void setAvailable(String value) {
                            this.available = value;
                        }

                        
                        public String getPerPaxPriceInd() {
                            return perPaxPriceInd;
                        }

                        
                        public void setPerPaxPriceInd(String value) {
                            this.perPaxPriceInd = value;
                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "qualifierInfo",
                            "price"
                        })
                        public static class ParticipantCategory {

                            @XmlElement(name = "QualifierInfo", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.QualifierInfo qualifierInfo;
                            @XmlElement(name = "Price", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.Price price;

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.QualifierInfo getQualifierInfo() {
                                return qualifierInfo;
                            }

                            
                            public void setQualifierInfo(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.QualifierInfo value) {
                                this.qualifierInfo = value;
                            }

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.Price getPrice() {
                                return price;
                            }

                            
                            public void setPrice(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory.Price value) {
                                this.price = value;
                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "value"
                            })
                            public static class Price {

                                @XmlValue
                                protected String value;
                                @XmlAttribute(name = "CurrencyCode")
                                protected String currencyCode;
                                @XmlAttribute(name = "Amount")
                                protected Float amount;
                                @XmlAttribute(name = "PriceForDate")
                                protected String priceForDate;

                                
                                public String getValue() {
                                    return value;
                                }

                                
                                public void setValue(String value) {
                                    this.value = value;
                                }

                                
                                public String getCurrencyCode() {
                                    return currencyCode;
                                }

                                
                                public void setCurrencyCode(String value) {
                                    this.currencyCode = value;
                                }

                                
                                public Float getAmount() {
                                    return amount;
                                }

                                
                                public void setAmount(Float value) {
                                    this.amount = value;
                                }

                                
                                public String getPriceForDate() {
                                    return priceForDate;
                                }

                                
                                public void setPriceForDate(String value) {
                                    this.priceForDate = value;
                                }

                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "value"
                            })
                            public static class QualifierInfo {

                                @XmlValue
                                protected String value;
                                @XmlAttribute(name = "Extension")
                                protected String extension;

                                
                                public String getValue() {
                                    return value;
                                }

                                
                                public void setValue(String value) {
                                    this.value = value;
                                }

                                
                                public String getExtension() {
                                    return extension;
                                }

                                
                                public void setExtension(String value) {
                                    this.extension = value;
                                }

                            }

                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "taxAmounts"
                        })
                        public static class Summary {

                            @XmlElement(name = "TaxAmounts", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary.TaxAmounts taxAmounts;
                            @XmlAttribute(name = "Amount")
                            protected Float amount;
                            @XmlAttribute(name = "CurrencyCode")
                            protected String currencyCode;

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary.TaxAmounts getTaxAmounts() {
                                return taxAmounts;
                            }

                            
                            public void setTaxAmounts(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.Summary.TaxAmounts value) {
                                this.taxAmounts = value;
                            }

                            
                            public Float getAmount() {
                                return amount;
                            }

                            
                            public void setAmount(Float value) {
                                this.amount = value;
                            }

                            
                            public String getCurrencyCode() {
                                return currencyCode;
                            }

                            
                            public void setCurrencyCode(String value) {
                                this.currencyCode = value;
                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "value"
                            })
                            public static class TaxAmounts {

                                @XmlValue
                                protected String value;
                                @XmlAttribute(name = "available")
                                protected String available;

                                
                                public String getValue() {
                                    return value;
                                }

                                
                                public void setValue(String value) {
                                    this.value = value;
                                }

                                
                                public String getAvailable() {
                                    return available;
                                }

                                
                                public void setAvailable(String value) {
                                    this.available = value;
                                }

                            }

                        }

                    }


                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "detail"
                    })
                    public static class Schedule {

                        @XmlElement(name = "Detail", required = true)
                        protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail detail;
                        @XmlAttribute(name = "IsDurationAvailable")
                        protected String isDurationAvailable;

                        
                        public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail getDetail() {
                            return detail;
                        }

                        
                        public void setDetail(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail value) {
                            this.detail = value;
                        }

                        
                        public String getIsDurationAvailable() {
                            return isDurationAvailable;
                        }

                        
                        public void setIsDurationAvailable(String value) {
                            this.isDurationAvailable = value;
                        }


                        
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "operationTimes"
                        })
                        public static class Detail {

                            @XmlElement(name = "OperationTimes", required = true)
                            protected CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail.OperationTimes operationTimes;
                            @XmlAttribute(name = "Start")
                            protected String start;
                            @XmlAttribute(name = "End")
                            protected String end;
                            @XmlAttribute(name = "Duration")
                            protected String duration;

                            
                            public CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail.OperationTimes getOperationTimes() {
                                return operationTimes;
                            }

                            
                            public void setOperationTimes(CommonSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Schedule.Detail.OperationTimes value) {
                                this.operationTimes = value;
                            }

                            
                            public String getStart() {
                                return start;
                            }

                            
                            public void setStart(String value) {
                                this.start = value;
                            }

                            
                            public String getEnd() {
                                return end;
                            }

                            
                            public void setEnd(String value) {
                                this.end = value;
                            }

                            
                            public String getDuration() {
                                return duration;
                            }

                            
                            public void setDuration(String value) {
                                this.duration = value;
                            }


                            
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "content"
                            })
                            public static class OperationTimes {

                                @XmlElementRef(name = "OperationTime", type = JAXBElement.class, required = false)
                                @XmlMixed
                                protected List<Serializable> content;
                                @XmlAttribute(name = "available")
                                protected String available;
                                @XmlAttribute(name = "IsTextFormat")
                                protected String isTextFormat;

                                
                                public List<Serializable> getContent() {
                                    if (content == null) {
                                        content = new ArrayList<Serializable>();
                                    }
                                    return this.content;
                                }

                                
                                public String getAvailable() {
                                    return available;
                                }

                                
                                public void setAvailable(String value) {
                                    this.available = value;
                                }

                                
                                public String getIsTextFormat() {
                                    return isTextFormat;
                                }

                                
                                public void setIsTextFormat(String value) {
                                    this.isTextFormat = value;
                                }


                                
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class OperationTime {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "Text")
                                    protected String text;
                                    @XmlAttribute(name = "Sun")
                                    protected String sun;
                                    @XmlAttribute(name = "Sat")
                                    protected String sat;
                                    @XmlAttribute(name = "Tue")
                                    protected String tue;
                                    @XmlAttribute(name = "Fri")
                                    protected String fri;
                                    @XmlAttribute(name = "Thur")
                                    protected String thur;
                                    @XmlAttribute(name = "Weds")
                                    protected String weds;
                                    @XmlAttribute(name = "Mon")
                                    protected String mon;

                                    
                                    public String getValue() {
                                        return value;
                                    }

                                    
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    
                                    public String getText() {
                                        return text;
                                    }

                                    
                                    public void setText(String value) {
                                        this.text = value;
                                    }

                                    
                                    public String getSun() {
                                        return sun;
                                    }

                                    
                                    public void setSun(String value) {
                                        this.sun = value;
                                    }

                                    
                                    public String getSat() {
                                        return sat;
                                    }

                                    
                                    public void setSat(String value) {
                                        this.sat = value;
                                    }

                                    
                                    public String getTue() {
                                        return tue;
                                    }

                                    
                                    public void setTue(String value) {
                                        this.tue = value;
                                    }

                                    
                                    public String getFri() {
                                        return fri;
                                    }

                                    
                                    public void setFri(String value) {
                                        this.fri = value;
                                    }

                                    
                                    public String getThur() {
                                        return thur;
                                    }

                                    
                                    public void setThur(String value) {
                                        this.thur = value;
                                    }

                                    
                                    public String getWeds() {
                                        return weds;
                                    }

                                    
                                    public void setWeds(String value) {
                                        this.weds = value;
                                    }

                                    
                                    public String getMon() {
                                        return mon;
                                    }

                                    
                                    public void setMon(String value) {
                                        this.mon = value;
                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "status",
        "userID",
        "sessionID",
        "transactionID"
    })
    public static class ResponseHeader {

        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "UserID", required = true)
        protected String userID;
        @XmlElement(name = "SessionID", required = true)
        protected String sessionID;
        @XmlElement(name = "TransactionID", required = true)
        protected String transactionID;

        
        public String getStatus() {
            return status;
        }

        
        public void setStatus(String value) {
            this.status = value;
        }

        
        public String getUserID() {
            return userID;
        }

        
        public void setUserID(String value) {
            this.userID = value;
        }

        
        public String getSessionID() {
            return sessionID;
        }

        
        public void setSessionID(String value) {
            this.sessionID = value;
        }

        
        public String getTransactionID() {
            return transactionID;
        }

        
        public void setTransactionID(String value) {
            this.transactionID = value;
        }

    }

}
