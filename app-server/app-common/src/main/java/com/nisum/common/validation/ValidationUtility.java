package com.nisum.common.validation;


/**
 * The class {@code ValidationUtility} includes methods 
 * for validating certain user inputs 
 *  
 *  
 * @author Owais Majid
 *
 */
public class ValidationUtility {
	
	
	/**
	 *  Validation of Zipcodes against US States.
	 *
	 * @param stateName the state name
	 * @param zipCode the zip code
	 * @return true, if is zip code valid
	 */
	public static boolean isZipCodeValid(String stateName, String zipCode) {
		if(zipCode.length()<5) return false;
		
		
		boolean isValid=true;
		int zipCodeStart3Digits=Integer.parseInt(zipCode.substring(0,3));
		int zipCodeStart2Digits=Integer.parseInt(zipCode.substring(0,2));
		int zipCodeStart1Digit=Integer.parseInt(zipCode.substring(0,1));
		
		
		switch(stateName){
		case "AL": isValid=(zipCodeStart3Digits >= 350 && zipCodeStart3Digits <= 369);
		break;
		case "AK": isValid=(zipCodeStart3Digits >= 995 && zipCodeStart3Digits <= 999);
		break;
		case "AZ": isValid=(zipCodeStart3Digits >= 850 && zipCodeStart3Digits <= 865);
		break;
		case "AR": isValid=((zipCodeStart3Digits >= 716 && zipCodeStart3Digits <= 729) || (zipCodeStart3Digits == 755));
		break;
		case "AS": isValid=(zipCodeStart3Digits == 967);
		break;
		case "CA": isValid=(zipCodeStart3Digits >= 900 && zipCodeStart3Digits <= 966);
		break;
		case "CO": isValid=(zipCodeStart3Digits >= 800 && zipCodeStart3Digits <= 816);
		break;
		case "CT": isValid=(zipCodeStart3Digits >= 60 && zipCodeStart3Digits <= 69);
		break;
		case "DC": isValid=(zipCodeStart3Digits >= 200 && zipCodeStart3Digits <= 205);
		break;
		case "DE": isValid=(zipCodeStart3Digits >= 197 && zipCodeStart3Digits <= 199);
		break;
		case "FL": isValid=((zipCodeStart3Digits >= 320 && zipCodeStart3Digits <= 349) && (zipCodeStart3Digits != 343 && zipCodeStart3Digits !=345 && zipCodeStart3Digits!=348));
		break;
		case "GA": isValid=(zipCodeStart3Digits >= 300 && zipCodeStart3Digits <= 319);
		break;
		case "GU": isValid=(zipCodeStart3Digits == 969);
		break;
		case "HI": isValid=(zipCodeStart3Digits >= 967 && zipCodeStart3Digits <= 968);
		break;
		case "ID": isValid=(zipCodeStart3Digits >= 832 && zipCodeStart3Digits <= 838);
		break;
		case "IL": isValid=(zipCodeStart3Digits >= 600 && zipCodeStart3Digits <= 629);
		break;
		case "IN": isValid=(zipCodeStart3Digits >= 460 && zipCodeStart3Digits <= 479);
		break;
		case "IA": isValid=(zipCodeStart3Digits >= 500 && zipCodeStart3Digits <= 528);
		break;
		case "KS": isValid=(zipCodeStart3Digits >= 660 && zipCodeStart3Digits <= 679);
		break;
		case "KY": isValid=(zipCodeStart3Digits >= 400 && zipCodeStart3Digits <= 427);
		break;
		case "LA": isValid=(zipCodeStart3Digits >= 700 && zipCodeStart3Digits <= 714);
		break;
		case "ME": isValid=(zipCodeStart3Digits >= 39 && zipCodeStart3Digits <= 49);
		break;
		case "MH": isValid=(zipCodeStart3Digits == 969);
		break;
		case "MD": isValid=(zipCodeStart3Digits >= 206 && zipCodeStart3Digits <= 219);
		break;
		case "MA": isValid=((zipCodeStart2Digits >= 10 && zipCodeStart2Digits <= 27) || (zipCodeStart2Digits == 55));
		break;
		case "MI": isValid=(zipCodeStart3Digits >= 480 && zipCodeStart3Digits <= 499);
		break;
		case "MN": isValid=(zipCodeStart3Digits >= 550 && zipCodeStart3Digits <= 567);
		break;
		case "MS": isValid=(zipCodeStart3Digits >= 386 && zipCodeStart3Digits <= 397);
		break;
		case "MO": isValid=(zipCodeStart3Digits >= 630 && zipCodeStart3Digits <= 658);
		break;
		case "MT": isValid=(zipCodeStart3Digits >= 590 && zipCodeStart3Digits <= 599);
		break;
		case "NE": isValid=(zipCodeStart3Digits >= 680 && zipCodeStart3Digits <= 693);
		break;
		case "NV": isValid=(zipCodeStart3Digits >= 889 && zipCodeStart3Digits <= 898);
		break;
		case "NH": isValid=(zipCodeStart3Digits >= 30 && zipCodeStart3Digits <= 38);
		break;
		case "NJ": isValid=(zipCodeStart2Digits >= 70 && zipCodeStart2Digits <= 89);
		break;
		case "NM": isValid=(zipCodeStart3Digits >= 870 && zipCodeStart3Digits <= 884);
		break;
		case "NY": isValid=((zipCodeStart2Digits >= 90 && zipCodeStart2Digits <= 149) || (zipCodeStart1Digit == 4) || (zipCodeStart2Digits == 63));
		break;
		case "NC": isValid=(zipCodeStart3Digits >= 269 && zipCodeStart3Digits <= 289);
		break;
		case "ND": isValid=(zipCodeStart3Digits >= 580 && zipCodeStart3Digits <= 588);
		break;
		case "MP": isValid=(zipCodeStart3Digits == 969);
		break;
		case "OH": isValid=(zipCodeStart3Digits >= 430 && zipCodeStart3Digits <= 458);
		break;
		case "OK": isValid=(zipCodeStart3Digits >= 730 && zipCodeStart3Digits <= 749);
		break;
		case "OR": isValid=(zipCodeStart3Digits >= 970 && zipCodeStart3Digits <= 979);
		break;
		case "PA": isValid=(zipCodeStart3Digits >= 150 && zipCodeStart3Digits <= 196);
		break;
		case "PR": isValid=(zipCodeStart1Digit >= 6 && zipCodeStart1Digit <= 9);
		break;
		case "RI": isValid=(zipCodeStart2Digits >= 28 && zipCodeStart2Digits <= 29);
		break;
		case "SC": isValid=(zipCodeStart3Digits >= 290 && zipCodeStart3Digits <= 299);
		break;
		case "SD": isValid=(zipCodeStart3Digits >= 570 && zipCodeStart3Digits <= 577);
		break;
		case "TN": isValid=(zipCodeStart3Digits >= 370 && zipCodeStart3Digits <= 385);
		break;
		case "TX": isValid=((zipCodeStart3Digits >= 750 && zipCodeStart3Digits <= 799) || (zipCodeStart3Digits == 885));
		break;
		case "UT": isValid=(zipCodeStart3Digits >= 840 && zipCodeStart3Digits <= 847);
		break;
		case "VT": isValid=(zipCodeStart2Digits >= 50 && zipCodeStart2Digits <= 59);
		break;
		case "VA": isValid=((zipCodeStart3Digits >= 220 && zipCodeStart3Digits <= 246) || (zipCodeStart3Digits == 201));
		break;
		case "VI": isValid=(zipCodeStart1Digit == 8);
		break;
		case "WA": isValid=(zipCodeStart3Digits >= 980 && zipCodeStart3Digits <= 994);
		break;
		case "WI": isValid=(zipCodeStart3Digits >= 530 && zipCodeStart3Digits <= 549);
		break;
		case "WV": isValid=(zipCodeStart3Digits >= 247 && zipCodeStart3Digits <= 268);
		break;
		case "WY": isValid=(zipCodeStart3Digits >= 820 && zipCodeStart3Digits <= 831);
		break;
		case "AE": isValid=(zipCodeStart2Digits >= 90 && zipCodeStart2Digits <= 98);
		break;
		case "AA": isValid=(zipCodeStart3Digits == 340);
		break;
		case "AP": isValid=(zipCodeStart3Digits >= 962 && zipCodeStart3Digits <= 966);
		break;
		}
		

		
		
		return isValid;
		
		
	}

}
