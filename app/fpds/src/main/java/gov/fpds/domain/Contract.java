package gov.fpds.domain;

import io.searchbox.annotations.JestId;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contract {
	@JestId
	private String id;
	private String vendorname;
	
	private String PlaceofPerformanceCity;
	private String a76action;
	private String account_title;
	private String agencyid;
	private String aiobflag;
	private BigDecimal annualrevenue;
	private String apaobflag;
	private String baobflag;
	private BigDecimal baseandalloptionsvalue;
	private BigDecimal baseandexercisedoptionsvalue;
	private String ccrexception;
	private String city;
	private String claimantprogramcode;
	private String clingercohenact;
	private String commercialitemacquisitionprocedures;
	private String commercialitemtestprogram;
	private String competitiveprocedures;
	private String congressionaldistrict;
	private String consolidatedcontract;
	private String contingencyhumanitarianpeacekeepingoperation;
	private String contractactiontype;
	private String contractbundling;
	private String contractfinancing;
	private String contractingofficeagencyid;
	private String contractingofficeid;
	private String contractingofficerbusinesssizedetermination;
	private String costaccountingstandardsclause;
	private String costorpricingdata;
	private String countryoforigin;
	private Date currentcompletiondate;
	private String davisbaconact;
	private String descriptionofcontractrequirement;
	private String divisionname;
	private String divisionnumberorofficecode;
	private BigDecimal dollarsobligated;
	private String dunsnumber;
	private String educationalinstitutionflag;
	private Date effectivedate;
	private String emergingsmallbusinessflag;
	private String evaluatedpreference;
	private String extentcompeted;
	private String faxno;
	private String fedbizopps;
	private String federalgovernmentflag;
	private String firm8aflag;
	private Integer fiscal_year;
	private String fundedbyforeignentity;
	private String fundingrequestingagencyid;
	private String fundingrequestingofficeid;
	private String gfe_gfp;
	private String haobflag;
	private String hbcuflag;
	private String hospitalflag;
	private String hubzoneflag;
	private String idvagencyid;
	private String idvmodificationnumber;
	private String idvpiid;
	private String informationtechnologycommercialitemcategory;
	private String interagencycontractingauthority;
	private String is1862landgrantcollege;
	private String is1890landgrantcollege;
	private String is1994landgrantcollege;
	private String isairportauthority;
	private String isalaskannativeownedcorporationorfirm;
	private String isarchitectureandengineering;
	private String iscitylocalgovernment;
	private String iscommunitydevelopedcorporationownedfirm;
	private String iscommunitydevelopmentcorporation;
	private String isconstructionfirm;
	private String iscorporateentitynottaxexempt;
	private String iscorporateentitytaxexempt;
	private String iscouncilofgovernments;
	private String iscountylocalgovernment;
	private String isdomesticshelter;
	private String isdotcertifieddisadvantagedbusinessenterprise;
	private String isecondisadvwomenownedsmallbusiness;
	private String isfederalgovernmentagency;
	private String isfederallyfundedresearchanddevelopmentcorp;
	private String isforeigngovernment;
	private String isforeignownedandlocated;
	private String isforprofitorganization;
	private String isfoundation;
	private String ishispanicservicinginstitution;
	private String ishousingauthoritiespublicortribal;
	private String isindiantribe;
	private String isintermunicipallocalgovernment;
	private String isinternationalorganization;
	private String isinterstateentity;
	private String isjointventureecondisadvwomenownedsmallbusiness;
	private String isjointventurewomenownedsmallbusiness;
	private String islaborsurplusareafirm;
	private String islimitedliabilitycorporation;
	private String islocalgovernmentowned;
	private String ismanufacturerofgoods;
	private String ismunicipalitylocalgovernment;
	private String isnativehawaiianownedorganizationorfirm;
	private String isotherbusinessororganization;
	private String isotherminorityowned;
	private String isothernotforprofitorganization;
	private String ispartnershiporlimitedliabilitypartnership;
	private String isplanningcommission;
	private String isportauthority;
	private String isprivateuniversityorcollege;
	private String issbacertifiedsmalldisadvantagedbusiness;
	private String isschooldistrictlocalgovernment;
	private String isschoolofforestry;
	private String isserviceprovider;
	private String issmallagriculturalcooperative;
	private String issoleproprietorship;
	private String isstatecontrolledinstitutionofhigherlearning;
	private String issubchapterscorporation;
	private String istownshiplocalgovernment;
	private String istransitauthority;
	private String istribalcollege;
	private String istriballyownedfirm;
	private String isveterinarycollege;
	private String isveterinaryhospital;
	private String iswomenownedsmallbusiness;
	private Date last_modified_date;
	private Date lastdatetoorder;
	private String lettercontract;
	private String localareasetaside;
	private String localgovernmentflag;
	private String locationcode;
	private String maj_agency_cat;
	private String maj_fund_agency_cat;
	private String majorprogramcode;
	private String manufacturingorganizationtype;
	private String minorityinstitutionflag;
	private String minorityownedbusinessflag;
	private String mod_agency;
	private String mod_parent;
	private String modnumber;
	private String multipleorsingleawardidc;
	private String multiyearcontract;
	private String naobflag;
	private String nationalinterestactioncode;
	private String nonprofitorganizationflag;
	private String numberofactions;
	private Integer numberofemployees;
	private Integer numberofoffersreceived;
	private String organizationaltype;
	private String otherstatutoryauthority;
	private String parentdunsnumber;
	private String performancebasedservicecontract;
	private String phoneno;
	private String piid;
	private String placeofmanufacture;
	private String placeofperformancecongressionaldistrict;
	private String placeofperformancecountrycode;
	private String placeofperformancezipcode;
	private String pop_cd;
	private String pop_state_code;
	private String priceevaluationpercentdifference;
	private String prime_awardee_executive1;
	private BigDecimal prime_awardee_executive1_compensation;
	private String prime_awardee_executive2;
	private BigDecimal prime_awardee_executive2_compensation;
	private String prime_awardee_executive3;
	private BigDecimal prime_awardee_executive3_compensation;
	private String prime_awardee_executive4;
	private BigDecimal prime_awardee_executive4_compensation;
	private String prime_awardee_executive5;
	private BigDecimal prime_awardee_executive5_compensation;
	private String principalnaicscode;
	private String productorservicecode;
	private String programacronym;
	private String progsourceaccount;
	private String progsourceagency;
	private String progsourcesubacct;
	private String psc_cat;
	private String purchasecardaspaymentmethod;
	private String reasonformodification;
	private String reasonnotcompeted;
	private String rec_flag;
	private String receivescontracts;
	private String receivescontractsandgrants;
	private String receivesgrants;
	private String recoveredmaterialclauses;
	private Date registrationdate;
	private Date renewaldate;
	private String research;
	private String saaobflag;
	private String sdbflag;
	private String seatransportation;
	private String servicecontractact;
	private String shelteredworkshopflag;
	private Date signeddate;
	private String smallbusinesscompetitivenessdemonstrationprogram;
	private String solicitationid;
	private String solicitationprocedures;
	private String srdvobflag;
	private String state;
	private String statecode;
	private String stategovernmentflag;
	private String statutoryexceptiontofairopportunity;
	private String streetaddress;
	private String streetaddress2;
	private String streetaddress3;
	private String subcontractplan;
	private String systemequipmentcode;
	private String transaction_status;
	private Long transactionnumber;
	private String tribalgovernmentflag;
	private String typeofcontractpricing;
	private String typeofidc;
	private String typeofsetaside;
	private Date ultimatecompletiondate;
	private String unique_transaction_id;
	private String useofepadesignatedproducts;
	private String vendor_cd;
	private String vendor_state_code;
	private String vendoralternatename;
	private String vendoralternatesitecode;
	private String vendorcountrycode;
	private String vendordoingasbusinessname;
	private String vendorenabled;
	private String vendorlegalorganizationname;
	private String vendorlocationdisableflag;
	private String vendorsitecode;
	private String veteranownedflag;
	private String walshhealyact;
	private String womenownedflag;
	private String zipcode;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}	
	public String getPlaceofPerformanceCity() {
		return PlaceofPerformanceCity;
	}
	public void setPlaceofPerformanceCity(String placeofPerformanceCity) {
		PlaceofPerformanceCity = placeofPerformanceCity;
	}
	public String getA76action() {
		return a76action;
	}
	public void setA76action(String a76action) {
		this.a76action = a76action;
	}
	public String getAccount_title() {
		return account_title;
	}
	public void setAccount_title(String account_title) {
		this.account_title = account_title;
	}
	public String getAgencyid() {
		return agencyid;
	}
	public void setAgencyid(String agencyid) {
		this.agencyid = agencyid;
	}
	public String getAiobflag() {
		return aiobflag;
	}
	public void setAiobflag(String aiobflag) {
		this.aiobflag = aiobflag;
	}
	public BigDecimal getAnnualrevenue() {
		return annualrevenue;
	}
	public void setAnnualrevenue(BigDecimal annualrevenue) {
		this.annualrevenue = annualrevenue;
	}
	public String getApaobflag() {
		return apaobflag;
	}
	public void setApaobflag(String apaobflag) {
		this.apaobflag = apaobflag;
	}
	public String getBaobflag() {
		return baobflag;
	}
	public void setBaobflag(String baobflag) {
		this.baobflag = baobflag;
	}
	public BigDecimal getBaseandalloptionsvalue() {
		return baseandalloptionsvalue;
	}
	public void setBaseandalloptionsvalue(BigDecimal baseandalloptionsvalue) {
		this.baseandalloptionsvalue = baseandalloptionsvalue;
	}
	public BigDecimal getBaseandexercisedoptionsvalue() {
		return baseandexercisedoptionsvalue;
	}
	public void setBaseandexercisedoptionsvalue(BigDecimal baseandexercisedoptionsvalue) {
		this.baseandexercisedoptionsvalue = baseandexercisedoptionsvalue;
	}
	public String getCcrexception() {
		return ccrexception;
	}
	public void setCcrexception(String ccrexception) {
		this.ccrexception = ccrexception;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getClaimantprogramcode() {
		return claimantprogramcode;
	}
	public void setClaimantprogramcode(String claimantprogramcode) {
		this.claimantprogramcode = claimantprogramcode;
	}
	public String getClingercohenact() {
		return clingercohenact;
	}
	public void setClingercohenact(String clingercohenact) {
		this.clingercohenact = clingercohenact;
	}
	public String getCommercialitemacquisitionprocedures() {
		return commercialitemacquisitionprocedures;
	}
	public void setCommercialitemacquisitionprocedures(
			String commercialitemacquisitionprocedures) {
		this.commercialitemacquisitionprocedures = commercialitemacquisitionprocedures;
	}
	public String getCommercialitemtestprogram() {
		return commercialitemtestprogram;
	}
	public void setCommercialitemtestprogram(String commercialitemtestprogram) {
		this.commercialitemtestprogram = commercialitemtestprogram;
	}
	public String getCompetitiveprocedures() {
		return competitiveprocedures;
	}
	public void setCompetitiveprocedures(String competitiveprocedures) {
		this.competitiveprocedures = competitiveprocedures;
	}
	public String getCongressionaldistrict() {
		return congressionaldistrict;
	}
	public void setCongressionaldistrict(String congressionaldistrict) {
		this.congressionaldistrict = congressionaldistrict;
	}
	public String getConsolidatedcontract() {
		return consolidatedcontract;
	}
	public void setConsolidatedcontract(String consolidatedcontract) {
		this.consolidatedcontract = consolidatedcontract;
	}
	public String getContingencyhumanitarianpeacekeepingoperation() {
		return contingencyhumanitarianpeacekeepingoperation;
	}
	public void setContingencyhumanitarianpeacekeepingoperation(
			String contingencyhumanitarianpeacekeepingoperation) {
		this.contingencyhumanitarianpeacekeepingoperation = contingencyhumanitarianpeacekeepingoperation;
	}
	public String getContractactiontype() {
		return contractactiontype;
	}
	public void setContractactiontype(String contractactiontype) {
		this.contractactiontype = contractactiontype;
	}
	public String getContractbundling() {
		return contractbundling;
	}
	public void setContractbundling(String contractbundling) {
		this.contractbundling = contractbundling;
	}
	public String getContractfinancing() {
		return contractfinancing;
	}
	public void setContractfinancing(String contractfinancing) {
		this.contractfinancing = contractfinancing;
	}
	public String getContractingofficeagencyid() {
		return contractingofficeagencyid;
	}
	public void setContractingofficeagencyid(String contractingofficeagencyid) {
		this.contractingofficeagencyid = contractingofficeagencyid;
	}
	public String getContractingofficeid() {
		return contractingofficeid;
	}
	public void setContractingofficeid(String contractingofficeid) {
		this.contractingofficeid = contractingofficeid;
	}
	public String getContractingofficerbusinesssizedetermination() {
		return contractingofficerbusinesssizedetermination;
	}
	public void setContractingofficerbusinesssizedetermination(
			String contractingofficerbusinesssizedetermination) {
		this.contractingofficerbusinesssizedetermination = contractingofficerbusinesssizedetermination;
	}
	public String getCostaccountingstandardsclause() {
		return costaccountingstandardsclause;
	}
	public void setCostaccountingstandardsclause(
			String costaccountingstandardsclause) {
		this.costaccountingstandardsclause = costaccountingstandardsclause;
	}
	public String getCostorpricingdata() {
		return costorpricingdata;
	}
	public void setCostorpricingdata(String costorpricingdata) {
		this.costorpricingdata = costorpricingdata;
	}
	public String getCountryoforigin() {
		return countryoforigin;
	}
	public void setCountryoforigin(String countryoforigin) {
		this.countryoforigin = countryoforigin;
	}
	public Date getCurrentcompletiondate() {
		return currentcompletiondate;
	}
	public void setCurrentcompletiondate(Date currentcompletiondate) {
		this.currentcompletiondate = currentcompletiondate;
	}
	public String getDavisbaconact() {
		return davisbaconact;
	}
	public void setDavisbaconact(String davisbaconact) {
		this.davisbaconact = davisbaconact;
	}
	public String getDescriptionofcontractrequirement() {
		return descriptionofcontractrequirement;
	}
	public void setDescriptionofcontractrequirement(
			String descriptionofcontractrequirement) {
		this.descriptionofcontractrequirement = descriptionofcontractrequirement;
	}
	public String getDivisionname() {
		return divisionname;
	}
	public void setDivisionname(String divisionname) {
		this.divisionname = divisionname;
	}
	public String getDivisionnumberorofficecode() {
		return divisionnumberorofficecode;
	}
	public void setDivisionnumberorofficecode(String divisionnumberorofficecode) {
		this.divisionnumberorofficecode = divisionnumberorofficecode;
	}
	public BigDecimal getDollarsobligated() {
		return dollarsobligated;
	}
	public void setDollarsobligated(BigDecimal dollarsobligated) {
		this.dollarsobligated = dollarsobligated;
	}
	public String getDunsnumber() {
		return dunsnumber;
	}
	public void setDunsnumber(String dunsnumber) {
		this.dunsnumber = dunsnumber;
	}
	public String getEducationalinstitutionflag() {
		return educationalinstitutionflag;
	}
	public void setEducationalinstitutionflag(String educationalinstitutionflag) {
		this.educationalinstitutionflag = educationalinstitutionflag;
	}
	public Date getEffectivedate() {
		return effectivedate;
	}
	public void setEffectiveDate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getEmergingsmallbusinessflag() {
		return emergingsmallbusinessflag;
	}
	public void setEmergingsmallbusinessflag(String emergingsmallbusinessflag) {
		this.emergingsmallbusinessflag = emergingsmallbusinessflag;
	}
	public String getEvaluatedpreference() {
		return evaluatedpreference;
	}
	public void setEvaluatedpreference(String evaluatedpreference) {
		this.evaluatedpreference = evaluatedpreference;
	}
	public String getExtentcompeted() {
		return extentcompeted;
	}
	public void setExtentcompeted(String extentcompeted) {
		this.extentcompeted = extentcompeted;
	}
	public String getFaxno() {
		return faxno;
	}
	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}
	public String getFedbizopps() {
		return fedbizopps;
	}
	public void setFedbizopps(String fedbizopps) {
		this.fedbizopps = fedbizopps;
	}
	public String getFederalgovernmentflag() {
		return federalgovernmentflag;
	}
	public void setFederalgovernmentflag(String federalgovernmentflag) {
		this.federalgovernmentflag = federalgovernmentflag;
	}
	public String getFirm8aflag() {
		return firm8aflag;
	}
	public void setFirm8aflag(String firm8aflag) {
		this.firm8aflag = firm8aflag;
	}
	public Integer getFiscal_year() {
		return fiscal_year;
	}
	public void setFiscal_year(Integer fiscal_year) {
		this.fiscal_year = fiscal_year;
	}
	public String getFundedbyforeignentity() {
		return fundedbyforeignentity;
	}
	public void setFundedbyforeignentity(String fundedbyforeignentity) {
		this.fundedbyforeignentity = fundedbyforeignentity;
	}
	public String getFundingrequestingagencyid() {
		return fundingrequestingagencyid;
	}
	public void setFundingrequestingagencyid(String fundingrequestingagencyid) {
		this.fundingrequestingagencyid = fundingrequestingagencyid;
	}
	public String getFundingrequestingofficeid() {
		return fundingrequestingofficeid;
	}
	public void setFundingrequestingofficeid(String fundingrequestingofficeid) {
		this.fundingrequestingofficeid = fundingrequestingofficeid;
	}
	public String getGfe_gfp() {
		return gfe_gfp;
	}
	public void setGfe_gfp(String gfe_gfp) {
		this.gfe_gfp = gfe_gfp;
	}
	public String getHaobflag() {
		return haobflag;
	}
	public void setHaobflag(String haobflag) {
		this.haobflag = haobflag;
	}
	public String getHbcuflag() {
		return hbcuflag;
	}
	public void setHbcuflag(String hbcuflag) {
		this.hbcuflag = hbcuflag;
	}
	public String getHospitalflag() {
		return hospitalflag;
	}
	public void setHospitalflag(String hospitalflag) {
		this.hospitalflag = hospitalflag;
	}
	public String getHubzoneflag() {
		return hubzoneflag;
	}
	public void setHubzoneflag(String hubzoneflag) {
		this.hubzoneflag = hubzoneflag;
	}
	public String getIdvagencyid() {
		return idvagencyid;
	}
	public void setIdvagencyid(String idvagencyid) {
		this.idvagencyid = idvagencyid;
	}
	public String getIdvmodificationnumber() {
		return idvmodificationnumber;
	}
	public void setIdvmodificationnumber(String idvmodificationnumber) {
		this.idvmodificationnumber = idvmodificationnumber;
	}
	public String getIdvpiid() {
		return idvpiid;
	}
	public void setIdvpiid(String idvpiid) {
		this.idvpiid = idvpiid;
	}
	public String getInformationtechnologycommercialitemcategory() {
		return informationtechnologycommercialitemcategory;
	}
	public void setInformationtechnologycommercialitemcategory(
			String informationtechnologycommercialitemcategory) {
		this.informationtechnologycommercialitemcategory = informationtechnologycommercialitemcategory;
	}
	public String getInteragencycontractingauthority() {
		return interagencycontractingauthority;
	}
	public void setInteragencycontractingauthority(
			String interagencycontractingauthority) {
		this.interagencycontractingauthority = interagencycontractingauthority;
	}
	public String getIs1862landgrantcollege() {
		return is1862landgrantcollege;
	}
	public void setIs1862landgrantcollege(String is1862landgrantcollege) {
		this.is1862landgrantcollege = is1862landgrantcollege;
	}
	public String getIs1890landgrantcollege() {
		return is1890landgrantcollege;
	}
	public void setIs1890landgrantcollege(String is1890landgrantcollege) {
		this.is1890landgrantcollege = is1890landgrantcollege;
	}
	public String getIs1994landgrantcollege() {
		return is1994landgrantcollege;
	}
	public void setIs1994landgrantcollege(String is1994landgrantcollege) {
		this.is1994landgrantcollege = is1994landgrantcollege;
	}
	public String getIsairportauthority() {
		return isairportauthority;
	}
	public void setIsairportauthority(String isairportauthority) {
		this.isairportauthority = isairportauthority;
	}
	public String getIsalaskannativeownedcorporationorfirm() {
		return isalaskannativeownedcorporationorfirm;
	}
	public void setIsalaskannativeownedcorporationorfirm(
			String isalaskannativeownedcorporationorfirm) {
		this.isalaskannativeownedcorporationorfirm = isalaskannativeownedcorporationorfirm;
	}
	public String getIsarchitectureandengineering() {
		return isarchitectureandengineering;
	}
	public void setIsarchitectureandengineering(String isarchitectureandengineering) {
		this.isarchitectureandengineering = isarchitectureandengineering;
	}
	public String getIscitylocalgovernment() {
		return iscitylocalgovernment;
	}
	public void setIscitylocalgovernment(String iscitylocalgovernment) {
		this.iscitylocalgovernment = iscitylocalgovernment;
	}
	public String getIscommunitydevelopedcorporationownedfirm() {
		return iscommunitydevelopedcorporationownedfirm;
	}
	public void setIscommunitydevelopedcorporationownedfirm(
			String iscommunitydevelopedcorporationownedfirm) {
		this.iscommunitydevelopedcorporationownedfirm = iscommunitydevelopedcorporationownedfirm;
	}
	public String getIscommunitydevelopmentcorporation() {
		return iscommunitydevelopmentcorporation;
	}
	public void setIscommunitydevelopmentcorporation(
			String iscommunitydevelopmentcorporation) {
		this.iscommunitydevelopmentcorporation = iscommunitydevelopmentcorporation;
	}
	public String getIsconstructionfirm() {
		return isconstructionfirm;
	}
	public void setIsconstructionfirm(String isconstructionfirm) {
		this.isconstructionfirm = isconstructionfirm;
	}
	public String getIscorporateentitynottaxexempt() {
		return iscorporateentitynottaxexempt;
	}
	public void setIscorporateentitynottaxexempt(
			String iscorporateentitynottaxexempt) {
		this.iscorporateentitynottaxexempt = iscorporateentitynottaxexempt;
	}
	public String getIscorporateentitytaxexempt() {
		return iscorporateentitytaxexempt;
	}
	public void setIscorporateentitytaxexempt(String iscorporateentitytaxexempt) {
		this.iscorporateentitytaxexempt = iscorporateentitytaxexempt;
	}
	public String getIscouncilofgovernments() {
		return iscouncilofgovernments;
	}
	public void setIscouncilofgovernments(String iscouncilofgovernments) {
		this.iscouncilofgovernments = iscouncilofgovernments;
	}
	public String getIscountylocalgovernment() {
		return iscountylocalgovernment;
	}
	public void setIscountylocalgovernment(String iscountylocalgovernment) {
		this.iscountylocalgovernment = iscountylocalgovernment;
	}
	public String getIsdomesticshelter() {
		return isdomesticshelter;
	}
	public void setIsdomesticshelter(String isdomesticshelter) {
		this.isdomesticshelter = isdomesticshelter;
	}
	public String getIsdotcertifieddisadvantagedbusinessenterprise() {
		return isdotcertifieddisadvantagedbusinessenterprise;
	}
	public void setIsdotcertifieddisadvantagedbusinessenterprise(
			String isdotcertifieddisadvantagedbusinessenterprise) {
		this.isdotcertifieddisadvantagedbusinessenterprise = isdotcertifieddisadvantagedbusinessenterprise;
	}
	public String getIsecondisadvwomenownedsmallbusiness() {
		return isecondisadvwomenownedsmallbusiness;
	}
	public void setIsecondisadvwomenownedsmallbusiness(
			String isecondisadvwomenownedsmallbusiness) {
		this.isecondisadvwomenownedsmallbusiness = isecondisadvwomenownedsmallbusiness;
	}
	public String getIsfederalgovernmentagency() {
		return isfederalgovernmentagency;
	}
	public void setIsfederalgovernmentagency(String isfederalgovernmentagency) {
		this.isfederalgovernmentagency = isfederalgovernmentagency;
	}
	public String getIsfederallyfundedresearchanddevelopmentcorp() {
		return isfederallyfundedresearchanddevelopmentcorp;
	}
	public void setIsfederallyfundedresearchanddevelopmentcorp(
			String isfederallyfundedresearchanddevelopmentcorp) {
		this.isfederallyfundedresearchanddevelopmentcorp = isfederallyfundedresearchanddevelopmentcorp;
	}
	public String getIsforeigngovernment() {
		return isforeigngovernment;
	}
	public void setIsforeigngovernment(String isforeigngovernment) {
		this.isforeigngovernment = isforeigngovernment;
	}
	public String getIsforeignownedandlocated() {
		return isforeignownedandlocated;
	}
	public void setIsforeignownedandlocated(String isforeignownedandlocated) {
		this.isforeignownedandlocated = isforeignownedandlocated;
	}
	public String getIsforprofitorganization() {
		return isforprofitorganization;
	}
	public void setIsforprofitorganization(String isforprofitorganization) {
		this.isforprofitorganization = isforprofitorganization;
	}
	public String getIsfoundation() {
		return isfoundation;
	}
	public void setIsfoundation(String isfoundation) {
		this.isfoundation = isfoundation;
	}
	public String getIshispanicservicinginstitution() {
		return ishispanicservicinginstitution;
	}
	public void setIshispanicservicinginstitution(
			String ishispanicservicinginstitution) {
		this.ishispanicservicinginstitution = ishispanicservicinginstitution;
	}
	public String getIshousingauthoritiespublicortribal() {
		return ishousingauthoritiespublicortribal;
	}
	public void setIshousingauthoritiespublicortribal(
			String ishousingauthoritiespublicortribal) {
		this.ishousingauthoritiespublicortribal = ishousingauthoritiespublicortribal;
	}
	public String getIsindiantribe() {
		return isindiantribe;
	}
	public void setIsindiantribe(String isindiantribe) {
		this.isindiantribe = isindiantribe;
	}
	public String getIsintermunicipallocalgovernment() {
		return isintermunicipallocalgovernment;
	}
	public void setIsintermunicipallocalgovernment(
			String isintermunicipallocalgovernment) {
		this.isintermunicipallocalgovernment = isintermunicipallocalgovernment;
	}
	public String getIsinternationalorganization() {
		return isinternationalorganization;
	}
	public void setIsinternationalorganization(String isinternationalorganization) {
		this.isinternationalorganization = isinternationalorganization;
	}
	public String getIsinterstateentity() {
		return isinterstateentity;
	}
	public void setIsinterstateentity(String isinterstateentity) {
		this.isinterstateentity = isinterstateentity;
	}
	public String getIsjointventureecondisadvwomenownedsmallbusiness() {
		return isjointventureecondisadvwomenownedsmallbusiness;
	}
	public void setIsjointventureecondisadvwomenownedsmallbusiness(
			String isjointventureecondisadvwomenownedsmallbusiness) {
		this.isjointventureecondisadvwomenownedsmallbusiness = isjointventureecondisadvwomenownedsmallbusiness;
	}
	public String getIsjointventurewomenownedsmallbusiness() {
		return isjointventurewomenownedsmallbusiness;
	}
	public void setIsjointventurewomenownedsmallbusiness(
			String isjointventurewomenownedsmallbusiness) {
		this.isjointventurewomenownedsmallbusiness = isjointventurewomenownedsmallbusiness;
	}
	public String getIslaborsurplusareafirm() {
		return islaborsurplusareafirm;
	}
	public void setIslaborsurplusareafirm(String islaborsurplusareafirm) {
		this.islaborsurplusareafirm = islaborsurplusareafirm;
	}
	public String getIslimitedliabilitycorporation() {
		return islimitedliabilitycorporation;
	}
	public void setIslimitedliabilitycorporation(
			String islimitedliabilitycorporation) {
		this.islimitedliabilitycorporation = islimitedliabilitycorporation;
	}
	public String getIslocalgovernmentowned() {
		return islocalgovernmentowned;
	}
	public void setIslocalgovernmentowned(String islocalgovernmentowned) {
		this.islocalgovernmentowned = islocalgovernmentowned;
	}
	public String getIsmanufacturerofgoods() {
		return ismanufacturerofgoods;
	}
	public void setIsmanufacturerofgoods(String ismanufacturerofgoods) {
		this.ismanufacturerofgoods = ismanufacturerofgoods;
	}
	public String getIsmunicipalitylocalgovernment() {
		return ismunicipalitylocalgovernment;
	}
	public void setIsmunicipalitylocalgovernment(
			String ismunicipalitylocalgovernment) {
		this.ismunicipalitylocalgovernment = ismunicipalitylocalgovernment;
	}
	public String getIsnativehawaiianownedorganizationorfirm() {
		return isnativehawaiianownedorganizationorfirm;
	}
	public void setIsnativehawaiianownedorganizationorfirm(
			String isnativehawaiianownedorganizationorfirm) {
		this.isnativehawaiianownedorganizationorfirm = isnativehawaiianownedorganizationorfirm;
	}
	public String getIsotherbusinessororganization() {
		return isotherbusinessororganization;
	}
	public void setIsotherbusinessororganization(
			String isotherbusinessororganization) {
		this.isotherbusinessororganization = isotherbusinessororganization;
	}
	public String getIsotherminorityowned() {
		return isotherminorityowned;
	}
	public void setIsotherminorityowned(String isotherminorityowned) {
		this.isotherminorityowned = isotherminorityowned;
	}
	public String getIsothernotforprofitorganization() {
		return isothernotforprofitorganization;
	}
	public void setIsothernotforprofitorganization(
			String isothernotforprofitorganization) {
		this.isothernotforprofitorganization = isothernotforprofitorganization;
	}
	public String getIspartnershiporlimitedliabilitypartnership() {
		return ispartnershiporlimitedliabilitypartnership;
	}
	public void setIspartnershiporlimitedliabilitypartnership(
			String ispartnershiporlimitedliabilitypartnership) {
		this.ispartnershiporlimitedliabilitypartnership = ispartnershiporlimitedliabilitypartnership;
	}
	public String getIsplanningcommission() {
		return isplanningcommission;
	}
	public void setIsplanningcommission(String isplanningcommission) {
		this.isplanningcommission = isplanningcommission;
	}
	public String getIsportauthority() {
		return isportauthority;
	}
	public void setIsportauthority(String isportauthority) {
		this.isportauthority = isportauthority;
	}
	public String getIsprivateuniversityorcollege() {
		return isprivateuniversityorcollege;
	}
	public void setIsprivateuniversityorcollege(String isprivateuniversityorcollege) {
		this.isprivateuniversityorcollege = isprivateuniversityorcollege;
	}
	public String getIssbacertifiedsmalldisadvantagedbusiness() {
		return issbacertifiedsmalldisadvantagedbusiness;
	}
	public void setIssbacertifiedsmalldisadvantagedbusiness(
			String issbacertifiedsmalldisadvantagedbusiness) {
		this.issbacertifiedsmalldisadvantagedbusiness = issbacertifiedsmalldisadvantagedbusiness;
	}
	public String getIsschooldistrictlocalgovernment() {
		return isschooldistrictlocalgovernment;
	}
	public void setIsschooldistrictlocalgovernment(
			String isschooldistrictlocalgovernment) {
		this.isschooldistrictlocalgovernment = isschooldistrictlocalgovernment;
	}
	public String getIsschoolofforestry() {
		return isschoolofforestry;
	}
	public void setIsschoolofforestry(String isschoolofforestry) {
		this.isschoolofforestry = isschoolofforestry;
	}
	public String getIsserviceprovider() {
		return isserviceprovider;
	}
	public void setIsserviceprovider(String isserviceprovider) {
		this.isserviceprovider = isserviceprovider;
	}
	public String getIssmallagriculturalcooperative() {
		return issmallagriculturalcooperative;
	}
	public void setIssmallagriculturalcooperative(
			String issmallagriculturalcooperative) {
		this.issmallagriculturalcooperative = issmallagriculturalcooperative;
	}
	public String getIssoleproprietorship() {
		return issoleproprietorship;
	}
	public void setIssoleproprietorship(String issoleproprietorship) {
		this.issoleproprietorship = issoleproprietorship;
	}
	public String getIsstatecontrolledinstitutionofhigherlearning() {
		return isstatecontrolledinstitutionofhigherlearning;
	}
	public void setIsstatecontrolledinstitutionofhigherlearning(
			String isstatecontrolledinstitutionofhigherlearning) {
		this.isstatecontrolledinstitutionofhigherlearning = isstatecontrolledinstitutionofhigherlearning;
	}
	public String getIssubchapterscorporation() {
		return issubchapterscorporation;
	}
	public void setIssubchapterscorporation(String issubchapterscorporation) {
		this.issubchapterscorporation = issubchapterscorporation;
	}
	public String getIstownshiplocalgovernment() {
		return istownshiplocalgovernment;
	}
	public void setIstownshiplocalgovernment(String istownshiplocalgovernment) {
		this.istownshiplocalgovernment = istownshiplocalgovernment;
	}
	public String getIstransitauthority() {
		return istransitauthority;
	}
	public void setIstransitauthority(String istransitauthority) {
		this.istransitauthority = istransitauthority;
	}
	public String getIstribalcollege() {
		return istribalcollege;
	}
	public void setIstribalcollege(String istribalcollege) {
		this.istribalcollege = istribalcollege;
	}
	public String getIstriballyownedfirm() {
		return istriballyownedfirm;
	}
	public void setIstriballyownedfirm(String istriballyownedfirm) {
		this.istriballyownedfirm = istriballyownedfirm;
	}
	public String getIsveterinarycollege() {
		return isveterinarycollege;
	}
	public void setIsveterinarycollege(String isveterinarycollege) {
		this.isveterinarycollege = isveterinarycollege;
	}
	public String getIsveterinaryhospital() {
		return isveterinaryhospital;
	}
	public void setIsveterinaryhospital(String isveterinaryhospital) {
		this.isveterinaryhospital = isveterinaryhospital;
	}
	public String getIswomenownedsmallbusiness() {
		return iswomenownedsmallbusiness;
	}
	public void setIswomenownedsmallbusiness(String iswomenownedsmallbusiness) {
		this.iswomenownedsmallbusiness = iswomenownedsmallbusiness;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public Date getLastdatetoorder() {
		return lastdatetoorder;
	}
	public void setLastdatetoorder(Date lastdatetoorder) {
		this.lastdatetoorder = lastdatetoorder;
	}
	public String getLettercontract() {
		return lettercontract;
	}
	public void setLettercontract(String lettercontract) {
		this.lettercontract = lettercontract;
	}
	public String getLocalareasetaside() {
		return localareasetaside;
	}
	public void setLocalareasetaside(String localareasetaside) {
		this.localareasetaside = localareasetaside;
	}
	public String getLocalgovernmentflag() {
		return localgovernmentflag;
	}
	public void setLocalgovernmentflag(String localgovernmentflag) {
		this.localgovernmentflag = localgovernmentflag;
	}
	public String getLocationcode() {
		return locationcode;
	}
	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}
	public String getMaj_agency_cat() {
		return maj_agency_cat;
	}
	public void setMaj_agency_cat(String maj_agency_cat) {
		this.maj_agency_cat = maj_agency_cat;
	}
	public String getMaj_fund_agency_cat() {
		return maj_fund_agency_cat;
	}
	public void setMaj_fund_agency_cat(String maj_fund_agency_cat) {
		this.maj_fund_agency_cat = maj_fund_agency_cat;
	}
	public String getMajorprogramcode() {
		return majorprogramcode;
	}
	public void setMajorprogramcode(String majorprogramcode) {
		this.majorprogramcode = majorprogramcode;
	}
	public String getManufacturingorganizationtype() {
		return manufacturingorganizationtype;
	}
	public void setManufacturingorganizationtype(
			String manufacturingorganizationtype) {
		this.manufacturingorganizationtype = manufacturingorganizationtype;
	}
	public String getMinorityinstitutionflag() {
		return minorityinstitutionflag;
	}
	public void setMinorityinstitutionflag(String minorityinstitutionflag) {
		this.minorityinstitutionflag = minorityinstitutionflag;
	}
	public String getMinorityownedbusinessflag() {
		return minorityownedbusinessflag;
	}
	public void setMinorityownedbusinessflag(String minorityownedbusinessflag) {
		this.minorityownedbusinessflag = minorityownedbusinessflag;
	}
	public String getMod_agency() {
		return mod_agency;
	}
	public void setMod_agency(String mod_agency) {
		this.mod_agency = mod_agency;
	}
	public String getMod_parent() {
		return mod_parent;
	}
	public void setMod_parent(String mod_parent) {
		this.mod_parent = mod_parent;
	}
	public String getModnumber() {
		return modnumber;
	}
	public void setModnumber(String modnumber) {
		this.modnumber = modnumber;
	}
	public String getMultipleorsingleawardidc() {
		return multipleorsingleawardidc;
	}
	public void setMultipleorsingleawardidc(String multipleorsingleawardidc) {
		this.multipleorsingleawardidc = multipleorsingleawardidc;
	}
	public String getMultiyearcontract() {
		return multiyearcontract;
	}
	public void setMultiyearcontract(String multiyearcontract) {
		this.multiyearcontract = multiyearcontract;
	}
	public String getNaobflag() {
		return naobflag;
	}
	public void setNaobflag(String naobflag) {
		this.naobflag = naobflag;
	}
	public String getNationalinterestactioncode() {
		return nationalinterestactioncode;
	}
	public void setNationalinterestactioncode(String nationalinterestactioncode) {
		this.nationalinterestactioncode = nationalinterestactioncode;
	}
	public String getNonprofitorganizationflag() {
		return nonprofitorganizationflag;
	}
	public void setNonprofitorganizationflag(String nonprofitorganizationflag) {
		this.nonprofitorganizationflag = nonprofitorganizationflag;
	}
	public String getNumberofactions() {
		return numberofactions;
	}
	public void setNumberofactions(String numberofactions) {
		this.numberofactions = numberofactions;
	}
	public Integer getNumberofemployees() {
		return numberofemployees;
	}
	public void setNumberofemployees(Integer numberofemployees) {
		this.numberofemployees = numberofemployees;
	}
	public Integer getNumberofoffersreceived() {
		return numberofoffersreceived;
	}
	public void setNumberofoffersreceived(Integer numberofoffersreceived) {
		this.numberofoffersreceived = numberofoffersreceived;
	}
	public String getOrganizationaltype() {
		return organizationaltype;
	}
	public void setOrganizationaltype(String organizationaltype) {
		this.organizationaltype = organizationaltype;
	}
	public String getOtherstatutoryauthority() {
		return otherstatutoryauthority;
	}
	public void setOtherstatutoryauthority(String otherstatutoryauthority) {
		this.otherstatutoryauthority = otherstatutoryauthority;
	}
	public String getParentdunsnumber() {
		return parentdunsnumber;
	}
	public void setParentdunsnumber(String parentdunsnumber) {
		this.parentdunsnumber = parentdunsnumber;
	}
	public String getPerformancebasedservicecontract() {
		return performancebasedservicecontract;
	}
	public void setPerformancebasedservicecontract(
			String performancebasedservicecontract) {
		this.performancebasedservicecontract = performancebasedservicecontract;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getPlaceofmanufacture() {
		return placeofmanufacture;
	}
	public void setPlaceofmanufacture(String placeofmanufacture) {
		this.placeofmanufacture = placeofmanufacture;
	}
	public String getPlaceofperformancecongressionaldistrict() {
		return placeofperformancecongressionaldistrict;
	}
	public void setPlaceofperformancecongressionaldistrict(
			String placeofperformancecongressionaldistrict) {
		this.placeofperformancecongressionaldistrict = placeofperformancecongressionaldistrict;
	}
	public String getPlaceofperformancecountrycode() {
		return placeofperformancecountrycode;
	}
	public void setPlaceofperformancecountrycode(
			String placeofperformancecountrycode) {
		this.placeofperformancecountrycode = placeofperformancecountrycode;
	}
	public String getPlaceofperformancezipcode() {
		return placeofperformancezipcode;
	}
	public void setPlaceofperformancezipcode(String placeofperformancezipcode) {
		this.placeofperformancezipcode = placeofperformancezipcode;
	}
	public String getPop_cd() {
		return pop_cd;
	}
	public void setPop_cd(String pop_cd) {
		this.pop_cd = pop_cd;
	}
	public String getPop_state_code() {
		return pop_state_code;
	}
	public void setPop_state_code(String pop_state_code) {
		this.pop_state_code = pop_state_code;
	}
	public String getPriceevaluationpercentdifference() {
		return priceevaluationpercentdifference;
	}
	public void setPriceevaluationpercentdifference(
			String priceevaluationpercentdifference) {
		this.priceevaluationpercentdifference = priceevaluationpercentdifference;
	}
	public String getPrime_awardee_executive1() {
		return prime_awardee_executive1;
	}
	public void setPrime_awardee_executive1(String prime_awardee_executive1) {
		this.prime_awardee_executive1 = prime_awardee_executive1;
	}
	public BigDecimal getPrime_awardee_executive1_compensation() {
		return prime_awardee_executive1_compensation;
	}
	public void setPrime_awardee_executive1_compensation(
			BigDecimal prime_awardee_executive1_compensation) {
		this.prime_awardee_executive1_compensation = prime_awardee_executive1_compensation;
	}
	public String getPrime_awardee_executive2() {
		return prime_awardee_executive2;
	}
	public void setPrime_awardee_executive2(String prime_awardee_executive2) {
		this.prime_awardee_executive2 = prime_awardee_executive2;
	}
	public BigDecimal getPrime_awardee_executive2_compensation() {
		return prime_awardee_executive2_compensation;
	}
	public void setPrime_awardee_executive2_compensation(
			BigDecimal prime_awardee_executive2_compensation) {
		this.prime_awardee_executive2_compensation = prime_awardee_executive2_compensation;
	}
	public String getPrime_awardee_executive3() {
		return prime_awardee_executive3;
	}
	public void setPrime_awardee_executive3(String prime_awardee_executive3) {
		this.prime_awardee_executive3 = prime_awardee_executive3;
	}
	public BigDecimal getPrime_awardee_executive3_compensation() {
		return prime_awardee_executive3_compensation;
	}
	public void setPrime_awardee_executive3_compensation(
			BigDecimal prime_awardee_executive3_compensation) {
		this.prime_awardee_executive3_compensation = prime_awardee_executive3_compensation;
	}
	public String getPrime_awardee_executive4() {
		return prime_awardee_executive4;
	}
	public void setPrime_awardee_executive4(String prime_awardee_executive4) {
		this.prime_awardee_executive4 = prime_awardee_executive4;
	}
	public BigDecimal getPrime_awardee_executive4_compensation() {
		return prime_awardee_executive4_compensation;
	}
	public void setPrime_awardee_executive4_compensation(
			BigDecimal prime_awardee_executive4_compensation) {
		this.prime_awardee_executive4_compensation = prime_awardee_executive4_compensation;
	}
	public String getPrime_awardee_executive5() {
		return prime_awardee_executive5;
	}
	public void setPrime_awardee_executive5(String prime_awardee_executive5) {
		this.prime_awardee_executive5 = prime_awardee_executive5;
	}
	public BigDecimal getPrime_awardee_executive5_compensation() {
		return prime_awardee_executive5_compensation;
	}
	public void setPrime_awardee_executive5_compensation(
			BigDecimal prime_awardee_executive5_compensation) {
		this.prime_awardee_executive5_compensation = prime_awardee_executive5_compensation;
	}
	public String getPrincipalnaicscode() {
		return principalnaicscode;
	}
	public void setPrincipalnaicscode(String principalnaicscode) {
		this.principalnaicscode = principalnaicscode;
	}
	public String getProductorservicecode() {
		return productorservicecode;
	}
	public void setProductorservicecode(String productorservicecode) {
		this.productorservicecode = productorservicecode;
	}
	public String getProgramacronym() {
		return programacronym;
	}
	public void setProgramacronym(String programacronym) {
		this.programacronym = programacronym;
	}
	public String getProgsourceaccount() {
		return progsourceaccount;
	}
	public void setProgsourceaccount(String progsourceaccount) {
		this.progsourceaccount = progsourceaccount;
	}
	public String getProgsourceagency() {
		return progsourceagency;
	}
	public void setProgsourceagency(String progsourceagency) {
		this.progsourceagency = progsourceagency;
	}
	public String getProgsourcesubacct() {
		return progsourcesubacct;
	}
	public void setProgsourcesubacct(String progsourcesubacct) {
		this.progsourcesubacct = progsourcesubacct;
	}
	public String getPsc_cat() {
		return psc_cat;
	}
	public void setPsc_cat(String psc_cat) {
		this.psc_cat = psc_cat;
	}
	public String getPurchasecardaspaymentmethod() {
		return purchasecardaspaymentmethod;
	}
	public void setPurchasecardaspaymentmethod(String purchasecardaspaymentmethod) {
		this.purchasecardaspaymentmethod = purchasecardaspaymentmethod;
	}
	public String getReasonformodification() {
		return reasonformodification;
	}
	public void setReasonformodification(String reasonformodification) {
		this.reasonformodification = reasonformodification;
	}
	public String getReasonnotcompeted() {
		return reasonnotcompeted;
	}
	public void setReasonnotcompeted(String reasonnotcompeted) {
		this.reasonnotcompeted = reasonnotcompeted;
	}
	public String getRec_flag() {
		return rec_flag;
	}
	public void setRec_flag(String rec_flag) {
		this.rec_flag = rec_flag;
	}
	public String getReceivescontracts() {
		return receivescontracts;
	}
	public void setReceivescontracts(String receivescontracts) {
		this.receivescontracts = receivescontracts;
	}
	public String getReceivescontractsandgrants() {
		return receivescontractsandgrants;
	}
	public void setReceivescontractsandgrants(String receivescontractsandgrants) {
		this.receivescontractsandgrants = receivescontractsandgrants;
	}
	public String getReceivesgrants() {
		return receivesgrants;
	}
	public void setReceivesgrants(String receivesgrants) {
		this.receivesgrants = receivesgrants;
	}
	public String getRecoveredmaterialclauses() {
		return recoveredmaterialclauses;
	}
	public void setRecoveredmaterialclauses(String recoveredmaterialclauses) {
		this.recoveredmaterialclauses = recoveredmaterialclauses;
	}
	public Date getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
	public Date getRenewaldate() {
		return renewaldate;
	}
	public void setRenewaldate(Date renewaldate) {
		this.renewaldate = renewaldate;
	}
	public String getResearch() {
		return research;
	}
	public void setResearch(String research) {
		this.research = research;
	}
	public String getSaaobflag() {
		return saaobflag;
	}
	public void setSaaobflag(String saaobflag) {
		this.saaobflag = saaobflag;
	}
	public String getSdbflag() {
		return sdbflag;
	}
	public void setSdbflag(String sdbflag) {
		this.sdbflag = sdbflag;
	}
	public String getSeatransportation() {
		return seatransportation;
	}
	public void setSeatransportation(String seatransportation) {
		this.seatransportation = seatransportation;
	}
	public String getServicecontractact() {
		return servicecontractact;
	}
	public void setServicecontractact(String servicecontractact) {
		this.servicecontractact = servicecontractact;
	}
	public String getShelteredworkshopflag() {
		return shelteredworkshopflag;
	}
	public void setShelteredworkshopflag(String shelteredworkshopflag) {
		this.shelteredworkshopflag = shelteredworkshopflag;
	}
	public Date getSigneddate() {
		return signeddate;
	}
	public void setSigneddate(Date signeddate) {
		this.signeddate = signeddate;
	}
	public String getSmallbusinesscompetitivenessdemonstrationprogram() {
		return smallbusinesscompetitivenessdemonstrationprogram;
	}
	public void setSmallbusinesscompetitivenessdemonstrationprogram(
			String smallbusinesscompetitivenessdemonstrationprogram) {
		this.smallbusinesscompetitivenessdemonstrationprogram = smallbusinesscompetitivenessdemonstrationprogram;
	}
	public String getSolicitationid() {
		return solicitationid;
	}
	public void setSolicitationid(String solicitationid) {
		this.solicitationid = solicitationid;
	}
	public String getSolicitationprocedures() {
		return solicitationprocedures;
	}
	public void setSolicitationprocedures(String solicitationprocedures) {
		this.solicitationprocedures = solicitationprocedures;
	}
	public String getSrdvobflag() {
		return srdvobflag;
	}
	public void setSrdvobflag(String srdvobflag) {
		this.srdvobflag = srdvobflag;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getStategovernmentflag() {
		return stategovernmentflag;
	}
	public void setStategovernmentflag(String stategovernmentflag) {
		this.stategovernmentflag = stategovernmentflag;
	}
	public String getStatutoryexceptiontofairopportunity() {
		return statutoryexceptiontofairopportunity;
	}
	public void setStatutoryexceptiontofairopportunity(
			String statutoryexceptiontofairopportunity) {
		this.statutoryexceptiontofairopportunity = statutoryexceptiontofairopportunity;
	}
	public String getStreetaddress() {
		return streetaddress;
	}
	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}
	public String getStreetaddress2() {
		return streetaddress2;
	}
	public void setStreetaddress2(String streetaddress2) {
		this.streetaddress2 = streetaddress2;
	}
	public String getStreetaddress3() {
		return streetaddress3;
	}
	public void setStreetaddress3(String streetaddress3) {
		this.streetaddress3 = streetaddress3;
	}
	public String getSubcontractplan() {
		return subcontractplan;
	}
	public void setSubcontractplan(String subcontractplan) {
		this.subcontractplan = subcontractplan;
	}
	public String getSystemequipmentcode() {
		return systemequipmentcode;
	}
	public void setSystemequipmentcode(String systemequipmentcode) {
		this.systemequipmentcode = systemequipmentcode;
	}
	public String getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}
	public Long getTransactionnumber() {
		return transactionnumber;
	}
	public void setTransactionnumber(Long transactionnumber) {
		this.transactionnumber = transactionnumber;
	}
	public String getTribalgovernmentflag() {
		return tribalgovernmentflag;
	}
	public void setTribalgovernmentflag(String tribalgovernmentflag) {
		this.tribalgovernmentflag = tribalgovernmentflag;
	}
	public String getTypeofcontractpricing() {
		return typeofcontractpricing;
	}
	public void setTypeofcontractpricing(String typeofcontractpricing) {
		this.typeofcontractpricing = typeofcontractpricing;
	}
	public String getTypeofidc() {
		return typeofidc;
	}
	public void setTypeofidc(String typeofidc) {
		this.typeofidc = typeofidc;
	}
	public String getTypeofsetaside() {
		return typeofsetaside;
	}
	public void setTypeofsetaside(String typeofsetaside) {
		this.typeofsetaside = typeofsetaside;
	}
	public Date getUltimatecompletiondate() {
		return ultimatecompletiondate;
	}
	public void setUltimatecompletiondate(Date ultimatecompletiondate) {
		this.ultimatecompletiondate = ultimatecompletiondate;
	}
	public String getUnique_transaction_id() {
		return unique_transaction_id;
	}
	public void setUnique_transaction_id(String unique_transaction_id) {
		this.unique_transaction_id = unique_transaction_id;
	}
	public String getUseofepadesignatedproducts() {
		return useofepadesignatedproducts;
	}
	public void setUseofepadesignatedproducts(String useofepadesignatedproducts) {
		this.useofepadesignatedproducts = useofepadesignatedproducts;
	}
	public String getVendor_cd() {
		return vendor_cd;
	}
	public void setVendor_cd(String vendor_cd) {
		this.vendor_cd = vendor_cd;
	}
	public String getVendor_state_code() {
		return vendor_state_code;
	}
	public void setVendor_state_code(String vendor_state_code) {
		this.vendor_state_code = vendor_state_code;
	}
	public String getVendoralternatename() {
		return vendoralternatename;
	}
	public void setVendoralternatename(String vendoralternatename) {
		this.vendoralternatename = vendoralternatename;
	}
	public String getVendoralternatesitecode() {
		return vendoralternatesitecode;
	}
	public void setVendoralternatesitecode(String vendoralternatesitecode) {
		this.vendoralternatesitecode = vendoralternatesitecode;
	}
	public String getVendorcountrycode() {
		return vendorcountrycode;
	}
	public void setVendorcountrycode(String vendorcountrycode) {
		this.vendorcountrycode = vendorcountrycode;
	}
	public String getVendordoingasbusinessname() {
		return vendordoingasbusinessname;
	}
	public void setVendordoingasbusinessname(String vendordoingasbusinessname) {
		this.vendordoingasbusinessname = vendordoingasbusinessname;
	}
	public String getVendorenabled() {
		return vendorenabled;
	}
	public void setVendorenabled(String vendorenabled) {
		this.vendorenabled = vendorenabled;
	}
	public String getVendorlegalorganizationname() {
		return vendorlegalorganizationname;
	}
	public void setVendorlegalorganizationname(String vendorlegalorganizationname) {
		this.vendorlegalorganizationname = vendorlegalorganizationname;
	}
	public String getVendorlocationdisableflag() {
		return vendorlocationdisableflag;
	}
	public void setVendorlocationdisableflag(String vendorlocationdisableflag) {
		this.vendorlocationdisableflag = vendorlocationdisableflag;
	}
	public String getVendorsitecode() {
		return vendorsitecode;
	}
	public void setVendorsitecode(String vendorsitecode) {
		this.vendorsitecode = vendorsitecode;
	}
	public String getVeteranownedflag() {
		return veteranownedflag;
	}
	public void setVeteranownedflag(String veteranownedflag) {
		this.veteranownedflag = veteranownedflag;
	}
	public String getWalshhealyact() {
		return walshhealyact;
	}
	public void setWalshhealyact(String walshhealyact) {
		this.walshhealyact = walshhealyact;
	}
	public String getWomenownedflag() {
		return womenownedflag;
	}
	public void setWomenownedflag(String womenownedflag) {
		this.womenownedflag = womenownedflag;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String toString() {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return  ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return super.toString();		
	}
	
}
