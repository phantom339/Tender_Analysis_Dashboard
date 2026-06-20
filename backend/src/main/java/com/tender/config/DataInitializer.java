package com.tender.config;

import com.tender.model.Tender;
import com.tender.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TenderRepository tenderRepository;

    @Override
    public void run(String... args) {
        if (tenderRepository.count() > 0) {
            System.out.println("Database already has data. Skipping initialization.");
            return;
        }

        System.out.println("Seeding tender data from Excel...");

        tenderRepository.save(new Tender("Airports Authority Of India", "Airport/Aviation", "Rajiv Gandhi Bhawan, Safdarjung Airport, New Delhi - 110003", "Rajiv Gandhi Bhawan, Safdarjung Airport, New Delhi - 110003", "Sunil Kumar", "DGM (E-C)", "011-24657900", "gme-bihta@aai.aero", "Yes", "NOT REACHABLE", "TRIED CALLING MANY TIMES"));

        tenderRepository.save(new Tender("National Highway Authority of India", "Construction/Maintenance", "Sector-10, Dwarka, New Delhi", "Sector-10, Dwarka, New Delhi", "Tushar Vyas", "General Manager", "Extn: 1434", "tusharvyas@nhai.org", "Yes", "Has Aon as their Risk Advisor", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Indian Highways Management Company Limited", "Construction/Maintenance", "Second Floor, Asia Bhawan, Sector 9, Dwarka, New Delhi", "Second Floor, Asia Bhawan, Sector 9, Dwarka, New Delhi", "A R Chitranshi", "Chief Operating Officer", "011-25074200", "tenders@ihmcl.com", "Yes", "Already have a broker", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("RailTel Corporation of India", "Data Center", "Plate-A, 6th Flr, Office Block Tower-2, East Kidwai Nagar, New Delhi - 110023", "Plate-A, 6th Flr, Office Block Tower-2, East Kidwai Nagar, New Delhi - 110023", "Sanjai Kumar", "Chairman & Managing Director", "011-22900600", "cmdrailtel@railtelindia.com", null, "Already closed their tender in March", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("National Small Industries Corporation", "MSME", "NSIC Bhawan, Okhla Nsic, New Delhi", "NSIC Bhawan, Okhla Nsic, New Delhi", "Sujay Khattri", "Insurance Head", "7292067025", "hoadmin@nsic.co.in", "Yes", "GMC placed/ Tender for Asset will open in November. Done a physical with them they are open to onboard insurance brokers.", "Meeting Done (Onboarding in Process)"));

        tenderRepository.save(new Tender("GAIL (India) Limited", "Energy Storage", "GAIL Bhawan, 16 Bhikaji Cama Place, R.K. Puram, New Delhi", "GAIL Bhawan, 16 Bhikaji Cama Place, R.K. Puram, New Delhi", "Satya Prakash Srivastava", "Chief Manager (Project)", "011-26172580", "slrnoida@gail.co.in", "Yes", "Closed their broker empanelment tender in April", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("ESE Energy Private Limited", "Ethanol", "Upper Ground Floor, A-4, Krishna Nagar, East Delhi, Delhi", "Upper Ground Floor, A-4, Krishna Nagar, East Delhi, Delhi", "Anil Kumar Chaurasia", "Director", "9560118288", "eseenergy4@gmail.com", null, "Got no. of person named Mayank who handles insurance. He said he will get back after confirmation.", "Called and texted him again but still not responded"));

        tenderRepository.save(new Tender("Noida Metro Rail Corporation", "Infrastructure", "Block-III, 3rd Floor, Ganga Shopping Complex, Sector-29, Noida 201301", "Block-III, 3rd Floor, Ganga Shopping Complex, Sector-29, Noida 201301", "Mr. CA Rajan Prakash", "DGM Finance", "120-2210603", "nmrcnoida@gmail.com", "Yes", "Placed for this year, with another broker", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Vidya Green Ventures Private Limited", "Ethanol", "Plot No.78, Ground Floor, Block-B Wazirpur Industrial Area, Delhi - 110052", "Plot No.78, Ground Floor, Block-B Wazirpur Industrial Area, Delhi - 110052", "Dharam Pal Aggarwal", "Director", null, "vidyagreenventures@gmail.com", null, "NOT REACHABLE", null));

        tenderRepository.save(new Tender("Bakshi Land and Development Private Limited", "Hospitality", "OSE Commercial Block, Hotel Aloft, Asset 5B, Aerocity, Hospitality District, IGI Airport, New Delhi", "OSE Commercial Block, Hotel Aloft, Asset 5B, Aerocity, Hospitality District, IGI Airport, New Delhi", "Amit Kumar", "Insurance Concerned Person", "011-46044604", "ose.secretarial@gmail.com", "No", "Do not involve Brokers. Given their email and phone no. asked to send the company profile.", null));

        tenderRepository.save(new Tender("India Tourism Development Corporation", "Hospitality", "SCOPE COMPLEX, 6th Floor, Core 8, 7 Lodi Road, New Delhi - 110003", "SCOPE COMPLEX, 6th Floor, Core 8, 7 Lodi Road, New Delhi - 110003", "Mr. Aryan Gyanchandani", "Manager-Finance", "011-24307608", "directorfin@itdc.co.in", "Yes", "Last year was placed with K.M. Dastur Reinsurance Brokers Pvt. Ltd", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("The Coalition for Disaster Resilient Infrastructure", "Infrastructure", "IIPA Bhawan, Indian Institute of Public Administration, Indraprastha Estate, New Delhi", "IIPA Bhawan, Indian Institute of Public Administration, Indraprastha Estate, New Delhi", "Raj Vikram Singh", "Senior Specialist- Disaster Risk Financing", "011-35059999", "info@cdri.world", "Yes", "Our existing client", "Ongoing a tender process"));

        tenderRepository.save(new Tender("Delhi Metro Rail Corporation", "Infrastructure", "Metro Building, Fire Brigade Lane, Barakhamba Road, New Delhi, Delhi", "Metro Building, Fire Brigade Lane, Barakhamba Road, New Delhi, Delhi", "Manvendra Singh", "CE/Tender (O&M)", "011-23413960", "cetenderonm@dmrc.org", "Yes", "Marsh India Insurance Brokers Private Ltd. is the existing broker", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Uttar Bharat Hydro Power Pvt. Ltd", "Hydro Power", "Saraswati kunj, Golf Crse Rd, Gurugram, Haryana", "Saraswati kunj, Golf Crse Rd, Gurugram, Haryana", "Mr. Bhupender Jain", "VP", "0124-2574500", null, "No", "NOT REACHABLE", "TRIED CALLING MANY TIMES"));

        tenderRepository.save(new Tender("RITES-REMCL", "Railways", "PNB Building, Bhikaji Cama Place, New Delhi", "PNB Building, Bhikaji Cama Place, New Delhi", "Sh. Rakesh Gupta", "Chief Financial Officer", "9811915501", "rakeshgupta1@rites.com", "Yes", "Currently Prudent Insurance Brokers Private Limited is the appointed broker", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("NAFED", "Fertilizers", "Ring Road, New Delhi", "Ring Road, New Delhi", "Saurav Sharma", "Insurance Head", "8510991777", "sharma210785@gmail.com", "No", "Current policy placed from IFFCO Tokyo", "On Leave asked to get back in 1st week of July"));

        tenderRepository.save(new Tender("National Highway Logistics Management Limited", "Logistic Park", "D-21, Corporate Park, Sector-21, Dwarka, New Delhi", "D-21, Corporate Park, Sector-21, Dwarka, New Delhi", "Raj Singh Chauhan", "VP Logistics", "011-25074100, Extn: 3419", "rajsingh.chauhan@nhlml.org", null, "NOT REACHABLE", null));

        tenderRepository.save(new Tender("Hindustan Syringes and Medical Devices Limited", "Medical Devices", "Narain Manzil, 23, Barakhamba Road, New Delhi", "Narain Manzil, 23, Barakhamba Road, New Delhi", "Sohail Nath", "Executive Director", "011-23314785", "legal@hmdhealthcare.com", "Yes", "Talked, told them insurance concerned person sit in factory office called but was not reachable asked to drop a mail with the proposal", "Called again told that have already placed this years policies. Not interested in meeting."));

        tenderRepository.save(new Tender("Oil and Natural Gas Corporation Limited", "Oil & Gas", "Deendayal Urja Bhavan, 5, Nelson Mandela Marg, Vasant Kunj, New Delhi", "Deendayal Urja Bhavan, 5, Nelson Mandela Marg, Vasant Kunj, New Delhi", "Rajeeva Kumar", "Chief General Manager (Environment)", "011-26750998", "head_env@ongc.co.in", "No", "Does business directly with UNITED INDIA INSURANCE COMPANY.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Rail Vikas Nigam Limited", "Railways", "1st Floor, August Kranti Bhawan, Bhikaji Cama Place, Africa Avenue, New Delhi", "1st Floor, August Kranti Bhawan, Bhikaji Cama Place, Africa Avenue, New Delhi", "Vivek Kumar", "General Manager (Projects & CC)", "011-26738553", "vivek.kumar2@rvnl.org", "No", "Do not involve insurance brokers.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("FIEO – Federation of Indian Export Organisations", "Trade Promotion / Government Body", "Niryat Bhawan, Rao Tula Ram Marg, Opp. Army Hospital R&R, New Delhi – 110057", "Niryat Bhawan, Rao Tula Ram Marg, Opp. Army Hospital R&R, New Delhi – 110057", "Mrs. Nirmala Tete", "Director Admin", "011-46042161", "nirmalatete@fieo.org", "Yes", "Policy already placed have UNISON insurance Broking Services Pvt. Ltd as their current insurance broker.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("VDMInfra Projects LLP", "Real Estate", "A-22, Sector-P3, Greater Noida, Gautam Buddha Nagar", "A-22, Sector-P3, Greater Noida, Gautam Buddha Nagar", "Sanjeev Kumar", null, "8800118150", "vdminfraprojects@gmail.com", "No", "Called on 9 Jun 2026, Doesn't involve insurance brokers", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Gabion Technologies India Limited", "Geotechnical", "38, 2nd floor, Mohhamadpur, Near Bhikaji Cama Place, New Delhi", "38, 2nd floor, Mohhamadpur, Near Bhikaji Cama Place, New Delhi", "Madhusudan Sarda", "Managing Director", "011-46321074", "info@gabionindia.com", null, "NOT REACHABLE", null));

        tenderRepository.save(new Tender("NTPC Limited", "Power / Energy", "NTPC Bhawan, SCOPE Complex, Lodhi Road, New Delhi – 110 003", "NTPC Bhawan, SCOPE Complex, Lodhi Road, New Delhi – 110 003", "Shri Anil Kumar Jadli", "Director – HR", "011-24360100", "insurance@ntpc.co.in", "Yes", "ISSUE TENDER FOR APPOINTMENT OF INSURANCE BROKER (May)", "Placed for this year not interested in meeting"));

        tenderRepository.save(new Tender("SAIL", "Steel/Manufacturing", "Ispat Bhawan, Lodhi Road, New Delhi – 110 003", "Ispat Bhawan, Lodhi Road, New Delhi – 110 003", "Sh. Prabal Sandhu", "Sr.Manager(HR)", "011-24300333", "pers.itb.med@sail.in", "No", "Doesn't have appointed insurance broker, issue tender for GMC Policy for retired employees.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Power Finance Corporation", "NBFC", "Urjanidhi, 1 Barakhamba Lane, Connaught Place, New Delhi – 110 001", "Urjanidhi, 1 Barakhamba Lane, Connaught Place, New Delhi – 110 001", "G Jawahar", "General Manager(HR & Admin)", "011-23456781", "hr@pfcindia.com", "Yes", "Its subsidiary, PFC consulting Ltd, conducts periodic e-tenders to appoint Lenders Insurance Advisors (LIA) on a Project by Project Basis.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("NBCC Ltd.", "Construction / Real Estate / Govt", "NBCC Bhawan, Lodhi Road, New Delhi – 110 003", "NBCC Bhawan, Lodhi Road, New Delhi – 110 003", "Raveesh Sethi", "General Manager(HRM)", "011-24366988", "raveesh_13@yahoo.com", "No", "Do not Appoint Broker instead issue tender in which Insurance company and Broker both can participate.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("World Association of Small and Medium Enterprises", "MSME", "WASME International Secretariat, Plot no.4 Sector 16 A Film City Noida", "WASME International Secretariat, Plot no.4 Sector 16 A Film City Noida", "Sanjiv Layke", "Executive Secretary", "9911500858", "es@wasmeinfo.org", "Can Involve", "Offers a Membership programme and once a part offer platform to expand insurance services", "Done"));

        tenderRepository.save(new Tender("SJVNL (Satluj Jal Vidyut Nigam)", "Electricity", "Shakti Sadan, Corporate Office Complex, Shannon, Shimla, Himachal Pradesh – 171006", "Office Block, Tower #1, 6th Floor, NBCC Complex, East Kidwai Nagar, New Delhi – 110023", "Mr. Sanjeev Gupta", "Manager-Finance", "011-61901919", "liaison.delhi@sjvn.nic.in", "No", "Do not appoint brokers", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("SPMCIL – Security Printing & Minting Corporation", "Currency / Security Printing / PSU", "3rd Floor, Tower-G, World Trade Centre, Nauroji Nagar, New Delhi – 110029", "3rd Floor, Tower-G, World Trade Centre, Nauroji Nagar, New Delhi – 110029", "Mr. Hema", "AM-F", "011-23701225", "info@spmcil.com", "Yes", "Currently have SMC insurance brokers, closed the tender in Feb.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("CWC (Central Warehousing Corporation)", "Warehousing / Logistics / PSU", "4/1 Siri Institutional Area, Hauz Khas, New Delhi – 110016", "4/1 Siri Institutional Area, Hauz Khas, New Delhi – 110016", "Ms. Shabdita Singh", "AGM (G)", "9582373705", "singhshabdita@cewacor.nic.in", "Yes", "Already have a broker", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Indraprastha Institute Of Information Technology", "Education", "IIIT Delhi Okhla Ph-III, New Delhi", "IIIT Delhi Okhla Ph-III, New Delhi", "Mr. Mohit Kumar", "Tender Management", "Extn.-431", "info@iiitd.ac.in", "No", "Issue tender for various insurance requirement in which broker as well as insurance company can participate", "Eligibility criteria met but failed to apply as quotes did not come."));

        tenderRepository.save(new Tender("NFL", "Fertilizers", "SCOPE Complex, Core-III, 7, Institutional Area, Lodhi Road, New Delhi-110003", "A-11, Sector-24, NOIDA-201301 (U.P.)", "Mr. S.K. Prajapati", "GM (F&A)", "9818580830", "skprajapati@nfl.co.in", "Yes", "Issue tender for appointment of insurance broker", "We have applied successfully"));

        tenderRepository.save(new Tender("BECIL", "Broadcasting", "BECIL BHAWAN, C56 A/17 Sector62, Noida -201307 U.P.", "BECIL BHAWAN, C56 A/17 Sector62, Noida -201307 U.P.", "Mr. Harmohan", "Insurance Personal", "120-4177850", null, "Yes", "New tender for policy of GMC closing on 17th June", "Meeting Done"));

        tenderRepository.save(new Tender("HITES (HLL Infratech Service Limited)", "Infrastructure", "B-14 A, Sector 62, Noida-201 307", "B-14 A, Sector 62, Noida-201 307", "Chunnu Mukamina", "Senior HR", "120-4071500", "cmukambika@hllhites.com", "No", "Issue tender only for insurance company", "Contacted with reference to GPA tender, does not entertain broker"));

        tenderRepository.save(new Tender("IPA (Indian Port Association)", "Port and Marine", "Bhishma Pitamah Marg, New Delhi, Delhi 110003", "Bhishma Pitamah Marg, New Delhi, Delhi 110003", "Mr. Sudesh Tikku", "HR", "022-24368334", null, "No", "Issue tender for HULL for both broker and insurance company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("National Film Development Corporation Limited", "Information and Broadcasting", "NFDC - FD Complex 24, Dr. Gopalrao Deshmukh Marg, Mumbai 400 026, Maharashtra.", "Room No.-401, 4th Floor, Soochna Bhavan, Phase 1, C.G.O Complex, Lodhi Road, New Delhi 110 003.", "Lalit Prasad Nauni", "Tender Management", "011-24369462", "lalit@nfdcindia.com", "No", "Issue tender for brokers and insurance company.", "Has an open tender for motor insurance"));

        tenderRepository.save(new Tender("Indian Institute of Technology (ISM)", "Education", "IIT (ISM), Dhanbad, Jharkhand", "IIT Delhi, Hauz Khas, New Delhi", "Ravi Kumar Chaurasia", "Deputy Registrar", "326-223-5608", "jrdtd@iitism.ac.in", "No", "Final Bid Submission Date-30th June, for student insurance policy. Open for broker and insurance company", "Tender No. SW-INS-CPPP-004-26-27, Tried calling many times"));

        tenderRepository.save(new Tender("Central U.P Gas Limited", "Joint Venture of GAIL and BPCL", "7th Floor, UPSIDC Complex, A-1/4 Lakhanpur, Kanpur -208024, U.P", "7th Floor, UPSIDC Complex, A-1/4 Lakhanpur, Kanpur -208024, U.P", "Mr. Shekhar Kankrej", "Tender Management", "9511118781", "shekhar.kankrej@cugl.co.in", "No", "Issue tender for both broker and insurance company", "CUGL/C&P/TEN2627/14 tender for renewal of asset policy insurance."));

        tenderRepository.save(new Tender("DSIIC (Delhi State Industrial and Infrastructure Development Corporation)", "Infrastructure", "N-36, Bombay Life Building, Connaught Circus, New Delhi - 110001", "N-36, Bombay Life Building, Connaught Circus, New Delhi - 110001", "Sh. Narender Kumar Singh", "Director(Finance)", "23730261", "fadsiidc@gmail.com", "No", "Does not involve broker placed insurance need directly through tenders", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("HIL India Ltd.", "Chemicals", "SCOPE Complex, Core-6, 7, Lodi Road, New Delhi-110003", "SCOPE Complex, Core-6, 7, Lodi Road, New Delhi-110003", "Shri Srinivasa Raju", "Director(Finance)", "011-24364234", "commercialho@hil.gov.in", "Yes", "Issue tender for broker empanelment have closed the process in April", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Central Medical Service Society", "Pharmaceuticals", "2nd Floor, Vishwa Yuvak Kendra, Teen Murti Marg, Chanakyapuri, New Delhi-110021.", "2nd Floor, Vishwa Yuvak Kendra, Teen Murti Marg, Chanakyapuri, New Delhi-110021.", "Anurodh Singh", "Tender Management", "011-21410905", "gmlogistics@cmss.gov.in", "No", "They issue their tender only for insurance company. Brokers can participate if authorised from insurance company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("GAIL India Ltd.", "Petroleum and Gas", "GAIL Jubilee Tower, B-35 & 36, Sector-1, Noida-201301", "GAIL Bhawan, 16 Bhikaiji Cama Place, R K Puram, New Delhi - 110066", "Mr. Alankar Sahu", "Sr. Officer (C&P)", "05683-230329", "alankar.sahu@gail.co.in", "No", "Have an active tender which closes on July 1, eligible for broker and insurance company", "Pre bid meeting on 13 June"));

        tenderRepository.save(new Tender("Software Tech Parks of India", "IT/Tech", "1st Floor, Plate B, Office Block-1, East Kidwai Nagar, New Delhi - 110023", "1st Floor, Plate B, Office Block-1, East Kidwai Nagar, New Delhi - 110023", "Sandeep Nair", "Tender Management", "011-24628081", "con8.stpis.mp@gembuyer.in", "No", "Neither involves broker nor allows them to participate in tenders.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("BHEL (Bharat Heavy Electronics Limited)", "Electronics", "Corporate Office, BHEL House, Siri Fort, New Delhi - 110049", "Industry Sector, Integrated Office Complex, Lodhi Road, New Delhi - 110003", "Mr. Neeraj Kumar", "Sr. DGM", "011-66337598", "webmail@bhel.in", "Yes", "They occasionally involve broker according to their need. Generally issue tender for empanelment of insurance.", "Tried calling not interested in meeting"));

        tenderRepository.save(new Tender("Delhi State Civil Supplies Corporation Ltd", "Supplies", "AAPURTI BHAWAN, 7-9, ARAM BAGH, NEW DELHI – 110055", "AAPURTI BHAWAN, 7-9, ARAM BAGH, NEW DELHI – 110055", "Shri Jai Prakash", "GM (F&A)", "011-23610120", "dscsc@delhi.gov.in", "No", "They take care of the insurance requirement only through government owned insurance company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Department of Law (Lawyers Association of India)", "Legal", "Karkaddoma Court, New Delhi", "Karkaddoma Court, New Delhi", "DD Pandey", "Secretary", "9810389119", "ddpandey119@gmail.com", "Yes", "Tender inviting GMC for advocates, do not have specific classification for brokers or company if technical criteria is qualified then eligible.", "Meeting Done"));

        tenderRepository.save(new Tender("HUDCO – Housing & Urban Development Corporation", "Housing", "HUDCO Bhawan, India Habitat Centre, Lodhi Road, New Delhi – 110003", "HUDCO Bhawan, India Habitat Centre, Lodhi Road, New Delhi – 110003", "Mrs. Ruchika", "Tender Management", "011-24648160", "ruchika@hudco.org", "No", "Do not directly involve insurance brokers issue tenders for both, closed tender in April.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Prasar Bharti", "Broadcasting", "Prasar Bharati House, Copernicus Marg, New Delhi – 110001", "Prasar Bharati House, Copernicus Marg, New Delhi – 110001", "Ms. Kanika Makhija", "Welfare section officer", "011-23421041", "groupinsurance.pb@gmail.com", "No", "Involves only insurance company for their requirement.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("NHPC (National Hydroelectric Power Corporation)", "Hydroelectricity", "NHPC Office Complex, Sector-33, Faridabad - 121003", "NHPC Office Complex, Sector-33, Faridabad - 121003", "Navin Kumar Singh", "GM(Civil) Division", "1292254677", "contcivil2-co@nhpc.nic.in", "No", "Only involve insurance company, and tender is also issue only for insurance company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("CDC (Center for Disease Control)", "Medicine", "Shamnath Marg, Civil Lines, Delhi 110054", "Shamnath Marg, Civil Lines, Delhi 110054", "Mr. Ruchika Khanna", "Head Support Services", "011-23913148", "nsrinivasan@cdcgroup.com", "No", "Does not involve broker in any sense.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("RFCL (Ramagundam Fertilizers and Chemical Limited)", "Fertilizers", "Scope Complex, Core-III 7, Institutional Area, Lodhi Road, New Delhi, 110003", "Scope Complex, Core-III 7, Institutional Area, Lodhi Road, New Delhi, 110003", "Mr. Sanjay Grover", "Manager-Finance", "120-2553600", "admin@rfcl.co.in", "Yes", "Issue tender for broker empanelment have closed the process", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("EGIS India Ltd.", "Engineering", "Egis Tower, Plot No 80, Sector-32, Gurgaon - 122001", "T-305, TF, Tirupati Plaza, Sector-XI, Dwarka, New Delhi, Delhi 110075", "Mr. Ashok Sharma", "Consultant", "0124-4787800", "Vivek.A@egis-india.com", null, "NOT REACHABLE", "Tried contacting many times"));

        tenderRepository.save(new Tender("BSES Rajdhani Power Ltd.", "Electricity", "BSES Bhawan, Nehru Place, New Delhi", "BSES Bhawan, Nehru Place, New Delhi", "Ms. Shilpa Suman", "General Manager(C&M)", "8800919123", "shipa.suman@reliancegroupindia.com", "Yes", "Have Marsh Insurance Broker as their advisory", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("EZOPS Technologies India Pvt. Ltd", "IT/Tech", "463 7th Ave #1504, New York, NY", "Discovery Tower, Sec 62, Noida", null, "HR", null, null, "Yes", "Current GMC Placed by LOCKTON Insurance Brokers", "Meeting Done"));

        tenderRepository.save(new Tender("Container Corporation of India", "Logistics & Shipping", "CONCOR Bhawan, C-3, Mathura Road, Opposite Apollo Hospital, New Delhi-110076", "CONCOR Bhawan, C-3, Mathura Road, Opposite Apollo Hospital, New Delhi-110076", "PK Agrawal", "Director(Domestic Division)", "011-41673010", "concor_biz@concorindia.com", "Yes", "Closed Broker Empanelment tender in February.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Power Grid Corporation of India", "Power Transmission", "B-9, Qutub Institutional Area, Katwaria Sarai, New Delhi", "Saudamini Plot No.-2, Sector-29, Gurgaon, Haryana", "Anoop Kumar", "Chief Manager(BD)", "0124-2571700", "powergrid_ncr@powergrid.in", "Yes", "Appoint broker for All Risk Insurance for transmission of assets, did on July 2025", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("HPCL (Hindustan Petroleum Corporation Limited)", "Oil & Gas", "Petroleum House, 17, Jamshedji Tata Road, Mumbai", "HPCL Regional Office, 8, Shoorji Vallabhdas Marg, Nehru Place, New Delhi", "Alok Kumar", "General Manager", "011-26444000", "hpcl_ncr@hpcl.in", "Yes", "Closed in April, works with Hindustan Insurance Brokers Limited. Uses Finhaat Insurance Broking Pvt. Ltd for employee benefit programs.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("BPCL (Bharat Petroleum Corporation Limited)", "Oil & Gas", "Bharat Bhavan, 4&6 Currimbhoy Road, Ballard Estate, Mumbai", "BPCL Office, Plot No. A-5&6, Sector-1, Noida, UP", "S.S Sundararajan", "Chief Manager(Marketing)", "0120-2474000", "bpcl_delhi@bharatpetroleum.in", "No", "Do not particularly engage brokers, issue policies through ICICI Lombard.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("CIL (Coal India Limited)", "Mining & Coal", "Coal Bhawan, Premise No-04 MAR, Plot No-AF-III, Action Area-1A, New Town, Rajahat, Kolkata", "Core 6, 6th Floor, SCOPE Minar, Laxmi Nagar District Centre, Delhi", "Manoj Kumar", "General Manager(Sales)", "011-22448860", "gmsales.cil@coalindia.in", "No", "Float Tenders only for Gov. Insurance Company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("DFCCIL (Dedicated Freight Corridor Corporation)", "Railway Infrastructure", "5th Floor, Supreme Court Metro Station Building Complex, New Delhi", "5th Floor, Supreme Court Metro Station Building Complex, New Delhi", "Hari Mohan Gupta", "Managing Director", "011-23454700", "MD@DFCCIL.CO.IN", "Yes", "Issue tender for broker appointment.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Engineers India Limited", "Engineering Consultancy", "Engineers India Bhavan, 1, Bhikaji Cama Place, New Delhi", "Engineers India Bhavan, 1, Bhikaji Cama Place, New Delhi", "Vartika Shukla", "Chairman & Managing Director", "022-26762121", "cmd@eil.co.in", "Yes", "Closed tender in February, AON Insurance Broker appointed.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("NBCC (India) Limited", "Construction/Real Estate", "NBCC Bhawan, Lodhi Road, New Delhi", "NBCC Bhawan, Lodhi Road, New Delhi", "K.P Mahdevaswamy", "Chairman & Managing Director", "011-24367314", "bdd@nbccindia.com", "No", "Conduct its policies directly with public sector insurance company.", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("Mahanagar Telephone Nigam Limited", "Telecom", "Mahanagar Doorsanchar Sadan, 5th Floor, 9 CGO Complex, Lodhi Road, New Delhi", "Mahanagar Doorsanchar Sadan, 5th Floor, 9 CGO Complex, Lodhi Road, New Delhi", "Ravi A. Robert Jerad", "Chairman & Managing Director", "011-24319020", "cmd@bol.net.in", "No", "Issue policy through tender only applicable to public sector insurance company", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("IRCON International Limited", "Construction/Railway", "C-4, District Center, Saket, New Delhi", "C-4, District Center, Saket, New Delhi", "Brijesh Kumar Gupta", "General Manager(HRM)", "011-29565666", "info@ircon.org", "Yes", "Currently have Global Insurance Private Limited", "Not Interested in Meeting"));

        tenderRepository.save(new Tender("FCI (Food Corporation of India)", "Logistics/Food Supply", "16-20, Barakhamba Lane, New Delhi", "16-20, Barakhamba Lane, New Delhi", null, null, null, null, null, null, null));

        System.out.println("Successfully seeded " + tenderRepository.count() + " tender records!");
    }
}
